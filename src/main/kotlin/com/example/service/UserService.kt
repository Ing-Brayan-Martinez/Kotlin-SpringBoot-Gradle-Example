package com.example.service

import com.example.domain.model.UserDTO
import java.util.*

interface UserService {

    fun save(dto: UserDTO): Optional<UserDTO>

    fun update(dto: UserDTO): Optional<UserDTO>

    fun delete(id: Long): Optional<UserDTO>

    fun findById(id: Long): Optional<UserDTO>

    fun findAll(): List<UserDTO>
}