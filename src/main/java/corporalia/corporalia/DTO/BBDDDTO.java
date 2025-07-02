package corporalia.corporalia.DTO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import com.fasterxml.jackson.annotation.JsonFormat;
import corporalia.corporalia.Model.BBDD;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BBDDDTO {

    private String codigo;
    private String club;
    private Integer cp;
    private String direccion;
    private String municipio;
    private String observacion;
    private String provincia;
    private String campania;
    private String color; // ✅ NUEVO CAMPO

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private String fechaInicio;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private String fechaFin;

    public BBDDDTO() {}

    public BBDDDTO(BBDD bbdd) {
        if (bbdd != null) {
            this.codigo = bbdd.getCodigo();
            this.club = bbdd.getClub();
            this.cp = bbdd.getCp();
            this.direccion = bbdd.getDireccion();
            this.municipio = bbdd.getMunicipio();
            this.observacion = bbdd.getObservacion();
            this.provincia = bbdd.getProvincia();
            this.campania = bbdd.getCampania();
            this.color = bbdd.getColor(); // ✅ NUEVO CAMPO ASIGNADO

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

            this.fechaInicio = (bbdd.getFechaInicio() != null) ? bbdd.getFechaInicio().format(formatter) : null;
            this.fechaFin = (bbdd.getFechaFin() != null) ? bbdd.getFechaFin().format(formatter) : null;
        }
    }

    public BBDD toEntity() {
        BBDD bbdd = new BBDD();
        bbdd.setCodigo(this.codigo != null ? this.codigo : "");
        bbdd.setClub(this.club != null ? this.club : "");
        bbdd.setCp(this.cp != null ? this.cp : 0);
        bbdd.setDireccion(this.direccion != null ? this.direccion : "");
        bbdd.setMunicipio(this.municipio != null ? this.municipio : "");
        bbdd.setObservacion(this.observacion != null ? this.observacion : "");
        bbdd.setProvincia(this.provincia != null ? this.provincia : "");
        bbdd.setCampania(this.campania != null ? this.campania : "");
        bbdd.setColor(this.color != null ? this.color : "white"); // ✅ ASIGNACIÓN DEL COLOR

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        if (this.fechaInicio != null && !this.fechaInicio.isEmpty()) {
            bbdd.setFechaInicio(LocalDateTime.parse(this.fechaInicio, formatter));
        } else {
            bbdd.setFechaInicio(null);
        }

        if (this.fechaFin != null && !this.fechaFin.isEmpty()) {
            bbdd.setFechaFin(LocalDateTime.parse(this.fechaFin, formatter));
        } else {
            bbdd.setFechaFin(null);
        }

        return bbdd;
    }
}
