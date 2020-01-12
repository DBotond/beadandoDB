package employees.exceptions;

import employees.models.Titles;

import java.util.UUID;

public class IdNotFoundException extends Throwable{
    public IdNotFoundException() {
    }

    public IdNotFoundException(UUID id) {
        super("Id not found :"+id);
    }


}
