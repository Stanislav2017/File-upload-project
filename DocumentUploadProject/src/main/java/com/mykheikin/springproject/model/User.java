package com.mykheikin.springproject.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Сущность таблицы users (информация о пользователе).
 */
@Entity
@Table(name = "users")
@NoArgsConstructor
public class User implements Serializable {

    /**
     * Идентификатор пользователя. */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Integer id;

    /**
     * Имя пользователя. */
    @Email
    @NotEmpty
    @Getter
    @Setter
    private String username;

    /**
     * Пароль. */
    @NotEmpty
    @Getter
    @Setter
    private String password;

    /**
     * Повторный ввод пароля. */
    @Getter
    @Setter
    @Transient
    private String confirmPassword;

    /**
     * Дата регистрации. */
    @Getter
    @Setter
    private String registrationAt;

    /**
     * Роли пользователя. */
    @Getter
    @Setter
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable
            (name = "user_roles",
                    joinColumns = @JoinColumn(name = "user_id"),
                    inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
}
