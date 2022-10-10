function submnotice(){

	if(f.bn_title.value==null){
		alert("제목을 입력하세요");
	}else if(f.bn_txt.value==null){
		alert("제목을 입력하세요.");
	}else{
		
	frm.method="post";
	frm.enctype="multipart/form-data";
	frm.action="./writenotice.do";
	frm.submit();
	}
}
