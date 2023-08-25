package com.example.storageservice.api.catalog.autogenerate;

import com.example.storageservice.api.base.OperationResult;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AutoGenerateResult implements OperationResult {

    @JsonProperty
    private String status;
}
