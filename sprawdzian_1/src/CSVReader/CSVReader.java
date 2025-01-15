package CSVReader;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CSVReader {
    BufferedReader reader;
    String delimiter;
    boolean hasHeader;

    String[] current;

    List<String> columnLabels = new ArrayList<>();
    Map<String,Integer> columnLabelsToInt = new HashMap<>();

    /**
     *
     * @param filename nazwa pliku
     * @param delimiter separator pól
     * @param hasHeader czy plik ma wiersz nagłówkowy
     */
    public CSVReader(String filename,String delimiter,boolean hasHeader) throws FileNotFoundException {
        this(new BufferedReader(new FileReader(filename)), delimiter, hasHeader);
    }
    public CSVReader(String filename,String delimiter) throws FileNotFoundException {
        this(filename, delimiter, true);
    }
    public CSVReader(String filename) throws FileNotFoundException {
        this(filename, ",");
    }
    public CSVReader(Reader reader, String delimiter, boolean hasHeader) {
        this.reader = new BufferedReader(reader);
        this.delimiter = delimiter;
        this.hasHeader = hasHeader;
        if(hasHeader) parseHeader();
    }

    public void parseHeader() {
        String line  = null;
        try {
            line = reader.readLine();
            if (line == null) return;
            String[] header = line.split(delimiter);
            for (int i=0;i<header.length;i++) {
                columnLabels.add(header[i]);
                columnLabelsToInt.put(header[i],i);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean next(){
        String line  = null;
        try {
            line = reader.readLine();
            if (line == null) return false;
            this.current = line.split(delimiter+"(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public List<String> getColumnLabels() {
        return columnLabels;
    }
    public int getRecordLength() {
        return current.length;
    }
    boolean isMissing(int columnIndex) {
        return columnIndex >= getRecordLength() || Objects.equals(current[columnIndex], "");
    }
    boolean isMissing(String columnLabel) {
        if (!columnLabels.contains(columnLabel)) return true;
        return isMissing(columnLabelsToInt.get(columnLabel));
    }
    public String get(int columnIndex) {
        if (isMissing(columnIndex)) return null;
        return current[columnIndex];
    }
    public String get(String columnLabel) {
        if (isMissing(columnLabel)) return null;
        return get(columnLabelsToInt.get(columnLabel));
    }
    public Integer getInt(int columnIndex) {
        if (isMissing(columnIndex)) return null;
        try {
            return Integer.parseInt(this.get(columnIndex));
        } catch (Exception e) {
            return null;
        }
    }
    public Integer getInt (String columnLabel) {
        if (isMissing(columnLabel)) return null;
        return getInt(columnLabelsToInt.get(columnLabel));
    }
    public Long getLong(int columnIndex) {
        if (isMissing(columnIndex)) return null;
        try {
            return Long.parseLong(this.get(columnIndex));
        } catch (Exception e) {
            return null;
        }
    }
    public Long getLong(String columnLabel) {
        if (isMissing(columnLabel)) return null;
        return getLong(columnLabelsToInt.get(columnLabel));
    }
    public Double getDouble(int columnIndex) {
        if (isMissing(columnIndex)) return null;
        try {
            return Double.parseDouble(this.get(columnIndex));
        } catch (Exception e) {
            return null;
        }
    }
    public Double getDouble(String columnLabel) {
        if (isMissing(columnLabel)) return null;
        return getDouble(columnLabelsToInt.get(columnLabel));
    }
    public LocalDate getDate(int columnIndex, String format) {
        if (isMissing(columnIndex)) return null;
        return LocalDate.parse(get(columnIndex), DateTimeFormatter.ofPattern(format));
    }
    public LocalDate getDate(String columnLabel, String format) {
        if (isMissing(columnLabel)) return null;
        return getDate(columnLabelsToInt.get(columnLabel), format);
    }
    public LocalTime getTime(int columnIndex, String format) {
        if (isMissing(columnIndex)) return null;
        return LocalTime.parse(get(columnIndex), DateTimeFormatter.ofPattern(format));
    }
    public LocalTime getTime(String columnLabel, String format) {
        if (isMissing(columnLabel)) return null;
        return getTime(columnLabelsToInt.get(columnLabel), format);
    }
}
