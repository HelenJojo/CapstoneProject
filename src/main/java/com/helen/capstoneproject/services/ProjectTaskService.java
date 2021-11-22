package com.helen.capstoneproject.services;

import com.helen.capstoneproject.domain.Backlog;
import com.helen.capstoneproject.domain.Project;
import com.helen.capstoneproject.domain.ProjectTask;
import com.helen.capstoneproject.exceptions.ProjectNotFoundException;
import com.helen.capstoneproject.repositories.BacklogRepository;
import com.helen.capstoneproject.repositories.ProjectRepository;
import com.helen.capstoneproject.repositories.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class ProjectTaskService {

    @Autowired
    private BacklogRepository backlogRepository;
    @Autowired
    private ProjectTaskRepository projectTaskRepository;
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectService projectService;

    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask ,String username){

           Backlog backlog=  projectService.findProjectByIdentifier(projectIdentifier,username).getBacklog();//backlogRepository.findByProjectIdentifier(projectIdentifier);


           projectTask.setBacklog(backlog);
           Integer BacklogSequence= backlog.getPTSequence();
           BacklogSequence++;
           backlog.setPTSequence(BacklogSequence);

           projectTask.setProjectSequence(projectIdentifier+"-"+BacklogSequence);
           projectTask.setProjectIdentifier(projectIdentifier);

           if(projectTask.getStatus()==""|| projectTask.getStatus()==null ){
               projectTask.setStatus("TO_DO");
           }
           if(projectTask.getPriority()==null || projectTask.getPriority()==0){
               projectTask.setPriority(3);
           }


           return projectTaskRepository.save(projectTask);

    }

    public Iterable<ProjectTask> findBacklogById(String id, String username) {
        projectService.findProjectByIdentifier(id, username);
        return projectTaskRepository.findByProjectIdentifierOrderByPriority(id);
    }
    public ProjectTask findPTByProjectSequence(String backlog_id,String pt_id, String  username){

        //making sure we search on an existing backlog
      projectService.findProjectByIdentifier(backlog_id, username);
        //make sure that our task exists
        ProjectTask projectTask = projectTaskRepository.findByProjectSequence(pt_id);

        if(projectTask == null){
            throw new ProjectNotFoundException("Project Task '"+pt_id+"' not found");
        }

        //make sure that the backlog/project id in the path corresponds to the right project
        if(!projectTask.getProjectIdentifier().equals(backlog_id)){
            throw new ProjectNotFoundException("Project Task '"+pt_id+"' does not exist in project: '"+backlog_id);
        }

        return projectTask;
    }
    public ProjectTask updateByProjectSequence(ProjectTask updatedTask, String backlog_id, String pt_id, String username){
        ProjectTask projectTask= findPTByProjectSequence(backlog_id,pt_id, username);
        projectTask=updatedTask;
        return projectTaskRepository.save(projectTask);
    }

    public void deletePTByProjectSequence(String backlog_id, String pt_id, String username){
        ProjectTask projectTask= findPTByProjectSequence(backlog_id,pt_id, username);
        projectTaskRepository.delete(projectTask);
    }
}
