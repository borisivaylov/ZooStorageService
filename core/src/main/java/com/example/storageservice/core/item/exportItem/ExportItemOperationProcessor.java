package com.example.storageservice.core.item.exportItem;


import com.example.storageservice.api.Item.export.ExportRequest;
import com.example.storageservice.api.Item.export.ExportResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.storageservice.persistence.entity.Shipment;
import com.example.storageservice.persistence.repository.ShipmentRepository;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ExportItemOperationProcessor implements com.example.storageservice.api.Item.export.ExportItemOperation {

    private final ShipmentRepository shipmentRepository;

    public ExportResponse process(ExportRequest exportRequest){

        Shipment shipment = shipmentRepository.findShipmentByItemId(exportRequest.getId())
                .orElseThrow(()-> new NoSuchElementException("No such shipment"));;
        shipment.setQuantity(shipment.getQuantity() - exportRequest.getQuantity());

        ExportResponse exportResponse = ExportResponse.builder()
                .id(exportRequest.getId())
                .quantity(shipment.getQuantity())
                .build();

        shipmentRepository.save(shipment);

        return exportResponse;
    }


}
