package org.example.superheroe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UsernameNotFoundException extends Exception{
    public UsernameNotFoundException() {
    }

   public  UsernameNotFoundException(String message){
        super(message);
    }
    public UsernameNotFoundException(String message,Throwable cause){
        super(message);

    }
}
