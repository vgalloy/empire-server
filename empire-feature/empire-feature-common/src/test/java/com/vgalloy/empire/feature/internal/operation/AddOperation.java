package com.vgalloy.empire.feature.internal.operation;

import com.vgalloy.empire.feature.api.Implementation;

/**
 * Created by Vincent Galloy on 07/10/18.
 *
 * @author Vincent Galloy
 */
@Implementation("add")
public class AddOperation implements Operation {

  @Override
  public int process(final int a, final int b) {
    return a + b;
  }
}
