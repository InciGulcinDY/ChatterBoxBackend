package com.tobeto.ChatterBoxBackend.core.configurations;

import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SocketIOConfiguration {

    @Value("${socket-server.host}")
    private String host;
    @Value("${socket-server.port}")
    private int port;

    @Bean
    public SocketIOServer socketIOServer(){
        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
        config.setHostname(host);
        config.setPort(port);
        //config.setPingTimeout(60000); // 60 seconds
        //config.setPingInterval(25000); // 25 seconds

        return new SocketIOServer(config);
    }
}
