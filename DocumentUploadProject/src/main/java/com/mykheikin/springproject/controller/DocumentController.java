package com.mykheikin.springproject.controller;

import com.mykheikin.springproject.model.Document;
import com.mykheikin.springproject.model.FileBucket;
import com.mykheikin.springproject.service.DocumentService;
import com.mykheikin.springproject.util.FileValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/")
public class DocumentController {

    private DocumentService documentService;

    private FileValidator fileValidator;

    @InitBinder("fileBucket")
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(this.fileValidator);
    }

    /**
     * @param documentService экземпляр для {@link DocumentController#documentService}.
     */
    @Autowired
    public DocumentController(DocumentService documentService, FileValidator fileValidator) {
        this.documentService = documentService;
        this.fileValidator = fileValidator;
    }

    @RequestMapping(value = { "/documents" }, method = RequestMethod.GET)
    public String listUsers(ModelMap model) {

        List<Document> documents = this.documentService.findAllDocuments();
        model.addAttribute("documents", documents);
        return "documents";
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

    @RequestMapping(value = { "/document" }, method = RequestMethod.GET)
    public String newDocument(ModelMap model) {
        FileBucket fileBucket = new FileBucket();
        model.addAttribute("fileBucket", fileBucket);
        return "documentform";
    }

    @RequestMapping(value = { "/document" }, method = RequestMethod.POST)
    public String saveDocument(
            @ModelAttribute("fileBucket") @Valid FileBucket fileBucket,
            BindingResult result)
            throws IOException
    {
        if (result.hasErrors()) {
            return "documentform";
        }

        String username = this.getUsername();
        this.documentService.save(fileBucket, username);
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
}
