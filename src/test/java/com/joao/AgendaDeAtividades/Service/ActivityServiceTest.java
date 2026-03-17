package com.joao.AgendaDeAtividades.Service;

import com.joao.AgendaDeAtividades.data.dto.ActivityDTO;
import com.joao.AgendaDeAtividades.data.dto.ActivityPatchDTO;
import com.joao.AgendaDeAtividades.exceptions.ActivityNotFoundException;
import com.joao.AgendaDeAtividades.mapper.ActivityMapper;
import com.joao.AgendaDeAtividades.model.Activity;
import com.joao.AgendaDeAtividades.model.ActivityStatus;
import com.joao.AgendaDeAtividades.repository.ActivityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class ActivityServiceTest {

    @Mock
    ActivityRepository repository;

    @Mock
    ValidatorActivity validator;

    @InjectMocks
    ActivityService service;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Should create an activity when parameters are valid")
    void createActivity() {

        ActivityDTO dto =
                new ActivityDTO("teste1",
                        "teste do metodo de criação de atividades",
                        1L,
                        ActivityStatus.TODO);

        Activity activity = new Activity(dto);


        Mockito.when(repository.save(Mockito.any()))
                .thenReturn(activity);

        ActivityDTO result = service.createActivity(dto);


        assertThat(result).isNotNull();


        verify(repository).save(Mockito.any());
    }


    @Test
    @DisplayName("Should delete activity by title")
    void deleteByTitlecase1() {
        String title = "teste 2";

        service.deleteByTitle(title);

        verify(repository).deleteByTitle(title);

    }

    @Test
    @DisplayName("should get acitivity by title")
    void searchByTitlecase1() {
        String title= "teste1";
        Activity activity =
                new Activity(new ActivityDTO("teste1",
                        "teste do metodo de criação de atividades",
                        1L,
                        ActivityStatus.TODO));


        Mockito.when(repository.findByTitle(title))
                .thenReturn(Optional.of(activity));
        Mockito.when(validator.titleActivityExists(title))
                .thenReturn(activity);

        ActivityDTO result = service.searchByTitle(title);

        assertThat(result).isNotNull();
        assertThat(result.getTitle()).isEqualTo(activity.getTitle());

        verify(repository).findByTitle("teste1");

    }

    @Test
    @DisplayName("Should patch update by title ")
    void patchByTitlecase1() {
        String title= "teste1";
        Activity activity =
                new Activity(new ActivityDTO("teste1",
                        "teste do metodo de criação de atividades",
                        1L,
                        ActivityStatus.TODO));

        ActivityPatchDTO dto = ActivityMapper.toPatchDTO(activity);

        Mockito.when(validator.titleActivityExists(title))
                .thenReturn(activity);

        ActivityDTO result = service.patchByTitle(title, dto);

        assertThat(result).isNotNull();
        assertThat(result.getTitle()).isEqualTo(activity.getTitle());

        verify(validator).titleActivityExists(title);

    }

    @Test
    @DisplayName("Should not patch update when title not exists ")
    void patchByTitlecase2() {
        String title= "teste2";
        ActivityPatchDTO dto = new ActivityPatchDTO();

        Mockito.when(validator.titleActivityExists(title))
                .thenThrow(new ActivityNotFoundException ("Atividade não encontrada no sistema."));

        assertThrows( ActivityNotFoundException.class , () -> service.patchByTitle(title, dto));
        verify(validator).titleActivityExists(title);

    }


}