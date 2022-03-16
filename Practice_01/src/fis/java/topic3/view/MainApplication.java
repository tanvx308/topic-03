package fis.java.topic3.view;

import java.util.List;

import fis.java.topic3.model.Transaction;
import fis.java.topic3.service.impl.ExcelService;

public class MainApplication {
	private static String FILE_PATH = "D:\\FIS\\Java\\Topic03\\VCB.xlsx";

	private static String FILE_PATH_WRITE = "D:\\FIS\\Java\\Topic03\\Data.xlsx";

	public static void main(String[] args) {
		try {
			ExcelService es = new ExcelService();
			List<Transaction> transactions = es.readFile(FILE_PATH);
			
			boolean check = es.checkTransfer("9366202468", "VU XUAN TAN chuyen tien", 173000d, transactions);
			
			System.out.println(check);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
