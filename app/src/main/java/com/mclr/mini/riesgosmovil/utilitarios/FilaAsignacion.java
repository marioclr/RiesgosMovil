package com.mclr.mini.riesgosmovil.utilitarios;

import java.io.Serializable;

public class FilaAsignacion implements Serializable{
	private String name="";
	private String id="";
	
	public FilaAsignacion(String name, String id) {
		this.setId(id);
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
