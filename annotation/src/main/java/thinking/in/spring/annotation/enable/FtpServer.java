package thinking.in.spring.annotation.enable;

public class FtpServer implements Server {
    @Override
    public void start() {
        System.out.println("Ftp服务启动中。。。");
    }

    @Override
    public void stop() {
        System.out.println("Ftp服务停止中。。。");
    }
}
