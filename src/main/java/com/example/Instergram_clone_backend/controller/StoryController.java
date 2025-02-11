package com.example.Instergram_clone_backend.controller;

import com.example.Instergram_clone_backend.exceptions.StoryException;
import com.example.Instergram_clone_backend.exceptions.UserException;
import com.example.Instergram_clone_backend.modal.Story;
import com.example.Instergram_clone_backend.modal.User;
import com.example.Instergram_clone_backend.service.StoryService;
import com.example.Instergram_clone_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stories")
public class StoryController {
    @Autowired
    private UserService userService;
    @Autowired
    private StoryService storyService;
    @PostMapping("/create")
    public ResponseEntity<Story> createStoryHandler(@RequestBody Story story, @PathVariable Integer userId , @RequestHeader("Authorization") String token) throws UserException {

        User user = userService.findUserProfile(token);

        Story createdStory = storyService.createStory(story, user.getId());
        return new ResponseEntity<Story>(createdStory, HttpStatus.OK);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<List<Story>> findAllStoryByUserHandler(@PathVariable Integer userId) throws UserException, StoryException {

        User user = userService.findUserById(userId);
        List<Story> stories = storyService.findStoryByUserId(user.getId());

        return new ResponseEntity<List<Story>>(stories, HttpStatus.OK);

    }


}
