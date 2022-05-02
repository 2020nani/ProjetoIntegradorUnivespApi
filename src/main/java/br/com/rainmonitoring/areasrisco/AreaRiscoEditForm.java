package br.com.rainmonitoring.areasrisco;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class AreaRiscoEditForm {

    private String nome;

    private Double latitude;

    private Double longitude;


    public String getName() {
        return nome;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public AreaRiscoEditForm(String nome, Double latitude, Double longitude) {
        this.nome = nome;
        this.latitude = latitude;
        this.longitude = longitude;
    }


    public AreaRisco update(Long areaId, AreaRiscoRepository areaRiscoRepository) {
        AreaRisco areaRisco = areaRiscoRepository.findById(areaId)
                .orElseThrow(() -> new IllegalArgumentException("Nao ha area de risco cadastrada id: " + areaId));
        AreaRisco areaRiscoUpdate = new AreaRisco(
                nome!= null ? nome : areaRisco.getNome(),
                latitude!=null ? latitude : areaRisco.getLatitude(),
                longitude!=null ? longitude : areaRisco.getLongitude()
        );
        return areaRiscoUpdate;
    }
}
