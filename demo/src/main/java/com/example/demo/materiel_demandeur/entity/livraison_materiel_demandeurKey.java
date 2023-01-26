package com.example.demo.materiel_demandeur.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class livraison_materiel_demandeurKey implements Serializable {

    @Column(name="id_demandeur")
    private int demandeurId;

    @Column(name="id_materiel")
    private int matrielId;

    public int getDemandeurId() {
        return demandeurId;
    }

    public void setDemandeurId(int demandeurId) {
        this.demandeurId = demandeurId;
    }

    public int getMatrielId() {
        return matrielId;
    }

    public void setMatrielId(int matrielId) {
        this.matrielId = matrielId;
    }
}
