package com.company.common;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import com.company.board.service.BoardVO;
import com.company.board.service.JsonUtil;

public class JsonUtilTest {
	public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		JsonUtil json = new JsonUtil();
		//List = vo 같은개념이라 []쓴다, +map이면 {}
		Map<String, Object> map = new HashMap<>();
		map.put("username", "홍길동");
		map.put("age", "30");
		map.put("dept", "개발");
		String result = json.toJson(map);
		System.out.println(result); // {"username":"홍길동","age":"30","dept":"개발"}
		
		BoardVO vo = new BoardVO();
		vo.setContent("test");
		vo.setTitle("title");
		vo.setFilename("file");
		result = json.toObjectJson(vo);
	}
}
