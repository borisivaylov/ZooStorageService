package com.example.storageservice.api.Item.getitembytag;

import com.example.storageservice.api.base.OperationInput;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetItemByTagRequest implements OperationInput {

    @JsonProperty("tagName")
    private String tagName;

}
