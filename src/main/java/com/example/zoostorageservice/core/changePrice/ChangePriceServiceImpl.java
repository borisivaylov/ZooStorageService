package com.example.zoostorageservice.core.changePrice;

import com.example.zoostorageservice.API.Item.item.changePrice.ChangePriceRequest;
import com.example.zoostorageservice.API.Item.item.changePrice.ChangePriceResponse;
import com.example.zoostorageservice.API.Item.item.changePrice.ChangePriceService;
import com.example.zoostorageservice.persistance.entity.Shipment;
import com.example.zoostorageservice.persistance.repository.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChangePriceServiceImpl implements ChangePriceService {

    private final ShipmentRepository shipmentRepository;

   public ChangePriceResponse process(ChangePriceRequest changePriceRequest){
            Shipment shipment = shipmentRepository.findById(changePriceRequest.getId()).orElseThrow(()
            -> new IllegalArgumentException("There is no item with id:"+changePriceRequest.getId()));
            shipment.setPrice(changePriceRequest.getPrice());

            ChangePriceResponse changePriceResponse = ChangePriceResponse.builder()
                    .id(changePriceRequest.getId())
                    .price(changePriceRequest.getPrice())
                    .build();
            shipmentRepository.save(shipment);

            return changePriceResponse;
    }

}


