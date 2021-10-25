package br.com.digitalinnovationone.apidatahora.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;

@RestController
@RequestMapping("/data-hora") // http://localhost:8080/data-hora
public class DataHoraController {

    @GetMapping
    public Map<String, Object> retornarDataHora(
            @RequestParam(value = "timezone", defaultValue = "America/Sao_Paulo") String timezone
    ) {
        var zone = ZoneId.of(timezone);
        var dataHora = LocalDateTime.ofInstant(Instant.now(), zone);
        return Map.of("dataHora", dataHora);
    }

}
