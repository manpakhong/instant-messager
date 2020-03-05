package hk.org.hkbh.cms.im.security;

import java.util.Collection;
import java.util.HashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import hk.org.hkbh.cms.im.models.eos.RoleEo;
import hk.org.hkbh.cms.im.models.eos.UserEo;
import hk.org.hkbh.cms.im.repositories.UserRepository;
import hk.org.hkbh.cms.im.transfer.user.CustomUserDetails;

@Service()
public class CustomUserDetailsService implements UserDetailsService {

    Logger log = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    private UserRepository userRepository;

    public CustomUserDetailsService() {
        super();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            UserEo userEo = userRepository.findByUsername(username);
            if (userEo != null) return new CustomUserDetails(userEo, getAuthorities(userEo));
        } catch (Exception ex) {
            log.error("Exception in CustomUserDetailsService: " + ex);
        }
        return null;
    }

    private Collection<GrantedAuthority> getAuthorities(UserEo userEo) {
        Collection<GrantedAuthority> authorities = new HashSet<>();
//        for (RoleEo roleEo : userEo.getRoleEoSet()) {
//            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(roleEo.getRoleName());
//            authorities.add(grantedAuthority);
//        }
        return authorities;
    }
}