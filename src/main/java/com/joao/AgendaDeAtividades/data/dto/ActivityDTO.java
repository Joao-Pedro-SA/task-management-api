package com.joao.AgendaDeAtividades.data.dto;

import com.joao.AgendaDeAtividades.model.ActivityStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ActivityDTO {

    @NotBlank
    @Size(max = 100)
    private String  title; 
    @Size(max = 150) 
    private String description;
    private Long id;
    private ActivityStatus status;
    
    public ActivityStatus getStatus() {
        return status;
    }
    public void setStatus(ActivityStatus status) {
        this.status = status;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String descricao) {
        this.description = descricao;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
}
