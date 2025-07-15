package com.levelupseon.demo3.domain;

import java.io.File;

import org.apache.ibatis.type.Alias;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Alias("attach")
public class Attach {

	private String uuid;	//파일 아이디
	private String path;	//경로(날짜별로 파일 관리하기 위한)
	private boolean image;	//파일 존재 여부
	private String origin;	//파일 원본
	private Long bno;
	private Long rno;
	private int odr;
	private long size;
	
	public Attach(String uuid, String path, boolean image, String origin, Long bno, int odr, long size) {
		super();
		this.uuid = uuid;
		this.path = path;
		this.image = image;
		this.origin = origin;
		this.bno = bno;
		this.odr = odr;
		this.size = size;
	}
	
//	public File toFile() {
//		//물리적 경로에 있는 파일명
//		//path : 자기 인스턴스에 있는 path 가져옴
//		return new File(UploadFile.UPLOAD_PATH + "/" + path, uuid);
//	}
	
	public Attach toThumb() {
		return Attach.builder().bno(bno).image(image).uuid("t_" + uuid).path(path).origin(origin).odr(odr).size(size).build();
	}
	
	
	
}
