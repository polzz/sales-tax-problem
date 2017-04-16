package org.salestaxes.tax;

import com.google.common.base.Preconditions;

public class ImportDutySalesTaxItem extends BasicSalesTax {

  private static double RATE = 0.05;

  protected ImportDutySalesTaxItem(Item item) {
    super(item);
    Preconditions.checkArgument(item.isImported());
  }

  @Override
  public Price getTotalPrice() {
    return item.getPrice().multiply(item.getQuantity()).add(getTotalTax());
  }

  @Override
  public Price getTotalTax() {
    return item
        .getPrice()
        .multiply(item.getQuantity())
        .multiply(RATE)
        .round()
        .add(super.getTotalTax());
  }

}
