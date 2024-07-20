package com.example.domain.types

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter(autoApply = true)
class BooleanConverter : AttributeConverter<Boolean, String> {
    override fun convertToDatabaseColumn(entity: Boolean): String {
        return if (entity != null && entity) "Y" else "N"
    }

    override fun convertToEntityAttribute(table: String): Boolean {
        return table == "Y"
    }
}