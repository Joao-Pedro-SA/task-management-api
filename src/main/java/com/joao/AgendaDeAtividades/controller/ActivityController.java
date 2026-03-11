
package com.joao.AgendaDeAtividades.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.joao.AgendaDeAtividades.Service.ActivityService;
import com.joao.AgendaDeAtividades.data.dto.ActivityDTO;
import com.joao.AgendaDeAtividades.data.dto.ActivityPatchDTO;
import com.joao.AgendaDeAtividades.mapper.ActivityMapper;
import com.joao.AgendaDeAtividades.model.Activity;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/activities")
@Tag(name = "activity", description = "Endpoints for managing tasks")
public class ActivityController implements ActivityControllerDocs {
    private ActivityService activity;

    public ActivityController(ActivityService activity) {
    this.activity = activity;
    }


    /*EndPoint para cria uma atividade no Formato DTO e retorna status 201 junto com o body.*/
    @Override
    @PostMapping
    public ResponseEntity<ActivityDTO> createActivity(@Valid @RequestBody ActivityDTO dto){
        ActivityDTO created = activity.createActivity(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);

    }

    //EndPoint para buscar entidade por titulo
    @Override
    @GetMapping("/title/{title}")
 
    public ResponseEntity<ActivityDTO> getActivity(@PathVariable String title){
       return ResponseEntity.ok(activity.searchByTitle(title));
    }
    

    //EndPoint para requisição de todas as entidades 
    @Override
    @GetMapping
    
    public ResponseEntity<Page<ActivityDTO>> getAllActivities(Pageable pageable){
        Page <ActivityDTO> list = activity.searchALLActivity(pageable);
        return  ResponseEntity.ok(list);
    }

    //Endpoint para deletar entidade por id
    @Override
    @DeleteMapping("/{id}")
      
    public ResponseEntity<Void> deleteActivityByID(@PathVariable Long id){
        activity.deleteById(id);

        return ResponseEntity.noContent().build();
    }


    //Endpoint para atualização parcial da entidade.
    @Override
    @PatchMapping("/{id}")
    
    public ResponseEntity<ActivityDTO> patchById(@PathVariable Long id, @RequestBody ActivityPatchDTO dto){
        Activity entity = activity.patchById(id, dto);
        return ResponseEntity.ok(ActivityMapper.toDTO(entity));

    }

}
