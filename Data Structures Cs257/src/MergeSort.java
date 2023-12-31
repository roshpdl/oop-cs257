// Routines to sort arrays of integers.
// (c) 1997, 2001 duane a. bailey
// modified by spc

import java.util.Scanner;

public class MergeSort
{
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter size of array to sort: ");
        int n = sc.nextInt();
        int[] data = new int[n];
        int i;

        for (i = 0; i < n; i++)
        {
            data[i] = (int) (Math.random() * 100);
        }

        long sec = System.currentTimeMillis();
        mergeSort(data,n);
        sec = System.currentTimeMillis() - sec;

        System.out.println("Want to print the entire array?");
        String yes = sc.next();
        if (yes.equals("no")){
            for (i = 0; i < 100; i++)	{
                System.out.print(data[i]+" ");
                if (((i+1) % 20) == 0) System.out.println();
            }
        }else {
            for (i = 0; i < n; i++)	{
                System.out.print(data[i]+" ");
                if (((i+1) % 20) == 0) System.out.println();
            }
        }

        System.out.println();
        System.out.println("Time to Sort: " + sec / 1000.0);
    }

    public static void mergeSort(int data[], int n)
    // pre: 0 <= n <= data.length
    // post: values in data[0..n-1] are in ascending order
    {
        mergeSortRecursive(data,new int[n],0,n-1);
    }

    private static void mergeSortRecursive(int data[],
                                           int temp[],
                                           int low, int high)
    // pre: 0 <= low <= high < data.length
    // post: values in data[low..high] are in ascending order
    {
        int n = high-low+1;
        int middle = low + n/2;
        int i;

        if (n < 2) return;
        // move lower half of data into temporary storage
        for (i = low; i < middle; i++)
        {
            temp[i] = data[i];
        }
        // sort lower half of array
        mergeSortRecursive(temp,data,low,middle-1);
        // sort upper half of array
        mergeSortRecursive(data,temp,middle,high);
        // merge halves together
        merge(data,temp,low,middle,high);
    }

    public static void merge(int data[], int temp[],
                             int low, int middle, int high)
    // pre: data[middle..high] are ascending
    //      temp[low..middle-1] are ascending
    // post: data[low..high] contains all values in ascending order
    {
        int ri = low; // result index
        int ti = low; // temp index
        int di = middle; // destination index
        // while two lists are not empty merge smaller value
        while (ti < middle && di <= high)
        {
            if (data[di] < temp[ti]) {
                data[ri++] = data[di++]; // smaller is in high data
            } else {
                data[ri++] = temp[ti++]; // smaller is in temp
            }
        }
        // possibly some values left in temp array
        while (ti < middle)
        {
            data[ri++] = temp[ti++];
        }
        // ...or some values left (in correct place) in data array
    }
    public static void swap(int data[], int i, int j)
    // pre: 0 <= i,j < data.length
    // post: data[i] and data[j] are exchanged
    {
        int temp;
        temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }


}
/*
100
73 92 40 38 51 17 8 31 56 84 21 34 29 16 61 31 7 63 70 32
69 38 98 93 31 84 45 74 8 33 30 32 76 69 21 27 85 81 9 92
16 82 26 45 97 38 75 3 53 97 86 21 34 35 65 64 71 9 89 30
42 70 13 69 90 33 47 75 13 56 18 29 89 95 25 85 33 99 39 37
95 76 9 29 62 74 44 32 82 85 61 75 54 73 96 44 58 42 70 70
3 7 8 8 9 9 9 13 13 16 16 17 18 21 21
21 25 26 27 29 29 29 30 30 31 31 31 32 32 32
33 33 33 34 34 35 37 38 38 38 39 40 42 42 44
44 45 45 47 51 53 54 56 56 58 61 61 62 63 64
65 69 69 69 70 70 70 70 71 73 73 74 74 75 75
75 76 76 81 82 82 84 84 85 85 85 86 89 89 90
92 92 93 95 95 96 97 97 98 99
*/
