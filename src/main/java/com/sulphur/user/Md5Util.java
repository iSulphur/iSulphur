package com.sulphur.user;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util {

	public String strToMd5(String str) throws NoSuchAlgorithmException{
		String md5Str = null;
		if(str !=null && str.length()!=0){
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte b[] = md.digest();
			
			int i;
			StringBuffer buf = new StringBuffer("");
			for(int offset = 0;offset<b.length;offset++){
				i = b[offset];
				if(i<0)
					i+=256;
				if(i<16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			md5Str = buf.toString().substring(8, 24);
		}
		return md5Str;
	}
}