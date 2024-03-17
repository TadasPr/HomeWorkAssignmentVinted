package org.shipment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFile {
    //Method is responsible for reading data from input.txt file and storing it in StringBuilder data,
    // if file not fount try catch added for exception handling
    public StringBuilder readFileData(String pathToFile)  {
        StringBuilder data = new StringBuilder();
        try {
            File fileObj = new File(pathToFile);
            Scanner fileScanner = new Scanner(fileObj);
            while (fileScanner.hasNextLine()) {
                data.append(fileScanner.nextLine()).append("\n");
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred. " + e.getMessage());
        }
        return data;
    }
}
