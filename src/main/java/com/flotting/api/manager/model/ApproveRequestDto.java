package com.flotting.api.manager.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApproveRequestDto {

    @Schema(description = "등급", example = "G", allowableValues = {"G", "D", "P"})
    private String grade;

    @Schema(description = "매니저 코멘트", example = "~~")
    private String comment;

    @Schema(description = "승인한 매니저ID", example = "342")
    private Long managerId;
}
