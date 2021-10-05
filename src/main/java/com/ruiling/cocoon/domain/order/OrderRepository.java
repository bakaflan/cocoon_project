package com.ruiling.cocoon.domain.order;

import com.ruiling.cocoon.infrastructure.CocoonObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepository {
    private final CocoonObjectMapper objectMapper;
    private final NamedParameterJdbcTemplate namedTemplate;

    public void save(Order order) {
        String sql = "INSERT INTO `ORDER` (ID, JSON_CONTENT) VALUES (:id,:jsonContent) ";
        MapSqlParameterSource param = new MapSqlParameterSource()
                .addValue("id", order.getId())
                .addValue("jsonContent", objectMapper.writeValueAsString(order));
        namedTemplate.update(sql, param);
    }
}
