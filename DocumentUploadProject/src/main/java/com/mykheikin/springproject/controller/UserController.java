package com.mykheikin.springproject.controller;

import com.mykheikin.springproject.dao.ConfirmEmailDao;
import com.mykheikin.springproject.model.ConfirmEmail;
import com.mykheikin.springproject.model.User;
import com.mykheikin.springproject.service.DocumentService;
import com.mykheikin.springproject.service.UserService;
import com.mykheikin.springproject.util.FileValidator;
import com.mykheikin.springproject.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class UserController {

    private UserService userService;

    private AuthenticationTrustResolver authenticationTrustResolver;

    private UserValidator userValidator;

    private ConfirmEmailDao confirmEmailDao;

    /**
     * @param userService экземпляр для {@link UserController#userService}.
     * @param authenticationTrustResolver экземпляр для {@link UserController#authenticationTrustResolver}.
     * @param userValidator экземпляр для {@link UserController#userValidator}.
     * @param confirmEmailDao экземпляр для {@link UserController#confirmEmailDao}.
     */
    @Autowired
    public UserController(
            UserService userService,
            AuthenticationTrustResolver authenticationTrustResolver,
            UserValidator userValidator,
            ConfirmEmailDao confirmEmailDao)
    {
        this.userService = userService;
        this.authenticationTrustResolver = authenticationTrustResolver;
        this.userValidator = userValidator;
        this.confirmEmailDao = confirmEmailDao;
    }

    @InitBinder("user")
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(this.userValidator);
    }

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public String login() {
        if (isCurrentAuthenticationAnonymous()) {
            return "login";
        } else {
            return "redirect:/documents";
        }
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(ModelMap model) {
        User user = new User();
        model.addAttribute("user", user);
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("user") @Valid User user, BindingResult result) {

        if (result.hasErrors()) {
            return "registration";
        }

        this.userService.save(user);
        return "login";
    }

    @RequestMapping(value = "/confirm_email/{hashCode}", method = RequestMethod.GET)
    public String confirmEmailAddres(@PathVariable Integer hashCode, ModelMap model) {
        ConfirmEmail confirmEmail = this.confirmEmailDao.findById(hashCode);

        if (null == confirmEmail) {
            throw new RuntimeException("Page does not exist or has not yet been created");
        }

        this.confirmEmailDao.delete(confirmEmail);
        model.addAttribute("email", confirmEmail.getEmail());

        return "verified";
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        return "redirect:/login?logout";
    }

    private boolean isCurrentAuthenticationAnonymous() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return this.authenticationTrustResolver.isAnonymous(authentication);
    }
}
