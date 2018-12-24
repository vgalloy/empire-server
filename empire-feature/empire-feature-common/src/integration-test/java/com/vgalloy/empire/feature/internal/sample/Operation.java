package com.vgalloy.empire.feature.internal.sample;

import com.vgalloy.empire.feature.api.Feature;

/**
 * Created by Vincent Galloy on 07/10/18.
 *
 * @author Vincent Galloy
 */
@Feature("operation")
public interface Operation {

  int apply(int a, int b);
}
