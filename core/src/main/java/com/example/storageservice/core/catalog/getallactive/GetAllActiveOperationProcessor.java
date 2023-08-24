package com.example.storageservice.core.catalog.getallactive;

import com.example.storageservice.api.catalog.checkcatalogstatus.CheckCatalogStatusInput;
import com.example.storageservice.api.catalog.getallactive.GetAllActiveCatalogsOperationProcessor;
import com.example.storageservice.api.catalog.getallactive.GetAllActiveInput;
import com.example.storageservice.api.catalog.getallactive.GetAllActiveResult;
import com.example.storageservice.core.catalog.checkstatus.CheckStatusOperationProcessor;
import com.example.storageservice.persistence.entity.Catalog;
import com.example.storageservice.persistence.repository.CatalogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllActiveOperationProcessor implements GetAllActiveCatalogsOperationProcessor {

    private final CatalogRepository catalogRepository;
    private final CheckStatusOperationProcessor checkStatusOperationProcessor;
    @Override
    public List<GetAllActiveResult> process(GetAllActiveInput operationInput){

        List<Catalog> catalogList = catalogRepository.findAllByExpired(false);
        List<GetAllActiveResult> getAllActiveResultList =new ArrayList<>();

        catalogList.forEach(catalog -> {

                checkStatusOperationProcessor.process(CheckCatalogStatusInput.builder()
                                .catalogId(catalog.getCatalogId())
                        .build());

            getAllActiveResultList.add(GetAllActiveResult.builder()
                            .catalogId(catalog.getCatalogId())
                            .catalogItems(catalog.getItems())
                            .dateOfCreation(catalog.getDateOfCreation())
                            .dateOfExpiration(catalog.getDateOfExpiration())
                            .status(catalog.isExpired())
                    .build());
        });


        return getAllActiveResultList;
    }
}
