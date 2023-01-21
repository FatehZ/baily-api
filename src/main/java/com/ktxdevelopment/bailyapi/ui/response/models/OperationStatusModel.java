package com.ktxdevelopment.bailyapi.ui.response.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OperationStatusModel {
    private String operationResult;
    private String operationName;
}