package tech.buildrun.orderms.service.dto;

import tech.buildrun.orderms.entity.OrderEntity;

import java.math.BigDecimal;

public record OrdersResponse(Long orderId, Long customerId, BigDecimal total) {

    public static OrdersResponse toResponse(OrderEntity orderEntity) {
        return new OrdersResponse(orderEntity.getOrderId(), orderEntity.getCustomerId(), orderEntity.getTotal());
    }
}
