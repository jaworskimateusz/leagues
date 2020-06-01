package pl.jaworskimateuszm.myleagues.model;

import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class League {
    private long leagueId;
    private long objectId;
    private long seasonId;

    @NotEmpty
    @Size(max = 45, message= "Maksymalna długość to 45 znaków.")
    private String level;

    @NotEmpty
    @Size(max = 100, message= "Maksymalna długość to 100 znaków.")
    private String description;

    public League() {
    }

    public League(long leagueId, long objectId, long seasonId, String level, String description) {
        this.leagueId = leagueId;
        this.objectId = objectId;
        this.seasonId = seasonId;
        this.level = level;
        this.description = description;
    }

    public long getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(long leagueId) {
        this.leagueId = leagueId;
    }

    public long getObjectId() {
        return objectId;
    }

    public void setObjectId(long objectId) {
        this.objectId = objectId;
    }

    public long getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(long seasonId) {
        this.seasonId = seasonId;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
