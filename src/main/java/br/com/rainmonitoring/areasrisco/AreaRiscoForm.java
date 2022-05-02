package br.com.rainmonitoring.areasrisco;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class AreaRiscoForm {

    @NotBlank
    private String nome;

    @NotNull
    private Double latitude;

    @NotNull Double longitude;


    public String getNome() {
        return nome;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public AreaRiscoForm(String name, Double latitude, Double longitude) {
        this.nome = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public AreaRisco converte() {
        return new AreaRisco(nome,latitude,longitude);
    }
}
