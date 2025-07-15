package com.levelupseon.demo3.mapper;

import java.util.List;
import com.levelupseon.demo3.domain.Member;


public interface MemberMapper {

	List<Member> select();
	
	Member findByNo(Long no);
	Member findById(String id);
	

	int insert(Member member);
	
	int delete(Long no);

	int update(Member member);
	
	
}
