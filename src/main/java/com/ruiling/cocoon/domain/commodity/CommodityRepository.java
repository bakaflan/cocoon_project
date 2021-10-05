package com.ruiling.cocoon.domain.commodity;

import com.ruiling.cocoon.infrastructure.CocoonObjectMapper;
import com.ruiling.cocoon.infrastructure.Image;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CommodityRepository {
    private final NamedParameterJdbcTemplate namedTemplate;
    private final CocoonObjectMapper objectMapper;

    public Optional<Commodity> bySku(String sku) {
        String sql = "select * from commodity " +
                "where sku = :sku";
        MapSqlParameterSource param = new MapSqlParameterSource()
                .addValue("sku", sku);
        try {
            return Optional.ofNullable(namedTemplate.queryForObject(sql, param, rowMapper()));
        } catch (EmptyResultDataAccessException ex) {
            return Optional.empty();
        }
    }

    public List<Commodity> bySku(List<String> sku) {
        String sql = "select * from commodity " +
                "where sku in (:sku)";
        MapSqlParameterSource param = new MapSqlParameterSource()
                .addValue("sku", sku);
        return namedTemplate.query(sql, param, rowMapper());
    }

    private RowMapper<Commodity> rowMapper() {
        return (rs, rowNum) -> {
            String sku = rs.getString("sku");
            String name = rs.getString("name");
            String description = rs.getString("description");
            Image image = objectMapper.readValue(rs.getString("image"), Image.class);
            BigDecimal price = new BigDecimal(rs.getString("price"));
            return new Commodity(sku, name, description, image, price);
        };
    }
}
