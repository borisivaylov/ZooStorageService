package com.example.storageservice.restexport;

import com.example.storageservice.api.Item.checkifonsale.CheckIfOnSaleInput;
import com.example.storageservice.api.Item.checkifonsale.CheckIfOnSaleResult;
import com.example.storageservice.api.Item.getItem.ItemResponse;
import com.example.storageservice.api.Item.getItemByIdReference.GetItemByIdReferenceResponse;
import com.example.storageservice.api.Item.getallitems.GetAllItemsResponse;
import com.example.storageservice.api.Item.getdiscount.GetDiscountResult;
import com.example.storageservice.api.Item.getitembytag.GetItemByTagResponse;
import com.example.storageservice.api.catalog.getallactive.GetAllActiveInput;
import com.example.storageservice.api.catalog.getallactive.GetAllActiveResult;
import com.example.storageservice.api.catalog.view.GetCatalogResult;
import com.example.storageservice.api.purchase.cartpurchase.StoragePurchaseRequest;
import com.example.storageservice.api.purchase.cartpurchase.StoragePurchaseResult;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@Headers({"Content-Type: application/json"})
public interface ZooStorageRestExport {

    @RequestLine("GET /zooStorage/{uuid}")
    ItemResponse getStorageItemById(@Param String uuid);
    @RequestLine("GET /zooStorage/byTag/{tagName}")
    List<GetItemByTagResponse> getStorageItemsByTag(@Param String tagName);

    @RequestLine("POST /zooStorage/purchase")
    StoragePurchaseResult purchase(@RequestBody StoragePurchaseRequest purchaseRequest);

    @RequestLine("GET /zooStorage/getAllItemsId/{string}")
    List<GetAllItemsResponse> getAllIds(@Param String string);

    @RequestLine("GET /catalog/{catalogId}")
    GetCatalogResult viewCatalog(@Param UUID catalogId);
    @RequestLine("POST /bffmethod/{itemId}")
    GetItemByIdReferenceResponse getItemByIdReference(@Param UUID itemId);

    @RequestLine("GET /bffmethod/itemDiscount/{id}")
    GetDiscountResult getItemDiscount(@Param UUID id);

    @RequestLine("GET /bffmethod/ifOnSale/{itemId}")
    CheckIfOnSaleResult onSaleResult(@Param UUID itemId);

    @RequestLine("POST /catalog/allActive")
    List<GetAllActiveResult> getAllActiveResultList(@RequestBody GetAllActiveInput getAllActiveInput) ;

}
