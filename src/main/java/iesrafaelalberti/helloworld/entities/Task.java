package iesrafaelalberti.helloworld.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Task {
    
    @Id
    @GeneratedValue (strategy = GenerationType.TABLE)
    // @SequenceGenerator(name="task_seq", sequenceName="task_seq", allocationSize=1)
    int id;

    String name;
    String description;

    int priority;
    int status;

    Date dueDate;
    
}
