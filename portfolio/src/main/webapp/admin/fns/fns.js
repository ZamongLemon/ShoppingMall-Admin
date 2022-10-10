	function addM(){
		location.href="./add_master.html";
	}

	function adminlgnsubmit(){
		if(a_frm.a_id.value==""){
			alert("아이디를 입력해주세요.");
			return;
		}else if(a_frm.a_pw.value==""){
			alert("비밀번호를를 입력해주세요.");
			return;
		}else{
		a_frm.method="post";
		a_frm.enctype="application/x-www-form-urlencoded";
		a_frm.action="./login";
		}
	}
	
