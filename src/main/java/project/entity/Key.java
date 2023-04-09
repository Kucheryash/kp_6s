package project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


@Entity(name = "roles")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Key implements UserDetails{
    @Column(name = "id_key")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int role;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String password;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user", nullable = false, foreignKey = @ForeignKey(name = "fk_key_id_user"))
    private User user;

    //security
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return login;
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
        return true;
    }

}
