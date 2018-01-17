/**
  * Copyright 2017 bejson.com 
  */
package com.example.wan.myjingdong.bean;
import java.util.List;

/**
 * Auto-generated: 2017-11-13 15:10:56
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class JsonRootBean {

    private String msg;
    private String code;
    private List<Data> data;
    private Tuijian tuijian;
    private Miaosha miaosha;
    public void setMsg(String msg) {
         this.msg = msg;
     }
     public String getMsg() {
         return msg;
     }

    public void setCode(String code) {
         this.code = code;
     }
     public String getCode() {
         return code;
     }

    public void setData(List<Data> data) {
         this.data = data;
     }
     public List<Data> getData() {
         return data;
     }

    public void setTuijian(Tuijian tuijian) {
         this.tuijian = tuijian;
     }
     public Tuijian getTuijian() {
         return tuijian;
     }

    public void setMiaosha(Miaosha miaosha) {
         this.miaosha = miaosha;
     }
     public Miaosha getMiaosha() {
         return miaosha;
     }

}