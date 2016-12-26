package com.tsty.io.file.update;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

/**
 * 主方法
 * Created by William on 2015/7/10.
 */
public class Main {

    private static String oldPath = "";  // 旧路径
    private static String newPath = "";  // 新路径
    private static String outPath = "";  // 输出路径

    static {
        Properties prop = new Properties();
//        InputStream in = Object.class.getResourceAsStream("./config/config.properties");
//        InputStream in = Object.class.getResourceAsStream("/Users/ningguangyuan/Desktop/1/config.properties");
//        InputStream in = Object.class.getResourceAsStream("config.properties");
        System.err.println(Object.class.getClassLoader());
        System.err.println(Main.class.getClassLoader());
        InputStream in = Main.class.getResourceAsStream("config.properties");
        
        try {
            prop.load(in);
            oldPath = prop.getProperty("oldPath").trim();
            newPath = prop.getProperty("newPath").trim();
            outPath = prop.getProperty("outPath").trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void checkFile() {

    }

    public static void main(String[] args) {

        System.out.println("---------------------------------------");
        System.out.println("===> 程序已启动");
        System.out.println("===> 正在遍历文件夹 " + newPath);
        // 遍历新文件目录
        Iterator iterator = FileUtils.iterateFiles(new File(newPath), null, true);
        // 遍历旧文件目录
        Iterator iterator1 = FileUtils.iterateFiles(new File(oldPath), null, true);
        // 用于接收被删除的目录
        List<String> deleteFiles = new ArrayList<String>();
        System.out.println("===> 遍历完成，开始执行分析");
        long startTime = System.currentTimeMillis();  //获取开始时间
        try {

            // 遍历比较新旧目录
            // 1. 如果文件不存在，则说明是新增的文件，复制该文件到输出路径
            // 2. 如果MD5值不一样，则文件被修改，复制该文件到输出路径
            while (iterator.hasNext()) {
                // 新文件路径
                String nPathStr = iterator.next().toString();
                File newFile = new File(nPathStr);
                // 旧文件路径
                File oldFile = new File(nPathStr.replace(newPath, oldPath));
                //System.out.println("===> 正在分析 " + nPathStr);

                // 判断文件是否存在
                if (!oldFile.exists()) {
                    File outFile = new File(nPathStr.replace(newPath, outPath));
                    FileUtils.copyFile(newFile, outFile);
                    System.out.println("===> 已找到新增文件 ");
                    System.out.println("======> 复制文件 " + outFile.toString());
                    System.out.println("Done.");
                    System.out.println();
                } else {
                    // MD5校验
                    // 如果不相同，则copy到输出路径
                    String newMD5 = CheckMD5.getMD5(newFile);
                    String oldMD5 = CheckMD5.getMD5(oldFile);
                    if (!StringUtils.equals( newMD5, oldMD5 )) {
                        File outFile = new File(nPathStr.replace(newPath, outPath));
                        FileUtils.copyFile(newFile, outFile);
                        System.out.println("===> 已找到修改文件 ");
                        System.out.println("======> 新文件 " + newFile + " (MD5: " + newMD5 + " )");
                        System.out.println("======> 旧文件 " + oldFile + " (MD5: " + oldMD5 + " )");
                        System.out.println("======> 复制文件 " + outFile.toString());
                        System.out.println("Done.");
                        System.out.println();
                    }
                }
            }

            // 遍历旧的文件目录
            // 主要是用于查找被删除的文件
            System.out.println("===> 已找到删除文件 ");
            while (iterator1.hasNext()) {
                // 旧文件路径
                String oPathStr = iterator1.next().toString();
                // 新文件路径
                File newFile = new File(oPathStr.replace(oldPath, newPath));
                if (!newFile.exists()) {
                    deleteFiles.add(oPathStr);
                    System.out.println("======> 文件路径 " + oPathStr);
                }
            }


        } catch (Exception e) {
            System.err.println("发生异常!");
        }
        long endTime=System.currentTimeMillis(); //获取结束时间
        System.out.println();
        System.out.println("分析完成 耗时：" + ((endTime-startTime) / 1000) + "s");
        System.out.println("输出路径：" + outPath);
        System.out.println("---------------------------------------");

    }


}