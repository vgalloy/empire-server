package com.vgalloy.empire.service.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Vincent Galloy on 20/08/17.
 *
 * @author Vincent Galloy
 */
public final class EmpireIdTest {

  @Test
  void simple() {
    // WHEN / THEN
    Assertions.assertThrows(NullPointerException.class, () -> EmpireId.of(null));
  }
}
