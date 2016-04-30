<%@ page language="java" import="webmail.wsdl.*,java.util.*,webmail.webservice.client.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
function getWebmailContactRefresh()
{
		//alert("contact");
		//var fdrname=document.getElementById('hid_active_fldr').value;
		//alert(fdrname)
		//var pview=document.getElementById("hid_panel_view").value;
		//alert(pview);
		var start='0';
		var end='15';
		try{
			 $('.cancel_right').hide();
			 $('.save_right').hide();
			 $('.cancel_fl_vi').hide();
			 $('.add_more').hide();
			 $( "#div_contact_disp_val" ).html( "" );
		}
		catch(err)
		{
			
		}
		//$( "#div_contact_disp_val" ).html( data );
		document.getElementById('action_gif').style.display= 'block';
		var pn = $('#pn').val();
		//alert('hi');
		$.ajax({
			type : "GET",
			url : "${pageContext.request.contextPath}/getContactList",
			data : {'pn':pn},
			contentType : "application/json",
			success : function(data) {
				// $("#fileSystem").html(data);
				document.getElementById('action_gif').style.display= 'none';
				// alert(data);
				//	alert(data);
// 				$("#contact_cnt_div_full").html(data);
				var obj = jQuery.parseJSON(data);
				$("#contact_cnt_div_full").html(obj.contacts);
				$('#start_end').html(obj.start+"-"+obj.end);
				$('#total').html(obj.total);
				$('.cal_next_and_Pre').show();
				//getAllMailCount(fdrname);
				document.getElementById('div_progress').style.display= 'none';
			}
		});

//saveContact()
}
</script>
<script type="text/javascript">
function createContactGroup() {
	 if($('.Sk').val()=='')
     {
             //  alert("Please Fill Group Name")
    	 showMsg("alert","Please Fill Group Name");
     }
     else if($('.Sk').val()=='Collected Contacts' || $('.Sk').val()=='collected contacts' || $('.Sk').val()=='Personal Contacts' || $('.Sk').val()=='personal contacts' || $('.Sk').val()=='Directory' || $('.Sk').val()=='directory')
    	 {
    	 showMsg("alert","This group is already exist.");
    	 }
     else 
     {
    	  var find_group = document.getElementById("contact_gp_val").value;
    		//alert(fname);
    				
    	  document.getElementById('action_gif').style.display= 'block';
    				
    				$.ajax({
    					type : "GET",
    					url : "${pageContext.request.contextPath}/createContactGroup",
    					data : {
    						'folderName': find_group
    					},
    					contentType : "application/json",
    					success : function(data) {
    						
    						document.getElementById('action_gif').style.display= 'none';
    						if(data=="true")
    							{
    						var option = document.createElement("option");
    						option.text = find_group;
    						option.value = find_group;
    						var select = document.getElementById("gp_select");
    						select.appendChild(option);
    					
	      
			//alert(find_group);
			$('.my_calender_con >ul').append('<li style="cursor: pointer;" onclick="getSelContactUserList(this.id)" id="/Contacts/'+find_group+'" > <img src="images/group_con.png" class="icon_con col_con group_img"> <span>'+ find_group +'</span><div class="clear"></div></li>');
			$('.my_calender_con ul li').hover(
	        		 function(){ $(this).css('border-left', "3px solid "+$("#hid_mail_bgColor").val()) },
	        		 function(){ 
	        			$(this).css('border-left', "3px solid #fff") 
	        			$(".my_con_active").css("border-left","3px solid "+$("#hid_mail_bgColor").val())	 
	        		 }
	        );
			 showMsg("alert","Group created successfully.");
    							}
			$('.Sk').val('');
			$('.group_name').hide();
			$('.web_dialog_overlay').hide();
    					}
    				});	
     } 
	   
}
</script>
<script type="text/javascript">
function openContactForm() {
	//alert('hi');
	var requestPage="${pageContext.request.contextPath}/openContactForm";
	jQuery.get(requestPage,
            /*                  {
                    'path' : folderPath
            }, */
                    function( data ) {
            	//alert(data);
             $( "#create_contact_form" ).html( data );
             
            });
	
	/* $.ajax({
		type : "GET",
		url : "${pageContext.request.contextPath}/openContactForm",
		data : {
			 'folderName': fname 
		},
		contentType : "application/json",
		success : function(data) {
			// $("#fileSystem").html(data);
			alert(data);
					//$("#contact_cnt_div_full").html(data);
			//document.getElementById('action_gif').style.display= 'none';
			//getAllMailCount(fdrname);
				//	document.getElementById('div_progress').style.display= 'none';
		}
	}); */
}
</script>
<script type="text/javascript">
function saveContact() {
	
	$('.error').removeClass("error");
	var fullname=document.getElementById("fullname").value;
	var company=document.getElementById("company").value;
	var job=document.getElementById("job").value;
	var email=document.getElementById("email").value;
	var web_page=document.getElementById("web_page").value;
	var note=document.getElementById("note").value;
	var phone_work=document.getElementById("phone_work").value;
	var phone_home=document.getElementById("phone_home").value;
	var phone_fax=document.getElementById("phone_fax").value;
	var phone_mob=document.getElementById("phone_mob").value;
	var addr_work=document.getElementById("addr_work").value;
	var addr_home=document.getElementById("addr_home").value;
	var pre=document.getElementById('hid_edt_nm_pre').value;
	var fnm=document.getElementById('hid_edt_nm_first').value;
	var mnm=document.getElementById('hid_edt_nm_middle').value;
	var lmn=document.getElementById('hid_edt_nm_last').value;
	var suf=document.getElementById('hid_edt_nm_suf').value;
	var selectedGp = $("#gp_select option:selected").val();
	var con_img=document.getElementById('hid_contact_img_code').value;
	//alert(email);
	//alert('hi');
	 if(email == "" && !isEmail(email))
	 {
// 		 alert("Invalid");
		$('#email').addClass("error");
	 	return false;
	 }
	 $('.cc_clear').val('');
	/* $('.create_contact').hide();
	 $('.web_dialog_overlay').hide();  */
	 $('.con_crt').hide();
	 $('#div_contact_create').hide();
		 
	  document.getElementById('action_gif').style.display= 'block';
	  
	  $.post( "${pageContext.request.contextPath}/saveContactForm", 
			  { 'selectedGp': selectedGp,
			 'fullname': fullname, 
			 'company': company, 
			 'job': job, 
			 'email': email, 
			 'web_page': web_page, 
			 'note': note, 
			 'phone_work': phone_work, 
			 'phone_home': phone_home, 
			 'phone_fax': phone_fax, 
			 'phone_mob': phone_mob, 
			 'addr_work': addr_work, 
			 'addr_home': addr_home, 
			 'pre': pre, 
			 'fnm': fnm, 
			 'mnm': mnm, 
			 'lmn': lmn, 
			 'suf': suf,
			 'con_img': con_img },
			  function( data ) {
				 getWebmailContactRefresh();
		});
	  
	}
	
	
function isEmail(email) {
	  var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	  return regex.test(email);
	}
	
function editContact() {
	
	var fullname=document.getElementById("fullname").value;
	var company=document.getElementById("company").value;
	var job=document.getElementById("job").value;
	var email=document.getElementById("email").value;
	var web_page=document.getElementById("web_page").value;
	var note=document.getElementById("note").value;
	var phone_work=document.getElementById("phone_work").value;
	var phone_home=document.getElementById("phone_home").value;
	var phone_fax=document.getElementById("phone_fax").value;
	var phone_mob=document.getElementById("phone_mob").value;
	var addr_work=document.getElementById("addr_work").value;
	var addr_home=document.getElementById("addr_home").value;
	var pre=document.getElementById('hid_edt_nm_pre').value;
	var fnm=document.getElementById('hid_edt_nm_first').value;
	var mnm=document.getElementById('hid_edt_nm_middle').value;
	var lmn=document.getElementById('hid_edt_nm_last').value;
	var suf=document.getElementById('hid_edt_nm_suf').value;
	var con_img=document.getElementById('hid_contact_img_code_edit').value;
	var flnm=$('#hid_contact_file_name').val();
	
// 	alert("flnm : "+flnm + " ,email "+email);
	$('#email').css("border",'1px solid #ccc');
	 if(email == "" && !isEmail(email))
	 {
		 $('#email').css("border",'1px solid red');
	 	return false;
	 }
	 
// 	 alert("again");
	 $('.cc_clear').val('');
	/* $('.create_contact').hide();
	 $('.web_dialog_overlay').hide();  */
	 $('.con_crt').hide();
	 $('#div_contact_create').hide();
	  document.getElementById('action_gif').style.display= 'block';
	  
	  $.post( "${pageContext.request.contextPath}/editContactForm", 
			  { 
			 'fullname': fullname, 
			 'company': company, 
			 'job': job, 
			 'email': email, 
			 'web_page': web_page, 
			 'note': note, 
			 'phone_work': phone_work, 
			 'phone_home': phone_home, 
			 'phone_fax': phone_fax, 
			 'phone_mob': phone_mob, 
			 'addr_work': addr_work, 
			 'addr_home': addr_home, 
			 'pre': pre, 
			 'fnm': fnm, 
			 'mnm': mnm, 
			 'lmn': lmn, 
			 'suf': suf,
			 'con_img': con_img,
			 'flnm': flnm
			  },
			  function( data ) 
			  {
				  $('.save_right').hide();
					$('.cancel_fl_vi').hide();
					$('.cancel_right').removeClass('cancel_right_active');
					 $('.edite_name').hide();
					 $('.edite_address_edit').hide();
				 getWebmailContactRefresh();
		});
	  
	
}
</script>
<script type="text/javascript">
function editNameOpen() {
	
	///alert('hi');
	var fullname=document.getElementById("fullname").value.trim();
	var res =fullname.split(" "); 
	if(res.length==1)
	{
	document.getElementById('edt_nm_first').value=res[0].trim();
	}
	if(res.length==2)
		{
		document.getElementById('edt_nm_first').value=res[0];
		document.getElementById('edt_nm_last').value=res[1];
		}
	if(res.length>2)
		{
		var i=0;
		var l=1;
		if(res[i]==document.getElementById('hid_edt_nm_pre').value)
			{
			document.getElementById('edt_nm_pre').value=res[i];
			i++;
			document.getElementById('edt_nm_first').value=res[i];
			}
		else
			{
			document.getElementById('edt_nm_first').value=res[i];
			}
		
		
		if(res[res.length-l]==document.getElementById('hid_edt_nm_suf').value)
			{
			
			document.getElementById('edt_nm_suf').value=res[res.length-l];
			//alert(res.length-l)
			l++;
			document.getElementById('edt_nm_last').value=res[res.length-l];
			//alert(res.length-l)
			}
		else
			{
			document.getElementById('edt_nm_last').value=res[res.length-l];
			}
		
		
		var mid="";
		for(i++,j=1;i< res.length-l; i++,j++)
			{
			if(res[i]!=" ")
				{
			if(j==1)
				{
				mid=res[i];
				}
			else
				{
				mid=mid+" "+res[i];
				}
				}
			}
		document.getElementById('edt_nm_middle').value=mid;
		}
	//alert(res.length);
	//if(res.length)
	/* var pre= document.getElementById('').value;
	var pre= document.getElementById('').value;
	var pre= document.getElementById('').value;
	var pre= document.getElementById('').value;
	var pre= document.getElementById('').value; */
 if($('.edite_name_box').css('display')=='none')
	 {
		$('.edite_name_box').show();
		$('.web_dialog_overlay').show();
		//$('.web_dialog_overlay').css('z-index','13');  
	//	$('.web_dialog_overlay').addClass('bg_blur');  
	  } 
	
 
	
}

