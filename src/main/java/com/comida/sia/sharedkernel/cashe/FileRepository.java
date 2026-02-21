package com.comida.sia.sharedkernel.cashe;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface FileRepository {
	public String read() throws FileNotFoundException, IOException;
	public void write(String content) throws IOException;
	public void delete() throws IOException;
	
	//TODO: Consider to create abstract class that implements this interface. The abstract class should implements shared method which are use by both JSON and CSV file repository
}
