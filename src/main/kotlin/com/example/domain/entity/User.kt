package com.example.domain.entity

import com.example.domain.types.BooleanConverter
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.stream.Collectors

@Entity
@Table(name = "users")
class User(
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var userId: Long = 0,

    @Column(length = 400)
    var name: String,

    @Column(name = "username", unique = true)
    var userName: String,

    @JsonIgnore
    @Column
    var password: String,

    @Convert(converter = BooleanConverter::class)
    @Column(nullable = false)
    private var isAccountNonExpired: Boolean = false,

    @Convert(converter = BooleanConverter::class)
    @Column(nullable = false)
    private var isAccountNonLocked: Boolean = false,

    @Convert(converter = BooleanConverter::class)
    @Column(nullable = false)
    private var isCredentialsNonExpired: Boolean = false,

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "user_role",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "role_id")]
    )
    private val roles: List<Role> = ArrayList()) : Auditable(), UserDetails {


    override fun isAccountNonExpired(): Boolean {
        return isAccountNonExpired
    }

    override fun isAccountNonLocked(): Boolean {
        return isAccountNonLocked
    }

    override fun isCredentialsNonExpired(): Boolean {
        return isCredentialsNonExpired
    }

    override fun isEnabled(): Boolean {
        return this.isEnabled
    }

    override fun getAuthorities(): Collection<GrantedAuthority?> {
        return roles.stream()
            .map { role: Role ->
                SimpleGrantedAuthority(
                    role.name
                )
            }
            .collect(Collectors.toList())
    }

    @JsonIgnore
    fun getRoles(): List<Role> {
        return roles
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return userName
    }

}
