package pl.jaworskimateuszm.myleagues.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class League {
    private int leagueId;
    private int objectId;
    private int disciplineId;

    @NotEmpty
    @Size(max = 45, message= "Maksymalna długość to 45 znaków.")
    private String level;

    @NotEmpty
    @Size(max = 100, message= "Maksymalna długość to 100 znaków.")
    private String description;

    public League() {
    }

    public int getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(int leagueId) {
        this.leagueId = leagueId;
    }

    public int getObjectId() {
        return objectId;
    }

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }

    public int getDisciplineId() {
        return disciplineId;
    }

    public void setDisciplineId(int disciplineId) {
        this.disciplineId = disciplineId;
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
