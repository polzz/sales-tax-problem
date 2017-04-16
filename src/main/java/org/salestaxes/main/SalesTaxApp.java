package org.salestaxes.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.salestaxes.cli.CLI;
import org.salestaxes.parser.ItemParser;
import org.salestaxes.receipt.ShoppingBasket;
import org.salestaxes.receipt.ShoppingBasketItem;
import org.salestaxes.tax.Item;
import org.salestaxes.tax.TaxCalculatorFactory;

import com.google.common.collect.ImmutableList;



public class SalesTaxApp {

  public static void main(String[] args) {
    String fileName = new CLI(args).parse().get();
    List<String> rows = processFile(fileName);
    
    List<Item> items = new ItemParser().process(rows);
    List<ShoppingBasketItem> taxedItems =
        items.stream()
        .map(TaxCalculatorFactory::create)
        .collect(Collectors.toList());
    List<String> output = new ShoppingBasket(taxedItems).receipt();
    output.stream().forEach(System.out::println);
  }

  private static List<String> processFile(final String fileName) {
    try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
      return br.lines().collect(Collectors.toList());
    } catch (IOException e) {
      return ImmutableList.of();
    }
  }

}
