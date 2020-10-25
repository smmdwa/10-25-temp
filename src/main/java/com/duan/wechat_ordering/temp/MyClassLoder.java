package com.duan.wechat_ordering.temp;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class MyClassLoder extends ClassLoader {
    private String myName="";
    public MyClassLoder(String myName){
        this.myName=myName;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
       byte[]data=this.loadClassData(name);
        return this.defineClass(name,data,0,data.length);
    }
    private byte []loadClassData(String clsName){
        byte []data=null;
        InputStream in=null;
        ByteArrayOutputStream out=new ByteArrayOutputStream();
        clsName=clsName.replaceAll(".","/");
        try{
            in=new FileInputStream(new File("classes/"+clsName+".class"));
            int a=0;
            while ((a=in.read())!=-1){
                out.write(a);
            }
            data=out.toByteArray();

        }catch (Exception e){
            e.printStackTrace();
        }
        return data;
    }
}
