import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class ForthCode {
    public static void main(String[] args) {
        /*简单的说去当前时间*/
        Date date = new Date();
        String dateformt = "yyyy-MM-dd HH:mm:ss";//具体的格式
        String dft = "yyyy 年 MM 月 dd 日 HH 时 mm 分 ss 秒";//具体的格式
        SimpleDateFormat smf = new SimpleDateFormat(dateformt);//格式化
        SimpleDateFormat smf1 = new SimpleDateFormat(dft);//格式化
        //System.out.print(smf.format(date));

        /*时间的工具，可以求出具体的年月日，以及当前日期所在的年，月，周中的第几天*/
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int dat = calendar.get(Calendar.DATE);
        int yod = calendar.get(Calendar.DAY_OF_YEAR);
        int mod = calendar.get(Calendar.DAY_OF_MONTH);
        int wod = calendar.get(Calendar.DAY_OF_WEEK);
        //System.out.print("当前的年份："+year+"\n"+"当前的月份:"+month+"\n"+"当前的日期:"+dat+"\n");
        //System.out.print("一年的第几天:"+yod+"\n"+"一个月的第几天"+mod+"\n"+"一周的第几天:"+wod+"\n");

        long dateStamp = System.currentTimeMillis();//系统时间
        /*三种转化格式的方式都可使用*/
        String str1 = smf1.format(Long.parseLong(String.valueOf(dateStamp)));
        String str = smf.format(new Date(Long.parseLong(String.valueOf(dateStamp))));
        String st = smf.format(dateStamp);

        //System.out.print(st+"\n"+str+"\n"+str1);
        /*同一个类之间的重载，父子之间的重写*/
        //ForthCode fc = new ForthCode();
        //fc.info();
        //fc.info(str);

        //int []a = {-1,-2,3,7,10,30,-50};
        //int res = maxSubArray(a);
        //System.out.print("本次的数据结果为："+res+"\n");
        //printStar(4);
        //printChengfa();
        //printSanjiaoxing(6);
        //printRandom(6);
        //printForth(6);
        printJuxing(5,4);
    }

    ForthCode Class() {
        System.out.print("无参数构造");
        return null;
    }

    ForthCode Clas(int i) {
        System.out.print("有参数构造");
        return null;
    }

    void info() {
        System.out.print("重写打印的方法");
    }

    void info(String str) {
        String s = "this is test file,because java is the best language"+"\n";
        System.out.print(s);
    }
    /*动态规划*/
    public static int maxSubArray(int[] nums) {
        int res = nums[0];
        for(int i = 1; i < nums.length; i++) {
            nums[i] += Math.max(nums[i - 1], 0);
            res = Math.max(res, nums[i]);
        }
        return res;
    }
/*注意后i表示行，j就表示列，每行*后面的都是空格没有进行输出*/
    public static void printStar(int size){
        if(size%2 == 0){
            size++;
        }
        for (int i = 0; i < size / 2 + 1; i++) {
            for (int j = size / 2 + 1; j > i + 1; j--) {
                System.out.print(" "); // 输出左上角位置的空白
            }
            for (int j = 0; j < 2 * i + 1; j++) {
                System.out.print("*"); // 输出菱形上半部边缘
            }
            System.out.println(); // 换行
        }
        for (int i = size / 2 + 1; i < size; i++) {
            for (int j = 0; j < i - size / 2; j++) {
                System.out.print(" "); // 输出菱形左下角空白
            }
            for (int j = 0; j < 2 * size - 1 - 2 * i; j++) {
                System.out.print("*"); // 输出菱形下半部边缘
            }
            System.out.println(); // 换行
        }
    }
    /*九九乘法表*/
    public static void printChengfa(){
        for (int i = 1; i<10;i++){
            for (int j= 1;j<=i;j++){
                System.out.print(i+"*"+j+"="+(i*j)+"\t");
            }
            System.out.print("\n");
        }
    }
/*三角形打印，主要是将三角形，分成多个模块进行处理*/
    public static void printSanjiaoxing(int size){
        for(int i=1;i<=size;i++){
            for(int j=size; i<=j; j--)
                System.out.print(" ");
            for(int j=1; j<=i; j++)
                System.out.print("*");
            for(int j=1; j<i; j++)
                System.out.print("*");
            System.out.println();
        }
    }
/*打印倒立的指定三角形*/
    public static void printRandom(int size){
        for (int i = size - 1; i >= 0; i--) {
            for (int j = 0; j < (size - 1) - i; j++) {
                System.out.print(" ");//打印空白
            }
            for (int j = 0; j < 2 * i + 1; j++) {
                System.out.print("*");//打印*
            }
            System.out.println();
        }
    }

    public static void printForth(int size){
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < (size - 1) - i; j++) {
                System.out.print(" "); // 打印每一行的空白
            }
            for (int j = 0; j < size; j++) {
                System.out.print("*"); // 打印每一行的*
            }
            System.out.println();
        }
    }
    /*打印指定的矩形*/
    public static void printJuxing(int lon,int weigth){
        for (int i = 0; i<weigth;i++){
            for(int j = 0;j<lon;j++){
                System.out.print("*");//内循环表示长度
            }
            System.out.print("\n");//外循环表示宽度
        }
    }
}