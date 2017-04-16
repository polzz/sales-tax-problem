package org.salestaxes.tax;

import org.salestaxes.receipt.ShoppingBasketItem;

import com.google.common.base.Preconditions;

public abstract class TaxCalculator implements ShoppingBasketItem {

  protected Item item;

  protected TaxCalculator(final Item item) {
    Preconditions.checkArgument(item != null);
    this.item = item;
  }

  @Override
  public abstract Price getTotalPrice();

  @Override
  public abstract Price getTotalTax();

  @Override
  public int getQuantity() {
    return item.getQuantity();
  }

  @Override
  public String getDescription() {
    return item.getDescription();
  }

}
