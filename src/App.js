//import logo from './logo.svg';
import "./App.css";
import Dashboard from "./components/Dashboard";
import Header from "./components/Layout/Header";
import "bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter as Router, Route } from "react-router-dom";
import CreateProjectButton from "./components/Project/CreateProjectButton";
import AddProject from "./components/Project/AddProject";
import { Provider } from "react-redux";
import store from "./store";
import UpdateProject from "./components/Project/UpdateProject";
import ProjectBoard from "./components/ProjectBoard/ProjectBoard";
import AddProjectTask from "./components/ProjectBoard/ProjectTask/AddProjectTask";

function App() {
  return (
    <Provider store={store}>
      <Router>
        <div className="App">
          <Header />

          <Route exact path="/dashboard" component={Dashboard}></Route>
          <Route exact path="/addProject" component={AddProject}></Route>

          <Route
            exact
            path="/updateProject/:id"
            component={UpdateProject}
          ></Route>
          <Route
            exact
            path="/projectBoard/:id"
            component={ProjectBoard}
          ></Route>
          <Route
            exact
            path="/addProjectTask/:id"
            component={AddProjectTask}
          ></Route>
        </div>
      </Router>
    </Provider>
  );
}

export default App;
