package pl.jaworskimateuszm.myleagues.model;

public class GameSet {
    private int gameSetId;
    private int number;
    private int firstPlayerScore;
    private int secondPlayerScore;
    private int gameId;

    public GameSet() {
    }

    public int getGameSetId() {
        return gameSetId;
    }

    public void setGameSetId(int gameSetId) {
        this.gameSetId = gameSetId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }
}
