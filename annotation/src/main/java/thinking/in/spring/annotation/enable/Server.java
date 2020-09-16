package thinking.in.spring.annotation.enable;

/**
 * 定义服务接口
 */
public interface Server {

    void start();

    void stop();

    enum Type{
        HTTP,
        FTP
    }
}
