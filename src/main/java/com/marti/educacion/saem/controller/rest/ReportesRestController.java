package com.marti.educacion.saem.controller.rest;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.marti.educacion.saem.dto.AlumnoReportVO;
import com.marti.educacion.saem.entities.Alumno;
import com.marti.educacion.saem.entities.AlumnoPago;
import com.marti.educacion.saem.entities.AlumnoReportDailyVO;
import com.marti.educacion.saem.entities.vo.AlumnoPagoBitacoraVO;
import com.marti.educacion.saem.repositories.AlumnoPagoBitacoraRepository;
import com.marti.educacion.saem.repositories.AlumnoPagoRepository;
import com.marti.educacion.saem.repositories.AlumnoRepository;
import com.marti.educacion.saem.services.AlumnoPagoBitacoraService;
import com.marti.educacion.saem.util.MyUtil;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

//@RestController
public class ReportesRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(ReportesRestController.class);
	
	private AlumnoPagoBitacoraRepository alumnoPagoBitacoraRepository;
	private AlumnoRepository alumnoRepository;
	private AlumnoPagoRepository alumnoPagoRepository;
	private AlumnoPagoBitacoraService alumnoPagoBitacoraService;
	
//	@Autowired
	public ReportesRestController(AlumnoPagoBitacoraRepository alumnoPagoBitacoraRepository,
			AlumnoRepository alumnoRepository, AlumnoPagoRepository alumnoPagoRepository,
			AlumnoPagoBitacoraService alumnoPagoBitacoraService){
		this.alumnoPagoBitacoraRepository = alumnoPagoBitacoraRepository;
		this.alumnoRepository = alumnoRepository;
		this.alumnoPagoRepository = alumnoPagoRepository;
		this.alumnoPagoBitacoraService = alumnoPagoBitacoraService;
	}
	
	/**
	 * Obtiene los pagos para el grado del alumno
	 * @param idGrado Grado del alumno
	 * @return JSON con mapa de id - conepto
	 */
	//@RequestMapping(value="/reporte/alumno/pagos/{idAlumno}/{start}/{end}", method = RequestMethod.GET,
			//produces="application/pdf")
	public ResponseEntity<InputStreamResource> reportAlumno(@PathVariable("idAlumno") Integer idAlumno, 
			@PathVariable("start") String start, @PathVariable("end") String end){
		
		logger.info("Reporte de alumno: " + idAlumno + " en el periodo " + start +"-" + end);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
		Calendar calInit = Calendar.getInstance();
		Calendar calFin = Calendar.getInstance();
		
		//List<AlumnoPagoBitacora> lstAlumnoPagoBitacora = null;
		List<AlumnoPagoBitacoraVO> lstAlumnoPagoBitacora = null;
		Alumno alumno = null;
		byte[] reporteByte;
		List<AlumnoReportVO> lstReporte = new ArrayList<AlumnoReportVO>();
		List<AlumnoPago> lstAlumnoPago;
		AlumnoReportVO reporteVO;
		
		//Parametros
		String alumnoNombre;
		String grado;
		String periodo;
		
		Map<String, Object> mapParams = new HashMap<String, Object>();
		
		try {
			calInit.setTime(sdf.parse(start));
			calInit.set(Calendar.HOUR_OF_DAY, 0);
			calInit.set(Calendar.MINUTE, 0);
			calInit.set(Calendar.SECOND, 0);
			
			calFin.setTime(sdf.parse(end));
			calFin.set(Calendar.HOUR_OF_DAY, 23);
			calFin.set(Calendar.MINUTE, 59);
			calFin.set(Calendar.SECOND, 59);
			calFin.set(Calendar.DAY_OF_MONTH, calFin.getActualMaximum(Calendar.DAY_OF_MONTH));
			
			alumno = alumnoRepository.findOne(idAlumno);
			
//			alumnoNombre = alumno.getNombre() + " " + alumno.getApPaterno() + " " + alumno.getApMaterno();
//			grado = alumno.getGrado().getName();
			periodo = sdf2.format(calInit.getTime()) + " - " + sdf2.format(calFin.getTime()); 
			
//			mapParams.put("alumno", alumnoNombre);
//			mapParams.put("grado", grado);
			mapParams.put("periodo", periodo);
			
//			lstAlumnoPago = alumnoPagoRepository.findByIdAlumnoAndBetweenMonthPays(alumno.getId(),calInit.getTime(), calFin.getTime());

/*			if(lstAlumnoPago.size() > 0){
				//Construyendo el reporte
				for(AlumnoPago alumnoPago : lstAlumnoPago){
					
					//lstAlumnoPagoBitacora = alumnoPagoBitacoraRepository.findByAlumnoPagoId(alumnoPago.getId());
					lstAlumnoPagoBitacora = alumnoPagoBitacoraRepository.findByAlumnoPagoIdSum(alumnoPago.getId());
					
					if(lstAlumnoPagoBitacora.size()>0){
						//for (AlumnoPagoBitacora alumnoPagoBitacora : lstAlumnoPagoBitacora) {
						for (AlumnoPagoBitacoraVO alumnoPagoBitacora : lstAlumnoPagoBitacora) {
							reporteVO = new AlumnoReportVO();
							reporteVO.setfConcepto(alumnoPago.getPagoGrado().getCatPago().getConcepto() + " " +
									MyUtil.getMonth(alumnoPago.getPagoGrado().getMes_corresponde()) + " " +
									alumnoPago.getPagoGrado().getAnio_corresponde());
							reporteVO.setfMonto(alumnoPago.getMonto());
							reporteVO.setfPago(alumnoPagoBitacora.getPago());
							//reporteVO.setfSaldo(alumnoPagoBitacora.getSaldo()?"SI":"NO");
							reporteVO.setfAdeudo(alumnoPago.getMonto()-alumnoPagoBitacora.getPago());
							reporteVO.setfFechaPago(sdf2.format(alumnoPagoBitacora.getFechaPago()));
							if(alumnoPago.getIdSemaforo()==1){
								reporteVO.setfEstatus("Pagado");
							}else if(alumnoPago.getIdSemaforo()==2){
								reporteVO.setfEstatus("Parcial");
							}else if(alumnoPago.getIdSemaforo()==3){
								reporteVO.setfEstatus("Adeudo");
							}else{
								reporteVO.setfEstatus("Pendiente");
							}
							lstReporte.add(reporteVO);
						}
					}else{
						reporteVO = new AlumnoReportVO();
						reporteVO.setfConcepto(alumnoPago.getPagoGrado().getCatPago().getConcepto() + " " +
								MyUtil.getMonth(alumnoPago.getPagoGrado().getMes_corresponde()) + " " +
								alumnoPago.getPagoGrado().getAnio_corresponde());
						reporteVO.setfMonto(alumnoPago.getMonto());
						reporteVO.setfPago(0D);
						//reporteVO.setfSaldo("");
						reporteVO.setfAdeudo(0D);
						reporteVO.setfFechaPago("");
						if(alumnoPago.getIdSemaforo()==1){
							reporteVO.setfEstatus("Pagado");
						}else if(alumnoPago.getIdSemaforo()==2){
							reporteVO.setfEstatus("Parcial");
						}else if(alumnoPago.getIdSemaforo()==3){
							reporteVO.setfEstatus("Adeudo");
						}else{
							reporteVO.setfEstatus("Pendiente");
						}
						lstReporte.add(reporteVO);
					}
					
				}
			}
			
			JasperReport reporte;
			
			//TODO para debug
			try{
				reporte = (JasperReport) JRLoader.loadObjectFromFile("C:/x/Workspaces/SAE/sae/src/main/resources/reports/reportAlumno1.jasper");
			}catch(Exception e){ 
				reporte = null; 
				reporte = (JasperReport) JRLoader.loadObject(ReportesRestController.class.getResourceAsStream("/reports/reportAlumno1.jasper"));
			}
			
			JRBeanCollectionDataSource ds =new JRBeanCollectionDataSource(lstReporte);
			
			JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, mapParams, ds);
			
			// OutputStream
			final ByteArrayOutputStream report = new ByteArrayOutputStream();

			// PDF
			//JasperExportManager.exportReportToPdfStream(jasperPrint, report);
			
			JRPdfExporter exporter = new JRPdfExporter();
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(report));
			SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
			exporter.setConfiguration(configuration);
			exporter.exportReport();

			reporteByte = report.toByteArray();
			
			final InputStream is = new ByteArrayInputStream(reporteByte);
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Cache-Control","no-cache, no-store, must-revalidate");
			headers.add("Expires", "0");
			headers.add("Content-Disposition", "attachment;filename="+alumnoNombre+".pdf");
			
			return ResponseEntity.ok().headers(headers).contentLength(reporteByte.length)
					.contentType(MediaType.parseMediaType("application/octet-stream"))
					.body(new InputStreamResource(is));
			*/
			/*return Response.status(Response.Status.OK).entity(is)
					.header("Cache-Control","private, max-age=1")
					.header("Content-Length", reporteByte.length+"")
					.header("Expires", "0")
					.header("Content-Disposition", "attachment;filename="+alumnoNombre+".pdf").build();
			*/
			return null;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// catch (JRException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		//}
		
		return null;
		
	}
	
	//@RequestMapping(value="/reporte/test", method = RequestMethod.GET,
//			produces="application/pdf")
	public ResponseEntity<InputStreamResource> reportTest(){
		
		byte[] reporteByte;
		
		Map<String, Object> mapParams = new HashMap<String, Object>();
		
		try {
			
			mapParams.put("alumno", "Cualquiera");
			mapParams.put("grado", "1");
			mapParams.put("periodo", "hoy");
			
			JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile("src/main/resources/reports/reportAlumno1.jasper");
			JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, mapParams, new JREmptyDataSource());
			
			// OutputStream
			final ByteArrayOutputStream report = new ByteArrayOutputStream();

			// PDF
			//JasperExportManager.exportReportToPdfStream(jasperPrint, report);
			
			JRPdfExporter exporter = new JRPdfExporter();
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(report));
			SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
			exporter.setConfiguration(configuration);
			exporter.exportReport();

			reporteByte = report.toByteArray();
			
			final InputStream is = new ByteArrayInputStream(reporteByte);
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Cache-Control","no-cache, no-store, must-revalidate");
			headers.add("Expires", "0");
			headers.add("Content-Disposition", "attachment;filename=test.pdf");
			
			return ResponseEntity.ok().headers(headers).contentLength(reporteByte.length)
					.contentType(MediaType.parseMediaType("application/octet-stream"))
					.body(new InputStreamResource(is));
			
			/*return Response.status(Response.Status.OK).entity(is)
					.header("Cache-Control","private, max-age=1")
					.header("Content-Length", reporteByte.length+"")
					.header("Expires", "0")
					.header("Content-Disposition", "attachment;filename="+alumnoNombre+".pdf").build();
			*/
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	/**
	 * Obtiene los pagos en un periodo de fechas
	 * @param start fecha inicial
	 * @param end fecha final
	 * @return reporte excel
	 */
	//@RequestMapping(value="/reporte/pagos/fechas/{start}/{end}", method = RequestMethod.GET,
//			produces="application/xls")
	public ResponseEntity<InputStreamResource> reportPagos( 
			@PathVariable("start") String start, @PathVariable("end") String end){
		
		logger.info("Reporte de pagos en fechas: " + start +"-" + end);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		
		List<AlumnoReportDailyVO> lst;
		Calendar calInit = Calendar.getInstance();
		Calendar calFin = Calendar.getInstance();
		Set<String> setConceptos;
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet;
		XSSFRow row;
		XSSFCell cell;
		int fila;
		int columna;
		float total;
		float totalFinal = 0;
		
		byte[] reporteByte;
		
		try {
			calInit.setTime(sdf.parse(start));
			calInit.set(Calendar.HOUR_OF_DAY, 0);
			calInit.set(Calendar.MINUTE, 0);
			calInit.set(Calendar.SECOND, 0);
			
			calFin.setTime(sdf.parse(end));
			calFin.set(Calendar.HOUR_OF_DAY, 23);
			calFin.set(Calendar.MINUTE, 59);
			calFin.set(Calendar.SECOND, 59);
			
			lst = null;// alumnoPagoBitacoraService.getPagosBetweenFechaPago(calInit.getTime(), calFin.getTime());
			setConceptos = new HashSet<String>();
			
			//Obtener los conceptos de los pagos
//			for (AlumnoReportDailyVO alumnoReportVO : lst) {
//				setConceptos.add(alumnoReportVO.getConcepto());
//			}
			
			//Crear el excel
			sheet = workbook.createSheet("Pagos");
			fila = 0;
			
			//Iterar los conceptos
			Iterator<String> it = setConceptos.iterator();
			String concepto;
			while(it.hasNext()){
				concepto = it.next();
				sheet.addMergedRegion(new CellRangeAddress(fila,fila,0,3));
				row = sheet.createRow(fila++);
				columna = 0;
				cell = row.createCell(columna);
				
				//Se pone el concepto como titulo
				cell.setCellValue(concepto.toUpperCase());
				cell.setCellStyle(cellBorderBold(workbook));
				
				//Nueva fila
				row = sheet.createRow(fila++);
				columna = 0;
				//Se ponen titulos
				cell = row.createCell(columna++);
				cell.setCellValue("NOMBRE");
				cell.setCellStyle(cellBorderBold(workbook));
				
				cell = row.createCell(columna++);
				cell.setCellValue("GRADO");
				cell.setCellStyle(cellBorderBold(workbook));
				
				cell = row.createCell(columna++);
				cell.setCellValue("USO SALDO");
				cell.setCellStyle(cellBorderBold(workbook));

				cell = row.createCell(columna++);
				cell.setCellValue("MONTO");
				cell.setCellStyle(cellBorderBold(workbook));
				
				total = 0;
				/*for (AlumnoReportDailyVO alumnoReportVO : lst) {
					if(concepto.equals(alumnoReportVO.getConcepto())){
						row = sheet.createRow(fila++);
						columna = 0;
						
						cell = row.createCell(columna++);
						cell.setCellValue(alumnoReportVO.getNombre());
						cell.setCellStyle(cellBorder(workbook));
						
						cell = row.createCell(columna++);
						cell.setCellValue(alumnoReportVO.getGrado());
						cell.setCellStyle(cellBorder(workbook));
						
						cell = row.createCell(columna++);
						cell.setCellValue(alumnoReportVO.isSaldo()?"X":"");
						cell.setCellStyle(cellBorder(workbook));
						
						cell = row.createCell(columna++);
						cell.setCellValue(alumnoReportVO.getPago());
						cell.setCellStyle(cellBorder(workbook));
						
						if(!alumnoReportVO.isSaldo()){
							total += alumnoReportVO.getPago();
						}
					}
				}*/
				//El total se pone en una columna fuera de la tabla para poder hacer operaciones
				//TOTAL
				columna = 4;
				
				//sheet.addMergedRegion(new CellRangeAddress(fila,fila,0,2));
				row = sheet.createRow(fila++);
				cell = row.createCell(columna++);
				cell.setCellValue("TOTAL");
				//cell.setCellStyle(cellBorder(workbook));
				
				cell = row.createCell(columna);
				cell.setCellValue(total);
				//cell.setCellStyle(cellBorder(workbook));
				
				row = sheet.createRow(fila++);
				
				totalFinal += total;
			}
			
			row = sheet.createRow(fila++);
			columna = 4;
			
			//sheet.addMergedRegion(new CellRangeAddress(fila,fila,0,2));
			row = sheet.createRow(fila++);
			cell = row.createCell(columna++);
			cell.setCellValue("TOTAL");
			cell.setCellStyle(cellFont14Bold(workbook));
			
			cell = row.createCell(columna);
			cell.setCellValue(totalFinal);
			cell.setCellStyle(cellFont14Bold(workbook));
			
			// OutputStream
			final ByteArrayOutputStream report = new ByteArrayOutputStream();
			workbook.write(report);
			workbook.close();

			reporteByte = report.toByteArray();
			
			final InputStream is = new ByteArrayInputStream(reporteByte);
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Cache-Control","no-cache, no-store, must-revalidate");
			headers.add("Expires", "0");
			headers.add("Content-Disposition", "attachment;filename=Reporte Pagos.xlsx");
			
			return ResponseEntity.ok().headers(headers).contentLength(reporteByte.length)
					.contentType(MediaType.parseMediaType("application/octet-stream"))
					.body(new InputStreamResource(is));
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return null;
		
	}
	
	//////////////////////////////////////////
	//funciones de las celdas
	//////////////////////////////////////////
	
	//Pone bordes a las celdas
	private CellStyle cellBorder(XSSFWorkbook wb){
	    CellStyle style = wb.createCellStyle();
	    style.setBorderBottom(BorderStyle.THIN);
	    style.setBorderLeft(BorderStyle.THIN);
	    style.setBorderRight(BorderStyle.THIN);
	    style.setBorderTop(BorderStyle.THIN);
	    
	    return style;
	}
	
	//Pone bordes a las celdas y letras en negritas
	private CellStyle cellBorderBold(XSSFWorkbook wb){
		CellStyle style = wb.createCellStyle();
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderTop(BorderStyle.THIN);
		
		Font font = wb.createFont();
		font.setBold(true);
		
		style.setFont(font);
		style.setAlignment(HorizontalAlignment.CENTER);
		
		return style;
	}
	
	//Pone bordes a las celdas y letras en negritas
	private CellStyle cellFont14Bold(XSSFWorkbook wb){
		CellStyle style = wb.createCellStyle();
//		style.setBorderBottom(BorderStyle.THIN);
//		style.setBorderLeft(BorderStyle.THIN);
//		style.setBorderRight(BorderStyle.THIN);
//		style.setBorderTop(BorderStyle.THIN);
		
		Font font = wb.createFont();
		font.setBold(true);
		font.setFontHeightInPoints((short)14);
		
		style.setFont(font);
		style.setAlignment(HorizontalAlignment.CENTER);
		
		return style;
	}
	

}
