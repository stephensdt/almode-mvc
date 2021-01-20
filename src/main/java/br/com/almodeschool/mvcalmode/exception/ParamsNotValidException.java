package br.com.almodeschool.mvcalmode.exception;

public class ParamsNotValidException extends RuntimeException{
    public ParamsNotValidException(String msg) {
        super(msg);
    }
}
