package fis.java.topic3.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	public static final int SO_LENH_GIAO_DICH = 0;
	public static final int THOI_GIAN_GIAO_DICH = 1;
	public static final int TAI_KHOAN_NGUON = 2;
	public static final int TAI_KHOAN_NGUOI_HUONG = 3;
	public static final int TEN_NGUOI_HUONG = 4;
	public static final int TEN_NGAN_HANG_HUONG = 5;
	public static final int SO_TIEN = 6;
	public static final int LOAI_PHI = 7;
	public static final int SO_TIEN_PHI = 8;
	public static final int NOI_DUNG_CHUYEN_TIEN = 9;

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

	// Xử lí đọc file

	// hàm lấy về sheet cần đọc
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

	// get value from a row
	private Transaction getValueRow(Row row) throws ParseException {
		Transaction transaction = new Transaction();

		// lấy danh sách tất cả các cell
		Iterator<Cell> cells = row.cellIterator();

		// tiến hành đọc và gán giá trị cho biến tran
		while (cells.hasNext()) {
			Cell current = cells.next();

			Object value = getValue(current);
			if (value == null || value.toString().isEmpty()) {
				continue;
			}

			int columnIndex = current.getColumnIndex();
			switch (columnIndex) {
			case SO_LENH_GIAO_DICH: {
				transaction.setSoLenhGiaoDich(value.toString());
				break;
			}
			case THOI_GIAN_GIAO_DICH: {
				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm E dd/MM/yyyy");
				transaction.setThoiGianGiaoDich(sdf.parse(value.toString()));
				break;
			}
			case TAI_KHOAN_NGUON: {
				transaction.setTaiKhoanNguon(value.toString());
				break;
			}
			case TAI_KHOAN_NGUOI_HUONG: {
				transaction.setTaiKhoanNguoiHuong(value.toString());
				break;
			}
			case TEN_NGUOI_HUONG: {
				transaction.setTenNguoiHuong(value.toString());
				break;
			}
			case TEN_NGAN_HANG_HUONG: {
				transaction.setTenNganHangHuong(value.toString());
				break;
			}
			case SO_TIEN: {
				transaction.setSoTien(Double.valueOf(value.toString()));
				break;
			}
			case LOAI_PHI: {
				transaction.setLoaiPhi(value.toString());
				break;
			}
			case SO_TIEN_PHI: {
				transaction.setSoTienPhi(Double.valueOf(value.toString()));
				break;
			}
			case NOI_DUNG_CHUYEN_TIEN: {
				transaction.setNoiDungChuyenTien(value.toString());
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + columnIndex);
			}

		}
		return transaction;
	}

	@Override
	public List<Transaction> readFile(String filePath) throws Exception {
		// TODO Auto-generated method stub
		List<Transaction> result = new ArrayList();

		// get file from path
		InputStream inputStream = new FileInputStream(new File(filePath));

		// get workbook
		Workbook workbook = getWorkbook(inputStream, filePath);

		// get sheet
		Sheet sheet = getSheet(workbook, 0);

		// get all row in sheet
		Iterator<Row> rows = sheet.iterator();

		// loop for get value

		while (rows.hasNext()) {
			Row row = rows.next();
			if (row.getRowNum() == 0) {
				continue;
			}
			Transaction transaction = getValueRow(row);
			result.add(transaction);
		}
		return result;
	}

	// Xử lí ghi file

	@Override
	public void writeFile(String filePath, List<Transaction> list) {
		// TODO Auto-generated method stub
		Workbook workbook = getWorkbook(filePath);

		// create sheet with name
		Sheet sheet = workbook.createSheet("Transactions");

		// bắt đầu viết dữ liệu

		for (int i = 0; i < list.size(); i++) {
			Row row = sheet.createRow(i);

			writeTransaction(list.get(i), row);
		}
		
		//tạo file excel
		createOutputFile(workbook, filePath);
		
		System.out.println("Thao tác hoàn thành!");
	}

	// create workbook
	private Workbook getWorkbook(String filePath) {
		Workbook workbook = null;

		if (filePath.endsWith("xlsx")) {
			workbook = new XSSFWorkbook();
		} else if (filePath.endsWith("xls")) {
			workbook = new HSSFWorkbook();
		} else {
			throw new IllegalArgumentException("Không phải file excel");
		}
		return workbook;
	}

	// write data
	private void writeTransaction(Transaction transaction, Row row) {
		Cell cell = row.createCell(SO_LENH_GIAO_DICH);
		cell.setCellValue(transaction.getSoLenhGiaoDich());

		cell = row.createCell(THOI_GIAN_GIAO_DICH);
		cell.setCellValue(transaction.getThoiGianGiaoDich().toString());

		cell = row.createCell(TAI_KHOAN_NGUON);
		cell.setCellValue(transaction.getTaiKhoanNguon());

		cell = row.createCell(TAI_KHOAN_NGUOI_HUONG);
		cell.setCellValue(transaction.getTaiKhoanNguoiHuong());

		cell = row.createCell(TEN_NGUOI_HUONG);
		cell.setCellValue(transaction.getTenNguoiHuong());

		cell = row.createCell(TEN_NGAN_HANG_HUONG);
		cell.setCellValue(transaction.getTenNganHangHuong());

		cell = row.createCell(SO_TIEN);
		cell.setCellValue(transaction.getSoTien());

		cell = row.createCell(LOAI_PHI);
		cell.setCellValue(transaction.getLoaiPhi());

		cell = row.createCell(SO_TIEN_PHI);
		cell.setCellValue(transaction.getSoTienPhi());

		cell = row.createCell(NOI_DUNG_CHUYEN_TIEN);
		cell.setCellValue(transaction.getNoiDungChuyenTien());

	}

	private void createOutputFile(Workbook workbook, String filePath){
		try (OutputStream os = new FileOutputStream(filePath)) {
			workbook.write(os);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//hàm kiểm tra giao dịch thanh toán đã được thực hiện chưa
	public boolean checkTransfer(String from, String message, Double amount, List<Transaction> transactions) {
		Transaction trans = transactions.stream().filter(i -> i.getTaiKhoanNguon().equals(from) 
				&& i.getNoiDungChuyenTien().equalsIgnoreCase(message)
				&& i.getSoTien().equals(amount)).findFirst().orElse(null);
		return trans != null;
	}
}
