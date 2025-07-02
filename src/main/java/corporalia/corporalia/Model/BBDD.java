package corporalia.corporalia.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "bbdd")
@Getter
@Setter
public class BBDD {

    @Id
    private String codigo;

    private String club;

    private Integer cp;

    private String direccion;

    private String municipio;

    @Column(name = "observaciones")
    private String observacion;

    private String provincia;

    private String campania;

    @Column(name = "fecha_inicio", nullable = true)
    private LocalDateTime fechaInicio;

    @Column(name = "fecha_fin", nullable = true)
    private LocalDateTime fechaFin;

    @Column(name = "color")
    private String color; // ✅ NUEVO CAMPO

    public BBDD() {}

    public BBDD(corporalia.corporalia.DTO.BBDDDTO bbddDTO) {
        this.codigo = bbddDTO.getCodigo();
        this.club = bbddDTO.getClub();
        this.cp = bbddDTO.getCp();
        this.direccion = bbddDTO.getDireccion();
        this.municipio = bbddDTO.getMunicipio();
        this.observacion = bbddDTO.getObservacion();
        this.provincia = bbddDTO.getProvincia();
        this.campania = bbddDTO.getCampania();
        this.color = bbddDTO.getColor(); // ✅ ASIGNACIÓN DEL NUEVO CAMPO

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        if (bbddDTO.getFechaInicio() != null && !bbddDTO.getFechaInicio().isEmpty()) {
            this.fechaInicio = LocalDateTime.parse(bbddDTO.getFechaInicio(), formatter);
        } else {
            this.fechaInicio = null;
        }

        if (bbddDTO.getFechaFin() != null && !bbddDTO.getFechaFin().isEmpty()) {
            this.fechaFin = LocalDateTime.parse(bbddDTO.getFechaFin(), formatter);
        } else {
            this.fechaFin = null;
        }
    }
}
