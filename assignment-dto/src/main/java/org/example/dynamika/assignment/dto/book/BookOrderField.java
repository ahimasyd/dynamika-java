package org.example.dynamika.assignment.dto.book;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Доступные поля для сортировки книг")
public enum BookOrderField {
    ID,
    TITLE
}
