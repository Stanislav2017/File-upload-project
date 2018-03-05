package com.mykheikin.springproject.security;

import com.mykheikin.springproject.dao.ConfirmEmailDao;
import com.mykheikin.springproject.dao.UserDao;
import com.mykheikin.springproject.model.ConfirmEmail;
import com.mykheikin.springproject.model.Role;
import com.mykheikin.springproject.model.User;
import com.mykheikin.springproject.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    private UserDao userDao;

    private ConfirmEmailDao confirmEmailDao;

    /**
     * @param userDao экземпляр для {@link CustomUserDetailsService#userDao}.
     * @param confirmEmailDao экземпляр для {@link CustomUserDetailsService#confirmEmailDao}.
     */
    @Autowired
    public CustomUserDetailsService(
            final UserDao userDao,
            final ConfirmEmailDao confirmEmailDao)
    {
        this.userDao = userDao;
        this.confirmEmailDao = confirmEmailDao;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = this.userDao.findByUsername(username);
        logger.info("User : {}", user);
        if(user == null){
            logger.info("User not found");
            throw new UsernameNotFoundException("Username not found");
        }
        ConfirmEmail confirmEmail = this.confirmEmailDao.findById(user.getUsername().hashCode());
        if (null != confirmEmail) {
            throw new RuntimeException("User is not confirm email.");
        }
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                true,
                true,
                true,
                true,
                getGrantedAuthorities(user));
    }


    private List<GrantedAuthority> getGrantedAuthorities(User user){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        for(Role role : user.getRoles()){
            logger.info("UserProfile : {}", role);
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        }
        logger.info("authorities : {}", authorities);
        return authorities;
    }
}
