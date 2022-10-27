function submnotice(){

	if(frm.bn_title.value==null){
		alert("제목을 입력하세요");
	}else if(frm.bn_txt.value==null){
		alert("제목을 입력하세요.");
	}else{
		
	frm.method="post";
	frm.enctype="multipart/form-data";
	frm.action="./writenotice";
	frm.submit();
	}
}



function submnoticemodify(){

	if(frm.bn_title.value==null){
		alert("제목을 입력하세요");
	}else if(frm.bn_txt.value==null){
		alert("제목을 입력하세요.");
	}else{
		
	frm.method="post";
	frm.enctype="multipart/form-data";
	frm.action="./noticeupdate";
	frm.submit();
	}
}

