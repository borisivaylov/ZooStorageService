package com.example.zoostorageservice.core.exportItem;

import com.example.zoostorageservice.API.Item.item.export.ExportItemService;
import com.example.zoostorageservice.API.Item.item.export.ExportRequest;
import com.example.zoostorageservice.API.Item.item.export.ExportResponse;
import com.example.zoostorageservice.persistance.entity.Shipment;
import com.example.zoostorageservice.persistance.repository.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExportItemOperation implements ExportItemService {

    private final ShipmentRepository shipmentRepository;

    public ExportResponse process(ExportRequest exportRequest){

        Shipment shipment = shipmentRepository.findShipmentByItemId(exportRequest.getId());
        shipment.setQuantity(shipment.getQuantity() - exportRequest.getQuantity());

        ExportResponse exportResponse = ExportResponse.builder()
                .id(exportRequest.getId())
                .quantity(shipment.getQuantity())
                .build();

        shipmentRepository.save(shipment);

        return exportResponse;
    }


}
