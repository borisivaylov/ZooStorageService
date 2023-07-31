package com.example.storageservice.core.getitembytag;

import com.example.storageservice.api.Item.getitembytag.GetItemByTagOperation;
import com.example.storageservice.api.Item.getitembytag.GetItemByTagRequest;
import com.example.storageservice.api.Item.getitembytag.GetItemByTagResponse;
import com.example.storageservice.persistence.entity.Shipment;
import com.example.storageservice.persistence.repository.ShipmentRepository;
import com.example.zoostoreproject.api.Item.getallitems.GetAllItemsResponse;
import com.example.zoostoreproject.restexport.ZooStoreRestExport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetItemByTagOperationProcessor implements GetItemByTagOperation {

    private final ShipmentRepository shipmentRepository;
    private  final ZooStoreRestExport zooStoreRestExport;
    @Override
    public List<GetItemByTagResponse> process(GetItemByTagRequest getItemByTagRequest) throws Exception {

        if (zooStoreRestExport.getItemsByTag(getItemByTagRequest.getTagName())==null){
            throw new Exception("No items found");
        }

        List<GetAllItemsResponse> getAllItemsResponseList=zooStoreRestExport.getItemsByTag(getItemByTagRequest.getTagName());
        List<GetItemByTagResponse> getItemByTagResponseList = new ArrayList<GetItemByTagResponse>();

        for (GetAllItemsResponse getAllItemsResponse: getAllItemsResponseList) {

            Shipment shipment = shipmentRepository.findShipmentByItemId(getAllItemsResponse.getId());
            getItemByTagResponseList.add(GetItemByTagResponse.builder()
                                                                        .id(shipment.getItemId())
                                                                        .price(shipment.getPrice())
                                                                        .quantity(shipment.getQuantity())
                                                                        .build());
        }


        return getItemByTagResponseList;
    }
}
