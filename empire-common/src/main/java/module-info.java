/**
 * Created by Vincent Galloy on 11/10/18.
 *
 * @author Vincent Galloy
 */
module empire.common {
    exports com.vgalloy.empire.common;
    exports com.vgalloy.empire.common.executiontime;
    exports com.vgalloy.empire.common.log;
    exports com.vgalloy.empire.common.nullable;


    requires transitive slf4j.api;
    requires transitive aspectjweaver;
    requires transitive spring.context;
}