package tech.buildrun.orderms.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import tech.buildrun.orderms.entity.OrderEntity;

import java.util.Set;

public interface OrderRepository extends MongoRepository<OrderEntity, Long> {
    Page<OrderEntity> findAllByCustomerId(Long customerId, PageRequest request);
    Set<OrderEntity> findAllByCustomerId(Long customerId);
}
