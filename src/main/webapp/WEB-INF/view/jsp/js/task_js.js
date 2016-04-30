// JavaScript Document

jQuery(document).ready(function() {
	
////NEW CODE FOR TASKS STRED HERE 
	  var getWidth = $(window).width()-322;
//	  alert(getWidth);
	//  $('.table_content').css('width',getWidth)
  /// NEW TASKS END HERE 
	
	  $(document.body).on('change', '.count_input' ,function(){
			var pr = $(this).val();
			if(!isNaN(pr))
				{
					var pa = parseInt(pr);
					if(pa <1)
					{
						$(this).val("0");
						$('.pro_status').val("0");
					}
					else if(pa >0 && pa <100)
					{
						$('.pro_status').val("50");
					}
					else
					{
						$(this).val("100");
						$('.pro_status').val("100");
					}
					
				}
			else
				{
					$(this).val("0");
					$('.pro_status').val("0");
				}
//			alert("This is onchange event");
		});
	  $(document.body).on('change', '.pro_status' ,function(){
			var pr = $(this).val();
			if(pr != "Deferred")
			{
				$('.count_input').val(pr);
			}
			
		});
	/// TEST STARED HERE 
	$(document.body).on('click', '.task_icon' ,function(){
			
			var uid=$(this).attr('id');
			 $('.web_dialog_overlay').show(); 
			 task_del_conf("confirm","Do you want to delete this task ?",uid);
		});
	
	  function task_del_conf(type,msg,uid) {
		   	//alert(id);
		         var n = noty({
		           text        : msg,
		           type        : type,
		           theme       : 'relax',
		           dismissQueue: false,
		           layout      : 'center',
		           theme       : 'defaultTheme',
		           buttons     : (type != 'confirm') ? false : [
		               {addClass: 'btn btn-primary', text: 'Ok', onClick: function ($noty) {
		   				
		                 $noty.close();
		                 $("#action_gif").css("display","block");
		                 $.ajax({
		     				type : "GET",
		     				url : "deleteTask",
		     				data : {'uid':uid},
		     				contentType : "application/json",
		     				success : function(data) {
		     					$("#action_gif").css("display","none");
		     					var obj = jQuery.parseJSON(data);
		     					if(obj.success == "true")
		     					{
		     						loadTaskList();
		     						showmsg("alert","Task deleted successfully !")
		     					}
		     					else
		     					{
		     						showmsg("alert","Sorry task not deleted !")
		     					}
		     					
		     					
		     				},
		     				error: function (xhr, ajaxOptions, thrownError) {
		     			        alert(xhr.status);
		     			      }
		     		    }) ;
		                 $('.web_dialog_overlay').hide(); 
		               }
		               },
		               {addClass: 'btn btn-danger', text: 'Cancel', onClick: function ($noty) {
		                   $noty.close();
		                   $('.web_dialog_overlay').hide(); 
		               
		               }
		               }
		           ]
		       });
		      
		           
		           //console.log(type + ' - ' + n.options.id);
		           return n; 
		            
		       }
	  
	  $(document.body).on('click', '.delete_all_tasks' ,function(){
			
//			alert("delete all task");
			
			var searchIDs = $(".task_check_box > input:checkbox:checked").map(function(){
		        return $(this).val();
		    }).toArray();
			    
//			alert(searchIDs);
			$('.web_dialog_overlay').show(); 
			task_del_sel_conf("confirm","Do you want to delete this task ?",searchIDs.toString());
			
		});
	  
	  
	  function task_del_sel_conf(type,msg,uid) {
//		   	alert(id);
		         var n = noty({
		           text        : msg,
		           type        : type,
		           theme       : 'relax',
		           dismissQueue: false,
		           layout      : 'center',
		           theme       : 'defaultTheme',
		           buttons     : (type != 'confirm') ? false : [
		               {addClass: 'btn btn-primary', text: 'Ok', onClick: function ($noty) {
		   				
		                 $noty.close();
		                 $("#action_gif").css("display","block");
		                 $.ajax({
		     				type : "GET",
		     				url : "deleteSelectedTask",
		     				data : {'uid':uid},
		     				contentType : "application/json",
		     				success : function(data) {
		     					$("#action_gif").css("display","none");
		     					var obj = jQuery.parseJSON(data);
		     					if(obj.success == "true")
		     					{
		     						$('.delete_all_tasks').hide();
		     						$('.select_all').removeAttr('checked','checked');
		     						loadTaskList();
		     						showmsg("alert","Selected Tasks deleted successfully !")
		     					}
		     					else
		     					{
		     						showmsg("alert","Sorry task not deleted !")
		     					}
		     					
		     					
		     				},
		     				error: function (xhr, ajaxOptions, thrownError) {
		     			        alert(xhr.status);
		     			      }
		     		    }) ;
		                 $('.web_dialog_overlay').hide(); 
		               }
		               },
		               {addClass: 'btn btn-danger', text: 'Cancel', onClick: function ($noty) {
		                   $noty.close();
		                   $('.web_dialog_overlay').hide(); 
		               
		               }
		               }
		           ]
		       });
		      
		           
		           //console.log(type + ' - ' + n.options.id);
		           return n; 
		            
		       }
	  
	  $(document.body).on('click', '#nextpage' ,function(){
		  var pagecount = parseInt($('#pagecount').val());
		  var taskcount = parseInt($('#taskcount').val());
//		  alert("next page "+ pagecount +" , taskcount : " + taskcount );
		  if(taskcount > pagecount*20)
		  {
//			  alert("call for next page " + pagecount);
			  pagecount = pagecount +1;
			  $("#action_gif").css("display","block");
			  pageTaskList(pagecount);
		  }
		  
	  });
	  $(document.body).on('click', '#privpage' ,function(){
		  var pagecount = parseInt($('#pagecount').val());
		  var taskcount = parseInt($('#taskcount').val());
//		  alert("next page "+ pagecount +" , taskcount : " + taskcount );
		  if(pagecount > 1)
		  {
//			  alert("call for pre page");
			  pagecount = pagecount - 1;
			  $("#action_gif").css("display","block");
			  pageTaskList(pagecount);
		  }
		  
	  });
	  
	  
	  
	  
	  function pageTaskList(pagecount)
	  {
//	  	alert("inside function pagecount : " + pagecount);
	  	
	  	$.ajax({
	  		type : "GET",
	  		url : "loadTaskList",
	  		data : {'pagecount':pagecount},
	  		contentType : "application/json",
	  		success : function(data) {
	  			
	  			var res = jQuery.parseJSON(data);
//	  			alert("Response Data : " +data);
	  			var totaltask = res.totaltask;
	  			var pagecount = res.pagecount;
	  			
	  			if(totaltask <= 20)
	  			{
	  				$('#totaltask').html("1-"+totaltask+" of "+totaltask);
	  			}
	  			else
	  			{
	  				$('#pagecount').val(pagecount);
	  				$('#taskcount').val(totaltask);
	  				if(pagecount == 1)
					{
						$('#totaltask').html("1 -  20 of "+totaltask);
					}
	  				else if(totaltask >= (pagecount)*20)
					{
	  					$('#totaltask').html((pagecount-1)*20+1 +" - "+pagecount*20 + " of "+totaltask);
					}
	  				else
					{
	  					$('#totaltask').html((pagecount-1)*20+1 +" - "+totaltask + " of "+totaltask);
					}
	  				
	  			}
	  			
	  			var obj = res.tasklist;
//	  			alert("Response Data : " +obj);
	  			$(".table_content").html("");
	  			$.each(obj, function(i, item) {
	  				row = $("<tr id='row_"+item.uid+"'></tr>");
	  				col1 = $("<td class='task_check_box new_chkbox'><input type='checkbox' name='wpmm[]' value='"+item.uid+"'></td>");
	  			   	col2 = $("<td class='left_text'>"+item.detail+"</td>");
	  			   	col3 = $("<td>"+item.status+"</td>");
	  			   	col4 = $("<td>"+item.endDate+"</td>");
	  			   	col5 = $("<td class='new_chkbox'>"+item.modifyDate+"</td>");
	  			   	col6 = $("<td class='task_check_box new_chkbox'><div id='del_"+item.uid+"' class='task_icon'></div></td>");
	  			   	
	  			   	row.append(col1,col2,col3,col4,col5,col6).appendTo(".table_content");  
	  			});
	  			$("#action_gif").css("display","none");
	  		},
	  		error: function (xhr, ajaxOptions, thrownError) {
	  	        alert(xhr.status);
	  	        alert(thrownError);
	  	      }
	      }) ;
	  }
	  
	  
	  
	  
	  
	  
	  
	  
	  $(document.body).on('click','.date_stared',function(event){
			 
		   // alert('Hi');
			
				var leftPos = $(this).offset().left;
				var topPos = $(this).offset().top;
//				 alert(leftPos);
//				 alert(topPos);
				$('input.sel_val').removeClass('sel_val');
				$('.remi_date_opt').css('left',leftPos);
				$('.remi_date_opt').css('top',topPos+30);
				$(this).addClass('sel_val');
				$('.dymaic_am').show();
				 
				 event.stopPropagation();
				 
		 });
	  
	  
	  $(document.body).on('click','table.table_content >tbody >tr',function(event){
			  
//		  		alert("Hello to all");
		  		var uid=$(this).attr('id');
		  		$("#action_gif").css("display","block");
//		  		alert('This is Row id : ' +uid);
		  		$.ajax({
     				type : "GET",
     				url : "getTaskDetailByUid",
     				data : {'uid':uid},
     				contentType : "application/json",
     				success : function(data) {

//     					alert(data);
     					var obj = jQuery.parseJSON(data);
     					$("#etask_detail").val(obj.detail);
     					$("#etask_startdate").val(obj.startDate);
     					$("#etask_starttime").val(obj.startTime);
     					$("#etask_enddate").val(obj.endDate);
     					$("#etask_endtime").val(obj.endTime);
     					$("#etask_progress").val(obj.progress);
     					$("#etask_desc").val(obj.taskDesc);
     					$("#etask_uid").val(obj.uid);
     					
     					$('#etask_status option[value="'+obj.status+'"]').attr('selected', true);
     					$('#etask_priority option[value="'+obj.priority+'"]').attr('selected', true);
     					$("#action_gif").css("display","none");
     					$('.active_tasks').removeClass('active_tasks');
     					$('.calender_pop_new').show();
     					$('.web_dialog_overlay').show();
     					$(this).addClass('active_tasks_new');
     					$('.select_all').removeAttr('checked');
     					$('.new_chkbox >input').removeAttr('checked');
     					event.stopPropagation();
     					
     				},
     				error: function (xhr, ajaxOptions, thrownError) {
     			        alert(xhr.status);
     			      }
     		    }) ;
			  });
	  
	  $('#select1').change(function(){
          var getValue = $(this).val();                                                          
          $('.control').css('width',getValue+'%');
			$('.vol-box').text(getValue+'%');
	 });
	  
	  
	  
	  /// NEW TEST 
	  
	  $('.select_all').click(function(){
		   
	      if($(this).prop('checked'))
		  {
		//	 alert('This is Checked Box');  
			 $('.task_check_box input').attr('checked','checked');
			 $('table.table_content>tbody>tr').addClass('active_tasks');
			  $('.delete_all_tasks').addClass('all_checked_delete');
			  $('.delete_all_tasks').show();
		  }
		  else
		  {
			//   alert('This is Not Checked ');
			   $('.task_check_box input').removeAttr('checked','checked');
			   $('table.table_content>tbody>tr').removeClass('active_tasks');
			   $('.delete_all_tasks').removeClass('all_checked_delete');
			   $('.delete_all_tasks').hide();
		  }
	   
	   });
	   
	  ///    22222
	  
	  $(document.body).on('click','.new_chkbox',function(event){	   
	       // alert('Hi-22');
			var count = $(".task_check_box >[type='checkbox']:checked").length;
			var rows = $(".task_check_box >[type='checkbox']").length;
//			 alert(count + " total "+ rows);
			var checkBox =  $(this).children();
			$('.calender_pop_new').hide();
			$('.web_dialog_overlay').hide();
		    $('.active_tasks_new').removeClass('active_tasks_new');
	        if( checkBox.prop('checked')){
	
	        //  alert('This Checked ');
	            $(this).parent().addClass('active_tasks');
	
	           // $(this).parent().removeClass('active_tasks');
				
	            
	        	if(count>=1)
				{
				   $('.delete_all_tasks').show();
				}
				else 	if(count == 0)
				{
			        $('.delete_all_tasks').hide();
					
				}
	        	if(rows == count+1)
        		{
	        		$('.select_all').attr('checked','checked');
        		}
            }
           else
        	   {
        	   
//        	   alert("hii");
        	
    		        //  alert('This is Not Checked');
    		           $(this).parent().removeClass('active_tasks');
    		           $('.active_tasks_new').removeClass('active_tasks_new');
               
    		           if(rows == count+1)
    	        		{
    		        		$('.select_all').removeAttr('checked','checked');
    	        		}
    		           if(count>=1)
    					{
    					   $('.delete_all_tasks').show();
    					}
    					else 	if(count == 0)
    					{
    				        $('.delete_all_tasks').hide();
    						
    					}         	   
        	   
        	   
        	 
        	   }
          event.stopPropagation();	
			
	   });	
	  
	  
	  
	  
	  /// 33333333333
	// ROW NEW CLICK 
	  /* $('.table_content >tbody >tr').click(function(event){
         alert('hi-333')
		
		
		 if($('.new_chkbox').prop('checked'))
			 {
			     alert('This checked Agains !');
			     $(this).addClass('active_tasks');
			 
			 }
		 else
			 {
			           alert('This Not checked Agains !');
			           $('.calender_pop_new').show();
			   		$('.web_dialog_overlay').show();
			 
			 }
		
		
		event.stopPropagation();
		
		});*/
		
		
		$('span.new_tasks_box').click(function(){
			//alert('Hi-33')
			  $('.active_tasks_new').removeClass('active_tasks_new');
		$('.calender_pop_new').hide();
		$('.web_dialog_overlay').hide();
		
		});
		
		$('.cancel_cal_new').click(function(){
			  $('.active_tasks_new').removeClass('active_tasks_new');
		$('.calender_pop_new').hide();
		$('.web_dialog_overlay').hide();
		
		});
	  
	  
	  
	  
	  /// NEW TEST 
	  
	  
	  
	  
	
	/// TEST END HERE 	
	
	
	
	
	/// DELETE TASKS ROW 
	$(document.body).on('click','.task_icon',function(){
		
		      pop('Are You Sure !')
			  $(this).parent().parent().hide();
		
		
		});
		
   /// DELET ON CHECK BOX 
   /*	$(document.body).on('click','.task_check_box >input',function(){	   
	       // alert('Hi');
			var count = $(".task_check_box >[type='checkbox']:checked").length
			 //alert(count);
			if(count>=1)
			{
			$('.delete_all_tasks').show();
			}else if(count == 0){
				 $('.delete_all_tasks').hide();
				
				}
				
		
					   

				
				
			
	   });	*/	
   
	
	
	
	
	
	
	// WINDOW HIGHT STARED HERE 
	
	var task_height = $(window).height();
	//alert(task_height);
	$('.task_content').css('height',task_height-119);
	
	
	/// MY CONTACT STARED HERE
	   	$(document.body).on('click','.my_tasks',function(){
						//$('.my_calender_content').slideToggle("slow");
						if($('.my_tasks_content').css('display')=='block')
						{
							$('.my_tasks_content').hide();
							$('.my_tasks').addClass('bottom_arrow');
						}
						else
						{
							$('.my_tasks_content').show();
							$('.my_tasks').removeClass('bottom_arrow');
						 }
		
		});
		
		/// MY CONTACT END HERE
		
		
		
		
		$(document.body).on('click','.first_cal_option_1',function(){

	                if($('.calender_pop').css('display')=='none')
				         {
					
					         $('.calender_pop').css('display','block');
					         $('.web_dialog_overlay').show();
					
					
			            }
			});
			
			
				$(document.body).on('click','.pop_header >span',function(){
				   $('.calender_pop').hide(500);
				   $('.web_dialog_overlay').hide();
		
	 });
	
		$(document.body).on('click','.cancel_cal',function(){
				 $('.calender_pop').hide(500);
				 $('.web_dialog_overlay').hide();
		
    });
	
	//var pro_val = $('.vol-box').html();
	//alert(pro_val);
		$(document.body).on('ready','.table_content',function(event){
			 
			   alert("table_content is ready");
					 
			 });
	
	
	
	
	
	
	});

