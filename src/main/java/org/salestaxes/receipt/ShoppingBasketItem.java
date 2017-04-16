package org.salestaxes.receipt;

import org.salestaxes.tax.Price;

public interface ShoppingBasketItem {

  int getQuantity();

  String getDescription();

  Price getTotalPrice();

  Price getTotalTax();

}
