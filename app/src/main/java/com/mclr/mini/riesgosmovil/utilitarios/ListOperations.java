package com.mclr.mini.riesgosmovil.utilitarios;

import com.mclr.mini.riesgosmovil.modelos.CatalogItem;

import java.util.List;

public class ListOperations {
	public static int getItemPositionByID(List<CatalogItem> p_listaCatalogItems, String id)
	{
		for (CatalogItem c : p_listaCatalogItems)
		{
			if (c.getId().equals(id))
			{
				return p_listaCatalogItems.indexOf(c);
			}
		}
		return 0;
	}
}
