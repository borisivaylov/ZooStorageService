package com.example.storageservice.core.catalog.expirationdate;

import com.example.storageservice.api.catalog.expirationdate.ExpirationDateInput;
import com.example.storageservice.api.catalog.expirationdate.ExpirationDateOperation;
import com.example.storageservice.api.catalog.expirationdate.ExpirationDateResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ExpirationDateOperationProcessor implements ExpirationDateOperation {
    @Override
    public ExpirationDateResult process(ExpirationDateInput operationInput) throws Exception {

        Timestamp currentTime = operationInput.getDateOfCreation();

        LocalDateTime localDateTime=currentTime.toLocalDateTime();

        LocalDateTime expirationTime = localDateTime.plusMinutes(10);


        return ExpirationDateResult.builder()
                .dateOfExpiration(Timestamp.valueOf(expirationTime))
                .build();
    }
}
