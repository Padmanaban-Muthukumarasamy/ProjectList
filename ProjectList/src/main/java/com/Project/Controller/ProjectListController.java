package com.Project.Controller;

import com.Project.Model.ProjectResponse;
import com.Project.Model.ProjectTechnologies;
import com.Project.Model.Projects;
import com.Project.Service.ProjectListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class ProjectListController {

    @Autowired
    ProjectListService projectListService;

    @GetMapping(value = "/getProjects", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getProjects() {
        ProjectResponse projectLists = projectListService.getAllProjects();
        return new ResponseEntity<>(projectLists, HttpStatus.OK);
    }

    @PostMapping(value = "/getProjectsByTechnologies", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getProjectsByFilter(@RequestBody ProjectTechnologies projectTechnologies) {
        List<Projects> projectListsByFilter = projectListService.getProjectsByFilter(projectTechnologies.getBackend(),projectTechnologies.getFrontend(),projectTechnologies.getDatabases());
        return new ResponseEntity<>(projectListsByFilter, HttpStatus.OK);
    }
}
