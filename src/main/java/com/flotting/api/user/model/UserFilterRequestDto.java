package com.flotting.api.user.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.flotting.api.user.enums.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@Slf4j
@ToString
@EqualsAndHashCode
public class UserFilterRequestDto {

    @Schema(description = "성별", allowableValues = {"M", "F"})
    List<GenderEnum> gender = new ArrayList<>();
    @Schema(description = "흡연여부", allowableValues = {"true", "false"})
    Boolean smoke;
    @Schema(description = "회원상태", allowableValues = {"NONE", "INPROGRESS", "REJECT", "WITHDRAWAL", "DORMANT", "NORMAL", "FORCED_WITHDRAWAL"})
    List<UserStatusEnum> userStatus = new ArrayList<>();

    @Schema(description = "등급", allowableValues = {"G","D","P"})
    List<GradeEnum> grade = new ArrayList<>();
    @Schema(description = "거주지", allowableValues = {"SEOUL_NORTH", "SEOUL_SOUTH", "SEOUL_WEST", "SEOUL_EAST",
            "GGYEONGGI_NORTH", "GGYEONGGI_SOUTH", "GGYEONGGI_WEST", "GGYEONGGI_EAST"
    })
    List<LocationEnum> location = new ArrayList<>();
    @Schema(description = "직업", allowableValues = {"PROFESSIONAL", "MID_MAJOR_COMPANY", "FINANCE", "PUBLIC_COMPANY", "EDU", "LAB", "MEDICAL",
                                                    "BUSNINESS", "SMALL_COMPANY", "FREELANCER", "STUDENT", "MAJOR_COMPANY", "MID_COMPANY", "MINOR_COMPANY", "STARTUP"})
    List<JobEnum> job = new ArrayList<>();
    @Schema(description = "신청 경로", allowableValues = {"SMALL_CLASS_C", "FRIP", "WADIZ", "NAVER_STORE", "RECOMMEND", "ETC"})
    List<AppliedPathEnum> appliedPath = new ArrayList<>();
    @Schema(description = "승인 매니저 id", format="Long")
    List<Long> managerIds = new ArrayList<>();
    @Schema(description = "승인일자", format = "YYYY-MM-DD HH:mm:ss", example = "{min:2024-01-01 00:00:00, max: 2025-01-01 00:00:00}")
    DateScopeModel approvedAt;

    @Schema(description = "키", example = "{min:150, max:200}")
    IntegerScopeModel height = new IntegerScopeModel();
    @Schema(description = "나이", example = "{min:20, max:45}")
    IntegerScopeModel age = new IntegerScopeModel();

    @Setter
    @Getter
    public class IntegerScopeModel {
        Integer min;

        Integer max;
    }

    @Setter
    @Getter
    public class DateScopeModel {

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime min;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime max;
    }

    public void setGender(List<String> genders) {
        ArrayList<GenderEnum> result = new ArrayList<>();
        genders.forEach(gender-> {
            GenderEnum parsedVal = GenderEnum.of(gender);
            if(Objects.isNull(parsedVal)) {
                log.error("No RequestedValue in GenderList! value : {}", gender);
                throw new IllegalArgumentException();
            }
            result.add(parsedVal);
        });
        if(result.size() > 0) {
            this.gender = result;
        }
    }
    public void setGrade(List<String> grades) {
        ArrayList<GradeEnum> result = new ArrayList<>();
        grades.forEach(value -> {
            GradeEnum parsedVal = GradeEnum.of(value);
            if(Objects.isNull(parsedVal)) {
                log.error("No RequestedValue in GradeList! value : {}", value);
                return;
            }
            result.add(parsedVal);
        });
        if(result.size() > 0) {
            this.grade = result;
        }
    }

    public void setLocation(List<String> locations) {
        ArrayList<LocationEnum> result = new ArrayList<>();
        locations.forEach(value -> {
            LocationEnum parsedVal = LocationEnum.of(value);
            if(Objects.isNull(parsedVal)) {
                log.error("No RequestedValue in LocationList! value : {}", value);
                return;
            }
            result.add(parsedVal);
        });

        if(result.size() > 0) {
            this.location = result;
        }
    }

    public void setJob(List<String> jobs) {
        ArrayList<JobEnum> result = new ArrayList<>();
        jobs.forEach(value -> {
            JobEnum parsedVal = JobEnum.of(value);
            if(Objects.isNull(parsedVal)) {
                log.error("No RequestedValue in LocationList! value : {}", value);
                return;
            }
            result.add(parsedVal);
        });
        if(result.size() > 0) {
            this.job = result;
        }
    }

    public void setHeight(IntegerScopeModel height) {
        if(height.getMin() < 150 || height.getMax() > 200) {
            log.error("Height must between(150,200) minVal: {} maxVal : {}", height.getMin(), height.getMax());
            throw new IllegalArgumentException("Height must between(150,200)");
        }
        this.height.setMin(height.getMin());
        this.height.setMax(height.getMax());
    }

    public void setAge(IntegerScopeModel age) {
        if(age.getMin() < 10 || age.getMax() > 60) {
            log.error("Age must between(1970,2024) minVal: {} maxVal : {}", age.getMin(), age.getMax());
            throw new IllegalArgumentException("Age must between(1970,2024)");
        }
        this.age.setMin(age.getMin());
        this.age.setMax(age.getMax());
    }

    public void setUserStatus(List<String> userStatuses) {
        ArrayList<UserStatusEnum> result = new ArrayList<>();
        userStatuses.forEach(value -> {
            UserStatusEnum parsedVal = UserStatusEnum.of(value);
            if(Objects.isNull(parsedVal)) {
                log.error("No RequestedValue in UserStatusList! value : {}", value);
                return;
            }
            result.add(parsedVal);
        });
        if(result.size() > 0) {
            this.userStatus = result;
        }
    }

    public void setAppliedPath(List<String> appliedPaths) {
        ArrayList<AppliedPathEnum> result = new ArrayList<>();
        appliedPaths.forEach(value -> {
            AppliedPathEnum parsedVal = AppliedPathEnum.of(value);
            if(Objects.isNull(parsedVal)) {
                log.error("No RequestedValue in AppliedPathList! value : {}", value);
                return;
            }
            result.add(parsedVal);
        });
        if(result.size() > 0) {
            this.appliedPath = result;
        }
    }

}
