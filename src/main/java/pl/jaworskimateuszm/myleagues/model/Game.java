package pl.jaworskimateuszm.myleagues.model;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class Game {

    private long gameId;
    private long setId;
    private long firstPlayerId;
    private long secondPlayerId;

    @NumberFormat
    private int firstPlayerScore;

    @NumberFormat
    private int secondPlayerScore;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date gameDate;

    @NotEmpty
    @Size(max = 45, message= "Maksymalna długość to 45 znaków.")
    private  String place;

    public Game() {
    }

    public Game(long gameId, long setId, Date gameDate, String place, long firstPlayerId, long secondPlayerId, int firstPlayerScore, int secondPlayerScore) {
        this.gameId = gameId;
        this.setId = setId;
        this.gameDate = gameDate;
        this.place = place;
        this.firstPlayerId = firstPlayerId;
        this.secondPlayerId = secondPlayerId;
        this.firstPlayerScore = firstPlayerScore;
        this.secondPlayerScore = secondPlayerScore;
    }

    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public long getSetId() {
        return setId;
    }

    public void setSetId(long setId) {
        this.setId = setId;
    }

    public Date getGameDate() {
        return gameDate;
    }

    public void setGameDate(Date gameDate) {
        this.gameDate = gameDate;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public long getFirstPlayerId() {
        return firstPlayerId;
    }

    public void setFirstPlayerId(long firstPlayerId) {
        this.firstPlayerId = firstPlayerId;
    }

    public long getSecondPlayerId() {
        return secondPlayerId;
    }

    public void setSecondPlayerId(long secondPlayerId) {
        this.secondPlayerId = secondPlayerId;
    }

    public int getFirstPlayerScore() {
        return firstPlayerScore;
    }

    public void setFirstPlayerScore(int firstPlayerScore) {
        this.firstPlayerScore = firstPlayerScore;
    }

    public int getSecondPlayerScore() {
        return secondPlayerScore;
    }

    public void setSecondPlayerScore(int secondPlayerScore) {
        this.secondPlayerScore = secondPlayerScore;
    }
}
