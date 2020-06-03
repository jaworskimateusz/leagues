package pl.jaworskimateuszm.myleagues.model;

import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Round {
    private long roundId;
    private long seasonId;

    @NotNull
    @NumberFormat
    private int number;

    @NotEmpty
    @Size(max = 100, message= "Maksymalna długość to 100 znaków.")
    private String discipline;

    public Round(long roundId, long seasonId, int number, String discipline) {
        this.roundId = roundId;
        this.seasonId = seasonId;
        this.number = number;
        this.discipline = discipline;
    }

    public Round() {

    }

    public long getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(long seasonId) {
        this.seasonId = seasonId;
    }

    public long getRoundId() {
        return roundId;
    }

    public void setRoundId(long roundId) {
        this.roundId = roundId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }
}
