package com.joao.AgendaDeAtividades.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joao.AgendaDeAtividades.model.Activity;

//Interface para processamento do repositorio 
public interface ActivityRepository extends JpaRepository<Activity, Long> {

    //métodos abstratos para procura de atividades por nome 
    Optional<Activity> findByTitle(String title);
    List<Activity> findAllByTitle(String title);

    long deleteByTitle(String title);
}
