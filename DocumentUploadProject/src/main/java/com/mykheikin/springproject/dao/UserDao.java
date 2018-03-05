package com.mykheikin.springproject.dao;

import com.mykheikin.springproject.model.User;

import java.util.List;

public interface UserDao {

    /**
     * Сохраняет информацию о пользователе в БД.
     * @param user информация о пользователе.
     */
    void save(User user);

    /**
     * @param userId идентификатор пользователя.
     * @return информация о пользователе.
     */
    User findById(Integer userId);

    /**
     * @param userName имя пользователя.
     * @return информация о пользователе.
     */
    User findByUsername(String userName);

    /**
     * Удаляет информацию о пользователе из БД.
     * @param user информация о пользователе.
     */
    void delete(User user);

    /**
     * @return список пользователей.
     */
    List<User> findAllUsers();
}
