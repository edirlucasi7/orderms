package tech.buildrun.orderms.listener.dto;

import tech.buildrun.orderms.entity.OrderEntity;

import java.util.List;

public record OrderCreatedEvent(Long codigoPedido, Long codigoCliente, List<OrderItemEvent> itens) {

    public OrderEntity toModel() {
        return OrderEntity.newOrder(codigoPedido, codigoCliente, itens);
    }
}
