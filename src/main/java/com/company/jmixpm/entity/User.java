package com.company.jmixpm.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.SystemLevel;
import io.jmix.core.metamodel.annotation.DependsOnProperties;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import io.jmix.security.authentication.JmixUserDetails;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
@JmixEntity
@Entity
@Table(name = "USER_", indexes = {
    @Index(name = "IDX_USER__ON_USERNAME", columnList = "USERNAME", unique = true)
})
public class User implements JmixUserDetails {

  @Column(name = "USERNAME", nullable = false)
  protected String username;
  @SystemLevel
  @Column(name = "PASSWORD")
  protected String password;
  @Column(name = "FIRST_NAME")
  protected String firstName;
  @Column(name = "LAST_NAME")
  protected String lastName;
  @Email
  @Column(name = "EMAIL")
  protected String email;
  @Column(name = "ACTIVE")
  protected Boolean active = true;
  @Transient
  protected Collection<? extends GrantedAuthority> authorities;
  @Id
  @Column(name = "ID")
  @JmixGeneratedValue
  private UUID id;
  @Version
  @Column(name = "VERSION", nullable = false)
  private Integer version;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities != null ? authorities : Collections.emptyList();
  }

  @Override
  public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
    this.authorities = authorities;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return Boolean.TRUE.equals(active);
  }

  @InstanceName
  @DependsOnProperties({"firstName", "lastName", "username"})
  public String getDisplayName() {
    return String.format("%s %s [%s]", (firstName != null ? firstName : ""),
        (lastName != null ? lastName : ""), username).trim();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof User)) {
      return false;
    }
    User user = (User) o;
    return id.equals(user.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}