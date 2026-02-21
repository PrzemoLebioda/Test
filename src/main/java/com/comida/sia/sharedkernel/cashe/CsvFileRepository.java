package com.comida.sia.sharedkernel.cashe;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.comida.sia.sharedkernel.AssertionConcern;

public class CsvFileRepository implements FileRepository{

	private String fileName;
	private String path;
	
	public CsvFileRepository(String path, String fileName) {
		setPath(path);
		setFileName(fileName);
	}
	
	private void setPath(String path) {
		AssertionConcern.assertNotEmpty(path, "Path is mandatory in order to create json file repository");
		this.path = path;
	}
	
	private void setFileName(String fileName) {
		AssertionConcern.assertNotEmpty(fileName, "File name is mandatory in order to create json file repository");
		this.fileName = fileName;
	}
	
	@Override
	public String read() throws FileNotFoundException, IOException {
		BufferedReader reader = new BufferedReader(
				new FileReader(
						getFilePath()
					)
				);
		
		StringBuilder stringBuilder = new StringBuilder();
		String line = null;

		while ((line = reader.readLine()) != null) {
			stringBuilder.append(line + "\r\n");
		}
		
		reader.close();
		
		return stringBuilder.toString();
	}

	@Override
	public void write(String content) throws IOException {
		AssertionConcern.assertNotEmpty(content, "Content for cache can't be empty");
		
		createDictionary();
		BufferedWriter writer = new BufferedWriter(
				new FileWriter(
						getFilePath()
					)
				);
		
	    writer.write(content);
	    
	    writer.close();
	}

	@Override
	public void delete() throws IOException {
		if(fileExists()) {
			Files.delete(
					Paths.get(
							getFilePath()
						)
					);
		}
	}
	
	private Boolean fileExists() {
		return Files.exists(
				Paths.get(
						getFilePath()
					)
				);
	}

	private void createDictionary() {
		File directory = new File(path);	
		if(!directory.exists()) {
			directory.mkdirs();
		}
	}
	
	private String getFilePath() {
		if(path.endsWith("\\")) {
			return path + fileName + "." + FileType.CSV.getName();
		} else {
			return path + "\\" +  fileName + "." + FileType.CSV.getName();
		}
	}
}
