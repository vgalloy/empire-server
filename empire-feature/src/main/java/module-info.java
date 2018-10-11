/**
 * Created by Vincent Galloy on 11/10/18.
 *
 * @author Vincent Galloy
 */
module empire.feature {
    requires transitive empire.common;
    exports com.vgalloy.empire.feature.api;

    requires transitive spring.core;
    requires transitive spring.boot;
}