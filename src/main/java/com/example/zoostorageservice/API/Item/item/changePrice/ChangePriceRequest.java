package com.example.zoostorageservice.API.Item.item.changePrice;


import com.example.zoostorageservice.API.Item.base.OperationInput;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChangePriceRequest implements OperationInput {

    @JsonProperty("itemId")
    private UUID id;
    @JsonProperty("price")
    private double price;
}
