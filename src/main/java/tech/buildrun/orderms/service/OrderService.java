package tech.buildrun.orderms.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import tech.buildrun.orderms.entity.OrderEntity;
import tech.buildrun.orderms.listener.dto.OrderCreatedEvent;
import tech.buildrun.orderms.repository.OrderRepository;
import tech.buildrun.orderms.service.dto.OrdersResponse;
import tech.buildrun.orderms.service.dto.OrdersTotalByCustomerResponse;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final Logger logger = LoggerFactory.getLogger(OrderService.class);

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void save(OrderCreatedEvent event) {
        OrderEntity orderEntity = event.toModel();
        orderRepository.save(orderEntity);
        logger.info("Message processed: {}", event);
    }

    public Page<OrdersResponse> findAllCustomerId(Long customerId, PageRequest request) {
        Page<OrderEntity> allByCustomerId = orderRepository.findAllByCustomerId(customerId, request);
        return allByCustomerId.map(OrdersResponse::toResponse);
    }

    public OrdersTotalByCustomerResponse getTotalOrdersByCustomer(Long customerId) {
        Set<OrderEntity> allByCustomerId = orderRepository.findAllByCustomerId(customerId);
        return OrdersTotalByCustomerResponse.getTotalPerOrdersByCustomer(allByCustomerId);
    }
}
