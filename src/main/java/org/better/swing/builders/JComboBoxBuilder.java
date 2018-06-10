package org.better.swing.builders;

import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import javax.swing.JComboBox;
import org.better.swing.builders.util.guava.Preconditions;


/**
 * Builds a swing JComboBox that supports String values only. This is a pull down
 * box with menu options that can be selected.
 *
 * Example usage:
 *
 * <pre>{@code
 *   JComboBox combBox = JComboBoxBuilder.builder()
 *       .menuOptions("option 1", "option 2")
 *       .itemListener(selection -> selection.equals("option 1") ? fooBar())
 *       .build();
 * }</pre>
 */
public final class JComboBoxBuilder {

  private final List<String> options = new ArrayList<>();

  private Consumer<String> selectionAction;

  private JComboBoxBuilder() {

  }

  public static JComboBoxBuilder builder() {
    return new JComboBoxBuilder();
  }

  /**
   * Builds the swing component.
   */
  public JComboBox<String> build() {
    Preconditions.checkState(!options.isEmpty());
    final Consumer<String> myAction = selectionAction;

    final JComboBox<String> comboBox = new JComboBox<>(options.toArray(new String[0]));
    if (selectionAction != null) {
      comboBox.addItemListener(e -> {

        // combo box will fire two events when you change selection, first a 'ItemEvent.DESELECTED' event and then
        // a 'ItemEvent.SELECTED' event. We keep it simple for now and ignore the deselected event
        if (e.getStateChange() == ItemEvent.SELECTED) {
          final String selectionValue = (e.getItem() == null) ? null : e.getItem().toString();
          myAction.accept(selectionValue);
        }
      });
    }

    return comboBox;
  }

  /**
   * Adds a set of options to be displayed in the combo box.
   */
  public JComboBoxBuilder menuOptions(final String... options) {
    Preconditions.checkArgument(options.length > 0);
    this.options.addAll(Arrays.asList(options));
    return this;
  }

  /**
   * Adds a listener that is fired when an item is selected. The input value
   * to the passed in consumer is the value selected.
   */
  public JComboBoxBuilder itemListener(final Consumer<String> selectionAction) {
    Preconditions.checkNotNull(selectionAction);
    this.selectionAction = selectionAction;
    return this;
  }


  public JComboBoxCompositeBuilder compositeBuilder() {
    return new JComboBoxCompositeBuilder(this);
  }

}
