package com.example.storageservice.core.importItem;


import com.example.storageservice.api.Item.importItem.ImportItemService;
import com.example.storageservice.api.Item.importItem.ImportRequest;
import com.example.storageservice.api.Item.importItem.ImportResponse;
import com.example.storageservice.persistence.entity.Shipment;
import com.example.storageservice.persistence.repository.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ImportItemOperationProcessor implements ImportItemService {

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
