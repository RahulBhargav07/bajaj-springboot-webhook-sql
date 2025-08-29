package com.example.webhook.util;

public class RegNoUtils {
  public static int lastTwoDigits(String regNo) {
    if (regNo == null) return 0;
    String digits = regNo.replaceAll("\\D", "");
    if (digits.isEmpty()) return 0;
    String lastTwo = digits.length() >= 2 ? digits.substring(digits.length() - 2) : digits;
    return Integer.parseInt(lastTwo);
  }
}
