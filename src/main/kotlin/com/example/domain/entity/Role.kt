package com.example.domain.entity

import jakarta.persistence.*

@Entity
@Table(name = "role")
class Role(
    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var roleId: Long = 0,

    @Column(unique = true)
    var name: String,

    @Column
    var description: String) : Auditable()