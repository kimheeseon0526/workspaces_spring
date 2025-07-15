package com.levelupseon.demo3.service;

import com.levelupseon.demo3.domain.Member;
import com.levelupseon.demo3.mapper.MemberMapper;
import com.levelupseon.demo3.util.PasswordEncoder;
import lombok.AllArgsConstructor;
import org.apache.ibatis.session.SqlSession;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

//import mapper.MemberMapper;
//import util.MybatisUtil;
//import util.PasswordEncoder;
@Slf4j
@AllArgsConstructor
@Service
public class MemberService {
  private MemberMapper mapper;
  private PasswordEncoder passwordEncoder;

	//회원가입
	public int register(Member member) {
			member.setPw(passwordEncoder.encode(member.getPw()));
			return mapper.insert(member);
	}
	public Member findById(String id) {
			return mapper.findById(id);
	}
	public boolean login(String id, String pw) {
		Member member = findById(id);
		if(member == null) {
			return false;
		}
		return passwordEncoder.matches(pw,  member.getPw());
	}

}
