package com.joao.AgendaDeAtividades.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;



import com.joao.AgendaDeAtividades.data.dto.ActivityDTO;
import com.joao.AgendaDeAtividades.data.dto.ActivityPatchDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;


public interface ActivityControllerDocs {

    /*EndPoint para cria uma atividade no Formato DTO e retorna status 201 junto com o body.*/
      
    @Operation(summary = "create a activity", 
    description = "Create a activity with title and description, and then return a activityDTO",
    responses = {
        @ApiResponse (
            description = "Succsess",
            responseCode = "201", 
            content = @Content(schema = @Schema(implementation = ActivityDTO.class))
        ),
        @ApiResponse (description = "no content", responseCode = "204", content = @Content),
        @ApiResponse (description = "Bad Request", responseCode = "400", content = @Content),
        @ApiResponse (description = "Unauthorized", responseCode = "401", content = @Content),
         @ApiResponse (description = "not found", responseCode = "404", content = @Content),
        @ApiResponse (description = "Internal Server Error", responseCode = "500", content = @Content)
    })
    ResponseEntity<ActivityDTO> createActivity(ActivityDTO dto);

    //EndPoint para buscar entidade por titulo
    @Operation(summary = "Find a activity by title", 
    description = "Returns a activity according to the title",
    responses = {
        @ApiResponse (
            description = "Succsess",
            responseCode = "200", 
            content = @Content(schema = @Schema(implementation = ActivityDTO.class))
        ),
        @ApiResponse (description = "no content", responseCode = "204", content = @Content),
        @ApiResponse (description = "Bad Request", responseCode = "400", content = @Content),
        @ApiResponse (description = "Unauthorized", responseCode = "401", content = @Content),
         @ApiResponse (description = "not found", responseCode = "404", content = @Content),
        @ApiResponse (description = "Internal Server Error", responseCode = "500", content = @Content)
    })
    ResponseEntity<ActivityDTO> getActivity(String title);

    //EndPoint para requisição de todas as entidades 
    @Operation(summary = "Find ALL activities", 
    description = "Returns a paginated list of all registered activities",
    responses = {
        @ApiResponse (description = "Succsess",
        responseCode = "200", 
        content = {@Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            array= @ArraySchema(schema = @Schema(implementation = ActivityDTO.class)))}),
        @ApiResponse (description = "no content", responseCode = "204", content = @Content),
        @ApiResponse (description = "Bad Request", responseCode = "400", content = @Content),
        @ApiResponse (description = "Unauthorized", responseCode = "401", content = @Content),
         @ApiResponse (description = "not found", responseCode = "404", content = @Content),
        @ApiResponse (description = "Internal Server Error", responseCode = "500", content = @Content)
    })
    ResponseEntity<Page<ActivityDTO>> getAllActivities(Pageable pageable);

    //Endpoint para deletar entidade por id
    @Operation(summary = "Delete an activity by Id", 
    description = "Delete a activity declarign a Id ",
    responses = {
        @ApiResponse (
            description = "Succsess",
            responseCode = "204", 
            content = @Content(schema = @Schema(implementation = ActivityDTO.class))
        ),
        @ApiResponse (description = "no content", responseCode = "204", content = @Content),
        @ApiResponse (description = "Bad Request", responseCode = "400", content = @Content),
        @ApiResponse (description = "Unauthorized", responseCode = "401", content = @Content),
         @ApiResponse (description = "not found", responseCode = "404", content = @Content),
        @ApiResponse (description = "Internal Server Error", responseCode = "500", content = @Content)
    })
    ResponseEntity<Void> deleteActivityByID(Long id);

    //Endpoint para atualização parcial da entidade.
    @Operation(summary = "Partial update declaring Id ", 
    description = "Partially updates an activity by id",
    responses = {
        @ApiResponse (
            description = "Succsess",
            responseCode = "200", 
            content = @Content(schema = @Schema(implementation = ActivityDTO.class))
        ),
        @ApiResponse (description = "no content", responseCode = "204", content = @Content),
        @ApiResponse (description = "Bad Request", responseCode = "400", content = @Content),
        @ApiResponse (description = "Unauthorized", responseCode = "401", content = @Content),
        @ApiResponse (description = "not found", responseCode = "404", content = @Content),
        @ApiResponse (description = "Internal Server Error", responseCode = "500", content = @Content)
    })
    ResponseEntity<ActivityDTO> patchById(Long id, ActivityPatchDTO dto);

}