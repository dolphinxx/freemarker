package freemarker.template;

/**
 * Delegate to a {link PrintWriter} or to a {link PrintStream}.
 */
public interface StackTraceWriter {
    void print(Object obj);

    void println(Object obj);

    void println();

    void printStandardStackTrace(Throwable exception);
}
