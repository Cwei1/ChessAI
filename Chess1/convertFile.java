import java.util.*;
import java.io.*;
public class convertFile{
    public static boolean isDigit(String s){
	try{
	    int i = Integer.parseInt(s);
	}
	catch(Exception e){
	    return false;
	}
	return true;
    }

    public static void main(String[] args){
	PrintWriter out = null;
	try {
	    Scanner sc = new Scanner (new File (args[0]));
	    String s = sc.nextLine();
	    ArrayList<String> moves = new ArrayList<String>();
	    String str = "";
	    for (int i = 0; i < s.length(); i++){
		if (!((s.substring(i, i+1)).equals(" ")))
		    str += s.substring(i, i+1);
		else{    
		    moves.add(str);
		    str = "";
		}
	    }
	    out = new PrintWriter(new BufferedWriter(new FileWriter("processed.txt")));
	    for (int i = 0; i < moves.size(); i++){
		String current = moves.get(i);
		for (int k = 0; k < current.length(); k++){
		    if (current.substring(k, k+1).equals(".")){
			moves.remove(i);
			i--;
			break;
		    }
		}
	    }
	    for (String g: moves)
		out.println(g);
	    out.close();
	}
	catch(Exception e){
	    e.printStackTrace();
	}
   }
}	


