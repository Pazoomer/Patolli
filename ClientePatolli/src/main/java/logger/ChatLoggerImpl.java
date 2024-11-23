package logger;

import java.io.Serializable;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 *
 * @author 
 */
class ChatLoggerImpl implements IChatLogger, Serializable {

    private static IChatLogger instance;
    private final Class clazz;
    private final Logger logger;

    private ChatLoggerImpl(Class clazz) {
        this.clazz = clazz;
        this.logger = Logger.getLogger(clazz.getName());
    }

    public static IChatLogger getInstance(Class clazz) {
        return instance != null ? instance : (instance = new ChatLoggerImpl(clazz));
    }

    @Override
    public void log(String message) {
        System.out.println(String.format("%s : %s", clazz.getName(), message));
        logger.log(Level.FINE, message);
    }

    @Override
    public void error(String message) {
        System.err.print(String.format("%s : %s", clazz.getName(), message));
        logger.log(Level.SEVERE, message);
    }

    @Override
    public void info(String message) {
        System.out.println(String.format("%s : %s", clazz.getName(), message));
        logger.log(Level.INFO, message);
    }

    @Override
    public void warning(String message) {
        System.err.print(String.format("%s : %s", clazz.getName(), message));
        logger.log(Level.WARNING, message);
    }

}
