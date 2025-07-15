package com.levelupseon.demo3.mapper;

import java.util.List;

import com.levelupseon.demo3.domain.Category;
import org.apache.ibatis.annotations.Select;


public interface CategoryMapper {
	@Select("select * from tbl_category tc where tc.status = 'ACTIVE' order by odr;")
	List<Category> list();
}
