package corporalia.corporalia.DTO;

import corporalia.corporalia.Model.HistorialCampanias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.format.DateTimeFormatter;

@Getter
@Setter

public class HistorialCampaniasDTO {

    private Long id;
    private String nFlota;
    private String matricula;
    private String campania;
    private String operador;
    private String carretera;
    private String fechaInicio;
    private String fechaFin;
    private String observaciones;

    public HistorialCampaniasDTO() {}

    public HistorialCampaniasDTO(HistorialCampanias h) {
        this.id = h.getId();
        this.nFlota = h.getNFlota();
        this.matricula = h.getMatricula();
        this.campania = h.getCampania();
        this.operador = h.getOperador();
        this.carretera = h.getCarretera();
        this.observaciones = h.getObservaciones();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        this.fechaInicio = h.getFechaInicio() != null ? h.getFechaInicio().format(formatter) : null;
        this.fechaFin = h.getFechaFin() != null ? h.getFechaFin().format(formatter) : null;
    }
}