function editNameSave() 
{
	var hid_edt_nm_pre= document.getElementById('edt_nm_pre').value;
	var hid_edt_nm_first= document.getElementById('edt_nm_first').value;
	var hid_edt_nm_middle= document.getElementById('edt_nm_middle').value;
	var hid_edt_nm_last= document.getElementById('edt_nm_last').value;
	var hid_edt_nm_suf= document.getElementById('edt_nm_suf').value;
	
	document.getElementById('hid_edt_nm_pre').value=hid_edt_nm_pre;
	document.getElementById('hid_edt_nm_first').value=hid_edt_nm_first;
	document.getElementById('hid_edt_nm_middle').value=hid_edt_nm_middle;
	document.getElementById('hid_edt_nm_last').value=hid_edt_nm_last;
	document.getElementById('hid_edt_nm_suf').value=hid_edt_nm_suf;
	var val="";
	if(hid_edt_nm_pre!=null && hid_edt_nm_pre!="")
	{
	val=val+hid_edt_nm_pre+" ";
	}
	if(hid_edt_nm_first!=null && hid_edt_nm_first!="")
	{
	val=val+hid_edt_nm_first+" ";
	}
	if(hid_edt_nm_middle!=null && hid_edt_nm_middle!="")
	{
	val=val+hid_edt_nm_middle+" ";
	}
	if(hid_edt_nm_last!=null && hid_edt_nm_last!="")
	{
	val=val+hid_edt_nm_last+" ";
	}
	//alert(hid_edt_nm_last);
	if(hid_edt_nm_suf!=null && hid_edt_nm_suf!="")
	{
	val=val+hid_edt_nm_suf+" ";
	}
	//alert(val);
	document.getElementById("fullname").value=val;
	 $('.edite_name_box').hide();
     $('.web_dialog_overlay').hide();
	// $('.web_dialog_overlay').css('z-index','3'); 
	 $('.web_dialog_overlay').removeClass('bg_blur'); 
}
</script>
<script type="text/javascript">
function  setGroupSelVal() {
	//alert('hi');
	 $.ajax({
        type: "GET",
        url: "${pageContext.request.contextPath}/setGroupSelVal",
       // data: {'path':folderPath},
        contentType: "application/json",
        success: function (data) {
           // $("#fileSystem").html(data);
          // alert(data);
           $("#gp_sel_span").html(data);
         
        }
    });
}
</script>
<script type="text/javascript">
$(document.body).on('click','#contact_cnt_div_full table tbody tr',function(event){
	document.getElementById('action_gif').style.display= 'block';
	var flname = $(this).attr('id');
	var con_name = $(this).attr('name');
	if(con_name=="Directory")
		{
		
		try{
			$('.con_edt').hide();
			$('.con_crt').hide();
			 $('#div_contact_create').hide();
			 $('#div_contact_disp_val').show();
			 $('.cancel_right').hide();
			 $('.save_right').hide();
			 $('.cancel_fl_vi').hide();
			 $('.add_more').hide();
			 $( "#div_contact_disp_val" ).html( "" );
		}
		catch(err)
		{
			
		}
		var requestPage="${pageContext.request.contextPath}/getAllValueContactLdap";
		jQuery.get(requestPage,
	                            {
	                    'searchid' : flname
	            },
	                    function( data ) {
	            	//alert(data);
	             $( "#div_contact_disp_val" ).html( data );
	             /* if($('.create_contact_edit').css('display') == 'none')
	        	 {
	        		
	        		$('.create_contact_edit').show();
	        		 $('.web_dialog_overlay').show(); 
	            } */
	             document.getElementById('action_gif').style.display= 'none';
	            });
		
		}
	else
		{
		
///
//function getAllValueContact(flname) {
	 var gp_name =  $('.my_con_active').attr('id');
	 var gp_nm=$('.my_con_active').attr('name');
//alert(gp_name);
	try{
		$('.con_edt').show();
		$('.con_crt').hide();
		 $('#div_contact_create').hide();
		 $('#div_contact_disp_val').show();
		if( gp_name.indexOf('/sharedContacts/')!=0)
			{
		 	$('.cancel_right').show();
			}
		else if(gp_nm=="manage")
			{
			$('.cancel_right').show();
			}
		else 
			{
			$('.cancel_right').hide();
			}
		 $('.save_right').hide();
		 $('.cancel_fl_vi').hide();
		 $('.add_more').hide();
		 $( "#div_contact_disp_val" ).html( "" );
	}
	catch(err)
	{
		
	}
	
	var requestPage="${pageContext.request.contextPath}/getAllValueContact";
	jQuery.get(requestPage,
                            {
                    'filename' : flname
            },
                    function( data ) {
            	//alert(data);
             $( "#div_contact_disp_val" ).html( data );
             $("#hid_contact_file_name").val(flname);
             $(".edite_name,.edite_address_edit").css("background-color",$("#hid_mail_bgColor").val());
           /*   if($('.create_contact_edit').css('display') == 'none')
        	 {
        		
        		$('.create_contact_edit').show();
        		 $('.web_dialog_overlay').show(); 
            } */
             document.getElementById('action_gif').style.display= 'none';
             event.stopPropagation();
            });
	
}
});


$(document.body).on('click','.na_li >table>tbody>tr>td',function(event){
	
	   $('.na_li_active').removeClass('na_li_active');
	   $('.con_edt').hide();
	   $(this).addClass('na_li_active');
	   var alpha=$(this).text();
	   document.getElementById('action_gif').style.display= 'block';
	   $('#pn').val("1");
	   var pn = $('#pn').val();
	   
	   if(alpha!="All")
	   {
	 		$.ajax({
	 			type : "GET",
	 			url : "${pageContext.request.contextPath}/getContactListAlpha",
	 			data : {
	 				'alpha' :alpha,'pn':pn
	 			},
	 			contentType : "application/json",
	 			success : function(data) {
	 				// $("#fileSystem").html(data);
	 				document.getElementById('action_gif').style.display= 'none';
	 				// alert(data);
	 				//	alert(data);
// 	 				$("#contact_cnt_div_full").html(data);
					var obj = jQuery.parseJSON(data);
					$("#contact_cnt_div_full").html(obj.contacts);
					$('.cal_next_and_Pre').hide();
// 					$('#start_end').html(obj.start+"-"+obj.end);
// 					$('#total').html(obj.total);
					
	 				$('#div_contact_disp_val').html("");
	 			}
	 		});
	   
	   //alert($(this).text());
	   
	   //event.stopPropagation();
	   }
	   else
	   {
		   $.ajax({
	 			type : "GET",
	 			url : "${pageContext.request.contextPath}/getContactList",
	 			data : {'pn':pn},
	 			contentType : "application/json",
	 			success : function(data) {
	 				// $("#fileSystem").html(data);
	 				document.getElementById('action_gif').style.display= 'none';
	 				// alert(data);
	 				//	alert(data);
	 				var obj = jQuery.parseJSON(data);
					$("#contact_cnt_div_full").html(obj.contacts);
					$('#start_end').html(obj.start+"-"+obj.end);
					$('#total').html(obj.total);
					$('.cal_next_and_Pre').show();
// 	 				$("#contact_cnt_div_full").html(data);
	 				 $('#div_contact_disp_val').html("");
	 			}
	 		});
	   }
	});
	
	
$(document.body).on('keydown','#searchid',function(e){
  	   if((e.which == 13 ))
  		   {
  		 searchContacts() ;
  		   }
  });
	
	function searchContacts() {
		   document.getElementById('action_gif').style.display= 'block';
		   
		   var csearch=$('#searchid').val();
		 $.ajax({
		 			type : "GET",
		 			url : "${pageContext.request.contextPath}/getContactListAlpha",
		 			data : {
		 				'alpha' :csearch
		 			},
		 			contentType : "application/json",
		 			success : function(data) {
		 				// $("#fileSystem").html(data);
		 				document.getElementById('action_gif').style.display= 'none';
		 				// alert(data);
		 				//	alert(data);
// 		 				 $("#contact_cnt_div_full").html(data);
						var obj = jQuery.parseJSON(data);
						$("#contact_cnt_div_full").html(obj.contacts);
						//$('#start_end').html(obj.start+"-"+obj.end);
						//$('#total').html(obj.total);
						$('.cal_next_and_Pre').hide();
		 				$('#div_contact_disp_val').html("");
		 				$('#searchid').val("");
		 		
		 			}
		 		});
		  
	}

/* function getAllValueContactLdap(searchid) {
	document.getElementById('action_gif').style.display= 'block';
	try{
		$('.con_edt').hide();
		$('.con_crt').hide();
		 $('#div_contact_create').hide();
		 $('#div_contact_disp_val').show();
		 $('.cancel_right').hide();
		 $('.save_right').hide();
		 $('.cancel_fl_vi').hide();
		 $('.add_more').hide();
		 $( "#div_contact_disp_val" ).html( "" );
	}
	catch(err)
	{
		
	}
	var requestPage="${pageContext.request.contextPath}/getAllValueContactLdap";
	jQuery.get(requestPage,
                            {
                    'searchid' : searchid
            },
                    function( data ) {
            	//alert(data);
             $( "#div_contact_disp_val" ).html( data );
              if($('.create_contact_edit').css('display') == 'none')
        	 {
        		
        		$('.create_contact_edit').show();
        		 $('.web_dialog_overlay').show(); 
            } 
             document.getElementById('action_gif').style.display= 'none';
            });
	
	
}
*/





