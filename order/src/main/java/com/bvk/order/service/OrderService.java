package com.bvk.order.service;

import com.bvk.order.entity.Order;
import com.bvk.order.entity.OrderInventory;
import com.bvk.order.repository.OrderInventoryRepo;
import com.bvk.order.repository.OrderRepo;
import jakarta.servlet.http.HttpServletRequest;
import org.bvk.enumeration.ResponseCode;
import org.bvk.response.InventoryDto;
import org.bvk.response.ListIdDto;
import org.bvk.response.PurchaseStockDto;
import org.bvk.response.ResponseRestfulEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private OrderInventoryRepo orderInventoryRepo;

    @Autowired
    @Qualifier("inventoryClient")
    public WebClient inventoryClient;

    @Autowired
    private ModelMapper mapper;

    public ResponseRestfulEntity findAll() {
        List<Order> orderList = orderRepo.findAll();
        return new ResponseRestfulEntity(ResponseCode.SUCCESS, orderList,true);
    }

    public ResponseRestfulEntity purchases(PurchaseStockDto dto) {
        try {ResponseEntity<Integer> response = inventoryPut("/reduce", dto, Integer.class).block();
            if(response.getBody() == 0) {
                List<Long> idList = dto.getCheckout().stream().map(InventoryDto::getId).toList();
                ListIdDto dtoList = new ListIdDto();
                dtoList.setIdList(idList);
                ResponseEntity<List> responseList =  inventoryPost("/findByIdList", dtoList, List.class).block();
                List<LinkedHashMap> inventoryList = (List<LinkedHashMap>) responseList.getBody();
                if(CollectionUtils.isEmpty(inventoryList)) return new ResponseRestfulEntity(ResponseCode.PRODUCT_NOT_FOUND, false);

                double totalPrice = 0.0;//(inventoryList.stream().map(InventoryDto::getPrice).toList()).stream().mapToDouble(Double::doubleValue).sum();
                for(LinkedHashMap i : inventoryList) {
                    totalPrice = totalPrice + (Double) i.get("price");
                }
                Order order = new Order();
                order.setAmount(totalPrice);
                order.setIdCreation(100L);
                order.setDateCreation(new Date());
                orderRepo.save(order);

                for(LinkedHashMap x : inventoryList) {
                    OrderInventory orderInventory = new OrderInventory();
                    orderInventory.setOrderId(order.getId());
                    orderInventory.setInventoryId(((Integer) x.get("id")));
                    orderInventory.setPrice((Double) x.get("price"));
                    orderInventory.setIdCreation(100L);
                    orderInventory.setDateCreation(new Date());
                    orderInventoryRepo.save(orderInventory);
                }
                return new ResponseRestfulEntity(ResponseCode.SUCCESS, true);
            } else {
                return new ResponseRestfulEntity(ResponseCode.INSUFICIENT_STOCK, false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseRestfulEntity(ResponseCode.INTERNAL_SYSTEM_ERROR, false);
        }
    }


    public <Req,Res> Mono<ResponseEntity<Res>> inventoryPost(String suffix, Req object, Class<Res> responseClass) {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String authorize = request.getHeader("Authorization");

        return inventoryClient.post()
                .uri(suffix)
                .header("Authorization", authorize)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(object)
                .retrieve()
                .toEntity(responseClass);
    }

    public <Req,Res> Mono<ResponseEntity<Res>> inventoryPut(String suffix, Req object, Class<Res> responseClass) {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String authorize = request.getHeader("Authorization");

        return inventoryClient.put()
                .uri(suffix)
                .header("Authorization", authorize)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(object)
                .retrieve()
                .toEntity(responseClass);
    }
}
