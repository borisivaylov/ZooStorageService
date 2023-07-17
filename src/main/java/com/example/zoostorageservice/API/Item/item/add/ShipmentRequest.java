package com.example.zoostorageservice.API.Item.item.add;

import com.example.zoostorageservice.API.Item.base.OperationInput;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShipmentRequest implements OperationInput {

    @JsonProperty("shipmentId")
    private UUID shipmentId;
    @JsonProperty("itemId")
    private UUID id;
    @JsonProperty("quantity")
    private long quantity;
    @JsonProperty("price")
    private double price;
}
