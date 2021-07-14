import com.sun.xml.internal.ws.resources.UtilMessages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class FirstCode {
    public static void main(String[] args) {
        /*linklist 和arraylist 之间的差别*/
//1、link基于链表实现，arraylist基于数组实现；
//2、使用场景也不同，arraylist适合随机查找，linklist更适合删除，添加；
//3、二者都实现了list接口，但是linklist还实现了Deque,因此可以当作队列来使用；
        ArrayList arrayList = new ArrayList();
        arrayList.add(1);
//        默认添加的位置为数组的最后一个；扩容会导致速度下降；
        arrayList.add(0, 1);
        arrayList.get(0);

        LinkedList linkList = new LinkedList();
        linkList.add(0);
        linkList.add(1, 2);
//      下标越大，迭代次数越多；
//      可以的当作双端队列 首位查询很快
        linkList.get(0);
        linkList.getFirst();
        linkList.getLast();

//        hashmap的PUT方法：可以理解为1.7和1.8之间的差距；
//        1、根据key通过哈希算法与运算得出数组的下标；
//        2、如果数组下标元素为空，则key和value封装为1.7为entry,1.8为node对象；放入数组位置；
//        3、如果为非空：（1）、  1、7先判断是否扩容，是则先扩容；否则生成entry,并且使用头插法放进当当前位置的链表；
//        （2）、1.8，先判断当前位置上node的类型，红黑树node或者链表node；
//        红黑树node:将key和value封装成一个红黑树节点，并添加到红黑树中去，在这个过程判断红黑树是否存在当前key，是则更新；
//        链表node:将key和value封装成一个链表node并通过尾插法插入到链表中去，需要遍历，存在则更新，否则遍历完链表，将新链表
//        node插入到原链表，如果链表节点数》=8则会将链表变成红黑树；
//        (3)、将key和value封装为node插入到链表或者红黑树中，再判断是否扩容，是则扩容，否则put方法；
//        红黑树和链表 entry和node
        HashMap hashMap = new HashMap();
        hashMap.put(1, 3);
        hashMap.put(2, "测试文件");

        /*简单说一下Threadlocal
         * 1、Threadlocal 是java中所提供的线程本地存储机制，可以用该机制将数据缓存在线程内部，该线程可以任意时间
         * 任意方法获取缓存数据；
         * 2、threadlocal底层是通过ThreadLocalMap来实现，每个thread都会存在一个threadLocalMap,map中的key为threadlocal，value
         * 为缓存的数据
         * 3、在线程池中会造成内存泄露,因为threadlocal对象不会主动回收线程，因此需要手动调动remove方法清除entry对象
         * 4、threadlocal中的经典应用场景就是连接管理（一个线程持有一个链接该链接对象可以在不同的方法之间进行传递，线程之间
         * 不共享一个连接）*/
//        测试数据有问题，与实际不符合；!!!注意自动生成的set方法和get方法的返回值；！！！
        Person person = new Person();
        new Thread(new Runnable() {
            @Override
            public void run() {
                person.setName("小伍");
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print("线程1=====" + person.getName());
                person.reMove();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                person.setName("小张");
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print("线程1=====" + person.getName());
                person.reMove();
            }
        }).start();

        /*JVM中的共享区哪些可以作为GCroot?
        * 1、堆区和方法区是所有线程共享的栈、本地方法栈、程序计数器是每个线程独有的；
        * 2、JVM在进行垃圾回收时，需要找到垃圾对象，也就是没有被引用的对象；（此处的方法为：找非垃圾对象，
        * root即根，引用的路径上的正常对象，特征为只会引用其他对象，而不会被其他引用；栈中的本地变量、
        * 方法区的静态变量、本地方法栈中的变量、正在运行的线程可以作为GC ROOT ）*/
//      垃圾回收机制 JVM！！！！


      /*项目中如何排查JVM问题？
        * 两个层面来解答：1、还在正常运行的系统；
        * （1）、使用JMAP查看各个区域的使用情况；（2）、通过JSTACK来查看线程的运行情况例如死锁和阻塞；
        * （3）、使用JSTAT命令来查看垃圾回收的情况，特别是fullgc,如果fullgc比较频繁，则需要调优；
        * （4）、通过命令结果或者jvisualvm等工具来进行分析；
        * （5）、初步猜测频繁发送fullgc的原因，频繁发送有没有出现内存，表示fullgc实际上已经回收了很多对象，所以这些对象
        * 最好能够在youunggc过程中就直接收掉，避免这些对象进入到老年代，对于这种情况，就要考虑这些存活时间不长的对象是不是
        * 比较大，导致年轻代放不下，直接进入到了老年代，尝试加大年轻代的大小，如果改完之后，fullgc减少，则证明效果有效
        * （6）、找到占用cpu最多的线程，定位到具体的方法对这个方法进行调优，节省内存；
        * 2、已经发生了OOM的系统；
        * （1）、一般都会设置系统发生OOM时会生成dump文件，（HeadDUMPPath=user/local/base）;
        * (2)、使用jvisualvm等工具来分析dump文件；
        * （3）、找到dump文件中异常的实例对象，和异常的线程（高cpu），定位到具体代码
        * （4）、详细分析和调试；
        * 整体思路结合到自己的项目中，进行分析、推理、实践、总结、在分析，最终定位；
         */


        /*如何查看线程死锁？
        * 1、通过jstack命令进行查看，该命令会显示发生了死锁的线程；
        * 2、两个线程去操作数据库时，数据库发生了死锁，这是可以查询数据库的思索情况；
        * show openTables where In_use>0;
        * show processlist;
        * select * from INFORMATION_SCHEMA.INNOB_LOCKS;(INFORMATION_SCHEMA.INNOB_LOCK_WAITS)
        * !!!!!!!!!数据库的死锁！！！！！！！！*/

        /*线程之间如何通讯的？
        * 1、通过共享内存或基于网络进行通信；
        * 2、如果是共享内存进行通信，考虑并发，什么时候并发，什么时候阻塞，什么时候唤醒；
        * 3、wait()、notify()就是阻塞和唤醒；
        * 4、通过网络，也要考虑并发，处理方式加锁等等方式；
        * ！！！！！！线程！！！！！！*/


        /*spring 和大致流程
        * 1、spring是一个快速的开发框架，用来帮助程序员管理对象；
        * 2、设计模式的应用、并发安全的实现、面向接口的设计是优点；
        * 3、spring容器的启动流程：a、首先进行扫描，扫描得到所有的BeanDefintion,并放入MAP中
        * b、筛选出非懒加载的单例BeanDefintion进行创建Bean，对于多例Bean不需要进行创建，会在获取Bean时利用
        * BeanDefition进行去创建
        * c、利用BeanDefition创建Bean，就是Bean的创建周期，这期间包括了合并BeanDefintion、推断构造方法
        * 、实例化、属性填充、初始化前、初始化、初始化后等步骤，其中AOP就是发生在初始化后这一步
        * 4、单例Bean创建完成后，spring会发布一个容器启动事件
        * 5、spring启动结束
        * 6、源码中更为复杂，源码中会提供一些模板方法，让子类来实现，源码中还会涉及BeanFactorypostprocessor和BeanpostPROCESSOR
        * 的注册spring的扫描就是通过BeanFactorypostProcessor来实现的，依赖注入就是通过Beanpostprocessor来实现的
        * 7、在spring启动过程中还回去处理@IMPORT等注解
        * ！！！！！！！！！spring全家桶!!!!*/


        /*spring的事务机制
        * 1、事务底层是基于数据库事务和AOP机制的
        * 2、对于使用了@Transactional注解的Bean,Spring会创建一个代理对象作为bean;
        * 3、当条用代理对象方法时，会先判断方法是否加上了@Transactional注解；
        * 4、已经添加，利用事务管理器创建一个数据库连接；
        * 5、修改数据库连接的autocommit属性为false，禁止此连接自动提交，这是实现事务的重要一步；
        * 6、然后执行当前方法，方法中会执行sql；
        * 7、无异常会直接提交事务；
        * 8、异常回滚或者继续提交，取决于异常是否回滚；
        * 9、spring事务隔离级别对应的就是数据库的隔离级别
        * 10、spring事务的传播机制是spring事务自己实现的，也是spring中最复杂的；
        * 11、spring事务的传播机制是基于数据库连接来做的一个数据库连接事务，如果新开一个事务，实际上
        * 可以理解为先建立一个数据连接，在此数据库上执行sql;
        * ！！！！！拓展数据库的知识点！！！！*/



        /*什么是@Transcational失效？
        * 1、本质上是基于代理实现的，所以只有是被代理对象调用时注解才会生效；不是被代理对象则不会生效；
        * 2、如果方法是private的，注解也会失效，因为底层cglib是基于父子类来实现的，子类不能重载父类的private；
        * ！！！！回滚！！！*/
                System.out.print("Hello world");

//                git@github.com:AzJoker97/97RE.git
    }
}
