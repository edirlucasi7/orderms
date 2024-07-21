package tech.buildrun.orderms.service.dto;

import tech.buildrun.orderms.entity.OrderEntity;

import java.math.BigDecimal;
import java.util.Set;

public record OrdersTotalByCustomerResponse(BigDecimal total) {

    public static OrdersTotalByCustomerResponse getTotalPerOrdersByCustomer(Set<OrderEntity> totalPerOrders) {
        BigDecimal totalOrdersByCustomer = totalPerOrders.stream().map(OrderEntity::getTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
        return new OrdersTotalByCustomerResponse(totalOrdersByCustomer);
    }
}
