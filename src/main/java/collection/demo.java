package collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.TreeSet;

public class demo<E> {
	static int count = 8000000;
	
	abstract class Person{
		
	}
	class Student extends Person{
		
	}
	
	public static void main(String[] args) {
		CollectionTool();
//		linkList();
//		arrayList();
//		
//		demo<String> aaDemo = new demo<String>();
//		aaDemo.fanxing(new ArrayList<String>());
	}
	
	public void system() {
		System.out.print(false);
		Properties properties = System.getProperties();
		
//		Runtime.getRuntime().
	}
	
	public static void CollectionTool(){
		Collections.synchronizedList(new ArrayList<String>());
		
		String[] array = new String[]{"44","11"};
		
		List<String> asList = Arrays.asList(array);
		String string = Arrays.toString(array);
		
		Object[] array2 = asList.toArray();
		Arrays.sort(array);
		System.out.println(asList);
		System.out.println(string);
	}
	
	private <T> void fanxing(T t){
		 
	}
	private <T> void fanxing(ArrayList<?> t,ArrayList<T> t2){
		 
	}
	private <T> void fanxing2(ArrayList<? extends Person> t){//可放Object的子类
		 
	}
	private <T> void fanxing3(ArrayList<? super Student> t){//可放Object的父类
		 
	}
	
	private static void treeSet(){
		TreeSet<String> set = new TreeSet<String>();
		set.add("");
	}
	
	private static void HashSetTest(){
		HashSet set = new HashSet();
			
	}
	
	private static void StringBuffer(){
		StringBuffer buffer = new StringBuffer();
		buffer.append(false);
		StringBuilder builder = new StringBuilder();
		builder.append(false);
	}
	
	private static void linkList() {
		
		List<String> linklist = new LinkedList<String>();
		linklist.toArray();
		long start = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			linklist.add("");
		}
		long end = System.currentTimeMillis();
		System.out.println("linklist:"+(end-start));
		
		System.out.println("over");
		long start2 = System.currentTimeMillis();
		
		String string = linklist.get(count-1);
		
		long end2 = System.currentTimeMillis();
		System.out.println("linklist get:"+(end2-start2));
		
	}
	
	private static void arrayList() {
		HashMap<String, String> aHashMap;
		List<String> arrayList = new ArrayList<String>();
		
		long start = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			arrayList.add("");
		}
		long end = System.currentTimeMillis();
		System.out.println("arrayList:"+(end-start));
		
		long start2 = System.currentTimeMillis();
		String string = arrayList.get(count-1);
		
		long end2 = System.currentTimeMillis();
		System.out.println("arrayList get:"+(end2-start2));
		
	}

}
