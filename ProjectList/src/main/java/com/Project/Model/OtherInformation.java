package com.Project.Model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
//@Document(collection = "Other_Information")
public class OtherInformation {

    @Field("Availability")
    private String otherInformationAvailability;
}
