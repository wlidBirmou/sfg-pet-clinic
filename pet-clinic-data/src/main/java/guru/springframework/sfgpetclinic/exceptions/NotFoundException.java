package guru.springframework.sfgpetclinic.exceptions;

//@ResponseStatus
public class NotFoundException extends RuntimeException{

    public NotFoundException(){
        super();
    }

    public NotFoundException(String message, Throwable cause){
        super(message,cause);
    }
}
