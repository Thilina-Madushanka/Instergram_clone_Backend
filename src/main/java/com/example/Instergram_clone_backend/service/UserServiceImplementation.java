package com.example.Instergram_clone_backend.service;

import com.example.Instergram_clone_backend.dto.UserDto;
import com.example.Instergram_clone_backend.exceptions.UserException;
import com.example.Instergram_clone_backend.modal.User;
import com.example.Instergram_clone_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class UserServiceImplementation implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerUser(User user) throws UserException {

        Optional<User> isEmailExists = userRepository.findByEmail(user.getEmail());
        if(isEmailExists.isPresent()){
            throw new UserException("Email is Already Exists!");
        }

        Optional<User> isUsernameExists = userRepository.findByUsername(user.getUsername());
        if(isUsernameExists.isPresent()){
            throw new UserException("Username is Already Taken!");
        }

        if(user.getEmail()==null || user.getPassword()==null || user.getUsername()==null || user.getName()==null){
            throw new UserException("All fields are required!");
        }

        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setPassword((user.getPassword()));
        newUser.setUsername(user.getUsername());
        newUser.setName(user.getName());

        return userRepository.save(newUser);
    }

    @Override
    public User findUserById(Integer userId) throws UserException {

        Optional<User> opt = userRepository.findById(userId);
        if(opt.isPresent()){
            return opt.get();
        }
        throw new UserException("user not exist with id:" + userId);
    }

    @Override
    public User findUserProfile(String token) throws UserException {
        return null;
    }

    @Override
    public User findUserByUsername(String username) throws UserException {
        return null;
    }

    @Override
    public String followUser(Integer reqUserId, Integer followUserId) throws UserException {

        User reqUser = findUserById(reqUserId);
        User followUser = findUserById(followUserId);

        UserDto follower = new UserDto();

        follower.setEmail(reqUser.getEmail());
        follower.setId(reqUser.getId());
        follower.setName(reqUser.getName());
        follower.setUserImage(reqUser.getUsername());
        follower.setUsername(reqUser.getUsername());

        UserDto following =  new UserDto();
        following.setEmail(follower.getEmail());
        following.setId(follower.getId());
        following.setUserImage(follower.getUserImage());
        following.setName(follower.getName());
        following.setUsername(following.getUsername());

        reqUser.getFollowing().add(following);
        followUser.getFollower().add(follower);

        userRepository.save(followUser);
        userRepository.save(reqUser);

        return "you are following" + followUser.getUsername();
    }

    @Override
    public String unFollowUser(Integer reqUserId, Integer followUserId) throws UserException {

        User reqUser = findUserById(reqUserId);
        User followUser = findUserById(followUserId);

        UserDto follower = new UserDto();

        follower.setEmail(reqUser.getEmail());
        follower.setId(reqUser.getId());
        follower.setName(reqUser.getName());
        follower.setUserImage(reqUser.getUsername());
        follower.setUsername(reqUser.getUsername());

        UserDto following =  new UserDto();
        following.setEmail(follower.getEmail());
        following.setId(follower.getId());
        following.setUserImage(follower.getUserImage());
        following.setName(follower.getName());
        following.setUsername(following.getUsername());

        reqUser.getFollowing().remove(following);
        followUser.getFollower().remove(follower);

        userRepository.save(followUser);
        userRepository.save(reqUser);

        return "you have unfollowed" + followUser.getUsername();
    }

    @Override
    public List<User> findUserByIds(List<Integer> userIds) throws UserException {
        return null;
    }

    @Override
    public List<User> searchUser(String query) throws UserException {
        return null;
    }

    @Override
    public User updateUserDetails(User updatedUser, User existingUser) throws UserException {
        return null;
    }
}
