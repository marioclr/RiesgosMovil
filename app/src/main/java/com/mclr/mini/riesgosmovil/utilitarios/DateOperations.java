package com.mclr.mini.riesgosmovil.utilitarios;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateOperations {

	public static String displayStringDate(Timestamp tDate){
		String strDate=null, strDay=null, strYear=null;
		int strMonth=0;
		String monthName[] = {"enero","febrero","marzo","abril","mayo","junio","julio",
				"agosto","septiembre","octubre","noviembre","diciembre"};
		strDay = tDate.toString().substring(8,11);
		strMonth = Integer.parseInt(tDate.toString().substring(5,7));
		strYear = tDate.toString().substring(0,4);
		strDate = strDay + " de " + monthName[strMonth-1] + " de " + strYear;
		return strDate;
	}

	/**
	 * <b>Capa: </b><u>N/A</u>
	 * <p>
	 * Functión para convertir una fecha en texto con formato dd-mmm-aaaa
	 * <p>
	 *  
	 * @param 	tDate: fecha en fomrato Timestamp
	 * @return	String: Fecha en formato cadena
	 */	

	public static String displayStringDate(String sDate){
		String strDate=null, strDay=null, strYear=null;
		int strMonth=0;
		String monthName[] = {"enero","febrero","marzo","abril","mayo","junio","julio",
				"agosto","septiembre","octubre","noviembre","diciembre"};
		strDay = sDate.toString().substring(8,11);
		strMonth = Integer.parseInt(sDate.toString().substring(5,7));
		strYear = sDate.toString().substring(0,4);
		strDate = strDay + " de " + monthName[strMonth-1] + " de " + strYear;
		return strDate;
	}

	/**
	 * <b>Capa: </b><u>N/A</u>
	 * <p>
	 * Functión para mostrar en formato cadena AAAAMMDD la fecha actual
	 * <p>
	 *  
	 * @return	String: Fecha en formato cadena
	 */	

	public static String getCurrentDate(){
		String strDay="0", strMonth="0", strYear=null;
		Calendar cal = Calendar.getInstance();
		if (cal.get(Calendar.DATE) < 10)
			strDay = strDay + Integer.toString(cal.get(Calendar.DATE));
		else
			strDay = Integer.toString(cal.get(Calendar.DATE));
		if (cal.get(Calendar.MONTH) < 10)
			strMonth = strMonth + Integer.toString(cal.get(Calendar.MONTH));
		else
			strMonth = Integer.toString(cal.get(Calendar.MONTH));
		strYear = Integer.toString(cal.get(Calendar.YEAR));
		
		return strYear + strMonth + strDay;
	}
	
	/**
	 * <b>Capa: </b><u>N/A</u>
	 * <p>
	 * Functión para mostrar la fecha actual en formato timestamp
	 * <p>
	 *  
	 * @return	Timestamp: Fecha en formato cadena
	 */	

	public static Timestamp getTimestampDate(){

		Calendar calendar = Calendar.getInstance();

		Date now = calendar.getTime();

		Timestamp currentDate = new Timestamp(now.getTime());
		return currentDate;
	}

	/**
	 * <b>Capa: </b><u>N/A</u>
	 * <p>
	 * Functión para obtener el a�o actual
	 * <p>
	 *  
	 * @return	int: Fecha en formato num�rico
	 */	

	public static int getCurrentYear(){
		int strYear=0;
		Calendar cal = Calendar.getInstance();
		strYear = cal.get(Calendar.YEAR) ;
		return strYear;
	}

	public static int getCurrentMonth(){
		int strMonth=0;
		Calendar cal = Calendar.getInstance();
		strMonth = cal.get(Calendar.MONTH) ;
		return strMonth;
	}

	public static int getCurrentDay(){
		int strDay=0;
		Calendar cal = Calendar.getInstance();
		strDay = cal.get(Calendar.DAY_OF_MONTH) ;
		return strDay;
	}

	public static String getFechaActual() {
        Date ahora = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
        return formateador.format(ahora);
    }

	public static String getYearOfDate(String sDate) {
		return sDate.substring(0, 4);
	}

	public static String getMonthOfDate(String sDate) {
		return sDate.substring(5, 7);
	}

	public static String getDayOfDate(String sDate) {
		return sDate.substring(8, 10);
	}

	public static String getDateString(int iYear, int iMonth, int iDay)
	{
		String sYear, sMonth, sDay;

		sYear = Integer.toString(iYear);
		if (Integer.toString(iMonth).length() == 1)
			sMonth = "0" + Integer.toString(iMonth);
		else
			sMonth = Integer.toString(iMonth);

		if (Integer.toString(iDay).length() == 1)
			sDay = "0" + Integer.toString(iDay);
		else
			sDay = Integer.toString(iDay);

		return new StringBuilder().append(sYear).append("-").append(sMonth).append("-").append(sDay).toString();
	}

}
