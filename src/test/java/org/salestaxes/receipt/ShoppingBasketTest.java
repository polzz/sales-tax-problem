package org.salestaxes.receipt;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;
import org.salestaxes.receipt.ShoppingBasket;
import org.salestaxes.receipt.ShoppingBasketItem;
import org.salestaxes.tax.Price;

import com.google.common.collect.ImmutableList;

public class ShoppingBasketTest {

  @Test
  public void givenInputItems_whenRequestToGenerateReceipt_thenShoulGenerateCorrectOutput() {

    ShoppingBasketItem item1 = Mockito.mock(ShoppingBasketItem.class);
    Mockito.when(item1.getDescription()).thenReturn("imported box of chocolates");
    Mockito.when(item1.getQuantity()).thenReturn(1);
    Mockito.when(item1.getTotalPrice()).thenReturn(new Price(10.5));
    Mockito.when(item1.getTotalTax()).thenReturn(new Price(0.05));
    
    ShoppingBasketItem item2 = Mockito.mock(ShoppingBasketItem.class);
    Mockito.when(item2.getDescription()).thenReturn("box of chocolates");
    Mockito.when(item2.getQuantity()).thenReturn(1);
    Mockito.when(item2.getTotalPrice()).thenReturn(new Price(10.45));
    Mockito.when(item2.getTotalTax()).thenReturn(Price.ZERO);

    ShoppingBasket receipt = new ShoppingBasket(ImmutableList.of(item1,item2));

    List<String> output = receipt.receipt();

    assertThat(output, equalTo(ImmutableList.of(
        "1 imported box of chocolates: 10.50",
        "1 box of chocolates: 10.45",
        "Sales Taxes: 0.05", 
        "Total: 20.95")));
  }

}
