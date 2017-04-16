package org.salestaxes.tax;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.salestaxes.tax.ImportDutySalesTaxItem;
import org.salestaxes.tax.Item;
import org.salestaxes.tax.Price;

public class ImportDutySalesTaxTest {

  private ImportDutySalesTaxItem tax;

  @Test(expected = IllegalArgumentException.class)
  public void givenANotImportedInputItem_whenCreated_thenShouldThrowException() {

    tax = new ImportDutySalesTaxItem(new Item.Builder().imported(false).build());

  }

  @Test
  public void givenANoExemptImportedItem_whenATotalPriceIsRequested_thenShoudApplyImportedDutyAndBasicSalesTax() {
    tax = new ImportDutySalesTaxItem(new Item.Builder()
        .exempt(false)
        .imported(true)
        .quantity(2)
        .price(new Price("12.20"))
        .build());

    assertThat(tax.getTotalPrice(), equalTo(new Price("28.10")));
  }

  @Test
  public void givenAExemptImportedItem_whenATotalPriceIsRequested_thenShoudApplyOnlyImportedDutySalesTax() {
    tax = new ImportDutySalesTaxItem(new Item.Builder()
        .exempt(true)
        .imported(true)
        .quantity(2)
        .price(new Price("12.20"))
        .build());

    assertThat(tax.getTotalPrice(), equalTo(new Price("25.65")));
  }

  @Test
  public void givenANoExemptImportedItem_whenATotalTaxIsRequested_thenResponseWithImportedDutyPlusBasicSalesTax() {
    tax = new ImportDutySalesTaxItem(new Item.Builder()
        .exempt(false)
        .imported(true)
        .quantity(2)
        .price(new Price("12.20"))
        .build());

    assertThat(tax.getTotalTax(), equalTo(new Price("3.70")));
  }

  @Test
  public void givenAnExemptImportedItem_whenATotalTaxIsRequested_thenResponseWithOnlyImportedDutySalesTax() {
    tax = new ImportDutySalesTaxItem(new Item.Builder()
        .exempt(true)
        .imported(true)
        .quantity(2)
        .price(new Price("12.20"))
        .build());

    assertThat(tax.getTotalTax(), equalTo(new Price("1.25")));
  }

}
