package com.example.storageservice.core.getallitems;

import com.example.storageservice.api.Item.getallitems.GetAllItemsOperation;
import com.example.storageservice.api.Item.getallitems.GetAllItemsRequest;
import com.example.storageservice.api.Item.getallitems.GetAllItemsResponse;
import com.example.storageservice.persistence.entity.Shipment;
import com.example.storageservice.persistence.repository.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllItemsOperationProcessors implements GetAllItemsOperation {

    private final ShipmentRepository shipmentRepository;
    @Override
    public List<GetAllItemsResponse> process(GetAllItemsRequest operationInput) throws Exception {

        List<Shipment> shipments = shipmentRepository.findAll();
        List<GetAllItemsResponse> getAllItemsResponseList = new ArrayList<>();

        shipments.stream().forEach(shipment ->{
            GetAllItemsResponse getAllItemsResponse = GetAllItemsResponse.builder()
                                                        .itemId(shipment.getItemId())
                                                        .build();
            getAllItemsResponseList.add(getAllItemsResponse);
        } );

        return getAllItemsResponseList;
    }
}
