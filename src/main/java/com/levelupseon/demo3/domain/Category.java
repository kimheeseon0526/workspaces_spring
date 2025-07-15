package com.levelupseon.demo3.domain;

import com.levelupseon.demo3.domain.en.CategoryStatus;
import com.levelupseon.demo3.domain.en.CategoryViewType;
import org.apache.ibatis.type.Alias;
import lombok.Builder;
import lombok.Data;

@Alias("category")
@Data
@Builder
public class Category {
	private Long cno;
	private String cname;
	private String regdate;
	private CategoryViewType cViewType; //enum
	private int odr;
	private CategoryStatus status;	//enum
}
