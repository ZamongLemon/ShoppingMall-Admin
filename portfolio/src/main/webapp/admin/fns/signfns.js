	var checked=false;
	$(function(){
			$("#c_overlap").click(function(){ 
			var $id = $("#a_id").val();
			 var idrule = /^[0-9a-z][0-9a-zA-Z]{2,14}$/;
			
			if($id ==""){
				alert("아이디를 입력해 주세요.");
			}else if($id=="master" || $id=="admin"){
				alert("사용불가능한 아이디입니다.");
			}else if(!idrule.test($id)){
				alert("3~15자의 영문,숫자만 가능합니다.");
			}else{
				$.fn.check($id);
			}
			
			});
			
			$.fn.check = function ($id){
			$.ajax({
				url:"admin_overlap_check.do",
				cache : false,
				type : "POST",
				data :{a_id:$id},
				dataType:"text",
				success:function($data){ 
					console.log($data);
					if($data=="ok"){
						alert("사용가능");
 						$("#a_id").attr("readonly",true);
						checked=true;
					}else{
						alert("사용불가");
					}
				},
				error:function(){
					console.log("fail");
				}
			});
		};

	});
	
	function backtomain(){
		if(confirm("취소하시겠습니까?"))location.href="./index.html";
	}
	
	function idsubmit(){
		var tel = [];
		var tels = document.getElementsByName("a_tel");
		var teln = tels.length
		for(var k = 0 ; k < teln;k++){
			tel[k] = tels[k];
		}
		var ps2 = document.getElementById("pw2");
		 var telrule1 = /^[0-9]{2,3}$/;
		 var mailrule = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,7}$/i;
		if(a_frm.a_id.value==""){
			alert("아이디를 입력하세요.");
		}else if(!checked){
			alert("아이디 중복체크를 하세요.");
		}else if(a_frm.a_pw.value==""){
			alert("비밀번호를 입력하세요.");
		}else if(pw2==""){
			alert("확인 비밀번호를 입력하세요.");
		}else if(pw2.intern() != a_frm.a_pw.value.intern()){
			alert("비밀번호가 일치하지 않습니다.");
		}else if(a_frm.a_name.value==""){
			alert("이름을 입력하세요.");
		}else if(a_frm.a_mail.value==""){
			alert("이메일을 입력하세요.");
		}else if(!mailrule.test(a_frm.a_mail.value)){
			alert("이메일 주소를 확인해주세요.");
		}else if(tel[1]=="" || tel[2] ==""){
			alert("전화번호를 입력해주세요.");
//		}else if(!telrule1.test(tel[1])|| !(/^[0-9]{3,3}$/).test(tel[2])){
//			alert("전화번호를 확인해주세요.");			
		}else if(a_frm.a_part.value=="-1"){
			alert("직책을 선택해주세요.");
		}else if(a_frm.a_grade.value=="-1"){
			alert("등급을 선택해주세요.");
		}else{
			a_frm.method="post";
			a_frm.action="./adminsignup.do";
			a_frm.enctype="application/x-www-form-urlencoded";
			a_frm.submit();
		}
		
	}