package com.jdh.utils;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.*;
import java.util.UUID;

public class FileUtil {

    //写文件
	public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
		File targetFile = new File(filePath);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		FileOutputStream out = new FileOutputStream(filePath + fileName);
        BufferedOutputStream bout=new BufferedOutputStream(out);
        bout.write(file);
        bout.flush();
        bout.close();
	}

	public static boolean deleteFile(String fileName) {
	    fileName=fileName.replace("/",File.separator);
		File file = new File(fileName);
		// 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
		if (file.exists() && file.isFile()) {
            boolean b = file.delete();//删除文件
            if(b){
                fileName=fileName.substring(0,fileName.lastIndexOf(File.separator));
                File file2 = new File(fileName);
                // File[] listFiles = file.listFiles();
                if (file2.isDirectory()&&file2.listFiles().length<=0){
                    boolean b1 = file2.delete();
                    if (b1){
                        fileName=fileName.substring(0,fileName.lastIndexOf(File.separator));
                        file2 = new File(fileName);
                        if (file2.isDirectory()&&file2.listFiles().length<=0)
                            file2.delete();
                    }
                }
            }

            return b;
        } else {
			return false;
		}
	}

	public static String renameToUUID(String fileName) {
		String filename= UUID.randomUUID() + "." + fileName.substring(fileName.lastIndexOf(".") + 1);
        filename=filename.replace("-","");
		return filename;
	}

    /**
     * 获取文件目录
     * 目录分离算法
     * @param name 文件名称
     * @return 目录
     */
    public static String getDir(String name) {
        int i = name.hashCode();
        String hex = Integer.toHexString(i);
        int j = hex.length();
        for (int k = 0; k < 8 - j; k++) {
            hex = "0" + hex;
        }
        return File.separator + hex.charAt(0) + File.separator + hex.charAt(1)+File.separator;
    }

    /**
     * 获取文件MD5
     * @param file
     * @return md5
     */
    public static String getFileMd5(byte[] file) throws IOException {
        return DigestUtils.md5Hex(file);
    }


}