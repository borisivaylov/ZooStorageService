package com.example.storageservice.core.catalog.autogenerate;

import com.example.storageservice.api.catalog.autogenerate.AutoGenerateInput;
import com.example.storageservice.api.catalog.autogenerate.AutoGenerateOperation;
import com.example.storageservice.api.catalog.autogenerate.AutoGenerateResult;
import com.example.storageservice.api.catalog.generate.GenerateCatalogInput;
import com.example.storageservice.api.catalog.getallactive.GetAllActiveInput;
import com.example.storageservice.api.catalog.getallactive.GetAllActiveResult;
import com.example.storageservice.core.catalog.generate.GenerateCatalogOperationProcessor;
import com.example.storageservice.core.catalog.getallactive.GetAllActiveOperationProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

// Automatically generate a catalog

@Service
@RequiredArgsConstructor


public class CatalogAutoGenerationOperationProcessor implements AutoGenerateOperation {

    private final GetAllActiveOperationProcessor getAllActiveOperationProcessor;
    private final GenerateCatalogOperationProcessor generateCatalogOperationProcessor;
    @Override
    public AutoGenerateResult process(AutoGenerateInput operationInput) {

        List<GetAllActiveResult> getAllActiveResultList = getAllActiveOperationProcessor.process(GetAllActiveInput.builder().build());
        if (getAllActiveResultList.isEmpty()){
            generateCatalogOperationProcessor.process(GenerateCatalogInput.builder()
                    .itemsCount(4).build());
            return AutoGenerateResult.builder().status("New catalog generated successfully").build();
        }
        return AutoGenerateResult.builder().build();
    }
}
