package com.company.board.service;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

//map, vo 를 Json String으로 변환하는 유틸파일 작성
public class JsonUtil {
	public String toJson(Map<String, Object> map) {
		StringBuilder result= new StringBuilder();
		result.append("{");
		Iterator<String> keys = map.keySet().iterator(); // Set 중복값 허용 X , 순서상관없음 ex){1,2} U {2,3} = {1,2,3}
		int i=0;
		while(keys.hasNext()) {
			String key = keys.next();
			String value = (String) map.get(key);
			if(i!=0) {
				result.append(",");
			}
			i++;
			result.append("\""+key+"\":\""+value+"\"");
		}
		result.append("}");
		return result.toString();
	}
	
	public String toJson(List<Map<String, Object>> map) {
		String result="";
		return result;
	}
	
	public String toObjectJson(Object vo) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		String result="";
		Field[] fields = vo.getClass().getDeclaredFields();
		for(Field field : fields) {
			String fieldName = field.getName();
			String method = "get"+field.getName().substring(0,1).toUpperCase()+field.getName().substring(1);
			Method m = vo.getClass().getDeclaredMethod(method, null);
			String value = (String) m.invoke(vo, null);
			System.out.println(fieldName+":"+value);
		}
		return result;
	}
	
	public String toObjectJson(List<Object> vo) {
		String result="";
		return result;
	}
}
