package app.util;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import app.entities.User;

@Component("user/listUser")
public class ListUserPdf extends AbstractPdfView {

	@SuppressWarnings("unchecked")
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception
	{		
		List<User> listUser =  (List<User>) model.get("listUser"); // Extrae el Model para usarlo
		
		// Las propiedades del documento
		document.setPageSize(PageSize.LETTER.rotate());
		document.setMargins(-20, -20, 50, 20);
		document.open();
		
		// Fuentes			
		Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, Color.blue);
		Font fontTitleColumns = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, Color.blue);
		Font fontDateCell = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 13, Color.black);
		
		// Tablas
		PdfPTable tableUser = new PdfPTable(4);	
		PdfPTable tableTitle = new PdfPTable(1);
		PdfPTable tableFooter = new PdfPTable(1);
		
		// Celdas
		PdfPCell cell = null;		
		
		// Config Celdas		
		cell = new PdfPCell(new Phrase("LISTADO DE USUARIOS", fontTitle));
		cell.setBorder(0);
		cell.setBackgroundColor(new Color(40, 190, 138));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_CENTER);
		cell.setPadding(30);
		tableTitle.addCell(cell);		
		
		// Config Tablas
		tableTitle.setSpacingAfter(30);
		
		tableUser.setHorizontalAlignment(Element.ALIGN_CENTER);
		tableUser.setWidths(new float[] {2f, 2f, 2f, 4f});		
		
		cell = new PdfPCell(new Phrase("Nombre", fontTitleColumns));
		cell.setBackgroundColor(Color.LIGHT_GRAY);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_CENTER);
		cell.setPadding(10);
		tableUser.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Apellido", fontTitleColumns));
		cell.setBackgroundColor(Color.LIGHT_GRAY);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_CENTER);
		cell.setPadding(10);
		tableUser.addCell(cell);	
		
		cell = new PdfPCell(new Phrase("Usuario", fontTitleColumns));
		cell.setBackgroundColor(Color.LIGHT_GRAY);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_CENTER);
		cell.setPadding(10);
		tableUser.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Email", fontTitleColumns));
		cell.setBackgroundColor(Color.LIGHT_GRAY);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_CENTER);
		cell.setPadding(10);
		tableUser.addCell(cell);
		
		for (User elemento : listUser)
		{
			cell = new PdfPCell(new Phrase(elemento.getName(), fontDateCell));
			cell.setPadding(5);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_CENTER);
			tableUser.addCell(cell);
			
			cell = new PdfPCell(new Phrase(elemento.getSurname(), fontDateCell));
			cell.setPadding(5);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_CENTER);
			tableUser.addCell(cell);
			
			cell = new PdfPCell(new Phrase(elemento.getUserName(), fontDateCell));
			cell.setPadding(5);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_CENTER);
			tableUser.addCell(cell);
			
			cell = new PdfPCell(new Phrase(elemento.getEmail(), fontDateCell));
			cell.setPadding(5);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_CENTER);
			tableUser.addCell(cell);
		}
		
		cell = new PdfPCell(new Phrase("Auditor: " + SecurityContextHolder.getContext().getAuthentication().getName(), fontTitle));
		cell.setBorder(0);
		cell.setBackgroundColor(new Color(40, 190, 138));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_CENTER);
		cell.setPadding(15);
		tableFooter.setSpacingAfter(30);
		tableFooter.setSpacingBefore(30);
		tableFooter.addCell(cell);
		
		document.add(tableTitle);
		document.add(tableUser);
		document.add(tableFooter);
	}

}
