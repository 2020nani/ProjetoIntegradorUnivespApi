package br.com.rainmonitoring.areasrisco;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class AreaRisco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    private String nome;

    @NotNull
    private Double latitude;

    @NotNull Double longitude;

    @NotNull Integer indicePluvial;

    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusRisco statusRisco;

    private String nivelRisco;


    public Long getId() {
        return id;
    }

    public void setIndicePluvial(Integer indicePluvial) {
        this.indicePluvial = indicePluvial;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getNivelRisco() {
        return nivelRisco;
    }

    @Deprecated
    public AreaRisco() {
    }

    public AreaRisco(String nome, Double latitude, Double longitude, Integer indicePluvial, StatusRisco statusRisco) {
        this.nome = nome;
        this.latitude = latitude;
        this.longitude = longitude;
        this.indicePluvial = indicePluvial;
        this.statusRisco = statusRisco;
        this.nivelRisco = statusRisco.avaliarRisco(indicePluvial);
    }
}
