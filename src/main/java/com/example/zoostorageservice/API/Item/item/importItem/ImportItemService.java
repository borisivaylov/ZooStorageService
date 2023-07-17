package com.example.zoostorageservice.API.Item.item.importItem;

import com.example.zoostorageservice.API.Item.base.OperationProcessor;
import com.example.zoostorageservice.API.Item.item.importItem.ImportRequest;
import com.example.zoostorageservice.API.Item.item.importItem.ImportResponse;
import org.springframework.stereotype.Service;

@Service
public interface ImportItemService extends OperationProcessor<ImportRequest, ImportResponse> {
    ImportResponse process(ImportRequest importRequest);
}
