package com.bio;

/**
 * 测试在一个有序的二维数字数组中判断是否包含数字n的算法
 * 要求程序用时小于1秒钟
 * @author 浮生若梦
 * @date 2016-8-3 上午2:09:56
 */
public class TestDemionArray {

	public static void main(String[] args) {
		long StartTime = System.currentTimeMillis();
		TestDemionArray test = new TestDemionArray();
		int arr[][]  = {{1,2,3},
						{4,5,6},
						{10,25,49},
						{51,61,67},
						{78,130,159}};
		
		int row = arr.length;
		int col = arr[0].length;
		boolean flag = true;
		for(int i=0;i<row;i++){
			for(int j=0;j<col;j++){
				int temp = arr[i][j];
				if(!test.findN(arr, temp)){
					flag = false;
					System.out.println("不存在的元素："+temp);
				}
			}
		}
		if(flag){
			System.out.println("数组的元素全都在");
		}
		
		int n = 13;
		boolean has = test.findN(arr, n);
		System.out.println("Array has element "+n+"? \n"+has);
		long sysTime = System.currentTimeMillis() - StartTime;
		System.out.println("程序共耗时："+sysTime+"毫秒");
	}
	
	/**
	 * 在一个二维数字数组arr中判断是否包含数字n
	 * 该数组数值为从左到右依次增大 且 从上到下依次增大
	 * @param arr
	 * @param n
	 * @return
	 */
	public boolean findN(int[][] arr,int n){
		int row = arr.length;
		int col = arr[0].length;
		if(arr[0][0] > n || arr[row-1][col-1] < n){
			//不存在
			return false;
		}
		int sum = row * col;
		if(sum % 2 == 0){
			//总共偶数个
			int index = sum/2 - 1; //中间数顺位下标   5
			//计算伪中间数的下标  如果不能正中间 则向前取一位
			int rowIndex = index / col;//伪中间数取行下标  
			int colIndex = index % col;//伪中间数取列下标
			//伪中间数取arr[rowIndex][colIndex]
			if(arr[rowIndex][colIndex] == n){
				return true;
			}else if(arr[rowIndex][colIndex] > n){
				//在前半部分
				int nowRow = 0;//分割后的行数
				if(row % 2 == 0){
					//行是偶数
					nowRow = row / 2;
				}else{
					//行是奇数
					nowRow = row / 2 + 1;
				}
				int[][] nowArr = new int[nowRow][col];
				for(int i=0;i<nowRow;i++){
					for(int j=0;j<col;j++){
						nowArr[i][j] = arr[i][j];
					}
				}
				return findN(nowArr,n);
			}else{
				//在后半部分
				int nowRow = 0;//分割后的行数
				int startRow = 0;//分割后的起始行下标
				if(row % 2 == 0){
					//行是偶数
					nowRow = row / 2;
					startRow = nowRow;
				}else{
					//行是奇数
					nowRow = row / 2 + 1;
					startRow = nowRow - 1;
				}
				int[][] nowArr = new int[nowRow][col];
				for(int i=0;i<nowRow;i++){
					for(int j=0;j<col;j++){
						nowArr[i][j] = arr[startRow+i][j];
					}
				}
				return findN(nowArr,n);
			}
		}else{
			//总共奇数个  即为正方形二维数组 或 单行数组
			//正方形
			if(row > 1){
				int[][] nowArr = new int[row/2+1][col];
				if(arr[row/2][col/2] == n){
					return true;
				}else if(arr[row/2][col/2] > n){
					//在前半部判断
					for(int i=0;i<row/2+1;i++){
						for(int j=0;j<col;j++){
							nowArr[i][j] = arr[i][j];
						}
					}
					return findN(nowArr, n);
				}else if(arr[row/2][col/2] < n){
					//在后半部判断
					for(int i=0;i<row/2+1;i++){
						for(int j=0;j<col;j++){
							nowArr[i][j] = arr[row/2+i][j];
						}
					}
					return findN(nowArr, n);
				}
			}else{
				//单行
				for(int i=0;i<col;i++){
					if(arr[0][i] == n){
						return true;
					}
				}
				return false;
			}
		}
		return false;
	}
}
