package hk.org.hkbh.cms.im.transfer.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import hk.org.hkbh.cms.im.models.eos.UserEo;

import java.util.Collection;

public class CustomUserDetails extends User {

    private UserEo userEo;

    public CustomUserDetails(UserEo userEo, Collection<? extends GrantedAuthority> authorities) {
        super(userEo.getUsername(), userEo.getPassword(), authorities);
        this.userEo = userEo;
    }

    public CustomUserDetails(UserEo userEo, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(userEo.getUsername(), userEo.getPassword(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.userEo = userEo;
    }

    public UserEo getUser() {
        return userEo;
    }
}