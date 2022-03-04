package br.com.mir4.guild.manager.core.config.userdetails

import br.com.mir4.guild.manager.model.login.Login
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserDetailsImpl(private val login: Login) : UserDetails {

    override fun getAuthorities() = mutableListOf<GrantedAuthority>()

    override fun getPassword() = login.password

    override fun getUsername() = login.username

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true

    override fun isCredentialsNonExpired() = true

    override fun isEnabled() = true
}
