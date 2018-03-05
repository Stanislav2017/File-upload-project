package com.mykheikin.springproject.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.apache.commons.io.FilenameUtils;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Сущность таблицы documents_info (информацией о документе).
 */
@Entity
@Table(name = "documents")
public class Document implements Serializable {

    /**
     * Идентификатор документа. */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Integer id;

    /**
     * Имя документа. */
    @NonNull
    @Getter
    @Setter
    private String name;

    @Transient
    private String fileName;

    /**
     * Расширение файла. */
    @NonNull
    @Getter
    @Setter
    private String type;

    @Transient
    private String fileType;

    /**
     * Автор документа. */
    @NonNull
    @Getter
    @Setter
    private String author;

    /**
     * Дата создания. */
    @NonNull
    @Getter
    @Setter
    private Date createdAt;

    @NonNull
    @Getter
    @Setter
    @Lob @Basic(fetch = FetchType.LAZY)
    private byte[] content;

    /**
     * Описание документа. */
    @NonNull
    @Getter
    @Setter
    private String description;

    public String getFileName() {
        return FilenameUtils.getBaseName(this.getName());
    }

    public String getFileType() {
        return FilenameUtils.getExtension(this.getName());
    }
}
