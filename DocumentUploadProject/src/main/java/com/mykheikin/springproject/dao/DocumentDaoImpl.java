package com.mykheikin.springproject.dao;

import com.mykheikin.springproject.model.Document;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("documentDao")
public class DocumentDaoImpl  extends AbstractDao<Integer, Document> implements DocumentDao {

    private static final Logger logger = LoggerFactory.getLogger(DocumentDaoImpl.class);

    @Override
    public Document findById(Integer documentId) {
        Document document = super.getByKey(documentId);
        logger.info("Docoment successfully found. Document details: " + document);
        return document;
    }

    @Override
    public void save(Document document) {
        super.persist(document);
        logger.info("Docoment successfully saved. Document details: " + document);
    }

    @Override
    public void update(Document document) {
        super.update(document);
        logger.info("Docoment successfully updated. Document details: " + document);
    }

    @Override
    public void delete(Document document) {
        super.delete(document);
        logger.info("Docoment successfully deleted. Document details: " + document);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Document> findAllDocuments() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Document> documents = super.createEntityCriteria().list();

        for (Document document : documents) {
            logger.info("Docoment successfully found. Document details: " + document);
        }
        return documents;
    }
}
