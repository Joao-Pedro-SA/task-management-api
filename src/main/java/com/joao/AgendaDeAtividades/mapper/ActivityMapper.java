package com.joao.AgendaDeAtividades.mapper;

import com.joao.AgendaDeAtividades.data.dto.ActivityDTO;
import com.joao.AgendaDeAtividades.data.dto.ActivityPatchDTO;
import com.joao.AgendaDeAtividades.model.Activity;

public class ActivityMapper {


   /*  public static ActivityDTO ToDTO(Activity atividade){
       return ActivityDTO.builder().id(atividade.getId())
       .title(atividade.getTitle())
       .status(atividade.getStatus())
       .descricao(atividade.getDescricao()).build();    
    }
    
    public static ActivityPatchDTO PatchToDTO(Activity atividade){
       return ActivityPatchDTO.builder()
       .title(atividade.getTitle())
       .status(atividade.getStatus())
       .descricao(atividade.getDescricao()).build();    
    }
    

    public static Activity ToActivity(ActivityDTO dto){
        Activity a = new Activity();

        a.setDescricao(dto.getDescricao());
        a.setTitle(dto.getTitle());
        if (dto.getStatus() != null) {
            a.setStatus(dto.getStatus());
        }
        
        return (a);
    }

      public static Activity PatchToActivity(ActivityPatchDTO dto){
        Activity a = new Activity();

        a.setDescricao(dto.getDescricao());
        a.setTitle(dto.getTitle());
        if (dto.getStatus() != null) {
            a.setStatus(dto.getStatus());
        }
        
        return (a);
    }
*/

    public static ActivityDTO toDTO(Activity activity) {
        return ActivityDTO.builder()
            .id(activity.getId())
            .title(activity.getTitle())
            .description(activity.getDescription())
            .status(activity.getStatus())
            .build();
    }

    // CREATE: DTO -> Entity nova
    public static Activity toEntity(ActivityDTO dto) {
        Activity a = new Activity();
        a.setTitle(dto.getTitle());
        a.setDescription(dto.getDescription());
        if (dto.getStatus() != null) a.setStatus(dto.getStatus());
        return a;
    }

    public static ActivityPatchDTO toPatchDTO(Activity activity){
         return ActivityPatchDTO.builder()
            .title(activity.getTitle())
            .description(activity.getDescription())
            .status(activity.getStatus())
            .build();

    }

    // PATCH: aplica em uma entity existente
    public static void applyPatch(ActivityPatchDTO dto, Activity entity) {
        if (dto.getTitle() != null) entity.setTitle(dto.getTitle());
        if (dto.getDescription() != null) entity.setDescription(dto.getDescription());
        if (dto.getStatus() != null) entity.setStatus(dto.getStatus());
    }
}




