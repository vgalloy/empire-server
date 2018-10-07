package com.vgalloy.empire.feature.internal;

import org.springframework.stereotype.Component;

import com.vgalloy.empire.feature.api.Implementation;

/**
 * Created by Vincent Galloy on 07/10/18.
 *
 * @author Vincent Galloy
 */
@Component
@Implementation("add")
public class AddOps implements Ops {
    @Override
    public int apply(final int a, final int b) {
        return a + b;
    }
}
