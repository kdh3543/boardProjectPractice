package kdh.personal.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import kdh.personal.dto.BoardDTO;
import kdh.personal.service.BoardService;
import kdh.personal.statics.Statics;

@Controller
@RequestMapping("/board/")
public class BoardController {

	@Autowired
	private HttpSession session;
	@Autowired
	private BoardService bService;

	@RequestMapping("boardList")
	public String boardList(int cpage, Model model) {
		int recordCount = bService.getRecordCount();

		String navi = bService.getPageNavi(recordCount,cpage);
		//		List<BoardDTO> list = bService.selectAll();

		int pageTotalCount = bService.getPageTotalCount(recordCount);
		if(cpage < 1) {
			cpage=1;
		}else if(cpage > pageTotalCount) {
			cpage = pageTotalCount;
		}

		int start = cpage*Statics.record_count_per_page-(Statics.record_count_per_page-1);
		int end = cpage*Statics.record_count_per_page;

		List<BoardDTO> list = bService.selectByBound(start, end);
		model.addAttribute("navi",navi);
		model.addAttribute("list",list);
		return "/board/boardList";
	}

	@RequestMapping("writeForm")
	public String writeForm() {
		return "/board/writeForm";
	}

	@RequestMapping("writeSuccess")
	public String writeSuccess(BoardDTO dto) {
		String id = (String)session.getAttribute("loginId");
		System.out.println(id);

		dto.setWriter(id);
		int result = bService.insert(dto);
		return "redirect:boardList?cpage=1";
	}

	@RequestMapping("boardContents")
	public String selectContents(int seq, Model model){
		String id = (String)session.getAttribute("loginId");
		BoardDTO dto = bService.selectContents(seq);
		model.addAttribute("dto",dto);
		model.addAttribute("id",id);
		return "/board/writeView";
	}

	@RequestMapping("deleteBoard")
	public String deleteBoard(int seq) {
		System.out.println("들어온 seq 값은: "+seq);
		bService.deleteBySeq(seq);
		return "redirect:boardList";
	}

	@RequestMapping("modSuccess")
	public String modSuccess(BoardDTO dto, Model model) {
		String id = (String)session.getAttribute("loginId");
		bService.modifyBoard(dto);
		dto = bService.selectContents(dto.getSeq());
		model.addAttribute("dto",dto);
		model.addAttribute("id",id);
		return "/board/writeView";
	}

	@RequestMapping("searchResult")
	public String searchResult(int cpage, String result, String option, Model model) {
		System.out.println("결과 값은: "+ result);
		System.out.println("옵션 값은: "+ option);

		List<BoardDTO> list = null;
		int recordCount = 0;

		int start = cpage*Statics.record_count_per_page-(Statics.record_count_per_page-1);
		int end = cpage*Statics.record_count_per_page;
		
		if(option.equals("검색")) {
			list = bService.searchAll(result,start,end);
			recordCount = bService.getRecordCountByAll(result);
			System.out.println("검색 개수는: "+ recordCount);
		}else if(option.equals("글쓴이")) {
			list = bService.searchByWriter(result,start,end);
			recordCount = bService.getRecordCountByWriter(result);
			System.out.println("글쓴이 개수는: "+ recordCount);
		}else{
			list = bService.searchByTitle(result,start,end);
			recordCount = bService.getRecordCountByTitle(result);
			System.out.println("제목 개수는: "+ recordCount);
		}

		
		String navi = bService.getPageNaviByRecordCount(recordCount,cpage,result,option);
		//		List<BoardDTO> list = bService.selectAll();

		int pageTotalCount = bService.getPageTotalCount(recordCount);
		if(cpage < 1) {
			cpage=1;
		}else if(cpage > pageTotalCount) {
			cpage = pageTotalCount;
		}
		

		//		List<BoardDTO> list = bService.selectByBound(start, end);

		model.addAttribute("navi",navi);
		model.addAttribute("list",list);
		return "/board/boardList";
	}

	//	@ResponseBody
	//	@RequestMapping(value = "searchResult", produces = "text/html; charset = utf8")
	//	public String searchResult(String result, String option, Model model) {
	//		System.out.println("결과 값은: "+ result);
	//		System.out.println("옵션 값은: "+ option);
	//		List<BoardDTO> list = null;
	//		
	//		if(result=="") {
	//			list = bService.selectAll();
	//		}else {
	//			if(option.equals("검색")) {
	//				list = bService.searchAll(result);
	//			}else if(option.equals("글쓴이")) {
	//				list = bService.searchByWriter(result);
	//			}else{
	//				list = bService.searchByTitle(result);
	//			}
	//		}
	//		System.out.println(list.size());
	//		
	//		Gson g = new Gson();
	//		String gList = g.toJson(list);
	//		
	//		return String.valueOf(gList);
	//	}
}
