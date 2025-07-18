package com.levelupseon.demo3.controller;

import com.levelupseon.demo3.domain.Attach;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@Slf4j
public class UploadController {
  public final static String UPLOAD_PATH = "d:/upload/files";

  @GetMapping("upload")
  public String uploadForm() {
    return "uploadForm";
  }

  @PostMapping("upload")
  @ResponseBody //JSP나 HTML로 안 가고, 그냥 문자열이 출력
  public ResponseEntity<?> upload(List<MultipartFile> f1) throws IOException {
    f1.forEach( f -> log.info(f.getOriginalFilename()));

    //업로드된 파일 처리
    List<Attach> attachs = new ArrayList<>();

    int odr = 0;

    for(MultipartFile part : f1) {
      if(part.getSize() == 0) {
        continue; //빈파일이면 넘어감
      }
      Long fileSize =  part.getSize();
      String origin =  part.getOriginalFilename();
      String contentType = part.getContentType(); //image, png 등

      //ext 추출
      int idx = origin.lastIndexOf(".");
      String ext = "";
      if(idx >= 0) {
        //확장자가 존재하는 경우
        ext = origin.substring(idx);
      }

      UUID uuid = UUID.randomUUID();	//이제 안겹친
      String fileName = uuid + ext;

      boolean image = contentType.startsWith("image");
      String path = genPath();
      String realPath = UPLOAD_PATH + "/" + path + "/";
      File file = new File(realPath);
      if(!file.exists()) {
        file.mkdirs();
      }

      part.transferTo(new File(realPath + fileName));
      if(image) {
        try {
          //이미지인 경우 추가 처리 > 썸네일 생성
          Thumbnails.of(new File(realPath + fileName)).size(150, 150).toFile(realPath + "t_" + fileName);
        }
        catch(Exception e) { // 이미지 변환 시도했는데 에러 뜨면 이건 이미지 아니라고 하고 종료
          image = false;
        }

      }


      log.info(" {} :: {} :: {} :: {}", fileSize, origin, contentType, ext);
      attachs.add(Attach.builder()
              .uuid(fileName)
              .origin(origin)
              .image(image)
              .path(path)
              .odr(odr++) //0,1,2,3~
              .size(fileSize)
              .build());
    }
    return ResponseEntity.ok().body(attachs);
  }

  private String genPath() {
    return new SimpleDateFormat("yyyy/MM/dd").format(new Date().getTime());
  }

  @GetMapping("display")
  public ResponseEntity<?> display(Attach attach) throws IOException {
    File file = new File(UPLOAD_PATH + "/" + attach.getPath(), attach.getUuid());
    if(!file.exists()) {
      return ResponseEntity.notFound().build();
    }

    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Type", Files.probeContentType(file.toPath()));
    headers.add("Content-Length", String.valueOf(file.length()));
    //응답 헤더 설정

    Resource resource = new FileSystemResource(file);
    return ResponseEntity.ok().headers(headers).body(resource);
  }

  @GetMapping("download")
  public ResponseEntity<?> download(Attach attach) throws IOException {
    File file = new File(UPLOAD_PATH + "/" + attach.getPath(), attach.getUuid());
    if(!file.exists()) {
      return ResponseEntity.notFound().build();
    }

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
    headers.setContentLength(file.length());
    headers.setContentDisposition(ContentDisposition.attachment().filename(URLEncoder.encode(attach.getOrigin(), "utf-8").replaceAll("]]+", "%20")).build());
    //응답 헤더 설정

    Resource resource = new FileSystemResource(file);
    return ResponseEntity.ok().headers(headers).body(resource);
  }
}



