package com.tobeto.ChatterBoxBackend.core.configurations;

import com.corundumstudio.socketio.SocketIOServer;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StartupConfiguration implements CommandLineRunner {

    private final SocketIOServer socketIOServer;
    @Override
    public void run(String... args) throws Exception {
        socketIOServer.start();

       /* Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Stopping SocketIOServer...");
            socketIOServer.stop();
        }));*/

    }
}
