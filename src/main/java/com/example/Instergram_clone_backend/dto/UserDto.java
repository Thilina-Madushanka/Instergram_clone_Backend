package com.example.Instergram_clone_backend.dto;

import com.example.Instergram_clone_backend.modal.User;

import java.util.Objects;

public class UserDto {

    private Integer id;
    private String username;
    private String email;
    private String name;
    private String userImage;


    public UserDto(){
    }

     public UserDto(Integer id, String username, String email, String name, String userImage) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.name = name;
        this.userImage = userImage;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    //hashcode and equals method
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if(getClass() != obj.getClass()) return false;
        UserDto userDto = (UserDto) obj;
        return Objects.equals(id, userDto.id) &&
                Objects.equals(username, userDto.username) &&
                Objects.equals(email, userDto.email) &&
                Objects.equals(name, userDto.name) &&
                Objects.equals(userImage, userDto.userImage);
    }
}
