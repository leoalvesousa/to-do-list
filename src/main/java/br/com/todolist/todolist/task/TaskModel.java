package br.com.todolist.todolist.task;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import java.time.LocalDateTime;
@Data
@Entity(name ="Task")
public class TaskModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String user;
    private String description;
    private String title;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String priority;

    private String idUser;

    public void setTitle(String title) throws Exception{
        if (title.length()>50){
            throw new Exception("O campo titulo só deve ter 50 caratcteres");
        }
        this.title = title;
    }

}
