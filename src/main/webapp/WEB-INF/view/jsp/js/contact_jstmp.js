// JavaScript Document

jQuery(document).ready(function() {
	//window.alert('Hi');
	
	 $(document.body).on('click','.delete_contact_row',function(){
	       
	       $(this).parent().remove();
	       
	       });
	
	$('.right_con_part >table >tbody>tr>td>input').attr('disabled','disabled');
	$('.right_con_part_pop_edit >table >tbody>tr>td>input').attr('disabled','disabled');
	
	/// EDITE ADDRESS STRED HERE 
	
	 $(document.body).on('click','.edite_address_edit',function(){
	
			
			//alert('Hi');
				
				     if($('.address_name_box').css('display')=='none')
					 {
						$('.address_name_box').show();
						$('.web_dialog_overlay').show();
						$('.web_dialog_overlay').css('z-index','10');  
					  }
					  else
					  {
					         $('.address_name_box').hide();
						     $('.web_dialog_overlay').hide();
							 $('.web_dialog_overlay').css('z-index','3');  
					   }
				
				});
		
		
		
		/// EDIT ADDRESS FOR FULL PEOPLE
		
		
	 $(document.body).on('click','.edite_address_edit_peole',function(){

			
			//alert
			     if($('.address_name_box').css('display')=='none')
				 {
			    	 $('.address_name_box').show();
					$('.web_dialog_overlay').show();
					//$('.web_dialog_overlay').css('z-index','13');  
					$('.web_dialog_overlay').addClass('bg_blur');  
				  }
				  else
				  {
					  $('.address_name_box').show();
					     $('.web_dialog_overlay').hide();
						// $('.web_dialog_overlay').css('z-index','3'); 
						 $('.web_dialog_overlay').removeClass('bg_blur'); 
				   }
			
			});
	// CANCEL NAME 
	
	
	
	
	
	
	
	
	
	
	/// MY CONTACT STARED HERE

	 $(document.body).on('click','.my_con',function(){
	 
		
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
	   
	 $(document.body).on('click','.other_con',function(){
	
		
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

	 $(document.body).on('click','.first_con_option_3',function(){

			
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
		//alert(con_left);
		
		
		/// EDITE OPTION STARED HERE 

	 $(document.body).on('click','.cancel_right',function(){

			
			     ///alert('Hi');
				 if($('.save_chnage').css('display')=='none')
				 {
					 $('.edite_name').show();
					 $('.cancel_right').addClass('cancel_right_active');
					 $('.add_more').show();
					 $('.save_chnage').show();
					 $('.cancel_fl_vi').show();
					 $('.edite_address_edit').show();
					 $('.right_con_part >table >tbody>tr>td>input').css('border','1px solid #ccc');
					 $('.right_con_part >table >tbody>tr>td>input').removeAttr('disabled');
					 $('.right_con_part >table >tbody>tr>td>textarea').removeAttr('disabled');
					 $('.right_con_part >table >tbody>tr>td>textarea').removeClass('disabled_textarea');

				  }
				  else
				  {
					 $('.save_chnage').hide();
					 $('.edite_name').hide();
					 $('.cancel_right').removeClass('cancel_right_active');
					 $('.add_more').hide();
					 $('.cancel_fl_vi').hide();
					 $('.edite_address_edit').hide();
					 $('.right_con_part >table >tbody>tr>td>input').attr('disabled','disabled');
					 $('.right_con_part >table >tbody>tr>td>input').css('border','none');
				  }
			
			})
			
			/// EDITE NAME  
			
	 $(document.body).on('click','.edite_name',function(){
		
				/*
				//alert
				     if($('.edite_name_box').css('display')=='none')
					 {
						$('.edite_name_box').show();
						$('.web_dialog_overlay').show();
						//$('.web_dialog_overlay').css('z-index','13');  
						$('.web_dialog_overlay').addClass('bg_blur'); 
					  }
					  else
					  {
					         $('.edite_name_box').hide();
						     $('.web_dialog_overlay').hide();
							// $('.web_dialog_overlay').css('z-index','3'); 
							 $('.web_dialog_overlay').removeClass('bg_blur'); 
					   }*/
				
				});
		// CANCEL NAME 
		
	 $(document.body).on('click','.cancel_right_name',function(){
		
			       
				             $('.edite_name_box').hide();
							 $('.address_name_box').hide();
						     $('.web_dialog_overlay').hide() 
			    
			  });
			  
	   /// EDITE NAME CANCEL TOP 

	 $(document.body).on('click','.cancel_top',function(){
	 
			       
				             $('.edite_name_box').hide();
							 $('.address_name_box').hide();
						     $('.web_dialog_overlay').hide() 
						     
			    
			  });
			
	   /// CREATE GROUP HERE 
	   
	 $(document.body).on('click','.first_con_option',function(){
	
		   
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

	 $(document.body).on('click','.cancel_grop',function(){
	
			
			        $('.group_name').hide();
				     $('.web_dialog_overlay').hide();
			});
			// CANCEL GROUP 

	 $(document.body).on('click','.cancel_grop_top',function(){
	
			
			        $('.group_name').hide();
				   $('.web_dialog_overlay').hide();
			});
	
	/// CREATE CONTACT 

	 $(document.body).on('click','.first_con_option_1',function(){

		
		   //  alert('hi');
			 if($('.create_contact').css('display') == 'none')
			 {
				
				 $('.create_contact').show();
				 $('.web_dialog_overlay').show(); 
				 $('.web_dialog_overlay').removeClass('bg_blur'); 
		     }
			 else 
			 {
		         $('.create_contact').hide();
				 $('.web_dialog_overlay').hide(); 
			 
			 }
		
		});
		
		/// CREATE CANCEL TOP
		
	 $(document.body).on('click','.create_top',function(){

			
			     $('.create_contact').hide();
				 $('.web_dialog_overlay').hide(); 
				 
			});
			
       /// ADD GROUP 
	 $(document.body).on('click','.save_right_name',function(){
	
		   
		        var find_group = $('.Sk').val();
				//alert(find_group);
				$('.my_calender_con >ul').append('<li> <img src="images/group_con.png" class="icon_con col_con group_img"> <span>'+ find_group +'</span><img id="hidcon_"' +find_group+ '" onclick="delConFldr(this.id)" src="images/tool.png" class="delete_contact_row"><div class="clear"></div></li>');
				$('.Sk').val('');
				$('.group_name').hide();
				$('.web_dialog_overlay').hide();
				
				
		   
		   });
		   
		   
		   /// CREATE CONTACT ON FULL VIEW TD 
	//$('table.con_he_content >tbody >tr>td').click(function(){
		/* $(document.body).on('click', 'table.con_he_content >tbody >tr>td' ,function(){
		
		
		    alert('hi');
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
		*/
		/// CREATE CANCEL TOP
		
	 $(document.body).on('click','.create_top_edit',function(){
	
			
			
			     $('.create_contact_edit').hide();
				 $('.web_dialog_overlay').hide(); 
				 
			});
		
		/// EDITE CONTACT STARED 
		
	 $(document.body).on('click','.cancel_right_edit',function(){

			
			    // alert('Hi');
				 if($('.save_chnage_pop_edit').css('display')=='none')
				 {
					 $('.edite_name_edit').show();
					 $('.cancel_right_edit').addClass('cancel_right_active_edit');
					 $('.add_more_edit').show();
					 $('.cancel_edit').show();
					 $('.save_chnage_pop_edit').show();
					 $('.edite_address_edit').addClass('left_details');
					 $('.right_con_part_pop_edit >table >tbody>tr>td>input').css('border','1px solid #ccc');
					 $('.right_con_part_pop_edit >table >tbody>tr>td>input').removeAttr('disabled');
					 $('.right_con_part_pop_edit >table >tbody>tr>td>textarea').removeAttr('disabled');
					 $('.right_con_part_pop_edit >table >tbody>tr>td>textarea').removeClass('disabled_textarea');
				  }
				  else
				  {
					 $('.save_chnage_pop_edit').hide();
					 $('.edite_name_edit').hide();
					 $('.cancel_right_edit').removeClass('cancel_right_active_edit');
					 $('.add_more_edit').hide();
					 $('.cancel_edit').hide();
					 $('.edite_address_edit').removeClass('left_details');
					 $('.right_con_part_pop_edit >table >tbody>tr>td>input').attr('disabled','disabled');
					 $('.right_con_part_pop_edit >table >tbody>tr>td>textarea').attr('disabled','disabled');
					 $('.right_con_part_pop_edit >table >tbody>tr>td>textarea').addClass('disabled_textarea');
					 $('.right_con_part_pop_edit >table >tbody>tr>td>input').css('border','none');
				  }
			
			});
			
			
			
	 $(document.body).on('click','.edite_name_edit',function(){
	
				
				    /* if($('.edite_name_box').css('display')=='none')
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
					   }*/
				
				});
				
			/// CANCEL EDITE POP 
	 $(document.body).on('click','.cancel_edit',function(){
		
				
				     $('.save_chnage_pop_edit').hide();
					 $('.edite_name_edit').hide();
					 $('.cancel_right_edit').removeClass('cancel_right_active_edit');
					 $('.add_more_edit').hide();
					 $('.cancel_edit').hide();
					 $('.edite_address_edit').removeClass('left_details');
					 $('.edite_address_edit').hide();
					 $('.right_con_part_pop_edit >table >tbody>tr>td>input').attr('disabled','disabled');
					 $('.right_con_part_pop_edit >table >tbody>tr>td>input').css('border','none');
					 $('.right_con_part_pop_edit >table >tbody>tr>td>textarea').attr('disabled','disabled');
					 $('.right_con_part_pop_edit >table >tbody>tr>td>textarea').addClass('disabled_textarea');

				
				});
				
				
	    /// CANCEL EDITE  DETAILS VIEW PAGES  
	 $(document.body).on('click','.cancel_fl_vi',function(){

				
				     $('.save_chnage').hide();
					 $('.edite_name').hide();
					 $('.edite_address_edit').hide();
					 $('.cancel_right').removeClass('cancel_right_active');
					 $('.add_more').hide();
					 $('.cancel_fl_vi').hide();
					 $('.right_con_part >table >tbody>tr>td>input').attr('disabled','disabled');
					 $('.right_con_part >table >tbody>tr>td>input').css('border','none');
					 $('.right_con_part >table >tbody>tr>td>textarea').attr('disabled','disabled');
					 $('.right_con_part >table >tbody>tr>td>textarea').addClass('disabled_textarea');
				
				});		
				
				
				
				/// MANGE SHARING 
				
	 $(document.body).on('click','.mange_sharing',function(){
				
					
					   //  alert('Hi');
						 $('.con_more').hide();
						 $('.calender_option').hide();
						 $('div.more_active').removeClass('more_active');
						 if($('.sharing_mange').css('display')=='none')
						 {
							 $('.sharing_mange').show();
							 $('.web_dialog_overlay').show();
							 
					     }
					
					});	
			
			   /// MANAGES CANCEL TOP 
	 $(document.body).on('click','.mange_can_top',function(){
				             $('.sharing_mange').hide();
							 $('.web_dialog_overlay').hide();
				   
			  });
			  
			   /// MANAGES CANCEL 
	 $(document.body).on('click','.mange_can',function(){
			
				   
				             $('.sharing_mange').hide();
							 $('.web_dialog_overlay').hide();
				   
			  });
			  // ADD MORE SHRE 
			  
			  $(document.body).on('click', '.share_more' ,function(){
				  //alert('hi');
				  $('.sharing_mange >.table_append >table.append_tr >tbody').append('<tr><td><input type="text"  class="initive_people" /></td><td><div class="can_edit"><div class="share_more">Add More</div><div class="can_edite">    <select><option>Can View </option><option>Can Edit </option><option>Can Mange </option></select></div></div></td></tr>');
				  });
		
		/// IMPORT FILE STRED HERE 
		
	 $(document.body).on('click','.import_click',function(){

			
			         if($('.import_con').css('display')=='none')
					 {
						  $('.import_con').show();
						  $('.web_dialog_overlay').show();
						  $('.con_more').hide();
						  $('div.more_active').removeClass('more_active');
					  }
			
			});
	
	 $(document.body).on('click','.import_can_top',function(){
	 
		                  $('.import_con').hide();
						  $('.web_dialog_overlay').hide();
		  
		  });
		  
	 $(document.body).on('click','.cancel_import',function(){
		 
		                  $('.import_con').hide();
						  $('.web_dialog_overlay').hide();
		  
		  });
		  
		  /// IMPORT FILE END HERE 
		  
		  	/// Export FILE STRED HERE 
		
	 $(document.body).on('click','.export_click',function(){
	
			
			         if($('.export_con').css('display')=='none')
					 {
						  $('.export_con').show();
						  $('.web_dialog_overlay').show();
						  $('.con_more').hide();
						  $('div.more_active').removeClass('more_active');
					  }
			
	  });
	
	 $(document.body).on('click','.export_can_top',function(){

		                  $('.export_con').hide();
						  $('.web_dialog_overlay').hide();
		  
		  });
		  
	 $(document.body).on('click','.cancel_export',function(){
    
		                  $('.export_con').hide();
						  $('.web_dialog_overlay').hide();
		  
    });
		
		
		
});