package edu.stanford.nlp.parser.nndep.demo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.lang.CharSequence;
public class data {private static char[] i;
static String s;

public String[] message()  {
	int i = 0;
	int j = 0;
	
	String[] mem = new String[70];
	try {
	Class.forName("com.mysql.jdbc.Driver");  //load jdbc
	Connection con;
	
		con = DriverManager.getConnection(  "jdbc:mysql://localhost:3306/prema","root","shivaraj");
	
   // String user   = "root";
   // String password = "shivaraj";
		

   // Connection m_Connection = DriverManager.getConnection(url,user,password);
	 for( i=0;i<=70;i++)
	 {
		 
    Statement m_Statement = con.createStatement(); 
   // String query = "SELECT * FROM Symptom";

	  
    ResultSet m_ResultSet = m_Statement.executeQuery("SELECT * FROM comb where comb_id='" + i + "'");
   // m_Statement.executeUpdate("INSERT INTO extracted" + "values ('"+i+"',)");
    
    while (m_ResultSet.next()) { 
  
     // System.out.println( m_ResultSet.getString(2) );
      
      mem[j]=m_ResultSet.getString(3);
      j++;
    }
    
	   }
	 
	 
	 System.out.println("memory:");
	// for(int k=0;k<=j;k++)
    //System.out.println(mem[k]);
	// return mem;
	}
	 catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
		} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	//System.out.println(mem);
	return mem;
  }
public String[] message1()  {
	int i = 0;
	int j = 0;
	

	  
	String[] mem1 = new String[70];
	
	try {
		Class.forName("com.mysql.jdbc.Driver");  //load jdbc
		Connection con;
		
			con = DriverManager.getConnection(  "jdbc:mysql://localhost:3306/prema","root","shivaraj");
	
   // String user   = "root";
   // String password = "shivaraj";


   // Connection m_Connection = DriverManager.getConnection(url,user,password);
	 for( i=0;i<=70;i++)
	 {
		 
    Statement m_Statement = con.createStatement();
   // String query = "SELECT * FROM Symptom"; 

    
    ResultSet m_ResultSet = m_Statement.executeQuery("SELECT Category FROM comb where comb_id='"+i+"'");
    
    while (m_ResultSet.next()) {
 
     // System.out.println( m_ResultSet.getString(2) );
      
      mem1[j]=m_ResultSet.getString(1);
      j++;
    }
    
    
	   }
	 
	 
	// System.out.println("memory:");
	// for(int k=0;k<=j;k++)
    //System.out.println(mem[k]);
	// return mem;
	}
	 catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	//System.out.println(mem);
	return mem1;
	  //load jdb
	   }

}



