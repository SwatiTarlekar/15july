import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBUtil {
	
	public static Connection getConnection()
	{
		Connection conn=null;
		String dbU=null;
		String dbP=null;
		String dbS=null;
		
		try
		{
			FileInputStream fis=new FileInputStream(System.getProperty("user.dir"));
			Properties properties=new Properties();
			try{
				properties.load(fis);
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		//passward decrypt
			dbU=properties.getProperty("dbUser");
			dbP=properties.getProperty("dbPassword");
			dbS=properties.getProperty("dbSchema");
		}
		catch(FileNotFoundException e1)
		{
			e1.printStackTrace();
		}
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3308/", + dbS,dbU,dbP);
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		return conn;
	}
}
