package com.example.pathfinderplayertool;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CharacterID {
	public ArrayList<Integer> allIDS;
	
	public static int generateID(File dir){
		int ID=0;
		int tempID=0;
		File tempIDFile = new File(dir, "/IDs.txt");
		
		try {
			tempIDFile.createNewFile();
			String name="";
			BufferedReader in = new BufferedReader(new FileReader(tempIDFile));
			while((name = in.readLine()) != null){
				tempID = Integer.parseInt(name);
				if (tempID > ID){
	    			ID = tempID;
	    		}
			}
			in.close();
		}catch(Exception e){e.printStackTrace();}
    		
    	ID+=1; //ID should be 1 greater than the largest existing ID
    	
    	try {
			BufferedWriter os = new BufferedWriter(new FileWriter(tempIDFile, true));
	    	os.append("" + ID);
	    	os.newLine();
	    	os.close();
		} catch (IOException e) {e.printStackTrace();}
		
		return ID;
	}
	
	public static boolean removeID(){
		//TODO
		return true;
	}
}
