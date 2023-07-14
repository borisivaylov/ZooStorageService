package com.example.zoostorageservice.Bussiness.operations.changePrice;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChangePriceResponse {
    @JsonProperty("itemId")
    private UUID id;
    @JsonProperty("price")
    private double price;
}
