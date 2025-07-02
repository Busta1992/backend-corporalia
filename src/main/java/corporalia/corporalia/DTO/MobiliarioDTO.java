package corporalia.corporalia.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import corporalia.corporalia.Model.HistorialCampanias;
import corporalia.corporalia.Model.Mobiliario;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;



@Getter
@Setter

public class MobiliarioDTO {




    @JsonProperty("codigo")
    private String codigo;

    @JsonProperty("campania")
    private String campania;

    @JsonProperty("municipio")
    private String municipio;

    @JsonProperty("provincia")
    private String provincia;

    @JsonProperty("cp")
    private String cp;

    @JsonProperty("observaciones")
    private String observaciones;

    // ✅ Ahora con camelCase, para que coincida con el frontend
    @JsonProperty("fechaInicio")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fechaInicio;

    @JsonProperty("fechaFin")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fechaFin;

    @JsonProperty("color")
    private String color;

    public MobiliarioDTO() {
        System.out.println("✅ Constructor DTO invocado");
    }

    public MobiliarioDTO(Mobiliario mobiliario) {
        if (mobiliario != null) {
            this.codigo = String.valueOf(mobiliario.getCodigo());
            this.campania = mobiliario.getCampania();
            this.provincia = mobiliario.getProvincia();
            this.municipio = mobiliario.getMunicipio();
            this.cp = mobiliario.getCp();
            this.observaciones = mobiliario.getObservaciones();
            this.fechaInicio = mobiliario.getFechaInicio();
            this.fechaFin = mobiliario.getFechaFin();
            this.color = mobiliario.getColor();
        }
    }
    }


