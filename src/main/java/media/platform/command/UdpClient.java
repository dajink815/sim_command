package media.platform.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.*;

public class UdpClient {

    private Logger log = LoggerFactory.getLogger(UdpClient.class);
    private InetAddress ia;
    private DatagramSocket ds;
    private int serverPort;

    //서버 포트를 초기화 시에 미리 받고 한 곳으로만 전송할지 전송할 때마다 서버 포트를 받을지
    public UdpClient(String ip, int localPort, int serverPort){
        try {
            ia = InetAddress.getByName(ip);
            ds = new DatagramSocket(localPort);
            ds = new DatagramSocket();
            this.serverPort = serverPort;
        } catch (Exception e) {
            log.error("UdpClient Initialize Error ",e);
        }
    }

    public UdpClient(String ip, int serverPort){
        try {
            ia = InetAddress.getByName(ip);
            ds = new DatagramSocket();
            this.serverPort = serverPort;
        } catch (Exception e) {
            log.error("UdpClient Initialize Error ",e);
        }
    }

    public void send(String msg){
        byte buffer[] = msg.getBytes();
        DatagramPacket dp = new DatagramPacket(buffer, buffer.length, ia, serverPort);

        try {
            ds.send(dp);
            System.out.println("SEND MSG : [" + msg + "]");
            //log.debug("send msg [{}]", msg);
        } catch (IOException e) {
            log.error("UdpClient Send Error ",e);
        }
    }

    public void send(byte[] buffer){

        DatagramPacket dp = new DatagramPacket(buffer, buffer.length, ia, serverPort);

        try {
            ds.send(dp);
            log.debug("send msg [{}]", buffer);
        } catch (IOException e) {
            log.error("UdpClient Send Error ",e);
        }
    }
}

