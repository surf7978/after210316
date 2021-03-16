package com.company.board.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.company.board.service.BoardVO;
import com.company.board.service.impl.BoardMapper;
import com.company.common.FileRenamePolicy;

@Controller
public class BoardController {

	@Autowired BoardMapper boardMapper;
	
	@GetMapping("/insertBoard")
	public String insertBoardForm() {
		return "board/insertBoard";
	}
	
	@PostMapping("/insertBoard")
	public String insertBoard(BoardVO vo) throws IllegalStateException, IOException {
		System.out.println(vo);
		//첨부파일처리
		MultipartFile[] files = vo.getUploadFile();
		String filenames = "";
		boolean start = true;
		for(MultipartFile file : files) {
			if(file != null && ! file.isEmpty() && file.getSize()>0 ) {
				String filename = file.getOriginalFilename();
				//파일명 중복 체크
				File rename = FileRenamePolicy.rename(new File("c:/upload", filename)); //같은 파일명 있으면 이름 바꿈
				//여러개 파일명 조회되게 하기
				if( ! start) {
					filenames += ",";
				}else {
					start = false;
				}
				filenames += rename.getName();
				// 임시폴더에서 업로드 폴더로 파일 이동
				file.transferTo(rename);//바뀐이름으로 저장
			}
		}
		vo.setFilename(filenames); // vo 업로드된 파일명 담아서 DB에 저장
		//등록 서비스 호출
		boardMapper.insertBoard(vo);
		return "redirect:/getBoard?seq="+vo.getSeq();
	}
	
	@GetMapping("/getBoard")
	public String getBoard(BoardVO vo, Model model) {
		model.addAttribute("board", boardMapper.getBoard(vo));
		return "board/getBoard";
	}
	
	@GetMapping("/fileDown")
	public void fileDown(BoardVO vo, HttpServletResponse response) throws IOException {
		vo = boardMapper.getBoard(vo);
		File file = new File("c:/upload", vo.getFilename());
		if(file.exists()) { //파일이 있으면=file.exists()
			response.setContentType("application/octet-stream;charset=UTF-8");
			response.setHeader("Content-Disposition", "attachment;filename=\"" 
								+ URLEncoder.encode(vo.getFilename(), "utf-8") + "\"");//다운 받을 때 보여주는 파일명
			BufferedInputStream in = null;
			BufferedOutputStream out = null;
				try {
					in = new BufferedInputStream(new FileInputStream(file));
					out = new BufferedOutputStream(response.getOutputStream());
					FileCopyUtils.copy(in, out); //input스트림으로 받아서 output스트림으로 주면 이게 다운로드임
					out.flush();
				} catch (IOException ex) {
				} finally {
					in.close(); 
					response.getOutputStream().flush(); 
					response.getOutputStream().close();
				}
		}else {
			response.setContentType("text/html; charset=utf-8");
			response.getWriter()
					.append("<script>")
					.append("alert('file not found(파일없음)');")
					.append("history.go(-1)")
					.append("</script>");
		}
	}
	
	@GetMapping("/fileNameDown")
	public void fileNameDown(BoardVO vo, HttpServletResponse response) throws IOException {
		//vo = boardMapper.getBoard(vo); 얘만 빼면됨 ---------------ㄱ
		File file = new File("c:/upload", vo.getFilename()); //여기 파일명 있으니까
		if(file.exists()) { //파일이 있으면=file.exists()
			response.setContentType("application/octet-stream;charset=UTF-8");
			response.setHeader("Content-Disposition", "attachment;filename=\"" 
								+ URLEncoder.encode(vo.getFilename(), "utf-8") + "\"");//다운 받을 때 보여주는 파일명
			BufferedInputStream in = null;
			BufferedOutputStream out = null;
				try {
					in = new BufferedInputStream(new FileInputStream(file));
					out = new BufferedOutputStream(response.getOutputStream());
					FileCopyUtils.copy(in, out); //input스트림으로 받아서 output스트림으로 주면 이게 다운로드임
					out.flush();
				} catch (IOException ex) {
				} finally {
					in.close(); 
					response.getOutputStream().flush(); 
					response.getOutputStream().close();
				}
		}else {
			response.setContentType("text/html; charset=utf-8");
			response.getWriter()
					.append("<script>")
					.append("alert('file not found(파일없음)');")
					.append("history.go(-1)")
					.append("</script>");
		}
	}
}
