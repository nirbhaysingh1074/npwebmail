// JavaScript Document
jQuery(document).ready(function() {

$(document.body).on('click','.cal_set_top',function(){
	 $('.cal_setting').hide();
	
	});
$(document.body).on('click','.cal_setting_opt',function(){
	var calr=$("#hid_cal_file_name").val();
//	alert("cal file name : " + calr);
	document.getElementById('action_gif').style.display= 'block';
	$.ajax({
			type : "GET",
			url : "getCalendarDetail",
			data : {'calendar':calr},
			contentType : "application/json",
			success : function(data) {
				
				var obj = jQuery.parseJSON(data);
				$("#cal_name").val(obj.calname);
				if(calr == "default.ics")
				{
					$("#cal_name").prop('disabled','disabled');
				}
				else
				{
					$("#cal_name").removeProp('disabled');
				}
				$("#cal_location").val(obj.location);
				$("#cal_desc").val(obj.description);
				$("#owner_id").html(obj.ownerid);
				document.getElementById('action_gif').style.display= 'none';
//				alert("response data "+ data);
				$('.cal_setting').show();
				$('.web_dialog_overlay').show();
					
			},
			error: function (xhr, ajaxOptions, thrownError) {
		        alert(xhr.status);
		      }
	    }) ;
	
	
	});

$(document.body).on('click','.add_notification',function(){
	
	$('.cal_notification').append('<div class="cal_row"><div class="clear"></div><select class="cal_email cal_setting_select"><option value="1">Email</option><option value="3">Pop-up</option></select><input  class="text_cal_notifi" type="text"/><select class="cal_time cal_setting_select"><option >minutes</option><option >hours</option><option >days</option><option >weeks</option></select> <span> before each event </span> <span class="cal_set_del"><img src="images/tool.png" /></span></div>');
	
	
	});	
	
	$(document.body).on('click','.cal_set_del',function(){
		
//						if (confirm("Are you sure?")) {
//							 your deletion code
//						}
						$(this).parent().remove();
						return false;
		});
		
});