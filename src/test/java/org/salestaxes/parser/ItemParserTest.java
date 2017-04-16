package org.salestaxes.parser;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.salestaxes.parser.ItemParser;
import org.salestaxes.tax.Item;
import org.salestaxes.tax.Price;

import com.google.common.collect.ImmutableList;

public class ItemParserTest {

  private ItemParser parser = new ItemParser();

  @Test(expected = IllegalArgumentException.class)
  public void givenWrongInput_whenProcessed_thenShouldThrowException() {
    parser.process(null);
  }

  @Test
  public void givenNoRow_whenProcessed_thenShouldReturnAnyItem() {
    List<Item> actual = parser.process(ImmutableList.of());

    assertThat(actual.size(), equalTo(0));
  }

  @Test
  public void givenInputRow_whenProcessed_thenShouldCreateItemWithCorrectFields() {
    ImmutableList<String> inputRows = ImmutableList.of("2 imported bottle of perfume at 27.99");

    List<Item> actual = parser.process(inputRows);

    assertThat(actual.size(), equalTo(1));
    Item item = actual.get(0);
    assertThat(item.getQuantity(), equalTo(2));
    assertThat(item.getDescription(), equalTo("imported bottle of perfume"));
    assertThat(item.getPrice(), equalTo(new Price("27.99")));
  }

  @Test
  public void givenImportedInputItem_whenProcessed_thenShouldReturnImportedItem() {
    ImmutableList<String> inputRows = ImmutableList.of(
        "1 imported book at 12.49\n",
        "1 imported packet of headache pills at 9.75\n", 
        "1 box of imported chocolates at 11.25");

    List<Item> actual = parser.process(inputRows);

    actual.stream().forEach(i -> assertThat(i.isExempt(), equalTo(true)));
  }

  @Test
  public void givenExemptInputItem_whenProcessed_thenShouldReturnExemptItem() {
    ImmutableList<String> inputRows = ImmutableList.of(
        "1 book at 12.49\n",
        "1 packet of headache pills at 9.75\n", 
        "1 box of chocolates at 11.25");

    List<Item> actual = parser.process(inputRows);

    actual.stream().forEach(i -> assertThat(i.isExempt(), equalTo(true)));
  }

  @Test
  public void givenImportedExemptInputItem_whenProcessed_thenShouldReturnImportedExemptItem() {
    ImmutableList<String> inputRows = ImmutableList.of(
        "1 imported book at 12.49\n",
        "1 imported packet of headache pills at 9.75\n", 
        "1 imported box of chocolates at 11.25");

    List<Item> actual = parser.process(inputRows);

    actual.stream().forEach(i -> {
      assertThat(i.isExempt(), equalTo(true));
      assertThat(i.isImported(), equalTo(true));
    });
  }

}
