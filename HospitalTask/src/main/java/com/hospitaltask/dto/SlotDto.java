package com.hospitaltask.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SlotDto {
    private String startTime;
    private String endTime;
    private String durationMinutes;
}
