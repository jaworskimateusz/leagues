package pl.jaworskimateuszm.myleagues.model;

import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.beans.Transient;

public class Season {
    private long seasonId;
    private long roundId;
    private long leagueId;

    @NotNull
    @NumberFormat
    private int number;

    @NotNull
    @Size(min = 1, message = "*")
    @Size(max = 100, message= "Maksymalna długość to 100 znaków.")
    private String description;

    public Season() {
    }

    public Season(long seasonId, long roundId, int number, String description) {
        this.seasonId = seasonId;
        this.roundId = roundId;
        this.number = number;
        this.description = description;
    }

    @Transient
    public long getLeagueId() {
        return leagueId;
    }

    @Transient
    public void setLeagueId(long leagueId) {
        this.leagueId = leagueId;
    }

    public long getRoundId() {
        return roundId;
    }

    public void setRoundId(long roundId) {
        this.roundId = roundId;
    }

    public long getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(long seasonId) {
        this.seasonId = seasonId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
