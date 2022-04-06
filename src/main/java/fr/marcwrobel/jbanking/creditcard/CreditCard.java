package fr.marcwrobel.jbanking.creditcard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreditCard {
  private String cardNumber;
  private String brand;

  public CreditCard(final String cardNumber) {
    this.cardNumber = cardNumber;
  }

  private ArrayList<String> cardBrands = new ArrayList<>(Arrays.asList(
    "visa",
    "mastercard",
    "discover",
    "amex",
    "diners",
    "jcb"
  ));


  public boolean isValid() {

    String BASIC_REGEX = "^(?:(?<visa>4[0-9]{12}(?:[0-9]{3})?)|" +
      "(?<mastercard>5[1-5][0-9]{14})|" +
      "(?<discover>6(?:011|5[0-9]{2})[0-9]{12})|" +
      "(?<amex>3[47][0-9]{13})|" +
      "(?<diners>3(?:0[0-5]|[68][0-9])?[0-9]{11})|" +
      "(?<jcb>(?:2131|1800|35[0-9]{3})[0-9]{11}))$";

    Pattern cardPattern = Pattern.compile(BASIC_REGEX);

    this.cardNumber = cardNumber.replaceAll("-", "");

    Matcher matcher = cardPattern.matcher(cardNumber);

    if (matcher.matches()) {
      for (String s : cardBrands) {
        if (matcher.group(s) != null)
          brand = s;
      }
    } else
      return false;

    return verifyChecksum();
  }

  public String getCardBrand() {
    if (brand == null) {
      if (!this.isValid())
        return "invalidCard";
    }

    return brand;

  }

  private boolean verifyChecksum() {
    int sum = 0;
    boolean alternate = false;
    for (int i = this.cardNumber.length() - 1; i >= 0; i--) {
      int n = Integer.parseInt(this.cardNumber.substring(i, i + 1));
      if (alternate) {
        n *= 2;
        if (n > 9) {
          n = (n % 10) + 1;
        }
      }
      sum += n;
      alternate = !alternate;
    }
    return (sum % 10 == 0);
  }


  public static void main(String[] args) {

    CreditCard c = new CreditCard("38520000023237");
    System.out.println(c.isValid() + " " + c.getCardBrand());

  }

}
