package utils;
import java.io.*;
import java.util.jar.*;

public class JarPackager
{
public static void packageToJar(String outputJar, String compiledClassesDir) throws IOException
{
FileOutputStream fos=new FileOutputStream(outputJar);
JarOutputStream jarOut=new JarOutputStream(fos);

File classesDir=new File(compiledClassesDir);
for(File file : classesDir.listFiles())
{
JarEntry entry=new JarEntry(file.getName());
jarOut.putNextEntry(entry);
FileInputStream fis = new FileInputStream(file);
byte[] buffer = new byte[1024];
int bytesRead;
while ((bytesRead = fis.read(buffer)) != -1) {
jarOut.write(buffer, 0, bytesRead);
}
fis.close();
jarOut.closeEntry();
}
jarOut.close();
}

}