package com.example.zoostorageservice.API.Item.item.changePrice;

import com.example.zoostorageservice.API.Item.base.OperationResult;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChangePriceResponse implements OperationResult {
    @JsonProperty("itemId")
    private UUID id;
    @JsonProperty("price")
    private double price;
}
