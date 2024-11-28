package logger;

import java.io.Serializable;
import javax.swing.JOptionPane;

/**
 *
 * @author
 */
class PatolliLoggerProxy implements IPatolliLogger, Serializable {

    private static IPatolliLogger instance;
    private IPatolliLogger logger;
    private Class clazz;

    private PatolliLoggerProxy(Class clazz) {
        this.clazz = clazz;
        this.logger = PatolliLoggerImpl.getInstance(clazz);
    }

    public static IPatolliLogger getInstance(Class clazz) {
        return instance != null ? instance : (instance = new PatolliLoggerProxy(clazz));
    }

    @Override
    public void log(String message) {
        JOptionPane.showMessageDialog(null, message, "success", JOptionPane.DEFAULT_OPTION);
        logger.log(message);
    }

    @Override
    public void error(String message) {
        JOptionPane.showMessageDialog(null, message, "error", JOptionPane.ERROR_MESSAGE);
        logger.error(message);
    }

    @Override
    public void info(String message) {
        JOptionPane.showMessageDialog(null, message, "info", JOptionPane.INFORMATION_MESSAGE);
        logger.info(message);
    }

    @Override
    public void warning(String message) {
        JOptionPane.showMessageDialog(null, message, "warning", JOptionPane.WARNING_MESSAGE);
        logger.warning(message);
    }

}
