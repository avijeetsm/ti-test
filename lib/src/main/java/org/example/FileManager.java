package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A utility class for basic file operations.
 */
public class FileManager {
    
    private final String baseDirectory;
    
    /**
     * Creates a new FileManager with the specified base directory.
     * 
     * @param baseDirectory the base directory for file operations
     */
    public FileManager(String baseDirectory) {
        this.baseDirectory = baseDirectory;
        createDirectoryIfNotExists(baseDirectory);
    }
    
    /**
     * Creates a directory if it doesn't exist.
     * 
     * @param directoryPath the path of the directory to create
     * @return true if the directory exists or was successfully created, false otherwise
     */
    public boolean createDirectoryIfNotExists(String directoryPath) {
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            return directory.mkdirs();
        }
        return directory.isDirectory();
    }
    
    /**
     * Writes content to a file.
     * 
     * @param fileName the name of the file
     * @param content the content to write
     * @return true if the write operation was successful, false otherwise
     * @throws IOException if an I/O error occurs
     */
    public boolean writeToFile(String fileName, String content) throws IOException {
        System.out.println("writeToFile");
        if (fileName == null || fileName.trim().isEmpty()) {
            throw new IllegalArgumentException("File name cannot be null or empty");
        }
        
        Path filePath = Paths.get(baseDirectory, fileName);
        Files.write(filePath, content.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        return Files.exists(filePath);
    }
    
    /**
     * Reads content from a file.
     * 
     * @param fileName the name of the file
     * @return the content of the file as a string
     * @throws IOException if an I/O error occurs
     */
    public String readFromFile(String fileName) throws IOException {
        if (fileName == null || fileName.trim().isEmpty()) {
            throw new IllegalArgumentException("File name cannot be null or empty");
        }
        
        Path filePath = Paths.get(baseDirectory, fileName);
        if (!Files.exists(filePath)) {
            throw new IOException("File does not exist: " + fileName);
        }
        
        return new String(Files.readAllBytes(filePath));
    }
    
    /**
     * Lists all files in the base directory.
     * 
     * @return a list of file names
     * @throws IOException if an I/O error occurs
     */
    public List<String> listFiles() throws IOException {
        Path path = Paths.get(baseDirectory);
        return Files.list(path)
                .filter(Files::isRegularFile)
                .map(p -> p.getFileName().toString())
                .collect(Collectors.toList());
    }
    
    /**
     * Deletes a file.
     * 
     * @param fileName the name of the file to delete
     * @return true if the file was successfully deleted, false otherwise
     * @throws IOException if an I/O error occurs
     */
    public boolean deleteFile(String fileName) throws IOException {
        if (fileName == null || fileName.trim().isEmpty()) {
            throw new IllegalArgumentException("File name cannot be null or empty");
        }
        
        Path filePath = Paths.get(baseDirectory, fileName);
        return Files.deleteIfExists(filePath);
    }
}
