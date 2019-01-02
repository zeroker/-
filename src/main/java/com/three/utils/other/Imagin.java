package com.three.utils.other;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class Imagin {

    BASE64Decoder decoder = new BASE64Decoder();
    BASE64Encoder encoder = new BASE64Encoder();
    private byte [] bytes;
    private String url;
    public Imagin(String url) throws UnsupportedEncodingException {
        this.url = url;
    }

    public void ByteToFile(String strImg)throws Exception{
        System.out.println(strImg);
       // bytes = strImg.getBytes(CharEncoding.ISO_8859_1);
        bytes = decoder.decodeBuffer(strImg);
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        BufferedImage bi1 =ImageIO.read(bais);
        try {
            System.out.println(url);                           //
            File w2 = new File(url);                                 //可以是jpg,png,gif格式
            ImageIO.write(bi1, "jpg", w2);              //不管输出什么格式图片，此处不需改动
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            bais.close();
        }
    }

    public String fileToByte() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        File img = new File(url);
        try {
            BufferedImage bi;
            bi = ImageIO.read(img);
            ImageIO.write(bi, "jpg", baos);
            bytes = baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            baos.close();
        }
        //String strImg = new String(bytes,CharEncoding.ISO_8859_1);
        String strImg = encoder.encode(bytes);
        return strImg;
    }
}
