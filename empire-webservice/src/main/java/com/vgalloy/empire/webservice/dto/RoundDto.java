package com.vgalloy.empire.webservice.dto;

/**
 * Create by Vincent Galloy on 03/08/2017.
 *
 * @author Vincent Galloy
 */
public final class RoundDto implements Dto {

    private static final long serialVersionUID = -1914593785780456504L;

    private Integer year;
    private String month;

    public Integer getYear() {
        return year;
    }

    public void setYear(final Integer year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(final String month) {
        this.month = month;
    }
}
