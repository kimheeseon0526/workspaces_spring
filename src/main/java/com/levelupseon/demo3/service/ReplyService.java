package com.levelupseon.demo3.service;

import java.util.List;

import com.levelupseon.demo3.domain.Reply;
import com.levelupseon.demo3.mapper.ReplyMapper;
import lombok.AllArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ReplyService {
  private ReplyMapper mapper;

	public Reply findBy(Long rno) {
			return mapper.selectOne(rno);
	}

	public List<Reply> list(Long bno, Long lastRno) {
			return mapper.list(bno, lastRno);
	}

	public void register(Reply reply) {
			mapper.insert(reply);
	}

	public void modify(Reply reply) {
			mapper.update(reply);
	}

	public void remove(Long rno) {
			mapper.delete(rno);
	}
}
