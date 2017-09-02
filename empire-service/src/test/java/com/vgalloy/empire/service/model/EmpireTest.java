package com.vgalloy.empire.service.model;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Created by Vincent Galloy on 20/08/17.
 *
 * @author Vincent Galloy
 */
public final class EmpireTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void simple() {
        // EXCEPTION
        expectedException.expect(NullPointerException.class);

        // WHEN
        Empire.newInstance();
    }
}
