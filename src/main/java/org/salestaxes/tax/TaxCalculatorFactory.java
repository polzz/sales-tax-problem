package org.salestaxes.tax;

import com.google.common.base.Preconditions;

public class TaxCalculatorFactory {

  public static TaxCalculator create(Item item) {
    Preconditions.checkArgument(item != null);

    if (item.isImported()) {
      return new ImportDutySalesTaxItem(item);
    } else {
      return new BasicSalesTax(item);
    }
  }

}
