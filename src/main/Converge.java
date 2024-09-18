import utils.Compiler;
import utils.JarPackager;
import java.io.*;
public class Converge
{
public static void main(String gg[])
{
if(gg.length!=1)
{
System.out.println("Usage : java -jar Converge.jar <command_name>");
return;
}
String command=gg[0];
if(command.equalsIgnoreCase("build"))
{
buildProject();
}
else if(command.equalsIgnoreCase("clean"))
{
cleanProject();
}
else if(command.equalsIgnoreCase("package"))
{
packageProject();
}
else
{
System.out.println("Error : Unkown command");
}
}

// Method to build the project (compile source files)
    public static void buildProject() {
        System.out.println("Building the project...");
        // Call your Compiler class or compilation logic here
        try {
            Compiler.compile("src/main", "classes");
            System.out.println("Build successful.");
        } catch (Exception e) {
            System.err.println("Build failed: " + e.getMessage());
        }
    }

    // Method to clean the project (delete compiled files)
    public static void cleanProject() {
        System.out.println("Cleaning the project...");
        // Logic to delete the 'classes' directory or any build artifacts
        File classesDir = new File("classes");
        if (classesDir.exists()) {
            deleteDirectory(classesDir);
            System.out.println("Clean successful.");
        } else {
            System.out.println("Nothing to clean.");
        }
    }

    // Method to package the project (create JAR file)
    public static void packageProject() {
        System.out.println("Packaging the project...");
        try {
            JarPackager.packageToJar("output.jar", "classes");
            System.out.println("Package successful.");
        } catch (Exception e) {
            System.err.println("Package failed: " + e.getMessage());
        }
    }

    // Utility method to recursively delete a directory
    public static void deleteDirectory(File directory) {
        File[] allContents = directory.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        directory.delete();
    }

}