import java.io.*;
import java.util.*;

/*
 * Write a description of class Baby Births here.
 * 
 * @author (Tanmay Sharma)
 * 
 */

class CSVRecords {
    private List<String[]> records;
    private String[] record;
    private int numberOfGirls;
    private int numberOfBoys;
    private int numberOfBoysBirths;
    private int numberOfGirlsBirths;
    private List<String[]> recordOfBoys;
    private List<String[]> recordOfGirls;
    private int year;

    public CSVRecords(int year) {
        records = new ArrayList<String[]>();
        recordOfBoys = new ArrayList<String[]>();
        recordOfGirls = new ArrayList<String[]>();
        numberOfBoys = 0;
        numberOfGirls = 0;
        this.year = year;

        String fname = "Resources\\us_babynames\\us_babynames_by_year\\yob" + year + ".csv";
        String delimeter = ",";
        try {
            String line;
            BufferedReader br = new BufferedReader(new FileReader(fname));
            while ((line = br.readLine()) != null) {
                String[] record = line.split(delimeter);
                if (record[1].equals("M")) {
                    numberOfBoys++;
                    recordOfBoys.add(record);
                    numberOfBoysBirths += Integer.parseInt(record[2]);
                } else {
                    numberOfGirls++;
                    recordOfGirls.add(record);
                    numberOfGirlsBirths += Integer.parseInt(record[2]);
                }
                records.add(record);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public int getNumberOfBirths(String name, String gender) {
        int result = 0;
        for (String[] r : records) {
            if (name.equals(r[0]) && gender.equals(r[1])) {
                result = Integer.parseInt(r[2]);
            }
        }
        return result;
    }

    public void totalBirths() {
        System.out.println("Number of girl names: " + numberOfGirls);
        System.out.println("Number of boy names: " + numberOfBoys);
        System.out.println("Total births in the year " + year + " are: " + (numberOfGirlsBirths + numberOfBoysBirths));
    }

    public List<String[]> getRecordOfBoys() {
        return recordOfBoys;
    }

    public List<String[]> getRecordOfGirls() {
        return recordOfGirls;
    }

    public List<String[]> getRecords() {
        return records;
    }

    public void printRecords() {
        for (String[] record : records) {
            System.out.println(record[0] + " " + record[1] + " " + record[2]);
        }
    }

}

public class BabyBirths {

    public static int getRank(int year, String name, String gender) {
        CSVRecords newRecords = new CSVRecords(year);
        int rank = 1;
        for (String[] record : newRecords.getRecords()) {
            if (record[0].equals(name) && record[1].equals(gender)) {
                break;
            } else if (!record[1].equals(gender)) {
                continue;
            } else {
                rank++;
            }
        }
        if ((rank > newRecords.getRecordOfBoys().size()) || (rank > newRecords.getRecordOfGirls().size())) {
            return -1;
        }
        return rank;
    }

    public static String getName(int year, int rank, String gender) {
        CSVRecords newRecords = new CSVRecords(year);
        String name = "";
        if (gender.equals("M")) {
            if (rank > newRecords.getRecordOfBoys().size()) {
                name += "NO NAME";
            } else {
                name += ((newRecords.getRecordOfBoys()).get(rank - 1))[0];
            }
        } else {
            if (rank > newRecords.getRecordOfGirls().size()) {
                name += "NO NAME";
            } else {
                name += ((newRecords.getRecordOfGirls()).get(rank - 1))[0];
            }
        }
        return name;
    }

    public static void whatIsNameInYear(String name, int year, int newYear, String gender) {
        CSVRecords newRecords = new CSVRecords(year);

        CSVRecords newBirthRecords = new CSVRecords(newYear);

        int oldBirths = newRecords.getNumberOfBirths(name, gender);

        String newName = "";

        if (gender.equals("M")) {
            for (String[] record : newBirthRecords.getRecords()) {
                if (Integer.parseInt(record[2]) > oldBirths) {
                    continue;
                } else {
                    newName += record[0];
                    break;
                }
            }
        } else {
            for (String[] record : newBirthRecords.getRecords()) {
                if (Integer.parseInt(record[2]) > oldBirths) {
                    continue;
                } else {
                    newName += record[0];
                    break;
                }
            }
        }

        System.out.println(name + " born in " + year + " would be " + newName + " if she was born in " + newYear + ".");

    }

    public static int yearOfHighestRank(String name, String gender) {
        List<Integer> ranks = new ArrayList<Integer>();

        for (int i = 1880; i <= 2014; i++) {
            int rank = getRank(i, name, gender);
            ranks.add(rank);
        }

        int index = 0;
        int rank = 10;

        for (int i = 0; i < ranks.size(); i++) {
            if (ranks.get(i) != -1) {
                if (rank > ranks.get(i)) {
                    rank = ranks.get(i);
                    index = i;
                }
            }
        }

        int year = 1880 + index;

        return year;
    }

    public static double getAverageRank(String name, String gender) {
        List<Integer> ranks = new ArrayList<Integer>();

        for (int i = 1880; i <= 2014; i++) {
            int rank = getRank(i, name, gender);
            ranks.add(rank);
        }

        double sum = 0;
        double numberOfRanks = 0;

        for(int i = 0; i < ranks.size(); i++) {
            if(ranks.get(i) != -1) {
                sum += ranks.get(i);
                numberOfRanks++;
            }
        }

        double result = sum / numberOfRanks;
        return result;
    }

    public static int getTotalBirthsRankedHigher(int year, String name, String gender) {
        CSVRecords newRecords = new CSVRecords(year);
        int totalBirthsRankedHigher = 0;
        if(gender.equals("M")) {
            for(String[] record : newRecords.getRecordOfBoys()) {
                if(record[0].equals(name) && record[1].equals(gender)) {
                    break;
                }
                totalBirthsRankedHigher = Integer.parseInt(record[2]);
            }
        }
        else {
            for(String[] record : newRecords.getRecordOfGirls()) {
                if(record[0].equals(name) && record[1].equals(gender)) {
                    break;
                }
                totalBirthsRankedHigher = Integer.parseInt(record[2]);
            }
        }
        return totalBirthsRankedHigher;
    }

    public void printRecords(int year) {
        CSVRecords newRecords = new CSVRecords(year);
        newRecords.printRecords();
    }

    public static void main(String[] args) {

        System.out.println(getTotalBirthsRankedHigher(2012, "Mason", "M"));
    }

}