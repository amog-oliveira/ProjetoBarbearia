/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Horarios;

/**
 *
 * @author Amanda de Oliveira
 */
public class Horarios {
   private Integer seqHorarios;
   private String dia;
   private String horario;
   private String descricao;
   private Integer idCliente;

    public Integer getSeqHorarios() {
        return seqHorarios;
    }

    public void setSeqHorarios(Integer seqHorarios) {
        this.seqHorarios = seqHorarios;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }
   
   
}
