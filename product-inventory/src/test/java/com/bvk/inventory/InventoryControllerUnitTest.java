package com.bvk.inventory;

import com.bvk.inventory.controller.InventoryController;
import com.bvk.inventory.entity.Inventory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bvk.response.InventoryDto;
import org.bvk.response.ResponseRestfulEntity;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;

@SpringBootTest
@AutoConfigureMockMvc
public class InventoryControllerUnitTest {

    @Autowired
    MockMvc mockMvc;

    private final String token = "7d3e2c7e-7ee8-49f4-b7fa-0dce69751774";

    @Test
    //@Order(1)
    public void addInventory() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/add")
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"HVS 1 rim\",\"price\":\"45000\",\"quantity\":\"10\"}");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        System.out.println();
        System.out.println("============================ADD INVENTORY START============================");
        System.out.println("Http Status : " + response.getStatus());
        System.out.println("Body : " + response.getContentAsString());
        System.out.println("============================ADD INVENTORY FINISH===========================");
        System.out.println();

        JsonNode jsonNode = new ObjectMapper().readTree(response.getContentAsString());
        String inventoryId = jsonNode.get("data").asText();
        updateQuantityInventory(inventoryId);
        checkAvailabilityStock(inventoryId);
    }

    //@Test
    //@Order(2)
    public void updateQuantityInventory(String inventoryId) throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/restock")
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":"+inventoryId+",\"addStock\":\"5\"}");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        System.out.println();
        System.out.println("============================UPDATE QUANTITY START============================");
        System.out.println("Http Status : " + response.getStatus());
        System.out.println("Body : " + response.getContentAsString());
        System.out.println("============================UPDATE QUANTITY FINISH===========================");
        System.out.println();
    }

    //@Test
    //@Order(2)
    public void checkAvailabilityStock(String inventoryId) throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/availability/" + inventoryId)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        System.out.println();
        System.out.println("============================CHECK STOCK START============================");
        System.out.println("Http Status : " + response.getStatus());
        System.out.println("Body : " + response.getContentAsString());
        System.out.println("============================CHECK STOCK FINISH===========================");
        System.out.println();
    }
}
