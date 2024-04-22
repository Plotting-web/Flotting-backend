package com.flotting.api.user.model;

import com.flotting.api.user.enums.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

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

    GenderEnum gender;
    boolean isSmoke;
    boolean isDormant;

    List<GradeEnum> grade;
    List<LocationEnum> location;
    List<JobEnum> job;

    UserStatusEnum userStatus;

    ScopeModel height;
    ScopeModel age;

    @Setter
    @Getter
    public class ScopeModel {
        Integer min;

        Integer max;
    }

    public void setGender(String gender) {
        GenderEnum parsedVal = GenderEnum.of(gender);
        if(Objects.isNull(parsedVal)) {
            log.error("No RequestedValue in GenderList! value : {}", gender);
            throw new IllegalArgumentException();
        }
        this.gender = parsedVal;
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

    public void setHeight(ScopeModel height) {
        if(height.getMin() < 150 || height.getMax() > 200) {
            log.error("Height must between(150,200) minVal: {} maxVal : {}", height.getMin(), height.getMax());
            throw new IllegalArgumentException("Height must between(150,200)");
        }
    }

    public void setAge(ScopeModel age) {
        if(age.getMin() < 1970 || age.getMax() > 2024) {
            log.error("Age must between(1970,2024) minVal: {} maxVal : {}", age.getMin(), age.getMax());
            throw new IllegalArgumentException("Age must between(1970,2024)");
        }
    }

    public void setUserStatus(String userStatus) {
        UserStatusEnum parsedVal = UserStatusEnum.of(userStatus);
        if(Objects.isNull(parsedVal)) {
            log.error("No RequestedValue in UserStatusList! value : {}", userStatus);
            throw new IllegalArgumentException();
        }
        this.userStatus = parsedVal;
    }
}