/* 
function delConFldr(conid) {
	document.getElementById('action_gif').style.display= 'block';
	//alert(conid);
	var arr=conid.split("_");
	
	$.ajax({
		type : "GET",
		url : "${pageContext.request.contextPath}/delConFldr",
		data : {
			'conid' : arr[1]
			
		},
		contentType : "application/json",
		success : function(data) {
			
			document.getElementById('action_gif').style.display= 'none';
			
		}
	});
	
}
 */

function shareConFldr() {
	document.getElementById('action_gif').style.display= 'block';
	var e = document.getElementById("shareid"); 
	   	var shareid = e.options[e.selectedIndex].value;
	   //	alert(shareid);
	   	var e1 = document.getElementById("confldr"); 
	   	var confldr = e1.options[e1.selectedIndex].value;
	  	//alert(confldr);
	$.ajax({
		type : "GET",
		url : "${pageContext.request.contextPath}/shareConFldr",
		data : {
			'shareid' : shareid,
			'confldr': confldr
			
		},
		contentType : "application/json",
		success : function(data) {
			
			document.getElementById('action_gif').style.display= 'none';
			//alert("Contact has been shared.");
			  $('.sharing_mange').hide();
              $('.web_dialog_overlay').hide();
        
		}
	});
	
}


 function exportVCF() 
 	{
	 $('.export_con').hide();
	 $('.web_dialog_overlay').hide();
	 var selectedVal="";
	 var selected = $("input[type='radio'][name='source']:checked");
	 if (selected.length > 0) {
	     selectedVal = selected.val();
	 }
	 var fldr="";
	 var con_arr = new Array();
	 var i=0;
	 if(selectedVal=="contact")
		 {
		
		
		 $('input[name="chk_con"]:checked').each(function() {
			//alert( $(this).val());
			con_arr[i]=$(this).val();
			i++;
   		});
		 }
	 
	 else if(selectedVal=="group")
		 {
		 fldr= $("#export_combo").find('option:selected').val();
		 }
	 if(i==0 && selectedVal=="contact")
		 {
		 
		 }
	 else
		 {
		 
	 document.getElementById('action_gif').style.display= 'block';
		var requestPage="${pageContext.request.contextPath}/exportVCFFile";
		jQuery.post(requestPage,
	                            {
	                 'group_name': fldr, 'export_type': selectedVal, 'con_arr' : JSON.stringify(con_arr)
	            }, 
	                    function( data ) {
	            	
	            	document.getElementById('action_gif').style.display= 'none';
	          
	            }); 
		 }
}
 
 function importVCF() 
 {
	 $('.import_con').hide();
	 $('.web_dialog_overlay').hide();
	/*  var success = generate_body("alert");
  	 $.noty.setText(success.options.id, 'not implemented for Demo'); // same as alert.setText('Text Override')
  	 setTimeout(function () {   $.noty.close(success.options.id); }, 5000);  */
}

</script>


<script type="text/javascript">
    function assignSinglePermissions(doctype) {
       
        $('.sharing_mange').hide();
        $('.web_dialog_overlay').hide();
        var count=0;
        document.getElementById('action_gif').style.display= 'block';
       
     //   var myVar=setTimeout(myFunctionl,500);
        var userss="";
        var valuess="";
       
        $(".userforshare").each(function() {
            count++;
            var counter=0;
            var userforshare=$(this).val();
            $(".permissionsforshare").each(function() {
                counter++;
                if(count==counter){
                var permissionforshare=$(this).val();
                var values=userforshare+","+permissionforshare;
                var vale = values;
                var valu = vale.split(",");
                var user = "";
                vale = valu[1];
                user = valu[0];
               
                var sourcePaths=$("#hid_active_contact_fldr").val();
                docPath1=sourcePaths;
               
                //alert(docPath1);
                value = valu[1];
            //    alert("user = "+user+"  value = "+value);
            if(user!=""){
                userss+=user+"/sharedContacts"+",";
                valuess+=value+",";
                }
            }
            });
        });
        //alert(userss);
        $.ajax({
            type : "GET",
            url : "${pageContext.request.contextPath}/assignSinglePermission",
            data : {
                'user' : userss,
                'value' : valuess,
                'doctype' : doctype,
                'multipath':docPath1,
            },
            contentType : "application/json",
            async : true,
            success : function(data) {
                if(data=="true")
                {location.href="ajaxTrue";
                }else
                {
                }
                document.getElementById('action_gif').style.display= 'none';
                showMsg("alert","Shared Successfully");
                   
                   
                }
            //}
        });

       // myStopFunction(myVar);
    }
   
    function showMsg(type,msg){
         var n = noty({
                text        : msg,
                type        : type,
                dismissQueue: false,
                layout      : 'topCenter',
                theme       : 'defaultTheme'
            });
       
       
                 var success = n;
             //    $.noty.setText(success.options.id, ''); // same as alert.setText('Text Override')
                 setTimeout(function () {
                            $.noty.close(success.options.id);
                        }, 5000);  
    }
</script>


</head>
<body>
<%
HttpSession hsbd=request.getSession();
String mailid_bd=hsbd.getAttribute("id").toString();
String fname_bd=hsbd.getAttribute("fname").toString();

String limitContact = hsbd.getAttribute("limitContact").toString();

byte[] jpegBytes2=(byte[])hsbd.getAttribute("img_myurl");
String chat_img_bd="";
if(jpegBytes2!=null)
{
	chat_img_bd= new sun.misc.BASE64Encoder().encode(jpegBytes2);
}

WebmailClient webmailClient=(WebmailClient)request.getAttribute("webmailClient");
FolderClient folderClient=(FolderClient)request.getAttribute("folderClient");
//HttpSession hs_c=request.getSession();
String id=(String)hsbd.getAttribute("id");
String pass=(String)hsbd.getAttribute("pass");
String ldapurl=(String)hsbd.getAttribute("ldapurl");
String ldpabase=(String)hsbd.getAttribute("ldapbase");
String path="/"+id+"/Contacts";
%>
<input type="hidden" id="hid_contact_confirm" value="" />
<div class="right-pane"> 
    
    <!-----------------------------/// Main Cointer Stared here --------------->
    <div  id="widget" > 
    <!-----// CONTACT HEADER STARED ------->
           <div class="for_tool_contact">
      
      <!-------/// CALENDER TOP THREE OPTION ----------->
           <div  class="top_three_con"> 
        
                    <!---/// FIRST CALENDER ------>
                    <div class="first_con_option new_con_option"><div class="create_con_icon"></div>Create Group </div>
                    <!------/// FIRST CALENDER END HERE --------> 
                    <!---///onclick="openContactForm()" SECOND CALENDER ------>
                    <div class="first_con_option_1 new_con_option"><div  class="create_con_icon_1"></div>Create Contact</div>
                    <!------/// SECOND CALENDER END HERE --------> 
                     <!---/// THIRD CALENDER ------>
                   <!--  <div class="first_con_option_2 mange_sharing new_con_option"><div class=""></div>Share</div> -->
                    <!------/// THIRD CALENDER END HERE --------> 
                         <!---/// SECOND CALENDER ------>
                   <!--   <div class="first_con_option_4"><div class="create_con_icon_4"></div>SMS</div> -->
                   
                <div class="first_con_option_3 new_con_option"><div class="create_con_icon_3"></div>More</div> 
                    
                    <div class="con_more">
                        <ul>  
                           
                           <li class="delete_group"><a>Delete Group</a></li>
                           <li class="mange_sharing"><a >Manage Sharing</a></li>
                           <li class="import_click"><a >Import...</a></li>
                           <li class="export_click"><a >Export...</a></li>
                          <!--  <li><a href="#">Print...</a></li>
                           <li><a href="#">Find & merge duplicates...</a></li> -->
                           
                        </ul>
                      <div class="clear"></div>
                    </div> 
                    <!----------/// CONTACT END HERE ------------>
                    <!------/// THIRD CALENDER END HERE --------> 
                    
                    <div class="first_con_option_4 delete_new_row" >
               		<img src="images/tool.png"> <!-- <span class="delete_row_1">1</span> --></div>
        
      </div>
      <!---------/// CALENDER TOP THREE OPTION END HERE -------->
      <!---// TOOL --->
              <div class="right_tool_part cal_rig">
                   <!--/// CONTACT VIEW CHANGES OPTION STARED HERE --------->
                  <!--  <div class="con_viw">
                       <ul>
                         <a href="#"/> <li class="list_active">
                              <div class="con_list"></div>
                         </li></a>
                         <a href="#"/> <li>
                              <div class="de_list"></div>
                          
                          </li></a>
                       </ul>
                       <div class="clear"></div>
                   </div> -->
                   <!----/// CONTACT VIEW CHANGES END HERE ----------->
                   
                   <!---------/// CONTACT VIEW CHAGES END ---->
                    <div class="cal_next_and_Pre">
                   
                      <a href="#" class="next"><div class="right_tool_1"> <img src="images/next_mail.png" class="next_imag "> </div></a>
                      <a href="#"  class="priv"><div class="right_tool_1"> <img src="images/privious_mail.png" class="next_imag "> </div></a>
