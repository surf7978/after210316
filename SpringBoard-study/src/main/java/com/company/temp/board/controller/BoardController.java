package com.company.temp.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.temp.board.service.BoardVO;
import com.company.temp.board.service.ReplyVO;
import com.company.temp.board.service.impl.BoardMapper;

@Controller
public class BoardController {
	//서비스
	@Autowired BoardMapper boardMapper;	///BoardMapper boardMapper = new BoardMapper 쓰는 대신에 Autowired를 써준다.
	
	//전체조회
	@RequestMapping("/getBoardList")				///통용되는거 .  getMapping이랑 postMapping 둘다되는거인듯.
	public String getBoardList(Model model)	 {			// 모델을 통해서 결과를 뷰페이지로 전달한다.
		model.addAttribute("list", boardMapper.getBoardList());
		return "board/getBoardList";		// board 폴더를  만들고 jsp파일을 만들면 된다. 여기까지 하고
		
	}
	
	@RequestMapping("/getBoard")   //http://localhost/getBoard? 				
	///통용되는거 .  getMapping이랑 postMapping 둘다되는거인듯.
	public String getBoard(Model model , BoardVO vo)	 {			// 모델을 통해서 결과를 뷰페이지로 전달한다.
		model.addAttribute("board", boardMapper.getBoard(vo));
		return "board/getBoard";		// board 폴더를  만들고 jsp파일을 만들면 된다. 여기까지 하고
		
	}
	
	//댓글조회
	@RequestMapping("/getReplyList")
	public @ResponseBody List<ReplyVO> getReplyList(ReplyVO vo) {  //@ResponseBody []로 바꿔줌 json구조로 바꿔준다
		return boardMapper.getReplyList(vo);
	}
	
	//댓글등록
	@RequestMapping("/insertReply")
	public @ResponseBody ReplyVO insertReply(ReplyVO vo) {  //@ResponseBody 있으면 무조건 아작스 요청결과
		boardMapper.getReplyList(vo);
		return vo;
	}
	
}
