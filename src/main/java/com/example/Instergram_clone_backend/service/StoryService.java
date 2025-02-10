package com.example.Instergram_clone_backend.service;

import com.example.Instergram_clone_backend.exceptions.StoryException;
import com.example.Instergram_clone_backend.exceptions.UserException;
import com.example.Instergram_clone_backend.modal.Story;

import java.util.List;

public interface StoryService {

    public Story createStory(Story story, Integer userId) throws UserException;

        public List<Story> findStoryByUserId(Integer userId) throws  UserException , StoryException;



}
