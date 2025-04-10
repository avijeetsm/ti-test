/*
 * Test file for FileManager class
 */
package org.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileManagerTest {
    
    private static final String TEST_DIR = System.getProperty("java.io.tmpdir") + "/filemanager_test";
    private static final String TEST_FILE = "test_file.txt";
    private static final String TEST_CONTENT = "Hello, this is a test content.";
    
    private FileManager fileManager;
    
    @Before
    public void setUp() {
        fileManager = new FileManager(TEST_DIR);
    }
    
    @After
    public void tearDown() throws IOException {
        // Clean up test directory
        Path testDir = Paths.get(TEST_DIR);
        if (Files.exists(testDir)) {
            Files.list(testDir).forEach(file -> {
                try {
                    Files.deleteIfExists(file);
                } catch (IOException e) {
                    // Ignore
                }
            });
            Files.deleteIfExists(testDir);
        }
    }
    
    @Test
    public void testCreateDirectory() {
        assertTrue(fileManager.createDirectoryIfNotExists(TEST_DIR));
        
        Path path = Paths.get(TEST_DIR);
        assertTrue(Files.exists(path));
        assertTrue(Files.isDirectory(path));
    }
    
    @Test
    public void testWriteAndReadFile() throws IOException {
        // Write to file
        assertTrue(fileManager.writeToFile(TEST_FILE, TEST_CONTENT));
        
        // Read from file
        String content = fileManager.readFromFile(TEST_FILE);
        assertEquals(TEST_CONTENT, content);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testWriteWithEmptyFileName() throws IOException {
        fileManager.writeToFile("", TEST_CONTENT);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testReadWithEmptyFileName() throws IOException {
        fileManager.readFromFile("");
    }
    
    @Test(expected = IOException.class)
    public void testReadNonExistentFile() throws IOException {
        fileManager.readFromFile("non_existent_file.txt");
    }
    
    @Test
    public void testListFiles() throws IOException {
        // Create a few test files
        fileManager.writeToFile("file1.txt", "Content 1");
        fileManager.writeToFile("file2.txt", "Content 2");
        
        List<String> files = fileManager.listFiles();
        assertEquals(2, files.size());
        assertTrue(files.contains("file1.txt"));
        assertTrue(files.contains("file2.txt"));
    }
    
    @Test
    public void testDeleteFile() throws IOException {
        // Create a file
        fileManager.writeToFile(TEST_FILE, TEST_CONTENT);
        
        // Check it exists
        Path filePath = Paths.get(TEST_DIR, TEST_FILE);
        assertTrue(Files.exists(filePath));
        
        // Delete it
        assertTrue(fileManager.deleteFile(TEST_FILE));
        
        // Check it no longer exists
        assertFalse(Files.exists(filePath));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testDeleteWithEmptyFileName() throws IOException {
        fileManager.deleteFile("");
    }
    
    @Test
    public void testDeleteNonExistentFile() throws IOException {
        // Should not throw an exception, but return false
        assertFalse(fileManager.deleteFile("non_existent_file.txt"));
    }
}
