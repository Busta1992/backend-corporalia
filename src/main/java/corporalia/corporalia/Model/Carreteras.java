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
@Table(name = "carreteras")
@Getter
@Setter


public class Carreteras {

    @Id
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
    @Column(name = "izq_delantera")
    private String izqDelantera;
    @Column(name = "izq_plus")
    private String izqPlus;

    @Column(name = "izq_trasera")

    private String izqTrasera;
    @Column(name = "der_delantera")
    private String derDelantera;
    @Column(name = "der_plus")
    private String derPlus;
    @Column(name = "der_trasera")
    private String derTrasera;
    private String campania;
    private String info_plus;
    private String observaciones;
    private String zona;

    @Column(name = "fecha_inicio", nullable = true)
    private LocalDateTime fechaInicio;

    @Column(name = "fecha_fin", nullable = true)
    private LocalDateTime fechaFin;
    @Column(name = "color")
    private String color;





    public Carreteras() {}

    public Carreteras(corporalia.corporalia.DTO.CarreterasDTO carreterasDTO) {

        this.nFlota = carreterasDTO.getNFlota();
        this.provincia = carreterasDTO.getProvincia();
        this.carretera = carreterasDTO.getCarretera();
        this.operador = carreterasDTO.getOperador();
        this.lineas = carreterasDTO.getLineas();
        this.matricula = carreterasDTO.getMatricula();
        this.base = carreterasDTO.getBase();
        this.modelo = carreterasDTO.getModelo();
        this.plantilla = carreterasDTO.getPlantilla();
        this.extra = carreterasDTO.getExtra(); // ← Estabas llamando a getModelo()
        this.trasera = carreterasDTO.getTrasera();
        this.izqDelantera = carreterasDTO.getIzqDelantera();
        this.izqPlus = carreterasDTO.getIzqPlus();
        this.izqTrasera = carreterasDTO.getIzqTrasera();
        this.derDelantera = carreterasDTO.getDerDelantera();
        this.derPlus = carreterasDTO.getDerPlus();
        this.derTrasera = carreterasDTO.getDerTrasera();
        this.campania = carreterasDTO.getCampania();
        this.info_plus= carreterasDTO.getInfo_plus();
        this.observaciones= carreterasDTO.getObservaciones();
        this.zona=carreterasDTO.getZona();
        this.color = carreterasDTO.getColor();



        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        if (carreterasDTO.getFechaInicio() != null && !carreterasDTO.getFechaInicio().isEmpty()) {
            this.fechaInicio = LocalDateTime.parse(carreterasDTO.getFechaInicio(), formatter);
        } else {
            this.fechaInicio = null;
        }

        if (carreterasDTO.getFechaFin() != null && !carreterasDTO.getFechaFin().isEmpty()) {
            this.fechaFin = LocalDateTime.parse(carreterasDTO.getFechaFin(), formatter);
        } else {
            this.fechaFin = null;
        }
    }
}











