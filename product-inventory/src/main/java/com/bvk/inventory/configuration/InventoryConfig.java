package com.bvk.inventory.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class InventoryConfig {

    @Value("${users.base.url}")
    private String usersBaseUrl;

    @Bean
    public ModelMapper modelMapperBean() {
        return new ModelMapper();
    }

    @Bean("usersClient")
    public WebClient usersClient() {
        return WebClient.builder().baseUrl(usersBaseUrl).build();
    }
}
