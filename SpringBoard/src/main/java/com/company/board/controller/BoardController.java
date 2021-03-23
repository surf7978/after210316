package com.company.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BoardController {
	//서비스
	@Autowired BoardMapper boardMapper;
	
	//전체조회
	@RequestMapping("/getBoardList")
	public String getBoardList(Model model) {
		model.addAttribute("list",boardMapper.getBoardList());
		return "board/getBoardList";
	}
}
