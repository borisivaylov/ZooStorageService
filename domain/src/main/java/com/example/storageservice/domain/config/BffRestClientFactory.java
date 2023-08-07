package com.example.storageservice.domain.config;

import com.example.bffshare.restexport.BffShareRestExport;
import com.example.zoostoreproject.restexport.ZooStoreRestExport;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BffRestClientFactory {

    @Bean
    public BffShareRestExport getBFFRestExportClient() {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        return Feign.builder()
                .encoder(new JacksonEncoder(objectMapper))
                .decoder(new JacksonDecoder(objectMapper))
                .target(BffShareRestExport.class, "http://localhost:8083");
    }
}
