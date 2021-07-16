import java.util.Arrays;

public class ThirdCode {
    public static void main(String[] args) {
        /*此处为填充数组*/
        double array[] = new double[3];
        double x = Math.random();//随机数
        Arrays.fill(array, 0, 1, 33);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + "\n");
        }
        /*数组扩容 参数对应：原数组，原数组起始位置，目标数组，目标启始位置，复制的长度*/
        String a[] = {"a","b","c"};
        String b[] = new String[5];
        b[3] = "d";
        b[4] = "e";
        System.arraycopy(a,0,b,0,a.length);
        for (String str: b) {
            System.out.println(str);
        }
//方法的调用
        int c[] = {1,2,3,4,5,6,7,2,3,4};
        findArray(c);
        Array(c);
    }

    /*找出重复的元素*/
    public static void findArray(int[] a){
        int count =0;
        for(int j = 0;j<a.length;j++){
            for(int k = j+1;k<a.length;k++){
                if (a[j]==a[k]){
                    count++;
                }
            }
            if (count==1){
                System.out.print("重复的元素："+a[j]+","+"重复的位置是："+(j+1)+"\n");
                count = 0;
            }
        }
    }
    /*找出重复的元素*/
    public static void Array(int[] a){
        int count =0;
        for(int j = 0;j<a.length;j++){
            for(int k = 0;k<a.length;k++){
                if (a[j]==a[k]){
                    count++;
                }
            }
            if (count==1){
                System.out.print("不重复的元素："+a[j]+","+"不重复的位置是："+(j+1)+"\n");
                count = 0;
            }
        }
    }
}
