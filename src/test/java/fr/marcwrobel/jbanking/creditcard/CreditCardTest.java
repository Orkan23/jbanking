package fr.marcwrobel.jbanking.creditcard;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditCardTest {


  @ParameterizedTest(name = "check if {0} is {1}")
  @CsvSource({"378282246310005, true", "371449635398431, true", "378734493671000, true", "5610591081018250, false",
    "30569309025904, true", "38520000023237, true", "6011111111111117, true", "6011000990139424, true",
    "6011000990139424, true", "3530111333300000, true", "3566002020360505, true", "5555555555554444,true",
    "5105105105105100, true", "4111111111111111, true", "4012888888881881, true"})
  void testisValid(String card, Boolean expected) {
    //setup
    CreditCard c = new CreditCard(card);
    //exercise
    Boolean result = c.isValid();
    //verify
    assertEquals(expected, result);
  }


  @ParameterizedTest(name = "check if {0} is from brand {1}")
  @CsvSource({"378282246310005, amex", "371449635398431, amex", "378734493671000, amex", "5610591081018250, invalidCard",
    "30569309025904, diners", "38520000023237, diners", "6011111111111117, discover", "6011000990139424, discover",
    "60110009901394241, invalidCard", "3530111333300000, jcb", "3566002020360505, jcb", "5555555555554444,mastercard",
    "5105105105105100, mastercard", "4111111111111111, visa", "4012888888881881, visa"})
  void testBrand(String card, String brand) {
    //setup
    CreditCard c = new CreditCard(card);
    //exercise
    String result = c.getCardBrand();
    //verify
    assertEquals(brand, result);
  }









}
