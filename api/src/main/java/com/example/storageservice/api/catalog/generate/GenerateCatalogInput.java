package com.example.storageservice.api.catalog.generate;

import com.example.storageservice.api.base.OperationInput;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenerateCatalogInput implements OperationInput {

    @JsonProperty
    private Integer itemsCount;

}
