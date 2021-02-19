package test1;

//二维数组转稀疏数组
/*当一个二维数组中存在大量的0，或者数组中所有的值相同时，使用稀疏数组保存
* 稀疏数组的形式是多行3列，第0列表示元素所在的行，第1列表示元素所在的列，第2列表示元素的值
*稀疏数组的第0行用来保存二维数组的详细信息，[0][0]记录二维数组的行数，[0][1]记录列数，[0][2]记录非0元素的个数
* 稀疏数组的其余行列代表对应二维数组上的行列位置和值
* */


import java.util.Arrays;

public class SparseArray {
    public static void main(String[] args) {

        //创建一个原始的二维数组
        //0:表示没有棋子，1：表示黑子，2：表示白子
        int chessArr1[][] = new int[11][10];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;

        //输出原始的二维数组
        System.out.println("输出原始的二维数组");
        for (int[] row : chessArr1){
            for (int data : row){
                System.out.print(data + "\t");
            }
            System.out.println();
        }

        System.out.println("########################## " );
        //将二维数组转为稀疏数组
        //1. 先遍历二维数组 得到非0元素的个数
        int sum = 0;
        for (int i = 0; i < 11 ; i++){
            for (int j = 0; j < 10 ; j++){
                if (chessArr1[i][j] != 0){
                    sum++;
                }
            }
        }

        //2.创建对应的稀疏数组
        int sparseArr[][] = new int[sum + 1][3];
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;

        //遍历二维数组，将非0值存放到稀疏数组中
        int count = 0;                      //用于记录是第几个非0数据
        for (int i = 0; i < 11; i++){
            for (int j = 0; j < 10; j++){
                if (chessArr1[i][j] != 0){
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];

                }
            }
        }

        //输出稀疏数组
        System.out.println("####################");
        System.out.println("稀疏数组为");
        for (int i = 0; i < sparseArr.length; i++){
            System.out.printf("%d\t%d\t%d\t\n", sparseArr[i][0],sparseArr[i][1],sparseArr[i][2]);

        }
        System.out.println();

        //稀疏数组转为二维数组
        //1.先读取稀疏数组第一行，根据第一行的数据，创建二维数组
        int chessArr2[][] = new int[sparseArr[0][0]][sparseArr[0][1]];

        //2.再读取稀疏数组后几行的数据（从第二行开始），并赋值给二维数组
        for (int i = 1 ; i < sparseArr.length; i++){
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];

        }

        //输出二维数组
        System.out.println("稀疏数组转为二维数组");
        for (int[] row : chessArr2){
            for (int data : row){
                System.out.print(data + "\t");
            }
            System.out.println();
        }

    }
}
