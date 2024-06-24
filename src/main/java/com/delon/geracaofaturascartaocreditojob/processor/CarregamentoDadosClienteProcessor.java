package com.delon.geracaofaturascartaocreditojob.processor;

import com.delon.geracaofaturascartaocreditojob.domain.Cliente;
import com.delon.geracaofaturascartaocreditojob.domain.FaturaCartaoCredito;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CarregamentoDadosClienteProcessor implements ItemProcessor<FaturaCartaoCredito, FaturaCartaoCredito> {

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public FaturaCartaoCredito process(FaturaCartaoCredito faturaCartaoCredito) {
        String uri = String.format("https://my-json-server.typicode.com/giuliana-bezerra/demo/profile/%d", faturaCartaoCredito.getCliente().id());
        ResponseEntity<Cliente> response = restTemplate.getForEntity(uri, Cliente.class);

        if (response.getStatusCode() != HttpStatus.OK) {
            throw new ValidationException("Failed to retrieve client information");
        }

        faturaCartaoCredito.setCliente(response.getBody());
        return faturaCartaoCredito;
    }
}
