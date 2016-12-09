package edu.prog3;

import edu.prog3.Model.AllocationRequest;
import edu.prog3.Model.DeallocationRequest;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Pratik Gayee pgayee
 * Jonathan Hale jonathanhale
 * Stephanie Akapkoun sakpakoun
 */
public class Prog3 {

    private static BuddySystemMemory memory;

    public static void main(String[] args) {

        File inputFile = new File(args[0]);
        int msize;
        int asize;

        try {
            Scanner sc = new Scanner(inputFile);

            //grab the memory size and min block size
            String[] line = sc.nextLine().split(" ");
            msize = Integer.parseInt(line[0]);
            asize = Integer.parseInt(line[1]);

            memory = new BuddySystemMemory(asize, msize);

            while(sc.hasNextLine()) {
                line = sc.nextLine().split(" ");

                AllocationRequest aRequest;
                DeallocationRequest dRequest;

                if(line[1].equals("+")) {
                    aRequest = new AllocationRequest(Integer.parseInt(line[0]), Integer.parseInt(line[2]));
                    System.out.println("Request ID " + aRequest.getId() + " : allocate " + aRequest.getSize() + " bytes.");
                    memory.allocate(aRequest, false);
                } else if(line[1].equals("-")) {
                    dRequest = new DeallocationRequest(Integer.parseInt(line[0]));
                    System.out.println("Request ID " + dRequest.getId() + " : deallocate");
                    memory.deallocate(dRequest);
                }
            }

            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
