package com.mykheikin.springproject.service;

import com.mykheikin.springproject.model.User;

import java.util.List;

public interface UserService {

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
     * @param username имя пользователя.
     * @return информация о пользователе.
     */
    User findByUsername(String username);

    /**
     * Удаляет информацию о пользователе из БД.
     * @param user информация о пользователе.
     */
    void delete(User user);

    /**
     * @return список пользователей.
     */
    List<User> findAllUsers();

    /**
     * @param user информация о пользователе.
     * @return true если пользователь не существуеь, иначе false.
     */
    public boolean isUsernameUnique(User user);
}
