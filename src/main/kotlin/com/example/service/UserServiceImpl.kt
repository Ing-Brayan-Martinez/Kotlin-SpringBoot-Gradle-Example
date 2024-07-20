package com.example.service

import com.example.convert.UserConvert
import com.example.domain.entity.User
import com.example.domain.model.UserDTO
import com.example.repository.UserRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

@Service
final class UserServiceImpl(
    private val userRepository: UserRepository,
    private val userConvert: UserConvert,
    private val passwordEncoder: PasswordEncoder) : UserService, UserAuditableService {

    override fun loadUserByUsername(username: String): UserDetails {
        return this.userRepository.findByUserName(username)
            .orElseThrow { UsernameNotFoundException("auth.invalidusername") }
    }

    override fun getCurrentUser(): Optional<User> {
        val authentication = SecurityContextHolder.getContext().authentication

        if (authentication == null || !authentication.isAuthenticated) {
            return Optional.empty()
        }

        val u = authentication.principal as User

        return Optional.of(u)
    }

    override fun save(dto: UserDTO): Optional<UserDTO> {
        val passwordEncrypted = this.passwordEncoder.encode(dto.password)
        dto.password = passwordEncrypted
        val user: User = this.userRepository.save(this.userConvert.fromDTO(dto))
        return Optional.ofNullable(user)
            .map { entity: User ->
                this.userConvert.toDTO(
                    entity
                )
            }
    }

    override fun update(dto: UserDTO): Optional<UserDTO> {
        val user: Optional<User> = this.userRepository.findById(dto.userId)
        return user
            .map { entity: User ->
                this.userRepository.save(
                    this.userConvert.fromDTO(entity, dto)
                )
            }
            .map { entity: User ->
                this.userConvert.toDTO(
                    entity
                )
            }
    }

    override fun delete(id: Long): Optional<UserDTO> {
        return Optional.empty<UserDTO>()
    }

    override fun findById(id: Long): Optional<UserDTO> {
        return this.userRepository.findById(id)
            .map { entity -> this.userConvert.toDTO(entity) }
    }

    override fun findAll(): List<UserDTO> {
        return this.userRepository.findAll().stream()
            .map { entity -> this.userConvert.toDTO(entity) }
            .collect(Collectors.toList())
    }
}