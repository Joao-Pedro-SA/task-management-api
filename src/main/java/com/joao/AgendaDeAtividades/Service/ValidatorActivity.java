package com.joao.AgendaDeAtividades.Service;

import com.joao.AgendaDeAtividades.exceptions.ActivityNotFoundException;
import com.joao.AgendaDeAtividades.model.Activity;
import com.joao.AgendaDeAtividades.repository.ActivityRepository;
import org.springframework.stereotype.Service;

@Service
public class ValidatorActivity {

    private final ActivityRepository repository;

    public ValidatorActivity(ActivityRepository repository) {
        this.repository = repository;
    }


    //Método para verificação de entity no banco de dados
    public  Activity activityExists(Long id){
        return repository.findById(id).orElseThrow(() -> new ActivityNotFoundException("Atividade referente ao id ("+id+") não encontrada."));
    }

    public  Activity titleActivityExists(String title){
        return repository.findByTitle(title).orElseThrow(() -> new ActivityNotFoundException("Atividade referente ao titulo ("+title+") não encontrada."));
    }




}
