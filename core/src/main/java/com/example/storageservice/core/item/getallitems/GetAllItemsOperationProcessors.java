package com.example.storageservice.core.item.getallitems;

import com.example.storageservice.api.Item.getallitems.GetAllItemsOperation;
import com.example.storageservice.api.Item.getallitems.GetAllItemsRequest;
import com.example.storageservice.api.Item.getallitems.GetAllItemsResponse;
import com.example.storageservice.persistence.entity.Shipment;
import com.example.storageservice.persistence.repository.OnSaleItemRepository;
import com.example.storageservice.persistence.repository.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

// Returns a list of all items  with updated prices if there are items on sale

@Service
@RequiredArgsConstructor
public class GetAllItemsOperationProcessors implements GetAllItemsOperation {

    private final ShipmentRepository shipmentRepository;
    private final OnSaleItemRepository onSaleItemRepository;
    @Override
    public List<GetAllItemsResponse> process(GetAllItemsRequest operationInput)  {

        List<Shipment> shipments = shipmentRepository.findAll();
        List<GetAllItemsResponse> getAllItemsResponseList = new ArrayList<>();

        shipments.forEach(shipment ->{

            if (onSaleItemRepository.existsById(shipment.getItemId()))
            {
                double onSalePercent = onSaleItemRepository.findById(shipment.getItemId())
                        .orElseThrow(()-> new NoSuchElementException("No such item.")).getDiscount();
                shipment.setPrice(shipment.getPrice() * (1-onSalePercent/100));
            }

            GetAllItemsResponse getAllItemsResponse = GetAllItemsResponse.builder()
                                                        .itemId(shipment.getItemId())
                                                        .build();
            getAllItemsResponseList.add(getAllItemsResponse);
        } );

        return getAllItemsResponseList;
    }
}