<!--                       PAGINATION -->
                      <div id="pagination_div" class="right_tool_2_11" style=" line-height: 29px;"><span id="start_end">1-<%=limitContact %></span> of <span id="total">61</span></div>
                      <input type="hidden" id="pn" class="pn" value="1">
                      <input type="hidden" id="rpp" class="rpp" value="<%=limitContact %>">
                    </div> 
                    
                    
                       <div class="right_tool_part for_calender">
                                 <div class="right_tool"> <a href=""> <img src="images/reload.png"> </a> </div>
                                 <!--  <div class="right_tool_1">
                                           <ul id="menu" >
            <li> <img src="images/setting_toll.png" class="four_margin" ></li>
            <li class="right_menu_1" > <img src="images/open_sub_menu.png" style="margin-left: 8px !important;"> 
              <ul class="for_setting">
                                            <li> <a href="#">Settings</a></li>
                                            <li><a href="#">Themes</a></li>
                                            <li> <a href="#">Help</a></li>
                                        </ul> 
            </li>
          </ul>
                                    </div> -->
                      </div>
              </div>
      <!---/// TOOL END ---->
      </div>
      <!----------/// CONTACT HEADER END HERE ------->
      <!--/// LIST ACCORDING TO A TO 10 STRAED HERE ------->
     <div class="na_li">
           <table>
                   <tr>
                          <td id="ctv_td" class="na_li_active">All</td>
                          <td>123</td>
                          <td>A</td>
                          <td>B</td>
                          <td>C</td>
                          <td>D</td>
                          <td>E</td>
                          <td>F</td>
                          <td>G</td>
                          <td>H</td>
                          <td>I</td>
                          <td>J</td>
                          <td>K</td>
                          <td>L</td>
                          <td>M</td>
                          <td>N</td>
                          <td>O</td>
                          <td>P</td>
                          <td>Q</td>
                          <td>R</td>
                          <td>S</td>
                          <td>T</td>
                          <td>U</td>
                          <td>V</td>
                          <td>W</td>
                          <td>X</td>
                          <td>Y</td>
                          <td>Z</td>
                     </tr>
           </table>
      
      </div> 
      <!-----// LIST ACCORDING TO A TO 10 END HERE -------->
             <!--------/// SPACE FOR CONTACT PAGES ------------->
             <!---//TABLE HAEDER STARED -------->
            <table class="con_he_list">
        <tr>
          <td class="left_head"><input class="contact_check_all" type="checkbox"/></td>
          <td class="con_imag_head"><img src="images/white_man.png" /></td>
          <td class="con_name_head">Name</td>
          <td class="con_email">Email</td>
        </tr>
      </table>
      <!--// RIGHT OPTION HERE ------->
      <table class="con_he_list_right">
        <tr>
          <td  colspan="4">
          <div class="con_edt">
          <div onclick="editContact()" class="save_right "> <div   class="save_icon"></div>Save</div>
              <div class="cancel_fl_vi"><div class="cancel_icon_fl_vi">X</div>Cancel</div>
              <div class="cancel_right "><div class="edite_icon"></div>Edit</div>
              <div class="add_more"><div class="add_more_icon"></div>Add More</div>
              </div>
              
              
              <div class="con_crt">
          	   <div onclick="return saveContact()" class="save_right_create">
              <div class="save_icon"></div>Save</div>
              <div class="cancel_fl_vi_create"><div class="cancel_icon_fl_vi">X</div>Cancel</div>
           </div>
              </td>
        </tr>
      </table>
             <!--------/// TABLE HEADER END HERE ------->
             <div class="contact_content_list"> 
        <!---------/// DETAILS VIEW STARED HERE --------> 
        
        <!---// LEFT PART CONTACT SATRED ---->
        <div class="left_con_part">
             <div  id="contact_cnt_div_full">
             
                 
               
                 
                 
                 
                 
             </div>
           </div>
           
            <div id="div_contact_disp_val" class="right_con_part"> 
            
            
            
            
            </div>
            
            <div id="div_contact_create" style="display: none;" class="right_con_part"> 
            
            
            
            <div class="right_con_part_pop" id="create_contact_form"> 
          
          <!------------/// RIGHT CONTENT STARED HERE -------------->
          <input type="hidden" id="hid_contact_img_code" />
           <table>
           <tr class="right_name_first_pop">
              <td class="group_name_sel">Group Name</td>
              <td colspan="2">
                            
               <select class="create_select"  id="gp_select">
               <option value="Personal Contacts">Personal Contacts</option>
               <%
               String path1="/"+id+"/Contacts";
         		GetFolderResponse folderResponse1 = folderClient.getFolderRequest(path1,id,pass);
         		List<Folder> folderList1 = folderResponse1.getGetFoldersByParentFolder().getFolderListResult().getFolderList();
         		for ( Folder fEntry : folderList1) {
     	        	// System.out.println(fileEntry.getName());
     	        	 if(!(fEntry.getFolderName().equalsIgnoreCase("Personal Contacts") || fEntry.getFolderName().equalsIgnoreCase("Collected Contacts") || fEntry.getFolderName().equalsIgnoreCase("Directory")))
     	        	 {
               %>
                  <option value="<%= fEntry.getFolderName() %>"><%= fEntry.getFolderName() %></option>
               <%} 
               }
               

              String  path2="/"+id+"/sharedContacts";
        		GetSharedFoldersByPathResponse sharedfolderResponse = folderClient.getSharedFoldersByPathRequest(id, pass, path2);
        		
        		 if(sharedfolderResponse.getGetSharedFoldersByPath().getFolderListResult()!=null){
     	 			List<Folder> sharedfolderList = sharedfolderResponse.getGetSharedFoldersByPath().getFolderListResult().getFolderList();
     			for ( Folder fEntry : sharedfolderList) {
     			
     		        	// System.out.println(fileEntry.getName());
     		        //	 if(!(fEntry.getFolderName().equalsIgnoreCase("Personal Contacts") || fEntry.getFolderName().equalsIgnoreCase("Collected Contacts") || fEntry.getFolderName().equalsIgnoreCase("Directory")))
     		        //	 {
     		        	//	 res+="<li style='cursor: pointer;'  onclick='getSelContactUserList(this.id)' id='/sharedContacts/"+fEntry.getFolderName()+"'> <img src='images/group_con.png' class='icon_con col_con group_img'> <span title='"+fEntry.getFolderName()+"("+fEntry.getCreatedBy()+")'>"+fEntry.getFolderName()+"("+fEntry.getCreatedBy()+")</span><div class='clear'></div></li>";
     		        	// }
     		        if(fEntry.getUserSecurity().toString().contains(id))	  	 
     		        {
     		                    %>
     		                       <option value="<%= "/sharedContacts/"+fEntry.getFolderName() %>"><%= fEntry.getFolderName()+"("+fEntry.getCreatedBy()+")" %></option>
     		                    <%
     		        	  	 }
     		    }}
               %>
               </select>
              
              
              
            </td>
            </tr>
            <tr>
              <td colspan="3" class="right_info_heading">
                 
              
              </td>
            </tr>
            <tr>
              <td colspan="3" class="right_info_heading_pop">Personal Information</td>
            </tr>
            <tr>
              <td class="right_name_first_pop">Full Name </td>
              <td class="right_text_pop"><input class="cc_clear" type="text" id="fullname" style="width: 180px;" value="" />
                <div onclick="editNameOpen()" style="display: block;" class="edite_name">
                  <div style="display: block;" class="name_edit"></div>
                  Edit Name</div></td>
              <td rowspan="3"><img width="96px"  height="97px" id="crt_con_img" src='images/contact_photo.png'/>
                <div onclick="uploadContactDP()" class="save_chnage_pop">Change Image</div></td>
            </tr>
            <tr>
              <td class="right_name_first_pop">Company</td>
              <td><input type="text" class="cc_clear" id="company"  value=""/></td>
            </tr>
            <tr>
              <td class="right_name_first_pop">Job Title</td>
              <td><input type="text" class="cc_clear" id="job" value=""/></td>
            </tr>
            <tr class="right_name_first_pop">
              <td colspan="3" class="right_info_heading">Internet</td>
            </tr>
            <tr class="right_name_first_pop">
              <td class="right_name_first">email</td>
              <td colspan="2"><input id="email" class="cc_clear" type="text" value="" /></td>
            </tr>
           <tr class="right_name_first_pop">
              <td class="right_name_first">WWW</td>
              <td colspan="2"><input id="web_page" class="cc_clear" type="text" value=""/></td>
            </tr>
            
            <tr class="right_name_first_pop">
              <td colspan="3" class="right_info_heading">Phone Number</td>
            </tr>
            <tr class="right_name_first_pop">
              <td class="right_name_first">Business</td>
              <td colspan="2"><input id="phone_work" class="cc_clear" type="text" value="" /></td>
            </tr>
            <tr class="right_name_first_pop">
              <td class="right_name_first">Home</td>
              <td colspan="2"><input id="phone_home" class="cc_clear" type="text" value="" /></td>
            </tr>
            <tr class="right_name_first_pop">
              <td class="right_name_first">Fax</td>
              <td colspan="2"><input id="phone_fax" class="cc_clear" type="text" value="" /></td>
            </tr>
            <tr class="right_name_first_pop">
              <td class="right_name_first">Mobile</td>
              <td colspan="2"><input id="phone_mob" class="cc_clear" type="text" value="" /></td>
            </tr>
            <tr class="right_name_first_pop">
              <td colspan="3" class="right_info_heading">Address</td>
            </tr>
            <tr class="right_name_first_pop">
              <td class="right_name_first">Business</td>
              <td colspan="2"><input id="addr_work" class="cc_clear" type="text"  value="" />
              <div style="display: none;" class="edite_address_edit_peole"><div class="name_edit_edit"></div> Edit Address</div>
              </td>
            </tr>
            <tr class="right_name_first_pop">
              <td class="right_name_first">Home</td>
              <td colspan="2"><input id="addr_home" class="cc_clear" type="text"  value="" />
              <div style="display: none;" class="edite_address_edit_peole"><div class="name_edit_edit"></div> Edit Address</div>
              </td>
            </tr>
             <tr class="right_name_first_pop">
              <td colspan="3" class="right_info_heading">Note</td>
            </tr>
            <tr class="right_name_first_pop">
              <td class="right_name_first">Add a Note</td>
              <td colspan="2"><textarea id="note" class="cc_clear" rows="5" cols="55"></textarea> </td>
            </tr>
          </table>
          <!------------/// RIGHT CONTENT END ----------> 
          
        </div>
            
            
            
            </div>
            
           </div>
             
             <!----------/// SPACE FOR CONTECT PAGES END HERE ------>
      
       
      
      <!-------------------/// Right View and Bottom View Tab End Here ---------------> 
      
    </div>
    
    <!--------------------/// Mail Cointer End Here ----------------->
    <div class="clear"></div>
  </div>
 
</div>
<div class="for_setting_1">
  <ul id="menu" class="extra_menu">
    <li> <a href="#">&nbsp; </a>
      <!-- <ul class="for_setting new_submenu">
        <li> <a href="#">Settings</a></li>
        <li><a href="#">Themes</a></li>
        <li> <a href="#">Help</a></li>
      </ul> -->
    </li>
  </ul>
</div>





<div class="pop_for_image_upload_contact" >
                 <h1>Upload Image <span>X</span></h1>
                      
                      
                       <form id="uploadImageContact" >
			         
			       
                      <table>
                      
                           <tbody><tr>
                              <td colspan="2"> <input type="file" id="upl2" name="upl2"  /></td>
                           </tr>
                           <tr>
							 <td colspan="2" style="    font-size: 11px;">Accepted image size is up to 10KB and File type must be png or jpg only. </td>
                           </tr>
                           <tr>
                              <td></td>
                              <td><input type="button" onclick="changeContactImg()" value="Upload" class="file_upload_contact search_button"></td>
                           
                           </tr>
                      	   </tbody></table>
                  	</form>
                 </div>
                 
                 
                 
                 <div class="pop_for_image_upload_contact_edit" >
                 <h1>Change Image <span>X</span></h1>
                      
                      
                       <form id="uploadImageContactEdit" >
			         
			       
                      <table>
                      
                           <tbody><tr>
                              <td colspan="2"> <input type="file" id="upl3" name="upl3"  /></td>
                           </tr>
                            <tr>
							 <td colspan="2" style="    font-size: 11px;">Accepted image size is up to 10KB and File type must be png or jpg only. </td>
                           </tr>
                           <tr>
                           <tr>
                              <td></td>
                              <td><input type="button" onclick="changeContactImgEdit()" value="Upload" class="file_upload_contact search_button"></td>
                           
                           </tr>
                      	   </tbody></table>
                  	</form>
                 </div>


  <!-- chat start here -->
               
               
