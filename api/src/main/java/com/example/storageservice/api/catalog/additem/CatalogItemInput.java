package com.example.storageservice.api.catalog.additem;

import com.example.storageservice.api.base.OperationInput;
import com.example.storageservice.persistence.entity.Shipment;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CatalogItemInput implements OperationInput {
    @JsonProperty
    private List<Shipment> ListOfItems;
}
