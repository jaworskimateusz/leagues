package pl.jaworskimateuszm.myleagues.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;

public class Game {

    private int gameId;

    @Min(0)
    private int roundId;
    private int firstPlayerScore;
    private int secondPlayerScore;
    private int firstPlayerId;
    private int secondPlayerId;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd h:mm")
    private Date gameDate;

    @NotEmpty
    @Size(max = 45, message= "Maksymalna długość to 45 znaków.")
    private  String place;

    private Boolean withoutPlayers = false;

    public Game() {

    }

    public Game(int roundId, int firstPlayerScore, int secondPlayerScore, int firstPlayerId, int secondPlayerId, Date gameDate, String place) {
        this.roundId = roundId;
        this.firstPlayerScore = firstPlayerScore;
        this.secondPlayerScore = secondPlayerScore;
        this.firstPlayerId = firstPlayerId;
        this.secondPlayerId = secondPlayerId;
        this.gameDate = gameDate;
        this.place = place;
    }

    public Boolean getWithoutPlayers() {
        return withoutPlayers;
    }

    public void setWithoutPlayers(Boolean withoutPlayers) {
        this.withoutPlayers = withoutPlayers;
    }

    public int getFirstPlayerId() {
        return firstPlayerId;
    }

    public void setFirstPlayerId(int firstPlayerId) {
        this.firstPlayerId = firstPlayerId;
    }

    public int getSecondPlayerId() {
        return secondPlayerId;
    }

    public void setSecondPlayerId(int secondPlayerId) {
        this.secondPlayerId = secondPlayerId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getRoundId() {
        return roundId;
    }

    public void setRoundId(int roundId) {
        this.roundId = roundId;
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
}
