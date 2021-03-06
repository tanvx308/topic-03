package fis.java.topic3.service;

import java.util.List;

import fis.java.topic3.model.Transaction;

public interface FileService {
	List<Transaction> readFile(String filePath) throws Exception;
	
	void writeFile(String filePath, List<Transaction> list);
}
