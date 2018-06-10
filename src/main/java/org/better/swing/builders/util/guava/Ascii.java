package org.better.swing.builders.util.guava;

public class Ascii {

  public static String truncate(String text, int maxTextLength, String delimiter) {
    Preconditions.checkArgument(text != null);
    Preconditions.checkArgument(maxTextLength >= 0);
    Preconditions.checkArgument(delimiter != null);

    return text.length() <= maxTextLength
        ? text
        : text.substring(0, maxTextLength) + delimiter;
  }
}
