/** 
 * Project Name:game-link-manager 
 * File Name:QiniuUploadUtil.java 
 * Package Name:com.imopan.glm.util 
 * Date:2017年1月14日下午6:00:15 
 * Copyright (c) 2017, zhangjiakun@imopan.com All Rights Reserved. 
 * 
*/ 

package com.imopan.glm.util;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

/** 
 * ClassName:QiniuUploadUtil <br/> 
 * Function: 七牛云上传工具类. <br/>  
 * Date:     2017年1月14日 下午6:00:15 <br/> 
 * @author   zhangjiakun 
 * @version   
 * @since    JDK 1.7       
 */
public class QiniuUploadUtil {
	 //设置好账号的ACCESS_KEY和SECRET_KEY
	  static String ACCESS_KEY = "AVA89oX1NiWOg-m1ZvgJfgSwRHSYASQ1ApQ2K7_R";
	  static String SECRET_KEY = "QQaSIxA0YhzgZMvCzUqEn70UoOVGGik5XwY1aDCW";
	  //要上传的空间
	  static String bucketname = "gamelink";
	  //上传到七牛后保存的文件名
	  static String key = "";

	  //密钥配置
	  static Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
	  
	  //访问图片的外网域名
	  static String u = "http://img.gfan.com/";


	  //自动识别要上传的空间(bucket)的存储区域是华东、华北、华南。
	  static Zone z = Zone.autoZone();
	  static Configuration c = new Configuration(z);

	  //创建上传对象
	  static UploadManager uploadManager = new UploadManager(c);
	  
	//设置callbackUrl以及callbackBody,七牛将文件名和文件大小回调给业务服务器
	  private static String getUpToken(){
	      /*return auth.uploadToken(null,null,3600,new StringMap()
	          .put("callbackUrl","http://your.domain.com/callback")
	          .put("callbackBody", "filename=$(fname)&filesize=$(fsize)"));*/
	      
	      return auth.uploadToken(bucketname);
	  }
	  

	  
	  private static Response uploadByFilePath(String filePath,String fileName) throws IOException{
	    try {
	      //调用put方法上传
	      Response res = uploadManager.put(filePath, fileName, getUpToken());
	      //打印返回的信息
	      return res;
	      } catch (QiniuException e) {
	          Response r = e.response;
	          // 请求失败时打印的异常的信息
	          System.out.println(r.toString());
	          try {
	              //响应的文本信息
	            System.out.println(r.bodyString());
	          } catch (QiniuException e1) {
	              //ignore
	          }
	      }
		return null;       
	  }
	  
	  
	  private static Response uploadByByte(byte[] b,String fileName) throws IOException{
		    try {
		      //调用put方法上传
		      Response res = uploadManager.put(b, fileName, getUpToken());
		      //打印返回的信息
		      return res;
		      } catch (QiniuException e) {
		          Response r = e.response;
		          // 请求失败时打印的异常的信息
		          System.out.println(r.toString());
		          try {
		              //响应的文本信息
		            System.out.println(r.bodyString());
		          } catch (QiniuException e1) {
		              //ignore
		          }
		      }
			return null;       
		  }

	  /**
	   * 
	   * uploadUtil:上传工具类. <br/> 
	   * 
	   * @author zhangjiakun
	   * @param filePath  文件路径
	   * @param fileName  保存后的文件名 （可为空）
	   * @param bucketname 空间名称 （可为空，默认为‘gamelink’）
	   * @return  返回图片链接地址
	   * @throws IOException 
	   * @since JDK 1.7
	   */
	  public static String uploadUtil(String filePath,String fileName,String bucketname) throws IOException{
		  if(StringUtils.isNotEmpty(bucketname)){
			  QiniuUploadUtil.bucketname = bucketname;
		  }
		  Response uploadByFilePath = uploadByFilePath(filePath,fileName);
		  if(uploadByFilePath != null){
			  String s = uploadByFilePath.jsonToMap().get("key").toString();
			  return u+s;
		  }
		  return null;
	  }
	  
	  /**
	   * 
	   * uploadUtil:上传工具类. <br/> 
	   * 
	   * @author zhangjiakun
	   * @param b 数组
	   * @param fileName 文件名称（可为空）
	   * @param bucketname 空间名称（可为空，默认为‘gamelink’）
	   * @return	返回图片链接地址
	   * @throws IOException 
	   * @since JDK 1.7
	   */
	  public static String uploadUtil(byte[] b,String fileName,String bucketname) throws IOException{
		  if(StringUtils.isNotEmpty(bucketname)){
			  QiniuUploadUtil.bucketname = bucketname;
		  }
		  Response uploadByFilePath = uploadByByte(b,fileName);
		  if(uploadByFilePath != null){
			  String s = uploadByFilePath.jsonToMap().get("key").toString();
			  return u+s;
		  }
		  return null;
	  }
	  
	  public static void main(String[] args) {
		  String a = "";
		  String filepath = "C:\\Users\\Administrator\\Desktop\\0357433bafacec8.jpg";
		  try {
			String uploadUtil = uploadUtil(filepath,"zxc.jpg",null);
			System.out.println(uploadUtil);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	  
	  
}
