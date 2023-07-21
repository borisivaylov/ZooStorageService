package com.example.storageservice.api.Item.importItem;


import com.example.storageservice.api.base.OperationProcessor;
import org.springframework.stereotype.Service;

@Service
public interface ImportItemService extends OperationProcessor<ImportRequest, ImportResponse> {
    ImportResponse process(ImportRequest importRequest);
}
