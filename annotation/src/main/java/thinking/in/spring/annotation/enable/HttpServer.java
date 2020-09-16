package thinking.in.spring.annotation.enable;

public class HttpServer implements Server {
    @Override
    public void start() {
        System.out.println("Http 服务启动中。。。");
    }

    @Override
    public void stop() {
        System.out.println("Http 服务停止中。。。");
    }
}
