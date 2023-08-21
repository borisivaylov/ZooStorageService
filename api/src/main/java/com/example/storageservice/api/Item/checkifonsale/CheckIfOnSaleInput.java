package com.example.storageservice.api.Item.checkifonsale;

import com.example.storageservice.api.base.OperationInput;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckIfOnSaleInput implements OperationInput {

    @JsonProperty
    private UUID itemId;
}
