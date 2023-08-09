package com.example.storageservice.api.Item.getallitems;

import com.example.storageservice.api.base.OperationResult;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllItemsResponse implements OperationResult {

    @JsonProperty
    private UUID itemId;
}
