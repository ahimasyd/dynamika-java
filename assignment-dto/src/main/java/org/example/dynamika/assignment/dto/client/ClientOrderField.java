package org.example.dynamika.assignment.dto.client;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Доступные поля для сортировки клиентов")
public enum ClientOrderField {
    ID,
    LAST_NAME
}
