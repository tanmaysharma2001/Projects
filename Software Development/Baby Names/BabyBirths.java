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
    private List<Integer> listOfBirthsBoys = new ArrayList<>();
    private List<Integer> listOfBirthsGirls = new ArrayList<>();

    public CSVRecords() {
        records = new ArrayList<String[]>();
        numberOfBoys = 0;
        numberOfGirls = 0;
    }

    public void addRecord(String[] record) {
        this.record = record;
        if (record[1].equals("M")) {
            numberOfBoys++;
            listOfBirthsBoys.add(Integer.parseInt(record[2]));
            numberOfBoysBirths += Integer.parseInt(record[2]);
        }
        else {
            numberOfGirls++;
            listOfBirthsGirls.add(Integer.parseInt(record[2]));
            numberOfGirlsBirths += Integer.parseInt(record[2]);
        }
        records.add(record);
    }

    public int getNumberOfBirths(String name, String gender) {
        int result = 0;
        for(String[] r : records) {
            if(name.equals(r[0]) && gender.equals(r[1])) {
                result = Integer.parseInt(r[2]);
            }
        }
        return result;
    }

    public int totalBirths() {
        return numberOfGirlsBirths + numberOfBoysBirths;
    }

    public List<String[]> getRecords() {
        return records;
    }

    public List<Integer> getNumberOfBirthsBoys() {
        return listOfBirthsBoys;
    }

    public List<Integer> getNumberOfBirthsGirls() {
        return listOfBirthsGirls;
    }

    public void printRecords() {
        for (String[] record : records) {
            System.out.println(record[0] + " " + record[1] + " " + record[2]);
        }
    }

}

public class BabyBirths {

    private CSVRecords newRecords = new CSVRecords();
    private List<Integer> ranksOfBirthsBoys;
    private List<Integer> ranksOfBirthsGirls;

    public BabyBirths(int year) {
        newRecords = new CSVRecords();
        String fname = "yob" + year + ".csv";
        String delimeter = ",";
        try {
            String line = "";
            BufferedReader br = new BufferedReader(new FileReader(fname));
            while ((line = br.readLine()) != null) {
                newRecords.addRecord(line.split(delimeter));
            }

            ranksOfBirthsBoys = newRecords.getNumberOfBirthsBoys();
            Collections.sort(ranksOfBirthsBoys);
            ranksOfBirthsGirls = newRecords.getNumberOfBirthsGirls();
            Collections.sort(ranksOfBirthsGirls);

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getRank(String name, String gender) {
        int result = -1;
        if(gender.equals("M")) {
            int numberOfBirths = newRecords.getNumberOfBirths(name, gender);
            for(int i = 0; i < ranksOfBirthsBoys.size(); i++) {
                if(ranksOfBirthsBoys.get(i) == numberOfBirths) {
                    result = (i+1);
                    break;
                }
            }
        }
        else {
            int numberOfBirths = newRecords.getNumberOfBirths(name, gender);
            for(int i = 0; i < ranksOfBirthsGirls.size(); i++) {
                if(ranksOfBirthsGirls.get(i) == numberOfBirths) {
                    result = (i+1);
                    break;
                }
            }
        }
        return result;
    }

    public String getName(int rank, String gender) {
        String name = "";
        if(gender.equals("M")) {
            int index = -1;
            for(int i = 0; i < ranksOfBirthsBoys.size(); i++) {
                if(rank == ranksOfBirthsBoys.get(i)) {
                    index = i;
                    break;
                }
            }

            for(int i = 0; i < ranksOfBirthsBoys.size();)

            List<String[]> recorded = newRecords.getRecords();
            String[] rec2 = recorded.get(index);

            name = rec2[0];
        }
        else {
            int index = -1;
            for(int i = 0; i < ranksOfBirthsGirls.size(); i++) {
                if(rank == ranksOfBirthsGirls.get(i)) {
                    index = i;
                    break;
                }
            }

            List<String[]> recorded = newRecords.getRecords();
            String[] rec2 = recorded.get(index);

            name = rec2[0];
        }

        return name;
    }

    public static void main(String[] args) {
        BabyBirths bb = new BabyBirths(2012);

        String name = bb.getName(2, "M");
        System.out.println(name);
        
    }

}