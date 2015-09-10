/**
 * JFlux
 *
 * Created by Giang Nguyen on 9/10/15.
 * Copyright (c) 2015 Umbala. All rights reserved.
 */

package com.tale.jflux;

public class Invariant {

  public static void invariant(boolean condition, String message, Object... object) {
    if (!condition) {
      throw new RuntimeException(String.format(message, object));
    }
  }
}
