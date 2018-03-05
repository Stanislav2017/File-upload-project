package com.mykheikin.springproject.dao;

import com.mykheikin.springproject.model.Role;

import java.util.List;

public interface RoleDao {

    Role findById(Integer roleId);

    Role findByName(String name);

    List<Role> findAllRoles();
}
