package start.entity;

/**
 * @Author: Jason
 * @Create: 2020/10/12  20:18
 * @Description 异常类
 */
public class UnableToAquireLockException extends RuntimeException{

    public UnableToAquireLockException(){};

    public UnableToAquireLockException(String message){
        super(message);
    }

    public UnableToAquireLockException(String message, Throwable cause){
        super(message,cause);
    }
}
