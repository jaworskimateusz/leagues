package pl.jaworskimateuszm.myleagues.model;

public class GameSet {
    private long gameSetId;
    private int setNumber;
    private int firstPlayerScore;
    private int secondPlayerScore;

    public GameSet() {
    }

    public GameSet(long gameSetId, int setNumber, int firstPlayerScore, int secondPlayerScore) {
        this.gameSetId = gameSetId;
        this.setNumber = setNumber;
        this.firstPlayerScore = firstPlayerScore;
        this.secondPlayerScore = secondPlayerScore;
    }

    public long getGameSetId() {
        return gameSetId;
    }

    public void setGameSetId(long gameSetId) {
        this.gameSetId = gameSetId;
    }

    public int getSetNumber() {
        return setNumber;
    }

    public void setSetNumber(int setNumber) {
        this.setNumber = setNumber;
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
