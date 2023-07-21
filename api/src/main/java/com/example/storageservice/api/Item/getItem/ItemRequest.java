package com.example.storageservice.api.Item.getItem;


import com.example.storageservice.api.base.OperationInput;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemRequest implements OperationInput {
    @JsonProperty("itemId")
    private UUID id;
}
