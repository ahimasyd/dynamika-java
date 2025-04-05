package org.example.dynamika.assignment.dto.commons;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Направление сортировки")
public enum OrderDirection {
    ASC,
    DESC
}
