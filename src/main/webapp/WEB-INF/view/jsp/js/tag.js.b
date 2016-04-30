// JavaScript Document
jQuery(document).ready(function() {
	
	//// ************************  ADDD THIS FOR NEW JS -----------------/////
	

	
	
	
	
	
	
	
	
	//// ************************************ END HERE -------------------------------------///
	
	
	
	
	
	
	
	
	
	
	
	/// TAGE STRED HERE 
	$('.tag_main').click(function(){
		
		  /// alert('Hi');
		   if($('.tag_content').css('display')== 'none')
		   {
			     
		   $('.tag_content').css('display','block');
		   
		   }else {
			   
			    $('.tag_content').css('display','none');
			   
			   }
		
		});
	
	
	/// TAG STRED HERE 
	
	$('.tag_sel_option').click(function(){
		
		  //  alert('Hi');
			if($('.select_tag').css('display')=='none')
			{
				
				$('.select_tag').css('display','block');
				
				}else {
					
					$('.select_tag').css('display','none');
					
					
					}
		
		
		});
	
	
	/// SELECT TAG STRED HERE 
	
	$('.select_tag >ul >li').click(function(){
		//alert('Hi')
		   $('.test_color').css('background','none');
		  $('.tag_sel_option >ul>li>span').html('');
		  var tag_color =  $(this).children('.color_tag').css('background-color');
		  var tag_text =  $(this).children('span').html();
		///  alert(tag_color);
		 // alert(tag_text);
		  $('.test_color').css('background',tag_color);
		  $('.tag_sel_option >ul>li>span').html(tag_text);

		  $(this).parent().parent().hide();
		
		});
	
	/// CRAETE TAG STRED HERE 
	$('.craet_gat').click(function(){
		
		$('.craete_tag').show();
		$('.web_dialog_overlay').show();
		
		
		});

	/// CANCEL CRATE 
	$('.tag_can').click(function(){
		
		$('.craete_tag').hide();
		$('.web_dialog_overlay').hide();
		
		});	
	
	
	/// SAVE TAG STRED HERE 
	$('.tag_save').click(function(){
		
		  var tag_name =  $('.tag_name').val();
		//  alert(tag_name);
		 var sel_color = $(this).parents().children('.tag_sel_option').children().children().children('.test_color').css('background-color');
		 // LIST OF COLOR 
		
		 var color_green = $('.color_green').css('background-color');
		// alert(color_green);
		 var color_blue = $('.color_blue').css('background-color');
		 var color_yellow = $('.color_yellow').css('background-color');
		 var color_black = $('.color_black').css('background-color');
		 var color_gray = $('.color_gray').css('background-color');
	     var color_orange = $('.color_orange').css('background-color');
	     var color_pink = $('.color_pink').css('background-color');
	     var color_drak_bl = $('.color_drak_bl').css('background-color');
	     var color_dar_gree = $('.color_dar_gree').css('background-color');
		// alert(sel_color);
		// alert(color_dar_gree);
		 // LIST COLOR END 

		
         $('.tag_inner_content >ul').append('<li><input type="checkbox" class="tag_check" /><span style="background:'+ sel_color +'">'+ tag_name +'</span><div class="clear"></div></li>');

		// alert(sel_color );
		//  $('.tag_inner_content >ul').append('<li><input type="checkbox" class="tag_check" /><span style="background:'+ sel_color +'">'+ tag_name +'</span><div class="clear"></div></li>');
		  $('.tag_name').val('');
		  $('.craete_tag').hide();
		  $('.web_dialog_overlay').hide();
		
		});
	
	// CHECK THE TAG 
	$(document.body).on('click','.tag_check',function(){
		
		//alert('Hi')
		var sel_val = $(this).parent().children('span').html();
		var sel_color = $(this).parent().children('span').css('background-color');  /// APPLY IF CHECK BOX IS CHECKED THE APPLY IT
		//alert(sel_val);
		$('.selected_row >.message >.inbox_tag').append('<div class="in_tag" style="background:' + sel_color + '"><span>'+ sel_val +'</span><div class="close_tag">x</div></div>')
		var count_gat_check = $('.tag_check:checked').length;
		if(count_gat_check ==0)
		{
			$('.apply_tag').hide();
			}else if(count_gat_check == 1){
				
				$('.apply_tag').show();
				
				}
		
		})
		 
		// $('.mail_checked').on
		 
	
	
	// haedked value 
	
	
	
	/// TAG APPLY STARED HERE 
	$('.apply_tag').click(function(){
	//	alert('Hi');
		//if($('.mail_checked:checked'))
		//if($('.tag_check:checked'))
		//{
			// alert('By');
			// var sel_opt_val = $('.tag_check:checked').parent().children('span');
			// alert(sel_opt_val);
			
		//	}
			$('.in_tag').show();
			$('.tag_content').hide();
			$('.tag_check:checked').removeAttr('checked');
			$('.web_dialog_overlay').hide();
		
		     // alert(sel_val);
		
		});
	
	
	/// CLOSE TAG STRED HERE 
		$(document.body).on('click','.close_tag',function(){
			
			//alert('Hi');
			
			$(this).parent().hide();
			
			
			
			
			})
	
	
	
	
	///  CLOSE TAG END 
	
	
	});