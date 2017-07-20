package com.sulphur.admin.test;

import java.security.NoSuchAlgorithmException;

import com.sulphur.user.Md5Util;

public class Test {
	public static void main(String[] args) {
		String md5Pass;
		Md5Util md5 = new Md5Util();
		try {
			md5Pass = md5.strToMd5("654321");
			System.out.println(md5Pass);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
