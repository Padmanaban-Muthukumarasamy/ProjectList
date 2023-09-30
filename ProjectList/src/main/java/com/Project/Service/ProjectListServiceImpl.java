package com.Project.Service;

import com.Project.Dao.ProjectListRepository;
import com.Project.Model.ProjectList;
import com.Project.Model.ProjectResponse;
import com.Project.Model.Projects;
import com.Project.Model.TechnicalSkillSetList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProjectListServiceImpl implements ProjectListService{

    @Autowired
    ProjectListRepository projectListRepository;

    @Override
    public ProjectResponse getAllProjects() {
        ProjectResponse projectResponse = new ProjectResponse();
        List<ProjectList> projectLists = projectListRepository.findAll();
        List<Projects> projects = new ArrayList<>();
        projects = getProjects(projectLists,projects);
        TechnicalSkillSetList technicalSkillSetList = new TechnicalSkillSetList();
        technicalSkillSetList.setFrontendList(projects.stream().filter(i-> !i.getTechnicalSkillSetFrontend().isEmpty()
                        && !i.getTechnicalSkillSetFrontend().matches("Not Specified|Not mentioned|Not Mentioned|Not specified|Interest in learning|No information provided|Basic knowledge|Undetermined|4-5/10|7.5/10|Unknown|Theoretical knowledge"))
                        .map(Projects::getTechnicalSkillSetFrontend).distinct().sorted().collect(Collectors.toList()));
        technicalSkillSetList.setBackendList(projects.stream().filter(i-> !i.getTechnicalSkillSetBackend().isEmpty()
                && !i.getTechnicalSkillSetBackend().matches("Not Specified|08-Oct|Not Mentioned|Not specified|Not experienced, but a quick learner|None mentioned|Willing to learn|Undetermined|No prior experience with backend frameworks|Unknown|Rating is 6/10|Theoretical knowledge"))
                .map(Projects::getTechnicalSkillSetBackend).distinct().sorted().collect(Collectors.toList()));
        technicalSkillSetList.setDatabasesList(projects.stream().filter(i-> !i.getTechnicalSkillDatabases().isEmpty()
                        && !i.getTechnicalSkillDatabases().matches("Not Specified|Unspecified|Not Mentioned|Not specified|Not Discussed|None mentioned|Undetermined|Not mentioned|Unknown|No information provided|Not provided|Good understanding of data structures and algorithms"))
                .map(Projects::getTechnicalSkillDatabases).distinct().sorted().collect(Collectors.toList()));
        projectResponse.setProjects(projects);
        projectResponse.setTechnicalSkillSetList(technicalSkillSetList);
        return projectResponse;
    }

    private List<Projects> getProjects(List<ProjectList> projectLists,  List<Projects> projects) {
        projectLists.parallelStream().forEachOrdered(projectList -> {
            Projects project = new Projects();
            project.setProjectTitle(getObjStr(projectList.getProject().getProjectTitle()));
            project.setProjectTechnologies(getObjStr(projectList.getProject().getProjectTechnologies()));
            project.setTechnicalSkillSetFrontend(getObjStr(projectList.getTechnicalSkillSet().getTechnicalSkillSetFrontend()));
            project.setTechnicalSkillSetBackend(getObjStr(projectList.getTechnicalSkillSet().getTechnicalSkillSetBackend()));
            project.setTechnicalSkillDatabases(getObjStr(projectList.getTechnicalSkillSet().getTechnicalSkillDatabases()));
            project.setTechnicalSkillSetInfrastructure(getObjStr(projectList.getTechnicalSkillSet().getTechnicalSkillSetInfrastructure()));
            project.setOtherInformationAvailability(getObjStr(projectList.getOtherInformation().getOtherInformationAvailability()));
            projects.add(project);
        });
        return projects;
    }

    public List<Projects> getProjectsByFilter(List<String> backend, List<String> frontend, List<String> databases){
        List<Projects> filterProjectList = new ArrayList<>();
        if(frontend.isEmpty() && databases.isEmpty()){
            List<ProjectList> projectLists = projectListRepository.findByTechnicalSkillSetTechnicalSkillSetBackendIn(backend);
            getProjects(projectLists, filterProjectList);
            filterProjectList = filterProjectList.stream().sorted(Comparator.comparingInt(i->Integer.parseInt(i.getProjectTitle().substring(8)))).collect(Collectors.toList());
        }
        else if(backend.isEmpty() && databases.isEmpty()){
            List<ProjectList> projectLists = projectListRepository.findByTechnicalSkillSetTechnicalSkillSetFrontendIn(frontend);
            getProjects(projectLists, filterProjectList);
            filterProjectList = filterProjectList.stream().sorted(Comparator.comparingInt(i->Integer.parseInt(i.getProjectTitle().substring(8)))).collect(Collectors.toList());
        }
        else if(frontend.isEmpty() && backend.isEmpty()){
            List<ProjectList> projectLists = projectListRepository.findByTechnicalSkillSetTechnicalSkillDatabasesIn(databases);
            getProjects(projectLists, filterProjectList);
            filterProjectList = filterProjectList.stream().sorted(Comparator.comparingInt(i->Integer.parseInt(i.getProjectTitle().substring(8)))).collect(Collectors.toList());
        }
        else if(databases.isEmpty()){
            List<ProjectList> backendProjectLists = projectListRepository.findByTechnicalSkillSetTechnicalSkillSetBackendIn(backend);
            List<ProjectList> frontendProjectLists = projectListRepository.findByTechnicalSkillSetTechnicalSkillSetFrontendIn(frontend);
            getProjects(backendProjectLists, filterProjectList);
            getProjects(frontendProjectLists, filterProjectList);
            filterProjectList = filterProjectList.stream().sorted(Comparator.comparingInt(i->Integer.parseInt(i.getProjectTitle().substring(8)))).collect(Collectors.toList());
        }
        else if (backend.isEmpty()){
            List<ProjectList> frontendProjectLists = projectListRepository.findByTechnicalSkillSetTechnicalSkillSetFrontendInAndTechnicalSkillSetTechnicalSkillDatabasesIn(frontend,databases);
            List<ProjectList> databasesProjectLists = projectListRepository.findByTechnicalSkillSetTechnicalSkillDatabasesIn(databases);
            getProjects(frontendProjectLists, filterProjectList);
            getProjects(databasesProjectLists, filterProjectList);
            filterProjectList = filterProjectList.stream().sorted(Comparator.comparingInt(i->Integer.parseInt(i.getProjectTitle().substring(8)))).collect(Collectors.toList());
        }
        else if(frontend.isEmpty()){
            List<ProjectList> backendProjectLists = projectListRepository.findByTechnicalSkillSetTechnicalSkillSetBackendIn(backend);
            List<ProjectList> databasesProjectLists = projectListRepository.findByTechnicalSkillSetTechnicalSkillDatabasesIn(databases);
            getProjects(backendProjectLists, filterProjectList);
            getProjects(databasesProjectLists, filterProjectList);
            filterProjectList = filterProjectList.stream().sorted(Comparator.comparingInt(i->Integer.parseInt(i.getProjectTitle().substring(8)))).collect(Collectors.toList());
        }
        else {
            List<ProjectList> backendProjectLists = projectListRepository.findByTechnicalSkillSetTechnicalSkillSetBackendIn(backend);
            List<ProjectList> frontendProjectLists = projectListRepository.findByTechnicalSkillSetTechnicalSkillSetFrontendIn(frontend);
            List<ProjectList> databasesProjectLists = projectListRepository.findByTechnicalSkillSetTechnicalSkillDatabasesIn(databases);
            getProjects(backendProjectLists, filterProjectList);
            getProjects(frontendProjectLists, filterProjectList);
            getProjects(databasesProjectLists, filterProjectList);
            filterProjectList = filterProjectList.stream().sorted(Comparator.comparingInt(i->Integer.parseInt(i.getProjectTitle().substring(8)))).collect(Collectors.toList());
        }
        return filterProjectList;
    }

    private String getObjStr(String attribute){
        String obj = "";
        if(Objects.nonNull(attribute)){
            obj = attribute;
        }
        return obj;
    }
}
