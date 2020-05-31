package pl.jaworskimateuszm.myleagues.model;

public class Player {
    private long playerId;
    private long feeId;
    private long disciplineId;
    private String name;
    private String surname;

    public Player(long playerId, long feeId, long disciplineId, String name, String surname) {
        this.playerId = playerId;
        this.feeId = feeId;
        this.disciplineId = disciplineId;
        this.name = name;
        this.surname = surname;
    }

    public Player() {

    }

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    public long getFeeId() {
        return feeId;
    }

    public void setFeeId(long feeId) {
        this.feeId = feeId;
    }

    public long getDisciplineId() {
        return disciplineId;
    }

    public void setDisciplineId(long disciplineId) {
        this.disciplineId = disciplineId;
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
