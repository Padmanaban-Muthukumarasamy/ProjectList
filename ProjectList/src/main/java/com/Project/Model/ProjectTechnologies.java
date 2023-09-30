package com.Project.Model;

import lombok.Data;

import java.util.List;

@Data
public class ProjectTechnologies {

    List<String> backend;

    List<String> frontend;

    List<String> databases;
}
