package corporalia.corporalia.Model;

import corporalia.corporalia.DTO.MobiliarioDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "mobiliario")
@Getter
@Setter
public class Mobiliario {
    @Id
    private String codigo;

    private String campania;
    private String municipio;
    private String provincia;
    private String cp;
    private String observaciones;

    @Column(name = "fecha_inicio", nullable = true)
    private LocalDateTime fechaInicio;

    @Column(name = "fecha_fin", nullable = true)
    private LocalDateTime fechaFin;

    @Column(name = "color")
    private String color;

    public Mobiliario() {}

    public Mobiliario(MobiliarioDTO mobiliarioDTO) {
        this.codigo = mobiliarioDTO.getCodigo();
        this.campania = mobiliarioDTO.getCampania();
        this.municipio = mobiliarioDTO.getMunicipio();
        this.provincia = mobiliarioDTO.getProvincia();
        this.cp = mobiliarioDTO.getCp();
        this.observaciones = mobiliarioDTO.getObservaciones();
        this.fechaInicio = mobiliarioDTO.getFechaInicio();  // ✅ Directamente como LocalDateTime
        this.fechaFin = mobiliarioDTO.getFechaFin();
        this.color = mobiliarioDTO.getColor();
// ✅ Directamente como LocalDateTime
    }
}



