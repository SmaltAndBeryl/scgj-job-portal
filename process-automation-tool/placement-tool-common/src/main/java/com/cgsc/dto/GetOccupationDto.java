package com.cgsc.dto;

public class GetOccupationDto {
    private int occupationId;
    private String occupationName;

    public int getOccupationId() {
        return occupationId;
    }

    public void setOccupationId(int occupationId) {
        this.occupationId = occupationId;
    }

    public String getOccupationName() {
        return occupationName;
    }

    public void setOccupationName(String occupationName) {
        this.occupationName = occupationName;
    }

    /**
     * @author Sarthak Bhutani
     * @param occupationId
     * @param occupationName
     */
    public GetOccupationDto(int occupationId, String occupationName) {
        this.occupationId = occupationId;
        this.occupationName=occupationName;
    }

    /**
     * Default Constructor
     */
    public GetOccupationDto(){
        super();
    }
}
