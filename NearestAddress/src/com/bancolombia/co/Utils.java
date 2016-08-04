package com.bancolombia.co;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

import au.com.bytecode.opencsv.CSVReader;


public class Utils {
	private static AtomicInteger counter = new AtomicInteger();
	/**
	 * parserCoordinates - parser the csv file to List<String[]>
	 * @param ISCoor
	 * @return
	 * @throws IOException
	 */
	public static TreeMap<Integer,Location> parserCoordinates(InputStream ISCoor) throws IOException{
		TreeMap<Integer,Location> parserCoor = new TreeMap<Integer,Location>();
		CSVReader reader = null;
		String seperatedBy = "COMMA";
        try {
            if (seperatedBy.equals("TAB")) {
                reader = new CSVReader(new InputStreamReader(ISCoor, "UTF-8"), '\t');
            } else if (seperatedBy.equals("HASH")) {
                reader = new CSVReader(new InputStreamReader(ISCoor, "UTF-8"), '#');
            } else if (seperatedBy.equals("SLASH")) {
                reader = new CSVReader(new InputStreamReader(ISCoor, "UTF-8"), '/');
            } else if (seperatedBy.equals("BACK SLASH")) {
                reader = new CSVReader(new InputStreamReader(ISCoor, "UTF-8"), '\\');
            } else if (seperatedBy.equals("COMMA")) {
                reader = new CSVReader(new InputStreamReader(ISCoor, "UTF-8"), ',');
            } else if (seperatedBy.equals("ASTERISK")) {
                reader = new CSVReader(new InputStreamReader(ISCoor, "UTF-8"), '*');
            } else if (seperatedBy.equals("PIPE")) {
                reader = new CSVReader(new InputStreamReader(ISCoor, "UTF-8"), '|');
            } else {
                reader = new CSVReader(new InputStreamReader(ISCoor, "UTF-8"), ',');
            }
            
            String[] coorLine;
            while ((coorLine = reader.readNext()) != null) {
            	Location loc = new Location();
            	loc.setAddress(coorLine[0]);
            	loc.setLatitude(Double.parseDouble(coorLine[1]));
            	loc.setLongitude(Double.parseDouble(coorLine[2]));
            	parserCoor.put(counter.incrementAndGet(),loc);
            }
            
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(FindNearestAddress.class.getName()).log(Level.SEVERE, null, ex);
        }
        return parserCoor;
	}
}
