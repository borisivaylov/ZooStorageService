package com.example.storageservice.api.catalog.additem;

import com.example.storageservice.api.base.OperationResult;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CatalogItemResult implements OperationResult {

    @JsonProperty
    private UUID itemId;
    @JsonProperty
    private Integer discount;
}
