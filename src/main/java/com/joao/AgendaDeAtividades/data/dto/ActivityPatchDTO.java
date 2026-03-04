package com.joao.AgendaDeAtividades.data.dto;

import com.joao.AgendaDeAtividades.model.ActivityStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActivityPatchDTO {

    private String  title;
    private String description;
    private ActivityStatus status;
        
}
