package lot.model;

public class PlateValidationRequest {
    private String plate;
    private String requester;

    public PlateValidationRequest() {
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getRequester() {
        return requester;
    }

    public void setRequester(String requester) {
        this.requester = requester;
    }

}
