import java.util.StringTokenizer;
import java.util.*;
import java.io.*;
import java.net.*;
public class test2 {
    public static void main(String args[]){
		try{
			String accession="128UP_DROME";
			String site = "http://pfam.xfam.org/protein/" + accession + "?output=xml";
			InputStreamReader isr = new InputStreamReader(new URL(site).openStream(), "UTF-8");
			Thread.sleep(100);
			BufferedReader in = new BufferedReader(isr); 
			while (in.ready()) {
				String s = in.readLine();
				if (s.contains("<match accession=\"")) {
					s = s.substring(s.indexOf("<match accession=\"") + 1);
					s = s.substring(s.indexOf("PF"));
					s = s.substring(0, s.indexOf('"'));
					System.out.println(s);
				}
				
				if (s.contains("species_name=\"")) {
					s = s.substring(s.indexOf("species_name=\"") + 1);
					s = s.substring(s.indexOf('"')+1);
					s = s.substring(0, s.indexOf('"'));
					System.out.println(s);
					
				}
				if(s.contains(" type=\"")) {

						s = s.substring(s.indexOf(" type=\"") + 1);
						s = s.substring(s.indexOf('"')+1);
						s = s.substring(0, s.indexOf('"'));
						System.out.println(s);
					
					
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
    } 
}
