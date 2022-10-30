<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
1,1<input type="button" onclick="addToCart(1,1)" value="1,1" />
2,2<input type="button" onclick="addToCart(2,1)" value="2,1" />
3,2<input type="button" onclick="addToCart(3,1)" value="3,1" />
3,3<input type="button" onclick="addToCart(3,3)" value="3,3" />
4,1<input type="button" onclick="addToCart(4,1)" value="4,1" />
5,2<input type="button" onclick="addToCart(5,1)" value="5,1" />
6,3<input type="button" onclick="addToCart(6,1)" value="6,1" />
<input type="button" onclick="cleanCart()" value="청소" />
</body>
<script>

	cleanCart = () => {localStorage.clear();}
	const addToCart = (code,count) =>{
		const cart = JSON.parse(localStorage.getItem("webpageCartTest")) || [];
		let isEx = false, cnt = false;
		cart.forEach((objects) => {
			if(objects.code === code){
				isEx = true;
				if(objects.count === count){
					cnt = true;										
				}else{
					objects.count = count;
				}
			}
		})
		
		if(isEx){
			if(cnt){				
			alert("중복된 개체, 갯수");
			return;
			}else{
				localStorage.setItem("webpageCartTest",JSON.stringify(cart));
				console.log(cart);
				return;
			}
		}
		value = {
				"code": ${productDTO},
				"count":count
		}
		cart.push(value);
		console.log(cart);
		localStorage.setItem("webpageCartTest",JSON.stringify(cart));
		
	};

</script>
</html>