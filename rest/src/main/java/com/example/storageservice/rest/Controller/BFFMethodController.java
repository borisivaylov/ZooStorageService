package com.example.storageservice.rest.Controller;

import com.example.storageservice.api.Item.checkifonsale.CheckIfOnSaleInput;
import com.example.storageservice.api.Item.checkifonsale.CheckIfOnSaleResult;
import com.example.storageservice.api.Item.getItemByIdReference.GetItemByIdReferenceRequest;
import com.example.storageservice.api.Item.getItemByIdReference.GetItemByIdReferenceResponse;
import com.example.storageservice.api.Item.getdiscount.GetDiscountInput;
import com.example.storageservice.api.Item.getdiscount.GetDiscountResult;
import com.example.storageservice.core.item.getItemByIdReference.GetItemByIdReferenceOperationProcessor;
import com.example.storageservice.core.item.getdiscount.GetItemDiscountOperationProcessor;
import com.example.storageservice.core.item.ifonsale.CheckIfOnSaleOperationProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/bffmethod")
@RequiredArgsConstructor
public class BFFMethodController {

    private final GetItemByIdReferenceOperationProcessor getItemByIdReferenceOperationProcessor;
    private final GetItemDiscountOperationProcessor getItemDiscountOperationProcessor;
    private final CheckIfOnSaleOperationProcessor checkIfOnSaleOperationProcessor;

    @PostMapping ("/{itemId}")
        GetItemByIdReferenceResponse getItemByIdReference(@PathVariable UUID itemId){
        return getItemByIdReferenceOperationProcessor.process(GetItemByIdReferenceRequest.builder().itemId(itemId).build());
    }
    @GetMapping("/itemDiscount/{id}")
    GetDiscountResult getDiscountResult(@PathVariable UUID id) throws Exception {
        return getItemDiscountOperationProcessor.process(GetDiscountInput.builder().itemId(id).build());
    }
    @GetMapping("/ifOnSale/{itemId}")
    CheckIfOnSaleResult onSaleResult(@PathVariable UUID itemId) throws Exception {
        return  checkIfOnSaleOperationProcessor.process(CheckIfOnSaleInput.builder().itemId(itemId).build());
    }



}
