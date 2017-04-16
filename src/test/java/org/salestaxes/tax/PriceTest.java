package org.salestaxes.tax;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.salestaxes.tax.Price;

public class PriceTest {

  @Test
  public void sumFromDouble() {
    Price add = new Price(1.10).add(1.2);

    assertThat(add, equalTo(new Price(2.3)));
  }

  @Test
  public void sumFromPrice() {
    Price add = new Price(1.10).add(new Price(1.2));

    assertThat(add, equalTo(new Price(2.3)));
  }

  @Test
  public void multiplyFromDouble() {
    Price add = new Price(1.10).multiply(1.2);

    assertThat(add, equalTo(new Price(1.32)));
  }

  @Test
  public void multiplyFromPrice() {
    Price add = new Price(1.10).multiply(new Price(1.2));

    assertThat(add, equalTo(new Price(1.32)));
  }

  @Test
  public void givenAPrice_whenAskedForRound_thenShouldRoundUpToTheUpper0_05() {
    assertThat(new Price(1.11).round(), equalTo(new Price(1.15)));
    assertThat(new Price(1.10).round(), equalTo(new Price(1.10)));
  }

  @Test
  public void toStringShouldFormatWith2Decimals() {
    assertThat(new Price(1.1).toString(), equalTo("1.10"));
    assertThat(new Price(1d).toString(), equalTo("1.00"));
  }

}
