package com.example.zoostorageservice.api.Item.getItem;

import com.example.zoostorageservice.api.base.OperationInput;
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
