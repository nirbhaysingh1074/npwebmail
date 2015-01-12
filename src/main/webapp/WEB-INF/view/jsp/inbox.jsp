
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>

<script type="text/javascript">
	function getWebmailInbox( fdrname) {
		//alert(fdrname);
		noneMail();
		var pview=document.getElementById("hid_panel_view").value;
		//alert(pview)
		document.getElementById("hid_dt_sorting").value="up";
		document.getElementById("hid_pagi_pcnt").value='1';
		document.getElementById("dt_sorting").innerHTML="<img src='images/down_date.png'>";
	var pfldr=document.getElementById('hid_active_fldr').value
	document.getElementById('hid_active_fldr').value=fdrname;
	document.getElementById(pfldr).className = "";
	document.getElementById(fdrname).className = "active_mailbox";
var start='0';
var end='15';
		document.getElementById('action_gif').style.display= 'block';
		$.ajax({
					type : "GET",
					url : "${pageContext.request.contextPath}/getMailInbox",
					data : {
						'folder' : fdrname,
						'start' : start,
						'end' : end,
						'pview' : pview
					},
					contentType : "application/json",
					success : function(data) {
						// $("#fileSystem").html(data);
						// alert(data);
						$("#inb_cnt_div").html(data);
						document.getElementById('action_gif').style.display= 'none';
						getAllMailCount(fdrname);
					}
				});

	}
	
	
	function getWebmailInboxDesc( ) {
		
		var fdrname=document.getElementById('hid_active_fldr').value;
		//alert("down"+fdrname);
		document.getElementById("hid_pagi_pcnt").value='1';
		var start='0';
		var end='15';
		document.getElementById('action_gif').style.display= 'block';
		$.ajax({
					type : "GET",
					url : "${pageContext.request.contextPath}/getMailInboxDesc",
					data : {
						'folder' : fdrname,
						'start' : start,
						'end' : end
					},
					contentType : "application/json",
					success : function(data) {
						// $("#fileSystem").html(data);
						// alert(data);
						$("#inb_cnt_div").html(data);
						document.getElementById('action_gif').style.display= 'none';
						getAllMailCount(fdrname);
					}
				});

	}
	
	
	function getAllMailCount(folderPath){
		//alert("meeeeeeeeeeeeeeeeeeeSub="+folderPath);
		 $.ajax({
	         type: "GET",
	         url: "${pageContext.request.contextPath}/getAllMailCountInfolderDiv",
	         data: {'path':folderPath},
	         contentType: "application/json",
	         success: function (data) {
	            $("#pagination_div").html(data);
	           // alert(data);
	         }
	     });
		
	}
	
	function getWebmailInboxRefresh( ) {
		//	alert(fdrname);
		var fdrname=document.getElementById('hid_active_fldr').value;
		//alert(fdrname)
		var pview=document.getElementById("hid_panel_view").value;
		//alert(pview);
	var start='0';
	var end='15';
	document.getElementById('div_progress').style.display= 'block';
			document.getElementById('action_gif').style.display= 'block';
			$.ajax({
						type : "GET",
						url : "${pageContext.request.contextPath}/getMailInbox",
						data : {
							'folder' : fdrname,
							'start' : start,
							'end' : end,
							'pview' : pview
						},
						contentType : "application/json",
						success : function(data) {
							// $("#fileSystem").html(data);
							//alert(data);
							$("#inb_cnt_div").html(data);
							//document.getElementById(fdrname).className = "active_mailbox";
							document.getElementById('action_gif').style.display= 'none';
							document.getElementById('div_progress').style.display= 'none';
							
						}
					});

		}
	

	function flagAction(id) {
		//alert('hii');
		document.getElementById('action_gif').style.display= 'block';

		//alert('hello');
		var flg_id = "div_flag_" + id;
		var fdrname = 'inbox';
		var type = 'set';
		var y = document.getElementById(flg_id).className;
		if (y == 'small_image_flag') 
		{
			type = 'remove';
		}
		
		$.ajax({
			type : "GET",
			url : "${pageContext.request.contextPath}/webmailFlagAtion",
			data : {
				'folder' : fdrname,
				'flagtp' : type,
				'uid' : id
			},
			contentType : "application/json",			
			success : function(data) {
				if (y == 'small_image_flag') {
					document.getElementById(flg_id).className = 'small_image';
				} else {
					document.getElementById(flg_id).className = 'small_image_flag';
				}
				document.getElementById('action_gif').style.display= 'none';

				// $("#fileSystem").html(data);
				//alert("st-end"+(start-end));
				// $("#inb_cnt_div").html(data);
			}
		});
		
	}

	function moveTrash(id) {
		//alert(id);
		document.getElementById('action_gif').style.display= 'block';
		
		var fdrname = document.getElementById('hid_active_fldr').value;
		$.ajax({
					type : "GET",
					url : "${pageContext.request.contextPath}/webmailMoveToTrash",
					data : {
						'folder' : fdrname,
						'uid' : id
					},
					contentType : "application/json",
					success : function(data) {
						var elem = document.getElementById("div_" + id);
						elem.parentNode.removeChild(elem);
						document.getElementById('action_gif').style.display = "none";
						// $("#fileSystem").html(data);
						// alert(data);
						// $("#inb_cnt_div").html(data);
					}
				});
	}
