package logger;

/**
 *
 * @author
 */
public class LoggerFactory {
    
    public static IChatLogger getLogger(Class c) {
        return ChatLoggerProxy.getInstance(c);
    }
    
}
