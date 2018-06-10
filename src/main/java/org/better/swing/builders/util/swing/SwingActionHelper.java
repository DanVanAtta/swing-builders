package org.better.swing.builders.util.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.function.Consumer;
import javax.swing.AbstractAction;
import javax.swing.Action;

public class SwingActionHelper {


  /**
   * Creates a Swing 'Action' object around a given name and action listener. Example:
   *
   * <pre>{@code
   *   private final Action nullAction = SwingAction.of(" ", e -> {
   *   });
   * }</pre>
   *
   * @param name Name for the abstract action, passed along to the AbstractAction constructor.
   * @param swingAction Lambda java.tools.function.Consumer object, accepts one arg and returns void.
   */
  public static Action of(final String name, final ActionListener swingAction) {
    return new AbstractAction(name) {
      private static final long serialVersionUID = 6751222534195121860L;

      @Override
      public void actionPerformed(final ActionEvent e) {
        swingAction.actionPerformed(e);
      }
    };
  }

  /**
   * Creates a new KeyListener that is executed on key release event.
   *
   * @param eventConsumer We will pass a key event to this consumer whenever there is a key released event.
   * @return A Swing KeyListener object, can be attached to swing component objects.
   */
  public static KeyListener keyReleaseListener(final Consumer<KeyEvent> eventConsumer) {
    return new KeyAdapter() {
      @Override
      public void keyReleased(final KeyEvent e) {
        eventConsumer.accept(e);
      }
    };
  }
}
