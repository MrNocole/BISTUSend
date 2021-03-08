import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
/*
    @author : zhanGTao
    @version : 1.0
 */
//TODO tcp握手
public class TCPConnector {
    private Socket socket;
    public TCPConnector(String IP , String Port){
        try {
            socket = new Socket(IP,Integer.getInteger(Port));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
