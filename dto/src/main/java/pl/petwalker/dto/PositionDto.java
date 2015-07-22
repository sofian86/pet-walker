package pl.petwalker.dto;

import lombok.Data;

public @Data class PositionDto {

    private double latitude;
    private double longtitude;

    public PositionDto() {
    }

    public PositionDto(double latitude, double longtitude) {
        this.latitude = latitude;
        this.longtitude = longtitude;
    }

}
