package com.gersen.bean;


import javax.persistence.*;

@Entity
@Table(name = "adress")
public class Adress {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "type")
    private String type;
    @Column(name = "numero")
    private String numero;
    @Column(name = "rue")
    private String rue;
    @Column(name = "codePostal")
    private String codePostal;
    @Column(name = "ville")
    private String ville;
    @Column(name = "pays")
    private String pays;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public User getUser() {
        return user;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Adress() {
    }

    public Adress(Long id, String type, String numero, String rue, String codePostal, String ville, String pays,User user) {
        this.id = id;
        this.type = type;
        this.numero = numero;
        this.rue = rue;
        this.codePostal = codePostal;
        this.ville = ville;
        this.pays = pays;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Adress{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", numero='" + numero + '\'' +
                ", rue='" + rue + '\'' +
                ", codePostal='" + codePostal + '\'' +
                ", ville='" + ville + '\'' +
                ", pays='" + pays + '\'' +
                ", user='" + user + '\'' +
                '}';
    }
}
