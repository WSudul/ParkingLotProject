package lot.model;

import java.time.Instant;

public class LotStatus {

    private String location;
    private Long capacity;
    private Long occupied;
    private Instant lastUpdate;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getCapacity() {
        return capacity;
    }

    public void setCapacity(Long capacity) {
        this.capacity = capacity;
    }

    public Long getOccupied() {
        return occupied;
    }

    public void setOccupied(Long occupied) {
        this.occupied = occupied;
    }

    public Instant getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Instant lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
