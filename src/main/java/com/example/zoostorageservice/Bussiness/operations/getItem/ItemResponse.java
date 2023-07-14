package com.example.zoostorageservice.Bussiness.operations.getItem;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemResponse {
    @JsonProperty("itemId")
    private UUID id;

}
