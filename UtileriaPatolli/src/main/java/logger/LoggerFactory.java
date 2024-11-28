package logger;

/**
 *
 * @author
 */
public class LoggerFactory {
    
    public static IPatolliLogger getLogger(Class c) {
        return PatolliLoggerProxy.getInstance(c);
    }
    
}
