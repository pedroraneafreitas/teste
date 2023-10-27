package com.example.rent.it.dto.avaliacao;
public class TransacaoAvaliacaoDto {
    private Long transacao;
    private Double nota;

    public Long getTransacao() {
        return transacao;
    }

    public void setTransacao(Long transacao) {
        this.transacao = transacao;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }
}
