package com.info.local24.local24.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrainRequestResponse {
    private TrainRequest trainRequest;
    private boolean error;
    private String message;
}
