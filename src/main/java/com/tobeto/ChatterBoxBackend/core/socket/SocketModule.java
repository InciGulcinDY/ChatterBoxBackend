package com.tobeto.ChatterBoxBackend.core.socket;


import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.tobeto.ChatterBoxBackend.services.dtos.message.MessageModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SocketModule {

    private final SocketIOServer socketIOServer;

    public SocketModule(SocketIOServer socketIOServer) {
        this.socketIOServer = socketIOServer;
        socketIOServer.addConnectListener(onConnected());
        socketIOServer.addDisconnectListener(onDisconnected());
        socketIOServer.addEventListener("send_message", MessageModel.class, onMessageReceived());
    }

    private ConnectListener onConnected(){
        return socketIOClient -> {
            String room = socketIOClient.getHandshakeData().getSingleUrlParam("room");
            socketIOClient.joinRoom(room);
            socketIOClient.getNamespace().getRoomOperations(room)
                    .sendEvent("get_message",
                            String.format("%s connected to -> %s", socketIOClient.getSessionId(), room));
            log.info(String.format("SocketID: %s connected", socketIOClient.getSessionId().toString()));
        };
    }

    private DisconnectListener onDisconnected(){
        return socketIOClient -> {
            String room = socketIOClient.getHandshakeData().getSingleUrlParam("room");
            socketIOClient.getNamespace().getRoomOperations(room)
                    .sendEvent("get_message",
                            String.format("%s disconnected to -> %s", socketIOClient.getSessionId(), room));
            log.info(String.format("SocketID: %s disconnected", socketIOClient.getSessionId().toString()));
        };
    }

    private DataListener<MessageModel> onMessageReceived(){
        return (senderClient, data, ackSender)->{
          log.info(String.format("%s -> %s", senderClient.getSessionId(), data.getContent()));
          String room = senderClient.getHandshakeData().getSingleUrlParam("room");
          senderClient.getNamespace().getRoomOperations(room).getClients().forEach(
                  socketIOClient -> {
                      //    Preventing getting the sending message back
                      if(!socketIOClient.getSessionId().equals(senderClient.getSessionId())){
                          socketIOClient.sendEvent("get_message", data.getContent());
                      }
                  }
          );
        };
    }
}
