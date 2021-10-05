package com.ruiling.cocoon;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ruiling.cocoon.infrastructure.CocoonObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.time.Instant;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;
import static com.fasterxml.jackson.annotation.PropertyAccessor.ALL;
import static com.fasterxml.jackson.annotation.PropertyAccessor.FIELD;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static java.time.ZoneId.of;
import static java.util.TimeZone.getTimeZone;

@SpringBootApplication
public class CocoonProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(CocoonProjectApplication.class, args);
    }

    @Bean
    public CocoonObjectMapper objectMapper() {
        CocoonObjectMapper mapper = new CocoonObjectMapper();
        mapper.findAndRegisterModules()
                .setVisibility(ALL, NONE)
                .setVisibility(FIELD, ANY)
                .registerModule(new JavaTimeModule()
                        .addSerializer(Instant.class, new JsonSerializer<Instant>() {
                            @Override
                            public void serialize(Instant value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                                gen.writeNumber(value.toEpochMilli());
                            }
                        })
                        .addDeserializer(Instant.class, new JsonDeserializer<Instant>() {
                            @Override
                            public Instant deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
                                return Instant.ofEpochMilli(p.getValueAsLong());
                            }
                        }))
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                .configure(FAIL_ON_UNKNOWN_PROPERTIES, false)
                .setTimeZone(getTimeZone(of("Asia/Shanghai")));
        return mapper;
    }
}
