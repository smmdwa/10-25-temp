package com.duan.wechat_ordering.temp;

import org.omg.PortableServer.THREAD_POLICY_ID;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.JFrame;

public class threadpool implements Runnable{
    @Override
    public void run() {
        uesr u=context.holeder.get();
        u.age=2;
    }

    public static void main(String[] args) throws InterruptedException {
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                uesr u=new uesr();
                context.holeder.set(u);
                service();
            }
            public void service(){
                uesr u=context.holeder.get();
                System.out.println(u.age);
                AtomicInteger a=new AtomicInteger();
                a.set(1);
                a.compareAndSet(1,2);
            }
        };

        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                uesr u=context.holeder.get();
                //System.out.println(u.age);
            }
        };
        new Thread(r1).start();
        Thread.sleep(1000);
        new Thread(r2).start();

    }
}
class uesr{
    public int age;
    public void setAge(int age) {this.age=age;}
    uesr(){
        age=1;
    }
}
class context{
    public static ThreadLocal<uesr> holeder =new ThreadLocal<uesr>();
}
