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

    @NotNull
//    @DateTimeFormat(pattern = "dd/MM/yyyy h:mm")
    @DateTimeFormat(pattern = "yyyy-MM-dd h:mm")
    private Date gameDate;

    @NotEmpty
    @Size(max = 45, message= "Maksymalna długość to 45 znaków.")
    private  String place;

    public Game() {
    }

    public Game(int gameId, int roundId, Date gameDate, String place, int firstPlayerScore, int secondPlayerScore) {
        this.gameId = gameId;
        this.roundId = roundId;
        this.gameDate = gameDate;
        this.place = place;
        this.firstPlayerScore = firstPlayerScore;
        this.secondPlayerScore = secondPlayerScore;
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
