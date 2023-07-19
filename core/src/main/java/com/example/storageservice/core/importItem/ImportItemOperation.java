package com.example.zoostorageservice.core.importItem;

import com.example.zoostorageservice.api.Item.importItem.ImportItemService;
import com.example.zoostorageservice.api.Item.importItem.ImportRequest;
import com.example.zoostorageservice.api.Item.importItem.ImportResponse;
import com.example.zoostorageservice.persistence.entity.Shipment;
import com.example.zoostorageservice.persistence.repository.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImportItemOperation implements ImportItemService {

    private final ShipmentRepository shipmentRepository;

    public ImportResponse process(ImportRequest importRequest){
        Shipment shipment = shipmentRepository.findShipmentByItemId(importRequest.getId());
                shipment.setQuantity(shipment.getQuantity()+importRequest.getQuantity());

                ImportResponse importResponse = ImportResponse.builder()
                                                .id(importRequest.getId())
                                                .quantity(shipment.getQuantity())
                                                .build() ;

                    shipmentRepository.save(shipment);

                return importResponse;
    }


}
