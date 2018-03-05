package com.mykheikin.springproject.dao;

import com.mykheikin.springproject.model.Document;

import java.util.List;

/**
 * Интерфейс для работы с таблицей(documents).
 */
public interface DocumentDao {

    /**
     * @param documentId идентификатор документа.
     * @return информация о документе.
     */
    Document findById(Integer documentId);

    /**
     * Сохраняет информацию о документе в БД.
     * @param document информация о документе.
     */
    void save(Document document);

    /**
     * Обновляет информацию о документе в БД.
     * @param document информация о документе.
     */
    void update(Document document);

    /**
     * Удаляет информацию о документе из БД.
     * @param document информация о документе.
     */
    void delete(Document document);

    /**
     * @return список с информацией о документах.
     */
    List<Document> findAllDocuments();

}
