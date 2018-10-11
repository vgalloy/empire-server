package com.vgalloy.empire.feature.internal.sample;

import com.vgalloy.empire.feature.api.Implementation;

/**
 * Created by Vincent Galloy on 07/10/18.
 *
 * @author Vincent Galloy
 */
@Implementation("Minus")
public class MinusOperation implements Operation {

    @Override
    public int apply(final int a, final int b) {
        return a  + b;
    }
}
