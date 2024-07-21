package tech.buildrun.orderms.service;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import tech.buildrun.orderms.entity.OrderEntity;
import tech.buildrun.orderms.listener.dto.OrderCreatedEvent;
import tech.buildrun.orderms.repository.OrderRepository;
import tech.buildrun.orderms.service.dto.OrdersResponse;

import java.math.BigDecimal;
import java.util.Objects;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Service
public class OrderService {

    private final Logger logger = LoggerFactory.getLogger(OrderService.class);

    private final OrderRepository orderRepository;
    private final MongoTemplate mongoTemplate;

    public OrderService(OrderRepository orderRepository, MongoTemplate mongoTemplate) {
        this.orderRepository = orderRepository;
        this.mongoTemplate = mongoTemplate;
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

    public BigDecimal getTotalOrdersByCustomer(Long customerId) {
        var aggregations = newAggregation(
                match(Criteria.where("customerId").is(customerId)),
                group().sum("total").as("total"));

        var response = mongoTemplate.aggregate(aggregations, "tb_order", Document.class);

        return new BigDecimal(Objects.requireNonNull(response.getUniqueMappedResult()).get("total").toString());
    }
}
