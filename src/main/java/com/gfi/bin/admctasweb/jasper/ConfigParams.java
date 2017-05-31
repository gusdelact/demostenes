package com.gfi.bin.admctasweb.jasper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component(value="configParams")
public class ConfigParams {
	
	@Value( "${reportes.rutaPlantillas}" )
	private String rutaPlantillas;
	@Value( "${reportes.rutaReportes}" )
	private String rutaReportes;
	@Value( "${reportes.rutaLogo}" )
	private String rutaLogo;

	public ConfigParams(){
		
	}

	public ConfigParams(String rutaPlantillas, String rutaReportes,
			String rutaLogo) {
		super();
		this.rutaPlantillas = rutaPlantillas;
		this.rutaReportes = rutaReportes;
		this.rutaLogo = rutaLogo;
	}

	public String getRutaPlantillas() {
		return rutaPlantillas;
	}

	public void setRutaPlantillas(String rutaPlantillas) {
		this.rutaPlantillas = rutaPlantillas;
	}

	public String getRutaLogo() {
		return rutaLogo;
	}

	public void setRutaLogo(String rutaLogo) {
		this.rutaLogo = rutaLogo;
	}

	public String getRutaReportes() {
		return rutaReportes;
	}

	public void setRutaReportes(String rutaReportes) {
		this.rutaReportes = rutaReportes;
	}
	
}
