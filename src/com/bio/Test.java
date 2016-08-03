package com.bio;

import java.util.Scanner;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
	
	}
	
	/**
	 * 利用快速排序法获得给定一维数组的第K大的数  
	 * @param a 数组
	 * @param n 数组长度
	 * @param k 第K大
	 * @return
	 */
	public int findKN(int a[],int n, int k){
		int kMax = 0;//记录当前第几大
		int[] temp = new int[n]; 
		for(int i=0;i<n;i++){
			int one = a[i];
			if(i > 0){//左侧有需要比较的
				for(int j=0;j<i;j++){
					//
				}
			}else{//i=0 只有右侧有
				for(int j=1;j<n;j++){
					if(a[j] < one){
						temp[j-1] = a[j];
					}
				}
			}
			
			
		}
		
		return 0;
	}
	
//	public 
	
}
