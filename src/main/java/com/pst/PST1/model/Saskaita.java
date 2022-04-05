package com.pst.PST1.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Saskaita implements Comparable<Saskaita> {

    @Id
    private Long id;
    private Long telNrId;
    private int menuo;
    private float suma;

    private String telNr;

    public Saskaita(){}

    public Saskaita(Long id, Long telNrId, int menuo, float suma){
        if (id == null
                || id < 0
                || telNrId == null
                || telNrId < 0
                || !(menuo >= 1 && menuo <=12)
                || suma < 0)
            throw new NullPointerException();

        this.id = id;
        this.telNrId = telNrId;
        this.menuo = menuo;
        this.suma = suma;
    }

    public Saskaita(Long id, Long telNrId, int menuo, float suma, String telNr){
        this.id = id;
        this.telNrId = telNrId;
        this.menuo = menuo;
        this.suma = suma;
        this.telNr = telNr;
    }

    @Override
    public String toString() {
        return "Saskaita [id=" + id + ", Telefono Nr Id =" + telNrId + ", menuo =" + menuo + ", suma =" + suma + "]";
    }

    @Override
    public int compareTo(Saskaita o) {
        return Long.compare(this.id, o.id);
    }

    @Override
    public int hashCode() {
        final long prime = 31;
        long result = 1;
        result = prime * result + id;
        return getId().hashCode(result);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Saskaita other = (Saskaita) obj;

        if(id == other.id
                && telNrId == other.telNrId
                && menuo == other.menuo
                && suma == other.suma) return true;

        return false;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        if (id == null || id < 0) throw new NullPointerException();
        this.id = id;
    }

    public Long getTelNrId() {
        return telNrId;
    }

    public void setTelNrId(Long telNrId) {
        if (telNrId == null || telNrId < 0) throw new NullPointerException();
        this.telNrId = telNrId;
    }

    public int getMenuo() {
        return menuo;
    }

    public void setMenuo(int menuo) {
        if (!(menuo >= 1 && menuo <=12)) throw new IllegalArgumentException();
        this.menuo = menuo;
    }

    public float getSuma() {
        return suma;
    }

    public void setSuma(float suma) {
        if (suma < 0) throw new IllegalArgumentException();
        this.suma = suma;
    }

    public String getTelNr() {
        return telNr;
    }

    public void setTelNr(String telNr) {
        this.telNr = telNr;
    }
}