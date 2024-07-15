package tech.buildrun.orderms.listener.dto;

import java.math.BigDecimal;

public record OrderItemEvent(String poduto, Integer quantidade, BigDecimal preco) { }
