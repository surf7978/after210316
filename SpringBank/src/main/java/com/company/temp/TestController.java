package com.company.temp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.company.temp.service.TestVO;

@Controller
public class TestController {

	//get,post ,커맨드객체(VO)
	@RequestMapping("/getTest1")
	public String getTest1(TestVO vo) {
		System.out.println(vo);
		return "home";
	}
	
	// 파라미터  request.getPapameter()
	@RequestMapping("/getTest2")
	public String getTest2(@RequestParam String firstName
			              ,@RequestParam int salary) {
		System.out.println(firstName + ":" + salary);
		return "home";
	}
	
	// 배열 request.getPapameterValues()
	@RequestMapping("/getTest3")
	public String getTest3(@RequestParam("hobby") String[] hobbies ) {
		System.out.println( Arrays.asList(hobbies) );
		return "home";
	}
	
	@RequestMapping("/getTest4")
	public String getTest4(@RequestBody List<Map> vo) {
		System.out.println( vo );
		return "home";
	}
	
	@RequestMapping("/getTest5/{firstName}/{salary}")   
	public String getTest5(   @PathVariable  String firstName,
	                          @PathVariable  String salary,
	                          @ModelAttribute("tvo") TestVO vo,
	                          Model model) {
		vo.setFirstName(firstName);
		vo.setSalary(salary);
		System.out.println(vo);	
		model.addAttribute("firstName", firstName);
		return "test";
	}
	
	@RequestMapping("/getTest6/{firstName}/{salary}")   
	public ModelAndView getTest6(   @PathVariable  String firstName,
	                          @PathVariable  String salary,
	                          @ModelAttribute("tvo") TestVO vo) {
		vo.setFirstName(firstName);
		vo.setSalary(salary);
//		ModelAndView mv = new ModelAndView();
//		mv.addObject("firstName", firstName);
//		mv.setViewName("test");
		return new  ModelAndView("test", "firstName", firstName);
	}
	

	// 응답결과 json
	@RequestMapping("/getTest7/{firstName}/{salary}") 
	@ResponseBody 
	public TestVO getTest7(   @PathVariable  String firstName,
	                          @PathVariable  String salary,
	                          TestVO vo) {
		vo.setFirstName(firstName);
		vo.setSalary(salary);
		return vo;
	}
	
	@RequestMapping("/getTest8")
	@ResponseBody
	public List<Map> getTest8(){
		List list = new ArrayList<>();
		Map<String, String> map = new HashMap<>();
		map.put("name", "park");
		map.put("sal", "1000");
		list.add(map);
		
		map = new HashMap<>();
		map.put("name", "kim");
		map.put("sal", "2000");
		list.add(map);
		
		return list;
	}
}
