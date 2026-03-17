package com.joao.AgendaDeAtividades.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.joao.AgendaDeAtividades.Service.ActivityService;
import com.joao.AgendaDeAtividades.data.dto.ActivityDTO;
import com.joao.AgendaDeAtividades.model.Activity;
import com.joao.AgendaDeAtividades.model.ActivityStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import tools.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@WebMvcTest(ActivityController.class)
class ActivityControllerTest {

    @Autowired
    private MockMvc mockmvc;

    @MockitoBean
    private ActivityService service;

    private String asJsonString(Object obj ) throws Exception{
        return new ObjectMapper().writeValueAsString(obj);
    }

    @Test
    @DisplayName("Should created activity successfully")
    void createActivity() throws Exception {
        ActivityDTO dto =
                new ActivityDTO("teste1",
                        "teste do metodo de criação de atividades",
                        1L,
                        ActivityStatus.TODO);


        Mockito.when(service.createActivity(Mockito.any()))
                .thenReturn(dto);

        mockmvc.perform(post("/activities").contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(dto))).andExpect(status().isCreated());

        verify(service).createActivity(Mockito.any());

    }

    @Test
    @DisplayName("Should get activity by title")
    void getActivity() throws Exception{
        ActivityDTO dto =
                new ActivityDTO("teste1",
                        "teste do metodo de criação de atividades",
                        1L,
                        ActivityStatus.TODO);


        Mockito.when(service.searchByTitle(Mockito.any()))
                .thenReturn(dto);

        mockmvc.perform(get("/activities/{title}", "teste1")).andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("teste1"));

        verify(service).searchByTitle(Mockito.any());
    }

    @Test
    void getAllActivities() throws Exception {
        int pageSize = 2;
        int pageNumber = 0;

        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        ActivityDTO dto = new ActivityDTO("teste1",
                        "1º entidade " +
                                "do teste do metodo de busca de todas as atividades usando Pageable",
                        1L,
                        ActivityStatus.TODO);

        ActivityDTO dto2 = new ActivityDTO("teste2",
                "1º entidade do teste " +
                        "do metodo de busca de todas as atividades usando Pageable",
                1L,
                ActivityStatus.TODO);


        List<ActivityDTO> dtosList = Arrays.asList(dto, dto2);
        Page<ActivityDTO> mockedPag= new PageImpl<>(dtosList, pageable, dtosList.size());

        Mockito.when(service.searchALLActivity(Mockito.any(Pageable.class)))
                .thenReturn(mockedPag);

        mockmvc.perform(get("/activities").
                        param("page" , "0").
                        param("size", "2")).
                andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].title").value("teste1")).
                andExpect(jsonPath("$.content[1].title").value("teste2")).
                andExpect(jsonPath("$.totalElements").value(2));

        verify(service).searchALLActivity(Mockito.any(Pageable.class));
    }

    @Test
    void deleteActivityByID() {
    }

    @Test
    void patchById() {
    }
}