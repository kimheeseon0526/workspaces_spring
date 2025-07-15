package com.levelupseon.demo3.mapper;

import java.util.List;

import com.levelupseon.demo3.domain.Reply;
import org.apache.ibatis.annotations.Param;

public interface ReplyMapper {
	void insert(Reply reply);
	void update(Reply reply);
	void delete(Long rno);
	void deleteByBno(Long rno);
	
	Reply selectOne(Long rno);
	
	List<Reply> list(@Param("bno") Long bno, @Param("lastRno") Long lastRno);
}
