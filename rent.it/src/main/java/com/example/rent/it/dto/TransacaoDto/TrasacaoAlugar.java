package com.example.rent.it.dto.TransacaoDto;

import java.util.Date;

public class TrasacaoAlugar {
    private Long cartaoId;
    private String cpf;
    private Date dtFim;
    private Date dtInicio;
    private Long itemId;
    private Long idUso;
    private double valorFinal;

    public double getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(double valorFinal) {
        this.valorFinal = valorFinal;
    }

    public Long getCartaoId() {
        return cartaoId;
    }

    public void setCartaoId(Long cartaoId) {
        this.cartaoId = cartaoId;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDtFim() {
        return dtFim;
    }

    public void setDtFim(Date dtFim) {
        this.dtFim = dtFim;
    }

    public Date getDtInicio() {
        return dtInicio;
    }

    public void setDtInicio(Date dtInicio) {
        this.dtInicio = dtInicio;
    }



    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getIdUso() {
        return idUso;
    }

    public void setIdUso(Long idUso) {
        this.idUso = idUso;
    }
}
