package pl.jaworskimateuszm.myleagues.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Fee {
    private int feeId;
    private int playedId;
    private int type;
    private int paid;

    @DateTimeFormat(pattern = "yyyy-MM-dd h:mm")
    private Date gameDate;

    public Fee(int feeId, int playedId, int type, int paid, Date gameDate) {
        this.feeId = feeId;
        this.playedId = playedId;
        this.type = type;
        this.paid = paid;
        this.gameDate = gameDate;
    }

    public int getFeeId() {
        return feeId;
    }

    public void setFeeId(int feeId) {
        this.feeId = feeId;
    }

    public int getPlayedId() {
        return playedId;
    }

    public void setPlayedId(int playedId) {
        this.playedId = playedId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getPaid() {
        return paid;
    }

    public void setPaid(int paid) {
        this.paid = paid;
    }

    public Date getGameDate() {
        return gameDate;
    }

    public void setGameDate(Date gameDate) {
        this.gameDate = gameDate;
    }
}
