package com.mykheikin.springproject.service;

import com.mykheikin.springproject.component.ConfirmationEmailComponent;
import com.mykheikin.springproject.dao.ConfirmEmailDao;
import com.mykheikin.springproject.dao.RoleDao;
import com.mykheikin.springproject.dao.UserDao;
import com.mykheikin.springproject.model.ConfirmEmail;
import com.mykheikin.springproject.model.Role;
import com.mykheikin.springproject.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ConfirmationEmailComponent confirmationEmailComponent;

    @Autowired
    private ConfirmEmailDao confirmEmailDao;

    @Override
    public void save(User user) {
        User userLocal = user;
        userLocal.setRegistrationAt(new Date().toString());
        String email = userLocal.getUsername();
        this.confirmationEmailComponent.sendConfirmationByEmail(email);

        ConfirmEmail confirmEmail = new ConfirmEmail();
        confirmEmail.setId(email.hashCode());
        confirmEmail.setEmail(email);
        this.confirmEmailDao.save(confirmEmail);

        String password = this.passwordEncoder.encode(user.getPassword());
        userLocal.setPassword(password);

        Role role = this.roleDao.findById(2);
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        userLocal.setRoles(roles);
        this.userDao.save(userLocal);
    }

    @Override
    public User findById(final Integer userId) {
        User user = this.userDao.findById(userId);
        return user;
    }

    @Override
    public User findByUsername(final String username) {
        User user = this.userDao.findByUsername(username);
        return user;
    }

    @Override
    public void delete(final User user) {
        this.userDao.delete(user);
    }

    @Override
    public List<User> findAllUsers() {
        List<User> users = this.userDao.findAllUsers();
        return users;
    }

    @Override
    public boolean isUsernameUnique(final User user) {
        User userLocal = this.userDao.findByUsername(user.getUsername());
        return userLocal == null ? true : false;
    }
}
