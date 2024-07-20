package com.example.repository

import com.example.domain.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, Long> {

    @Query(value = " SELECT u FROM User u WHERE u.isActive = true AND u.userName = ?1 ")
    fun findByUserName(userName: String): Optional<User>

    @Query(value = " SELECT u FROM User u WHERE u.isActive = true AND u.userId = ?1")
    override fun findById(userId: Long): Optional<User>

    @Query(value = " SELECT u FROM User u WHERE u.isActive = true ")
    override fun findAll(): MutableList<User>
}