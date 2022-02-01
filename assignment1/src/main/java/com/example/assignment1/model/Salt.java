package com.example.assignment1.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Date;

@Data
@Document(collection="controlledSalt")
public class Salt {

    @Id
    private String id;
    private String saltName;
    private Date controlStartDate;
    private Date controlEndDate;
    private boolean control;
    private String ailment;
    private String url;
    private String apiGroup;
    private boolean Deleted;

}
