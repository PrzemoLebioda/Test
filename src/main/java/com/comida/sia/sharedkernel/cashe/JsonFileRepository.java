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

public class JsonFileRepository implements FileRepository {
	
	private String fileName;
	private String path;
	
	public JsonFileRepository(String path, String fileName) {
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
			stringBuilder.append(line);
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
	
	private void createDictionary() {
		File directory = new File(path);	
		if(!directory.exists()) {
			directory.mkdirs();
		}
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

	private String getFilePath() {
		if(path.endsWith("\\")) {
			return path + fileName + "." + FileType.JSON.getName();
		} else {
			return path + "\\" +  fileName + "." + FileType.JSON.getName();
		}
	}
}
