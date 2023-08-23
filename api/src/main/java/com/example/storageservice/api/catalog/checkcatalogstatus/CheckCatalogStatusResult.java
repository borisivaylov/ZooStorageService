package com.example.storageservice.api.catalog.checkcatalogstatus;

import com.example.storageservice.api.base.OperationResult;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckCatalogStatusResult implements OperationResult {
    @JsonProperty
    private boolean catalogStatus;
}
