package lot.model;

import java.util.List;

public class PlateValidationRequest {
    private List<String> plates;
    private String requester;

    public PlateValidationRequest() {
    }

    public List<String> getPlates() {
        return plates;
    }

    public void setPlates(List<String> plates) {
        this.plates = plates;
    }

    public String getRequester() {
        return requester;
    }

    public void setRequester(String requester) {
        this.requester = requester;
    }

}
