package com.mykheikin.springproject.dao;

import com.mykheikin.springproject.model.ConfirmEmail;

public interface ConfirmEmailDao {

    void save(ConfirmEmail confirmEmail);

    void delete(ConfirmEmail confirmEmail);

    ConfirmEmail findById(Integer ConfirmEmailId);
}
