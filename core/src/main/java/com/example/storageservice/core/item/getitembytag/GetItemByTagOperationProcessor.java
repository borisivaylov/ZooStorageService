package com.example.storageservice.core.item.getitembytag;

import com.example.storageservice.api.Item.checkifonsale.CheckIfOnSaleInput;
import com.example.storageservice.api.Item.getdiscount.GetDiscountInput;
import com.example.storageservice.api.Item.getitembytag.GetItemByTagOperation;
import com.example.storageservice.api.Item.getitembytag.GetItemByTagRequest;
import com.example.storageservice.api.Item.getitembytag.GetItemByTagResponse;
import com.example.storageservice.core.item.getdiscount.GetItemDiscountOperationProcessor;
import com.example.storageservice.core.item.ifonsale.CheckIfOnSaleOperationProcessor;
import com.example.storageservice.persistence.entity.Shipment;
import com.example.storageservice.persistence.repository.OnSaleItemRepository;
import com.example.storageservice.persistence.repository.ShipmentRepository;
import com.example.zoostoreproject.api.Item.getallitems.GetAllItemsResponse;
import com.example.zoostoreproject.restexport.ZooStoreRestExport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

// Returns information about an item (price updated if on sale) found by tag

@Service
@RequiredArgsConstructor
public class GetItemByTagOperationProcessor implements GetItemByTagOperation {

    private final ShipmentRepository shipmentRepository;
    private  final ZooStoreRestExport zooStoreRestExport;
    private final CheckIfOnSaleOperationProcessor checkIfOnSaleOperationProcessor;
    private  final GetItemDiscountOperationProcessor getItemDiscountOperationProcessor;
    @Override
    public List<GetItemByTagResponse> process(GetItemByTagRequest getItemByTagRequest) {

        List<GetAllItemsResponse> getAllItemsResponseList = zooStoreRestExport.getItemsByTag(getItemByTagRequest.getTagName());

        if (getAllItemsResponseList.isEmpty()) {
            throw new NoSuchElementException("There are no such items");
        }

        //List<GetAllItemsResponse> getAllItemsResponseList=zooStoreRestExport.getItemsByTag(getItemByTagRequest.getTagName());
        List<GetItemByTagResponse> getItemByTagResponseList = new ArrayList<GetItemByTagResponse>();

        for (GetAllItemsResponse getAllItemsResponse: getAllItemsResponseList) {

            Shipment shipment = shipmentRepository.findShipmentByItemId(getAllItemsResponse.getId())
                    .orElseThrow(()-> new NoSuchElementException("No such shipment"));

            if (checkIfOnSaleOperationProcessor.process(CheckIfOnSaleInput.builder()
                    .itemId(shipment.getItemId()).build()).isOnSale()){
                Integer discount = getItemDiscountOperationProcessor.process(GetDiscountInput.builder().itemId(shipment.getItemId()).build()).getDiscount();
                Double price = shipment.getPrice()*(1-discount/100);
                shipment.setPrice(price);
            }

            getItemByTagResponseList.add(GetItemByTagResponse.builder()
                                                                        .id(shipment.getItemId())
                                                                        .price(shipment.getPrice())
                                                                        .quantity(shipment.getQuantity())
                                                                        .build());
        }


        return getItemByTagResponseList;
    }
}
