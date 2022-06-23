package media.platform.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Optional;

public class Command {
    private static final Logger log = LoggerFactory.getLogger(Command.class);
    private final UdpClient udpClient;
    private boolean isQuit = false;

    public Command(String ip, int localPort, int serverPort){
        udpClient = new UdpClient(ip, localPort, serverPort);
    }

    public Command(String ip, int serverPort){
        udpClient = new UdpClient(ip, serverPort);
    }

/*    public void init(String ip, int localPort, int serverPort){
        udpClient = new UdpClient(ip, localPort, serverPort);
    }*/

    public void loop(){

        //start
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {

            log.error("Process is about to quit (Ctrl+C)");
            this.isQuit = true;

            this.stopService();
        })); //end addShutdownHook

        while (!isQuit) {
            try {
                inputCmd();
                Thread.sleep(1000);
            } catch (Exception e) {
                log.error("ServiceManager.loop",e);
            }
        } //end while
        log.error("Process End");
    }//end loop

    private boolean inputCmd(){
        System.out.print("INPUT COMMAND/FILE NAME : ");

        try {
            //user command
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String fileName = reader.readLine();

            ReadCmdFile readCmdFile = new ReadCmdFile();
            Optional.ofNullable(readCmdFile.read(fileName)).ifPresent(userCmd ->
                //udp 전송
                udpClient.send(userCmd)
            );

        } catch (IOException e) {
            System.out.println("User Command ReadLine Error " + e);
            //log.error("User Command ReadLine Error ", e);
        }

        return true;
    }

    private void stopService(){
    }

}
