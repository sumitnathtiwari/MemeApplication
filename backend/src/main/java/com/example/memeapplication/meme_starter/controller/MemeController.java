package com.example.memeapplication.meme_starter.controller;

import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

import com.example.memeapplication.meme_starter.dto.MemeUser;
import com.example.memeapplication.meme_starter.model.MemeModel;
import com.example.memeapplication.meme_starter.repositoryservices.MemeRepository;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
/**
 * This is the controller class wich will handle the backedn request for api's
 */
@RestController
public class MemeController {
    
    private static final String ENDPOINT = "/memes";
    private static final ModelMapper modelmapper = new ModelMapper();
    private static final Type memeUserToken = new TypeToken<List<MemeUser>>(){}.getType();


    @Autowired 
    private MemeRepository memeRepository;
    /**
     * this method will return the top 100 latest result from db
     * @return List<MemeUser>
     */
    @GetMapping(ENDPOINT)
    public List<MemeUser> getMeme(@RequestParam(defaultValue = "", name = "id") String id) {
        if(id.isBlank()) {
            Pageable queryPageable = PageRequest.of(0, 5, Direction.DESC, "currenttime");
            Page<MemeModel> memeModels = memeRepository.findAll(queryPageable);
            return modelmapper.map(memeModels.getContent(), memeUserToken);
        }
        List<MemeModel> memeModel = memeRepository.findById(id)
                                        .stream()
                                        .collect(Collectors.toList());
        return modelmapper.map(memeModel,memeUserToken);

    }
    /**
     * this method store the Memes with userId and name in Db
     * @return void
     */
    @PostMapping(ENDPOINT)
    public String postMeme(@RequestBody MemeUser memeUser) {
        try {
            MemeModel inserted = memeRepository.insert(modelmapper.map(memeUser,MemeModel.class));
            return "Success the unique id -:" + inserted.getId();
        } catch (Exception e) {
            return "Database Error Encountered";
        }
    }


}
