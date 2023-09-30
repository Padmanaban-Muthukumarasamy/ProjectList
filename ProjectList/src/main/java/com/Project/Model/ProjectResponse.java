package com.Project.Model;

import lombok.Data;

import java.util.List;

@Data
public class ProjectResponse {

    private List<Projects> projects;

    private TechnicalSkillSetList technicalSkillSetList;
}
