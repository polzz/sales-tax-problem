package org.salestaxes.tax;

public class BasicSalesTax extends TaxCalculator {

  private static final double RATE = 0.1;

  protected BasicSalesTax(Item item) {
    super(item);
  }

  @Override
  public Price getTotalPrice() {
    Price price = item.getPrice().multiply(item.getQuantity());
    return item.isExempt() ? price : price.add(getTotalTax());
  }

  @Override
  public Price getTotalTax() {
    return item.isExempt() ? Price.ZERO
        : item.getPrice()
        .multiply(item.getQuantity())
        .multiply(RATE)
        .round();
  }



}
