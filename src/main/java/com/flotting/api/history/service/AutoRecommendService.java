package com.flotting.api.history.service;

import com.flotting.api.history.entity.AutoRecommendHistory;
import com.flotting.api.history.model.AutoRecommendedData;
import com.flotting.api.history.repository.AutoRecommendRepository;
import com.flotting.api.user.entity.UserDetailEntity;
import com.flotting.api.user.entity.UserSimpleEntity;
import com.flotting.api.user.enums.GradeEnum;
import com.flotting.api.user.model.UserDetailResponseDto;
import com.flotting.api.user.service.UserService;
import com.flotting.api.util.type.AutoRecommendProcessEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.collections4.SetUtils;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.compress.utils.Sets;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AutoRecommendService {

    private final UserService userService;
    private final AutoRecommendRepository autoRecommendRepository;
    private final MatchingHistoryService matchingHistoryService;

    @Transactional(readOnly = true)
    public List<AutoRecommendedData> getAccessibleAutoRecommendedProfile(Long receiverId) {
        List<AutoRecommendHistory> result = autoRecommendRepository.findByReceiver_UserNoAndAutoRecommendProcessEnum(receiverId, AutoRecommendProcessEnum.INPROGRESS);
        return result.stream().map(AutoRecommendedData::new).collect(Collectors.toList());
    }

    @Transactional
    public void updateProcess() {
        List<AutoRecommendHistory> autoRecommendHistories = autoRecommendRepository.findAll();
        autoRecommendHistories.forEach(autoRecommendHistory -> {
            /**
             * 7일 지난 자동 매칭 상태 업데이트
             * 프로필 내려줄 때 FAIL것들은 안내려주도록 api필요
             */
            LocalDateTime createdAt = autoRecommendHistory.getCreatedAt();
            boolean isExpired = createdAt.plusDays(7).isBefore(LocalDateTime.now());
            if(isExpired) {
                log.info("Auto Recommend History Receiver: {} Received_profile: {} requestedAt: {} isExpired: {}",
                        autoRecommendHistory.getReceiver(), autoRecommendHistory.getProfile(), createdAt, isExpired);
                autoRecommendHistory.updateProcess(AutoRecommendProcessEnum.FAIL);
            }
        });
    }

    public List<AutoRecommendedData> createAutoRecommend(String phoneNumber) {
        log.info("phoneNumber  : {}", phoneNumber);
        UserSimpleEntity user = userService.getUserByPhoneNumber(phoneNumber);
        log.info("name : {} user : {}", phoneNumber, user.getName());
        return createAutoRecommend(user.getUserNo());
    }
        /**
         * userSimpleEntity의 랜덤 2인 매칭
         * 이전에 소개받은 사람이 나와선 안된다.
         * 가입 후 2주동안은 한등급 높은 이성을 소개해준다.
         * 그 이후부터는 동일등급의 이성을 소개해준다.
         * 2명이상의 리스트가 나올경우, 나이차가 적은 사람 순으로 정렬해서 2명만 소개, 만약 3명이상이면 랜덤 2명고르기
         * 자동 매칭 결과가 2명 미만일 경우 프로필 대신에 매니저에게 문의주세요 문구생성
         */
    public List<AutoRecommendedData> createAutoRecommend(Long simpleProfileId) {
        List<AutoRecommendedData> recommendedList = new ArrayList<>();
        UserSimpleEntity targetUser = userService.getSimpleUser(simpleProfileId);
        UserDetailEntity targetDetailUser = targetUser.getUserDetailEntity();
        /**
         * 자동 매칭 이력에 존재하는 사람 제외
         * 플러팅 이력 존재하는 사람 제외
         * 가입 기간 2주 이하 이상 체크 필요
         *      < 2주 : 한등급 높은 이성등급 및 같은 등급2명 & 높은 등급 2명
         *      > 2주 : 동일 이성등급 2명
         * 2명 초과일 경우, 나이차 적은 순 오름차순 정렬하여 2명 필터
         * 이래도 2명 초과일 경우, 랜덤 2명 필터
         * 2명 미만일 경우, 매니저에게 문의주세요 문구 생성
         *
         * 4명 미만일 경우 :
         * 낮은등급 사람에서 찾기 없으면 더 낮은등급까지...
         *order by grade, age
         * p, d, g
         * p1명일 경우 & d 1명나오는지
         */
        /**
         * 2명: 한등급 -> 등급 내림차순으로 / 2명 : 동등급 -> 등급 내림차순으로
         * 첫번쿼리 : 한등급 위인거를 지정해서 날리고 2명 미만 나오면째
         * 두번째쿼리 : 동등급인거 쿼리
         * 세번째쿼리 : 등급순 orderby한 쿼리 날린다.
         * 승인일자 2주 이내, 한등급 위 2명, 동등급 2명 추천 order by 한등급위 -> 동등급 -> 하위등급 limit 4
         *                                                  ex : G : P -> G -> D = 2 -> 1 -> 3
         *                                                      P : D -> P -> G = 3 -> 2 -> 1
         *                                                      D : D -> P -> G = 3 -> 2 -> 1
         * 첫번째쿼리 : 동등급 쿼리 날리기
         * 등급순 orderby한 쿼리를 날린다.
         *        2주 이상, 동등급 2명 추천 order by 등동급 -> 한등급위 -> 하위등급 limit 2
         *                                                  ex : G : G -> P -> D = 1 -> 2 -> 3
         *                                                      P : P -> D -> G = 2 -> 3 -> 1
         *                                                      D : D -> P -> G = 3 -> 2 -> 1
         */
        Set<Long> exceptIds = getAlreadyRecommendedOrMatchedUsers(targetUser);
        Set<UserDetailResponseDto> result = getFutureRecommendedUsers(targetDetailUser.getGrade(), targetUser, exceptIds);
        result.forEach(data -> {
            exceptIds.add(data.getSeq());
        });

        boolean isSignedUpOverTwoWeeks = LocalDateTime.now().isAfter(targetDetailUser.getApprovedAt().plusWeeks(2));
        if(targetUser.isShouldReceiveFourAutoRecommend() || !isSignedUpOverTwoWeeks) {
            log.info("2명 추가 쿼리 ! simpleUser : {}", targetUser.getUserNo());
            GradeEnum targetGrade = GradeEnum.getUpperGrade(targetDetailUser.getGrade());
            result.addAll(getFutureRecommendedUsers(targetGrade, targetUser, exceptIds));
        }
        int totalSize = isSignedUpOverTwoWeeks ? 2 : 4;

        if (result.size() < totalSize) {
            log.info("매니저에게 문의해주세요");
            recommendedList.add(AutoRecommendedData.emptyData(targetUser.getName(), totalSize));
            return recommendedList;
        }

        result.forEach(data -> {
            AutoRecommendHistory history = AutoRecommendHistory.builder()
                    .receiver(targetUser)
                    .profile(userService.getSimpleUser(data.getSeq()))
                    .build();
            log.info("Save RecommendedHistory! receiverNo : {} recommendUser: {}", targetUser.getUserNo(), data.getSeq());
            AutoRecommendHistory savedEntity = autoRecommendRepository.save(history);
            recommendedList.add(new AutoRecommendedData(savedEntity));
        });
        return recommendedList;
    }
    
    private Set<UserDetailResponseDto> getFutureRecommendedUsers(GradeEnum targetGrade, UserSimpleEntity targetUser, Set<Long> exceptIds) {
        UserDetailEntity targetDetailUser = targetUser.getUserDetailEntity();
        Set<UserDetailResponseDto> result = new HashSet<>();

        List<UserDetailResponseDto> sameGradeData = userService.getUsersByGradeAndSimpleProfileIdsNotInLimit(targetGrade, targetDetailUser, exceptIds, 2);
        log.info("동등급 조회 쿼리 수행!! 타겟 등급 : {} 쿼리 결과 : {}", targetDetailUser.getGrade(), sameGradeData.toString());
        result.addAll(sameGradeData);
        if(result.size() < 2) {
            result.forEach(data -> exceptIds.add(data.getSeq()));
            List<UserDetailResponseDto> downGradeData = userService.getUsersBySimpleProfileIdsNotInLimit(targetDetailUser, exceptIds, 2 - result.size());
            log.info("하위 등급 조회 쿼리 결과 : {}", downGradeData.toString());
            result.addAll(downGradeData);
        }
        
        return result;
    }
    
    private Set<Long> getAlreadyRecommendedOrMatchedUsers(UserSimpleEntity targetUser) {
        /**
         * 자동추천 매칭 이력에 있으면 제외
         */
        Set<Long> alreadyAutoRecommendedUsers = getAutoRecommendedUsers(targetUser.getUserNo());
        /**
         * 플러팅 이력에 있으면 제외
         */
        Set<Long> alreadyMatchedUsers = matchingHistoryService.getMatchedUsers(targetUser.getUserNo());
        Set<Long> exceptIds = new HashSet<>();
        exceptIds.addAll(alreadyAutoRecommendedUsers);
        exceptIds.addAll(alreadyMatchedUsers);
        return exceptIds;
    }

    @Transactional(readOnly = true)
    public Set<Long> getAutoRecommendedUsers(Long targetUserId) {
        Set<Long> result = new HashSet<>();
        result.add(targetUserId);
        List<AutoRecommendHistory> datas = autoRecommendRepository.findByReceiver_UserNoOrProfile_UserNo(targetUserId, targetUserId);
        datas.forEach(data -> {
            result.add(data.getProfile().getUserDetailEntity().getSeq());
            result.add(data.getReceiver().getUserDetailEntity().getSeq());
        });
        result.remove(targetUserId);
        log.info("getAutoRecommendedUsers! targetUser : {} RecommendedUsers : {}", targetUserId, result.toString());
        return result;
    }

    @Transactional(readOnly = true)
    public List<AutoRecommendHistory> getAll() {
        return autoRecommendRepository.findAll();
    }

    @Transactional(readOnly = true)
    public boolean alreadyRecommendedProfileThisWeek(Long userId) {
        LocalDate now = LocalDate.now();
        TemporalField fieldISO = WeekFields.of(Locale.KOREA).dayOfWeek();
        LocalDateTime startDayOfThisWeek = now.with(fieldISO, 2).atStartOfDay();
        return autoRecommendRepository.existsByReceiver_UserNoAndCreatedAtGreaterThan(userId, startDayOfThisWeek);
    }
}
