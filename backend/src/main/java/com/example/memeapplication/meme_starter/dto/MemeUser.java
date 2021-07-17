package com.example.memeapplication.meme_starter.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemeUser {
    private String username;
    private String caption;
    private String uri;
    private Date currenttime;
}
