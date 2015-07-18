package pl.petwalker.test.model;

import java.util.Date;

public class UserTrace {

    private Date time;
    private Position position;

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
