package pl.jaworskimateuszm.myleagues.model;

import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class Place {
    private long placeId;

    @NotNull
    private BigDecimal cost;

    @NotNull
    @NumberFormat
    private int numberOfHours;
    private String name;

    private int confirmed;

    private Boolean confirmedFlag = false;

    public Place() {

    }

    public Place(long placeId, BigDecimal cost, int numberOfHours, String name, Boolean confirmedFlag) {
        this.placeId = placeId;
        this.cost = cost;
        this.numberOfHours = numberOfHours;
        this.name = name;
        this.confirmedFlag = confirmedFlag;
    }

    public int getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(int confirmed) {
        this.confirmed = confirmed;
    }

    public Boolean getConfirmedFlag() {
        return confirmedFlag;
    }

    public void setConfirmedFlag(Boolean confirmedFlag) {
        this.confirmedFlag = confirmedFlag;
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

}