</script>
<script type="text/javascript">
function dtSorting()
{
	alert('hi');
	var dt_sort_val=document.getElementById("hid_dt_sorting").value;
	document.getElementById("hid_pagi_pcnt").value='1';
	//var fdrnm=document.getElementById('hid_active_fldr').value;
	//alert(fdrnm);
	if(dt_sort_val=="up")
		{
		document.getElementById("hid_dt_sorting").value="down";
		document.getElementById("dt_sorting").innerHTML="<img src='images/up_date.png'>";
		getWebmailInboxDesc( );
		}
	else
		{
		document.getElementById("hid_dt_sorting").value="up";
		document.getElementById("dt_sorting").innerHTML="<img src='images/down_date.png'>";
		getWebmailInboxRefresh( )
		}
	
}
</script>

<script type="text/javascript">
function toggle(source) {
	
	var cnt=0;
	
	var pview=document.getElementById("hid_panel_view").value;
	var left_css="row_content ";
	
		if(pview=="leftview")
			{
			left_css=left_css+"left_view_mess ";
			}
		else
			{
			left_css=left_css+"pading_main ";
			}
	
  checkboxes1 = document.getElementsByName('unseen-stared');
  for(var i=0, n=checkboxes1.length;i<n;i++) {
    checkboxes1[i].checked = source.checked;
    if(source.checked==true)
    	{
    	document.getElementById("div_"+(checkboxes1[i].value)).className=left_css+"selected_row";
    	}
    else
    	{
    	document.getElementById("div_"+(checkboxes1[i].value)).className=left_css;
    	}
    cnt++;
  }

  checkboxes2 = document.getElementsByName('seen-unstared');
  for(var i=0, n=checkboxes2.length;i<n;i++) {
    checkboxes2[i].checked = source.checked;
    if(source.checked==true)
	{
	document.getElementById("div_"+(checkboxes2[i].value)).className=left_css+"selected_row";
	}
else
	{
	document.getElementById("div_"+(checkboxes2[i].value)).className=left_css;
	}
    cnt++;
  }
  
  checkboxes3 = document.getElementsByName('unseen-unstared');
  for(var i=0, n=checkboxes3.length;i<n;i++) {
    checkboxes3[i].checked = source.checked;
    if(source.checked==true)
	{
	document.getElementById("div_"+(checkboxes3[i].value)).className=left_css+"selected_row";
	}
else
	{
	document.getElementById("div_"+(checkboxes3[i].value)).className=left_css;
	}
    cnt++;
  }
  
  checkboxes4 = document.getElementsByName('seen-stared');
  for(var i=0, n=checkboxes4.length;i<n;i++) {
    checkboxes4[i].checked = source.checked;
    if(source.checked==true)
	{
	document.getElementById("div_"+(checkboxes4[i].value)).className=left_css+"selected_row";
	}
else
	{
	document.getElementById("div_"+(checkboxes4[i].value)).className=left_css;
	}
    cnt++;
	}
  
  if(source.checked==true)
	{
	  document.getElementById('div_search_tool').style.display = "block";
	  if(cnt>1)
	 	 {
	 	 document.getElementById("div_hid_opt1").className="hidden_option disable";
	 	 document.getElementById("div_hid_opt2").className="hidden_option disable";
	 	 }
	}
  else
	  {
	  document.getElementById('div_search_tool').style.display = "none";
	  }
  return cnt;
}

function allMail()
{
//alert('all');
 document.getElementById("allmail").checked = true;
 var cnt=parseInt(toggle(document.getElementById("allmail")) );
 
if(cnt>0)
	{
 document.getElementById('div_search_tool').style.display = "block";
 if(cnt>1)
	 {
	 document.getElementById("div_hid_opt1").className="hidden_option disable";
	 document.getElementById("div_hid_opt2").className="hidden_option disable";
	 }
 selMailCountInPanel();
	}
}

function noneMail()
{
//alert('none');
 document.getElementById("allmail").checked = false;
 var cnt=toggle(document.getElementById("allmail")) ;
 document.getElementById('div_search_tool').style.display = "none";
 selMailCountInPanel();
}

