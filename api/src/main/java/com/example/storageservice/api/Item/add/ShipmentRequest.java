package com.example.storageservice.api.Item.add;

import com.example.storageservice.api.base.OperationInput;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShipmentRequest implements OperationInput {

    @JsonProperty("itemId")
    private UUID id;
    @JsonProperty("quantity")
    private long quantity;
    @JsonProperty("price")
    private double price;
}
