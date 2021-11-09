package com.helen.capstoneproject.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ProjectTask {
    @Id
    @GeneratedValue
    private  Long id;
    @Column(updatable = false,unique = true)
    private String projectSequence;
    @NotBlank(message = "Include a project summary")
    private String summary;
    private  String acceptanceCriteria;
    private String status;
    private Integer priority;
    private Date dueDate;
    @Column(updatable = false)
    private String projectIdentifier;
    private  Date create_At;
    private Date update_At;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "backlog_id", updatable = false, nullable = false)
    @JsonIgnore
    private Backlog backlog;
    @PrePersist
    protected  void onCreate(){
        this.create_At= new Date();
    }


    @PreUpdate
    protected void onUpdate(){
        this.update_At= new Date();
    }



    @Override
    public String toString() {
        return "ProjectTask{" +
                "id=" + id +
                ", projectSequence='" + projectSequence + '\'' +
                ", summary='" + summary + '\'' +
                ", acceptanceCriteria='" + acceptanceCriteria + '\'' +
                ", status='" + status + '\'' +
                ", priority=" + priority +
                ", dueDate=" + dueDate +
                ", projectIdentifier='" + projectIdentifier + '\'' +
                ", create_At=" + create_At +
                ", update_At=" + update_At +
                '}';
    }

}