<div class="all_chat_option">
                                       <div class="chat_info arrow-left">
                                           <div class="chat_info_left">
                                              <p class="name">Hariom Srivastava</p>
                                              <span>hari@silvereye.co</span>
                                              <p class="com"></p>
                                           </div>
                                           <div class="chat_info_right">
                                              <img src="images/photo_1.jpg" />
                                           
                                           </div>
                                           <div class="clear"></div>
                                           <div class="bottom_option">
                                              <!-------/// Bottom _Left_part--->
                                                <div class="left_bottom">
                                                    <ul>  
                                                       <li><a style="cursor: pointer;">Contact info</a></li>
                                                       <li><a style="cursor: pointer;">Emails</a></li>
                                                    </ul>
                                                </div>
                                              <!----------/// Bottom Left Part End --->
                                              <!--------/// Bottom Right part Stared Here ------>
                                               <div class="right_bottom">
                                                      <ul>
                                                         <li class="chat_mail"><a style="cursor: pointer;"></a></li>
                                                         <li class="contact_mail"><a style="cursor: pointer;"></a></li>
                                                         <li><a style="cursor: pointer;"></a></li>

                                                      </ul>
                                               
                                               </div>
                                              <!---------------/// Bottom  Right Part End Here -------->	
                                           
                                           </div>
                                      
                                      </div>
                                      
                                       <!-----------------//// Chat Search option Here --------->
                                       <div class="chat_info_1 arrow-left">
                                           <div class="chat_info_left">
                                              <p class="name">Hariom Srivastava</p>
                                              <span>hari@silvereye.co</span>
                                             
                                           </div>
                                           <div class="chat_info_right">
                                              <img src="images/photo_1.jpg" />
                                           
                                           </div>
                                           <div class="clear"></div>
                                           <div class="bottom_option">
                                              <!-------/// Bottom _Left_part--->
                                                <div class="left_bottom">
                                                    <ul>  
                                                       <li><a style="cursor: pointer;">Add to contacts</a></li>
                                                       <li><a style="cursor: pointer;">Emails</a></li>
                                                    </ul>
                                                </div>
                                              <!----------/// Bottom Left Part End --->
                                              <!--------/// Bottom Right part Stared Here ------>
                                               <div class="right_bottom">
                                                      <ul>
                                                         <li class="chat_mail"><a style="cursor: pointer;"></a></li>
                                                         <li class="contact_mail"><a style="cursor: pointer;"></a></li>
                                                         <li><a style="cursor: pointer;"></a></li>

                                                      </ul>
                                               
                                               </div>
                                              <!---------------/// Bottom  Right Part End Here -------->	
                                           
                                           </div>
                                      
                                      </div>
                                       <!---------------/// Chat Search End Here ----------->
                                                        <!-------/// Chat Search option--------->
                                         <div class="chat_search">
                                             <div class="chat_search_option">
                                                   <div class="heading_caht">
                                                   <input type="text" placeholder="Search for people" />
                                              </div>
                                               <!-----//// Chat Search Content ----->
                                                                      <div class="chat_search_content">
                                              
                                                        <!------------//// FIRST ROW --------->
                                                           <div class="cheat_row_11">
                                                                  <div class="small_images"> <img src="chat/photo.jpg" class="user_images"/><img src="chat/green.png" class="online_green"> </div>
                                                                      <div class="contact_information">
                                                                      <p><strong>Nitit Gupta</strong><br>
                                                                       nitin@silvereye.in</p>
                                                                    </div>
                                                                 
                                                           </div>
                                                        <!-----------/// FIRST ROW -------------->
                                                              <!------------//// FIRST ROW --------->
                                                     <div class="cheat_row_11">
                                                            <div class="small_images"> <img src="chat/photo.jpg" class="user_images"/><img src="chat/block.png" class="online_green"> </div>
                                                                <div class="contact_information">
                                                                <p><strong>Shiv Shankar</strong><br>
                                                                  shiv@silvereye.in</p>
                                                              </div>
                                                         
                                                     </div>
                                                  <!-----------/// FIRST ROW -------------->
                                                              <!------------//// FIRST ROW --------->
                                                     <div class="cheat_row_11">
                                                            <div class="small_images"><img src="chat/photo.jpg" class="user_images" /><img src="chat/green.png" class="online_green"> </div>
                                                                <div class="contact_information">
                                                                <p><strong>Abhay Pratap Singh</strong><br>
                                                                  abhay@silvereye.in</p>
                                                              </div>
                                            
                                                     </div>
                                                  <!-----------/// FIRST ROW -------------->
                                                              <!------------//// FIRST ROW --------->
                                                     <div class="cheat_row_11">
                                                            <div class="small_images"><img src="chat/photo.jpg" class="user_images"/><img src="chat/green.png" class="online_green"></div>
                                                                <div class="contact_information">
                                                                <p><strong>Lalit Maurya</strong><br>
                                                                 lalit@silvereye.in</p>
                                                              </div>
                                                    
                                                     </div>
                                                  <!-----------/// FIRST ROW -------------->
                                                              <!------------//// FIRST ROW --------->
                                                     <div class="cheat_row_11">
                                                            <div class="small_images"><img src="chat/photo.jpg" class="user_images"/><img src="chat/busy.png" class="online_green"></div>
                                                                <div class="contact_information">
                                                                <p><strong>RamSiya Tiwari</strong><br>

                                                                  ramsiya@silvereye.in</p>
                                                              </div>
                                                   
                                                     </div>
                                                  <!-----------/// FIRST ROW -------------->
                                                              <!------------//// FIRST ROW --------->
                                                     <div class="cheat_row_11">
                                                            <div class="small_images"><img src="chat/photo.jpg" class="user_images"/><img src="chat/offline.png" class="online_green"></div>
                                                                <div class="contact_information">
                                                                <p><strong>Rohan Pandey</strong><br>
                                                                 rohan@silvereye.in</p>
                                                              </div>
                                                    
                                                     </div>
                                                  <!-----------/// FIRST ROW -------------->
                                                              <!------------//// FIRST ROW --------->
                                                     <div class="cheat_row_11">
                                                            <div class="small_images"><img src="chat/photo.jpg" class="user_images"/><img src="chat/busy.png" class="online_green"></div>
                                                                <div class="contact_information">
                                                                <p><strong>Naredra Modi</strong><br>
                                                                  narendra@silvereye.in</p>
                                                              </div>
                                                   
                                                     </div>
                                                     
                                                     
                                                       <!-----------/// FIRST ROW -------------->
                                                              <!------------//// FIRST ROW --------->
                                                     <div class="cheat_row_11">
                                                            <div class="small_images"><img src="chat/photo.jpg" class="user_images"/><img src="chat/offline.png" class="online_green"></div>
                                                                <div class="contact_information">
                                                                <p><strong>Amit Pandey</strong><br>
                                                                 amit.pandey@silvereye.in</p>
                                                              </div>
                                                    
                                                     </div>
                                                  <!-----------/// FIRST ROW -------------->
                                                  
                                                    <!-----------/// FIRST ROW -------------->
                                                              <!------------//// FIRST ROW --------->
                                                     <div class="cheat_row_11">
                                                            <div class="small_images"><img src="chat/photo.jpg" class="user_images"/><img src="chat/offline.png" class="online_green"></div>
                                                                <div class="contact_information">
                                                                <p><strong>Ram Mishra</strong><br>
                                                                 ram@silvereye.in</p>
                                                              </div>
                                                    
                                                     </div>
                                                  <!-----------/// FIRST ROW -------------->
                                                  
                                                    <!-----------/// FIRST ROW -------------->
                                                              <!------------//// FIRST ROW --------->
                                                     <div class="cheat_row_11">
                                                            <div class="small_images"><img src="chat/photo.jpg" class="user_images"/><img src="chat/offline.png" class="online_green"></div>
                                                                <div class="contact_information">
                                                                <p><strong>Umesh Singh</strong><br>
                                                                 umesh@silvereye.in</p>
                                                              </div>
                                                    
                                                     </div>
                                                  <!-----------/// FIRST ROW -------------->
                                                  
                                                 
                                              
                                              </div>
                                              <!--------/// Chat Search End -------->
                                              </div>
                                         </div>
                        <!------------/// Chat Search Option End ------>
                          <!-------/// Chat Downarrow option--------->
                                         <div class="chat_search_11">
                                            
                                              <!-----//// Chat Search Content ----->
                                               <div class="chat_downarrow">
                                               <!--------------//// Chat Down Main Page ------------->
                                               <div class="chat_down_main">
                                                          <div class="chat_down_top">
                                                            <div class="chat_down_left">
                                                                <img style="height: 28px;" src="data:image/jpg;base64,<%=chat_img_bd %>" id="chat_id_bd" onerror="getAltChatImage(this.id)" />
                                                            </div>
                                                            <div class="chat_down_right">
                                                                <%=fname_bd %>
                                                                <span><%=mailid_bd %></span>
                                                            </div>

                                                            <div class="clear"></div>
                                                       </div>
                                                       <div class="clear"></div>
                                                          <ul>
                                                            <li class="chat_status_menu"><a style="cursor: pointer;" >Chat&nbsp;Status </a></li>
                                                            <li class="invites_menu"><a style="cursor: pointer;">Invites</a></li>
                                                            <li class="blocked_menu"><a style="cursor: pointer;">Blocked&nbsp;People </a></li>
                                                            <li class="share_your_menu"><a style="cursor: pointer;"> Share&nbsp;your&nbsp;status </a></li>
                                                       </ul>
                                                       <div class="clear"></div>
                                                       <div class="chat_out">Sign out of Chat</div>
                                                </div>
                                              <!------------------/// Chat Down Menu End ------------->
                                       
                                              </div>
                                              <!--------/// Chat Search End -------->
                                              <!------------/// Chat Sub menu ----------->
                                              <div class="chat_down_submenu">
                                              <!-------------// Chat Status box Stared here----------->
                                                <div class="chat_status">  
                                                   <!--------///Chat Haeding ---->
                                                   <div class="chat_subheading">
                                                      <div class="chat_main_menu"><img src="images/portlet-remove-icon.png"/></div>
                                                      <p>Chat Status </p>
                                                      <div class="clear"></div>
                                                    </div>
                                                   <!-----------/// Chat Heading End Here ----->
                                                   <!----------/// Chat Status Content ------->
                                                   <div class="chat_status_content">
                                                        
                                                        <ul>
                                                          <li><input type="radio" /><span>Online</span> <div class="online"></div></li>
                                                          <li><input type="radio" /><span>Offline</span> <div class="offline"></div></li>
                                                          <li><input type="radio" /><span>Busy</span> <div class="busy"></div></li>
                                                          <li><input type="radio" /><span>Away</span> <div class="away"></div></li>
                                                        </ul>
                                                   
                                                   
                                                   </div>
                                                   <!-----------/// Chat Status  End Here ----->
                                                 </div>
                                              <!-------------/// Chat Status Box End Here--------->
                                               <!-------------// Invites box Stared here----------->
                                                         <div class="Blocked_status">  
                                                   <!--------///Chat Haeding ---->
                                                   <div class="chat_subheading">
                                                      <div class="chat_main_menu"><img src="images/portlet-remove-icon.png"/></div>
                                                      <p>Invites </p>
                                                      <div class="clear"></div>
                                                    </div>
                                                   <!-----------/// Chat Heading End Here ----->
                                                   <!----------/// Chat Status Content ------->
                                                   <div class="chat_status_content">
                                                        <!----------/// Main ROW Content ---------->
                                                        <div class="chat_row_content">
                                                     <!-----------/// INVITE ROW FIRST STARED HERE ---------->
                                                              <div class="invite_row">
                                                     <!---------------/// INVITE LEFT PART -------->
                                                     <div class="invite_left">Rohit Tiwari</div>
                                                     <!--------------/// INVITE LEFT END HERE -------->
                                                     <!---------------/// INVITE RIGHT PART -------->
                                                     <div class="invite_right">
                                                     <a style="cursor: pointer;">Unblock</a>
                                                     </div>
                                                     <!--------------/// INVITE RIGHT END HERE -------->
                                                     <div class="clear"></div>
                                                     </div>
                                                     <!-------------/// INVITE Row END HERE -------------->
                                                        <!-----------/// INVITE ROW FIRST STARED HERE ---------->
                                                              <div class="invite_row">
                                                     <!---------------/// INVITE LEFT PART -------->
                                                     <div class="invite_left">Rohit Tiwari</div>
                                                     <!--------------/// INVITE LEFT END HERE -------->
                                                     <!---------------/// INVITE RIGHT PART -------->
                                                     <div class="invite_right">
                                                     <a style="cursor: pointer;">Unblock</a>
                                                     </div>
                                                     <!--------------/// INVITE RIGHT END HERE -------->
                                                     <div class="clear"></div>
                                                     </div>
                                                     <!-------------/// INVITE Row END HERE -------------->
                                                        <!-----------/// INVITE ROW FIRST STARED HERE ---------->
                                                              <div class="invite_row">
                                                     <!---------------/// INVITE LEFT PART -------->
                                                     <div class="invite_left">Rohit Tiwari</div>
                                                     <!--------------/// INVITE LEFT END HERE -------->
                                                     <!---------------/// INVITE RIGHT PART -------->
                                                     <div class="invite_right">
                                                     <a style="cursor: pointer;">Unblock</a>
                                                     </div>
                                                     <!--------------/// INVITE RIGHT END HERE -------->
                                                     <div class="clear"></div>
                                                     </div>
                                                     <!-------------/// INVITE Row END HERE -------------->
                                                        <!-----------/// INVITE ROW FIRST STARED HERE ---------->
                                                              <div class="invite_row">
                                                     <!---------------/// INVITE LEFT PART -------->
                                                     <div class="invite_left">Rohit Tiwari</div>
                                                     <!--------------/// INVITE LEFT END HERE -------->
                                                     <!---------------/// INVITE RIGHT PART -------->
                                                     <div class="invite_right">
                                                     <a style="cursor: pointer;">Unblock</a>
                                                     </div>
                                                     <!--------------/// INVITE RIGHT END HERE -------->
                                                     <div class="clear"></div>
                                                     </div>
                                                     <!-------------/// INVITE Row END HERE -------------->
                                                     </div>
                                                   <!----------/// Main Row Content End Here ---------->
                                                   <div class="clear"></div>
                                                   </div>
                                                   <!-----------/// Chat Status  End Here ----->
                                                   <div class="clear"></div>
                                                 </div>
                                              <!-------------/// Invites Box End Here--------->
                                                  <!-------------// Blocked People  box Stared here----------->
                                                         <div class="Invites_status">  
                                                   <!--------///Chat Haeding ---->
                                                   <div class="chat_subheading">
                                                      <div class="chat_main_menu"><img src="images/portlet-remove-icon.png"/></div>
                                                      <p>Blocked People</p>
                                                      <div class="clear"></div>
                                                    </div>
                                                   <!-----------/// Chat Heading End Here ----->
                                                   <!----------/// Chat Status Content ------->
                                                   <div class="chat_status_content">
                                                           <!----------/// Main ROW Content ---------->
                                                        <div class="chat_row_content">
                                                     <!-----------/// INVITE ROW FIRST STARED HERE ---------->
                                                              <div class="invite_row">
                                                     <!---------------/// INVITE LEFT PART -------->
                                                     <div class="invite_left">Rohit Tiwari</div>
                                                     <!--------------/// INVITE LEFT END HERE -------->
                                                     <!---------------/// INVITE RIGHT PART -------->
                                                     <div class="invite_right">
                                                     <a style="cursor: pointer;">Unblock</a>
                                                     </div>
                                                     <!--------------/// INVITE RIGHT END HERE -------->
                                                     <div class="clear"></div>
                                                     </div>
                                                     <!-------------/// INVITE Row END HERE -------------->
                                                        <!-----------/// INVITE ROW FIRST STARED HERE ---------->
                                                              <div class="invite_row">
                                                     <!---------------/// INVITE LEFT PART -------->
                                                     <div class="invite_left">Rohit Tiwari</div>
                                                     <!--------------/// INVITE LEFT END HERE -------->
                                                     <!---------------/// INVITE RIGHT PART -------->
                                                     <div class="invite_right">
                                                     <a style="cursor: pointer;">Unblock</a>
                                                     </div>
                                                     <!--------------/// INVITE RIGHT END HERE -------->
                                                     <div class="clear"></div>
                                                     </div>
                                                     <!-------------/// INVITE Row END HERE -------------->
                                                        <!-----------/// INVITE ROW FIRST STARED HERE ---------->
                                                              <div class="invite_row">
                                                     <!---------------/// INVITE LEFT PART -------->
                                                     <div class="invite_left">Rohit Tiwari</div>
                                                     <!--------------/// INVITE LEFT END HERE -------->
                                                     <!---------------/// INVITE RIGHT PART -------->
                                                     <div class="invite_right">
                                                     <a style="cursor: pointer;">Unblock</a>
                                                     </div>
                                                     <!--------------/// INVITE RIGHT END HERE -------->
                                                     <div class="clear"></div>
                                                     </div>
                                                     <!-------------/// INVITE Row END HERE -------------->
                                                        <!-----------/// INVITE ROW FIRST STARED HERE ---------->
                                                              <div class="invite_row">
                                                     <!---------------/// INVITE LEFT PART -------->
                                                     <div class="invite_left">Rohit Tiwari</div>
                                                     <!--------------/// INVITE LEFT END HERE -------->
                                                     <!---------------/// INVITE RIGHT PART -------->
                                                     <div class="invite_right">
                                                     <a style="cursor: pointer;">Unblock</a>
                                                     </div>
                                                     <!--------------/// INVITE RIGHT END HERE -------->
                                                     <div class="clear"></div>
                                                     </div>
                                                     <!-------------/// INVITE Row END HERE -------------->
                                                     </div>
                                                   <!----------/// Main Row Content End Here ---------->
                                                   </div>
                                                   <!-----------/// Chat Status  End Here ----->
                                                 </div>
                                              <!-------------/// Blocked People  Box End Here--------->
                                                <!-------------// Blocked People  box Stared here----------->
                                                         <div class="Share_status">  
                                                   <!--------///Chat Haeding ---->
                                                   <div class="chat_subheading">
                                                      <div class="chat_main_menu"><img src="images/portlet-remove-icon.png"/></div>
                                                      <p> Share your status </p>
                                                      <div class="clear"></div>
                                                    </div>
                                                   <!-----------/// Chat Heading End Here ----->
                                                   <!----------/// Chat Status Content ------->
                                                   <div class="chat_status_content">
                                                        
                                                   
                                                   <!----------/// Chat --------->
                                                     <!----------/// Main ROW Content ---------->
                                                        <div class="chat_row_content">
                                                     <!-----------/// INVITE ROW FIRST STARED HERE ---------->
                                                              <div class="invite_row">

                                                     <!---------------/// INVITE LEFT PART -------->
                                                     <div class="invite_left">Rohit Tiwari</div>
                                                     <!--------------/// INVITE LEFT END HERE -------->
                                                     <!---------------/// INVITE RIGHT PART -------->
                                                     <div class="invite_right">
                                                     <a style="cursor: pointer;">Unblock</a>
                                                     </div>
                                                     <!--------------/// INVITE RIGHT END HERE -------->
                                                     <div class="clear"></div>
                                                     </div>
                                                     <!-------------/// INVITE Row END HERE -------------->
                                                        <!-----------/// INVITE ROW FIRST STARED HERE ---------->
                                                              <div class="invite_row">
                                                     <!---------------/// INVITE LEFT PART -------->
                                                     <div class="invite_left">Rohit Tiwari</div>
                                                     <!--------------/// INVITE LEFT END HERE -------->
                                                     <!---------------/// INVITE RIGHT PART -------->
                                                     <div class="invite_right">
                                                     <a style="cursor: pointer;">Unblock</a>
                                                     </div>
                                                     <!--------------/// INVITE RIGHT END HERE -------->
                                                     <div class="clear"></div>
                                                     </div>
                                                     <!-------------/// INVITE Row END HERE -------------->
                                                        <!-----------/// INVITE ROW FIRST STARED HERE ---------->
                                                              <div class="invite_row">
                                                     <!---------------/// INVITE LEFT PART -------->
                                                     <div class="invite_left">Rohit Tiwari</div>
                                                     <!--------------/// INVITE LEFT END HERE -------->
                                                     <!---------------/// INVITE RIGHT PART -------->
                                                     <div class="invite_right">
                                                     <a style="cursor: pointer;">Unblock</a>
                                                     </div>
                                                     <!--------------/// INVITE RIGHT END HERE -------->
                                                     <div class="clear"></div>
                                                     </div>
                                                     <!-------------/// INVITE Row END HERE -------------->
                                                        <!-----------/// INVITE ROW FIRST STARED HERE ---------->
                                                              <div class="invite_row">
                                                     <!---------------/// INVITE LEFT PART -------->
                                                     <div class="invite_left">Rohit Tiwari</div>

                                                     <!--------------/// INVITE LEFT END HERE -------->
                                                     <!---------------/// INVITE RIGHT PART -------->
                                                     <div class="invite_right">
                                                     <a style="cursor: pointer;">Unblock</a>
                                                     </div>
                                                     <!--------------/// INVITE RIGHT END HERE -------->
                                                     <div class="clear"></div>
                                                     </div>
                                                     <!-------------/// INVITE Row END HERE -------------->
                                                     </div>
                                                   <!----------/// Main Row Content End Here ---------->
                                                   <!----------/// Chat End -------->
                                                   
                                                   </div>
                                                   <!-----------/// Chat Status  End Here ----->
                                                 </div>
                                              <!-------------/// Blocked People  Box End Here--------->
                                              </div>
                                              <!---------------//// Chat Down Sub Menu ---------->
                                         
                                         </div>
                        <!------------/// Chat Downarrow Option End ------>
                        </div>
                        
                        <div id="appendchatdiv"></div>                  
                        <!-- --CHAT BOX HERE -->
	<div class="main_chat_box">
		<div class="main_inner_box">
			<div class="overflow_chat">
				<div class="overflow_info">
					<div class="overflow_info_content"></div>
					<div class="overflow_info_bottom">
						<img src="images/chat_icon.png" />
						<div class="count_overflow"></div>
					</div>

				</div>
			</div>
			<div class="inner_chat_box"></div>
		</div>
	</div>
	<!-- CHAT BOX END HERE -->
                        
                                 <script>
       jQuery(function($) {
    $(window).on('resize', function() {
        var height = $(window).height()-90;
		//alert(height);
        console.log(height);
        $('#foo').height(height).split({ orientation:'horizontal', limit:50 });
		//$('#foo').css('height',height)
      //  $('.b').height((height / 2)+60);
		$('#b').css('height',(height/2-1)) ;
		//$('.chat_inner_content').height(height / 2);
    }).trigger('resize');
});

  
    </script>
