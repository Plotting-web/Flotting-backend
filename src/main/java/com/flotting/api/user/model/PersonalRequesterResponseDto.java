package com.flotting.api.user.model;

import com.flotting.api.user.entity.PersonalManagerRequesterEntity;
import lombok.Data;

@Data
public class PersonalRequesterResponseDto {

    private Long managerId;

    private Long requesterId;

    private String managerLetter;

    private String requesterLetter;

    public PersonalRequesterResponseDto(PersonalManagerRequesterEntity entity) {
        this.managerId = entity.getManager().getSeq();
        this.requesterId = entity.getRequester().getUserNo();
        this.managerLetter = entity.getManagerLetter();
        this.requesterLetter = entity.getRequesterLetter();
    }
}
