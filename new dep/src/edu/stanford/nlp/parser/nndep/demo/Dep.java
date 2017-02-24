package edu.stanford.nlp.parser.nndep.demo;
import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.ling.TaggedWord;
import edu.stanford.nlp.parser.nndep.DependencyParser;
import edu.stanford.nlp.process.DocumentPreprocessor;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import edu.stanford.nlp.trees.GrammaticalStructure;
import edu.stanford.nlp.util.logging.Redwood;
import java.io.StringReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mysql.jdbc.PreparedStatement;

import java.io.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.lang.CharSequence;
public class Dep extends data{
	static data dataobject = new data();
	static String[] rr = dataobject.message();
	static String[] rr1 = dataobject.message1();
	
	
	private static Redwood.RedwoodChannels log = Redwood.channels(Dep.class);
	private static char[] retval;
	public static void main(String[] args) 
{
		
		//Dep obj=new Dep();
		// TODO Auto-generated method stub
		 String modelPath = DependencyParser.DEFAULT_MODEL;
		    String taggerPath = "edu/stanford/nlp/models/pos-tagger/english-left3words/english-left3words-distsim.tagger";

		    for (int argIndex = 0; argIndex < args.length; )
		    {
		      switch (args[argIndex]) 
		      {
		        case "-tagger":
		          taggerPath = args[argIndex + 1];
		          argIndex += 2; 
		          break;
		        case "-model":
		          modelPath = args[argIndex + 1];
		          argIndex += 2;
		          break;
		        default:
		          throw new RuntimeException("Unknown argument " + args[argIndex]);
		      }
		    }
		    for(int i=0;i<70;i++)
		    {
		    	 String w=null ;
				 String w2=null ;
				 String w3=null ;
				 String w4=null ;
		    	// Scanner scanner =new Scanner(System.in); 
                    System.out.println(rr[i]);
                   // System.out.println(rr1[i]);
                 //String text =scanner.nextLine();
                    w4=rr1[i];

                    MaxentTagger tagger = new MaxentTagger(taggerPath);
                    DependencyParser parser = DependencyParser.loadFromModelFile(modelPath);
                    DocumentPreprocessor tokenizer = new DocumentPreprocessor(new StringReader(rr[i]));
                    	for (List<HasWord> sentence : tokenizer) 
                    	{ 
                    			List<TaggedWord> tagged = tagger.tagSentence(sentence);
                    			GrammaticalStructure gs = parser.predict(tagged);
                    			// System.out.println(gs);
                    			System.out.println(gs.toString());
                    			String s =gs.toString(); 
                    				Pattern pattern1 = Pattern.compile("(?<=dobj).*.(?=)");
                    				Matcher matcher1 = pattern1.matcher(s);
                    				boolean found = false;
                    				
                    			while (matcher1.find()) 
                    			{
                    				w = matcher1.group().toString();
                    				String result = w.substring(w.indexOf("(") + 1, w.indexOf(")"));  
                    				w = result.replaceAll("[\\-[0-9],]", "");
                    				System.out.println("I found the dobj: ");// + matcher1.group().toString());
                    				System.out.println(w);
                    				
                    			    found = true;
                    			   
		                        }
		                          if (!found) 
		                          {
		                            System.out.println("I didn't found dobj");
		                          }  
                                    Pattern pattern2 = Pattern.compile("(?<=nsubj).*.(?=)");
		                            Matcher matcher2 = pattern2.matcher(s);
		                            boolean found2 = false;
		                            
		                        while (matcher2.find()) 
		                        {
		                        	 w2 = matcher2.group().toString();
		                        	String result2 = w2.substring(w2.indexOf("(") + 1, w2.indexOf(")"));  
		                        	w2 = result2.replaceAll("[\\-[0-9],]", "");
		                        	System.out.println("I found the nsubj: ");// + matcher2.group().toString());
		                        	System.out.println(w2);
		                        	found2 = true;
		                         }
		                           if (!found2) 
		                           {
		                            System.out.println("I didn't found the nsubj");
		                           }  
                                    Pattern pattern3 = Pattern.compile("(?<=compound).*.(?=)");
		                            Matcher matcher3 = pattern3.matcher(s);
		                            boolean found3 = false;
		 
		                         while (matcher3.find()) 
		                         {
			                         w3 = matcher3.group().toString();
			                        String result = w3.substring(w3.indexOf("(") + 1, w3.indexOf(")"));  
			                        w3 = result.replaceAll("[\\-[0-9],]", "");
			                        System.out.println("I found the compound: ");// + matcher1.group().toString());
			                        System.out.println(w3);
			                        found3 = true;
			                       
		                         }
		                           if (!found3)  
		                           {
		                        	System.out.println("I didn't found compound");
		                           }  
		
		                  }
                    	try {
                    		Class.forName("com.mysql.jdbc.Driver");  //load jdbc
                    		Connection con;
                    		
                    			con = DriverManager.getConnection(  "jdbc:mysql://localhost:3306/prema","root","shivaraj");
                    		
                    			// Create a statement object instance from the 
                                // connection
                                Statement stmt = con.createStatement();
                     
                                // We are going to execute an insert statement.  
                                // First you have to create a table that has an   
                                // ID, NAME and ADDRESS field. For ID you can use 
                                // an auto number, while NAME and ADDRESS are 
                                // VARCHAR fields
                                
                                String sql = "INSERT ignore  INTO result (dobj, nsubj,compound,category) " + 
                                        "VALUES ('"+w+"','"+w2+"' ,'"+w3+"','"+w4+"' )";
                     
                                // Call an execute method in the statement object 
                                // and passed the sql or query string to it.
                                stmt.execute(sql);
                     
                                // After this statement is executed you'll have a 
                                // record in your users table.
                    		
                    	    
                    		   } 
                    		 
                    	
                    		 catch (SQLException e) {
                    				// TODO Auto-generated catch block
                    				e.printStackTrace();
                    			} catch (ClassNotFoundException e) {
                    			// TODO Auto-generated catch block
                    			e.printStackTrace();
                    		}
                    	
                    	
                    	  }

          } 
		   
}
	
