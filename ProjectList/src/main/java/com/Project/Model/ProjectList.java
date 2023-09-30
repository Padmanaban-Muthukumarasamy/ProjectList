package com.Project.Model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "Projectlist")
public class ProjectList {

    @Field("Project")
    private Project project;

    @Field("Technical_Skillset")
    private TechnicalSkillSet technicalSkillSet;

    @Field("Other_Information")
    private OtherInformation otherInformation;
}
