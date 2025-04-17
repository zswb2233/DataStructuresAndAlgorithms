public class PascalTriangle {
    /**
     * 递归的杨辉三角
     * @param i
     * @param j
     * @return
     */
    private static int element1(int i,int j){
        if(i==0 || i==j){
            return 1;
        }
        return element1(i-1,j)+element1(i-1,j-1);
    }

    /**
     * 记忆法的杨辉三角
     * @param triangle
     * @param i
     * @param j
     * @return
     */
    private static int element2(int[][] triangle,int i,int j){
        if(triangle[i][j]!=0){ return triangle[i][j]; }
        if(i==0 || i==j){
            triangle[i][j]=1;
            return 1;
        }
        triangle[i][j]=element2(triangle,i-1,j-1)+element2(triangle,i-1,j);
        return triangle[i][j];
    }
    private static void createRow(int[] row,int i){
        if(i==0){
            row[0]=1;
            return;
        }
        for(int j=i;j>0;j--){
            row[j]=row[j-1]+row[j];
        }
    }
}
