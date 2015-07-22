package pl.petwalker.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

public @Data class UserTraceDto {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "CEST")
    private Date time;
    private PositionDto position;

    public UserTraceDto() {
    }

    public UserTraceDto(Date time, PositionDto position) {
        this.time = time;
        this.position = position;
    }

}
