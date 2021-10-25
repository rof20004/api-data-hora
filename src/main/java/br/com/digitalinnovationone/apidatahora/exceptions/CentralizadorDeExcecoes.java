package br.com.digitalinnovationone.apidatahora.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.zone.ZoneRulesException;

@RestControllerAdvice
public class CentralizadorDeExcecoes {

    private Erro buildErro(RuntimeException e) {
        return Erro.newBuilder()
                .withDataHoraErro(LocalDateTime.now())
                .withMensagem(e.getMessage());
    }

    @ExceptionHandler(ZoneRulesException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Erro handleZoneRulesException(ZoneRulesException e) {
        return buildErro(new RuntimeException("por favor, forneça um timezone válido"));
    }

    @ExceptionHandler(DateTimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Erro handleZoneRulesException(DateTimeException e) {
        return buildErro(new RuntimeException("timezone com formato inválido"));
    }

}

class Erro {

    private LocalDateTime dataHoraErro;
    private String mensagem;

    public Erro() {}

    public static Erro newBuilder() {
        return new Erro();
    }

    public Erro withDataHoraErro(final LocalDateTime dataHoraErro) {
        this.dataHoraErro = dataHoraErro;
        return this;
    }

    public Erro withMensagem(final String mensagem) {
        this.mensagem = mensagem;
        return this;
    }

    public LocalDateTime getDataHoraErro() {
        return dataHoraErro;
    }

    public void setDataHoraErro(LocalDateTime dataHoraErro) {
        this.dataHoraErro = dataHoraErro;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

}
