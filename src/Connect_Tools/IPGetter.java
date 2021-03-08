import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
    @author : zhanGTao
    @version : 1.0
 */
//TODO 错误处理
//testversion
public class IPGetter{
//    InetAddress LocalIp;
    InetAddress OtherSideIp;
    String LocalIp;
    public IPGetter(){}
    // 扫描外网ip，如果没有配置则返回本地ip
    public void getIP() throws SocketException {
        String localIp = null;
        String netIp = null;
        Enumeration<NetworkInterface> networkInterfaceEnumeration
                = NetworkInterface.getNetworkInterfaces();
        InetAddress ip;
        boolean isNet = false;
        while (networkInterfaceEnumeration.hasMoreElements() && !isNet){
            NetworkInterface NI = networkInterfaceEnumeration.nextElement();
            Enumeration<InetAddress> addressEnumeration = NI.getInetAddresses();
            while (addressEnumeration.hasMoreElements()){
                ip = addressEnumeration.nextElement();
                if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress() &&
                        !ip.getHostAddress().contains(":")){
                    netIp = ip.getHostAddress();
                    isNet = true;
                    break;
                } else if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress()
                                && !ip.getHostAddress().contains(":")){
                    localIp = ip.getHostAddress();
                }
            }
        }
        if (netIp != null && !netIp.equals(""))LocalIp = netIp;
        else LocalIp = localIp;
    }

    // 返回实际ip
    // 暂时不用这个功能
    /*
    public String getV4IP(){
        String ip = null;
        String chinaZ = "http://ip.chinaz.com";
        StringBuilder inputLine = new StringBuilder();
        String read;
        URL url;
        HttpURLConnection urlConnection;
        BufferedReader in = null;
        try{
            url = new URL(chinaZ);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedReader(new InputStreamReader(
                    urlConnection.getInputStream(), StandardCharsets.UTF_8));
            while ((read = in.readLine()) != null){
                inputLine.append(read).append("\r\n");
            }

        } catch (MalformedURLException e) {
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
        Pattern pattern = Pattern.compile("<dd class=\"fz24\">(.*?)</dd>");
        Matcher matcher = pattern.matcher(inputLine.toString());
        if (matcher.find()){
            ip = matcher.group(1);
        }
        return ip;
    }
    */
}