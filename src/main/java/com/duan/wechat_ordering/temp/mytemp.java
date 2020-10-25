package com.duan.wechat_ordering.temp;

import lombok.Data;
import lombok.Getter;
import org.springframework.data.relational.core.sql.In;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;
//class temp{
//    public static void main(String[] args) {
//        HashMap map=new HashMap();
//        HashMap result=new HashMap();
//        String a="abc123ab";
//        for (int i = 0; i < a.length(); i++) {//从前到后扫描
//            if(map.containsKey(a[i])){ //如果已经出现过的话，那就判断他们的距离
//                int distance=i-map[a[i]]; //计算新更新的距离 a-0   a-6   distance=6
//                result.put(a[i],distance);  //更新距离
//            }
//            else{
//                //第一次出现
//                map.put(a[i],i);//  记录下字符和他们的位置 如 a,0   b,1  c,2
//                result.put(a[i],i);
//            }
//        }
//        //最后挑选result中 距离最大的就行
//
//    }
//}
public class mytemp implements Runnable{
    static account account_a=new account(100);
    static account account_b=new account(100);

    int flag;
    @Override
    public void run() {
        if(flag==1){
            transfer(account_a,account_b,1);
        }
        else{
            transfer(account_b,account_a,2);
        }
    }

    public  void transfer(account a,account b,int money){
        synchronized (a){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (b){
                if(a.balance<money){
                    System.out.println("余额不足！");
                    return;
                }
                a.balance-=money;
                b.balance+=money;
                System.out.println("转账成功：  "+"a:"+a.balance+"  b:"+b.balance);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        mytemp d1=new mytemp();
        d1.flag=1;
        mytemp d2=new mytemp();
        d2.flag=2;
        Thread t1=new Thread(d1);
        Thread t2=new Thread(d2);
        t1.start();t2.start();
        t1.join();t2.join();

        System.out.println("a:"+mytemp.account_a.balance+"  b:"+mytemp.account_b.balance);
    }
}
 class account{
    int balance;
    public account(int balance){
        this.balance=balance;
    }
    public account(){}
}

class randomtransfer {
    public static final int people=1000;
    public static final int time=100000;
    public static final int account_money=1000;
    public static final int thread_num=20;
    public  static void transfer(account a,account b,int money){
        synchronized (a){
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            synchronized (b){
                if(a.balance<money){
                    System.out.println("余额不足！");
                    return;
                }
                a.balance-=money;
                b.balance+=money;
                System.out.println("转账成功：  "+"a:"+a.balance+"  b:"+b.balance);
            }
        }
    }

    public static void main(String[] args) {
        account []peo=new account[people];
        for (int i = 0; i < people; i++) {
            //peo[i].balance=1000;
            // System.out.println(1);
            peo[i]=new account(account_money);
        }
        Random random=new Random();
        class transferthread extends Thread{
            @Override
            public void run() {
                for (int i = 0; i < time; i++) {
                    int from=random.nextInt(people);
                    int to=random.nextInt(people);
                    int money=random.nextInt(account_money);
                    transfer(peo[from],peo[to],money);
                }
            }
        }
        for (int i = 0; i < thread_num; i++) {
            new transferthread().start();
        }
    }
}

class zhexuejia implements Runnable{
    static int num_zhexuejia=5;
     Object leftchopsticks;
     Object rightchopsticks;

    public static void main(String[] args) {
        zhexuejia [] phis=new zhexuejia[num_zhexuejia];
        Object[] chopstiks=new Object[num_zhexuejia];
        for (int i = 0; i < num_zhexuejia; i++) {
            chopstiks[i]=new Object();
        }
        for (int i = 0; i < num_zhexuejia; i++) {
            phis[i]=new zhexuejia();
        }
            for (int i = 0; i < num_zhexuejia; i++) {
                phis[i].leftchopsticks=chopstiks[i%5];
                phis[i].rightchopsticks=chopstiks[(i+1)%5];
                new Thread(phis[i],"哲学家"+i+"号").start();
            }
    }
    @Override
    public void run() {
        while (true){
            try{
                synchronized (leftchopsticks){
                    //Thread.sleep(100);
                    System.out.println(Thread.currentThread().getName()+"拿起左筷子");
                    synchronized (rightchopsticks){
                        Thread.sleep(100);
                        System.out.println(Thread.currentThread().getName()+"拿起右筷子");
                    }
                    System.out.println(Thread.currentThread().getName()+"放下右筷子");
                }
                System.out.println(Thread.currentThread().getName()+"放下左筷子");
            }catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}

class interrupt implements Runnable{
    @Override
    public void run() {
        while (true){
            try {
                System.out.println(1);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().isInterrupted());
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Thread t1=new Thread(new interrupt());
        t1.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.interrupt();
    }


}

class newway implements Runnable{
    @Override
    public void run() {
        while (true){
            try {
                doaction();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void doaction() throws InterruptedException {
            Thread.sleep(1000);
    }


}

class ehan{

    private static class newinstance{
        public static final ehan instance=new ehan();
    }
    public static ehan getinstance(){
        return newinstance.instance;
    }
}

class OuterClass {
    public static long OUTER_DATE = System.currentTimeMillis();
    public static int a = 1;
    static {
        System.out.println("外部类静态块加载时间：" + System.currentTimeMillis());
    }

    public OuterClass() {
        timeElapsed();
        System.out.println("外部类构造函数事件：" + System.currentTimeMillis());
    }

    static class InnerStaticClass {
        static {
            System.out.println("内部类静态块加载时间：" + System.currentTimeMillis());
        }
        public static long INNER_STATIC_DATE = System.currentTimeMillis();

    }

    class InnerClass {
        public long INNER_DATE = 0;
        public InnerClass() {
            timeElapsed();
            INNER_DATE = System.currentTimeMillis();
        }
    }

    public static void Hello(){System.out.println("Hello");}

    public static void main(String[] args) {
        System.out.println("内部类静态变量加载时间：" + InnerStaticClass.INNER_STATIC_DATE );
        System.out.println("外部类静态变量加载时间：" + OuterClass.OUTER_DATE );
        //System.out.println(new InnerClass().INNER_DATE);
    }

    //单纯的为了耗时而已
    private void timeElapsed() {
        for (int i = 0; i < 10000000; i++) {
            int a = new Random(100).nextInt(), b = new Random(100).nextInt();
            a = a + b;
        }
    }
}

///公平锁和非公平锁
class fair implements Runnable{
    printqueue queue;
    public fair(printqueue queue){
        this.queue=queue;
    }
    @Override
    public void run() {
        int a=1;
        System.out.println(Thread.currentThread().getName()+"开始打印");
        queue.print();
        System.out.println(Thread.currentThread().getName()+"打印结束");
    }

    public static void main(String[] args) {
        Thread []threads=new Thread[3];
        printqueue queue=new printqueue();
        for (int i = 0; i < 3; i++) {
            threads[i]=new Thread(new fair(queue),"第"+i+"号线程");
        }
        for (int i = 0; i < 3; i++) {
            threads[i].start();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

class printqueue{
    public Lock lock=new ReentrantLock(false);//true为公平，false为不公平
    public void print(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"正在打印a 还需要等待1秒");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"正在打印b 还需要等待1秒");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

//==============读写锁  共享排他锁
class readandwrite extends Thread{
    static ReentrantReadWriteLock readWriteLock=new ReentrantReadWriteLock();
    static ReentrantReadWriteLock.ReadLock readLock=readWriteLock.readLock();
    static ReentrantReadWriteLock.WriteLock writeLock=readWriteLock.writeLock();
    int flag;
    @Override
    public void run() {
        if(flag==1){
            read();
        }
        else{
            write();
        }
    }
    public void read(){
        readLock.lock();
        try{
            System.out.println(Thread.currentThread().getName()+"正在读");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }finally {
            System.out.println(Thread.currentThread().getName()+"读取完成");
            readLock.unlock();

        }

    }
    public void write(){
        writeLock.lock();
        try{
            System.out.println(Thread.currentThread().getName()+"正在写");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }finally {
            System.out.println(Thread.currentThread().getName()+"写完成");
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        readandwrite rw1 = new readandwrite();
        readandwrite rw2= new readandwrite();
        readandwrite rw3 = new readandwrite();
        readandwrite rw4 = new readandwrite();
        rw1.flag=1;
        rw2.flag=1;
        rw3.flag=2;
        rw4.flag=2;
        rw1.start();rw2.start();rw3.start();rw4.start();
    }
}


////////////////// 可重入锁
class reentrant extends Thread{
    ReentrantLock lock=new ReentrantLock();
    int flag;
    @Override
    public void run() {
        if(flag==1){
            try {
                lock.lockInterruptibly();
            } catch (InterruptedException e) {
                System.out.println("我被中断拉！");
            }
        }
        else{
            lock.lock();
            while (true){
            }
        }
    }
    public static void main(String[] args) {
        reentrant t1 = new reentrant();
        reentrant t2 = new reentrant();
        t1.flag=2;
        t2.flag=1;
        t1.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
        t2.interrupt();
    }
}

///写降级
class jiangji implements Runnable{
    ReentrantReadWriteLock readWriteLock=new ReentrantReadWriteLock();
    ReentrantReadWriteLock.ReadLock readLock=readWriteLock.readLock();
    ReentrantReadWriteLock.WriteLock writeLock=readWriteLock.writeLock();
    @Override
    public void run() {
        writeLock.lock();
        try{
            System.out.println("已经获得了写锁");
            readLock.lock();
            try{
                System.out.println("已经获得了读锁");
            }finally {
                readLock.unlock();
            }
        }finally {
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        new Thread(new jiangji()).start();

    }
}
class a_b_c{
    public static void main(String[] args) {
        ExecutorService service= Executors.newFixedThreadPool(3);
        CountDownLatch wait_a=new CountDownLatch(1);
        CountDownLatch wait_b=new CountDownLatch(1);
        Runnable a = new Runnable(){
            @Override
            public void run() {
                System.out.println("a线程运行结束");
                wait_a.countDown();
            }
        };
        Runnable b = new Runnable(){
            @Override
            public void run() {
                try {
                    wait_a.await();
                    System.out.println("b线程运行结束");
                    wait_b.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Runnable c = new Runnable(){
            @Override
            public void run() {
                try {
                    wait_b.await();
                    System.out.println("c线程运行结束");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        service.submit(a);service.submit(b);service.submit(c);


    }
}

class xinhaoliang{
    public static Semaphore a=new Semaphore(1,true);
    public static Semaphore b=new Semaphore(0,true);

    public static void main(String[] args) {
        ExecutorService service=Executors.newFixedThreadPool(10);
        Runnable r1 = new Runnable(){
            @Override
            public void run() {
                try {
                    a.acquire();
                    System.out.println("a");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    b.release();
                }

            }
        };
        Runnable r2 = new Runnable(){
            @Override
            public void run() {
                try {
                    b.acquire();
                    System.out.println("b");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    a.release();
                }

            }
        };
        for (int i = 0; i < 10; i++) {
            service.submit(r1);service.submit(r2);
        }
        service.shutdown();
        System.out.println("gg");
    }
}




class jiaoti{
    public static Semaphore a=new Semaphore(1,true);
    public static Semaphore b=new Semaphore(0,true);
    public static Semaphore c=new Semaphore(0,true);
    static volatile int x=0;
    static volatile boolean flag=false;

    public static void main(String[] args) {
        ExecutorService service=Executors.newFixedThreadPool(10);
        int n=5;

        Runnable r1 = new Runnable(){
            @Override
            public void run() {
                for(int i=0;i<n;i++){
                    try {
                        a.acquire();
                        System.out.println(0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        if(x%2==0){
                            b.release();
                        }else{
                            c.release();
                        }
                    }
                }

            }
        };
        Runnable r2 = new Runnable(){
            @Override
            public void run() {
                for(int i=0;i<n/2;i++){
                        try {
                            c.acquire();
                            x++;
                            System.out.println(x);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }finally {
                            if(x<n)
                                a.release();
                            else{
                                flag=true;
                            }
                        }
                }
            }
        };
        Runnable r3 = new Runnable(){
            @Override
            public void run() {
                int temp=n%2==1?n/2+1:n/2;
                System.out.println("temp"+temp);
                for(int i=0;i<temp;i++){
                        try {
                            b.acquire();
                            x++;
                            System.out.println(x);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }finally {
                            if(x<n)
                                a.release();
                            else {
                                flag=true;
                            }
                        }
                }
            }
        };
//        service.submit(r1);service.submit(r2);service.submit(r3);
//        service.shutdown();
        new Thread(r1).start();new Thread(r2).start();new Thread(r3).start();

    }
}


class xinhao{
    public static Semaphore h=new Semaphore(2,true);
    public static Semaphore o=new Semaphore(1,true);
    public static CyclicBarrier cyclicBarrier=new CyclicBarrier(3);
    public static void main(String[] args) {
        Runnable h = new Runnable(){
            @Override
            public void run() {
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        };
        Runnable o = new Runnable(){
            @Override
            public void run() {

            }
        };


    }
}