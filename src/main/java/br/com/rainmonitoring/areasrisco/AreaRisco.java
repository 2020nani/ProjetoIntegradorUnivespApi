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


    public Long getId() {
        return id;
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

    @Deprecated
    public AreaRisco() {
    }

    public AreaRisco(String name, Double latitude, Double longitude) {
        this.nome = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
