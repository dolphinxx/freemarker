package freemarker.template;

import java.io.PrintStream;

public class PrintStreamStackTraceWriter implements StackTraceWriter {

    private final PrintStream out;

    public PrintStreamStackTraceWriter(PrintStream out) {
        this.out = out;
    }

    public void print(Object obj) {
        out.print(obj);
    }

    public void println(Object obj) {
        out.println(obj);
    }

    public void println() {
        out.println();
    }

    public void printStandardStackTrace(Throwable exception) {
        if (exception instanceof TemplateException) {
            ((TemplateException) exception).printStandardStackTrace(out);
        } else {
            exception.printStackTrace(out);
        }
    }

}
