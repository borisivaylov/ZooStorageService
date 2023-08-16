package com.example.storageservice.api.catalog.generate;

import com.example.storageservice.api.base.OperationResult;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenerateCatalogResult implements OperationResult {

    @JsonProperty
    private UUID catalogId;
    @JsonProperty
    private List<UUID> items;
    @JsonProperty
    private Date timestamp;
}
