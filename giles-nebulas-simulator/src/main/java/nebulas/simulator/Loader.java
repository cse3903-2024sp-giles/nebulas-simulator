package nebulas.simulator;

import java.nio.file.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
/**
 *Loader - The Loader contains one public method that will Load and setup a machine based on a file path
 *
 *<p>
 *Loads the machine object and sets up for simulation
 *<p>
 **/
public interface Loader{
    //This should not be implemented in a seperate class object. The implementation should be here in the interface

    /**
     * Loads the machine based on the filepath provided
     * @param machine The machine object to be loaded
     * @param path The file path of the program to be loaded
     */
      public class GlobalVariables {
        public static String SegmentLength = "";
        public static String LoadAddress = "";
        public static String InfoAddress = "";
        public static String InitialCon = "";
        public static String ExeAdd = "";
        public static String SegmentName = "";
        public static Set<String> HEX_CODE = new HashSet<String>();
    }
    

    public static void hexSet(Set<String> set) {
        for (int i = 0; i < 10; i++) {
            set.add(Integer.toString(i));

        }
        set.add("A");
        set.add("B");
        set.add("C");
        set.add("D");
        set.add("E");
        set.add("F");
    }

    public static boolean headerProcessor(String text) {
        boolean format = true;
        String segmentName = "";
        String strLoadAddress = "";
        String strSegmentLength = "";
        if (text.length() != 16) {
            format = false;
        } else {
            segmentName = text.substring(1, 7).trim();
            strLoadAddress = text.substring(7, 11);
            strSegmentLength = text.substring(11, 15);
            for (int i = 0; i < strLoadAddress.length(); i++) {
                if (!GlobalVariables.HEX_CODE.contains(String.valueOf(strLoadAddress.charAt(i)))) {
                    format = false; // Found a character not in the set
                }
            }
            for (int i = 0; i < strSegmentLength.length(); i++) {
                if (!GlobalVariables.HEX_CODE.contains(String.valueOf(strSegmentLength.charAt(i)))) {
                    format = false; // Found a character not in the set
                }
            }
        }
        GlobalVariables.SegmentName = segmentName;
        if (format) {
            GlobalVariables.LoadAddress = hexToBinary(strLoadAddress);
            GlobalVariables.SegmentLength = hexToBinary(strSegmentLength);
        }
        return format;
    }

    public static boolean textRecordProcessor(String text) {
        boolean format = true;
        String info = "";
        String content = "";
        if (text.length() != 10) {
            format = false;
        } else {
            info = text.substring(1, 4);
            content = text.substring(5, 8);
            for (int i = 0; i < info.length(); i++) {
                if (!GlobalVariables.HEX_CODE.contains(String.valueOf(info.charAt(i)))) {
                    format = false; // Found a character not in the set
                }
            }
            for (int i = 0; i < content.length(); i++) {
                if (!GlobalVariables.HEX_CODE.contains(String.valueOf(content.charAt(i)))) {
                    format = false; // Found a character not in the set
                }
            }
        }
        if (format) {
            GlobalVariables.InfoAddress = hexToBinary(info);
            GlobalVariables.InitialCon = hexToBinary(content);
        }
        return format;
    }

    public static boolean endProcessor(String text) {
        boolean format = true;
        if (text.length() != 5) {
            format = false;
        } else {
            String executionAdd = text.substring(1, 4);

            for (int i = 0; i < executionAdd.length(); i++) {
                if (!GlobalVariables.HEX_CODE.contains(String.valueOf(executionAdd.charAt(i)))) {
                    format = false; // Found a character not in the set
                }

            }
            if (format) {
                GlobalVariables.ExeAdd = hexToBinary(executionAdd);

            }
        }
        return format;

    }

    public static String hexToBinary(String hexStr) {

        // Convert hexadecimal string to integer
        int decimalValue = Integer.parseInt(hexStr, 16);
        // Convert integer to binary string
        String binaryStr = Integer.toBinaryString(decimalValue);
        return binaryStr;
    }

    /**
     * Loads the machine based on the filepath provided
     * @param machine The machine object to be loaded
     * @param path The file path of the program to be loaded
     */
     static void loadMachine(Machine machine, Path path){

        Scanner scanner = new Scanner(System.in);
        boolean format = true;
        hexSet(GlobalVariables.HEX_CODE);
        System.out.println("Please enter the file u want to use: ");
        String fileName = scanner.nextLine(); // Replace with the path to your file
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String text;
            boolean headerProcessed = false;
            
            int textNum = 0;
            outerLoop: while ((text = br.readLine()) != null) {
                char recordType = text.charAt(0);

                switch (recordType) {
                    case 'H':
                        if (headerProcessed) {
                            System.err.println(
                                    "ERROR: There are multiple Header Text Records!");
                            format = false;
                            break outerLoop;
                        }
                        headerProcessor(text);
                        if (!format) {
                            System.err.println(
                                    "ERROR: Invalid header record format. ");
                            break outerLoop;
                        }
                        headerProcessed = true;

                    case 'T':
                        if (!headerProcessed) {
                            System.err.println(
                                    "ERROR: text record found before header record.");
                            break outerLoop;

                        }
                        format = textRecordProcessor(text);
                        textNum++;
                        if (!format) {
                            System.err.println(
                                    "ERROR: Invalid text record format. ");
                            break outerLoop;
                        } else if (textNum > Integer.parseInt(GlobalVariables.SegmentLength,
                                2)) {
                            System.err.println(
                                    "ERROR: Invalid text record number. ");
                            break outerLoop;
                        }
                    case 'E':
                        if (!headerProcessed) {
                            System.err.println(
                                    "ERROR: text record found before header record.");
                            break outerLoop;
                        }
                        format = endProcessor(text);
                        if (!format) {
                            System.err.println(
                                    "ERROR: Invalid end record format. ");
                            break outerLoop;
                        } else {
                            break outerLoop;
                        }
                    default:
                        System.err.println(
                                "ERROR: Invalid input record format. ");
                        break outerLoop;

                }
            }

        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: "
                    + e.getMessage());
        } //end
     }
}
