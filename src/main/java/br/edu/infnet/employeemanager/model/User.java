package br.edu.infnet.employeemanager.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "usuarios")
public class User {
    @Id
    private String id;
    private String name;
    private String password;
    private String role;
}
