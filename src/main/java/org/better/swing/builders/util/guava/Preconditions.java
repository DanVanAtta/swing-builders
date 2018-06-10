package org.better.swing.builders.util.guava;

public class Preconditions {

  public static <T> T checkNotNull(final T child) {
    if (child == null) {
      throw new NullPointerException("Null value");
    }
    return child;
  }

  public static void checkArgument(final boolean condition) {
    if (!condition) {
      throw new IllegalArgumentException("Argument has bad value");
    }

  }

  public static void checkState(final boolean condition) {
    checkState(condition, "Bad state");
  }

  public static void checkState(final boolean condition, String msg) {
    if (!condition) {
      throw new IllegalStateException(msg);
    }
  }
}
