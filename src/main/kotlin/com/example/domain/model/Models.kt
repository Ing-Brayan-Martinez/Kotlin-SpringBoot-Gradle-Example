package com.example.domain.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import java.util.stream.Collectors

data class UserDTO(
    var userId: Long = 0,

    var name: String,

    var userName: String,

    @JsonIgnore
    var password: String,

    var isAccountNonExpired: Boolean,

    var isAccountNonLocked: Boolean,

    var isCredentialsNonExpired: Boolean,

    var roles: List<RoleDTO> = ArrayList(),

    var token: String? = null) {

    fun getAuthorities(): Collection<GrantedAuthority?> {
        return roles.stream()
            .map { role: RoleDTO ->
                SimpleGrantedAuthority(
                    role.name
                )
            }
            .collect(Collectors.toList())
    }
}

data class RoleDTO(
    var roleId: Long = 0,

    var name: String,

    var description: String)



data class ContactDTO(val id: Int, val name: String)