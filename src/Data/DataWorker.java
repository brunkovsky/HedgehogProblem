package Data;

import IO.FileWorker;

public class DataWorker {

    public HedgehogField readInputField() {
        String rawDataString = FileWorker.convertFileToString("/Volumes/Users/Stas/Desktop/input.txt");
        int[][] fieldArray = getDataFromRawDataString(rawDataString);
        return new HedgehogField(fieldArray);
    }

    public void writeOutputData(int value) {
        FileWorker.writeToFile(value, "/Volumes/Users/Stas/Desktop/output.txt");
    }

    private int[][] getDataFromRawDataString(String rawDataString) {
        String rawDataStringWithSize = getStringWithSizeOnly(rawDataString);
        String rawDataStringWithoutSize = getStringWithoutSize(rawDataString);
        int rows = getNumberOfRows(rawDataStringWithSize);
        int columns = getNumberOfColumns(rawDataStringWithSize);
        return fillHedgehogFieldWithApples(rawDataStringWithoutSize, rows, columns);
    }

    private String getStringWithoutSize(String rawData) {
        int i = rawData.indexOf("\n");
        return rawData.substring(i + 1, rawData.length());
    }

    private int[][] fillHedgehogFieldWithApples(String rawData, int rows, int columns) {
        int[][] result = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            result[i] = getDataFromRow(rawData, i, columns);
        }
        return result;
    }

    private int[] getDataFromRow(String rawData, int row, int columns) {
        String rowString = getRowFromRawData(rawData, row);
        String[] split = rowString.split(" ");
        int[] result = new int[columns];
        for (int i = 0; i < columns; i++) {
            result[i] = Integer.parseInt(split[i]);
        }
        return result;
    }

    private String getRowFromRawData(String rawData, int row) {
        String[] result = rawData.split("\n");
        return result[row].replaceAll("[\\s]{2,}", " ").trim();     // regExp and trim() remove unnecessary spaces
    }

    private String getStringWithSizeOnly(String rawDataString) {
        int indexOfReturn = rawDataString.indexOf("\n");
        return rawDataString.substring(0, indexOfReturn).trim();
    }

    private int getNumberOfRows(String rawDataString) {
        int indexOfSpace = rawDataString.indexOf(' ');
        return Integer.parseInt(rawDataString.substring(0, indexOfSpace));
    }

    private int getNumberOfColumns(String rawDataString) {
        int indexOfLastSpace = rawDataString.lastIndexOf(' ');
        return Integer.parseInt(rawDataString.substring(indexOfLastSpace + 1, rawDataString.length()));
    }
}
