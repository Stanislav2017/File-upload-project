package com.mykheikin.springproject.controller;

import com.mykheikin.springproject.dao.ConfirmEmailDao;
import com.mykheikin.springproject.dao.RoleDao;
import com.mykheikin.springproject.dao.UserDao;
import com.mykheikin.springproject.model.*;
import com.mykheikin.springproject.service.DocumentService;
import com.mykheikin.springproject.service.UserService;

import com.mykheikin.springproject.util.FileValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/")
public class AppController {

    private UserService userService;

    private DocumentService documentService;

    private AuthenticationTrustResolver authenticationTrustResolver;

    private ConfirmEmailDao confirmEmailDao;

    @Autowired
    private FileValidator fileValidator;

    @InitBinder("fileBucket")
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(fileValidator);
    }

    /**
     * @param userService экземпляр для {@link AppController#userService}.
     * @param documentService экземпляр для {@link AppController#documentService}.
     * @param authenticationTrustResolver экземпляр для {@link AppController#authenticationTrustResolver}.
     * @param confirmEmailDao экземпляр для {@link AppController#confirmEmailDao}.
     */
    @Autowired
    public AppController(
            UserService userService,
            DocumentService documentService,
            AuthenticationTrustResolver authenticationTrustResolver,
            ConfirmEmailDao confirmEmailDao)
    {
        this.userService = userService;
        this.documentService = documentService;
        this.authenticationTrustResolver = authenticationTrustResolver;
        this.confirmEmailDao = confirmEmailDao;
    }

    @RequestMapping(value = { "/download_document/{docId}" }, method = RequestMethod.GET)
    public String downloadDocument(@PathVariable int docId, HttpServletResponse response) throws IOException {
        Document document = this.documentService.findById(docId);
        response.setContentType(document.getType());
        response.setContentLength(document.getContent().length);
        response.setHeader("Content-Disposition","attachment; filename=\"" + document.getName() +"\"");

        FileCopyUtils.copy(document.getContent(), response.getOutputStream());

        return "redirect:/documents";
    }

    @RequestMapping(value = "/confirm_email/{hashCode}", method = RequestMethod.GET)
    public String confirmEmailAddres(@PathVariable Integer hashCode, ModelMap model) {
        ConfirmEmail confirmEmail = this.confirmEmailDao.findById(hashCode);

        if (null == confirmEmail) {
            throw new RuntimeException("Page does not exist or has not yet been created");
        }

        this.confirmEmailDao.delete(confirmEmail);
        model.addAttribute("email", confirmEmail);

        return "verified";
    }

    @RequestMapping(value = { "/documents" }, method = RequestMethod.GET)
    public String listUsers(ModelMap model) {

        List<Document> documents = this.documentService.findAllDocuments();
        model.addAttribute("documents", documents);
        return "documents";
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
    public String saveUser(@Valid User user, BindingResult result) {

        if (!user.getPassword().equals(user.getConfirmPassword())) {
            result.rejectValue("confirmPassword", null, "Passwords do not match.");
        }

        if (!this.userService.isUsernameUnique(user)) {
            result.rejectValue("username",null, "User is already exist.");
        }

        if (result.hasErrors()) {
            return "registration";
        }

        this.userService.save(user);
        return "login";
    }

    @RequestMapping(value = { "/document" }, method = RequestMethod.GET)
    public String newDocument(ModelMap model) {
        FileBucket fileBucket = new FileBucket();
        model.addAttribute("fileBucket", fileBucket);
        return "documentform";
    }

    @RequestMapping(value = { "/document" }, method = RequestMethod.POST)
    public String saveDocument(@Valid FileBucket fileBucket, BindingResult result) throws IOException {

        if (result.hasErrors()) {
            return "documentform";
        }

//        String username = this.getUsername();
        this.documentService.save(fileBucket, "stanislav");
        return "redirect:/documents";
    }


    private String getUsername() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

    /**
     * This method returns true if users is already authenticated [logged-in], else false.
     */
    private boolean isCurrentAuthenticationAnonymous() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return this.authenticationTrustResolver.isAnonymous(authentication);
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        return "redirect:/login?logout";
    }
}
