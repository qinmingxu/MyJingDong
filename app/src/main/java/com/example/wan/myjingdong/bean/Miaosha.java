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
public class Miaosha {

    private List<DataList> list;
    private String name;
    private int time;
    public void setList(List<DataList> list) {
         this.list = list;
     }
     public List<DataList> getList() {
         return list;
     }

    public void setName(String name) {
         this.name = name;
     }
     public String getName() {
         return name;
     }

    public void setTime(int time) {
         this.time = time;
     }
     public int getTime() {
         return time;
     }

}