package pl.petwalker.test.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class UserTrace {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "CEST")
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
