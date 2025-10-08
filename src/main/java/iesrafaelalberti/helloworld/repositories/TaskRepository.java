package iesrafaelalberti.helloworld.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import iesrafaelalberti.helloworld.entities.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByDone(boolean done);
}
