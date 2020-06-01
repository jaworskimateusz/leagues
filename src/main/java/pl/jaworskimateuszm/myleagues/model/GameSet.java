package pl.jaworskimateuszm.myleagues.model;

public class GameSet {
    private long setId;
    private int setNumber;
    private int firstPlayerScore;
    private int secondPlayerScore;

    public GameSet() {
    }

    public GameSet(long setId, int setNumber, int firstPlayerScore, int secondPlayerScore) {
        this.setId = setId;
        this.setNumber = setNumber;
        this.firstPlayerScore = firstPlayerScore;
        this.secondPlayerScore = secondPlayerScore;
    }

    public long getSetId() {
        return setId;
    }

    public void setSetId(long setId) {
        this.setId = setId;
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
