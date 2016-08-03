package com.bio;

import java.util.Scanner;

public class Test {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Test test = new Test();
        int[] arr = { 5, 4, 6, 2, 8, 7, 3 };
        int length = arr.length;
        for (int i = 0; i < length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        test.sortS(arr, 0, length - 1);

    }

    /**
     * 利用快速排序法获得给定一维数组的第K大的数
     * 
     * @param a 数组
     * @param n 数组长度
     * @param k 第K大
     * @return
     */
    public int findKN(int a[], int n, int k) {
        int kMax = 0;// 记录当前第几大
        int[] temp = new int[n];
        for (int i = 0; i < n; i++) {
            int one = a[i];
            if (i > 0) {// 左侧有需要比较的
                for (int j = 0; j < i; j++) {
                    //
                }
            } else {// i=0 只有右侧有
                for (int j = 1; j < n; j++) {
                    if (a[j] < one) {
                        temp[j - 1] = a[j];
                    }
                }
            }
        }
        return 0;
    }

    public void sort(int arr[], int low, int high) {
        int l = low;
        int h = high;
        int povit = arr[low];

        while (l < h) {
            while (l < h && arr[h] >= povit){
                h--;
            }
            if (l < h) {
                int temp = arr[h];
                arr[h] = arr[l];
                arr[l] = temp;
                l++;
            }
            while (l < h && arr[l] <= povit){
                l++;
            }
            if (l < h) {
                int temp = arr[h];
                arr[h] = arr[l];
                arr[l] = temp;
                h--;
            }
        }
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
        System.out.println();
//        System.out.print("l=" + (l + 1) + " h=" + (h + 1) + " povit=" + povit + "\n");
        if (l > low){
            sort(arr, low, l - 1);
        }
        if (h < high){
            sort(arr, l + 1, high);
        }
    }

    /**
     * 快速排序法
     * 
     * @param arr 需要排序的数组
     * @param i 对比元素的初始下标
     * @param j 需要对比元素的最大下标
     * @return
     */
    public int[] sortOne(int[] arr, int i, int j) {
        int temp = arr[i];// 初始的比较值
        int length = arr.length;
        if (length == 1) {
            return arr;
        } else if (length == 2) {
            if (arr[0] < arr[1]) {
                return arr;
            } else {
                arr[0] = arr[1];
                arr[1] = temp;
                return arr;
            }
        }
        // 从后面比较 小的放前面
        for (; j > i; j--) {
            if (temp > arr[j]) {
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                if (i == j) {
                    // 一组判断完毕
                    int[] preArr = new int[i];
                    int[] behArr = new int[length - i];
                    for (int num = 0; num < length; num++) {
                        if (num < i) {
                            preArr[num] = arr[num];
                        } else {
                            behArr[num - i] = arr[num];
                        }
                    }
                    int[] preSortArr = sortOne(preArr, 0, i - 1);
                    int[] behSortArr = sortOne(behArr, 0, length - i - 1);
                    int[] sortArr = new int[length];
                    for (int num = 0; num < length; num++) {
                        if (num < i) {
                            sortArr[num] = preSortArr[num];
                        } else {
                            sortArr[num] = behSortArr[num - i];
                        }
                    }
                    return sortArr;
                }
                break;
            }
        }
        // 从前面开始比较 大的 放后面
        for (; i < j; i++) {
            if (temp < arr[i]) {
                arr[j] = arr[i];
                arr[i] = temp;
                j--;
                if (i == j) {
                    // 一组判断完毕
                    int[] preArr = new int[i];
                    int[] behArr = new int[length - i];
                    for (int num = 0; num < length; num++) {
                        if (num < i) {
                            preArr[num] = arr[num];
                        } else {
                            behArr[num - i] = arr[num];
                        }
                    }
                    int[] preSortArr = sortOne(preArr, 0, i - 1);
                    int[] behSortArr = sortOne(behArr, 0, length - i - 1);
                    int[] sortArr = new int[length];
                    for (int num = 0; num < length; num++) {
                        if (num < i) {
                            sortArr[num] = preSortArr[num];
                        } else {
                            sortArr[num] = behSortArr[num - i];
                        }
                    }
                    return sortArr;
                }
                break;
            }
        }
        if (i != j) {
            arr = sortOne(arr, i, j);
        }

        return arr;
    }

    /**
     * 按照快速排序法排序
     * 
     * @param arr
     * @return
     */
   public void sortS(int[] arr,int low,int high){
       int l = low;
       int h = high;
       int fValue = arr[l];
       while(l<h){
           //如果后面的比前面的大 则递减继续判断
           while(l<h && fValue < arr[h]){
               h--;
           }
           //遇到后面比较小的 则交换
           if(l<h){
               int temp = arr[l];
               arr[l] = arr[h];
               arr[h] = temp;
               l++;
           }
           //如果前面的小于选中的值  则递增判断
           while(l<h && fValue > arr[l]){
               l++;
           }
           //如果遇到前面的大于后面的 则交换顺序
           while(l<h){
               int temp = arr[l];
               arr[l] = arr[h];
               arr[h] = temp;
               h--;
           }
       }
       for(int i=0;i<arr.length;i++){
           System.out.print(arr[i]+" ");
       }
       System.out.println();
       
       if(l>low){
           sortS(arr,low,l-1);
       }
       if(h<high){
           sortS(arr,l+1,high);
       }
       
   }

}
