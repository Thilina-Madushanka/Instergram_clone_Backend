package com.example.Instergram_clone_backend.modal;

import com.example.Instergram_clone_backend.dto.UserDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "Stories")
public class Story {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="id",column=@Column(name="user_id")),
            @AttributeOverride(name="email",column=@Column(name="user_email"))
    })
    private UserDto user;
    @NotNull
    private String image;
    private String caption;
    private LocalDateTime timestamp;

    public Story(){

    }

    //constructor
    public Story(Integer id, UserDto user, String image, String caption, LocalDateTime timestamp) {
        super();
        this.id = id;
        this.user = user;
        this.image = image;
        this.caption = caption;
        this.timestamp = timestamp;
    }

    //insert getter and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
