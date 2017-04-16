package org.salestaxes.tax;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;



public class Price {

  private final BigDecimal value;

  public static final Price ZERO = new Price(BigDecimal.ZERO);

  private Price(BigDecimal value) {
    this.value = new BigDecimal(value.doubleValue());
  }

  public Price(String price) {
    this(new BigDecimal(price));
  }

  public Price(double price) {
    this(new BigDecimal(price));
  }

  public BigDecimal getValue() {
    return new BigDecimal(value.doubleValue());
  }

  public Price multiply(Price value) {
    return new Price(this.value.multiply(value.value));
  }

  public Price multiply(double value) {
    return multiply(new Price(value));
  }

  public Price add(Price value) {
    return new Price(this.value.add(value.value));
  }

  public Price add(double value) {
    return add(new Price(value));
  }

  public Price round() {
    return new Price(Math.ceil(value.doubleValue() * 20) / 20);
  }

  @Override
  public String toString() {
    return String.format("%1.2f", value.doubleValue());
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this)
      return true;
    if (!(obj instanceof Price)) {
      return false;
    }
    Price other = (Price) obj;
    return roundForCompare(this.getValue()).compareTo(roundForCompare(other.getValue())) == 0;
  }

  private BigDecimal roundForCompare(BigDecimal value) {
    return value.setScale(2, RoundingMode.HALF_UP);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }

}
