package utils;

import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.util.*;

public class DependencyManager {

    private static final String MAVEN_CENTRAL_URL = "https://repo1.maven.org/maven2/";

    // Method to download dependencies based on entries in dependencies.txt
    public static void downloadDependencies(String dependencyFilePath, String libDir) throws Exception {
        File dependencies = new File(dependencyFilePath); // Correct variable name 'dependencyFilePath'
        if (!dependencies.exists()) {
            throw new FileNotFoundException("Dependency file not found: " + dependencyFilePath);
        }

        File libDirectory = new File(libDir);
        if (!libDirectory.exists()) {
            libDirectory.mkdir();
        }

        List<String> lines = Files.readAllLines(dependencies.toPath());
        for (String line : lines) {
            if (!line.trim().isEmpty()) {
                downloadDependency(line.trim(), libDir);
            }
        }
    }

    private static void downloadDependency(String dependency, String libDir) throws Exception {
        // Parse dependency in the form: groupId:artifactId:version
        String[] parts = dependency.split(":");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid dependency format: " + dependency);
        }

        String groupId = parts[0].replace('.', '/');
        String artifactId = parts[1];
        String version = parts[2];

        String jarFileName = artifactId + "-" + version + ".jar";
        String jarUrl = MAVEN_CENTRAL_URL + groupId + "/" + artifactId + "/" + version + "/" + jarFileName;

        File outputFile = new File(libDir + File.separator + jarFileName);
        if (outputFile.exists()) {
            System.out.println("Dependency already downloaded: " + jarFileName);
        } else {
            downloadFile(jarUrl, outputFile);
            System.out.println("Downloaded: " + jarFileName);
        }
    }

    private static void downloadFile(String fileUrl, File outputFile) throws IOException {
        URL url = new URL(fileUrl);
        try (InputStream in = url.openStream();
             FileOutputStream fos = new FileOutputStream(outputFile)) {

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: Dependency not found at " + fileUrl);
        }
    }

    public static void main(String[] args) {
        try {
            // Example usage
            String dependencyFilePath = "dependencies.txt";  // Path to the dependencies file
            String libDir = "lib";  // Directory to store downloaded dependencies
            downloadDependencies(dependencyFilePath, libDir);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
