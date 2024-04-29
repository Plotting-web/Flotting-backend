package com.flotting.api.manager.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RejectRequestDto {

    @Schema(description = "매니저 반려사유", example = "~~")
    private String reason;

    @Schema(description = "승인한 매니저ID", example = "342")
    private Long managerId;
}
