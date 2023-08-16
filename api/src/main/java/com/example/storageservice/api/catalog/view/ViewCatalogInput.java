package com.example.storageservice.api.catalog.view;

import com.example.storageservice.api.base.OperationInput;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ViewCatalogInput implements OperationInput {

    @JsonProperty
    private UUID catalogId;
}
