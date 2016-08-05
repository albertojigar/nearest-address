package com.bancolombia.co;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Pattern;

public class FindNearestAddress {

	private static String[] currentAddress;
	public static void main(String[] args) {
		Path path = Paths.get("./resources/medellin100k.csv");
		byte[] data;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Enter the Address: ");
			String s = br.readLine();
			if(s!=null && !s.isEmpty()){
				data = Files.readAllBytes(path);
				InputStream ISCoor = new ByteArrayInputStream(data);
				TreeMap<Integer,Location> parserCoordinates = Utils.parserCoordinates(ISCoor);
				//Collections.binarySearch(parserCoordinates, new Comparator());
				
				currentAddress = s.split(" ");

				SortedSet<Map.Entry<Integer,Location>> sortedset = new TreeSet<Map.Entry<Integer,Location>>(
						new Comparator<Map.Entry<Integer,Location>>() {
							@Override
							public int compare(Map.Entry<Integer,Location> e1,
									Map.Entry<Integer,Location> e2) {
								//Here I need to do the comparation
								Pattern patternCl = Pattern.compile( "(\\CL+)" );
								Pattern patternKr = Pattern.compile( "(\\KR+)" );
								Pattern patternNum = Pattern.compile( "[0-9]" );
								Pattern patternLet = Pattern.compile( "[a-zA-Z]" );
								
								Location loc1 = e1.getValue();
								Location loc2 = e1.getValue();
								String[] address1 = loc1.getAddress().split(" ");
								String[] address2 = loc2.getAddress().split(" ");
								
								if(currentAddress!=null && currentAddress.length>0 && address1!=null && address2!=null && address1.length>0 && address2.length>0){
									if(patternCl.matcher(currentAddress[0]).matches() && patternCl.matcher(address1[0]).matches()){
										if(currentAddress[1]!=null && address1[1]!=null){
											
										}
									}
								}
								return 0;
							}
						});

				sortedset.addAll(parserCoordinates.entrySet());

				//			Set set = parserCoordinates.entrySet();
				//			Iterator iterator = set.iterator();
				//			while(iterator.hasNext()) {
				//				Map.Entry mentry = (Map.Entry)iterator.next();
				//				System.out.print("key is: "+ mentry.getKey() + " & Value is: ");
				//				String[] arr = (String[])mentry.getValue();
				//				System.out.println(arr[0]);
				//			}

				System.out.println("NEAREST ADDRESS "+parserCoordinates.get(0));
				
			}
		}catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
