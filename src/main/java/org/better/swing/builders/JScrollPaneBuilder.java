package org.better.swing.builders;

import java.awt.Component;
import java.util.Optional;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import org.better.swing.builders.util.guava.Preconditions;

/**
 * A builder for incrementally creating instances of {@link JScrollPane}.
 *
 * <p>
 * Example usage:
 * </p>
 *
 * <pre>{@code
 * JScrollPaneBuilder scrollPane = JScrollPaneBuilder.builder()
 *     .view(view)
 *     .build();
 * }</pre>
 */
public final class JScrollPaneBuilder {

  private Border border;
  private Component view;

  private JScrollPaneBuilder() {
  }

  public static JScrollPaneBuilder builder() {
    return new JScrollPaneBuilder();
  }

  /**
   * Sets the scroll pane border.
   *
   * @param border The border.
   */
  public JScrollPaneBuilder border(final Border border) {
    Preconditions.checkNotNull(border);

    this.border = border;
    return this;
  }

  /**
   * Conditionally sets the scroll pane border.
   *
   * @param border The border; if empty, the current border will not be changed.
   */
  public JScrollPaneBuilder border(final Optional<Border> border) {
    Preconditions.checkNotNull(border);

    border.ifPresent(this::border);
    return this;
  }

  /**
   * Sets the component to display in the scroll pane's viewport.
   *
   * @param view The component to display in the scroll pane's viewport.
   */
  public JScrollPaneBuilder view(final Component view) {
    Preconditions.checkNotNull(view);

    this.view = view;
    return this;
  }

  /**
   * Creates a new scroll pane using the builder's current state.
   *
   * @return A new scroll pane.
   * @throws IllegalStateException If {@code view} is unspecified.
   */
  public JScrollPane build() {
    Preconditions.checkState(view != null, "view must be specified");

    final JScrollPane scrollPane = new JScrollPane(view);

    if (border != null) {
      scrollPane.setBorder(border);
    }

    return scrollPane;
  }
}
