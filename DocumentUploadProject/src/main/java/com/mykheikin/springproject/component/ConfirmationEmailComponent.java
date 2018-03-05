package com.mykheikin.springproject.component;

import com.mykheikin.springproject.model.User;

public interface ConfirmationEmailComponent {

    /**
     * @param recipientEmail email получателя.
     */
    void sendConfirmationByEmail(String recipientEmail);

    /**
     * @param user информация о пользователе.
     * @return true если пользователь подтвердил email, иначе false.
     */
    boolean isUserConfirmedEmail(User user);
}
