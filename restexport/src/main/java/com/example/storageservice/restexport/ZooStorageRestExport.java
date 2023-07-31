package com.example.storageservice.restexport;

import com.example.storageservice.api.Item.getItem.ItemResponse;
import com.example.storageservice.api.Item.getitembytag.GetItemByTagResponse;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

import java.util.List;

@Headers({"Content-Type: application/json"})
public interface ZooStorageRestExport {

    @RequestLine("GET /zooStorage/{uuid}")
    ItemResponse getStorageItemById(@Param String uuid);
    @RequestLine("GET /zooStorage/byTag/{tagName}")
    List<GetItemByTagResponse> getStorageItemsByTag(@Param String tagName);
}
