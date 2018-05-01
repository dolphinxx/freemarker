/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package freemarker.log;

/**
 * Delegates logger creation to an actual logging library. By default it looks for logger libraries in this order (in
 * FreeMarker 2.3.x): Log4J, Avalon LogKit, JUL (i.e., <tt>java.util.logging</tt>). Prior to FreeMarker 2.4, SLF4J and
 * Apache Commons Logging aren't searched automatically due to backward compatibility constraints. But if you have
 * {@code log4j-over-slf4j} properly installed (means, you have no real Log4j in your class path, and SLF4J has a
 * backing implementation like {@code logback-classic}), then FreeMarker will use SLF4J directly instead of Log4j (since
 * FreeMarker 2.3.22).
 * <p>
 * <p>
 * If the auto detection sequence describet above doesn't give you the result that you want, see
 * {link #SYSTEM_PROPERTY_NAME_LOGGER_LIBRARY}.
 */
public class Logger {

    private static String categoryPrefix = "";

    private String category;

    /**
     * Sets a category prefix. This prefix is prepended to any logger category name. This makes it possible to have
     * different FreeMarker logger categories on a per-application basis (better said, per-classloader basis). By
     * default the category prefix is the empty string. If you set a non-empty category prefix, be sure to include the
     * trailing separator dot (i.e. "MyApp.") If you want to change the default setting, do it early in application
     * initialization phase, before calling any other FreeMarker API since once various parts of the FreeMarker library
     * bind to the logging subsystem, the change in this value will have no effect on them.
     *
     * @deprecated This wasn't reliable, unless you can somehow ensure that you access the FreeMarker classes first. As
     * it's not known to be useful for users, consider it removed.
     */
    @Deprecated
    public static void setCategoryPrefix(String prefix) {
        if (prefix == null) {
            throw new IllegalArgumentException();
        }
        categoryPrefix = prefix;
    }

    public Logger(String category) {
        this.category = category + ':';
    }

    /**
     * Logs a debugging message.
     */
    public void debug(String message) {
    }

    /**
     * Logs a debugging message with accompanying throwable.
     */
    public void debug(String message, Throwable t) {
    }

    /**
     * Logs an informational message.
     */
    public void info(String message) {
    }

    /**
     * Logs an informational message with accompanying throwable.
     */
    public void info(String message, Throwable t) {
    }

    /**
     * Logs a warning message.
     */
    public void warn(String message) {
    }

    /**
     * Logs a warning message with accompanying throwable.
     */
    public void warn(String message, Throwable t) {
    }

    /**
     * Logs an error message.
     */
    public void error(String message) {
    }

    /**
     * Logs an error message with accompanying throwable.
     */
    public void error(String message, Throwable t) {
    }

    /**
     * Returns true if this logger will log debug messages.
     */
    public boolean isDebugEnabled() {
        return true;
    }

    /**
     * Returns true if this logger will log informational messages.
     */
    public boolean isInfoEnabled() {
        return true;
    }

    /**
     * Returns true if this logger will log warning messages.
     */
    public boolean isWarnEnabled() {
        return true;
    }

    /**
     * Returns true if this logger will log error messages.
     */
    public boolean isErrorEnabled() {
        return true;
    }

    /**
     * Returns true if this logger will log fatal error messages.
     */
    public boolean isFatalEnabled() {
        return true;
    }

    /**
     * Returns a logger for the specified category.
     *
     * @param category a dot separated hierarchical category name. If a category prefix is in effect, it's prepended to the
     *                 category name.
     */
    public static Logger getLogger(String category) {
        if (categoryPrefix.length() != 0) {
            category = categoryPrefix + category;
        }
        return new Logger(category);
    }
}
