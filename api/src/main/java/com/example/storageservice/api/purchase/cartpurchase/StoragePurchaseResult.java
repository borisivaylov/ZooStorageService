package com.example.storageservice.api.purchase.cartpurchase;

import com.example.storageservice.api.base.OperationResult;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoragePurchaseResult implements OperationResult {

    @JsonProperty("Status:")
    private String status;
}
