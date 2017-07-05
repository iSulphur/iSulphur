package com.sulphur.admin.test;

public class Test {
	public static void main(String[] args) {
//        String s=HttpRequest.sendGet("http://localhost:6144/Home/RequestString", "key=123&v=456");
//        System.out.println(s);
        
        //���� POST ����
		
		String sr0=HttpRequest.sendPost("http://localhost:8080/iSulphur/admin/update_pwd.do", "password=123");
		System.out.println(sr0);
//		String sr1=HttpRequest.sendPost("http://localhost:8080/iSulphur/admin/update_pwd.do", "password=123");
//        System.out.println(sr1);
	}

}
