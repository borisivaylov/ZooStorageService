package com.example.storageservice.api.catalog.view;

import com.example.storageservice.api.base.OperationResult;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetCatalogResult implements OperationResult {
    @JsonProperty
    private UUID catalogId;
    @JsonProperty
    private List<GetCatalogItem> catalogItems = new ArrayList<>();
    @JsonProperty
    private Date dateOfCreation;
}
