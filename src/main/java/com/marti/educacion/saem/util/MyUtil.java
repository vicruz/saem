package com.marti.educacion.saem.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Component
public class MyUtil {

	private static MessageSource messageSource;
	
	@Autowired
	public MyUtil(MessageSource messageSource){
		MyUtil.messageSource = messageSource;
	}
	
	public static void flash(RedirectAttributes redirectAttributes, String kind, String messageKey){
		redirectAttributes.addFlashAttribute("flashKind",kind);
		redirectAttributes.addFlashAttribute("flashMessage",MyUtil.getMessage(messageKey));
	}
	
	public static void flash(RedirectAttributes redirectAttributes, String kind, String messageKey, Object... args){
		redirectAttributes.addFlashAttribute("flashKind",kind);
		redirectAttributes.addFlashAttribute("flashMessage",MyUtil.getMessage(messageKey,args));
	}
	
	public static void flashNotProperties(RedirectAttributes redirectAttributes, String kind, String messageKey){
		redirectAttributes.addFlashAttribute("flashKind",kind);
		redirectAttributes.addFlashAttribute("flashMessage",MyUtil.getMessage(messageKey));
	}

	private static String getMessage(String messageKey, Object... args) {
		return messageSource.getMessage(messageKey, args, Locale.getDefault());
	}
	
	public static String getMonth(Integer idMonth){
		String month = "";
		switch(idMonth){
		case 1:
			month = "Enero";
			break;
		case 2:
			month = "Febrero";
			break;
		case 3:
			month = "Marzo";
			break;
		case 4:
			month = "Abril";
			break;
		case 5:
			month = "Mayo";
			break;
		case 6:
			month = "Junio";
			break;
		case 7:
			month = "Julio";
			break;
		case 8:
			month = "Agosto";
			break;
		case 9:
			month = "Septiembre";
			break;
		case 10:
			month = "Octubre";
			break;
		case 11:
			month = "Noviembre";
			break;
		case 12:
			month = "Diciembre";
			break;
		default:
				month = "";
				break;
		}
		
		return month;
	}
	
	/**
     * Método que formatea un afecha en base al formato pasado como
     * parámetro.
     * @param fecha: fecha a formatear
     * @param pattern: formato que se dará a la fecha.
     * @return Fecha formateada en base al formato de pattern.
     * null si se presenta alguna excepción
     */
    private static Date formatearDate(Date fecha, String pattern) {
        SimpleDateFormat formato = new SimpleDateFormat(pattern);
        Date fechaFormateada = null;
        try {
            fechaFormateada = formato.parse(formato.format(fecha));
            return fechaFormateada;
        } catch (Exception ex) {
            return fechaFormateada;
        }
    }    
 
    /**
     * Método que remplaza el año y el mes de fecha y pone
     * el año y mes de fechaActual
     * @param fecha: fecha a remplazar el mes y el año
     * @param fechaActual: fecha de la cual se tomara el mes y el año
     * y se colocara en fecha
     * @return Calendar con la nueva fecha calculada.
     */
    private static Calendar ponerAnioMesActual(Date fecha, Date fechaActual) {
        try {
            Calendar cActual = Calendar.getInstance();
            cActual.setTime(fechaActual);
            Calendar cfecha = Calendar.getInstance();
            cfecha.setTime(fecha);
            //la fecha nueva
            Calendar c1 = Calendar.getInstance();
            c1.set(cActual.get(Calendar.YEAR), cActual.get(Calendar.MONTH), cfecha.get(Calendar.DATE));
            return c1;
        } catch (Exception e) {
            return null;
        }
    }
	
    /**
     * Método que calcula los meses que han pasado dese fecha inicio hasta
     * fecha fin.
     * @param fechaInicio: fecha de inicio de comparación.
     * @param fechaFin: fecha de finalización de comparación.
     * @return 0 si no ha pasado un mes o si se presenta alguna exepción.
     * > 0 si han pasado almenos un mes.
     */
	public static int calcularMesesAFecha(Date fechaInicio, Date fechaFin) {
        try {
            //Fecha inicio en objeto Calendar
            Calendar startCalendar = Calendar.getInstance();
            startCalendar.setTime(fechaInicio);
            //Fecha finalización en objeto Calendar
            Calendar endCalendar = Calendar.getInstance();
            endCalendar.setTime(fechaFin);
            //Cálculo de meses para las fechas de inicio y finalización
            int startMes = (startCalendar.get(Calendar.YEAR) * 12) + startCalendar.get(Calendar.MONTH);
            int endMes = (endCalendar.get(Calendar.YEAR) * 12) + endCalendar.get(Calendar.MONTH);
            //Diferencia en meses entre las dos fechas
            int diffMonth = endMes - startMes;
            
            //Si la el día de la fecha de finalización es menor que el día de la fecha inicio
            //se resta un mes, puesto que no estaria cumpliendo otro periodo.
            //Para esto ocupo el metoddo ponerAnioMesActual
            Date aFecha = ponerAnioMesActual(fechaInicio,fechaFin).getTime();
            if(formatearDate(fechaFin, "dd/MM/yyyy").compareTo(
                    formatearDate(aFecha,"dd/MM/yyyy")) < 0){
                diffMonth = diffMonth - 1;
            }
            //Si la fecha de finalización es menor que la fecha de inicio, retorno que los meses
            // transcurridos entre las dos fechas es 0
            if(diffMonth < 0){
                diffMonth = 0;
            }
            return diffMonth;
        } catch (Exception e) {
            return 0;
        }
    }
	
	public static String getGrado(int idGrado){
		String grado = "";
		switch(idGrado){
		case 1:
			grado = "Kinder 1";
			break;
		case 2:
			grado = "Kinder 2";
			break;
		case 3:
			grado = "Kinder 3";
			break;
		case 4:
			grado = "Primaria 1";
			break;
		case 5:
			grado = "Primaria 2";
			break;
		case 6:
			grado = "Primaria 3";
			break;
		case 7:
			grado = "Primaria 4";
			break;
		case 8:
			grado = "Primaria 5";
			break;
		default:
			grado = "Primaria 6";
			break;
		}
		return grado;
	}
}
