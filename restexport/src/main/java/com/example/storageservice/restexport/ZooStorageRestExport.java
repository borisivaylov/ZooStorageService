package com.example.storageservice.restexport;

import com.example.storageservice.api.Item.getItem.ItemResponse;
import com.example.storageservice.api.Item.getitembytag.GetItemByTagResponse;
import com.example.storageservice.api.purchase.cartpurchase.StoragePurchaseRequest;
import com.example.storageservice.api.purchase.cartpurchase.StoragePurchaseResult;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Headers({"Content-Type: application/json"})
public interface ZooStorageRestExport {

    @RequestLine("GET /zooStorage/{uuid}")
    ItemResponse getStorageItemById(@Param String uuid);
    @RequestLine("GET /zooStorage/byTag/{tagName}")
    List<GetItemByTagResponse> getStorageItemsByTag(@Param String tagName);

    @RequestLine("POST /zooStorage/purchase")
    StoragePurchaseResult purchase(@RequestBody StoragePurchaseRequest purchaseRequest);
}