<!-- chat end here -->










<!-------------------//// NEW SPILITTER END HERE ---------------> 

<!------------/// CREATE CALENDER POP STARED HERE ----------->
<!-------------/// EDITE NAME ------------>
<!--------// CREATE GROUP POP STRED HERE ----------->
<div class="group_name">
  <h1>Create Group<div class="cancel_top cancel_grop_top">X</div></h1>
  <table class="Sj">
    <tbody>
      <tr>
        <td class="Sl">Group Name</td>
        <td><input class="nr Sk" id="contact_gp_val" type="text" value=""></td>
      </tr>
      <tr>
          <td colspan="4">
                   <div onclick="createContactGroup()" class="save_right_name"> <div class="save_icon_name "></div> Save</div>
                   <div class="cancel_right_name cancel_grop"><div class="edite_icon_name"></div>Cancel</div>
          </td>
      </tr>
    </tbody>
  </table>
</div>
<!----------/// CREATE GROUP END HERE ---------------->
<input type="hidden" id="hid_edt_nm_pre" />
<input type="hidden" id="hid_edt_nm_first" />
<input type="hidden" id="hid_edt_nm_middle" />
<input type="hidden" id="hid_edt_nm_last" />
<input type="hidden" id="hid_edt_nm_suf" />
<div class="edite_name_box">
  <h1>Edit name<div class="cancel_top">X</div></h1>
  <table class="Sj">
    <tbody>
      <tr>
        <td class="Sl">Prefix:</td>
        <td><input id="edt_nm_pre" class="nr Sk" type="text" value=""></td>
      </tr>
      <tr>
        <td class="Sl">First:</td>
        <td><input id="edt_nm_first" class="nr Sk" type="text" value=""></td>
      </tr>
      <tr>
        <td class="Sl">Middle:</td>
        <td><input id="edt_nm_middle" class="nr Sk" type="text" value=""></td>
      </tr>
      <tr>
        <td class="Sl">Last:</td>
        <td><input id="edt_nm_last" class="nr Sk" type="text" value=""></td>
      </tr>
      <tr>
        <td class="Sl">Suffix:</td>
        <td><input id="edt_nm_suf" class="nr Sk" type="text" value=""></td>
      </tr>
      <tr>
          <td colspan="4">
                   <div class="save_right_name" onclick="return editNameSave()"> <div class="save_icon_name"></div> Save</div>
                   <div class="cancel_right_name" onclick="editNameCancel()"><div class="edite_icon_name"></div>Cancel</div>
          </td>
      </tr>
    </tbody>
  </table>
