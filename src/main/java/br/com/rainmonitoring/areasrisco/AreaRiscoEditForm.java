package br.com.rainmonitoring.areasrisco;


import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class AreaRiscoEditForm {

    private String nome;

    private Double latitude;

    private Double longitude;

    private Integer indicePluvial;

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


    public AreaRisco update(Long areaId, AreaRiscoRepository areaRiscoRepository) {
        AreaRisco areaRisco = areaRiscoRepository.findById(areaId)
                .orElseThrow(() -> new IllegalArgumentException("Nao ha area de risco cadastrada id: " + areaId));
        AreaRisco areaRiscoUpdate = new AreaRisco(
                nome!= null ? nome : areaRisco.getNome(),
                latitude!=null ? latitude : areaRisco.getLatitude(),
                longitude!=null ? longitude : areaRisco.getLongitude(),
                indicePluvial!=null ? indicePluvial : areaRisco.getIndicePluvial(),
                statusRisco!=null ? statusRisco : areaRisco.getStatusRisco()
        );
        return areaRiscoUpdate;
    }
}
