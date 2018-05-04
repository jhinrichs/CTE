package reporting;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.sql.rowset.RowSetWarning;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelExport {

	XSSFWorkbook workbook = new XSSFWorkbook();
	XSSFSheet sheet = workbook.createSheet("First Sheet");

	
	
	int actualRow = 0;
	int actualColumn = 0;


	public ExcelExport(int maxRows, int maxColumns) {

	}

	public void write(int rowNumber,int columnNumber,  String value) {
		XSSFCell myCell = getOrCreateCell(rowNumber, columnNumber);
		myCell.setCellValue(value);
	}
	public void write(int rowNumber,int columnNumber,  int value) {
		XSSFCell myCell = getOrCreateCell(rowNumber, columnNumber);
		myCell.setCellType(CellType.NUMERIC);
		myCell.setCellValue(value);
	}

	/**
	 * gets a cell. If it doesnt exist yet it will create it
	 * @param rowNumber
	 * @param columnNumber
	 * @return
	 */
	private XSSFCell getOrCreateCell(int rowNumber, int columnNumber) {
		XSSFRow myRow = null;
		try {
			 myRow = sheet.getRow(rowNumber);

		} catch (Exception e) {

			
		}
		if(myRow == null) {
			 myRow = sheet.createRow(rowNumber);
		}
		XSSFCell myCell = myRow.createCell(columnNumber);
		return myCell;
	}

	public void save() {

		try {
			workbook.write(new FileOutputStream("C:\\Users\\jonas\\OneDrive\\Dokumente\\Uni\\BA\\Bachelor Arbeit-DESKTOP-2ID5GM5\\output\\testfile.xlsx"));
			workbook.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}



}
