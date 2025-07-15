package com.levelupseon.demo3.mapper;

import com.levelupseon.demo3.domain.Board;
import com.levelupseon.demo3.domain.dto.Criteria;

import java.util.List;


public interface BoardMapper {

	List<Board> list(Criteria cri);


	void insert(Board board);
	
	long getCount(Criteria cri);

	Board selectOne(Long bno);

	void update(Board board);

	void delete(Long bno);
	
	void increaseCnt(Long bno);
	
	void updateGrpMyself(Board board);
	
	void updateSeqIncrease(Board parent);
	
	void insertChild(Board board);
	
	int selectMaxSeq(Board parent);

}
