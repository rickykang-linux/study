package io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.mysql.cj.core.io.WriterWatcher;

public class Demo {

	public static void main(String[] args) {
		
	}
	
	public static void Writer() throws IOException{
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File("")));
		writer.close();
		
		BufferedReader reader = new BufferedReader(new FileReader(new File("")));
		
	}
}
