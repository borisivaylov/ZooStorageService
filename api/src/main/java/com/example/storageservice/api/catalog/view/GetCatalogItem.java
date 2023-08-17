package com.example.storageservice.api.catalog.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetCatalogItem {

    @JsonProperty
    private UUID itemId;
    @JsonProperty
    private String title;
    @JsonProperty
    private String description;
    @JsonProperty
    private double actualPrice;
    @JsonProperty
    private Integer discount;
    @JsonProperty
    private double onSalePrice;
}
