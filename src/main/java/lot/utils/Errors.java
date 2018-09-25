package lot.utils;

import java.util.ArrayList;
import java.util.List;

public class Errors {

    private List<String> errors = new ArrayList<>();

    public void addError(String error) {
        errors.add(error);
    }

    public boolean hasErrors() {
        return errors.isEmpty();
    }

    public List<String> getErrors() {
        return errors;
    }

}
