package sisdrones.controller.register;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import sisdrones.controller.generic.Funciones;
import sisdrones.controller.generic.Mensaje;
import sisdrones.model.dao.entities.RegDronPersona;
import sisdrones.model.manager.ManagerRegistro;

@ManagedBean
@ViewScoped
public class RegistroBean implements Serializable {

	/**
	 * SERIAL ID
	 */
	private static final long serialVersionUID = 1L;
	private String PERIODO_ACTUAL = "201602";

	@EJB
	private ManagerRegistro mngReg;

	private String cedula;
	private String nombres;
	private String apellidos;
	private String correo;
	private String confCorreo;
	private String nacionalidad;
	private String recidencia;
	private String telefono;
	private boolean poseeDron;
	private boolean poseeExperiencia;
	private String interes;

	public RegistroBean() {

		poseeDron = false;
		poseeExperiencia = false;
	}

	/**
	 * @return the cedula
	 */
	public String getCedula() {
		return cedula;
	}

	/**
	 * @param cedula
	 *            the cedula to set
	 */
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	/**
	 * @return the nombres
	 */
	public String getNombres() {
		return nombres;
	}

	/**
	 * @param nombres
	 *            the nombres to set
	 */
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	/**
	 * @return the apellidos
	 */
	public String getApellidos() {
		return apellidos;
	}

	/**
	 * @param apellidos
	 *            the apellidos to set
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	/**
	 * @return the correo
	 */
	public String getCorreo() {
		return correo;
	}

	/**
	 * @param correo
	 *            the correo to set
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	}

	/**
	 * @return the confCorreo
	 */
	public String getConfCorreo() {
		return confCorreo;
	}

	/**
	 * @param confCorreo
	 *            the confCorreo to set
	 */
	public void setConfCorreo(String confCorreo) {
		this.confCorreo = confCorreo;
	}

	/**
	 * @return the nacionalidad
	 */
	public String getNacionalidad() {
		return nacionalidad;
	}

	/**
	 * @param nacionalidad
	 *            the nacionalidad to set
	 */
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	/**
	 * @return the recidencia
	 */
	public String getRecidencia() {
		return recidencia;
	}

	/**
	 * @param recidencia
	 *            the recidencia to set
	 */
	public void setRecidencia(String recidencia) {
		this.recidencia = recidencia;
	}

	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono
	 *            the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * @return the poseeDron
	 */
	public boolean isPoseeDron() {
		return poseeDron;
	}

	/**
	 * @param poseeDron
	 *            the poseeDron to set
	 */
	public void setPoseeDron(boolean poseeDron) {
		this.poseeDron = poseeDron;
	}

	/**
	 * @return the poseeExperiencia
	 */
	public boolean isPoseeExperiencia() {
		return poseeExperiencia;
	}

	/**
	 * @param poseeExperiencia
	 *            the poseeExperiencia to set
	 */
	public void setPoseeExperiencia(boolean poseeExperiencia) {
		this.poseeExperiencia = poseeExperiencia;
	}

	/**
	 * @return the interes
	 */
	public String getInteres() {
		return interes;
	}

	/**
	 * @param interes
	 *            the interes to set
	 */
	public void setInteres(String interes) {
		this.interes = interes;
	}

	/**
	 * Método para controlar la validacion de los campos en la vista
	 * 
	 */
	public void validar() {
		
		try {
			if (!Funciones.validacionCedula(getCedula().trim())) {
				System.out.println("aqui se queda1");
				throw new Exception("Ingrese una cédula válida");
			} else if (mngReg.existeRegistroPeriodo(getCedula().trim(),
					PERIODO_ACTUAL)) {
				System.out.println("aqui se queda2");
				throw new Exception("Usted ya se encuentra registrado.");
			} else if (!getCorreo().trim().equals(getConfCorreo().trim())) {
				System.out.println("aqui se queda3");
				throw new Exception(
						"Los correos no coinciden. Favor revisarlos.");
			} else
				RequestContext.getCurrentInstance().execute(
						"PF('dlgconf').show();");
		} catch (Exception e) {
			Mensaje.crearMensajeWARN("ADVERTENCIA: " + e.getMessage());
		}
	}

	/**
	 * Método para realizar el registro de usuarios
	 * 
	 */
	public void registrarse() {
		
		try {
			RegDronPersona reg = new RegDronPersona();
			reg.setPerCedula(getCedula().trim());
			reg.setPerNombres(getNombres().trim());
			reg.setPerApellidos(getApellidos().trim());
			reg.setPerCorreo(getCorreo().trim());
			reg.setPerTelefono(getTelefono().trim());
			reg.setPerNacionalidad(getNacionalidad().trim());
			reg.setPerResidencia(getRecidencia().trim());
			reg.setPerExperiencia(isPoseeExperiencia());
			reg.setPerPoseedron(isPoseeDron());
			reg.setPerPeriodo(PERIODO_ACTUAL);
			reg.setPerInteres(getInteres().trim());
			System.out.println("llega hasta aca");
			mngReg.insertarDronPersona(reg);
			System.out.println("inserta");
			vaciarCampos();
			System.out.println("limpia");
			Mensaje.crearMensajeINFO("Registro correcto.");
		} catch (Exception e) {
			Mensaje.crearMensajeERROR("ERROR: " + e.getMessage());
		}
	}

	/**
	 * Método para limpiar los campos
	 * 
	 */
	public void vaciarCampos() {
		
		this.setNombres("");
		this.setApellidos("");
		this.setCedula("");
		this.setCorreo("");
		this.setRecidencia("");
		this.setConfCorreo("");
		this.setNacionalidad("");
		this.setTelefono("");
		this.setInteres("");
		this.setPoseeDron(false);
		this.setPoseeExperiencia(false);
	}
}
