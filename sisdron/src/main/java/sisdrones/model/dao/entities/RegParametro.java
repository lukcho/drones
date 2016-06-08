package sisdrones.model.dao.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the reg_parametros database table.
 * 
 */
@Entity
@Table(name="reg_parametros")
@NamedQuery(name="RegParametro.findAll", query="SELECT r FROM RegParametro r")
public class RegParametro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="par_id")
	private String parId;

	@Column(name="par_nombre")
	private String parNombre;

	@Column(name="par_valor")
	private String parValor;

	public RegParametro() {
	}

	public String getParId() {
		return this.parId;
	}

	public void setParId(String parId) {
		this.parId = parId;
	}

	public String getParNombre() {
		return this.parNombre;
	}

	public void setParNombre(String parNombre) {
		this.parNombre = parNombre;
	}

	public String getParValor() {
		return this.parValor;
	}

	public void setParValor(String parValor) {
		this.parValor = parValor;
	}

}