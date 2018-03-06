package com.mykheikin.springproject.util;

import com.mykheikin.springproject.model.User;
import com.mykheikin.springproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    private UserService userService;

    /**
     * @param userService экземпляр для {@link UserValidator#userService}.
     */
   @Autowired
   public UserValidator(UserService userService) {
       this.userService = userService;
   }

   @Override
   public boolean supports(Class<?> aClass) {
       return User.class.isAssignableFrom(aClass);
   }

   @Override
   public void validate(Object o, Errors errors) {
       User user = (User) o;

       if (!user.getPassword().equals(user.getConfirmPassword())) {
           errors.rejectValue("confirmPassword", null, "Passwords do not match.");
       }

       if (!this.userService.isUsernameUnique(user)) {
           errors.rejectValue("username",null, "User is already exist.");
       }
   }
}
