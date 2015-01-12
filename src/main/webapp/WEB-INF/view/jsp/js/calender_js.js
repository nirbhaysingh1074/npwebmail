// JavaScript Document
jQuery(document).ready(function() {
	
	 
	
	
	/// TEST 
	
	/// CALENDER TOP OPTION STRAED HERE
	
	
	// OPTION 1 STARTD
	$('.first_cal_option').click(function(){
		
		         if($('.create_cal').css('display')=='none')
				 {
					 
					 $('.web_dialog_overlay').show();
					 $('.create_cal').css('display','block');
					 
				  }
		
		});
		
		$('.create_cal >.pop_header >span').click(function(){
			
			     
			        $('.web_dialog_overlay').hide();
					 $('.create_cal').hide();
			
			});
			
	  $('.cancel_cal_1').click(function(){
			
			     
			        $('.web_dialog_overlay').hide();
					 $('.create_cal').hide();
			
	  });
	  
	  
	  
     $('.save_cal_1').click(function(){
		 
		      // alert(displayColor);
			 //  alert();
			  // alert(applyOptions);
			  // alert(colorInput.value);
			  //[r,g,b] = displayColor;
			  var cre_cal_nam = $('.dis_name').val();
			 // alert(cre_cal_nam);
			   
			 //var cal_col_di = $('.color_calender').css('background',displayColor);
			   //find('.sp-preview-inner').addClass('hi');
			   $('.my_calender_content >ul').append('<li><div class="color_calender" style="background:'+ displayColor +';"></div><span>'+ cre_cal_nam +'</span><div class="cal_option"><img src="images/cal-open.png"></div><div class="clear"></div></li>');
			    $('.create_cal').hide();
				$('.dis_name').val('');
				  $('.web_dialog_overlay').hide();
			  // alert(rgb);
		 
		 });

	
	
	// OPTION 2 STARTD
	
	
	
	
	// OPTION 3 STARTD 
	$('.first_cal_option_2').click(function(){	
	if($('.calender_pop').css('display')=='none')
				{
					
					$('.calender_pop').css('display','block');
					 $('.web_dialog_overlay').show();
					
					
			}
			});
			
			
	
	/// CALENDER TOP OPTION END HERE
	
	/// TEST CANCEL CALENDER 
	
		$('.pop_header >span').click(function(){
		
		 $('.calender_pop').hide(500);
		 $('.web_dialog_overlay').hide();
		
		});
	
	$('.cancel_cal').click(function(){
		
		 $('.calender_pop').hide(500);
		  $('.web_dialog_overlay').hide();
		
		});
	
	
	
	/// CALENDER POP UP STARED HERE
	
	// GENERAL OPTION
	$('.gen_opt').click(function(){
		
		    if($('.gen_content').css('display')=='block' || $('.gen_content').css('display')=='none')
			{
				$('.gen_content').css('display','block');
				$('.repeat_cal').css('display','none');
				$('.reminder_cal').css('display','none');
				$('.workgroup_cal').css('display','none');
				$('.repe').removeClass('repe_active');
				$('.remind').removeClass('remind_active');
				$('.work_g').removeClass('work_active');
				$('.gen_opt').addClass('gen_active');
				
			 }
		
		});
	 /// GENERAL OPTION END HERE 
	 
	 
	 // REPEAT OPTION STARED HERE 
	 $('.repe').click(function(){
		
		    if($('.repeat_cal').css('display')=='none')
			{
				$('.repeat_cal').css('display','block');
				//$('.repeat_cal').css('display','none');
				$('.reminder_cal').css('display','none');
				$('.workgroup_cal').css('display','none');
				$('.gen_content').css('display','none');
				$('.repe').addClass('repe_active');
				$('.gen_opt').removeClass('gen_active');
				$('.remind').removeClass('remind_active');
				$('.work_g').removeClass('work_active');
				
			 }
		
		});
	 
	 // REPEAT OPTION END HERE
	 
	 
	 	 // REPEAT OPTION STARED HERE 
	 $('.remind').click(function(){
		
		    if($('.reminder_cal').css('display')=='none')
			{
				$('.reminder_cal').css('display','block');
				$('.repeat_cal').css('display','none');
				//$('.reminder_cal').css('display','none');
				$('.workgroup_cal').css('display','none');
				$('.gen_content').css('display','none');
				$('.remind').addClass('remind_active');
				$('.repe').removeClass('repe_active');
				$('.gen_opt').removeClass('gen_active');
				$('.work_g').removeClass('work_active');
				
			 }
		
		});
	 
	 // REPEAT OPTION END HERE
	 
	 
	  	 // WORKGROUP OPTION STARED HERE 
	 $('.work_g').click(function(){
		
		    if($('.workgroup_cal').css('display')=='none')
			{
				$('.workgroup_cal').css('display','block');
				$('.repeat_cal').css('display','none');
				$('.reminder_cal').css('display','none');
				//$('.workgroup_cal').css('display','none');
				$('.gen_content').css('display','none');
				$('.work_g').addClass('work_active');
				$('.remind').removeClass('remind_active');
				$('.repe').removeClass('repe_active');
				$('.gen_opt').removeClass('gen_active');
				
			 }
		
		});
	 
	 // REPEAT OPTION END HERE
	 
	 
	/// CALENDER POP UP END HERE 
	
	
	
	
	
	
	
	
	
	
	//// JS FOR CALENDER STRED HERE 
	
	///////////////////////////////////////////
	///                                    ///
	///    ONLY FOR MY CALENDER OPTION     ///
	///                                    ///
	/////////////////////////////////////////
	
	/// CALENDER OPTION HERE 
	$(document.body).on('click', '.cal_option' ,function(){
	//$('.cal_option').click(function(){
		//alert('Hi');
		
		       $('div.hi').removeClass('hi');
			   
		       var cho_arrow =  $(this).parent().parent().parent().children('.calender_option').addClass('show_option');
			   
			   //  var cho_arrow =  $(this)
			   var cho_box_left = $(this).offset().left ;
			   var cho_box_top = $(this).offset().top ;
			    $('.show_option').css('top',cho_box_top - 85);
		        $('.show_option').css('left',cho_box_left - 64); 
				//alert(cho_box_top);
				 $('.show_option').slideToggle("slow");
				  $('.other_calender_option').hide("slow")
				 
		
		});
		
		
		//$('.cal_option').click(function(){
			   //  if($('.calender_option').css('display')=='block')
			     //   {
                      //    $('.calender_option').css('display','none');
                 //  }
		//});
		
		
	
	
	/// My calender 
	$('.my_claender').click(function(){
		
						//$('.my_calender_content').slideToggle("slow");
						if($('.my_calender_content').css('display')=='block')
						{
							$('.my_calender_content').hide();
							$('.my_claender').addClass('bottom_arrow');
						}
						else
						{
							$('.my_calender_content').show();
							$('.my_claender').removeClass('bottom_arrow');
						 }
		
		});
	
    ///////////////////////////////////////////
	///                                    ///
	///   ONLY FOR MY CALENDER OPTION END  ///
	///                                    ///
	/////////////////////////////////////////
	
	
	
	
	 ////////////////////////////////////////////////
	///                                           ///
	///   ONLY FOR OTHER CALENDER OPTION STRAED   ///
	///                                           ///
	////////////////////////////////////////////////
	
	/// OTHER CALENDER OPTION HERE 
	//$('.other_cal_option').click(function(){
		
		  //    if($('.other_calender_option').css('display')=='none')
			//  {
				  
				//  $('.other_calender_option').css('display','block');
				  
		//	 }
		//	 else
			// {
				//  $('.other_calender_option').css('display','none');
				 
			//  }
		
	//	});
	
	
		$(document.body).on('click', '.other_cal_option' ,function(){
	//$('.cal_option').click(function(){
		//alert('Hi');
		
		       $('div.hi').removeClass('hi');
			   
		       var other_cho_arrow =  $(this).parent().parent().parent().children('.other_calender_option').addClass('other_show_option');
			   
			   //  var cho_arrow =  $(this)
			   var other_cho_box_left = $(this).offset().left ;
			   var other_cho_box_top = $(this).offset().top ;
			    $('.other_show_option').css('top',other_cho_box_top - 85);
		        $('.other_show_option').css('left',other_cho_box_left - 64); 
				//alert(cho_box_top);
				 $('.other_show_option').slideToggle("slow")
				 $('.calender_option').hide("slow");
				
		
		});
	
	
	
	
	
	
	
	
	
	/// OTHER My calender 
	$('.other_claender').click(function(){
		
						//$('.my_calender_content').slideToggle("slow");
						if($('.other_calender_content').css('display')=='none')
						{
							$('.other_calender_content').show();
							$('.other_claender').addClass('other_bottom_arrow');
						}
						else
						{
							$('.other_calender_content').hide();
							$('.other_claender').removeClass('other_bottom_arrow');
						 }
		
		});
	
	
	
	//// CREATE CALENDER 
	
	
	/////////////////////////////////////////////
	///                                        ///
	///   ONLY FOR OTHER CALENDER OPTION END   ///
	///                                        ///
	/////////////////////////////////////////////
	
	$('.create_calender').click(function(){
		
		     
		
		});
		});