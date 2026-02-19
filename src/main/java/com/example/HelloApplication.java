package com.example;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

/**
 * Registers JAX-RS under /api.
 * Looks empty. Is empty. Still vital.
 * Like a manager.
 */
@ApplicationPath("/api")
public class HelloApplication extends Application {
}
