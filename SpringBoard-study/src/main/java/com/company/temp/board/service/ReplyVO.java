package com.company.temp.board.service;

import lombok.Data;

@Data
public class ReplyVO {
	
	String Seq;	//댓글번호
	String Content; //댓글내용
	String board_seq; //게시글 번호
}
