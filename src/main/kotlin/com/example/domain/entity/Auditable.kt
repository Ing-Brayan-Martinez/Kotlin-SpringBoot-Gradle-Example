package com.example.domain.entity

import com.example.domain.types.BooleanConverter
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.io.Serializable
import java.time.LocalDateTime


@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class Auditable : Serializable {
    @CreatedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", referencedColumnName = "user_id", updatable = false)
    private val createdBy: User? = null

    @CreatedDate
    @Column
    private val created: LocalDateTime? = null

    @LastModifiedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by", referencedColumnName = "user_id", updatable = true)
    private val updatedBy: User? = null

    @LastModifiedDate
    @Column
    private val updated: LocalDateTime? = null

    @Convert(converter = BooleanConverter::class)
    @Column(length = 1)
    private val isActive = true
}
