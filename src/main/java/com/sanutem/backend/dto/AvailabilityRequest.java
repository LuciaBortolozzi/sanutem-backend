package com.sanutem.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvailabilityRequest {
    private String month;
    private String[] days;
    private String[] hours;
    private String nameReceptionist;
}
