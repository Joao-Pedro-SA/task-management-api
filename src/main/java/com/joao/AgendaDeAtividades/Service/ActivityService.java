package com.joao.AgendaDeAtividades.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.joao.AgendaDeAtividades.data.dto.ActivityDTO;
import com.joao.AgendaDeAtividades.data.dto.ActivityPatchDTO;
import com.joao.AgendaDeAtividades.exceptions.ActivityNotFoundException;
import com.joao.AgendaDeAtividades.mapper.ActivityMapper;
import com.joao.AgendaDeAtividades.model.Activity;
import com.joao.AgendaDeAtividades.repository.ActivityRepository;

import jakarta.transaction.Transactional;

@Service
public class ActivityService {

    private final ActivityRepository repository;
    
    public ActivityService(ActivityRepository repository){
        this.repository = repository;
    }

    /*O objeto activity "dados" serve para criar a entidade que sera salva no banco de dados.
    O objeto activity "salvo" é a entidade que foi registrada no banco de dados.
    O return pega os dados da entiddade registrada e passa para o DTO. O DTO entrega ao return os dados que devem ser mostrados ao cliente.
    */

    

    //Método para criação de atividades usando a classe ActivityDTO para controle de dados.
    public ActivityDTO createActivity(ActivityDTO dto){ 
        dto.setId(null);   
        Activity dados = ActivityMapper.toEntity(dto);
        Activity salvo = repository.save(dados);

        return ActivityMapper.toDTO(salvo);
    }

    @Transactional
    public void deleteById(Long id){
        if(!repository.existsById(id)){
            throw new ActivityNotFoundException( "Id ("+id+") não encontrado");
        }

        repository.deleteById(id);
  
    }


    /*Método para deletar atividades usando o parametro "title". 
    A anotação "Transactional" serve para garantir que a ação de deletar seja cancelada caso ocorra algum erro durante a execução*/
    @Transactional
    public void deleteByTitle(String title){
        long deleted = repository.deleteByTitle(title);
      
        if (deleted == 0) {
            throw new ActivityNotFoundException("Atividade referente com o titulo de ("+title+") não encontrada.");
        }
    }

    //Método para procurar uma atividade por titulo
    public ActivityDTO searchByTitle(String title){

        Activity activity = titleActivityExists(title);

        return ActivityMapper.toDTO(activity);
    }  


    //método para procurar todas as atividades
     public Page <ActivityDTO> searchALLActivity(Pageable pageable){
        return repository.findAll(pageable)
        .map(ActivityMapper::toDTO);
        
    }   

    //método para fazer atualização parcial de uma atividade. Usa o id para encontrar a atividade a ser atualizada
    @Transactional
    public Activity patchById(Long id, ActivityPatchDTO activity){
        
        Activity atividadeAtual = activityExists(id);

        ActivityMapper.applyPatch(activity, atividadeAtual);
        return repository.save(atividadeAtual);

    }

    //método para fazer atualização parcial de uma atividade usando o Titulo para encontrar a atividade.
    @Transactional
    public ActivityDTO patchByTitle(String title, ActivityPatchDTO dto) {
        Activity atual = titleActivityExists(title);

        ActivityMapper.applyPatch(dto, atual);

        Activity save = repository.save(atual);

        return ActivityMapper.toDTO(save);
}
     //Método para verificação de entity no banco de dados
    private Activity activityExists(Long id){
        return repository.findById(id).orElseThrow(() -> new ActivityNotFoundException("Atividade referente ao id ("+id+") não encontrada."));
    }

    private Activity titleActivityExists(String title){
        return repository.findByTitle(title).orElseThrow(() -> new ActivityNotFoundException("Atividade referente ao titulo ("+title+") não encontrada."));
    }
   

}
