<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/head.jsp" %>
</head>
<body>
<%@ include file="../common/header.jsp" %>
<%@ include file="../common/nav.jsp" %>
	<div class="container p-0">

        <main>
            <form method="post" id="writeForm" action="write">
            <div class="samll border-bottom border-3 border-primary p-0 pb-2">
            	<a href="#" class="samll">
            		<span class="text-primary">
            			<c:forEach items="${cate}" var="c">
            				<c:if test="${c.cno == cri.cno}">
            				${c.cname}
            				</c:if>
            			</c:forEach>
            		</span> 
            		카테고리
            	</a>
            </div>
            <div class="small p-0 py-2 ">
                <input placeholder="title" class="form-control" name="title" id="title">
            </div>
            <div class="p-0 py-3 ps-1 small border-bottom border-1 border-muted">
                <textarea name="content" id="editor1" class="form-control resize-none"></textarea>
            </div>
			
			
			
			<div class="d-grid my-2 attach-area">
				<div class="small my-1"><i class="fa-solid fa-paperclip"></i> 첨부파일</div>
				<label class="btn btn-info">파일 첨부<input type="file" multiple class="d-none" id="f1"></label>
				<ul class="list-group my-3 attach-list">
				</ul>
				<div class="row justify-content-around w-75 mx-auto attach-thumb">
				</div>
	
			</div>
	
            <div class="my-2">
                <button class="btn btn-secondary btn-sm"><i class="fa-solid fa-list-ul"></i> 목록</button>
                <div class="float-end">
                    <button class="btn btn-outline-primary btn-sm"><i class="fa-solid fa-share-nodes"></i> 글쓰기</button>
                </div>
            </div>
            <input type="hidden" name="id" value="${member.id}" />
            <input type="hidden" name="cno" value="${cri.cno}" />
            <input type="hidden" name="page" value="1">
            <input type="hidden" name="amount" value="${cri.amount}">
            <input type="hidden" name="encodedStr" value="">
            <c:if test= "${not empty param.bno}">
            <input type="hidden" name="bno" value="${param.bno}">
            </c:if>
            </form>
        </main>
    </div>
	<script src="https://code.jquery.com/ui/1.14.1/jquery-ui.js"></script>
    <script>
 
        $(function() {
            CKEDITOR.replace('editor1', {
                height : 400
            });
        });
    </script>
    	<script>
	$(function() {
   	 $( ".attach-list" ).sortable();
   	 
		//return true / false
		function validateFiles(files){
			const MAX_COUNT = 5;
			const MAX_FILE_SIZE = 10 * 1024 * 1024;	//10MB
			const MAX_TOTAL_SIZE = 50 * 1024 * 1024;	//50MB
			const BLOCK_EXT = ['exe','sh', 'jsp', 'java','class', 'html', 'js'];

			if(files.length > MAX_COUNT){
				alert('파일은 최대 5개만 업로드 가능');
				return false;
			}
			let totalSize = 0;	//파일 총량
			const isValid = files.every(f =>  {
				const ext = f.name.split(".").pop().toLowerCase(); //확장자 추출(확장자 삭제)
				totalSize += f.size;
				console.log(totalSize, f.size, ext)
				return !BLOCK_EXT.includes(ext) && f.size <= MAX_FILE_SIZE; //위에 선언했던 확장자 미포함
			}) && totalSize <= MAX_TOTAL_SIZE
			if(!isValid){
				alert('다음 파일 확장자는 업로드가 불가능하다[exe, sh, jsp, java, class, html, js] \n 또한 각 파일은 10MB이하 전체 합계는 50MB 이하여야한다 ')
			}
			return isValid;
		}
		
		
		$("#f1").change(function() { //값 변경시
		//$("#uploadForm").submit(function() {
			event.preventDefault();
			const formData = new FormData();
			
			const files = this.files;
			console.log(formData, files);
			
			for(let i = 0; i < files.length; i++){
				formData.append("f1", files[i]);
			}
			
			const valid =  validateFiles([...files])
			//배열로 만든 files 전달
			if(!valid){
				return;
			}
	
			
			
			$.ajax({
				url : '${cp}/upload',
				method : 'POST',
				data : formData,
				processData : false,	//data 를 queryString으로 쓰지 않음
				contentType : false, 	//원래는 multipart/form-data; 이후에 나오게 될 브라우저 정보 포함. 즉, 기본 브라우저 설정 따르는 옵션
				success : function(data){
					console.log(data);
					//확인용
					let str = "";
					let thumbStr = "";
					for(let a of data){
					//$(".container").append("<p>" + data[a].origin + "x</p>");
					str += `<li class="list-group-item d-flex align-items-center justify-content-between"
						data-uuid="\${a.uuid}"
						data-origin="\${a.origin}"
						data-image="\${a.image}"
						data-path="\${a.path}"
						data-odr="\${a.odr}"	
						data-size="\${a.size}"
					>

						<a href="${cp}/download?uuid=\${a.uuid}&origin=\${a.origin}&path=\${a.path}">\${a.origin}</a>
						<i class="fa-regular fa-circle-xmark float-end text-danger"></i>
						</li>`;
						if(a.image){
							thumbStr +=	`<div class="my-2 col-12 col-sm-4 col-lg-2"
								data-uuid="\${a.uuid}"
							>
								<div class="my-2 bg-primary" 
								style="height: 150px; background-size : cover; background-image:url('${cp}/display?uuid=t_\${a.uuid}&path=\${a.path}')">
									<i class="fa-regular fa-circle-xmark float-end text-danger m-2"></i>
								</div>
								</div>`;
						}
					}
					console.log(thumbStr);
					$(".attach-list").html(str);
					$(".attach-thumb").html(thumbStr);
					//이미지인 경우와 아닌 경우
					
				}
			})
		})
		$(".attach-area").on("click", "i", function() {
			const uuid = $(this).closest("[data-uuid]").data("uuid");
			console.log(uuid);
			$("[data-uuid='" + uuid + "']").remove();
		});
		

		$("#writeForm").submit(function(e) {
      e.preventDefault();
      $(this).append($("<input>").attr({type: "hidden", name:"attachs[0].uuid", value:"test-uuid.png"}));
      // const formData = new FormData(this);
      // formData.append("attachs[0].uuid", "test-uuid.png") //postman 에서의 key, value
			const data = [];
			$(".attach-list li").each(function() {
				data.push({...this.dataset});
			});
      console.log(data)

      for(let d in data) {  //object 타입 객체 반복
        for(let i in data[d]){  //그 안의 속성값
          $(this).append($("<input>").attr({type: "hidden", name:`attachs[\${d}].\${i}`, value:data[d][i]}));
          console.log(i, data[d][i]);
          debugger;
        }
      }
			// console.log(JSON.stringify(data));
			// data.forEach((item, idx) => item.odr = idx);
			// //item의 odr 을 idx로 덮어쓰기
			// $("[name='encodedStr']").val(JSON.stringify(data));
			$(this).off().submit(); //중복  호출 방지
		})
	})
	</script>
<%@ include file="../common/footer.jsp" %>
</body>
</html>