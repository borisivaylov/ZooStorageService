package com.example.storageservice.api.catalog.expirationdate;

import com.example.storageservice.api.base.OperationResult;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.sql.Timestamp;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExpirationDateResult implements OperationResult {
    @JsonProperty
    Timestamp dateOfExpiration;
}
