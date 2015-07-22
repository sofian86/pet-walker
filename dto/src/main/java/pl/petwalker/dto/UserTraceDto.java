package pl.petwalker.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class UserTraceDto {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "CEST")
    private Date time;
    private PositionDto position;

    public UserTraceDto() {
    }

    public UserTraceDto(Date time, PositionDto position) {
        this.time = time;
        this.position = position;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public PositionDto getPosition() {
        return position;
    }

    public void setPosition(PositionDto position) {
        this.position = position;
    }
}
