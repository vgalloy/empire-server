package com.vgalloy.empire.webservice.dto;

/**
 * Create by Vincent Galloy on 03/08/2017.
 *
 * @author Vincent Galloy
 */
public final class StockDto implements JsonDto {

  private Long current;
  private Long granary;
  private Long max;

  public Long getCurrent() {
    return current;
  }

  public void setCurrent(final Long current) {
    this.current = current;
  }

  public Long getGranary() {
    return granary;
  }

  public void setGranary(final Long granary) {
    this.granary = granary;
  }

  public Long getMax() {
    return max;
  }

  public void setMax(final Long max) {
    this.max = max;
  }
}
