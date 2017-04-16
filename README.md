# Sales Tax Problem #

Basic sales tax is applicable at a rate of 10% on all goods, except books, food, and medical products that are exempt. Import duty is an additional sales tax applicable on all imported goods at a rate of 5%, with no exemptions.

When I purchase items I receive a receipt which lists the name of all the items and their price (including tax), finishing with the total cost of the items, and the total amounts of sales taxes paid. The rounding rules for sales tax are that for a tax rate of n%, a shelf price of p contains (np/100 rounded up to the nearest 0.05) amount of sales tax.

The application prints out the receipt details for these shopping baskets:

```
Input 1:
1 book at 12.49
1 music CD at 14.99
1 chocolate bar at 0.85

Input 2:
1 imported box of chocolates at 10.00
1 imported bottle of perfume at 47.50

Input 3:
1 imported bottle of perfume at 27.99
1 bottle of perfume at 18.99
1 packet of headache pills at 9.75
1 box of imported chocolates at 11.25
```

Ouptut Receipts

```
Output 1:
1 book : 12.49
1 music CD: 16.49
1 chocolate bar: 0.85
Sales Taxes: 1.50
Total: 29.83

Output 2:
1 imported box of chocolates: 10.50
1 imported bottle of perfume: 54.65
Sales Taxes: 7.65
Total: 65.15

Output 3:
1 imported bottle of perfume: 32.19
1 bottle of perfume: 20.89
1 packet of headache pills: 9.75
1 imported box of chocolates: 11.85
Sales Taxes: 6.70
Total: 74.68
```

## How to run ##
from the route source folder:


```
./gradlew clean test fatJar
```

 will produce a runnable jar under build/libs/sales_taxes-all-1.0.jar copy the jar in your current directory and run:

```
java -jar sales_taxes-all-1.0.jar -i <input_file_name>

```
substitute <input_file_name> with the full path of the file containing purchased items

i.e:
```
java -jar sales_taxes-all-1.0.jar -i /tmp/input_1.txt

```

sample input files could be found under src/test/resources

to print usage:

```
java -jar sales_taxes-all-1.0.jar -h

```
## Implementation notes ##

* Used a Decorator pattern to implement tax calculation 
* Used Facory pattern to create the right Tax Implementation given an Item
* Used Price as Immutable Object to wrap item's price as BigDecimal
* Used Java 8 Features

## Assumptions ##

* Input not validate, expected in the right way
* Imported items contain "imported" in the description
* Exempts items cointain  "book" or "chocolate" or  "pill" in the description
* The application process input file one at a time

 3rd party libraries

* Apache Commons CLI to process command line arguments
* Google Guava
* Qos Lockback fo logging
* Mockito to mock object on unit tests
