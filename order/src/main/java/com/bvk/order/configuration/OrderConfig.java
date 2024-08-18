package com.bvk.order.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class OrderConfig {

    @Value("${users.base.url}")
    private String usersBaseUrl;

    @Value("${inventory.base.url}")
    private String inventoryBaseUrl;

    @Bean
    public ModelMapper modelMapperBean() {
        return new ModelMapper();
    }

    @Bean("usersClient")
    public WebClient usersClient() { return WebClient.builder().baseUrl(usersBaseUrl).build(); }

    @Bean("inventoryClient")
    public WebClient inventoryClient() {
        return WebClient.builder().baseUrl(inventoryBaseUrl).build();
    }
}
