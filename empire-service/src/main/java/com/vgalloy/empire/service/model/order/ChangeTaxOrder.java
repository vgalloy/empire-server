package com.vgalloy.empire.service.model.order;

import com.vgalloy.empire.service.model.Empire;

/**
 * Create by Vincent Galloy on 04/08/2017.
 *
 * @author Vincent Galloy
 */
final class ChangeTaxOrder implements Order {

  private final long amount;

  /**
   * Constructor. Private to avoid non managed instantiation
   *
   * @param amount the new tax
   */
  private ChangeTaxOrder(final long amount) {
    this.amount = amount;
  }

  /**
   * Build a new Order of type Change tax.
   *
   * @param amount the amount
   * @return the a new Order
   */
  static ChangeTaxOrder of(final long amount) {
    return new ChangeTaxOrder(Math.max(0, amount));
  }

  @Override
  public Empire apply(final Empire empire) {
    return empire.tax(amount);
  }
}
