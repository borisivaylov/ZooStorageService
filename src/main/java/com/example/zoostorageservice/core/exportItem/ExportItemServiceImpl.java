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
public class ExportItemServiceImpl implements ExportItemService {

    private final ShipmentRepository shipmentRepository;

    public ExportResponse process(ExportRequest exportRequest){

        Shipment shipment= shipmentRepository.findById(exportRequest.getId()).orElseThrow(()
            -> new IllegalArgumentException("There is no item with id:"+exportRequest.getId()));
            shipment.setQuantity(shipment.getQuantity() - exportRequest.getQuantity());

            shipmentRepository.save(shipment);

        return ExportResponse.builder()
                                        .id(exportRequest.getId())
                                        .quantity(exportRequest.getQuantity())
                                        .build();
    }


}
