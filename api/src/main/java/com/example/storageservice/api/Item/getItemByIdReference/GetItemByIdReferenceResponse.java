package com.example.storageservice.api.Item.getItemByIdReference;

import com.example.storageservice.api.base.OperationResult;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetItemByIdReferenceResponse implements OperationResult {

    private UUID id;
    private UUID ItemId;
    private double price;
    private long quantity;
}
