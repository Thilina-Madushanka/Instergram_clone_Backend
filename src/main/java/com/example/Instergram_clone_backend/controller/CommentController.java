package com.example.Instergram_clone_backend.controller;

import com.example.Instergram_clone_backend.exceptions.CommentException;
import com.example.Instergram_clone_backend.exceptions.PostException;
import com.example.Instergram_clone_backend.exceptions.UserException;
import com.example.Instergram_clone_backend.modal.Comment;
import com.example.Instergram_clone_backend.modal.User;
import com.example.Instergram_clone_backend.service.CommentService;
import com.example.Instergram_clone_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/comments")
@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;

    @PostMapping("/create/{postId}")
    public ResponseEntity<Comment> createCommentHandler(@RequestBody Comment comment , @PathVariable Integer postId , @RequestHeader("Authorization") String token) throws UserException, PostException {
        User user = userService.findUserProfile(token);

        Comment createdComment = commentService.createComment(comment,postId,user.getId());
        return new ResponseEntity<Comment>(createdComment, HttpStatus.OK);

    }
    @PutMapping("/like/{commentId}")
    public ResponseEntity<Comment> likeCommentHandler(@RequestHeader("Authorization") String token , @PathVariable Integer commentId) throws UserException, CommentException {

        User user = userService.findUserProfile(token);
        Comment comment = commentService.likeComment(commentId, user.getId());

        return new ResponseEntity<Comment>(comment,HttpStatus.OK);

    }

    @PutMapping("/unlike/{commentId}")
    public ResponseEntity<Comment> unlikeCommentHandler(@RequestHeader("Authorization") String token , @PathVariable Integer commentId) throws UserException, CommentException {

        User user = userService.findUserProfile(token);
        Comment comment = commentService.unlikeComment(commentId, user.getId());

        return new ResponseEntity<Comment>(comment,HttpStatus.OK);

    }


}
