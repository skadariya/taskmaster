package saurav.taskmaster.task.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import saurav.taskmaster.task.Model.Task;
import saurav.taskmaster.task.Repository.TaskRepository;

import java.util.ArrayList;
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
    public List<Task> postTasks(@RequestBody Task task) {
    // Task task doesn't matter what you have in constructor, it what data is been passed
        if (task.getAssignee().equals("")) {
            task.setStatus("Available");
        }else {
            task.setStatus("Assigned");
        }
        taskRepository.save(task);
        List<Task> allTask = (List) taskRepository.findAll();
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
    /*
    Lab 27: Structuring Data with NoSQL
     */
    @GetMapping("/users/{name}/tasks")
    public List<Task> getTaskByAssignee(@PathVariable String name){
        List<Task> allTask = taskRepository.findByAssignee(name);
        return allTask;
    }
    @PutMapping("/tasks/{id}/assign/{assignee}")
    public List<Task> putTasksIdAssignAssignee(@PathVariable UUID id, @PathVariable String assignee){
        Task task = taskRepository.findById(id).get();
        if(task != null){
            task.setAssignee(assignee);
            task.setStatus("Assigned");
            taskRepository.save(task);
        }

        List<Task> allTask = (List)taskRepository.findAll();
        return allTask;
    }
}
