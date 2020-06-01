package pl.jaworskimateuszm.myleagues.model;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

public class Place {
    private long placeId;

    @NotNull
    private BigDecimal cost;

    @NotNull
    @NumberFormat
    private int numberOfHours;

    @NotNull
    @Size(max = 45, message= "Maksymalna długość to 45 znaków.")
    private String name;

    @NotNull
    private int isConfirmed;

    public Place() {

    }

    public Place(long placeId, BigDecimal cost, int numberOfHours, String name, int isConfirmed) {
        this.placeId = placeId;
        this.cost = cost;
        this.numberOfHours = numberOfHours;
        this.name = name;
        this.isConfirmed = isConfirmed;
    }

    public long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(long placeId) {
        this.placeId = placeId;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public int getNumberOfHours() {
        return numberOfHours;
    }

    public void setNumberOfHours(int numberOfHours) {
        this.numberOfHours = numberOfHours;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIsConfirmed() {
        return isConfirmed;
    }

    public void setIsConfirmed(int isConfirmed) {
        this.isConfirmed = isConfirmed;
    }
}
