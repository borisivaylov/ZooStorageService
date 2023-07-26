package com.example.storageservice.api.Item.getItemByIdReference;

import com.example.storageservice.api.base.OperationInput;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetItemByIdReferenceRequest implements OperationInput {

    @JsonProperty("itemId")
    private UUID itemId;
}
