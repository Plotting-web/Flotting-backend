package com.flotting.api.manager.service;

import com.flotting.api.manager.entity.ManagerProfileEntity;
import com.flotting.api.manager.model.RejectRequestDto;
import com.flotting.api.manager.repository.ManagerRepository;
import com.flotting.api.user.entity.UserDetailEntity;
import com.flotting.api.user.entity.UserSimpleEntity;
import com.flotting.api.user.enums.GenderEnum;
import com.flotting.api.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ManagerService {

    private final ManagerRepository managerRepository;
    private final UserService userService;

    /**
     * 매니저 조회
     * @param managerId
     * @return
     */
    @Transactional(readOnly = true)
    public ManagerProfileEntity getManager(Long managerId) {
        ManagerProfileEntity manager = managerRepository.findById(managerId)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 매니저"));
        return manager;
    }

    /**
     * 매니저 프로필 승인
     * @param
     * @param detailProfileId
     *
     * 남자 : 직업, 키, 체형, 학력, 나이, 외모
     *
     * 여자 : 나이, 체형, 신장, 직업, 학력,외모
     */
    @Transactional
    public void approveInfo(Long detailProfileId) {
        ManagerProfileEntity manager = getManager(detailProfileId);
        UserDetailEntity detailProfile = userService.getDetailUser(detailProfileId);
        UserSimpleEntity simpleProfile = detailProfile.getUserSimpleEntity();
        detailProfile.approveProfile(manager);
        GenderEnum gender = detailProfile.getGender();
        log.info("Approve User : {} ", simpleProfile.getEmail());
    }

    @Transactional
    public void rejectInfo(Long detailProfileId, RejectRequestDto requestDto) {
        //TODO 세션으로 managerID가져오기 필요
        ManagerProfileEntity manager = getManager(detailProfileId);
        UserDetailEntity detailProfile = userService.getDetailUser(detailProfileId);
        detailProfile.rejectProfile(manager, requestDto.getReason());
    }
}