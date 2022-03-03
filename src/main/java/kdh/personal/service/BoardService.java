package kdh.personal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kdh.personal.dao.BoardDAO;
import kdh.personal.dto.BoardDTO;
import kdh.personal.statics.Statics;

@Service
public class BoardService {
	
	@Autowired
	private BoardDAO bdao;
	
	public int getRecordCount() {
		return bdao.getRecordCount();
	}
	
	public int getRecordCountByAll(String result) {
		return bdao.getRecordCountByAll(result);
	}
	
	public int getRecordCountByWriter(String result) {
		return bdao.getRecordCountByWriter(result);
	}
	
	public int getRecordCountByTitle(String result) {
		return bdao.getRecordCountByTitle(result);
	}
	
	public int getPageTotalCount(int recordCount) {
		int recordTotalCount = recordCount;
		
		int pageTotalCount = 0;
		if(recordTotalCount % Statics.record_count_per_page == 0) {
			pageTotalCount = recordTotalCount / Statics.record_count_per_page;
		}else {
			pageTotalCount = recordTotalCount / Statics.record_count_per_page+1;
		}
		return pageTotalCount;
	}
	
	public String getPageNavi(int recordCount, int cpage) {
		int recordTotalCount = recordCount;
		
		int pageTotalCount = 0;
		if(recordTotalCount % Statics.record_count_per_page == 0) {
			pageTotalCount = recordTotalCount / Statics.record_count_per_page;
		}else {
			pageTotalCount = recordTotalCount / Statics.record_count_per_page+1;
		}	
		
		// 잚못된 파라미터에 대한 방어작업
//		if(cpage < 1) {
//			cpage = 1;
//		}else if(cpage > pageTotalCount) {
//			cpage = pageTotalCount;
//		}
		
		int startNavi = (cpage-1)/Statics.navi_count_per_page*Statics.navi_count_per_page+1;
		int endNavi = startNavi + Statics.navi_count_per_page - 1;
		
		if(endNavi > pageTotalCount) {
			endNavi = pageTotalCount;
		}
		
		boolean needPrev = true;
		boolean needNext = true;
		
		if(startNavi == 1) {
			needPrev = false;
		}
		if(endNavi == pageTotalCount) {
			needNext = false;
		}
		
		String pageNavi = "";
		if(needPrev) {
			pageNavi += "<a href='/board/boardList?cpage="+(startNavi-1)+"'><</a> ";
		}
		for(int i = startNavi; i <= endNavi; i++) {
			pageNavi +="<a href=' /board/boardList?cpage="+i+"'>"+ i+"</a> ";
		}
		if(needNext) {
			pageNavi += "<a href='/board/boardList?cpage="+(endNavi+1)+"'>></a>";
		}
		return pageNavi;
	}
	
	public String getPageNaviByRecordCount(int recordCount, int cpage, String result, String option) {
		int recordTotalCount = recordCount;
		
		int pageTotalCount = 0;
		if(recordTotalCount % Statics.record_count_per_page == 0) {
			pageTotalCount = recordTotalCount / Statics.record_count_per_page;
		}else {
			pageTotalCount = recordTotalCount / Statics.record_count_per_page+1;
		}	
		
		// 잚못된 파라미터에 대한 방어작업
//		if(cpage < 1) {
//			cpage = 1;
//		}else if(cpage > pageTotalCount) {
//			cpage = pageTotalCount;
//		}
		
		int startNavi = (cpage-1)/Statics.navi_count_per_page*Statics.navi_count_per_page+1;
		int endNavi = startNavi + Statics.navi_count_per_page - 1;
		
		if(endNavi > pageTotalCount) {
			endNavi = pageTotalCount;
		}
		
		boolean needPrev = true;
		boolean needNext = true;
		
		if(startNavi == 1) {
			needPrev = false;
		}
		if(endNavi == pageTotalCount) {
			needNext = false;
		}
		
		String pageNavi = "";
		if(needPrev) {
			pageNavi += "<a href='/board/searchResult?cpage="+(startNavi-1)+"&result="+result+"&option="+option+"'><</a> ";
		}
		for(int i = startNavi; i <= endNavi; i++) {
			pageNavi +="<a href=' /board/searchResult?cpage="+i+"&result="+result+"&option="+option+"'>"+ i+"</a> ";
		}
		if(needNext) {
			pageNavi += "<a href='/board/searchResult?cpage="+(endNavi+1)+"&result="+result+"&option="+option+"'>></a>";
		}
		return pageNavi;
	}
	
	public int insert(BoardDTO dto) {
		return bdao.insert(dto);
	}
	
	public List<BoardDTO> selectAll(){
		return bdao.selectAll();
	}
	
	public BoardDTO selectContents(int seq) {
		return bdao.selectContents(seq);
	}
	
	public int deleteBySeq(int seq) {
		return bdao.deleteBySeq(seq);
	}
	
	public int modifyBoard(BoardDTO dto) {
		return bdao.modifyBoard(dto);
	}
	
	public List<BoardDTO> searchAll(String result, int start, int end) {
		return bdao.searchAll(result, start, end);
	}
	
	public List<BoardDTO> searchByWriter(String result, int start, int end) {
		return bdao.searchByWriter(result, start, end);
	}

	public List<BoardDTO> searchByTitle(String result, int start, int end) {
		return bdao.searchByTitle(result, start, end);
	}
	
	//페이징 관련 코드
	public List<BoardDTO> selectByBound(int start, int end){
		return bdao.selectByBound(start, end);
	}

	
}