function seenMail()
{
 noneMail()
 var pview=document.getElementById("hid_panel_view").value;
	var left_css="row_content ";
	
		if(pview=="leftview")
			{
			left_css=left_css+"left_view_mess ";
			}
		else
			{
			left_css=left_css+"pading_main ";
			}
 var cnt=0;
 //var source=document.getElementById("allmail");
 checkboxes2 = document.getElementsByName('seen-unstared');
  for(var i=0, n=checkboxes2.length;i<n;i++) 
  {
    checkboxes2[i].checked = true;
   	document.getElementById("div_"+(checkboxes2[i].value)).className=left_css+"selected_row";
	cnt++;
  }
  
    checkboxes4 = document.getElementsByName('seen-stared');
  for(var i=0, n=checkboxes4.length;i<n;i++)
  {
    checkboxes4[i].checked = true;
    document.getElementById("div_"+(checkboxes4[i].value)).className=left_css+"selected_row";
    cnt++;
	}
 // alert(cnt);
  if(cnt>0)
	  {
	  document.getElementById("allmail").checked = true;
 document.getElementById('div_search_tool').style.display = "block";
 if(cnt>1)
	 {
	 document.getElementById("div_hid_opt1").className="hidden_option disable";
	 document.getElementById("div_hid_opt2").className="hidden_option disable";
	 }
 selMailCountInPanel();
	  }
}

function unseenMail()
{
noneMail()
var cnt=0;
var pview=document.getElementById("hid_panel_view").value;
var left_css="row_content ";

	if(pview=="leftview")
		{
		left_css=left_css+"left_view_mess ";
		}
	else
		{
		left_css=left_css+"pading_main ";
		}
checkboxes1 = document.getElementsByName('unseen-stared');
  for(var i=0, n=checkboxes1.length;i<n;i++) 
  {
    checkboxes1[i].checked = true;
    document.getElementById("div_"+(checkboxes1[i].value)).className=left_css+"selected_row";
    cnt++;
  }

  
  checkboxes3 = document.getElementsByName('unseen-unstared');
  for(var i=0, n=checkboxes3.length;i<n;i++) 
  {
    checkboxes3[i].checked = true;
    document.getElementById("div_"+(checkboxes3[i].value)).className=left_css+"selected_row";
    cnt++;
  }
  //alert(cnt);
   if(cnt>0)
	  {
	  document.getElementById("allmail").checked = true;
  document.getElementById('div_search_tool').style.display = "block";
 if(cnt>1)
	 {
	 document.getElementById("div_hid_opt1").className="hidden_option disable";
	 document.getElementById("div_hid_opt2").className="hidden_option disable";
	 }
 selMailCountInPanel();
	  }
}


function staredMail()
{
noneMail();
var cnt=0;
var pview=document.getElementById("hid_panel_view").value;
var left_css="row_content ";

	if(pview=="leftview")
		{
		left_css=left_css+"left_view_mess ";
		}
	else
		{
		left_css=left_css+"pading_main ";
		}
checkboxes1 = document.getElementsByName('unseen-stared');
  for(var i=0, n=checkboxes1.length;i<n;i++) 
  {
    checkboxes1[i].checked = true;
    document.getElementById("div_"+(checkboxes1[i].value)).className=left_css+"elected_row";
    cnt++;
  }

 
  checkboxes4 = document.getElementsByName('seen-stared');
  for(var i=0, n=checkboxes4.length;i<n;i++) 
  {
    checkboxes4[i].checked = true;
    document.getElementById("div_"+(checkboxes4[i].value)).className=left_css+"selected_row";
    cnt++;
	}
//alert(cnt);
 if(cnt>0)
	  {
	  document.getElementById("allmail").checked = true;
document.getElementById('div_search_tool').style.display = "block";
 if(cnt>1)
	 {
	 document.getElementById("div_hid_opt1").className="hidden_option disable";
	 document.getElementById("div_hid_opt2").className="hidden_option disable";
	 }
 selMailCountInPanel();
	  }
}


function unstaredMail()
{
noneMail();
var cnt=0;
var pview=document.getElementById("hid_panel_view").value;
var left_css="row_content ";

	if(pview=="leftview")
		{
		left_css=left_css+"left_view_mess ";
		}
	else
		{
		left_css=left_css+"pading_main ";
		}

 checkboxes2 = document.getElementsByName('seen-unstared');
  for(var i=0, n=checkboxes2.length;i<n;i++) 
  {
    checkboxes2[i].checked = true;
    document.getElementById("div_"+(checkboxes2[i].value)).className=left_css+"elected_row";
    cnt++;
  }
  
  checkboxes3 = document.getElementsByName('unseen-unstared');
  for(var i=0, n=checkboxes3.length;i<n;i++) 
  {
    checkboxes3[i].checked = true;
    document.getElementById("div_"+(checkboxes3[i].value)).className=left_css+"selected_row";
    cnt++;
  }
 // alert(cnt);
 
  if(cnt>0)
	  {
	  document.getElementById("allmail").checked = true;
 document.getElementById('div_search_tool').style.display = "block";
 if(cnt>1)
	 {
	 document.getElementById("div_hid_opt1").className="hidden_option disable";
	 document.getElementById("div_hid_opt2").className="hidden_option disable";
	 }
	 
 selMailCountInPanel();
	  }
}



