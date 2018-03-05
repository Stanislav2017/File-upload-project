package com.mykheikin.springproject.service;

import com.mykheikin.springproject.dao.DocumentDao;
import com.mykheikin.springproject.model.Document;
import com.mykheikin.springproject.model.FileBucket;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service("documentService")
@Transactional
public class DocumentServiceImpl implements DocumentService {

    private DocumentDao documentDao;

    /**
     * @param documentDao экземпляр для {@link DocumentServiceImpl#documentDao}.
     */
    @Autowired
    public DocumentServiceImpl(DocumentDao documentDao) {
        this.documentDao = documentDao;
    }

    @Override
    public void save(FileBucket fileBucket, String username) throws IOException {
        MultipartFile multipartFile = fileBucket.getFile();

        Document document = new Document();
        document.setCreatedAt(new Date());
        document.setDescription(fileBucket.getDescription());
        document.setContent(multipartFile.getBytes());
        document.setName(multipartFile.getOriginalFilename());
        document.setType(multipartFile.getContentType());
        document.setAuthor(username);
        this.documentDao.save(document);
    }

    @Override
    public void update(Document document, String username) {
        String authorName = document.getAuthor();
        if (!authorName.equals(username)) {
            throw new RuntimeException("User is not the creator of the document");
        }
        this.documentDao.update(document);
    }

    @Override
    public Document findById(Integer documentId) {
        Document document = this.documentDao.findById(documentId);
        return document;
    }

    @Override
    public void delete(Document document, String username) {
        String authorName = document.getAuthor();
        if (!authorName.equals(username)) {
            throw new RuntimeException("User is not the creator of the document");
        }
        this.documentDao.delete(document);
    }

    @Override
    public List<Document> findAllDocuments() {
        List<Document> documents = this.documentDao.findAllDocuments();
        return documents;
    }
}
