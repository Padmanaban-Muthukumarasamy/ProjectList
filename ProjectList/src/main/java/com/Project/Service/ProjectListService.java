package com.Project.Service;

import com.Project.Model.ProjectResponse;
import com.Project.Model.Projects;

import java.util.List;

public interface ProjectListService {

    ProjectResponse getAllProjects();

    List<Projects> getProjectsByFilter(List<String> backend, List<String> frontend, List<String> databases);
}
