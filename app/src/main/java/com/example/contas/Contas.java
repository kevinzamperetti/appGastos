package com.example.contas;

import java.io.Serializable;
import java.util.Date;

public class Contas implements Serializable {
    private int _id;
    private String nome;
    private String data;
    private String tipo;
    private String valor;

    public Contas(String nome, String data, String tipo, String valor) {
        this.nome = nome;
        this.data= data;
        this.tipo = tipo;
        this.valor = valor;
    }

    public Contas(int id, String nome, String data, String tipo, String valor) {
        this.nome = nome;
        this.data= data;
        this.tipo = tipo;
        this.valor = valor;
        this._id = id;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public int getId(){
        return _id;
    }

    @Override
    public String toString() {
        return "Contas{" +
                "nome='" + nome + '\'' +
                ", data=" + data +
                ", tipo='" + tipo + '\'' +
                ", valor='" + valor + '\'' +
                '}';
    }
}
