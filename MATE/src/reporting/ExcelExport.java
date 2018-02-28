package reporting;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.sql.rowset.RowSetWarning;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelExport {
	
	
	

	XSSFWorkbook workbook = new XSSFWorkbook();
	XSSFSheet sheet = workbook.createSheet("First Sheet");

	XSSFRow row = sheet.createRow(0);
	XSSFCell cell = row.createCell(0);

	int actualRow=0;
	int actualColumn=0;

	public ExcelExport(String[][] table) {

		writeTreeData(table,0);
	}
	
	public void write(int columnNumber, int rowNumber, String value ) {
		sheet.createRow(rowNumber).createCell(columnNumber).setCellValue(value);
	}

	public void writeTreeData(String[][] table, int rowNumber) {
		
		actualRow = rowNumber;
		
		for(String[] data : table) {
			row = sheet.createRow(actualRow);
			
			row.createCell(0).setCellValue(data[0]);
			row.createCell(1).setCellValue(data[1]);
		}
		
	}

	public void save() throws FileNotFoundException, IOException {

		 workbook.write(new FileOutputStream("D:\\Bachelor Arbeit\\Output\\testfile.xlsx"));

	}

}
