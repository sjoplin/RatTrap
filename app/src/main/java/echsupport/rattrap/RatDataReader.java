package echsupport.rattrap;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

public class RatDataReader {
   public static void main(String args[]) {
      String ratData = "Rat_Sightings.csv";
      System.out.println(ratData);
      String line = "";
      String[][] data = new String[100637][];
      int i = 0;
      try {
         BufferedReader br = new BufferedReader(new FileReader(ratData));
         while (i < 100637) {
            line = br.readLine();
            data[i] = line.split(",");
            i++;
         }
         String[][] temp = new String [100636][9];
         for (int j = 0; j < 100636; j++) {
            temp[j][0] = data[j + 1][0]; //unique Key

            temp[j][1] = data[j + 1][1]; //Created Date
            temp[j][2] = data[j + 1][7]; //location type
            temp[j][3] = data[j + 1][8]; //incident zip
            temp[j][4] = data[j + 1][9]; //incident address
            temp[j][5] = data[j + 1][16]; //city
            temp[j][6] = data[j + 1][23]; //Borough
            temp[j][7] = data[j + 1][25]; //latitude
            temp[j][8] = data[j + 1][24]; //longitude
         }
         data = temp;

         for(int j = 0; j < 10; j++) {
            for (int k = 0; k < 9; k++) {
               System.out.print(data[j][k]);
            }
            System.out.println();
         }
      } catch (FileNotFoundException e) {
         System.out.println("File not found");
      } catch (IOException e) {
         System.out.println("data stream error");
      } catch (Exception e) {
         System.out.println("something else went wrong");
      }
   }




}
