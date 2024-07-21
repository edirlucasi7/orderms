package tech.buildrun.orderms.service.dto;

import java.util.List;

public record ApiResponse<T>(List<T> data, PaginationResponse pagination) { }
