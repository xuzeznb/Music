package com.example.demo.exception;

public class NotFindArticleException extends RuntimeException{
    public NotFindArticleException(){
        super("找不到当前文章");
    }
    public NotFindArticleException(String message){
        super(message);
    }
}
