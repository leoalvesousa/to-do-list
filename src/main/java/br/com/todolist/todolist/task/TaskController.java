package br.com.todolist.todolist.task;

import br.com.todolist.todolist.utils.Utils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @PostMapping("/")
    public ResponseEntity create(@RequestBody TaskModel taskModel, HttpServletRequest request){

        var idUser=request.getAttribute("idUser");
        taskModel.setIdUser((String) idUser);

        var currentDate = LocalDateTime.now();
        if(currentDate.isAfter(taskModel.getStartAt()) || currentDate.isAfter(taskModel.getEndAt())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A data de inicio e termino deve ser depois de hoje");
        }

        if(taskModel.getStartAt().isAfter(taskModel.getEndAt())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A data de inicio e não pode ser depois da de termino");
        }
        var task =this.taskRepository.save(taskModel);
        return ResponseEntity.status(HttpStatus.OK).body(task);
    }

    @GetMapping("/")
    public List list(HttpServletRequest request){
        var idUser= request.getAttribute("idUser");

        var tasks = this.taskRepository.findByIdUser((String) idUser);
        return tasks;
    }
    @PutMapping("/{id}")
    public ResponseEntity update (@RequestBody TaskModel taskModel , @PathVariable String id, HttpServletRequest request) {
        var task = this.taskRepository.findById(id).orElse(null);

        if(task == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Essa tarefa não foi encontrada");
        }

        var idUser=request.getAttribute("idUser");
        if (!task.getIdUser().equals(idUser)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Esse usuário não tem autorização para editar essa tarefa");
        }

        Utils.copyNonNullProperties(taskModel, task);
        var taskUpdate =this.taskRepository.save(task);

        return ResponseEntity.ok().body(taskUpdate);
    }
}
