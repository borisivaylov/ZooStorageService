package com.example.storageservice.api.purchase.cartpurchase;

import com.example.bffshare.persistence.entity.Cart;
import com.example.storageservice.api.base.OperationInput;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class StoragePurchaseRequest implements OperationInput {


    @JsonProperty("cartId")
    private UUID cartId;
    @JsonProperty("userId")
    private UUID userId;
    @JsonProperty("itemMap")
    private Map<UUID,Integer> items;
    @JsonProperty("sum")
    private double sumPrice;

}
