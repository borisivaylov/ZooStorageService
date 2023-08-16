package com.example.storageservice.core.item.getitembytag;

import com.example.storageservice.api.Item.getitembytag.GetItemByTagOperation;
import com.example.storageservice.api.Item.getitembytag.GetItemByTagRequest;
import com.example.storageservice.api.Item.getitembytag.GetItemByTagResponse;
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

@Service
@RequiredArgsConstructor
public class GetItemByTagOperationProcessor implements GetItemByTagOperation {

    private final ShipmentRepository shipmentRepository;
    private  final ZooStoreRestExport zooStoreRestExport;
    private final OnSaleItemRepository onSaleItemRepository;
    @Override
    public List<GetItemByTagResponse> process(GetItemByTagRequest getItemByTagRequest) throws Exception {

        if (zooStoreRestExport.getItemsByTag(getItemByTagRequest.getTagName())==null){
            throw new Exception("No items found");
        }

        List<GetAllItemsResponse> getAllItemsResponseList=zooStoreRestExport.getItemsByTag(getItemByTagRequest.getTagName());
        List<GetItemByTagResponse> getItemByTagResponseList = new ArrayList<GetItemByTagResponse>();

        for (GetAllItemsResponse getAllItemsResponse: getAllItemsResponseList) {

            Shipment shipment = shipmentRepository.findShipmentByItemId(getAllItemsResponse.getId())
                    .orElseThrow(()-> new NoSuchElementException("No such shipment"));

            if (onSaleItemRepository.existsById(shipment.getItemId()))
            {
                double onSalePercent = onSaleItemRepository.findById(shipment.getItemId())
                        .orElseThrow(()-> new NoSuchElementException("No such item.")).getDiscount();
                shipment.setPrice(shipment.getPrice() * (1-onSalePercent/100));
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
