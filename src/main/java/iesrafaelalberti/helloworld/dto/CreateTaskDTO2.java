package iesrafaelalberti.helloworld.dto;

import jakarta.validation.constraints.NotBlank;

public class CreateTaskDTO2 {
    @NotBlank(message = "El título no puede estar vacío")
    private String title;

    public CreateTaskDTO2(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
}
