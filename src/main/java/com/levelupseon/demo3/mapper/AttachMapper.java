package com.levelupseon.demo3.mapper;

import com.levelupseon.demo3.domain.Attach;

import java.util.List;


public interface AttachMapper {
	//첨부파일 매퍼
	void insert(Attach attach);
	List<Attach> list(Long bno);
	Attach selectOne(String uuid);
	void delete(String uuid);
	void deleteByBno(Long bno);
	
	List<Attach> selectYesterdayList();

}
