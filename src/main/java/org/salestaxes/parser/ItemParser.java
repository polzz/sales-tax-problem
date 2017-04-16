package org.salestaxes.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.salestaxes.tax.Item;
import org.salestaxes.tax.Price;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;


public class ItemParser {

  private static final String IMPORTED_PATTERN = "imported";
  private static final String DESCRIPTION_REGEX = "^(\\d*)\\s*(.+)\\s*at\\s*(\\d*\\.?\\d*)$";

  private List<String> exemptions = ImmutableList.of("book", "chocolate", "pill");

  public List<Item> process(List<String> rows) {

    Preconditions.checkArgument(rows != null);

    Pattern pattern = Pattern.compile(DESCRIPTION_REGEX);

    List<Item> items = new ArrayList<>();

    for (String row : rows) {
      Matcher matcher = pattern.matcher(row);
      while (matcher.find()) {
        Item.Builder builder = new Item.Builder();
        String description = matcher.group(2).trim();
        if (description.contains(IMPORTED_PATTERN)) {
          builder.imported(true);
        }
        if (exemptions.stream().anyMatch(description::contains)) {
          builder.exempt(true);
        }
        Item item = builder
            .quantity(Integer.parseInt(matcher.group(1)))
            .description(description)
            .price(new Price(matcher.group(3)))
            .build();
        items.add(item);
      }
    }
    return items;
  }

}
