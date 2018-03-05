package com.mykheikin.springproject.dao;

import com.mykheikin.springproject.model.Role;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("roleDao")
public class RoleDaoImpl extends AbstractDao<Integer, Role> implements RoleDao {

    private static final Logger logger = LoggerFactory.getLogger(RoleDaoImpl.class);

    @Override
    public Role findById(Integer roleId) {
        Role role = super.getByKey(roleId);
        logger.info("Role successfully found. Role details: " + role);
        return role;
    }

    @Override
    public Role findByName(String name) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("name", name));
        Role role = (Role) crit.uniqueResult();
        logger.info("Role successfully found. Role details: " + role);

        return role;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Role> findAllRoles() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Role> roles = super.createEntityCriteria().list();

        for (Role role : roles) {
            logger.info("Role successfully found. Role details: " + role);
        }
        return roles;
    }
}
