package kdh.personal.dao;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kdh.personal.dto.MemberDTO;

@Repository
public class MemberDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;	
	
	public int login(String id, String pw) {
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("pw", pw);
		return mybatis.selectOne("member.login",map);
	}
	
	public int signUp(MemberDTO dto) {
		return mybatis.insert("member.signUp",dto);
	}
	
}