function getSelectdMailUid()
{
var checkedValue = ""; 
var inputElements1 = document.getElementsByName('unseen-stared');
for(var i=0; inputElements1[i]; ++i){
      if(inputElements1[i].checked){
			if(checkedValue == "")
			{
			checkedValue = inputElements1[i].value;
			}
			else
			{
			checkedValue =checkedValue+"-"+ inputElements1[i].value;
			}
          
      }
}

var inputElements2 = document.getElementsByName('unseen-unstared');
for(var i=0; inputElements2[i]; ++i){
      if(inputElements2[i].checked){
			if(checkedValue == "")
			{
			checkedValue = inputElements2[i].value;
			}
			else
			{
			checkedValue =checkedValue+"-"+ inputElements2[i].value;
			}
          
      }
}

var inputElements3 = document.getElementsByName('seen-unstared');
for(var i=0; inputElements3[i]; ++i){
      if(inputElements3[i].checked){
			if(checkedValue == "")
			{
			checkedValue = inputElements3[i].value;
			}
			else
			{
			checkedValue =checkedValue+"-"+ inputElements3[i].value;
			}
          
      }
}


var inputElements4 = document.getElementsByName('seen-stared');
for(var i=0; inputElements4[i]; ++i){
      if(inputElements4[i].checked){
			if(checkedValue == "")
			{
			checkedValue = inputElements4[i].value;
			}
			else
			{
			checkedValue =checkedValue+"-"+ inputElements4[i].value;
			}
          
      }
}

//alert("hi"+checkedValue);
return checkedValue;
}



function setSetectedMailUnRead() {
	var smail= getSelectdMailUid();
	
	alert(smail);
	
	document.getElementById('action_gif').style.display= 'block';

	//alert('hello');
	
	var fdrname = document.getElementById('hid_active_fldr').value;
	
	//var y = document.getElementById(flg_id).className;
	
	$.ajax({
		type : "GET",
		url : "${pageContext.request.contextPath}/webmailUnReadAtion",
		data : {
			'folder' : fdrname,
			'uid' : smail
		},
		contentType : "application/json",			
		success : function(data) {
			
			var arr=smail.split("-");
			for(i=0;i<arr.length;i++)
				{
				var flg_id = "div_unread_" + arr[i];
				document.getElementById(flg_id).className = 'row_left unread_message';
				}
				document.getElementById('action_gif').style.display= 'none';

		}
	});
	
}


function setSetectedMailRead() {
	var smail= getSelectdMailUid();
	
	alert(smail);
	
	document.getElementById('action_gif').style.display= 'block';

	//alert('hello');
	
	var fdrname = document.getElementById('hid_active_fldr').value;
	
	//var y = document.getElementById(flg_id).className;
	
	$.ajax({
		type : "GET",
		url : "${pageContext.request.contextPath}/webmailReadAtion",
		data : {
			'folder' : fdrname,
			'uid' : smail
		},
		contentType : "application/json",			
		success : function(data) {
			
			var arr=smail.split("-");
			for(i=0;i<arr.length;i++)
				{
				var flg_id = "div_unread_" + arr[i];
				document.getElementById(flg_id).className = 'row_left';
				}
				document.getElementById('action_gif').style.display= 'none';

		}
	});
	
}


function setSetectedMailFlag() {
	var smail= getSelectdMailUid();
	document.getElementById('action_gif').style.display= 'block';

	var fdrname = document.getElementById('hid_active_fldr').value;
	var type = 'set';
	
	$.ajax({
		type : "GET",
		url : "${pageContext.request.contextPath}/webmailFlagAtion",
		data : {
			'folder' : fdrname,
			'flagtp' : type,
			'uid' : smail
		},
		contentType : "application/json",			
		success : function(data) {
			
			var arr=smail.split("-");
			for(i=0;i<arr.length;i++)
				{
				var flg_id = "div_flag_" + arr[i];
				document.getElementById(flg_id).className = 'small_image_flag';
				}
				document.getElementById('action_gif').style.display= 'none';

		}
	});
	
}
</script>

