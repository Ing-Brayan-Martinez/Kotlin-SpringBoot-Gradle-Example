package com.example.service

import com.example.domain.entity.User
import org.springframework.security.core.userdetails.UserDetailsService
import java.util.*

interface UserAuditableService : UserDetailsService {
    fun getCurrentUser(): Optional<User>
}