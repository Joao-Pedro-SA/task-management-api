
package com.joao.AgendaDeAtividades.controller;

import org.springframework.http.HttpStatus;
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
import com.joao.AgendaDeAtividades.Service.ActivityService;
import com.joao.AgendaDeAtividades.data.dto.ActivityDTO;
import com.joao.AgendaDeAtividades.data.dto.ActivityPatchDTO;
import com.joao.AgendaDeAtividades.mapper.ActivityMapper;
import com.joao.AgendaDeAtividades.model.Activity;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/activities")
public class ActivityController {
    private ActivityService activity;

    public ActivityController(ActivityService activity) {
    this.activity = activity;
    }


    /*EndPoint para cria uma atividade no Formato DTO e retorna status 201 junto com o body.*/
    @PostMapping
    public ResponseEntity<ActivityDTO> createActivity(@Valid @RequestBody ActivityDTO dto){
        ActivityDTO created = activity.createActivity(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);

    }

    //EndPoint para buscar entidade por titulo
    @GetMapping("/by-title")
    public ResponseEntity<ActivityDTO> getActivity(@RequestParam String title){
        Activity entity = activity.searchByTitle(title);
        return ResponseEntity.ok(ActivityMapper.toDTO(entity));
    }

    //Endpoint para deletar entidade por id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActivityByID(@PathVariable Long id){
        activity.deleteById(id);

        return ResponseEntity.noContent().build();
    }


    //Endpoint para atualização parcial da entidade.
    @PatchMapping("/{id}")
    public ResponseEntity<ActivityDTO> patchById(@PathVariable Long id, @RequestBody ActivityPatchDTO dto){
        Activity entity = activity.patchById(id, dto);
        return ResponseEntity.ok(ActivityMapper.toDTO(entity));

    }

}
