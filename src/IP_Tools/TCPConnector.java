import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

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
