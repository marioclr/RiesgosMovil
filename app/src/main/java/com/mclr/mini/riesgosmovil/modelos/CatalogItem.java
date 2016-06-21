package com.mclr.mini.riesgosmovil.modelos;

public class CatalogItem {
	private String id;
	private String key;
	private String name;
	private int icon;
	
	public CatalogItem(String p_id, String p_key, String p_name, int p_icon)
	{
		setId(p_id);
		setKey(p_key);
		setName(p_name);
		setIcon(p_icon);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIcon() {
		return icon;
	}

	public void setIcon(int icon) {
		this.icon = icon;
	}
}
