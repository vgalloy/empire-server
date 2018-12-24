package com.vgalloy.empire.feature.internal.operation;

import com.vgalloy.empire.feature.api.Feature;

/**
 * Created by Vincent Galloy on 07/10/18.
 *
 * @author Vincent Galloy
 */
@Feature("operation")
public interface Operation {

  int process(int a, int b);
}
