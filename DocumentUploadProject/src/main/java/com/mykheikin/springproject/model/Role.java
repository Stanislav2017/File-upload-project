package com.mykheikin.springproject.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Сущность таблицы roles (роли пользователя).
 */
@Entity
@Table(name = "roles")
@NoArgsConstructor
public class Role {

    /**
     * Идентификатор роли. */
    @Id
    @Getter
    @Setter
    private Integer id;

    /**
     * Название роли. */
    @Getter
    @Setter
    private String name;

    /**
     * Список пользователе с данной ролью. */
    @ManyToMany(mappedBy = "roles")
    @Getter
    @Setter
    private Set<User> users = new HashSet<>();
}
