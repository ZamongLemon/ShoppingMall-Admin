<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="./pagesources/session.jsp"%>
<!doctype html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko"  xmlns:fb="http://ogp.me/ns/fb#"  xmlns:og="http://ogp.me/ns#">
<head prefix="og: http://ogp.me/ns# fb: http://ogp.me/ns/fb# website: http://ogp.me/ns/fb/website#">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0">
<title>인테리어 TRENDY LIVING IDEA</title>
<link rel="image_src" href="./images/snslogo.png"/>

<!-- 구글 웹폰트 -->
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:100,300,400,500,700&amp;subset=korean" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700" rel="stylesheet">

<link rel="stylesheet" type="text/css" href="./css/jquery-ui-1.8.16.custom.css" />
<link rel="stylesheet" type="text/css" href="./css/slick.css"><!-- 반응형 슬라이드 -->
<link rel="stylesheet" type="text/css" href="./css/goods_info_style.css"><!-- 상품디스플레이 CSS -->
<link rel="stylesheet" type="text/css" href="./css/lib.css" />
<link rel="stylesheet" type="text/css" href="./css/common.css?v=20221004" />
<link rel="stylesheet" type="text/css" href="./css/board.css" />
<link rel="stylesheet" type="text/css" href="./css/buttons.css" />
<link rel="stylesheet" type="text/css" href="./css/mobile_pagination.css" />
<link rel="stylesheet" type="text/css" href="./css?k=quickdesign&v=20221004095207" /><!-- Quick Design CSS -->
<link rel="stylesheet" type="text/css" href="./css/broadcast.css" /> 
<link rel="stylesheet" type="text/css" href="./css/user.css" /><!-- ++++++++++++ 스킨 사용자/제작자 CSS ++++++++++++ -->
<link rel="stylesheet" href="./css/swiper.css" />
<!-- /CSS -->
<link rel="stylesheet" type="text/css" href="./css/jquery_swipe.css" />

<!-- 파비콘 -->
</head>

<!-- 자바스크립트 -->
<script src="./js/jquery.min.js"></script>
<script src="./js/jquery-ui.min.js"></script>
<script src="./js/jquery.poshytip.min.js"></script>
<script src="./js/jquery.activity-indicator-1.0.0.min.js"></script>
<script src="./js/jquery.cookie.js"></script>
<script src="./js/jquery.slides.min.js"></script>
<script src="./js/jquery.placeholder.js"></script>
<script src="./js/jquery.validate.js"></script>
<script src="./js/jquery.ezmark.min.js"></script>
<script src="./js/custom-select-box.js"></script>
<script src="./js/custom-mobile-pagination.js"></script>
<script src="./js/slick.min.js"></script>
<script src="./js/jquery.event.swipe.js"></script>
<script src="./js/swiper.js"></script>
<body>
<style>
        #layout_body { background-color:#ffffff; }
        #layer_pay {position:absolute;top:0px;width:100%;height:100%;background-color:#ffffff;text-align:center;z-index:999999;}
        #payprocessing {text-align:center;position:absolute;width:100%;top:150px;z-index:99999999px;}
        #layout_body { max-width:100%; padding-left:0; padding-right:0; }
        #layout_footer { margin-top:0; }
</style>

<div id="wrap">
<div id="layout_wrap" class="layout_wrap">
    <%@ include file="./pagesources/top.html" %>
</div>
<form name = "f" enctype="application/x-www-form-urlencoded">
<div class="resp_wrap display_wrap">
<%@ include file="./pagesources/cart.html" %>
</div>
<input type ="hidden" name="codelists" value="" />
<input type ="hidden" name="counts" value=""/>
</form>		
<div id="layout_footer" class="layout_footer">
     <%@ include file="./pagesources/footer.html" %>
</div>
</div>


</body>

