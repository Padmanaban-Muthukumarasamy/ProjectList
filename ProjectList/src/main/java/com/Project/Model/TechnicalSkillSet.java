package com.Project.Model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
//@Document(collection = "Technical_Skillset")
public class TechnicalSkillSet {

    @Field("Frontend")
    private String technicalSkillSetFrontend;

    @Field("Backend")
    private String technicalSkillSetBackend;

    @Field("Databases")
    private String technicalSkillDatabases;

    @Field("Infrastructre")
    private String technicalSkillSetInfrastructure;
}
