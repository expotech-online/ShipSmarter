package org.ahmedukamel.shipsmarter.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(name = "USER_EMAIL_UNIQUE_CONSTRAINT", columnNames = {"email", "role"}),
        @UniqueConstraint(name = "USER_PICTURE_UNIQUE_CONSTRAINT", columnNames = "picture")})
public class User implements UserDetails {
    @Id
    private String username;

    @Column(nullable = false)
    private String email;

    private String password;

    private String picture;

    private String providerId;

    @Embedded
    private PhoneNumber number = new PhoneNumber();

    @Embedded
    private Name name;

    @Column(nullable = false, updatable = false)
    private ZonedDateTime registration;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, updatable = false, columnDefinition = "varchar(16) default 'LOCAL'")
    private Provider provider = Provider.LOCAL;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, columnDefinition = "varchar(16) default 'NOT_SELECTED'")
    private Gender gender = Gender.NOT_SELECTED;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, columnDefinition = "varchar(16) default 'USER'")
    private Role role = Role.USER;

    @Column(nullable = false, columnDefinition = "bit(1) default false")
    private boolean enabled;

    @Column(nullable = false, columnDefinition = "bit(1) default true")
    private boolean accountNonLocked = true;

    @ElementCollection
    private Set<String> deviceTokens = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AccessToken> accessTokens = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}