	var j;
	function subm(){
		frm.action="./adminsiteset";
		frm.method="post";
		frm.enctype="application/x-www-form-urlencoded";
		frm.submit();
	};
	function goback(){
		frm.reset();
		history.back();
	}
	$.ajax({
    url: "callhpset", 
    data: { key: "values" },                        
    method: "GET",                      
    dataType: "text",
    success: function(data){
	var k = data;
	j = k.substring(1,k.length-1).split(', ');	
	var a = ["hpsign_title", "hpsign_email", "hpsign_usepoint", "hpsign_basicpoint", "hpsign_firstlevel", "hpset_companyname",
		"hpset_companyreginum","hpset_agentname", "hpset_agenttel", "hpset_mailsalesnum", "hpset_subsalesnum", "hpset_buispostcode",
		"hpset_buisaddress", "hpset_securitymanagername", "hpset_securitymanageremail", "payset_bankname", "payset_accnum",
		"payset_creditcard", "payset_phone", "payset_bookgiftcard", "payset_payminimumpoint", "payset_paymaximumpoint",
		"payset_cashreceipt", "payset_postcompanyname", "payset_postfee", "payset_posteddayselect"];	

	var al = a.length;
	for(var t = 0 ; t < al;t++){
	var l = document.getElementsByName(a[t]);
	if(l.length>1){l[j[t]].checked=true;}	
	else{l[0].value=j[t];}		
	}

	},error: console.log("error")
	});

