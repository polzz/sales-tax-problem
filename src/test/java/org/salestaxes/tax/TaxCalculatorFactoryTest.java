package org.salestaxes.tax;

import static org.junit.Assert.assertThat;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.salestaxes.tax.BasicSalesTax;
import org.salestaxes.tax.ImportDutySalesTaxItem;
import org.salestaxes.tax.Item;
import org.salestaxes.tax.TaxCalculator;
import org.salestaxes.tax.TaxCalculatorFactory;

public class TaxCalculatorFactoryTest {

  @Test(expected = IllegalArgumentException.class)
  public void givenWrongItem_whenCreated_thenShouldThrowException() {
    TaxCalculatorFactory.create(null);
  }

  @Test
  public void givenNoImportedItem_whenCreated_thenShouldCreateSalesTaxCalculator() {
    Item item = new Item.Builder().imported(true).build();

    TaxCalculator create = TaxCalculatorFactory.create(item);

    assertThat(create, CoreMatchers.instanceOf(BasicSalesTax.class));
  }

  @Test
  public void givenImportedItem_whenCreated_thenShouldCreateImportedSalesTaxCalculator() {
    Item importedItem = new Item.Builder().imported(true).build();

    TaxCalculator create = TaxCalculatorFactory.create(importedItem);

    assertThat(create, CoreMatchers.instanceOf(ImportDutySalesTaxItem.class));
  }

}
