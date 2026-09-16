package com.marcosmartinezdeveloper.practiceProject.persistence.entities;

import java.util.Collection;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.marcosmartinezdeveloper.practiceProject.persistence.entities.Utils.AuditableDates;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.SuperBuilder;
import lombok.ToString;

@Entity
@Table(name="users")

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class User extends AuditableDates implements UserDetails{

	private static final long serialVersionUID = 7797239752479129282L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private String password;

    private String role;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(
	        new SimpleGrantedAuthority("ROLE_" + role)
		);
	}
	

	public Long getId() {
		return this.id;
	}

	@Override
	public @Nullable String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	public String getRole() {
		return this.role;
	}
}
