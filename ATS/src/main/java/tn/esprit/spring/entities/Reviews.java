package tn.esprit.spring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Reviews implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idreview;

    private int value;
    private String content;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idproduct", nullable = false)
    private Produit produit;


    public int getIdreview() {
        return idreview;
    }


    public void setIdreview(int idreview) {
        this.idreview = idreview;
    }


    public int getValue() {
        return value;
    }


    public void setValue(int value) {
        this.value = value;
    }


    public String getContent() {
        return content;
    }


    public void setContent(String content) {
        this.content = content;
    }





}
