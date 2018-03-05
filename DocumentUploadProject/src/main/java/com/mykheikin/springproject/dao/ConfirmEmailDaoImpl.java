package com.mykheikin.springproject.dao;

import com.mykheikin.springproject.model.ConfirmEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("confirmEmailDao")
@Transactional
public class ConfirmEmailDaoImpl extends AbstractDao<Integer, ConfirmEmail> implements ConfirmEmailDao {

    private static final Logger logger = LoggerFactory.getLogger(ConfirmEmailDaoImpl.class);

    @Override
    public void save(ConfirmEmail confirmEmail) {
        super.persist(confirmEmail);
        logger.info("ConfirmEmail successfully saved. ConfirmEmail details: ", confirmEmail);
    }

    @Override
    public void delete(ConfirmEmail confirmEmail) {
        super.delete(confirmEmail);
        logger.info("ConfirmEmail successfully deleted. ConfirmEmail details: ", confirmEmail);
    }

    @Override
    public ConfirmEmail findById(Integer confirmEmailId) {
        ConfirmEmail confirmEmail = super.getByKey(confirmEmailId);
        logger.info("ConfirmEmail successfully found. ConfirmEmail details: ", confirmEmail);
        return confirmEmail;
    }
}
