package sisdrones.model.manager;

import sisdrones.model.dao.entities.RegDronPersona;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class ManagerRegistro implements Serializable {

	private static final long serialVersionUID = 1L;
	private static String VALOR_TODOS_PERIODOS = "all";
	
	@EJB
	private ManagerDAO mDAO;

	public ManagerRegistro() {
	}
	
	/**
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<String> findAllPeriodos(){
		return mDAO.findSQL("SELECT DISTINCT per_periodo FROM reg_dron_persona ORDER BY per_periodo DESC;");
	}
	
	/**
	 * 
	 * @param periodo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<RegDronPersona> findAllRegistrosPeriodo(String periodo) {
		if(periodo.equals(VALOR_TODOS_PERIODOS))
			return mDAO.findAll(RegDronPersona.class, "o.perFechaReg DESC");
		else
			return mDAO.findWhere(RegDronPersona.class, "o.perPeriodo='"+periodo+"'", "o.perFechaReg DESC");
	}

	/**
	 * 
	 * @param dp
	 * @throws Exception
	 */
	public void insertarDronPersona(RegDronPersona dp) throws Exception {
		dp.setPerFechaReg(new Timestamp(new Date().getTime()));
		dp.setPerFecha(new Date());
		mDAO.insertar(dp);
	}

	/**
	 * 
	 * @param dp
	 * @throws Exception
	 */
	public void editarDronPersona(RegDronPersona dp) throws Exception {
		RegDronPersona ndp = findDronPersonaByID(dp.getPerCedula());
		ndp.setPerNombres(dp.getPerNombres());
		ndp.setPerApellidos(dp.getPerApellidos());
		ndp.setPerCorreo(dp.getPerCorreo());
		ndp.setPerTelefono(dp.getPerTelefono());
		ndp.setPerNacionalidad(dp.getPerNacionalidad());
		ndp.setPerResidencia(dp.getPerResidencia());
		ndp.setPerExperiencia(dp.getPerExperiencia());
		ndp.setPerPoseedron(dp.getPerPoseedron());
		ndp.setPerInteres(dp.getPerInteres());
		mDAO.actualizar(ndp);
	}
	
	/**
	 * 
	 * @param perCedula
	 * @return
	 * @throws Exception
	 */
	public RegDronPersona findDronPersonaByID(String perCedula) throws Exception {
		return (RegDronPersona) mDAO.findById(RegDronPersona.class, perCedula);
	}
	
	/**
	 * 
	 * @param cedula
	 * @param periodo
	 * @return
	 */
	public boolean existeRegistroPeriodo(String cedula, String periodo){
		if(mDAO.findWhere(RegDronPersona.class, "o.perCedula='"+cedula+"' AND o.perPeriodo='"+periodo+"'", null).size()>0)
			return true;
		else
			return false;
	}
}
