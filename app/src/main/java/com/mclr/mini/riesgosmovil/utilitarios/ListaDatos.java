package com.mclr.mini.riesgosmovil.utilitarios;

public class ListaDatos {
	private int idImagen;
	private String titulo;
	private String descripcion;
	private String propertyTypeID;
	
	public ListaDatos(int p_imagen, String p_titulo, String p_descripcion, String p_PropertyTypeID) {
		setIdImagen(p_imagen);
		setTitulo(p_titulo);
		setDescripcion(p_descripcion);
		setPropertyTypeID(p_PropertyTypeID);
	}

	public int getIdImagen() {
		return idImagen;
	}

	public void setIdImagen(int idImagen) {
		this.idImagen = idImagen;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getPropertyTypeID() {
		return propertyTypeID;
	}

	public void setPropertyTypeID(String propertyTypeID) {
		this.propertyTypeID = propertyTypeID;
	}
}
