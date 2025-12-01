package com.ezlearning.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

/**
 * Application entry point.
 * Enforces property-based server binding and ignores CLI property injection.
 * Binds to 0.0.0.0:3001 by default (or uses SERVER_ADDRESS and SERVER_PORT if set).
 */
@SpringBootApplication
public class PlatformApplication {

    // PUBLIC_INTERFACE
    public static void main(String[] args) {
        /*
         Configure default properties so that 0.0.0.0:3001 are used when nothing else is provided.
         Use SERVER_ADDRESS and SERVER_PORT environment variables if present.
         Also explicitly disable command-line property injection so any CLI flags like --server.* are ignored.
        */
        Map<String, Object> defaults = new HashMap<>();
        String envAddress = System.getenv("SERVER_ADDRESS");
        String envPort = System.getenv("PORT");
        if (envPort == null || envPort.isBlank()) {
            envPort = System.getenv("SERVER_PORT");
        }

        defaults.put("server.address", (envAddress == null || envAddress.isBlank()) ? "0.0.0.0" : envAddress);
        defaults.put("server.port", (envPort == null || envPort.isBlank()) ? "3001" : envPort);
        defaults.put("spring.main.add-command-line-properties", "false");

        SpringApplication app = new SpringApplication(PlatformApplication.class);
        app.setAddCommandLineProperties(false);
        app.setDefaultProperties(defaults);
        // Run with args for standard Boot behavior; CLI property injection remains disabled via setAddCommandLineProperties(false).
        app.run(args);
    }
}
