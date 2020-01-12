package employees.exceptions;

import employees.models.Titles;

public class CEOAlreadyExists extends Throwable {
    public CEOAlreadyExists(Titles title) {
        super("There cannot be more than one :"+title);
    }

    public CEOAlreadyExists() {

    }
}
