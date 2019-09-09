package com.feechan.projectk.projectk.entity;

import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @author Feechan
 * @since 9/9/2019
 */
@Data
@Builder
@Document
public class User implements Serializable {
    @Id
    private ObjectId id;
    private String name;
    private String username;
    private String email;
    private String password;
}
