package iesrafaelalberti.helloworld.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import iesrafaelalberti.helloworld.dto.CreateTaskDTO;
import iesrafaelalberti.helloworld.entities.Task;
import iesrafaelalberti.helloworld.repositories.TaskRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskRepository repository;

    public TaskController(TaskRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Task> list(@RequestParam(required = false) Boolean done) {
        if (done == null) {
            return repository.findAll();
        } else {
            return repository.findByDone(done);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> tareaPorId(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Task> create(@RequestBody @Valid CreateTaskDTO dto) {
        Task newTask = Task.builder()
                .title(dto.title())
                .done(false)
                .build();

        Task saved = repository.save(newTask);

        return ResponseEntity
                .created(URI.create("/tasks/" + saved.getId()))
                .body(saved);
    }

    @PatchMapping("/{id}/toggle")
    public ResponseEntity<Task> toggle(@PathVariable Long id) {
        Task task = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));

        task.setDone(!task.isDone());
        Task updated = repository.save(task);

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Task not found");
        }

        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}