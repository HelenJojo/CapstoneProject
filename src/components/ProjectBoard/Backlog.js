import React, { Component } from "react";
import ProjectTask from "./ProjectTask/ProjectTask";

class Backlog extends Component {
  render() {
    const { project_tasks_prop } = this.props;
    const tasks = project_tasks_prop.map((project_task) => (
      <ProjectTask key={project_task.id} project_task={project_task} />
    ));

    let todoItems = [];
    let inProgressItems = [];
    let doneItems = [];
    let dueItems = [];

    for (let i = 0; i < tasks.length; i++) {
      if (tasks[i].props.project_task.status === "TO_DO") {
        todoItems.push(tasks[i]);
      }
      if (tasks[i].props.project_task.status === "IN_PROGRESS") {
        inProgressItems.push(tasks[i]);
      }
      if (tasks[i].props.project_task.status === "DONE") {
        doneItems.push(tasks[i]);
      }
      if (tasks[i].props.project_task.status === "OVERDUE") {
        dueItems.push(tasks[i]);
      }
    }
    return (
      <div className="container">
        <div className="row">
          <div className="col-md-3">
            <div className="card text-center mb-2">
              <div className="card-header bg-secondary text-white">
                <h3>TO DO</h3>
              </div>
            </div>
            {todoItems}
            {
              // <!-- SAMPLE PROJECT TASK STARTS HERE -->
            }
          </div>

          <div className="col-md-3">
            <div className="card text-center mb-2">
              <div className="card-header bg-primary text-white">
                <h3>In Progress</h3>
              </div>
            </div>
            {inProgressItems}
          </div>
          <div className="col-md-3">
            <div className="card text-center mb-2">
              <div className="card-header bg-success text-white">
                <h3>Done</h3>
              </div>
            </div>
            {doneItems}
          </div>

          {
            //<!-- -------------------------------------------------------------------- -->
          }

          <div className="col-md-3">
            <div className="card text-center mb-2">
              <div className="card-header bg-danger text-white">
                <h3>OVERDUE</h3>
              </div>
            </div>
            {dueItems}
          </div>
        </div>
      </div>
    );
  }
}
export default Backlog;
