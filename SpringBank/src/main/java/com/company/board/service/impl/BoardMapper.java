package com.company.board.service.impl;

import com.company.board.service.BoardVO;

public interface BoardMapper {
	public int insertBoard(BoardVO vo);
	public BoardVO getBoard(BoardVO vo);
}
