function makeset(){
	var d = document.getElementById("notice_table");
	var o = document.createElement("ol");
	var l = [];
	for(var i = 0 ; i < 6 ; i ++){
		l[i] = document.createElement("li");
		o.append(l[i]);
	}

	l[0].innerHTML="<input type='checkbox' name='cbox'>";
	l[1].innerText="order";
	l[2].innerText="Title";
	l[3].innerText="master";
	l[4].innerText="date";
	l[5].innerText="<%=nlist.get(v).return_values()%>";
	d.append(o);
}