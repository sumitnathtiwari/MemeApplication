package com.example.memeapplication.meme_starter.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Document(collection = "test_db")
public class MemeModel {

    @Id
    private String id;

    private Date currenttime;
    private String username;
    private String caption;
    private String uri;

}
