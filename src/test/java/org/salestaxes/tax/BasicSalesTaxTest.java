package org.salestaxes.tax;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.salestaxes.tax.BasicSalesTax;
import org.salestaxes.tax.Item;
import org.salestaxes.tax.Price;

public class BasicSalesTaxTest {

  private BasicSalesTax tax;

  @Test(expected = IllegalArgumentException.class)
  public void givenNullItem_whenCreated_thenShouldThrowException() {
    tax = new BasicSalesTax(null);
  }

  @Test
  public void givenAnExemptItem_whenATotalPriceIsRequested_whenShoudMultiplyQuantityByPriceWithoutApplyTax() {
    tax = new BasicSalesTax(
        new Item.Builder()
        .exempt(true)
        .quantity(2)
        .price(new Price("12.20"))
        .build());

    assertThat(tax.getTotalPrice(), equalTo(new Price("24.40")));
  }

  @Test
  public void givenAnNoExemptItem_whenATotalPriceIsRequested_thenShoudApplyRoundedTaxToTotalPrice() {
    tax =
        new BasicSalesTax(new Item.Builder()
            .quantity(2)
            .price(new Price("12.20"))
            .build());

    assertThat(tax.getTotalPrice(), equalTo(new Price("26.85")));
  }

  @Test
  public void givenAnExemptItem_whenATotalTaxIsRequested_whenShoudResponseWithZero() {
    tax = new BasicSalesTax(
        new Item.Builder()
        .exempt(true)
        .quantity(2)
        .price(new Price("12.20"))
        .build());

    assertThat(tax.getTotalTax(), equalTo(Price.ZERO));
  }

  @Test
  public void givenAnNoExemptItem_whenATotalTaxIsRequested_whenShoudResponseWithRoundedTax() {
    tax =
        new BasicSalesTax(new Item.Builder()
            .quantity(2)
            .price(new Price("12.20"))
            .build());

    assertThat(tax.getTotalTax(), equalTo(new Price("2.45")));
  }

}
