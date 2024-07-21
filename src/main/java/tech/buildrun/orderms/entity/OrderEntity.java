package tech.buildrun.orderms.entity;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.*;
import tech.buildrun.orderms.listener.dto.OrderItemEvent;

import java.math.BigDecimal;
import java.util.List;

@Document(collection = "tb_order")
public class OrderEntity {

    @MongoId
    private Long orderId;

    @Indexed(name = "customer_id_index")
    private Long customerId;

    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal total;

    private List<OrderItem> items;

    private OrderEntity(Long orderId, Long customerId, List<OrderItem> items) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.total = items.stream().map(OrderItem::getTotalPerProduct).reduce(BigDecimal.ZERO, BigDecimal::add);
        this.items = items;
    }

    public static OrderEntity newOrder(Long orderId, Long customerId, List<OrderItemEvent> items) {
        return new OrderEntity(orderId, customerId, items.stream().map(OrderItem::newOrderItem).toList());
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public BigDecimal getTotal() {
        return total;
    }
}