//jQuery(document).ready(function() {
//	alert("on ready function");
//	loadTaskList();
//});

function loadTaskList()
{
//	alert("inside function");
	var pagecount  = $('#pagecount').val();
	$.ajax({
		type : "GET",
		url : "loadTaskList",
		data : {'pagecount':pagecount},
		contentType : "application/json",
		success : function(data) {
			
			var res = jQuery.parseJSON(data);
//			alert("Response Data : " +data);
			var totaltask = res.totaltask;
  			var pagecount = res.pagecount;
  			if(totaltask == 0)
  			{
  				$('#totaltask').html("0 - 0 of 0");
  			}
  			else if(totaltask <= 20)
  			{
  				$('#totaltask').html("1-"+totaltask+" of "+totaltask);
  			}
  			else
  			{
  				$('#pagecount').val(pagecount);
  				$('#taskcount').val(totaltask);
  				if(pagecount == 1)
				{
					$('#totaltask').html("1 -  20 of "+totaltask);
				}
  				else if(totaltask >= (pagecount)*20)
				{
  					$('#totaltask').html((pagecount-1)*20 +" - "+pagecount*20 + " of "+totaltask);
				}
  				else
				{
  					$('#totaltask').html((pagecount-1)*20 +" - "+totaltask + " of "+totaltask);
				}
  				
  				
  			}
			var obj = res.tasklist;
//			alert("Response Data : " +obj);
			$(".table_content").html("");
			$.each(obj, function(i, item) {
				row = $("<tr id='row_"+item.uid+"'></tr>");
				col1 = $("<td class='task_check_box new_chkbox'><input type='checkbox' name='wpmm[]' value='"+item.uid+"'></td>");
			   	col2 = $("<td class='left_text'>"+item.detail+"</td>");
			   	if(item.status == "Deferred")
		   		{
			   		col3 = $("<td>Deferred</td>");
		   		}
			   	else if(item.status == "100")
		   		{
			   		col3 = $("<td>Completed</td>");
		   		}
			   	else if(item.status == "50")
		   		{
			   		col3 = $("<td>In Progress</td>");
		   		}
			   	else
		   		{
			   		col3 = $("<td>Not Started</td>");
		   		}
			   	 
			   	col4 = $("<td>"+item.endDate+"</td>");
			   	col5 = $("<td class='new_chkbox'>"+item.modifyDate+"</td>");
			   	col6 = $("<td class='task_check_box new_chkbox'><div id='del_"+item.uid+"' class='task_icon'></div></td>");
			   	
			   	row.append(col1,col2,col3,col4,col5,col6).appendTo(".table_content");  
			});
			$("#action_gif").css("display","none");
		},
		error: function (xhr, ajaxOptions, thrownError) {
	        alert(xhr.status);
	        alert(thrownError);
	      }
    }) ;
}
