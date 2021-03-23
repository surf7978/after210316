package com.company.temp.board.service.impl;

import java.util.List;

import com.company.temp.board.service.BoardVO;
import com.company.temp.board.service.ReplyVO;

public interface BoardMapper {
	//게시글
	//board_mapper.xml랑 세트.!	보드메퍼.xml을 실행하는게 BoardMapper.java다 . 보면서 규칙대로 만들어야함.
	public List<BoardVO> getBoardList( );				//실행해야할 것의 id랑 메소드명이 같아야한다. 파라메터 타입이 매개변수에 들어가야한다.
												//한건이라면 그냥 VO써주면 되고 여러건이면 List 를 써주면 된다.
	public BoardVO  getBoard(BoardVO vo);		//파라메터 타입이 있어서 보드보를 넣어줬다	
	
	//댓글
	public List<ReplyVO> getReplyList(ReplyVO vo);
	
	public int insertReply(ReplyVO vo); //int나 void나 상관없다
}
