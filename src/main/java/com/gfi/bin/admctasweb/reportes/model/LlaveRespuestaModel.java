package com.gfi.bin.admctasweb.reportes.model;

/**
 * Representa la llave de una respuesta de Oficio
 * Compuesta por Tipo de Oficio y Tipo de requerimiento
 * @author ESS3VAVC
 *
 */
public class LlaveRespuestaModel {

	private final String tipoOficio;
	private final String tipoRequerimiento;
	
	public LlaveRespuestaModel(String tipoOficio, String tipoRequerimiento) {
        this.tipoOficio = tipoOficio;
        this.tipoRequerimiento = tipoRequerimiento;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((tipoOficio == null) ? 0 : tipoOficio.hashCode());
		result = prime
				* result
				+ ((tipoRequerimiento == null) ? 0 : tipoRequerimiento
						.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LlaveRespuestaModel other = (LlaveRespuestaModel) obj;
		if (tipoOficio == null) {
			if (other.tipoOficio != null)
				return false;
		} else if (!tipoOficio.equals(other.tipoOficio))
			return false;
		if (tipoRequerimiento == null) {
			if (other.tipoRequerimiento != null)
				return false;
		} else if (!tipoRequerimiento.equals(other.tipoRequerimiento))
			return false;
		return true;
	}	
	
	
}
