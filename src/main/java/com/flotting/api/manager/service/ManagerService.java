package com.flotting.api.manager.service;

import com.flotting.api.manager.entity.ManagerProfileEntity;
import com.flotting.api.manager.model.ApproveRequestDto;
import com.flotting.api.manager.model.RejectRequestDto;
import com.flotting.api.manager.repository.ManagerRepository;
import com.flotting.api.user.entity.UserDetailEntity;
import com.flotting.api.user.entity.UserSimpleEntity;
import com.flotting.api.user.enums.GenderEnum;
import com.flotting.api.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.Manager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class ManagerService {

    private final ManagerRepository managerRepository;
    private final UserService userService;

    @Transactional
    public void makeSampleData() {
        ManagerProfileEntity managerProfileEntity = ManagerProfileEntity.sampleData();
        managerRepository.save(managerProfileEntity);
    }
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
     */
    @Transactional
    public void approveInfo(Long simpleProfileId, ApproveRequestDto approveRequestDto) {
        Long managerId = approveRequestDto.getManagerId();
        ManagerProfileEntity manager = Objects.nonNull(managerId) ? getManager(approveRequestDto.getManagerId()) : null;
        UserSimpleEntity simpleUser = userService.getSimpleUser(simpleProfileId);
        UserDetailEntity detailUser = simpleUser.getUserDetailEntity();
        detailUser.approveProfile(approveRequestDto, manager);
        log.info("Approve SimpleUserId : {} ManagerId : {}", simpleProfileId, manager.getSeq());
    }

    @Transactional
    public void rejectInfo(Long simpleProfileId, RejectRequestDto rejectRequestDto) {
        ManagerProfileEntity manager = getManager(rejectRequestDto.getManagerId());
        UserSimpleEntity simpleUser = userService.getSimpleUser(simpleProfileId);
        UserDetailEntity detailUser = simpleUser.getUserDetailEntity();
        detailUser.rejectProfile(rejectRequestDto, manager);
    }
}