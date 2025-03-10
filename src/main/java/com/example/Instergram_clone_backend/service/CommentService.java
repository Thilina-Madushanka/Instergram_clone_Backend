package com.example.Instergram_clone_backend.service;

import com.example.Instergram_clone_backend.exceptions.CommentException;
import com.example.Instergram_clone_backend.exceptions.PostException;
import com.example.Instergram_clone_backend.exceptions.UserException;
import com.example.Instergram_clone_backend.modal.Comment;

public interface CommentService {

    public Comment createComment(Comment comment , Integer postId, Integer userId) throws UserException, PostException;

    public Comment findCommentById(Integer commentId) throws CommentException;

    public Comment likeComment(Integer commentId, Integer userId) throws CommentException, UserException;

    public Comment unlikeComment(Integer commentId, Integer userId) throws CommentException, UserException;


}
