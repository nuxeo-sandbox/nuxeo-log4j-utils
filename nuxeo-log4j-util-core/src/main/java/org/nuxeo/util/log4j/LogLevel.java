package org.nuxeo.util.log4j;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Level;
import org.nuxeo.ecm.automation.core.Constants;
import org.nuxeo.ecm.automation.core.annotations.Operation;
import org.nuxeo.ecm.automation.core.annotations.OperationMethod;
import org.nuxeo.ecm.automation.core.annotations.Param;
import org.nuxeo.log4j.Log4JHelper;

/**
 * Enable or disable debug logging for specified log categories.
 */
@Operation(id = LogLevel.ID, category = Constants.CAT_SERVICES, label = "Change Log Level", description = "Change (or reset) a Log Level, modified CONSOLE appender.")
public class LogLevel {

    public static final String ID = "Services.LogLevel";

    @Param(name = "categories", required = true, description = "Comma separated list of categories to modify.")
    protected String categories;

    @Param(name = "children", required = false, description = "Enable or disable debug for sub-categories.", values = {
            "false" })
    protected boolean children;

    @Param(name = "debug", required = false, description = "Enable or disable debug for the categories.", values = {
            "true" })
    protected boolean debug = true;

    @Param(name = "level", required = false, description = "Specify level to override.")
    protected String level = null;

    @OperationMethod
    public void run() {
        Level lvl = this.debug ? Level.DEBUG : Level.WARN;
        String[] cat = categories.split(",");
        if (StringUtils.isNotBlank(level)) {
            lvl = Level.toLevel(level);
        }
        Log4JHelper.setLevel(cat, lvl, children);
    }
}
