package com.joao.AgendaDeAtividades.exceptions;

public class ActivityNotFoundException extends RuntimeException{
    public ActivityNotFoundException(String message){
        super(message);
    }
}
