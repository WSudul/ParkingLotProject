package lot.model;

import java.util.ArrayList;
import java.util.List;

public class PlateValidationResponse {
    private String plate;
    private boolean validation;
    private List<String> details = new ArrayList<String>();

    public PlateValidationResponse() {
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public boolean isValidation() {
        return validation;
    }

    public void setValidation(boolean validation) {
        this.validation = validation;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }
}
