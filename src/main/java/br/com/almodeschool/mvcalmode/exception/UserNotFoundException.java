package br.com.almodeschool.mvcalmode.exception;

public class UserNotFoundException extends  RuntimeException{
    public UserNotFoundException() {
        super ("Usuario não encontrado");
    }
}
