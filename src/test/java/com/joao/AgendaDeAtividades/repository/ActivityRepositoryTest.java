package com.joao.AgendaDeAtividades.repository;

import com.joao.AgendaDeAtividades.data.dto.ActivityDTO;
import com.joao.AgendaDeAtividades.model.Activity;
import com.joao.AgendaDeAtividades.model.ActivityStatus;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class ActivityRepositoryTest {

    @Autowired
    ActivityRepository repository;

    @Autowired
    EntityManager entity;



    @Test
    @DisplayName("should get activity successfully from DB")
    void findByTitlecase1() {
     ActivityDTO dto = new ActivityDTO("Teste1","primeiro teste de JUnit", 1L, ActivityStatus.TODO);
        this.createActivity(dto);

        Optional<Activity> result = this.repository.findByTitle(dto.getTitle());

       assertThat(result.isPresent()).isTrue();
    }

    @Test
    @DisplayName("should not get activity from DB when user not exists")
    void findByTitlecase2() {
        String title = "koakdoakdak";
        Optional<Activity> result = this.repository.findByTitle(title);

        assertThat(result.isEmpty()).isTrue();
    }

    @Test
    void findAllByTitle() {
    }

    @Test
    void deleteByTitle() {
    }


    private Activity createActivity(ActivityDTO data){
        Activity newActivity = new Activity(data);
        this.entity.persist(newActivity);
        return newActivity;

    }
}