<script type="text/javascript">
function pagiNextPage() {
	var pcnt=parseInt(document.getElementById("hid_pagi_pcnt").value);
	var allml=parseInt(document.getElementById("hid_pagi_allmail").value);
	 var lmt=pcnt+1;
	 var sml=pcnt*15;
	var all=pcnt*15;
	if(all<=allml)
		{
		alert(all);
		var s=(pcnt*15)+1;
		var e=(pcnt*15)+15;
		if(e>allml)
			{
			e=allml;
			}
		document.getElementById("pagination_div").innerHTML=''+s+" - "+e+" of "+allml;
		document.getElementById("hid_pagi_pcnt").value=lmt;
		var dtsort=document.getElementById("hid_dt_sorting").value;
		var path="getMailInbox";
		if(dtsort=="down")
			{
			path="getMailInboxDesc";
			}
	var pfldr=document.getElementById('hid_active_fldr').value;
	//document.getElementById('hid_active_fldr').value=fdrname;
	//document.getElementById(pfldr).className = "";
	//document.getElementById(fdrname).className = "active_mailbox";
	var start=''+sml;
	var end='15';
	var pview=document.getElementById("hid_panel_view").value;
	document.getElementById('action_gif').style.display= 'block';
		$.ajax({
					type : "GET",
					url : "${pageContext.request.contextPath}/"+path,
					data : {
						'folder' : pfldr,
						'start' : start,
						'end' : end,
						'pview' : pview
					},
					contentType : "application/json",
					success : function(data) {
						// $("#fileSystem").html(data);
						// alert(data);
						$("#inb_cnt_div").html(data);
						document.getElementById('action_gif').style.display= 'none';
						getAllMailCount(fdrname);
					}
				});
		
		
		}
	
	
	
}


function pagiPrevPage() {
	var pcnt=parseInt(document.getElementById("hid_pagi_pcnt").value);
	var allml=parseInt(document.getElementById("hid_pagi_allmail").value);
	 var lmt=pcnt-1;
	 var sml=(lmt*15)-15;
	var all=lmt*15;
	if(all>0)
		{
		
		var s=(lmt*15)-15+1;
		var e=(lmt*15);
		if(e>allml)
			{
			e=allml;
			}
		document.getElementById("pagination_div").innerHTML=''+s+" - "+e+" of "+allml;
		
		document.getElementById("hid_pagi_pcnt").value=lmt;
		var dtsort=document.getElementById("hid_dt_sorting").value;
		var path="getMailInbox";
		if(dtsort=="down")
			{
			path="getMailInboxDesc";
			}
		var pfldr=document.getElementById('hid_active_fldr').value
		var start=''+sml;
		var end='15';
		document.getElementById('action_gif').style.display= 'block';
		var pview=document.getElementById("hid_panel_view").value;
		$.ajax({
					type : "GET",
					url : "${pageContext.request.contextPath}/"+path,
					data : {
						'folder' : pfldr,
						'start' : start,
						'end' : end,
						'pview' : pview
					},
					contentType : "application/json",
					success : function(data) {
						$("#inb_cnt_div").html(data);
						document.getElementById('action_gif').style.display= 'none';
						getAllMailCount(fdrname);
					}
				});
		
		
		}
	
}
</script>

<script type="text/javascript">
function getMailCnt(uid) 
{
	
	alert(uid);
	noneMail()
	var pview=document.getElementById("hid_panel_view").value;
	//alert(pview);
	document.getElementById('action_gif').style.display= 'block';
	document.getElementById('div_search_tool').style.display = "block";
	 document.getElementById("div_hid_opt1").className="hidden_option";
	 document.getElementById("div_hid_opt2").className="hidden_option";
	 var fdrname = document.getElementById('hid_active_fldr').value;
	 document.getElementById("chk_id_"+uid).checked = true;
	/* var str= document.getElementById("unread_inbox").innerHTML;
		var res = str.substr(1, (str.length-2));
		var num=parseInt(res);
		num--;
		if(num!=0)
			{
			res="("+ num+")";
			}
		else
			{
			res="";
			}
		*/
	if(pview=="leftview")
		{
		selMailCountInPanel();
		document.getElementById("div_"+uid).className="row_content selected_row left_view_mess";
		
		$.ajax({
			type : "GET",
			url : "${pageContext.request.contextPath}/getMailContentByUid",
			data : {
				'folder' : fdrname,
				'uid' : uid
			},
			contentType : "application/json",
			success : function(data) {
				//alert(data)
				document.getElementById('hid_no_cnt').style.display= 'none';
				document.getElementById('div_left_cnt').style.display= 'block';
				document.getElementById('action_gif').style.display= 'none';
				$("#div_left_cnt").html(data);
				//document.getElementById("unread_inbox").innerHTML=res;
				document.getElementById("div_unread_"+uid).className ="row_left left_view_con";
				if(fdrname=="inbox")
					{
					inboxUnread()
					}
			}
		});
		}
	else
		{
		var ur_scc="row_left";
		document.getElementById("div_"+uid).className="row_content selected_row pading_main";
		
		$.ajax({
			type : "GET",
			url : "${pageContext.request.contextPath}/getMailContentFullPageByUid",
			data : {
				'folder' : fdrname,
				'uid' : uid
			},
			contentType : "application/json",
			success : function(data) {
				//alert(data)
				document.getElementById('div_half_pg_disp').style.display= 'none';
				document.getElementById('div_full_pg_disp').style.display= 'block';
				document.getElementById('action_gif').style.display= 'none';
				$("#div_full_pg_disp").html(data);
				//document.getElementById("unread_inbox").innerHTML=res;
				$('.mail_content').css('height',left_scollx-109);
				document.getElementById("div_unread_"+uid).className ="row_left";
				if(fdrname=="inbox")
				{
				inboxUnread()
				}
			}
		});
		
		
		}
}

