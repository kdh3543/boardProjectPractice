package kdh.personal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kdh.personal.dao.MemberDAO;
import kdh.personal.dto.MemberDTO;

@Service
public class MemberService {

	@Autowired
	private MemberDAO mdao;
	
	public int login(String id, String pw) {
		return mdao.login(id,pw); 
	}
	
	public int signUp(MemberDTO dto) {
		return mdao.signUp(dto);
	}
}
