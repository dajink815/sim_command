package media.platform.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CmdMain {

    public static void main(String[] args){
        Logger logger = LoggerFactory.getLogger(CmdMain.class);
        String udpIp = "127.0.0.1";

        if(args.length != 1) {
            logger.error("Argument error");
        }

/*        int localPort = Integer.parseInt(args[0]);
        int serverPort = Integer.parseInt(args[1]);*/

        int serverPort = Integer.parseInt(args[0]);

        /*Command command = new Command(udpIp, localPort, serverPort);*/
        Command command = new Command(udpIp, serverPort);
        command.loop();
    }
}
