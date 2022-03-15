package fis.java.topic3.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import fis.java.topic3.exception.CustomException;
import fis.java.topic3.model.Transaction;
import fis.java.topic3.service.FileService;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelService implements FileService {

	// Xử lí đọc file
	/*
	 * Hàm lấy về đối tượng Workbook đuôi xls -> HSSF đuôi xlsx -> XSSF
	 */
	private Workbook getWorkbook(InputStream inputStream, String filePath) throws Exception {
		Workbook workbook = null;
		if (filePath.endsWith("xlsx")) {
			workbook = new XSSFWorkbook(inputStream);
		} else if (filePath.endsWith("xls")) {
			workbook = new HSSFWorkbook(inputStream);
		} else {
			throw new CustomException("File này không phải là file excel");
		}
		return workbook;
	}
	
	//hàm lấy về sheet cần đọc
	private Sheet getSheet(Workbook workbook, int number) {
		return workbook.getSheetAt(number);
	}

	/*
	 * hàm lấy giá trị ở 1 cell
	 */

	private Object getValue(Cell cell) {
		Object value = null;
		CellType cellType = cell.getCellTypeEnum();
		switch (cellType) {
		case BOOLEAN: {
			value = cell.getBooleanCellValue();
			break;
		}
		case NUMERIC: {
			value = cell.getNumericCellValue();
			break;
		}
		case STRING: {
			value = cell.getStringCellValue();
			break;
		}
		case _NONE: {
		}
		case BLANK: {
		}
		case ERROR: {
			break;
		}
		default:
			break;
		}
		return value;
	}
	
	//get value from a row
	private Transaction getValueRow(Row row) {
		Transaction transaction = new Transaction();
		
		//lấy danh sách tất cả các cell
		Iterator<Cell> cells = row.cellIterator();
		
		//tiến hành đọc và gán giá trị cho biến tran
		while(cells.hasNext()) {
			Cell current = cells.next();
			
		}
		return transaction;
	}

	@Override
	public List<Transaction> readFile(String filePath) throws Exception{
		// TODO Auto-generated method stub
		List<Transaction> result = new ArrayList();
		
		//get file from path
		InputStream inputStream = new FileInputStream(new File(filePath));
		
		//get workbook
		Workbook workbook = getWorkbook(inputStream, filePath);
		
		//get sheet
		Sheet sheet = getSheet(workbook, 0);
		
		//get all row in sheet
		Iterator<Row> rows = sheet.iterator();
		
		//loop for get value
		
		
		
		return result;
	}

	// Xử lí ghi file

	@Override
	public void writeFile(String filePath, List<Transaction> list) {
		// TODO Auto-generated method stub

	}

}
