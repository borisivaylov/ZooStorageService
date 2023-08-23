package com.example.storageservice.api.catalog.getallactive;

import com.example.storageservice.api.base.OperationResult;
import com.example.storageservice.api.catalog.view.GetCatalogItem;
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
public class GetAllActiveResult implements OperationResult {

    @JsonProperty
    private UUID catalogId;
    @JsonProperty
    private List<UUID> catalogItems = new ArrayList<>();
    @JsonProperty
    private Date dateOfCreation;
    @JsonProperty
    private Date dateOfExpiration;
    @JsonProperty
    private boolean status;
}

