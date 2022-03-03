package kdh.personal.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kdh.personal.dto.BoardDTO;

@Repository
public class BoardDAO {

	@Autowired
	private SqlSessionTemplate mybatis;
	
	public int getRecordCount() {
		return mybatis.selectOne("board.getRecordCount");
	}
	
	public int getRecordCountByAll(String result) {
		return mybatis.selectOne("board.getRecordCountByAll",result);
	}
	
	public int getRecordCountByWriter(String result) {
		return mybatis.selectOne("board.getRecordCountByWriter",result);
	}
	
	public int getRecordCountByTitle(String result) {
		return mybatis.selectOne("board.getRecordCountByTitle",result);
	}
	
	public int insert(BoardDTO dto) {
		return mybatis.insert("board.insert",dto);
	}
	
	public List<BoardDTO> selectAll(){
		return mybatis.selectList("board.selectAll");
	}
	
	public BoardDTO selectContents(int seq) {
		return mybatis.selectOne("board.selectContents",seq);
	}
	
	public int deleteBySeq(int seq) {
		return mybatis.delete("board.deleteBySeq",seq);
	}
	
	public int modifyBoard(BoardDTO dto) {
		return mybatis.update("board.modifyBoard",dto);
	}
	
	public List<BoardDTO> searchAll(String result, int start, int end) {
		Map<Object, Object> map = new HashMap<>();
		map.put("result", result);
		map.put("start", start);
		map.put("end", end);
		return mybatis.selectList("board.searchAll",map);
	}
	
	public List<BoardDTO> searchByWriter(String result, int start, int end) {
		Map<Object, Object> map = new HashMap<>();
		map.put("result", result);
		map.put("start", start);
		map.put("end", end);
		return mybatis.selectList("board.searchByWriter",map);
	}
	
	public List<BoardDTO> searchByTitle(String result, int start, int end) {
		Map<Object, Object> map = new HashMap<>();
		map.put("result", result);
		map.put("start", start);
		map.put("end", end);
		return mybatis.selectList("board.searchByTitle",map);
	}
	
	public List<BoardDTO> selectByBound(int start, int end){
		Map<String, Integer> map = new HashMap<>();
		map.put("start", start);
		map.put("end", end);
		return mybatis.selectList("board.selectByBound",map);
	}
}
