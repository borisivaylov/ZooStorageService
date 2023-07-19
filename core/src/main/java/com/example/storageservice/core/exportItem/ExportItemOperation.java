package com.example.zoostorageservice.core.exportItem;

import com.example.zoostorageservice.api.Item.export.ExportItemService;
import com.example.zoostorageservice.api.Item.export.ExportRequest;
import com.example.zoostorageservice.api.Item.export.ExportResponse;
import com.example.zoostorageservice.persistence.entity.Shipment;
import com.example.zoostorageservice.persistence.repository.ShipmentRepository;
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
