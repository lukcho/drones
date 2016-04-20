package sisdrones.model.dao.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the reg_dron_persona database table.
 * 
 */
@Entity
@Table(name="reg_dron_persona")
@NamedQuery(name="RegDronPersona.findAll", query="SELECT r FROM RegDronPersona r")
public class RegDronPersona implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="per_cedula", length=10)
	private String perCedula;

	@Column(name="per_apellidos", length=150)
	private String perApellidos;

	@Column(name="per_correo", length=100)
	private String perCorreo;

	@Column(name="per_experiencia")
	private Boolean perExperiencia;

	@Temporal(TemporalType.DATE)
	@Column(name="per_fecha")
	private Date perFecha;

	@Column(name="per_fecha_reg")
	private Timestamp perFechaReg;

	@Column(name="per_interes", length=200)
	private String perInteres;

	@Column(name="per_nacionalidad", length=100)
	private String perNacionalidad;

	@Column(name="per_nombres", length=150)
	private String perNombres;

	@Column(name="per_periodo", length=6)
	private String perPeriodo;

	@Column(name="per_poseedron")
	private Boolean perPoseedron;

	@Column(name="per_residencia", length=200)
	private String perResidencia;

	@Column(name="per_telefono", length=50)
	private String perTelefono;

	public RegDronPersona() {
	}

	public String getPerCedula() {
		return this.perCedula;
	}

	public void setPerCedula(String perCedula) {
		this.perCedula = perCedula;
	}

	public String getPerApellidos() {
		return this.perApellidos;
	}

	public void setPerApellidos(String perApellidos) {
		this.perApellidos = perApellidos;
	}

	public String getPerCorreo() {
		return this.perCorreo;
	}

	public void setPerCorreo(String perCorreo) {
		this.perCorreo = perCorreo;
	}

	public Boolean getPerExperiencia() {
		return this.perExperiencia;
	}

	public void setPerExperiencia(Boolean perExperiencia) {
		this.perExperiencia = perExperiencia;
	}

	public Date getPerFecha() {
		return this.perFecha;
	}

	public void setPerFecha(Date perFecha) {
		this.perFecha = perFecha;
	}

	public Timestamp getPerFechaReg() {
		return this.perFechaReg;
	}

	public void setPerFechaReg(Timestamp perFechaReg) {
		this.perFechaReg = perFechaReg;
	}

	public String getPerInteres() {
		return this.perInteres;
	}

	public void setPerInteres(String perInteres) {
		this.perInteres = perInteres;
	}

	public String getPerNacionalidad() {
		return this.perNacionalidad;
	}

	public void setPerNacionalidad(String perNacionalidad) {
		this.perNacionalidad = perNacionalidad;
	}

	public String getPerNombres() {
		return this.perNombres;
	}

	public void setPerNombres(String perNombres) {
		this.perNombres = perNombres;
	}

	public String getPerPeriodo() {
		return this.perPeriodo;
	}

	public void setPerPeriodo(String perPeriodo) {
		this.perPeriodo = perPeriodo;
	}

	public Boolean getPerPoseedron() {
		return this.perPoseedron;
	}

	public void setPerPoseedron(Boolean perPoseedron) {
		this.perPoseedron = perPoseedron;
	}

	public String getPerResidencia() {
		return this.perResidencia;
	}

	public void setPerResidencia(String perResidencia) {
		this.perResidencia = perResidencia;
	}

	public String getPerTelefono() {
		return this.perTelefono;
	}

	public void setPerTelefono(String perTelefono) {
		this.perTelefono = perTelefono;
	}

}