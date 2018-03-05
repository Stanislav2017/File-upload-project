package com.mykheikin.springproject.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Сущность таблицы confirm_email (подтверждение по email).
 */
@Entity
@Table(name = "confirm_email")
@NoArgsConstructor
public class ConfirmEmail {

    /**
     * Идентификатор подтверждения. */
    @Id
    @Getter
    @Setter
    private Integer id;

    /**
     * Email получателя. */
    @Getter
    @Setter
    private String email;
}