</div>

<!----/// CREATE POP HERE ----->
<!-- <div class="create_contact">
   <h1>Create Contact
     <div class="cancel_top cancel_grop_top create_top">X</div>
   </h1>
   --// SAVE AND CANCEl -------
   <table class="con_he_list_right_pop">
          <tbody>
              <tr>
              <td colspan="3" style="padding-left: 23px;text-align: left;"><span style="color: #fff; font-weight: bold;">Select Group:</span>
              <span id="gp_sel_span"><select id="gp_select"><option value="Personal Contacts">Personal Contacts</option></select></span>
               </td>
                <td colspan="1"><div onclick="saveContact()" class="save_right_pop">
                    <div  class="save_icon_pop"></div>
                    Save</div>
                    </td>
              </tr>
        </tbody>
    </table>
   ----/// SAVE END HERE --------
   <div class="right_con_part_pop" id="create_contact_form"> 
          
          ----------/// RIGHT CONTENT STARED HERE ------------
           <table>
           
            <tr>
              <td colspan="3" class="right_info_heading_pop">Personal Information</td>
            </tr>
            <tr>
              <td class="right_name_first_pop">Full Name </td>
              <td class="right_text_pop"><input class="cc_clear" type="text" id="fullname" style="width: 180px;" value="" />
                <div onclick="editNameOpen()" style="display: block;" class="edite_name">
                  <div style="display: block;" class="name_edit"></div>
                  Edit Name</div></td>
              <td rowspan="3"><img  src='images/contact_photo.png'/>
                <div class="save_chnage_pop">Change Image</div></td>
            </tr>
            <tr>
              <td class="right_name_first_pop">Company</td>
              <td><input type="text" class="cc_clear" id="company"  value=""/></td>
            </tr>
            <tr>
              <td class="right_name_first_pop">Job Title</td>
              <td><input type="text" class="cc_clear" id="job" value=""/></td>
            </tr>
            <tr class="right_name_first_pop">
              <td colspan="3" class="right_info_heading">Internet</td>
            </tr>
            <tr class="right_name_first_pop">
              <td class="right_name_first">email</td>
              <td colspan="2"><input id="email" class="cc_clear" type="text" value="" /></td>
            </tr>
           <tr class="right_name_first_pop">
              <td class="right_name_first">Web page address</td>
              <td colspan="2"><input id="web_page" class="cc_clear" type="text" value=""/></td>
            </tr>
            
            <tr class="right_name_first_pop">
              <td colspan="3" class="right_info_heading">Phone Number</td>
            </tr>
            <tr class="right_name_first_pop">
              <td class="right_name_first">Business</td>
              <td colspan="2"><input id="phone_work" class="cc_clear" type="text" value="" /></td>
            </tr>
            <tr class="right_name_first_pop">
              <td class="right_name_first">Home</td>
              <td colspan="2"><input id="phone_home" class="cc_clear" type="text" value="" /></td>
            </tr>
            <tr class="right_name_first_pop">
              <td class="right_name_first">Business Fax</td>
              <td colspan="2"><input id="phone_fax" class="cc_clear" type="text" value="" /></td>
            </tr>
            <tr class="right_name_first_pop">
              <td class="right_name_first">Mobile</td>
              <td colspan="2"><input id="phone_mob" class="cc_clear" type="text" value="" /></td>
            </tr>
            <tr class="right_name_first_pop">
              <td colspan="3" class="right_info_heading">Address</td>
            </tr>
            <tr class="right_name_first_pop">
              <td class="right_name_first">Business</td>
              <td colspan="2"><input id="addr_work" class="cc_clear" type="text"  value="" />
              <div style="display: none;" class="edite_address_edit_peole"><div class="name_edit_edit"></div> Edit Address</div>
              </td>
            </tr>
            <tr class="right_name_first_pop">
              <td class="right_name_first">Home</td>
              <td colspan="2"><input id="addr_home" class="cc_clear" type="text"  value="" />
              <div style="display: none;" class="edite_address_edit_peole"><div class="name_edit_edit"></div> Edit Address</div>
              </td>
            </tr>
             <tr class="right_name_first_pop">
              <td colspan="3" class="right_info_heading">Note</td>
            </tr>
            <tr class="right_name_first_pop">
              <td class="right_name_first">Add a Note</td>
              <td colspan="2"><textarea id="note" class="cc_clear" rows="5" cols="55"></textarea> </td>
            </tr>
          </table>
          ----------/// RIGHT CONTENT END -------- 
          
        </div>
        -/// RIGHT PART END HERE ------- 
        -------/// DETAILS VIEW END HERE ----------- 
        
      </div> -->

<!--------// EDITE NAME ----->      
<div class="create_contact_edit">
   <h1>Edit Contact
     <div class="cancel_top cancel_grop_top create_top_edit">X</div>
   </h1>
   <!----// SAVE AND CANCEl --------->
   <!-- <table class="con_he_list_right_pop_edit">
          <tbody>
              <tr>
                <td colspan="4">
                 <div class="save_right_edit"><div class="save_icon_edit"></div>Save</div>
                 <div class="cancel_edit"><div class="cancel_icon_edit">X</div>Cancel</div>
                 <div class="cancel_right_edit"><div class="edite_icon_edit"></div>Edit</div>
                 <div class="add_more_edit"><div class="add_more_icon_edit"></div>Add More</div>
                 
               
                    </td>
              </tr>
        </tbody>
    </table> -->
   <!------/// SAVE END HERE ---------->
   
   
  <!--  <div id="div_contact_disp_val"></div> -->
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
        <!---/// RIGHT PART END HERE ---------> 
        <!---------/// DETAILS VIEW END HERE -------------> 
        
      </div>
      
      <!---/// Upload photo ---------> 
        <div class="pop_for_image_upload" >
                 <h1>Change Your Image <span>X</span></h1>
                      
                      
                       <form id="uploadImage" >
			         
			       
                      <table>
                      
                           <tbody><tr>
                              <td colspan="2"> <input type="file" id="upl1" name="upl1"  /></td>
                           </tr>
                            <tr>
 							<td colspan="2" style="  font-size: 11px; ">
 							Accepted image size is up to 100KB and File type must be png or jpg only. </td>
                           </tr>
                           <tr>
                              <td></td>
                              <td><input type="button" onclick="changeLDAPImg()" value="Upload" class="file_upload search_button"></td>
                           
                           </tr>
                      </tbody></table>
                  </form>
         </div>
      <div class="web_dialog_overlay contact_blur" ></div>
      
