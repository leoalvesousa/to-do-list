package br.com.todolist.todolist.user;

import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Id> {
    UserModel findByUsername(String username);
}