function inboxUnread() {
	
	$.ajax({
		type : "GET",
		url : "${pageContext.request.contextPath}/getUnreadMailCountInfolderDiv",
		data : {
			'fdr' : 'inbox'
			
		},
		contentType : "application/json",
		success : function(data) {
			
			$("#unread_inbox").html(data);
			
			
		}
	});
	
}


function panelView(val) {
	document.getElementById("hid_panel_view").value=val;
}



function selMailCountInPanel()
{
	
	var msg="No";
	var cnt=0;
	var inputElements1 = document.getElementsByName('unseen-stared');
	for(var i=0; inputElements1[i]; ++i){
	      if(inputElements1[i].checked){
				cnt++;
	          
	      }
	}

	var inputElements2 = document.getElementsByName('unseen-unstared');
	for(var i=0; inputElements2[i]; ++i){
	      if(inputElements2[i].checked){
	    	  cnt++;
	          
	      }
	}

	var inputElements3 = document.getElementsByName('seen-unstared');
	for(var i=0; inputElements3[i]; ++i){
	      if(inputElements3[i].checked){
	    	  cnt++;
	          
	      }
	}


	var inputElements4 = document.getElementsByName('seen-stared');
	for(var i=0; inputElements4[i]; ++i){
	      if(inputElements4[i].checked){
	    	  cnt++;
	          
	      }
	}

	if(cnt>0)
		{
		msg=""+cnt;
		}
	//alert(cnt)
	document.getElementById("span_con").innerHTML=msg;
	document.getElementById("div_left_cnt").style.display= 'none';
	document.getElementById("hid_no_cnt").style.display= 'block';
}
</script>

<script type="text/javascript">
function downloadAttach(uid,name) {
	alert("uid="+uid);
	alert("name="+name);
	var fdrname = document.getElementById('hid_active_fldr').value;
	$.ajax({
		type : "GET",
		url : "${pageContext.request.contextPath}/downloadMailAttachByName",
		data : {
			'folder' : fdrname,
			'uid' : uid,
			'name' : name
		},
		contentType : "application/json",
		success : function(data) {
			alert(data)
			//document.getElementById('hid_no_cnt').style.display= 'none';
			//$("#div_left_cnt").html(data);
			
		}
	});
}

function backToMailList() {
	document.getElementById('div_half_pg_disp').style.display= 'block';
	document.getElementById('div_full_pg_disp').style.display= 'none';
}
</script>

<script type="text/javascript">
function mailCompose() {
	//alert('hi');
	var requestPage="${pageContext.request.contextPath}/composeMailView";
	jQuery.get(requestPage,
            /*                  {
                    'path' : folderPath
            }, */
                    function( data ) {
            	document.getElementById('div_for_inbox').style.display= 'none';
            	document.getElementById('div_for_compose').style.display= 'block';
             $( "#div_for_compose" ).html( data );
             
            });
}

function backFromCompose() {
	
	document.getElementById('div_for_inbox').style.display=  'block'; 
	document.getElementById('div_for_compose').style.display='none';
}
</script>
</head>
<body>