<!--------/// EDITE NAME END HERE -------->
<!---------// SHARING PAGES STARTED---------->
<%-- <div class="sharing_mange">
   <h1>Share Contacts <div class="mange_can_top">X</div></h1>
   <div class="table_append">
                   <table class="append_tr">
                       
                       <tr>
                           <td>Contacts</td>
                           <td >
                           <%
                           WebmailClient webmailClient=(WebmailClient)request.getAttribute("webmailClient");
                           FolderClient folderClient=(FolderClient)request.getAttribute("folderClient");
                           HttpSession hs_c=request.getSession();
                           String id=(String)hs_c.getAttribute("id");
                           String pass=(String)hs_c.getAttribute("pass");
           					String ldapurl=(String)hs_c.getAttribute("ldapurl");
           				String ldpabase=(String)hs_c.getAttribute("ldapbase");
                           String path="/"+id+"/Contacts";
                    		GetFolderResponse folderResponse = folderClient.getFolderRequest(path,id);
                    		List<Folder> folderList = folderResponse.getGetFoldersByParentFolder().getFolderListResult().getFolderList();
                           %>
                           <select id="confldr" >
                           <%
                          
           				//System.out.println("!!!!!!!!!!!! ldap dn="+ldpabase);
           				for(Folder fEntry : folderList)
           				{
           					if(!(fEntry.getFolderName().equalsIgnoreCase("Personal Contacts") || fEntry.getFolderName().equalsIgnoreCase("Collected Contacts") || fEntry.getFolderName().equalsIgnoreCase("Directory")))
           	        	 {
           					%>
           					<option value="/Contacts/<%=fEntry.getFolderName() %>"><%=fEntry.getFolderName() %></option>
           					<%
           	        	 }
           				}
                           %>
                           </select>
                          </td>
                       </tr>
                      
                      <tr>
                           <td>Share Email Id</td>
                           <td >
                           <select id="shareid">
                           <%
                          
           			
           				GetVCFLdapDirResponse getdirres=webmailClient.getLdapDirectory(ldapurl, id, pass, ldpabase);
           		 		List<VCFLdapDirAtt> ldapDirList=getdirres.getGetVCFLdapDirByParentFile().getVCFLdapDirListResult().getVCFLdapDirList();
           				for(VCFLdapDirAtt ulst : ldapDirList)
           				{
           					if(!ulst.getContactEmail().equalsIgnoreCase(id))
           					{
           					%>
           					<option value="<%=ulst.getContactEmail() %>"><%=ulst.getContactEmail() %></option>
           					<%
           					}
           				}
                           %>
                           </select>
                           </td>
                       </tr>
                       
                   </table>
                   <div class="clear"></div>
   
        </div>
          
                 <div>
                       
                       <div class="cancel_share mange_can">Cancel</div>
                       <div onclick="shareConFldr()" class="send_share">Share</div>
            
  </div>
                 
                 
</div> --%>

        <!----------/// CALENDER SHARE POP STARED HERE ----------->
<div class="sharing_mange">
<%--    <h1>Sharing<div class="mange_can_top">X</div></h1>

   <div class="table_append">
   <table class="append_tr">
    <tr class="share_bottom">
                           <td class="td_head" colspan="2">Contacts</td>
                       </tr>
                       <tr>
                           <td colspan="2" class="own_row">
                            <span class="you_share">
                            
                            <%
                         
                    		GetFolderResponse folderResponse = folderClient.getFolderRequest(path,id,pass);
                    		List<Folder> folderList = folderResponse.getGetFoldersByParentFolder().getFolderListResult().getFolderList();
                           %>
                           <select style="  padding: 5px;  width: 448px;" id="confldr" >
                           <%
                          
           				//System.out.println("!!!!!!!!!!!! ldap dn="+ldpabase);
           				for(Folder fEntry : folderList)
           				{
           					if(!(fEntry.getFolderName().equalsIgnoreCase("Personal Contacts") || fEntry.getFolderName().equalsIgnoreCase("Collected Contacts") || fEntry.getFolderName().equalsIgnoreCase("Directory")))
           	        	 {
           					%>
           					<option value="/Contacts/<%=fEntry.getFolderName() %>"><%=fEntry.getFolderName() %></option>
           					<%
           	        	 }
           				}
                           %>
                           </select>
                            
                            
                             </span>
                             
                            </td>
                        
                       </tr>
   </table>
                   <table class="append_tr">
                      <!-- <tr>
                           <td colspan="2">Link to share (only accessible by collaborators)</td>
                       </tr>-->
                   <!--    <tr>
                           <td colspan="2"><input type="text"  class="link_share"/></td>
                       </tr>-->
                       
                       <tr class="share_bottom">
                           <td class="td_head" colspan="2">Who has access</td>
                       </tr>
                       <tr>
                           <td colspan="2" class="own_row">
                            <span class="you_share"><%=fname_bd %> <!---<span class="email_share">hari@silvereye.co</span> --></span>
                               <span class="text_right">Is Owner </span>
                              
                            
                            
                            </td>
                        
                       </tr>
                       <tr >
                         <td colspan="2" class="invite_people_list">
                             <div class="invite_people_box">
                             
                             
                             
                             <div class="clear"></div>
                             </div>
                         
                         
                         </td>
                       
                       </tr>
                       <tr class="share_bottom">
                           <td class="td_head" colspan="2">Invite people</td>
                       </tr>
               
                       <tr>
                           <td><input type="text" id="share_input"  class="initive_people" /></td>
                           <td>
                              <div class="can_edit">
                                     
                                      <div class="can_edite">
                                           <select>
                                               <option class="select_val">Can View </option>
                                               <option class="select_val" >Can Edit </option>
                                               <option class="select_val">Can Mange </option>
                                           </select>
                                      </div>
                                       <div class="share_more">Add More</div>
                                     
                              </div>
                          </td>
                       </tr>
                   </table>
                   <div class="clear"></div>
   
        </div>
     <!--     <table>
            <tr>
           <td colspan="2"><input type="checkbox" />Notify people via email<a href="#">Add message</a></td>
          </tr>
   </table> -->
                 <div class="share_save_close">
                 <div class="your_self"><input type="checkbox"/><a href="#">Notify Via Email </a></div>
                    <div class="share_save_box">
                              <div class="cancel_share mange_can">Cancel</div>
                               <div class="send_share cancel_share">Save</div>
                               <div class="clear"></div>
                    </div>
                 <div class="clear"></div>
                 </div>
                 <div class="clear"></div>
                 
           --%>       
</div>
<!-------/// IMPORT POP STARED HERE ---------->
 <%
 GetFolderResponse folderResponse2 = folderClient.getFolderRequest(path,id,pass);
 List<Folder> folderList2 = folderResponse2.getGetFoldersByParentFolder().getFolderListResult().getFolderList();
                           %>
<div class="import_con">
<h1>Import contacts
     <div class="import_can_top">x</div>
</h1>
<div class="import_details">

<p>Please select a vCard(.vcf) file to upload:</p>
<p><input type="file" id="upl_con" name="upl_con" /></p>
<div class="submit_right">
    <!-- <div class="import search_button" onclick="importVCF()">Import</div> -->
    <div class="import search_button" >Import</div>
    <div class="cancel_import search_button">Cancel</div>
</div>
<div class="clear"></div>
</div>
<div class="clear"></div>
</div>
<!---------/// IMPORTANT END HERE --------------->
<!-------/// EXPORT POP STARED HERE ---------->
<div class="export_con">
<h1>Export contacts
     <div class="export_can_top">x</div>
</h1>
<div class="export_details">
<!---// DETAILS --------->
<div class="export_details">
<div class="ex_he">Which contacts do you want to export?</div>
<!-- <div class="ex_details"><input type="radio" value="contact" name="source" >Selected contacts (<span id="no_cont">0</span>)</div> -->
<div class="ex_details"><!-- <input type="radio" value="group" name="source" > -->Select group 


<select id="export_combo"  name="export_combo">
	<option value="/Contacts/Personal Contacts">Personal Contacts</option>
<%

           				for(Folder fEntry : folderList2)
           				{
           					if(!(fEntry.getFolderName().equalsIgnoreCase("Personal Contacts") ))
           	        	 {
           					%>
           					<option value="/Contacts/<%=fEntry.getFolderName() %>"><%=fEntry.getFolderName() %></option>
           					<%
           	        	 }
           				}
                           %>

</select> </div>
<!-- <div class="ex_details"><input type="radio" value="all" name="source" checked="true">All contacts</div>  -->

 </div>
<!---// DETAILS END --------->
<div class="submit_right">
   <!--  <div class="export search_button" onclick="exportVCF()">Export</div> -->
    <div class="export search_button" >Export</div>
    <div class="cancel_export search_button">Cancel</div>

</div>
<div class="clear"></div>
</div>
<div class="clear"></div>
</div>
<!------------/// CALENDER SHARE POP END HERE ------------->

    
<!----------/// EDITE ADDRESS STRED HERE  ---------------->
<div class="address_name_box">
  <h1>Edit Address<div class="cancel_top">X</div></h1>
  <table class="Sj">
    <tbody>
      <tr>
        <td class="Sl">Street:</td>
        <td><textarea id="edt_addr_street"></textarea></td>
      </tr>
      <tr>
        <td class="Sl">City:</td>
        <td><input id="edt_addr_city" class="nr Sk" type="text" value=""></td>
      </tr>
      <tr>
        <td class="Sl">State:</td>
        <td><input id="edt_addr_state" class="nr Sk" type="text" value=""></td>
      </tr>
      <tr>
        <td class="Sl">Country:</td>
        <td><input id="edt_addr_country" class="nr Sk" type="text" value=""></td>
      </tr>
      <tr>
        <td class="Sl">ZIP/Postal Code:</td>
        <td><input id="edt_addr_zip" class="nr Sk" type="text" value=""></td>
      </tr>
      
      <tr>
          <td colspan="4">
                   <div class="save_right_name"> <div class="save_icon_name"></div> Save</div>
                   <div class="cancel_right_name"><div class="edite_icon_name"></div>Cancel</div>
          </td>
      </tr>
    </tbody>
  </table>
</div>
<!---------// SHARING PAGES END---------->

<!-------/// CREATE POP END HERE ------->
  
  

  
<!------------/// EDITE STARED HERE --------->



           
</body>
</html>