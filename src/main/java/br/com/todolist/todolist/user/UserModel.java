package br.com.todolist.todolist.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;



@Data
@Entity(name= "Users")
public class UserModel {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @NotBlank
    @Column (unique = true)
    private String username;
    @NotBlank
    private String name;
    @NotBlank
    private String password;
    @CreationTimestamp
    private LocalDateTime createdAt;
}
