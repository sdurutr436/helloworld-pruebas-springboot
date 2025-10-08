package iesrafaelalberti.helloworld.services;

import java.util.List;

import org.springframework.stereotype.Service;

import iesrafaelalberti.helloworld.dto.CreateTaskDTO;
import iesrafaelalberti.helloworld.entities.Task;
import iesrafaelalberti.helloworld.repositories.TaskRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class TaskService {
    private final TaskRepository repo;

    public TaskService(TaskRepository repo) {
        this.repo = repo;
    }

    public List<Task> list(Boolean done) {
        return (done == null) ? repo.findAll() : repo.findByDone(done);
    }

    public Task create(CreateTaskDTO dto) {
        Task task = Task.builder()
                        .title(dto.title())
                        .done(false)
                        .build();
        return repo.save(task);
    }

    public Task toggle(Long id) {
        Task t = repo.findById(id)
                     .orElseThrow(() -> new IllegalArgumentException("Task not found"));
        t.setDone(!t.isDone());
        return repo.save(t);
    }

    public void delete(Long id) {
        if (!repo.existsById(id))
            throw new IllegalArgumentException("Task not found");
        repo.deleteById(id);
    }
}
