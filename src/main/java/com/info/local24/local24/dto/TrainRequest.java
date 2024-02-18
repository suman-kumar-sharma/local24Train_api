package com.info.local24.local24.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class TrainRequest {

    @NotBlank
    private String trainNumber;
    @NotBlank(message = "name should not be blank")
    private String name;
    private List<String> station;
}
