package horzsolt.javaplayground;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PascalTriangle {

    //https://algorithms.tutorialhorizon.com/pascal-triangle/
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> all_rows = new ArrayList<List<Integer>>(); //result
        ArrayList<Integer> row = new ArrayList<Integer>();
        for(int i=0;i<numRows;i++) {
            row.add(0, 1); //add element 1 at index 0.

            //now iterate the row, keep adding elements in pair, starting from j=1,
            // and set the sum at index j
            for(int j=1;j<row.size()-1;j++)
                row.set(j, row.get(j)+row.get(j+1));

            //create a copy of row and store it in all_rows
            all_rows.add(new ArrayList<Integer>(row));
        }
        return all_rows;
    }

    public static void main(String[] args) {
        List<List<Integer>> result = generate(5);
        for (int i = 0; i <result.size() ; i++) {
            List<Integer> current = result.get(i);
            System.out.println(Arrays.toString(current.toArray()));
        }
    }
}
