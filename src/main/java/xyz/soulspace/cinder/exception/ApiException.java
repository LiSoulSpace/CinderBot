package xyz.soulspace.cinder.exception;

/**
 * 自定义API异常
 * Created by macro on 2020/2/27.
 */
public class ApiException extends RuntimeException {
    private String message;

    public ApiException(String message) {
        super(message);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