<%
HttpSession hsbd=request.getSession();
String mcnt=request.getAttribute("MailCount").toString();
%>

	<!------/// RIGHT PANNEL ONLY FOR TOOL-------->

	<div class="right-pane">
	<div id="div_for_inbox">
	<div id="div_half_pg_disp" style="display: block">
		<div class="right_top_pannel">

			<!-------// RIGHT TOP TOOL END HERE -------->
			<div class="for_tool">
				<ul>
					<li>
						<div class="tool_inner_box">
							<ul id="menu">
								<li><input class="check_box" type="checkbox" name="all" id="allmail" onclick="toggle(this)"  />
								<a	href="#" class="sub_menu_link"><img
										src="images/open_sub_menu.png" style="margin-left: -5px;"></a>
									<ul>
										<li><a style="cursor: pointer;" onclick="allMail()">All</a></li>
										<li><a style="cursor: pointer;" onclick="noneMail()">None</a></li>
										<li><a style="cursor: pointer;" onclick="seenMail()">Read</a></li>
										<li><a style="cursor: pointer;" onclick="unseenMail()">Unread</a></li>
										<li><a style="cursor: pointer;" onclick="staredMail()">Starred</a></li>
										<li><a style="cursor: pointer;" onclick="unstaredMail()">Unstarred</a></li>
									</ul></li>
							  
							</ul>
						</div>
					</li>
					<li>
						<div id="div_search_tool" class="large_tool search_form_tool">
							<ul>
								<li id="div_hid_opt1" class="hidden_option"><a href="#"><img
										src="images/back_one.png"></a></li>
								<div class="right_border"></div>
								<li id="div_hid_opt2" class="hidden_option"><a href="#"><img
										src="images/back_doble.png"></a></li>
								<div class="right_border"></div>
								<ul id="menu" class="next_mail">
									<li><a href="#" style="padding: 0px;"><img
											src="images/next.png"></a> <a href="#"
										class="sub_menu_link" style="padding: 0px;"><img
											src="images/open_sub_menu.png"></a>
										<ul>
											<li><a href="#">Forward Inline</a></li>
											<li><a href="#">Forward As Attachment</a></li>
										</ul></li>
								</ul>
								<div class="right_border"></div>
								<li><a href="#"><img src="images/tool_delete.png"></a></li>
								<div class="right_border"></div>
								<li><a href="#"><img src="images/restriction.png"></a></li>
								<div class="right_border"></div>
								<ul id="menu" class="next_mail more_arrow">
									<li class="more">More</li>
									<li><a href="#" class="sub_menu_link"><img
											src="images/open_sub_menu.png"></a>
										<ul style="margin-left: -27px !important;">
											<li><a style="cursor: pointer;" onclick="setSetectedMailUnRead()">Mark as unread</a></li>
											<li><a style="cursor: pointer;" onclick="setSetectedMailFlag()">Add Star</a></li>
											<li><a href="#">Add To Task</a></li>
											<li><a href="#">Create Filter </a></li>
											<li><a href="#">Add To Task </a></li>
											<li><a href="#">Create Event </a></li>
										</ul></li>
								</ul>
							</ul>
						</div>
						<div class="calender_tool">
							<ul id="menu">
								<li><img src="images/tool_calender.png"
									class="four_margin calender_images"></li>
								<li style="margin-left: 12px;"><a href="#"
									class="sub_menu_link"><img src="images/open_sub_menu.png"></a>
									<ul class="for_calenderand_date">
										<li><a style="cursor: pointer;" onclick="mailCompose()">Compose Messages</a></li>
										<li><a href="#">Compose SMS</a></li>
										<li><a href="#">Create Contact</a></li>
										<li><a href="#">Create Event</a></li>
										<li><a href="#">Create Task</a></li>

									</ul></li>
							</ul>
						</div>
					</li>
				</ul>
				<!---- RIGHT TOOL STARTED HERE ---->
				<!--------/// Main Right Tool Stared Here -------->
				<div class="right_tool_part">
					<div class="right_tool">
						<a href="#"> <img src="images/reload.png">
						</a>
					</div>
					<div class="right_tool_1">
						<ul id="menu">
							<li><img src="images/setting_toll.png" class="four_margin"></li>
							<li class="right_menu_1"><img
								src="images/open_sub_menu.png"
								style="margin-left: 8px !important;"> <!-- <ul class="for_setting">
                                            <li> <a href="#">Settings</a></li>
                                            <li><a href="#">Themes</a></li>
                                            <li> <a href="#">Help</a></li>
                                        </ul>--></li>
						</ul>

					</div>
					<div class="right_tool_1">
						<ul id="menu">
							<li><img src="images/multi_level.png"> <a href="#"
								class="sub_menu_link"><img src="images/open_sub_menu.png"
									style="margin-left: 8px;"></a>
								<ul>
									<li><a style="cursor: pointer;" onClick="toggleViewPanel();panelView('offview')">Off</a></li>
									<li><a style="cursor: pointer;" onClick="shiftViewLeft();panelView('leftview')">Left view</a></li>
									<li><a style="cursor: pointer;" onClick="shiftViewBottom();">Bottom
											view</a></li>
								</ul></li>
								
						</ul>
						<input type="hidden" id="hid_panel_view" value="offview"/>
					</div>
					<a style="cursor: pointer;" onclick="pagiNextPage()">
						<div class="right_tool_1">
							<img src="images/next_mail.png" class="next_imag" >
						</div>
					</a> <a style="cursor: pointer;" onclick="pagiPrevPage()">
						<div class="right_tool_1">
							<img src="images/privious_mail.png" class="next_imag" >
						</div>
					</a>
					<%
					long mcount=Long.parseLong(mcnt);
					long end=15;
					if(end>mcount)
					{
						end=mcount;
					}
					%>
					<input type="hidden" id="hid_pagi_pcnt" value="1" />
					<input type="hidden" id="hid_pagi_allmail" value="<%=mcount %>">
					<%
					if(mcount==0)
					{
						 String act_fldrnm= hsbd.getAttribute("active_folder").toString();
					%>
					<div id="pagination_div" class="right_tool_2"
						style="margin-left: -14px; line-height: 29px;"><%=act_fldrnm %> is Empty </div>
					<%}
					else
					{
					%>
					<div id="pagination_div" class="right_tool_2" 
						style="margin-left: -14px; line-height: 29px;">1-<%=end %> of <%=mcount %></div>
						<%} %>
				</div>

				<!-------------------/// Main Right Tool End Here ------------->
			</div>
			<!------ RIGHT TOOL END HERE TOP ---->

			<div class="top_tool_information">
				<div class="left_function">
					<ul>
						<li class="flag_heading"><img src="images/black_star.png"></li>
						<li class="form_heading">FROM</li>
						<li>SUBJECT</li>
					</ul>
				</div>
				<div class="right_bortion">
					<ul>
						<li><a href="#"><img src="images/attachment.png"></a></li>
						<li><a href="#"><img src="images/tool.png"></a></li>
					</ul>
				</div>
				<div class="right_bortion  date">
					<ul id="menu" class="">
						<li style="height: 23px;">
							<div class="date_div">
								DATE <a style="margin-top: -2px;margin-left: -6px;cursor: pointer;" onclick="dtSorting()">
								<span id="dt_sorting"><img src="images/down_date.png"></span>
								</a>
								<input type="hidden" id="hid_dt_sorting" value="up"/>
							</div>
							
						</li>
					</ul>
				</div>
			</div>
		</div>


		<!-----------------------------/// Main Cointer Stared here --------------->
		<div id="widget" class="widget_new">



			<!---------------/// Tab Content Stared Here Off VIEW ---------------------------->
			<div class="mid_tab tab_main_div">


				<div class="mid_tab_1 right_tab">
					<div class="inner_tab_content">

						<!-------/// TAB HEADING FIRST ----->
						<div class="main_tab_first">
							<!-------/// TAB HEADING STARTED HERE ----->


							<!-------/// TAB HEADING END HERE ----->
							<!----/// TAB CONTENT MAIN STARTED HERE ------->

							<!----------// TAB FIRST CONTENT STARED HERE -------->
							<div id="inb_cnt_div" class="tab_first_content"></div>
							<!----------// TAB FIRST CONTENT END HERE -------->
							<div class="clear"></div>
						</div>


						<!-----/// RIGHT MID CONTENT TAB STARED HERE -------->
					</div>
				</div>




			</div>
			<!-----------------/// Tab Content End Here Off VIEW---------------------------->



			<!-------------------/// Right View and Bottom View Tab Stared Here --------------->

			<div class="mail_content">



				<div class="mail_content_1"
					style="width: 48.5%; float: right; display: block; min-height: 75%; max-height: 95%;">

					<div class="mail_left_content">
					
					<div id="hid_no_cnt" style="margin-top: 100px;text-align: center;">
  					
  					<span id="span_con">No</span> conversations selected<br><br>
  					<%
  					String qper=(String)request.getAttribute("QuotaPer");
  					String ql=(String)request.getAttribute("QuotaLimit");
  					String qu=(String)request.getAttribute("QuotaUses");
  					String qutu="0";
  					String qutl="0";
  					if(qper!=null && !(qper.equals("")) && ql!=null && !(ql.equals(""))  && qu!=null && !(qu.equals("")))
  					{
  						if(((Integer.parseInt(ql))/(1024))>=1024)
  						{
  							qutl=""+((Integer.parseInt(ql))/(1024*1024))+" GB";
  						}
  						else
  						{
  							qutl=""+((Integer.parseInt(ql))/(1024))+" MB";
  						}
  						
  						if(((Integer.parseInt(qu))/(1024))>=1024)
  						{
  							qutu=""+((Integer.parseInt(qu))/(1024*1024))+" GB";
  						}
  						else
  						{
  							qutu=""+((Integer.parseInt(qu))/(1024))+" MB";
  						}
  					}
  					%>
  					<div>
  					You are currently using <%=qutu %>  (<%=qper %> %) of your <%=qutl %><br>
  					<div style="border: 1px solid #c7c7c7;width: 300px;margin-left: auto;margin-right: auto;height: 10px;margin-top: 5px; ">
  					<div  style="height: 95%;background-color: #c7c7c7;width: <%=qper %>%"></div>
  					</div>
  					</div>
  					
  					</div>
					
					
					<div id="div_left_cnt">

					</div>
					</div>

					<!------/// MAIL MID CONTENT ---->
					<div class="clear"></div>


					<div class="clear"></div>
				</div>



			</div>

			<!-------------------/// Right View and Bottom View Tab End Here --------------->

		</div>

		<!--------------------/// Mail Cointer End Here ----------------->
		<div class="clear"></div>
		</div>
		<!--------------------/// Mail full apge display Here ----------------->
		<div id="div_full_pg_disp" style="display: none">
		
		
				
		</div>
		
	</div>
	<div id="div_for_compose" style="display: none;">
	</div>
	</div>
	</div>


	<div class="for_setting_1">

		<ul id="menu" class="extra_menu">
			<li><a href="#">&nbsp; </a>
				<ul class="for_setting new_submenu">
					<li><a href="#">Settings</a></li>
					<li><a href="#">Themes</a></li>
					<li><a href="#">Help</a></li>
				</ul></li>
		</ul>

	</div>



</body>
</html>