package com.ryan.commons.util.File;

import com.ryan.commons.util.io.CloserUtil;
import com.ryan.commons.util.security.Coder;
import com.google.common.hash.BloomFilter;
import org.apache.commons.lang3.StringUtils;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Enumeration;

/**
 * Created by ryan on 15/6/28.
 * 增加魔数判断文件类型
 */
public class FileUtil {

    public static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

    public static final int FileHeaderLen = 28;

    public static String getFileHeader(String filePath) throws IOException {
        if(StringUtils.isNotEmpty(filePath)){
            return getFileHeader(new File(filePath));
        }
        return null;
    }

    public static String getFileHeader(File file) throws IOException {
        String header = null;
        if(file.exists()){
            byte[] headerBuff = new byte[FileHeaderLen];
            InputStream is = null;
            try{
                is = new FileInputStream(file);
                is.read(headerBuff, 0, headerBuff.length);
//                return ByteUtils.toHexString(headerBuff);
                return Coder.bytes2hex(headerBuff);
            } catch (Exception e){

            } finally {
                CloserUtil.closeIO(is);
            }
        }
        return header;
    }

    public static FileTypeEnum getFileType(String filePath) throws IOException {
        if(StringUtils.isNotEmpty(filePath)){
            return getFileType(new File(filePath));
        }
        return null;
    }

    public static FileTypeEnum getFileType(File file) throws IOException {
        String fileHeader = getFileHeader(file);
        if(StringUtils.isNotEmpty(fileHeader)){
            fileHeader = fileHeader.toUpperCase();
            FileTypeEnum[] fileTypes = FileTypeEnum.values();
            for(FileTypeEnum type : fileTypes){
                if(fileHeader.startsWith(type.getMagicValue())){
                    return type;
                }
            }
        }
        return null;
    }

    public static String STR_END_CTRL = "\n";

    public static boolean readBigFileAddFilter(String file, BloomFilter<String> filter){
        return readBigFileAddFilter(new File(file), filter);
    }

    public static boolean readBigFileAddFilter(File file, BloomFilter<String> filter){
        FileChannel fc = null;
        try{
            fc = new RandomAccessFile(file, "r").getChannel();
            StringBuffer strBuf = new StringBuffer("");
            //开100k的buff
            int buffer = 1024 * 100;
            byte[] bytes = new byte[buffer];
            ByteBuffer byteBuffer = ByteBuffer.allocate(buffer);
            while(fc.read(byteBuffer) != -1){
                int size = byteBuffer.position();
                byteBuffer.rewind();
                byteBuffer.get(bytes);
                byteBuffer.clear();
                String tempString = new String(bytes, 0, size);
                int fromIndex = 0;
                int endIndex = 0;
                while((endIndex = tempString.indexOf(STR_END_CTRL, fromIndex)) != -1){
                    String line = tempString.substring(fromIndex, endIndex);
                    line = new String(strBuf.toString() + line).trim();
                    filter.put(line);
                    strBuf.delete(0, strBuf.length());
                    fromIndex = endIndex + 1;
                }
                if(size > tempString.length()){
                    strBuf.append(tempString.substring(fromIndex, tempString.length()));
                }else{
                    strBuf.append(tempString.substring(fromIndex, size));
                }
            }
            if(StringUtils.isNotEmpty(strBuf.toString())){
                filter.put(strBuf.toString());
            }
            return true;
        }catch (Exception e){
            logger.error("解析文件出错, File:{}", file.getName());
            return false;
        }finally {
            closeIO(fc);
        }
    }

    public static void closeIO(Closeable io){
        if(io != null){
            try{
                io.close();
            }catch (Exception e){

            }
        }
    }

    public static boolean unZip(String srcFile, String dest, boolean deleteFile) {
        try {
            File file = new File(srcFile);
            if (!file.exists()) {
                //throw new RuntimeException("解压文件不存在!");
                return false;
            }
            ZipFile zipFile = new ZipFile(file);
            Enumeration e = zipFile.getEntries();
            while (e.hasMoreElements()) {
                ZipEntry zipEntry = (ZipEntry) e.nextElement();
                if (zipEntry.isDirectory()) {
                    String name = zipEntry.getName();
                    name = name.substring(0, name.length() - 1);
                    File f = new File(dest + name);
                    f.mkdirs();
                } else {
                    File f = new File(dest + zipEntry.getName());
                    f.getParentFile().mkdirs();
                    f.createNewFile();
                    InputStream is = zipFile.getInputStream(zipEntry);
                    FileOutputStream fos = new FileOutputStream(f);
                    int length = 0;
                    byte[] b = new byte[1024];
                    while ((length = is.read(b, 0, 1024)) != -1) {
                        fos.write(b, 0, length);
                    }
                    is.close();
                    fos.close();
                }
            }

            if (zipFile != null) {
                zipFile.close();
            }

            if (deleteFile) {
                file.deleteOnExit();
            }

            return true;
        } catch (IOException ex) {
            return false;
        }
    }
}
