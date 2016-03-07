package com.ryan.commons.util.File;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ryan on 15/5/25.
 * 魔数
 */
public enum FileTypeEnum {

    /**
     * JPEG
     */
    JPEG(FileTypeConstant.JPEG, "FFD8FF"),
    /**
     * PNG
     */
    PNG(FileTypeConstant.PNG, "89504E47"),
    /**
     * GIF
     */
    GIF(FileTypeConstant.GIF, "47494638"),
    /**
     * TIFF
     */
    TIFF(FileTypeConstant.TIFF, "49492A00"),
    /**
     * WINDOWS BITMAP
     */
    BMP(FileTypeConstant.BMP, "424D"),
    /**
     * CAD
     */
    DWG(FileTypeConstant.DWG, "41433130"),
    /**
     * ADOBE PHOTOSHOP
     */
    PSD(FileTypeConstant.PSD, "38425053"),
    /**
     * XML
     */
    XML(FileTypeConstant.XML, "3C3F786D6C"),
    /**
     * HTML
     */
    HTML(FileTypeConstant.HTML, "68746D6C3E"),
    /**
     * ADOBE ACROBAT
     */
    PDF(FileTypeConstant.PDF, "25504446"),
    /**
     * ZIP ARCHIVE
     */
    ZIP(FileTypeConstant.ZIP, "504B0304"),
    /**
     * RAR ARCHIVE
     */
    RAR(FileTypeConstant.RAR, "52617221"),
    /**
     * WAVE
     */
    WAV(FileTypeConstant.WAV, "57415645"),
    /**
     * AVI
     */
    AVI(FileTypeConstant.AVI, "41564920")
    ;

    private String magicValue;
    private String fileType;

    private FileTypeEnum(String fileType, String magicValue){
        this.fileType = fileType;
        this.magicValue = magicValue;
    }

    public String getMagicValue() {
        return magicValue;
    }

    public void setMagicValue(String magicValue) {
        this.magicValue = magicValue;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileTypeByMagicValue(@NotNull String magicValue){
        for(FileTypeEnum f : FileTypeEnum.values()){
            if(f.getMagicValue().equals(magicValue)){
                return f.getFileType();
            }
        }
        return null;
    }

    public static Map<String, String> getMap(){
        FileTypeEnum[] arrs = FileTypeEnum.values();
        Map<String, String> map = new HashMap<String, String>();
        for(FileTypeEnum arr : arrs){
            map.put(arr.getFileType(), arr.getMagicValue());
        }
        return map;
    }

}
