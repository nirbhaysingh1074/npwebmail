
jQuery(document).ready(function() {

	/// NEW JS FOR ATTACHMENT STARED HERE
	$('.drop').click(function(){
		
		if($('.small_to').css('display')=='block')
			{
			
			   //alert('Test Hi'); 
			   $('.attach_row').removeClass('small_to');
			   $('.disable_row').css('display','none');
			
			}
		else{
			
			// alert('Test By');
			 $('.attach_row').addClass('small_to');
			  $('.disable_row').css('display','block');
			
		}
		
		
	});
	
	
	/// NEW JS FOR ATTACHMENT END HERE
	
	
	
	
	
	
	
	
	
	
	
	
	
	/// TEST HERE 
	
	  //  $('')
	
	//$(document.body).on('click', '#cke_1_top' ,function(){
		   // alert('Hi')
		
  //action B
      //  });
	
	// TEST HERE END 
	
	/// PLAIN TEXT EVENT HERE 
	$('.plain_text').click(function(){
		//alert('Hi');
		if($('.written_space >textarea.for_plaintext + .cke_1 >.cke_inner>.cke_top').css('display')=='block')
		{
			           $('.for_plaintext').addClass('change_plaintext');
					    $('.green_plain_text_sign').css('display','none');
		}
		else if($('.written_space >textarea.for_plaintext + .cke_1 >.cke_inner>.cke_top').css('display')=='none')
		{
			     $('.for_plaintext').removeClass('change_plaintext');
				  $('.green_plain_text_sign').css('display','block');
				
			
		}

		
		});
	
	
	
	
// EVENT ON CLICK 
$('#upload').click(function(){
	
	   if($('.disable_row').css('display')=='display')
	   {
		   $('.disable_row').css('display','block');
		   
	   }
	   else
	   {
		    $('.disable_row').css('display','none');
		   
	   }
	
	
	});




	
	/// Close Mail id 
	//var in = document.getElementById("closeit");

	
	// CK EDITOR CHANGES
	//$('.written_space').delegate('#cke_1_contents',function(){$('#cke_1_contents').css('height',ck_height)});
	/// var liId = $(this).parent(".vR").attr("class");
   // alert(liId);        
//});
/* close it to *****************/	
$(document.body).on('click', '.closeit_to' ,function(){
	//alert("Hi");
	var txt= $(this).parent().children().html();
	var txt1=txt+",";
	var txt2=","+txt;
	alert(txt);
	$(this).parent().parent().remove();
	 var hid_txt=$('#hid_to_id').val();
	 var res="";
	 hid_txt = hid_txt.replace(txt2,"");
	 hid_txt = hid_txt.replace(txt1,"");
	 hid_txt = hid_txt.replace( txt,"");
	 document.getElementById("hid_to_id").value =hid_txt;
	});



/*close it to *****************/	
$(document.body).on('click', '.closeit_cc' ,function(){
	//alert("Hi");
	var txt= $(this).parent().children().html();
	alert(txt);
	$(this).parent().parent().remove();
	var hid_txt=$('#hid_cc_id').val();
	 var res="";
	 hid_txt = hid_txt.replace(txt2,"");
	 hid_txt = hid_txt.replace(txt1,"");
	 hid_txt = hid_txt.replace( txt,"");
	 document.getElementById("hid_cc_id").value =hid_txt;
	});



/*
 * close it to 
*/	
$(document.body).on('click', '.closeit_bcc' ,function(){
	//alert("Hi");
	var txt= $(this).parent().children().html();
	alert(txt);
	$(this).parent().parent().remove();
	var hid_txt=$('#hid_bcc_id').val();
	 var res="";
	 hid_txt = hid_txt.replace(txt2,"");
	 hid_txt = hid_txt.replace(txt1,"");
	 hid_txt = hid_txt.replace( txt,"");
	 document.getElementById("hid_bcc_id").value =hid_txt;
	});

	/*var dd = document.getElementById("closeit");
	$(dd).click(function(){
		
		     alert('Hi');
		
		});
	$('.vR').click(function(){
		
		 alert("Hi");
		 
		});*/
	
	
	/// ONLY FOR TO FUNCTION HERE
	
	           /// FOR ENTER UP THE FOR TO      
	           $('.enter_email').keydown(function(e){
	        	//   alert(e.which);
		        if(e.which == 13 || e.which == 32 || e.which == 9)
		        {
			  e.preventDefault();
			  var textarea = $('.enter_email').val();
			 
			 // alert(textarea);
			  if(textarea!="" && textarea!=null)
				  {
				  var hid_txt=$('#hid_to_id').val();
				  var tocnt="";
				  if(hid_txt==null || hid_txt=="")
					  {
					  tocnt=textarea;
					  }
				  else
					  {
					  tocnt=hid_txt+","+textarea;
					  }
				  
				  document.getElementById("hid_to_id").value =tocnt;
			  $('.enter_email').val('');
		
		
		
		
		
			 // var n_to = $('vR').length;
			  //alert(n);
			  	$('.combind_email').append('<div class="vR to_content" id="'+textarea+'"><span class="vN Y7BVp a3q" email="hari@silvereye.co"><div class="vT">' + textarea +'</div><div class="vM closeit_to"></div></span></div>');
			   var n_to = $('.to_content').length;
			  $('.email_id_info_to').text(n_to + " more");
		  
			
			// EMAIL VALIDATION START HERE 
            if (!checkEmail(textarea))
            {
				 $('#'+textarea).addClass('wrong_email')
            }
		
		// EMAIL VALIDATION END HERE 
		
		
				  
				  }
			  
			  
			  
		        }
		  })
	
	
	
		/// FOR ENTER UP THE FOR CC
      
	  $('.enter_email_cc').keydown(function(e){
		  
		  if(e.which == 13 || e.which == 32 || e.which == 9)
		  {
			  e.preventDefault();
			  var textarea = $('.enter_email_cc').val();
			  
			  if(textarea!="" && textarea!=null)
			  {
			  var hid_txt=$('#hid_cc_id').val();
			  var cccnt="";
			  if(hid_txt==null || hid_txt=="")
				  {
				  cccnt=textarea;
				  }
			  else
				  {
				  cccnt=hid_txt+","+textarea;
				  }
			  
			  document.getElementById("hid_cc_id").value =cccnt;
			  $('.enter_email_cc').val('');
			  
			  				//   VALIDATED THE EMAIL ID 
			/*/// EMAIL VALIDATION HERE 
                var testEmail = /^[A-Z0-9._%+-]+@([A-Z0-9-]+\.)+[A-Z]{2,4}$/i;
              if (testEmail.test(this.value)){ 
                          $('input').siblings(".check").css('visibility', 'visible');
						  $('.cc_content').removeClass('wrong_email')
                 }
                else{
					   $('.cc_content').addClass('wrong_email')
                    }
		
		// EMAIL VALIDATION END HERE 
*/			    //   VALIDATED THE EMAIL END HERE 
			  
			  	$('.combind_email_cc').append('<div class="vR cc_content"  id="'+textarea+'"><span class="vN Y7BVp a3q" ><div class="vT">' + textarea +'</div><div class="vM closeit_cc"></div></span></div>');

				
					var n_cc = $('.cc_content').length;
			        $('.email_id_info_cc').text(n_cc + " more");
					
					  if( n_cc==0)
			  {
				//  alert('It Blank')
				$('.email_id_info_cc').addClass('info_cc');
				  
			  }
			  else{
				  
				   //alert('Value Are Here' + n_to);
				   $('.email_id_info_cc').removeClass('info_cc');
				  
				  
				  }
			  
					  

						// EMAIL VALIDATION START HERE 
			            if (!checkEmail(textarea))
			            {
							 $('#'+textarea).addClass('wrong_email')
			            }
					
					// EMAIL VALIDATION END HERE 
		   }
		  }
		  })
	
	
			/// FOR ENTER UP THE FOR CC
      
	  $('.enter_email_bcc').keydown(function(e){
		  
		  if(e.which == 13 || e.which == 32 || e.which == 9)
		  {
			  e.preventDefault();
			  var textarea = $('.enter_email_bcc').val();
			  
			  
			  if(textarea!="" && textarea!=null)
			  {
			  var hid_txt=$('#hid_bcc_id').val();
			  var bcccnt="";
			  if(hid_txt==null || hid_txt=="")
				  {
				  bcccnt=textarea;
				  }
			  else
				  {
				  bcccnt=hid_txt+","+textarea;
				  }
			  
			  document.getElementById("hid_bcc_id").value =bcccnt;
			  $('.enter_email_bcc').val('');
			  	/*	/// EMAIL VALIDATION HERE 
                var testEmail = /^[A-Z0-9._%+-]+@([A-Z0-9-]+\.)+[A-Z]{2,4}$/i;
              if (testEmail.test(this.value)){ 
                          $('input').siblings(".check").css('visibility', 'visible');
						  $('.bcc_content').removeClass('wrong_email')
                 }
                else{
					   $('.bcc_content').addClass('wrong_email')
                    }
		
		// EMAIL VALIDATION END HERE 
*/			  	$('.combind_email_bcc').append('<div class="vR bcc_content"  id="'+textarea+'"><span class="vN Y7BVp a3q" email="hari@silvereye.co"><div class="vT">' + textarea +'</div><div class="vM closeit_bcc"></div></span></div>');
			  	var n_bcc = $('.bcc_content').length;
			    $('.email_id_info_bcc').text(n_bcc + " more");
			    
			    

				// EMAIL VALIDATION START HERE 
	            if (!checkEmail(textarea))
	            {
					 $('#'+textarea).addClass('wrong_email')
	            }
			
			// EMAIL VALIDATION END HERE 
		   }
		  }
	  })
		  
		  
	/// FOR ENTER UP THE FOR ATTACHMENT
      
	  $('.enter_email_attachment').keypress(function(e){
		  
		  if(e.which == 13 || e.which == 32)
		  {
			  e.preventDefault();
			  var textarea = $('.enter_email_attachment').val();
			  $('.enter_email_attachment').val('');
			  	$('.combind_email_attachment').append('<div class="vR"><span class="vN Y7BVp a3q" email="hari@silvereye.co"><div class="vT">' + textarea +'</div><div class="vM closeit"></div></span></div>');
			  
		   }
		  })
		  
	
	
	/// MAIL SEND OPTION CC AND BCC HERE 
	/// ATTACHMENT OPTION 
	
	//$('.attach_option').attachEvent('onclick', function() { /* do stuff here*/ });
	//$('.attach_option').att(function(){
		
		//     alert('Hi');
		
		
		//});
		///  EMAIL ATTACH TO OPTION HERE STARED HERE 
		/// SMALL TO 
		var composed_height =$('.composed_pages').innerHeight() - $('.all_send').innerHeight();
          // $('.text_plugins').height(composed_height);
        // var rx = $('#cke_1_contents').css('height',composed_height);
		//alert(composed_height);
		//alert($('.composed_pages').innerHeight());
		//alert($('.all_send').innerHeight());
		$('.small_to').click(function(){
			
			 $('.composed_input').addClass('small_to');
			 $('.email_id_info_to').css('display','block');
			  $('.combind_email').addClass('border_to');
			
			
			});
		
		/// ROW 
		// TO ROW
		   $('.to_row').click(function()
		     {
			
			       $('.to_row').removeClass('small_to');
				   $('.cc_row').addClass('small_to');
				   $('.full_cc').css('height','32')
				   $('.bcc_row').addClass('small_to');
				   $('.attach_row').addClass('small_to');
				   $('.subject_row').addClass('small_to');
				   $('.email_id_info_to').css('display','none');
				   $('.email_id_info_cc').css('display','block');
				   $('.email_id_info_bcc').css('display','block');

	         });
		// CC ROW
		   $('.cc_row').click(function()
		     {
			
			       $('.to_row').addClass('small_to');
				   $('.cc_row').removeClass('small_to');
				   $('.bcc_row').addClass('small_to');
				   $('.attach_row').addClass('small_to');
				   $('.subject_row').addClass('small_to');
				   $('.email_id_info_to').css('display','block');
                   $('.email_id_info_cc').css('display','none');
				   $('.email_id_info_bcc').css('display','block');
	         });
			 
		// BCC ROW
		   $('.bcc_row').click(function()
		     {
			       $('.full_cc').css('height','32')
			       $('.to_row').addClass('small_to');
				   $('.cc_row').addClass('small_to');
				   $('.bcc_row').removeClass('small_to');
				   $('.attach_row').addClass('small_to');
				   $('.subject_row').addClass('small_to');
				   $('.email_id_info_to').css('display','block');
				   $('.email_id_info_cc').css('display','block');
				   $('.email_id_info_bcc').css('display','none');

	         });
			 	 
		// SUBJECT ROW	
	    $('.subject_row').click(function()
		    {
			       $('.to_row').addClass('small_to');
				   $('.cc_row').addClass('small_to');
				   $('.bcc_row').addClass('small_to');
				   $('.attach_row').addClass('small_to');
				   $('.subject_row').removeClass('small_to');
				   $('.email_id_info_to').css('display','block');
                   $('.email_id_info_cc').css('display','block');
				   $('.email_id_info_bcc').css('display','block');
				   $('.full_cc').css('height','32');
				   $('.combind_email_bcc').addClass('');
				   $('.combind_email_cc').addClass('');
	       });
		// ATTACH ROW	
	    $('.attach_row').click(function()
		    {
			       $('.to_row').addClass('small_to');
				   $('.cc_row').addClass('small_to');
				   $('.bcc_row').addClass('small_to');
				 //  $('.attach_row').addClass('small_to');
				   $('.subject_row').addClass('small_to');
				   $('.attach_row').removeClass('small_to');
				   $('.email_id_info_to').css('display','block');
                   $('.email_id_info_cc').css('display','block');
				   $('.email_id_info_bcc').css('display','block');
				   $('.full_cc').css('height','32');
				   $('.combind_email_bcc').addClass('');
				   $('.combind_email_cc').addClass('');
	       });
	  // ATTACH ROW
		  // $('.bcc_row').click(function()
		   //  {
			
			  //     $('.to_row').addClass('small_to');
				//   $('.cc_row').addClass('small_to');
				//   $('.bcc_row').removeClass('small_to');
				//   $('.attach_row').addClass('small_to');
				//   $('.subject_row').addClass('small_to');
				//   $('.email_id_info_to').css('display','block');
				//   $('.email_id_info_cc').css('display','block');
				//    $('.email_id_info_bcc').css('display','block');
				//   $('.full_cc').css('height','32')
	      //   });
		
			
	   // OTHER TO OPTION 
	   /// FIRST OPTION
	   $('.email_id_info_to').click(function(){
		   if($('.email_id_info_to').css('display')=='block')
		     {
		   
		       $('.composed_input').removeClass('small_to');
			   $('.email_id_info_to').css('display','none');
			     $('.combind_email').removeClass('border_to');
			  
		      }
		   });		
		
		/// SECOND OPTION 
		$('.full_to').click(function(){
		   if($('.email_id_info_to').css('display')=='block')
		     {
		   
		       $('.composed_input').removeClass('small_to');
			   $('.email_id_info_to').css('display','none');
			     $('.combind_email').removeClass('border_to');
			  
		      }
		   });
		
		///  EMAIL ATTACH TO OPTION HERE END HERE 
		
		
		/// ON FOCUS EVENT HERE 
		$('.on_focus').focus(function(){
			
			$('.combind_email').append('<div class="vR"><span class="vN Y7BVp a3q" email="hari@silvereye.co"><div class="vT">Hari Om Srivastava</div><div class="vM"></div></span></div>');
			
			
			});
		
		
		
		
	
	// Full Screen 
	$('.cke_button__maximize').click(function(){
		
		alert('Hi');
		
		});
	
	//CC MAIL OPTION
	$('.cc_link').click(function(){
		
		if($('.cc_row').css('display')=='none')
		{
			$('.cc_row').addClass('full_row');
			$('.cc_link').css('display','none');
			
		}
		else{
			
			$('.cc_row').removeClass('full_row');
			}
		
	});
	
	// BCC MAIL LINK
	$('.bcc_link').click(function(){
	       if($('.bcc_hide').css('display')=='none')
		   {
			   $('.bcc_hide').addClass('full_row');
			  // $('.bcc_link').css('display','none');
			   $('.green_sign').css('display','block');
			   }	
			   else{
				    $('.bcc_hide').removeClass('full_row');
					 $('.green_sign').css('display','none');
				   
				   
				   }   
		
	});
		
		
});

