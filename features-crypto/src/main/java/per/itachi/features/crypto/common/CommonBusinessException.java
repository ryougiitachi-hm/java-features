package per.itachi.features.crypto.common;

/**
 * placeholder
 * */
public class CommonBusinessException extends RuntimeException {

    public CommonBusinessException(String message) {
        super(message);
    }

    public CommonBusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommonBusinessException(Throwable cause) {
        super(cause);
    }
}
