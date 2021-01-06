import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IPGetter{
    public String getIP() throws SocketException{
        // 扫描外网ip，如果没有配置则返回本地ip
        String localIp = null;
        String netIp = null;
        Enumeration<NetworkInterface> networkInterfaceEnumeration
                = NetworkInterface.getNetworkInterfaces();
        InetAddress ip = null;
        boolean isNet = false;
        while (networkInterfaceEnumeration.hasMoreElements() && !isNet){
            NetworkInterface NI = networkInterfaceEnumeration.nextElement();
            Enumeration<InetAddress> addressEnumeration = NI.getInetAddresses();
            while (addressEnumeration.hasMoreElements()){
                ip = addressEnumeration.nextElement();
                if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress() &&
                                ip.getHostAddress().indexOf(":")==-1){
                    netIp = ip.getHostAddress();
                    isNet = true;
                    break;
                } else if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress()
                                && ip.getHostAddress().indexOf(":") == -1){
                    localIp = ip.getHostAddress();
                }
            }
        }
        if (netIp != null && !netIp.equals(""))return netIp;
        else return localIp;
    }
        //返回实际ip
    public String getV4IP(){
        String ip = null;
        String chinaZ = "http://ip.chinaz.com";
        StringBuilder inputLine = new StringBuilder();
        String read = null;
        URL url = null;
        HttpURLConnection urlConnection = null;
        BufferedReader in = null;
        try{
            url = new URL(chinaZ);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedReader(new InputStreamReader(
                    urlConnection.getInputStream(),"UTF-8"));
            while ((read = in.readLine()) != null){
                inputLine.append(read + "\r\n");
            }

        } catch (MalformedURLException e) {
            System.out.println("URL created error !!!");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null){
                try{
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Pattern pattern = Pattern.compile("\\<dd class\\=\"fz24\">(.*?)\\<\\/dd>");
        Matcher matcher = pattern.matcher(inputLine.toString());
        if (matcher.find()){
            String ipAddress = matcher.group(1);
            ip = ipAddress;
        }
        return ip;
    }
}