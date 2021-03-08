import java.net.SocketException;
/*
    @author : zhanGTao
    @version : 1.0
 */
public class IPGetter_Tester {
    public static void main(String[] args) throws SocketException, InterruptedException {
        IPGetter ipGetter = new IPGetter();
        ipGetter.getIP();
        System.out.println(ipGetter.LocalIp);
        System.out.println(ipGetter.OtherSideIp);
        LAN_Tool lanTool = new LAN_Tool(ipGetter.LocalIp);
        lanTool.PingAll();
        for (int i = 0 ; i < LAN_Tool.ping.size() ; i ++){
            System.out.println(LAN_Tool.ping.get(i));
        }
    }
}
