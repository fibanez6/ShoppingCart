# SHOPPING CART

## REQUIREMENTS
* Java 8
* Maven
* GIT

## Installation

```
  > git pull git@github.com:fibanez6/ShoppingCart.git
  > cd ShoppingCart
  > mvn clean install
```

## Execution

```
  > java -jar target/shoppingCart-1.0-SNAPSHOT.jar item1 item2 item3 ...
```


## Introduction

Write a program and associated unit tests that can price a basket of goods, accounting for special offers.

The goods that can be purchased, which are all priced in GBP, are:

-   Soup – 65p per tin
-   Bread – 80p per loaf
-   Milk – £1.30 per bottle
-   Apples – £1.00 per bag

Current special offers:

-   Apples have a 10% discount off their normal price this week
-   Buy 2 tins of soup and get a loaf of bread for half price

The program should accept a list of items in the basket and output the
subtotal, the special offer discounts and the final price.

Input should be via the command line in the form `PriceBasket item1 item2 item3 ...`

For example:

    PriceBasket Apples Milk Bread

Output should be to the console, for example:

    Subtotal: £3.10
    Apples 10% off: -10p
    Total: £3.00

If no special offers are applicable the code should output:

    Subtotal: £1.30
    (no offers available)
    Total price: £1.30

The code and design should meet these requirements but be sufficiently flexible to allow for future extensibility. The
code should be well structured, suitably commented, have error handling and be tested.

## Solution

The chain-of-responsibility pattern is used to solve this challenge.

Each offer will be part of the chain of handlers, which are classes that extend the OfferHandler.class.

The BillingGenerator.class will create the chain of handlers from an offer list and price list, and it
is responsible of generate the bill given a shopping cart. The process is as follows:

- The chain are set as follows: OfferHandler1 -> OfferHandler2 -> OfferHandler3 -> OfferHandler4
- Each of the offer handlers in the chain takes its turn at trying to handle the shopping cart it
receives from the BillingGenerator.class.
- If the offerHandler_i can handle it, then a BillDiscount object is created and added to the the list of bill discounts. Then if chain has a next offer handler,
the shopping cart is sent to the handle offerHandler_i+1.
- If the offerHandler_i can not handle it, but chain has a next offer handler then the shopping cart is sent to the handle offerHandler_i+1.






