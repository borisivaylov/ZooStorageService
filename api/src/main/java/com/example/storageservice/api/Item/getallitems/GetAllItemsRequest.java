package com.example.storageservice.api.Item.getallitems;

import com.example.storageservice.api.base.OperationInput;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class GetAllItemsRequest implements OperationInput {
    @JsonProperty
    private String testing1;
}
