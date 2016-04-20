package sisdrones.controller.reports;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import org.primefaces.context.RequestContext;

import sisdrones.controller.access.SesionBean;
import sisdrones.controller.generic.Mensaje;
import sisdrones.model.dao.entities.RegDronPersona;
import sisdrones.model.manager.ManagerRegistro;

/**
 * sdasdas
 * @author lcisneros
 *
 */
@ManagedBean
@ViewScoped
public class RptRegistroBean implements Serializable{

	/**
	 * SERIAL ID
	 */
	private static final long serialVersionUID = 1833068677414520293L;
	
	@EJB
	private ManagerRegistro mngReg;
	
	private String periodo;
	private List<RegDronPersona> listaRegistros;
	
	//EDICION DE REGISTRO
	private String cedula;
	private String nombres;
	private String apellidos;
	private String correo;
	private String nacionalidad;
	private String residencia;
	private String telefono;
	private boolean poseeDron;
	private boolean poseeExperiencia;
	private String interes;
	
	@Inject
	SesionBean sesion;
	
	public RptRegistroBean() {
	}
	
	@PostConstruct
	public void init(){
		sesion.validarSesion("rptRegistros.xhtml");
		periodo = "all";
		listaRegistros = mngReg.findAllRegistrosPeriodo(periodo);
	}

	/**
	 * @return the periodo
	 */
	public String getPeriodo() {
		return periodo;
	}

	/**
	 * @param periodo the periodo to set
	 */
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	/**
	 * @return the listaRegistros
	 */
	public List<RegDronPersona> getListaRegistros() {
		return listaRegistros;
	}

	/**
	 * @param listaRegistros the listaRegistros to set
	 */
	public void setListaRegistros(List<RegDronPersona> listaRegistros) {
		this.listaRegistros = listaRegistros;
	}
	
	//EDICIÓN REGISTRO
	/**
	 * @return the cedula
	 */
	public String getCedula() {
		return cedula;
	}

	/**
	 * @param cedula the cedula to set
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
	 * @param nombres the nombres to set
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
	 * @param apellidos the apellidos to set
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
	 * @param correo the correo to set
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	}

	/**
	 * @return the nacionalidad
	 */
	public String getNacionalidad() {
		return nacionalidad;
	}

	/**
	 * @param nacionalidad the nacionalidad to set
	 */
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	/**
	 * @return the residencia
	 */
	public String getResidencia() {
		return residencia;
	}

	/**
	 * @param residencia the residencia to set
	 */
	public void setResidencia(String residencia) {
		this.residencia = residencia;
	}

	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono the telefono to set
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
	 * @param poseeDron the poseeDron to set
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
	 * @param poseeExperiencia the poseeExperiencia to set
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
	 * @param interes the interes to set
	 */
	public void setInteres(String interes) {
		this.interes = interes;
	}

	/**
	 * 
	 * @return
	 */
	public List<SelectItem> listadoPeriodos(){
		List<SelectItem> periodos = new ArrayList<SelectItem>();
		periodos.add(new SelectItem("all", "Todos los periodos"));
		for (String periodo : mngReg.findAllPeriodos()) {
			periodos.add(new SelectItem(periodo, periodo));
		}
		return periodos;
	}

	/**
	 * 
	 */
	public void consultar(){
		try {
			getListaRegistros().clear();
			getListaRegistros().addAll(mngReg.findAllRegistrosPeriodo(getPeriodo()));
		} catch (Exception e) {
			Mensaje.crearMensajeERROR("ERROR: "+e.getMessage());
		}
	}
	
	/**
	 * 
	 * @param registro
	 */
	public void cargarDatos(RegDronPersona registro){
		setNombres(registro.getPerNombres());
		setApellidos(registro.getPerApellidos());
		setCedula(registro.getPerCedula());
		setTelefono(registro.getPerTelefono());
		setCorreo(registro.getPerCorreo());
		setResidencia(registro.getPerResidencia());
		setNacionalidad(registro.getPerNacionalidad());
		setPoseeDron(registro.getPerPoseedron());
		setPoseeExperiencia(registro.getPerExperiencia());
		setInteres(registro.getPerInteres());
		RequestContext.getCurrentInstance().execute("PF('dlged').show();");
	}
	
	/**
	 * 
	 */
	public void editarRegistro(){
		try {
			RegDronPersona reg = new RegDronPersona();
			reg.setPerCedula(getCedula().trim());
			reg.setPerNombres(getNombres().trim());
			reg.setPerApellidos(getApellidos().trim());
			reg.setPerCorreo(getCorreo().trim());
			reg.setPerTelefono(getTelefono().trim());
			reg.setPerNacionalidad(getNacionalidad().trim());
			reg.setPerResidencia(getResidencia().trim());
			reg.setPerExperiencia(isPoseeExperiencia());
			reg.setPerPoseedron(isPoseeDron());
			reg.setPerInteres(getInteres());
			mngReg.editarDronPersona(reg);
			RequestContext.getCurrentInstance().execute("PF('dlged').hide();");
			Mensaje.crearMensajeINFO("Datos modificados correctamente.");
		} catch (Exception e) {
			Mensaje.crearMensajeERROR("ERROR: "+e.getMessage());
		}
	}

}
