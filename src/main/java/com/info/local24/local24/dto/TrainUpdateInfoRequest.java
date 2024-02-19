package com.info.local24.local24.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@Data
@AllArgsConstructor
public class TrainUpdateInfoRequest {

    private TrainRequest trainRequest;
    private String queryType;

}
