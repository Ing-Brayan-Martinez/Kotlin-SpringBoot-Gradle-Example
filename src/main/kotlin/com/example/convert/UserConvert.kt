package com.example.convert

import com.example.domain.entity.User
import com.example.domain.model.RoleDTO
import com.example.domain.model.UserDTO
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Component
import java.util.stream.Collectors


@Component
class UserConvert(
    val modelMapper: ModelMapper) : Convert<UserDTO, User> {

    override fun fromDTO(dto: UserDTO): User {
        return modelMapper.map(dto, User::class.java)
    }

    override fun fromDTO(entity: User, dto: UserDTO): User {
        modelMapper.map(dto, entity)
        return entity
    }

    override fun toDTO(entity: User): UserDTO {
        val roles: List<RoleDTO> = entity.getRoles()
            .stream()
            .map { role -> modelMapper.map(role, RoleDTO::class.java) }
            .collect(Collectors.toList())

        val dto: UserDTO = modelMapper.map(entity, UserDTO::class.java)
        dto.roles = roles

        return dto
    }
}