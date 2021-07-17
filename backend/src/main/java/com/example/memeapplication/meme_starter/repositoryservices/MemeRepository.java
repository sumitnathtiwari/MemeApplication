package com.example.memeapplication.meme_starter.repositoryservices;

import com.example.memeapplication.meme_starter.model.MemeModel;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemeRepository extends MongoRepository<MemeModel,String> {
    
}
