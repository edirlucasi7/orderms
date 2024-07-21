package tech.buildrun.orderms.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.buildrun.orderms.service.OrderService;
import tech.buildrun.orderms.service.dto.*;

import java.math.BigDecimal;
import java.util.Map;

@RestController
public class OrderController {

    public final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/customers/{customerId}/orders")
    public ResponseEntity<ApiResponse<OrdersResponse>> listOrdersByCustomer(@PathVariable(name = "customerId") Long customerId,
                                                                  @RequestParam(name = "page", defaultValue = "0") Integer page,
                                                                  @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {

        Page<OrdersResponse> pageResponse = orderService.findAllCustomerId(customerId, PageRequest.of(page, pageSize));
        BigDecimal totalOrdersByCustomer = orderService.getTotalOrdersByCustomer(customerId);

        return ResponseEntity.ok(new ApiResponse<>(
                Map.of("totalPerOrders", totalOrdersByCustomer),
                pageResponse.getContent(),
                PaginationResponse.fromPage(pageResponse)
        ));
    }
}