package com.zamyshlyaev.websockettest.util;

import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Utility class to managing string objects.
 */
public class StringUtils {

  /**
   * Reverse string value.
   *
   * @param value String value which must be reversed
   * @return reversed string value
   */
  public static String reverse(@NotNull String value) {
    Objects.requireNonNull(value, "String value must be not null.");
    return new StringBuilder(value).reverse().toString();
  }

  /**
   * Check if text is empty or null.
   *
   * @param value string value.
   * @return true if value is null or contains only spaces.
   */
  public static boolean isEmpty(String value) {
    return value == null || value.trim().isEmpty();
  }
}
