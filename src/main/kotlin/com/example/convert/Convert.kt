package com.example.convert

internal interface Convert<T, S> {
    fun fromDTO(dto: T): S

    fun fromDTO(entity: S, dto: T): S

    fun toDTO(entity: S): T
}