package org.better.swing.builders;

import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import org.better.swing.builders.util.apache.Pair;

/**
 * Builder for a JTabbedPane. Provides a convenient API for adding components
 * to a JTabbedPane and starts off with reasonable defaults that can be configured
 * further as needed.
 *
 * Example usage:
 *
 * <pre>{@code
 * JTabbedPaneBuilder JTabbedPaneBuilder.builder()
 *    .addTab(new JLabel("I will be tab one")
 *    .addTab(new JLabel("And I will be tab two")
 *    .build();
 * }</pre>
 */
public class JTabbedPaneBuilder {

  private static final int DEFAULT_TAB_WIDTH = 130;
  private static final int DEFAULT_TAB_HEIGHT = 20;
  private final List<Pair<String, Component>> components = new ArrayList<>();
  private int tabIndex = 0;

  private JTabbedPaneBuilder() {

  }

  public static JTabbedPaneBuilder builder() {
    return new JTabbedPaneBuilder();
  }

  private static JScrollPane newJScrollPane(Component contents) {
    final JScrollPane scroll = new JScrollPane();
    scroll.setViewportView(contents);
    scroll.setBorder(null);
    scroll.getViewport().setBorder(null);
    return scroll;
  }

  /**
   * Builds the swing component.
   */
  public JTabbedPane build() {
    final JTabbedPane tabbedPane = new JTabbedPane();

    components.forEach(component -> {
      final JLabel sizedLabel = new JLabel(component.getKey());
      sizedLabel.setPreferredSize(new Dimension(DEFAULT_TAB_WIDTH, DEFAULT_TAB_HEIGHT));
      tabbedPane.addTab(component.getKey(), newJScrollPane(component.getValue()));
      tabbedPane.setTabComponentAt(tabIndex, sizedLabel);
      tabIndex++;
    });

    return tabbedPane;
  }

  public JTabbedPaneBuilder addTab(final String tabTitle, final Component tabContents) {
    components.add(new Pair<>(tabTitle, tabContents));
    return this;
  }
}
