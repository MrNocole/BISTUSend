import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
/*
    @author : zhanGTao
    @version : 1.0
 */
//NOT TEST ON WINDOWS YET!!
//ONLY ON LINUX!!
public class LAN_Tool {
//    static public List<String> ping = new ArrayList<>();
    static public List<String> ping = new Vector<>();
    private int threadCnt = 0;
    private String local ;
    final static int MAX_THREAD = 30;
    public LAN_Tool(){
        try {
            local = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
    public LAN_Tool(String local){
        this.local = local;
    }
    //并发中间件，控制线程数量、并发启动ping
    public void Ping(String ip) throws InterruptedException {
        while (threadCnt>MAX_THREAD) Thread.sleep(5);
        threadCnt++;
        doPing ping = new doPing(ip);
        ping.start();
    }
    //获取局域网内全部在线ip，代价比想象大，有待改进
    public void PingAll() throws InterruptedException {
        String hostIp;
        hostIp = local;
        int index = hostIp.lastIndexOf('.');
        String part = hostIp.substring(0,index+1);
        for (int i = 1 ; i <= 255 ; i ++){
            String ip = part+i;
            Ping(ip);
//            System.out.println(i);
        }

        while (threadCnt>0)Thread.sleep(50);
        System.out.println("done!");
    }
    //并发内部类
    class doPing extends Thread{
        public String ip;
        public doPing(String ip){
            this.ip = ip;
        }
        @Override
        public void run() {
            try{
                Process process = Runtime.getRuntime().exec("ping -s 0 -c 3 " + ip);
                InputStreamReader isr = new InputStreamReader(process.getInputStream());
                LineNumberReader inr = new LineNumberReader(isr);
                inr.readLine();
                String ret = inr.readLine();
                if (ret != null && !ret.equals("") && ret.charAt(0) == '8'){
                    ping.add(ret.substring(8,ret.lastIndexOf(':')));
                }
                threadCnt--;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
