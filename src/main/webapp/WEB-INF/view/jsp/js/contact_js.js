// JavaScript Document

jQuery(document).ready(function() {
	$('.right_con_part >table >tbody>tr>td>input').attr('disabled','disabled');
	$('.right_con_part_pop_edit >table >tbody>tr>td>input').attr('disabled','disabled');
	
	$('li.right_menu_1').mouseover(function(){
	/*	alert('hi');*/
		var left_space = $(this).offset().left + $('.for_setting_1').width();
		var fullbody = window.scrollX + window.innerWidth;
		var extraspace = window.innerWidth - $('.content').offset().left - $('.content').width();
		$('.for_setting_1').css('display','block');
		 $('.for_setting_1').offset({top:$(this).offset().top,left:$(this).offset().left});
		 if( left_space > fullbody){
			 $('.for_setting_1').offset({left:$(this).offset().left - ( left_space - fullbody ) - extraspace -33});
			 
			 
			 }	
	}
		);
		
	
	
	
	/// MY CONTACT STARED HERE
	   $('.my_con').click(function(){
		
						//$('.my_calender_content').slideToggle("slow");
						if($('.my_calender_con').css('display')=='block')
						{
							$('.my_calender_con').hide();
							$('.my_con').addClass('bottom_arrow');
						}
						else
						{
							$('.my_calender_con').show();
							$('.my_con').removeClass('bottom_arrow');
						 }
		
		});
		
		/// MY CONTACT END HERE
		
	   /// OTHER CALENDER  HERE 
	   
	   $('.other_con').click(function(){
		
						//$('.my_calender_content').slideToggle("slow");
						if($('.other_calender_con').css('display')=='none')
						{
							$('.other_calender_con').show();
							$('.other_con').addClass('other_bottom_con');
						}
						else
						{
							$('.other_calender_con').hide();
							$('.other_con').removeClass('other_bottom_con');
						 }
		
		});
		
		/// OTHER CALENDER END HERE 
		
		
		/// MORE OPTION ARE HERE 
		$('.first_con_option_3').click(function(){
			
			   //alert('Hi');
			   
			   if($('.con_more').css('display')=="none")
			   {
				   $('.con_more').show();
				   $('.first_con_option_3').addClass('more_active');
				}
				else
				{
					$('.con_more').hide();
				    $('.first_con_option_3').removeClass('more_active');
			     }
				
			   
			   
			   
			})
			
		/// LEFT VIEW CONTENT WINDOW HEIGHT 
		
		var con_left =$(window).height()-172;
		$('.left_con_part').css('height',con_left);
		$('.right_con_part').css('height',con_left + 72-69);
		//alert(con_left)	;
		
		
		/// EDITE OPTION STARED HERE 
		$('.cancel_right').click(function(){
			
			     ///alert('Hi');
				 if($('.save_chnage').css('display')=='none')
				 {
					 $('.edite_name').show();
					 $('.cancel_right').addClass('cancel_right_active');
					 $('.add_more').show();
					 $('.save_chnage').show();
					 $('.cancel_fl_vi').show();
					 $('.right_con_part >table >tbody>tr>td>input').css('border','1px solid #ccc');
					 $('.right_con_part >table >tbody>tr>td>input').removeAttr('disabled');
				  }
				  else
				  {
					 $('.save_chnage').hide();
					 $('.edite_name').hide();
					 $('.cancel_right').removeClass('cancel_right_active');
					 $('.add_more').hide();
					 $('.cancel_fl_vi').hide();
					 $('.right_con_part >table >tbody>tr>td>input').attr('disabled','disabled');
					 $('.right_con_part >table >tbody>tr>td>input').css('border','none');
				  }
			
			})
			
			/// EDITE NAME  
			
			$('.edite_name').click(function(){
				
				     if($('.edite_name_box').css('display')=='none')
					 {
						$('.edite_name_box').show();
						$('.web_dialog_overlay').show();
						$('.web_dialog_overlay').css('z-index','10');  
					  }
					  else
					  {
					         $('.edite_name_box').hide();
						     $('.web_dialog_overlay').hide();
							 $('.web_dialog_overlay').css('z-index','3');  
					   }
				
				});
		// CANCEL NAME 
		
		  $('.cancel_right_name').click(function(){
			       
				             $('.edite_name_box').hide();
						     $('.web_dialog_overlay').hide() 
			    
			  });
			  
	   /// EDITE NAME CANCEL TOP 
	   $('.cancel_top').click(function(){
			       
				             $('.edite_name_box').hide();
						     $('.web_dialog_overlay').hide() 
			    
			  });
			
	   /// CREATE GROUP HERE 
	   
	   $('.first_con_option').click(function(){
		   
		      // alert('Hi');
			   if($('.group_name').css('display')=="none")
			   {
				   $('.group_name').show();
				   $('.web_dialog_overlay').show() 
				   
				}
				else
				{ 
				   $('.group_name').hide();
				   $('.web_dialog_overlay').hide();
			    
				}
		   
		   });
		// CANCEL GROUP 
		$('.cancel_grop').click(function(){
			
			        $('.group_name').hide();
				     $('.web_dialog_overlay').hide();
			});
			// CANCEL GROUP 
		$('.cancel_grop_top').click(function(){
			
			        $('.group_name').hide();
				   $('.web_dialog_overlay').hide();
			});
	
	/// CREATE CONTACT 
	$('.first_con_option_1').click(function(){
		
		   //  alert('hi');
			 if($('.create_contact').css('display') == 'none')
			 {
				
				 $('.create_contact').show();
				 $('.web_dialog_overlay').show(); 
		     }
			 else 
			 {
		         $('.create_contact').hide();
				 $('.web_dialog_overlay').hide(); 
			 
			 }
		
		});
		
		/// CREATE CANCEL TOP
		
		$('.create_top').click(function(){
			
			     $('.create_contact').hide();
				 $('.web_dialog_overlay').hide(); 
				 
			});
			
       /// ADD GROUP 
	   $('.save_right_name').click(function(){
		   
		        var find_group = $('.Sk').val();
				//alert(find_group);
				$('.my_calender_con >ul').append('<li> <img src="images/group_con.png" class="icon_con col_con group_img"> <span>'+ find_group +'</span><div class="clear"></div></li>');
				$('.Sk').val('');
				$('.group_name').hide();
				$('.web_dialog_overlay').hide();
				
				
		   
		   });
		   
		   
		   /// CREATE CONTACT ON FULL VIEW TD 
	$('table.con_he_content >tbody >tr>td').click(function(){
		
		
		    //alert('hi');
			 if($('.create_contact_edit').css('display') == 'none')
			 {
				
				 $('.create_contact_edit').show();
				 $('.web_dialog_overlay').show(); 
		     }
			 else 
			 {
		         $('.create_contact_edit').hide();
				 $('.web_dialog_overlay').hide(); 
			 
			 }
		
		});
		
		/// CREATE CANCEL TOP
		
		$('.create_top_edit').click(function(){
			
			
			     $('.create_contact_edit').hide();
				 $('.web_dialog_overlay').hide(); 
				 
			});
		
		/// EDITE CONTACT STARED 
		
		$('.cancel_right_edit').click(function(){
			
			    // alert('Hi');
				 if($('.save_chnage_pop_edit').css('display')=='none')
				 {
					 $('.edite_name_edit').show();
					 $('.cancel_right_edit').addClass('cancel_right_active_edit');
					 $('.add_more_edit').show();
					  $('.cancel_edit').show();
					 $('.save_chnage_pop_edit').show();
					 $('.right_con_part_pop_edit >table >tbody>tr>td>input').css('border','1px solid #ccc');
					 $('.right_con_part_pop_edit >table >tbody>tr>td>input').removeAttr('disabled');
				  }
				  else
				  {
					 $('.save_chnage_pop_edit').hide();
					 $('.edite_name_edit').hide();
					 $('.cancel_right_edit').removeClass('cancel_right_active_edit');
					 $('.add_more_edit').hide();
					  $('.cancel_edit').hide();
					 $('.right_con_part_pop_edit >table >tbody>tr>td>input').attr('disabled','disabled');
					 $('.right_con_part_pop_edit >table >tbody>tr>td>input').css('border','none');
				  }
			
			});
			
			
			
			$('.edite_name_edit').click(function(){
				
				     if($('.edite_name_box').css('display')=='none')
					 {
						$('.edite_name_box').show();
						$('.web_dialog_overlay').show();
						$('.web_dialog_overlay').css('z-index','10');  
					  }
					  else
					  {
					         $('.edite_name_box').hide();
						     $('.web_dialog_overlay').hide();
							 $('.web_dialog_overlay').css('z-index','3');  
					   }
				
				});
				
			/// CANCEL EDITE POP 
			$('.cancel_edit').click(function(){
				
				      $('.save_chnage_pop_edit').hide();
					 $('.edite_name_edit').hide();
					 $('.cancel_right_edit').removeClass('cancel_right_active_edit');
					 $('.add_more_edit').hide();
					  $('.cancel_edit').hide();
					 $('.right_con_part_pop_edit >table >tbody>tr>td>input').attr('disabled','disabled');
					 $('.right_con_part_pop_edit >table >tbody>tr>td>input').css('border','none');
				
				});
				
				
	    /// CANCEL EDITE  DETAILS VIEW PAGES  
			$('.cancel_fl_vi').click(function(){
				
				     $('.save_chnage').hide();
					 $('.edite_name').hide();
					 $('.cancel_right').removeClass('cancel_right_active');
					 $('.add_more').hide();
					 $('.cancel_fl_vi').hide();
					 $('.right_con_part >table >tbody>tr>td>input').attr('disabled','disabled');
					 $('.right_con_part >table >tbody>tr>td>input').css('border','none');
				
				});		
				
				
				
				/// MANGE SHARING 
				
				$('.mange_sharing').click(function(){
					
					   //  alert('Hi');
						 $('.con_more').hide();
						 $('div.more_active').removeClass('more_active');
						 if($('.sharing_mange').css('display')=='none')
						 {
							 $('.sharing_mange').show();
							 $('.web_dialog_overlay').show();
							 
					     }
					
					});	
			
			   /// MANAGES CANCEL TOP 
			   $('.mange_can_top').click(function(){
				   
				             $('.sharing_mange').hide();
							 $('.web_dialog_overlay').hide();
				   
			  });
			  
			   /// MANAGES CANCEL 
			   $('.mange_can').click(function(){
				   
				             $('.sharing_mange').hide();
							 $('.web_dialog_overlay').hide();
				   
			  });
			  // ADD MORE SHRE 
			  $(document.body).on('click', '.share_more' ,function(){
			//	  alert('hi');
				  $('.sharing_mange >.table_append >table.append_tr >tbody').append('<tr><td><input type="text"  class="initive_people" /></td><td><div class="can_edit"><div class="share_more">Add More</div><div class="can_edite">Can Edit</div></div></td></tr>');
				  });
		
		
		
		
});