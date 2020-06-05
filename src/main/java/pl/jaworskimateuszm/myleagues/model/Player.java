package pl.jaworskimateuszm.myleagues.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.beans.Transient;

public class Player {
    private int playerId;
    @NotEmpty
    @Size(max = 45, message= "Maksymalna długość to 45 znaków.")
    private String name;

    @NotEmpty
    @Size(max = 45, message= "Maksymalna długość to 45 znaków.")
    private String surname;

    @Size(max = 11, message= "Maksymalna długość to 45 znaków.")
    private String pesel;

    @NotEmpty
    private int[] leagueIds;

    public Player() {
    }

    public int[] getLeagueIds() {
        return leagueIds;
    }

    public void setLeagueIds(int[] leagueIds) {
        this.leagueIds = leagueIds;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
