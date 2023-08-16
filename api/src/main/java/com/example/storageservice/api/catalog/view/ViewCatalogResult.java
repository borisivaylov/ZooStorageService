package com.example.storageservice.api.catalog.view;

import com.example.storageservice.api.base.OperationResult;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ViewCatalogResult implements OperationResult {
    @JsonProperty
    private UUID catalogId;
    @JsonProperty
    private List<ViewCatalogItem> catalogItems;
    @JsonProperty
    private Date timestamp;

}
