package com.example.storageservice.api.catalog.checkcatalogstatus;

import com.example.storageservice.api.base.OperationInput;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckCatalogStatusInput implements OperationInput {

    @JsonProperty
    private UUID catalogId;
}
