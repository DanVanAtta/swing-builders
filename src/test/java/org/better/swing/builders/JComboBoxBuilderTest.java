package org.better.swing.builders;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.awt.event.ItemEvent;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.JComboBox;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class JComboBoxBuilderTest {
  @Mock private ItemEvent mockItemEvent;

  @Test
  public void buildInvalidEmptyOptionSet() {
    assertThrows(
        IllegalArgumentException.class, () -> JComboBoxBuilder.builder().menuOptions().build());
  }

  @Test
  public void builderNoMenuOptionSpecified() {
    assertThrows(IllegalStateException.class, JComboBoxBuilder.builder()::build);
  }

  @Test
  public void basicBuilderWithMenuOptions() {
    final JComboBox<String> box =
        JComboBoxBuilder.builder().menuOptions("option 1", "option 2", "option 3").build();

    MatcherAssert.assertThat(box.getSelectedIndex(), Is.is(0));
    MatcherAssert.assertThat(box.getItemCount(), Is.is(3));
    MatcherAssert.assertThat(box.getItemAt(0), Is.is("option 1"));
    MatcherAssert.assertThat(box.getItemAt(1), Is.is("option 2"));
    MatcherAssert.assertThat(box.getItemAt(2), Is.is("option 3"));
    MatcherAssert.assertThat(box.getSelectedItem(), Is.is("option 1"));
  }

  @Test
  public void itemListener() {

    final AtomicInteger triggerCount = new AtomicInteger(0);

    final String secondOption = "option 2";
    final JComboBox<String> box =
        JComboBoxBuilder.builder()
            .menuOptions("option 1", secondOption, "option 3")
            .itemListener(
                value -> {
                  if (value.equals(secondOption)) {
                    triggerCount.incrementAndGet();
                  }
                })
            .build();
    box.setSelectedIndex(1);
    MatcherAssert.assertThat(triggerCount.get(), Is.is(1));
  }
}
