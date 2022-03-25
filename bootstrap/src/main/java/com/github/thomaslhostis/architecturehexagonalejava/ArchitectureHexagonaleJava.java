package com.github.thomaslhostis.architecturehexagonalejava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Avec cette annotation, Spring Boot scanne toutes les classes qui se
// trouvent dans le même package et dans tous ses sous-packages. Cela
// inclut les autres modules. Pour cette raison, il ne faut pas mettre
// cette classe dans un package "bootstrap" par exemple.
@SpringBootApplication
public class ArchitectureHexagonaleJava {

    public static void main(String[] args) {
        SpringApplication.run(ArchitectureHexagonaleJava.class, args);
    }
}
