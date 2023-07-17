package com.example.zoostorageservice.API.Item.item.getItem;

import com.example.zoostorageservice.API.Item.base.OperationResult;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemResponse implements OperationResult {
    @JsonProperty("itemId")
    private UUID id;
    @JsonProperty("quantity")
    private long quantity;
    @JsonProperty ("price")
    private double price;
}
