package com.example.Instergram_clone_backend.service;

import com.example.Instergram_clone_backend.dto.UserDto;
import com.example.Instergram_clone_backend.exceptions.PostException;
import com.example.Instergram_clone_backend.exceptions.UserException;
import com.example.Instergram_clone_backend.modal.Post;
import com.example.Instergram_clone_backend.modal.User;
import com.example.Instergram_clone_backend.repository.PostRepository;
import com.example.Instergram_clone_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImplementation implements PostService{
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Post createPost (Post post, Integer userId) throws UserException {

        User user = userService.findUserById(userId);

        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setName(user.getName());
        userDto.setUserImage(user.getImage());
        userDto.setUsername(user.getUsername());

        post.setUser(userDto);
//        post.setCreatedAt(LocalDateTime.now()); // Ensure createdAt is set

        Post createdPost = postRepository.save(post);

        return createdPost;
    }

    @Override
    public String deletePost(Integer postId, Integer userId) throws UserException, PostException {

        Post post = findPostById(postId);

        User user = userService.findUserById(userId);

        if(post.getUser().getId().equals(user.getId())){
            postRepository.deleteById(post.getId());
            return "Post deleted Successfully!";
        }
        throw new PostException("You can't delete Other User's post!");

    }

    @Override
    public List<Post> findPostByUserId(Integer userId) throws UserException {

        List<Post> posts = postRepository.findByUserId(userId);

        if(posts.size()==0){
            throw new UserException("this user does not have any post");
        }

        return posts;
    }

    @Override
    public Post findPostById(Integer postId) throws PostException {

        Optional<Post> opt = postRepository.findById(postId);

        if(opt.isPresent()){
            return opt.get();
        }
        throw new PostException("Post not found with id"+postId);
    }

    @Override
    public List<Post> findAllPostByUserIds(List<Integer> userIds) throws PostException {

        List<Post> posts= postRepository.findAllPostByUserIds(userIds);
        if(posts.size()==0){
            throw new PostException("no posts available");
        }
        return posts;
    }

    @Override
    public String savedPost(Integer postId, Integer userId) throws PostException, UserException {

        Post post = findPostById(postId);

        User user = userService.findUserById(userId);

        if(!user.getSavePost().contains(post)){
            user.getSavePost().add(post);
            userRepository.save(user);
        }

        return "Post saved successfully!";
    }

    @Override
    public String unSavedPost(Integer postId, Integer userId) throws PostException, UserException {
        Post post = findPostById(postId);

        User user = userService.findUserById(userId);

        if(user.getSavePost().contains(post)){
            user.getSavePost().remove(post);
            userRepository.save(user);
        }

        return "Post Remove successfully!";
    }

    @Override
    public Post likePost(Integer PostId, Integer userId) throws PostException, UserException {

        Post post = findPostById(PostId);
        User user = userService.findUserById(userId);

        UserDto userDto = new UserDto();

        userDto.setEmail(user.getEmail());
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setUserImage(user.getImage());
        userDto.setUsername(user.getUsername());

        post.getLikedByUsers().add(userDto);

        return postRepository.save(post);
    }

    @Override
    public Post unLikePost(Integer PostId, Integer userId) throws UserException, PostException {
        Post post = findPostById(PostId);
        User user = userService.findUserById(userId);
        UserDto userDto = new UserDto();

        userDto.setEmail(user.getEmail());
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setUserImage(user.getImage());
        userDto.setUsername(user.getUsername());

        post.getLikedByUsers().remove(userDto);

        return postRepository.save(post);
    }
}
