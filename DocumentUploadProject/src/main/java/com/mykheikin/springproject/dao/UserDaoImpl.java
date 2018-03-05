package com.mykheikin.springproject.dao;

import com.mykheikin.springproject.model.User;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("userDao")
public class UserDaoImpl extends  AbstractDao<Integer, User> implements UserDao {

    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @Override
    public void save(User user) {
        super.persist(user);
        logger.info("User successfully saved. User details: " + user);
    }

    @Override
    public User findById(Integer userId) {
        User user = super.getByKey(userId);
        logger.info("User successfully found. User details: " + user);
        return user;
    }

    @Override
    public User findByUsername(String username) {
        Criteria crit = super.createEntityCriteria();
        crit.add(Restrictions.eq("username", username));
        User user = (User) crit.uniqueResult();
        logger.info("User successfully found. User details: " + user);

        return user;
    }

    @Override
    public void delete(User user) {
        super.delete(user);
        logger.info("User successfully deleted. User details: " + user);
    }

    @Override
    public List<User> findAllUsers() {
        Criteria criteria = super.createEntityCriteria().addOrder(Order.asc("id"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<User> users = super.createEntityCriteria().list();

        for (User user : users) {
            logger.info("User successfully found. User details: " + user);
        }
        return users;
    }
}
