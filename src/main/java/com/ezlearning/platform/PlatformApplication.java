package com.ezlearning.platform;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * Application entry point.
 * Enforces property-based server binding, ignoring CLI property injection and ensuring
 * the app binds to 0.0.0.0:3001 by default or to values from SERVER_ADDRESS and SERVER_PORT.
 */
@SpringBootApplication
public class PlatformApplication {

    // PUBLIC_INTERFACE
    public static void main(String[] args) {
        /*
         Enforce property-based binding for server address/port.
         Provide defaultProperties so that 0.0.0.0:3001 are used when nothing else is provided.
         Use SERVER_ADDRESS and SERVER_PORT environment variables if present.
         Also disable command-line property injection so any CLI flags like --server.* are ignored.
        */
        Map<String, Object> defaults = new HashMap<>();
        String envAddress = System.getenv("SERVER_ADDRESS");
        String envPort = System.getenv("SERVER_PORT");

        defaults.put("server.address", (envAddress == null || envAddress.isBlank()) ? "0.0.0.0" : envAddress);
        defaults.put("server.port", (envPort == null || envPort.isBlank()) ? "3001" : envPort);
        defaults.put("spring.main.add-command-line-properties", "false");

        new SpringApplicationBuilder(PlatformApplication.class)
                .properties(defaults)
                .run(args);
    }
}