<script>

 	const cart = JSON.parse(localStorage.getItem("webpageCart")) || [];
 	var saletotal = 0;
 	var normaltotal=0;
	var codelist = [];
	var codecnt =[];
 	cart.forEach( (obj) =>{
 		normaltotal += Number(obj.product.pdd_nprice)*Number(obj.count);
		saletotal += Number(obj.product.pdd_sprice)*Number(obj.count);
 		codecnt = [obj.product.pdd_code,obj.count];
 		codelist.push(codecnt);

 	})

 	document.querySelector("#nPrice").innerText = normaltotal.toLocaleString();
	document.querySelector("#sPrice").innerText = saletotal.toLocaleString();
	document.querySelector("#saled").innerText = (normaltotal-saletotal).toLocaleString();
	
	codes = ()=>{
		f.method="post";
		f.action="order";
		
		f.codelists.value=codelist;
		console.log(codelist);
		f.submit();		
	}
	var codeindex = [];
	var idx = 0;
	cart.forEach((obj) =>{
		var ul2 = document.querySelector(".shipping_group_list");
		ul2.innerHTML+="<li class='cart_goods'><div class='cart_goods_detail'><div class='cgd_top'><label><input type='checkbox' id='smallcbox"+idx+"' name='cart_option' /><span id='goodsName"+idx+"'class='goods_name'>예시 소파 상품명</span></label></div><div class='cgd_contents'><div class='block block1'><ul><li class='img_area'><a href=''><img id='img"+idx+"' src='./product/27921_temp_16329010671170list1.jpg' class='goods_thumb'></a></li><li class='option_area'><ul class='cart_option'><li><span class='xtle'>구성</span><div id='explain"+idx+"'>explain<div></li></ul><div class='cart_quantity'><span class='xtle'>수량</span> <div id='count"+idx+"'>1</div> <span class='add_txt'><div id='singleNpriceA"+idx+"'>1,790,000</div></span></div></li></ul></div><ul class='block block2' id='mobile_cart_sale_tr_987'><li class='price_a'><span class='ptitle'>상품금액</span> <div id='singleNprice"+idx+"'>1,790,000&#x20a9;</div></li><li class='price_b'><span class='ptitle'>할인금액</span>(-) <span id='saledPrice"+idx+"'>358,000&#x20a9;</span></li><li class='price_c'><span class='ptitle'>할인적용금액</span><span class='total_p' id='option_suboption_price_sum_987'><span id='sPrice"+idx+"'class='num'>1,432,000</span>&#x20a9;</span></li></ul><ul class='block block3'><li></li><li><button type='button' onclick='buysingle("+idx+")' class='btn_direct_buy btn_resp color2'>바로구매</button></li></ul></div></div></li>";
		
		var cbox = document.getElementById("smallcbox"+idx);
		cbox.value=obj.product.pdd_code;
		var img = document.getElementById("img"+idx);
		img.src = obj.product.pdd_imgurl;
		var name = document.getElementById("goodsName"+idx);
		name.innerText= obj.product.pdd_name;
		var explain = document.getElementById("explain"+idx);
		var count = document.getElementById("count"+idx);
		explain.innerText=obj.product.pdd_explain;
		count.innerText=obj.count+"개";
		var singleNpriceA = document.getElementById("singleNpriceA"+idx);
		var singleNprice = document.getElementById("singleNprice"+idx);
		singleNpriceA.innerText="("+Number(obj.product.pdd_nprice)+"₩)";
		singleNprice.innerText=Number(obj.product.pdd_nprice)*Number(obj.count)+"₩";
		var sPrice = document.getElementById("sPrice"+idx);
		var saledPrice = document.getElementById("salePrice"+idx);
		sPrice.innerText= obj.product.pdd_sprice*Number(obj.count);
		salePrice = (Number(obj.product.pdd_nprice)-Number(obj.product.pdd_sprice))*Number(obj.count);
		var vals =[obj.product.pdd_code,obj.count];
		codeindex.push(vals);
		idx++;		
	})
	buysingle =(idx) =>{
		location.href="./orderSingle?code="+codeindex[idx][0]+"&count="+codeindex[idx][1];				
	
	}
	
</script>
</html>