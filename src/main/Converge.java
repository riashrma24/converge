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
}