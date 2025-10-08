package iesrafaelalberti.helloworld.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Task {
    
    @Id
    int id;

    String name;
}
