package com.helen.capstoneproject.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Backlog {
    @Id
    @GeneratedValue
    private Long id;
    private Integer PTSequence=0;
    private String projectIdentifier;

    //onetoone - with project
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id", nullable = false)
    @JsonIgnore
    private Project project;
    //onetomany- backlog can have many project tasks
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER ,mappedBy = "backlog")
    private List<ProjectTask> projectTasks = new ArrayList<>();



   

    public List<ProjectTask> getProjectTasks() {
        return projectTasks;
    }




}
