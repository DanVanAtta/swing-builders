package org.better.swing.builders.util.guava;

import java.util.Optional;

public class Strings {

  public static String nullToEmpty(final String toolTip) {
    return Optional.ofNullable(toolTip).orElse("");
  }
}
