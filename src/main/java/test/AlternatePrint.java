package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * TODO
 *  交替打印字母和数字
 * @author lyl
 * @version 1.0
 * @date 2021/4/1 9:32
 */
public class AlternatePrint {
    static List<String> charList = Arrays.asList("a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z".split(","));
    static List<String> numList = Arrays.asList("1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6".split(","));
    ;
    static AtomicInteger integer = new AtomicInteger(0);
    static AtomicInteger integer1 = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (String item : charList) {
//                    synchronized (this){
                    while (true) {
                        if (integer.get() % 2 == 0) {
                            System.out.print(item);
                            integer.incrementAndGet();
                            break;
                        }
                    }

//                    }


                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {

                for (String item : numList) {
//                    synchronized (this){
                    while (true) {
                        if (integer.get() % 2 == 1) {
                            System.out.print(item);
                            integer.incrementAndGet();
                            break;
                        }
                    }
//                    }
                }

            }
        });
//        thread1.start();
//        thread2.start();
//        thread1.join();
//        thread2.join();
        System.out.println();
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                if (integer1.get() % 2 == 0) {
                    synchronized (AlternatePrint.class) {
                        while (i < charList.size()) {
                            System.out.print(charList.get(i++));
                            integer1.incrementAndGet();
                            AlternatePrint.class.notify();
                            try {
                                AlternatePrint.class.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }


            }
        });
        Thread thread4 = new Thread(new Runnable() {
            @Override
            public void run() {


                int i = 0;
                if (integer1.get() % 2 == 1) {
                    synchronized (AlternatePrint.class) {
                        while (i < numList.size()) {
                            System.out.print(numList.get(i++));
                            integer1.incrementAndGet();
                            AlternatePrint.class.notify();
                            try {
                                AlternatePrint.class.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });

        thread3.start();
        thread4.start();
        thread3.join();
        thread4.join();
    }

}
