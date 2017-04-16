package org.salestaxes.tax;

import java.util.Objects;

import com.google.common.base.MoreObjects;

public class Item {

  private final int quantity;
  private final String description;
  private final Price price;
  private final boolean imported;
  private final boolean exempt;

  public int getQuantity() {
    return quantity;
  }

  public String getDescription() {
    return description;
  }

  public Price getPrice() {
    return price;
  }

  public boolean isImported() {
    return imported;
  }

  public boolean isExempt() {
    return exempt;
  }

  @Override
  public String toString() {
    return MoreObjects
        .toStringHelper(this)
        .add("quantity", quantity)
        .add("description", description)
        .add("price", price)
        .add("imported", imported)
        .add("exempt", exempt)
        .toString();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (!(obj instanceof Item)) {
      return false;
    }
    Item item = (Item) obj;
    return quantity == item.quantity
        && price == item.price
        && imported == item.imported
        && exempt == item.exempt
        && Objects.equals(description, item.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(quantity, description, price, imported, exempt);
  }

  public static class Builder {
    private int quantity;
    private String description;
    private Price price;
    private boolean imported;
    private boolean exempt;

    public Builder quantity(int quantity) {
      this.quantity = quantity;
      return this;
    }

    public Builder description(String description) {
      this.description = description;
      return this;
    }

    public Builder price(Price price) {
      this.price = price;
      return this;
    }

    public Builder imported(boolean imported) {
      this.imported = imported;
      return this;
    }

    public Builder exempt(boolean exempt) {
      this.exempt = exempt;
      return this;
    }

    public Item build() {
      return new Item(this);
    }
  }

  private Item(Builder builder) {
    this.quantity = builder.quantity;
    this.description = builder.description;
    this.price = builder.price;
    this.imported = builder.imported;
    this.exempt = builder.exempt;
  }
}
