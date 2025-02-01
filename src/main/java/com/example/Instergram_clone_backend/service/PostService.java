package com.example.Instergram_clone_backend.service;

import com.example.Instergram_clone_backend.exceptions.PostException;
import com.example.Instergram_clone_backend.exceptions.UserException;
import com.example.Instergram_clone_backend.modal.Post;
import org.springframework.stereotype.Service;

import java.util.List;
public interface PostService {
    public Post createpost(Post post ,  Integer userId) throws UserException;

    public String deletePost(Integer postId, Integer userId) throws UserException, PostException;

    public List<Post> findPostByUserId(Integer userId) throws UserException;

    public Post findPostById(Integer postId) throws PostException;

    public List<Post> findAllPostByUserIds(List<Integer> userIds) throws PostException, UserException;

    public String savedPost(Integer postId , Integer userId) throws PostException, UserException;

    public String unSavedPost(Integer postId , Integer userId) throws PostException, UserException;

    public Post likePost(Integer postId, Integer userId) throws PostException, UserException;

    public Post unLikePost(Integer postId , Integer userId) throws UserException, PostException;

}
