package com.example.storageservice.api.Item.getItem;


import com.example.storageservice.api.Item.add.ShipmentRequest;
import com.example.storageservice.api.base.OperationProcessor;

public interface GetItemService extends OperationProcessor<ShipmentRequest,ItemResponse> {
}
