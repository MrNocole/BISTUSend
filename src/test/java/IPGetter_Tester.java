import java.net.SocketException;

public class IPGetter_Tester {
    public static void main(String[] args) throws SocketException {
        IPGetter ipGetter = new IPGetter();
        System.out.println(ipGetter.getIP());
        System.out.println(ipGetter.getV4IP());
    }
}
