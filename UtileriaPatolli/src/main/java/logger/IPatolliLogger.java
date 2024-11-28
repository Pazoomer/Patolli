package logger;

/**
 *
 * @author
 */
public interface IPatolliLogger {
    void log(String message);
    void error(String message);
    void info(String message);
    void warning(String message);
}
