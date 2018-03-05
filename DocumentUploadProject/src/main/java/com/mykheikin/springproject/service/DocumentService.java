package com.mykheikin.springproject.service;

import com.mykheikin.springproject.model.Document;
import com.mykheikin.springproject.model.FileBucket;

import java.io.IOException;
import java.util.List;

public interface DocumentService {

    /**
     * Сохраняет информацию о документе в БД.
     * @param document информация о документе.
     */
    void save(FileBucket fileBucket, String username) throws IOException;

    /**
     * Обновляет информацию о документе в БД.
     * @param document информация о документе.
     * @param username имя пользователя.
     */
    void update(Document document, String username);

    /**
     * @param documentId идентификатор документа.
     * @return информация о документе.
     */
    Document findById(Integer documentId);

    /**
     * Удаляет информацию о документе из БД.
     * @param document информация о документе.
     * @param username имя авторизированного пользователя.
     */
    void delete(Document document, String username);

    /**
     * @return список с информацией о документах.
     */
    List<Document> findAllDocuments();
}
