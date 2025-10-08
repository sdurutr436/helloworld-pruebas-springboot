package iesrafaelalberti.helloworld.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor
@Builder
public class Task {
    
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    // @SequenceGenerator(name="task_seq", sequenceName="task_seq", allocationSize=1)
    int id;

    String name;
    String description;

    int priority;
    int status;

    Date dueDate;

}
