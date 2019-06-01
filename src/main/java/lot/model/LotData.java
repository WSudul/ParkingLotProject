package lot.model;

import javax.validation.constraints.NotBlank;

public class LotData {

    @NotBlank
    private String name;
    @NotBlank
    private String location;
    private Integer capacity;
    private Integer occupied;

    public LotData(@NotBlank String name, @NotBlank String location, Integer capacity, Integer occupied) {
        this.name = name;
        this.location = location;
        this.occupied = occupied;
        this.capacity = capacity;
    }

    public LotData() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer cpacity) {
        this.capacity = cpacity;
    }

    public Integer getOccupied() {
        return occupied;
    }

    public void setOccupied(Integer occupied) {
        this.capacity = occupied;
    }
}
