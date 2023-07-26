package com.example.storageservice.restexport;

import com.example.storageservice.api.Item.getItem.ItemResponse;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

@Headers({"Content-Type: application/json"})
public interface ZooStorageRestExport {

    @RequestLine("GET /zooStorage/{uuid}")
    ItemResponse getStorageItemById(@Param String uuid);
}
