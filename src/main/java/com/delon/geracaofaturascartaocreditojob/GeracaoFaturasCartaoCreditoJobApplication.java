package com.delon.geracaofaturascartaocreditojob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GeracaoFaturasCartaoCreditoJobApplication {

    public static void main(String[] args) {
        var context = SpringApplication.run(GeracaoFaturasCartaoCreditoJobApplication.class, args);
        context.close();
    }

}
