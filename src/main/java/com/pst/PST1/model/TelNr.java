package com.pst.PST1.model;


import javax.persistence.*;

@Entity
public class TelNr implements Comparable<TelNr>{
    @Id
    private Long id;
    @Column(unique=true)
    private String telNr;
    private Long userId;

    public TelNr(){}

    public TelNr(String telNr, Long userId){
        super();
        if (telNr == null
                || telNr.isEmpty()
                || telNr.isBlank()
                || userId < 0
                || userId == null)
            throw new NullPointerException();
        if (!telNr.matches("(86|\\+3706)\\d{3}\\d{4}")) throw new IllegalArgumentException();

        this.telNr = telNr;
        this.userId = userId;
    }

    public TelNr(Long id, String telNr, Long userId){
        super();
        if (id < 0
                || id == null
                || telNr == null
                || telNr.isEmpty()
                || telNr.isBlank()
                || userId < 0
                || userId == null)
            throw new NullPointerException();
        if (!telNr.matches("(86|\\+3706)\\d{3}\\d{4}")) throw new IllegalArgumentException();

        this.id = id;
        this.telNr = telNr;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Telefono numeris [id=" + id + ", numeris =" + telNr + ", naudotojo numeris =" + userId + "]";
    }

    @Override
    public int compareTo(TelNr o) {
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
        TelNr other = (TelNr) obj;

        if(id == other.id
                && telNr == other.telNr
                && userId == other.userId) return true;
        return false;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        if (id == null || id < 0) throw new NullPointerException();
        this.id = id;
    }

    public String getTelNr() {
        return telNr;
    }

    public void setTelNr(String telNr) {
        if (telNr == null || telNr.isEmpty() || telNr.isBlank()) throw new NullPointerException();
        else if (!telNr.matches("(86|\\+3706)\\d{3}\\d{4}")) throw new IllegalArgumentException();
        this.telNr = telNr;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        if (userId == null || userId < 0) throw new NullPointerException();
        this.userId = userId;
    }
}