package git.MatheusOliveira04.services.exception;

public class ObjectNotFoundException extends RuntimeException {

    public ObjectNotFoundException(String message){
        super(message);
    }

}
