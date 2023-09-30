package com.Project.Model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
//@Document(collection = "Project")
public class Project {

    @Field("Title")
    private String projectTitle;

    @Field("Technologies")
    private String projectTechnologies;
}
