package com.ssm_Oracle.util;

import java.util.Collection;
import java.util.Map;

public class CommonUtils {
	
	public static  boolean isEmpty(Object obj){
		if(obj == null){
			return true;
		}else if(obj instanceof String){
			if(((String) obj).trim().length() == 0){
				return true;
			}
		}else if(obj instanceof Map){
			if(((Map) obj).size() == 0){
				return true;
			}
		}else if(obj instanceof Collection){
			if(((Collection) obj).size()==0){
				return true;
			}
		}
		return false;
	}
	
	public static  boolean isNotEmpty(Object obj){
		if(isEmpty(obj)==true){
			
			return false;
		}else{
			
			return true;
		}
	}
}
