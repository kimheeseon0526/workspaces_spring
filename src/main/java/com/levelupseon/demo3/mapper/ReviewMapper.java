package com.levelupseon.demo3.mapper;

import java.util.List;

import com.levelupseon.demo3.domain.Review;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


public interface ReviewMapper {
	@Select("select * from tbl_review")
	List<Review> select();
	
	@Select("select * from tbl_review where rno = #{rno}")
	Review findBy(Long rno);
	
	@Insert("insert into tbl_review(content, rating, writer, pno) values (#{content}, #{rating}, #{writer}, #{pno})")
	int insert(Review review);
	
	@Update("update tbl_review set content = #{content}, rating = #{rating}, writer = #{writer} where rno = #{rno}")
	int update(Review review);
	
	@Delete("delete from tbl_review where rno = #{rno}")
	int remove(Long rno);
	
}
