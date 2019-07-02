package saurav.taskmaster.task.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import saurav.taskmaster.task.Model.Task;
import saurav.taskmaster.task.Repository.TaskRepository;

import java.util.List;
import java.util.UUID;

@RestController
public class TasksController {
    @Autowired
    TaskRepository taskRepository;

    @GetMapping("/home")
    public String getHome(){
        return "index";
    }
    //String title,String description, String status
    /*
    ** Commented out due, initially create controller, updated to rest controller

    @GetMapping("/task/post")
            public String postTask(){
            return "getTask";
    }
    @PostMapping(value = "/task/post")
    public String postTask(@RequestParam String title, String description){
        Task newTask = new Task(title,description);
        taskRepository.save(newTask);
        return "index";
    }
    @GetMapping("/task/{id}/put")
    public String putTask(){
        return "getTask";
    }
    */
    @PostMapping("/tasks")
    public List<Task> postTasks(@RequestParam String title, String description) {
        Task newTask = new Task(title, description);
        taskRepository.save(newTask);
        List<Task> allTask = (List)taskRepository.findAll();
        return allTask;
    }
    @GetMapping("/tasks")
    public List<Task> getTasks(){
        List<Task> allTask = (List)taskRepository.findAll();
        return allTask;
    }
    @PutMapping("/tasks/{id}/state")
    public List<Task> putTasks(@PathVariable UUID id){
        Task task = taskRepository.findById(id).get();
        if(task.getStatus().equals("Available")){
            task.setStatus("Assigned");
        }
        else if(task.getStatus().equals("Assigned")){
            task.setStatus("Accepted");
        }
        else if(task.getStatus().equals("Accepted")){
            task.setStatus("Finished");
        }
        taskRepository.save(task);
        List<Task> allTask = (List)taskRepository.findAll();
        return allTask;
    }
}
