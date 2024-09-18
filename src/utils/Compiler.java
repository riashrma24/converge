package utils;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;
import java.util.Arrays;

public class Compiler
{
public static void compile(String sourceDir, String outputDir) throws Exception
{
JavaCompiler compiler=ToolProvider.getSystemJavaCompiler();
File sourceFolder=new File(sourceDir);
File[] javaFiles=sourceFolder.listFiles((dir,name)->name.endsWith(".java"));

if(javaFiles==null || javaFiles.length==0)
{
throw new Exception("Error : No java files to compile");
}

for(File javaFile : javaFiles)
{
int result=compiler.run(null,null,null,"-d",outputDir,javaFile.getPath());
if(result!=0)throw new Exception("Error compiling : "+javaFile.getName());
}

}
}