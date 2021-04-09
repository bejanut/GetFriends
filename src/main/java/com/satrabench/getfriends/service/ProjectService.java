package com.satrabench.getfriends.service;

import com.satrabench.getfriends.model.Project;
import com.satrabench.getfriends.model.Supervised;
import com.satrabench.getfriends.model.Task;
import com.satrabench.getfriends.repository.ProjectRepository;
import com.satrabench.getfriends.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, TaskRepository taskRepository) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }

    public ResponseEntity<Object> getAll(){
        List<Project> project = projectRepository.findAll();
        return new ResponseEntity<Object>(project, HttpStatus.OK);
    }

    public ResponseEntity<Object> create(Project project){
        Project p = projectRepository.save(project);
        return new ResponseEntity<Object>(p, HttpStatus.OK);
    }

    public ResponseEntity<Object> calculateCompletedTasks(Integer id) {
        Integer completedTasks = 0;
        Project project = projectRepository.findById(id).get();
        for (Task task : project.getProjects()) {
            if (task.isDone())
                completedTasks++;
        }

        if(completedTasks == project.getProjects().size()) {
            project.setCompletion(100);
        }
        return new ResponseEntity<Object>(completedTasks, HttpStatus.OK);
    }

    public ResponseEntity<Object> completion(Integer id) {
        Project project = projectRepository.findById(id).get();
        Integer completion = 0;
        if(project.getCompletedTasks() != 0) {
            completion = (project.getProjects().size() * 100) / project.getCompletedTasks();
        }

        return new ResponseEntity<Object>(completion, HttpStatus.OK);
    }

        public ResponseEntity<Object> taskForProject(Supervised supervised, Integer projectId, Task task) {
            Project project = projectRepository.findById(projectId).get();
            task.setProject(project);
            Task task1 = taskRepository.save(task);
            project.getProjects().add(task1);
            projectRepository.save(project);
            return new ResponseEntity<Object>(task, HttpStatus.OK);
        }

}
