package corporalia.corporalia.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import corporalia.corporalia.Model.Carreteras;
import lombok.Getter;
import lombok.Setter;

import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class CarreterasDTO {




    @JsonProperty("n_flota")
    private String nFlota;

    private String carretera;
    private String provincia;
    private String operador;
    private String lineas;
    private String matricula;
    private String base;
    private String modelo;
    private String plantilla;
    private String extra;
    private String trasera;

    @JsonProperty("izq_delantera")
    private String izqDelantera;

    @JsonProperty("izq_plus")
    private String izqPlus;

    @JsonProperty("izq_trasera")
    private String izqTrasera;

    @JsonProperty("der_delantera")
    private String derDelantera;

    @JsonProperty("der_plus")
    private String derPlus;

    @JsonProperty("der_trasera")
    private String derTrasera;

    private String campania;

    @JsonProperty("info_plus")
    private String info_plus;

    private String observaciones;
    private String zona;



    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private String fechaInicio;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private String fechaFin;

    @JsonProperty("color")
    private String color;

    // ✅ Constructor vacío requerido por Jackson
    public CarreterasDTO() {
        System.out.println("✅ Constructor DTO invocado");
    }

    // Constructor para construir DTO a partir de entidad
    public CarreterasDTO(Carreteras carreteras) {
        if (carreteras != null) {
            this.nFlota = String.valueOf(carreteras.getNFlota());
            this.provincia = carreteras.getProvincia();
            this.carretera = carreteras.getCarretera();
            this.operador = carreteras.getOperador();
            this.lineas = carreteras.getLineas();
            this.matricula = carreteras.getMatricula();
            this.base = carreteras.getBase();
            this.modelo = carreteras.getModelo();
            this.plantilla = carreteras.getPlantilla();
            this.extra = carreteras.getExtra();
            this.trasera = carreteras.getTrasera();
            this.izqDelantera = carreteras.getIzqDelantera();
            this.izqPlus = carreteras.getIzqPlus();
            this.izqTrasera = carreteras.getIzqTrasera();
            this.derDelantera = carreteras.getDerDelantera();
            this.derPlus = carreteras.getDerPlus();
            this.derTrasera = carreteras.getDerTrasera();
            this.campania = carreteras.getCampania();
            this.info_plus = carreteras.getInfo_plus();
            this.observaciones = carreteras.getObservaciones();
            this.zona = carreteras.getZona();
            this.color = carreteras.getColor();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
            this.fechaInicio = (carreteras.getFechaInicio() != null) ? carreteras.getFechaInicio().format(formatter) : null;
            this.fechaFin = (carreteras.getFechaFin() != null) ? carreteras.getFechaFin().format(formatter) : null;
        }
    }
}