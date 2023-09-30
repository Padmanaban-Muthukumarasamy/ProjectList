package com.Project.Dao;

import com.Project.Model.ProjectList;
import com.Project.Model.Projects;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectListRepository extends MongoRepository<ProjectList, String> {

    List<ProjectList> findByTechnicalSkillSetTechnicalSkillSetBackendIn(List<String> backend);

    List<ProjectList> findByTechnicalSkillSetTechnicalSkillSetFrontendIn(List<String> frontEnd);

    List<ProjectList> findByTechnicalSkillSetTechnicalSkillDatabasesIn(List<String> databases);

    List<ProjectList> findByTechnicalSkillSetTechnicalSkillSetBackendInAndTechnicalSkillSetTechnicalSkillDatabasesIn(List<String> backend, List<String> databases);

    List<ProjectList> findByTechnicalSkillSetTechnicalSkillSetFrontendInAndTechnicalSkillSetTechnicalSkillDatabasesIn(List<String> frontEnd, List<String> databases);

    List<ProjectList> findByTechnicalSkillSetTechnicalSkillSetBackendInAndTechnicalSkillSetTechnicalSkillSetFrontendIn(List<String> backend, List<String> frontEnd);

    List<ProjectList> findByTechnicalSkillSetTechnicalSkillSetBackendInAndTechnicalSkillSetTechnicalSkillSetFrontendInAndTechnicalSkillSetTechnicalSkillDatabasesIn(List<String> backend, List<String> frontEnd, List<String> databases);
}
