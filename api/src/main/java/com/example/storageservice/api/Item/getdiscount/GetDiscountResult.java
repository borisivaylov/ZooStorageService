package com.example.storageservice.api.Item.getdiscount;

import com.example.storageservice.api.base.OperationResult;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetDiscountResult implements OperationResult {

    @JsonProperty
    private UUID itemId;
    @JsonProperty
    private Integer discount;
}
