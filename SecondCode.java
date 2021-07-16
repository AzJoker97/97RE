import java.nio.CharBuffer;
import java.util.*;

public class SecondCode {
    public static void main(String[] args) {
        /*字符串中间对比*/
        String str1 = "That is just now";
        String str2 = "This is just now";
        boolean match1 = str1.regionMatches(true, 5, str2, 5, 7);
//        System.out.print(match1);
        /*测试运行的性能!通过时间对比;
         * 基础数据类型的创建速度明显快于引用的String*/
        long stratTime = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            String name = "小伍";
            String name2 = "小张";
        }
        long endTime = System.currentTimeMillis();
//        System.out.print("本次运行的时间为:"+(endTime-stratTime)+"毫秒");
        long startTime1 = System.currentTimeMillis();
        for (int j = 0; j < 10; j++) {
            int number = 20;
            float demo = 30f;
        }
        long endTime1 = System.currentTimeMillis();
//        System.out.print("本次的基础数据类型时间:"+(endTime1-startTime1)+"毫秒");
        /*此处需要理解为 堆栈*/
        String str3 = "abc";
        String str4 = "abc";
        String str5 = "abc";
//        System.out.print(str3==str4.intern());
//        System.out.print(str3==str5);
/* 格式化字符串
        double e =Math.E;
        System.out.print(e+"\n");
        System.out.format(Locale.CHINA,"%-10.4f%n%n",e);*/

        /*字符串小结*/
        StringBuffer sb1 = new StringBuffer();
        sb1 = sb1.append(str1);
        sb1 = sb1.append(str2);
        sb1 = sb1.append(str3);
        sb1 = sb1.append(str4);
//        System.out.print(sb1);


        /*字符反转!!反转的方法在StringBuffer中,而不是在String中*/
        String string = "Test";
        StringBuffer stringBuffer = new StringBuffer();
        String revser = stringBuffer.append(string).reverse().toString();
//        System.out.print(revser);

        /*数组的查找和排序*/
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        System.out.print(array + "\n");
        Arrays.sort(array);//升
        int index = Arrays.binarySearch(array, 9);
        System.out.print("元素0在第" + index + "个位置" + "\n");
        int nindex = index;
        array = insertElement(array, 9, nindex);
        printArray("插入的数据:", array);
        Isresver(array, array.length);
//        int min = (int) Collections.min(Arrays.asList());
//        int max = (int) Collections.max(Arrays.asList());
//        System.out.print(min + "\n" + max);
        /*数据添加，利用的是泛型的addall方法：需要注意该方法的参数类型*/
        String a[] = {"test", "is", "the", "side"};
        String b[] = {"测试", "就", "是", "路边"};
        List list = new ArrayList(Arrays.asList(a));
        list.addAll(Arrays.asList(b));
        Object[] c = list.toArray();
        System.out.print(Arrays.toString(c));
        /*更为好用的数组连接*/
        char f[] = {'1', '2', 'c', 'd', 'y'};
        char x[] = {'a', 'b', 'c', 'd', 'f'};
        CharBuffer charBuffer = CharBuffer.allocate(f.length + x.length);
        charBuffer.put(f);
        charBuffer.put(x);
//合并后的数组；
        char[] chars = charBuffer.array();
        for (int i = 0; i < chars.length; i++) {
            System.out.print(chars[i] + "\n");
        }
    }

    /*输出数组*/
    private static void printArray(String messgae, int array[]) {
        System.out.print(messgae + ":数组的长度为:" + array.length + "\n");
        for (int i = 0; i < array.length; i++) {
            if (array[i] != 0) {
                System.out.print(",");
            }
            System.out.print(array[i]);
        }
        System.out.print("\n" + "over");
    }

    /*添加一个元素*/
    private static int[] insertElement(int oraigin[], int element, int index) {
        int length = oraigin.length;
        int[] newarray = new int[length + 1];//x新建一个长度+1的数组
        System.arraycopy(oraigin, 0, newarray, 0, length);//复制数组
        newarray[length] = element;//指定位置 写入元素
        System.arraycopy(oraigin, index, newarray, index + 1, length - index);//指定复制
        return newarray;
    }

    /*数组反转 迭代器的使用*/
    private static void Isresver(int[] ary, int length) {
        int[] b = new int[length];
        int l = length;
        for (int n = 0; n < length; n++) {
            b[l - 1] = ary[n];
            l = l - 1;
        }
//        foe-each
        for (int obj : b
        ) {
            System.out.print(obj + ",");
        }
    }

}
