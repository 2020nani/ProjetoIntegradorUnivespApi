package br.com.rainmonitoring.areasrisco;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class AreaRiscoForm {

    @NotBlank
    private String nome;

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;

    @NotNull
    private Integer indicePluvial;

    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusRisco statusRisco;


    public String getNome() {
        return nome;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Integer getIndicePluvial() {
        return indicePluvial;
    }

    public StatusRisco getStatusRisco() {
        return statusRisco;
    }

    public AreaRiscoForm(String nome, Double latitude, Double longitude, Integer indicePluvial, StatusRisco statusRisco) {
        this.nome = nome;
        this.latitude = latitude;
        this.longitude = longitude;
        this.indicePluvial = indicePluvial;
        this.statusRisco = statusRisco;
    }

    public AreaRisco converte() {
        return new AreaRisco(nome,latitude,longitude, indicePluvial, statusRisco);
    }
}
