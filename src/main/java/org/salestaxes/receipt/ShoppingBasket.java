package org.salestaxes.receipt;

import java.util.ArrayList;
import java.util.List;

import org.salestaxes.tax.Price;


public class ShoppingBasket {

  List<ShoppingBasketItem> items;

  public ShoppingBasket(List<ShoppingBasketItem> items) {
    this.items = items;
  }

  public List<String> receipt() {
    List<String> output = new ArrayList<>();

    Price taxes = Price.ZERO;
    Price total = Price.ZERO;
    for (ShoppingBasketItem item : items) {
      output.add(String.format("%1$d %2$s: %3$s", item.getQuantity(), item.getDescription(),
          item.getTotalPrice()));
      taxes = taxes.add(item.getTotalTax());
      total = total.add(item.getTotalPrice());
    }
    output.add(String.format("Sales Taxes: %1$s", taxes));
    output.add(String.format("Total: %1$s", total));

    return output;
  }

}
