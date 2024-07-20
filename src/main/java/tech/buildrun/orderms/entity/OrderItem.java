package tech.buildrun.orderms.entity;

import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import tech.buildrun.orderms.listener.dto.OrderItemEvent;

import java.math.BigDecimal;

public class OrderItem {

    private String product;
    private Integer quantity;
    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal price;

    @Deprecated
    public OrderItem() { }

    private OrderItem(String product, Integer quantity, BigDecimal price) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    public static OrderItem newOrderItem(OrderItemEvent itemEvent) {
        return new OrderItem(itemEvent.produto(), itemEvent.quantidade(), itemEvent.preco());
    }

    public BigDecimal getTotalPerProduct() {
        return this.price.multiply(BigDecimal.valueOf(this.quantity));
    }
}
