package com.lxk.ChatRoom.utils;

import java.util.Random;

public class Test {
	public static void main(String[] args) {
		String id = CreateId();
		System.out.println(id);
//		while(true){
//			if(id.length() == 6 && !id.substring(0,1).equals('0') ){
////				user.setUsername(id);
//				System.out.println("id:"+id);
//				break;
//			}else{
//				id = CreateId();
//			}
//		}
	}
	
	public static String CreateId() {
		//设置user的id，生成一个id
		Random r = new Random();
		int nextInt = r.nextInt(1000000);
		String id = String.valueOf(nextInt);
		return id;
	}
}
