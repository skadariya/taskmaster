package saurav.taskmaster.task.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;
import saurav.taskmaster.task.Model.Task;
import saurav.taskmaster.task.Repository.S3Client;
import saurav.taskmaster.task.Repository.TaskRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class TasksController {

    private S3Client s3Client;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    TasksController(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    @GetMapping("/home")
    public String getHome(){
        return "index";
    }

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
    /*
    Lab 04: Programmatic S3 Uploads
     */
    // .get(0) returns the file url in regular bucket
    // .get(1) returns the file url saved in the resized bucket
    @PostMapping("/tasks/{id}/images")
    public RedirectView addImages(@PathVariable UUID id, @RequestParam(value="file")MultipartFile file){
        Task selectedTask = taskRepository.findById(id).get();
        List<String> pic = this.s3Client.uploadFile(file);
        //saving images and thumbImg
        selectedTask.setImages(pic.get(0));
        selectedTask.setThumbImg(pic.get(1));

        taskRepository.save(selectedTask);
        return new RedirectView("http://taskmaster1.s3-website-us-west-2.amazonaws.com/");
    }
    @GetMapping("/tasks/{id}")
    public Task addImages(@PathVariable UUID id){
        Task selectedTask = taskRepository.findById(id).get();
        return selectedTask;
    }
}
