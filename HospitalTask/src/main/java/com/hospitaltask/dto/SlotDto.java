package com.hospitaltask.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@SuppressWarnings("unused")
public class SlotDto {
    private String startTime;
    private String endTime;
    private String durationMinutes;
}
