package com.truemoney.walletgw.common;

import java.util.Random;

import org.apache.commons.lang3.StringUtils;

public class NumberUtil {
  public static int randomNumber(int min, int max) {
    int range = (max - min) + 1;
    Random random = new Random();
    int result = random.nextInt(range) + min;

    return result;
  }

  public static Long convertToLong(String number) {
    try {
      if (StringUtils.isEmpty(number))
        return 0L;
      Long n = Long.parseLong(number);
      return n;
    } catch (Exception ex) {
      return 0L;
    }

  }

  public static int convertToInt(String number) {
    try {
      if (StringUtils.isEmpty(number))
        return 0;
      int n = Integer.parseInt(number);
      return n;
    } catch (Exception ex) {
      return 0;
    }

  }
}
