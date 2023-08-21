package com.example.storageservice.api.Item.checkifonsale;

import com.example.storageservice.api.base.OperationResult;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckIfOnSaleResult implements OperationResult {
    @JsonProperty
    private boolean isOnSale;
}
