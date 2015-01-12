



function showHide(event) {
   var ele = document.getElementById("search_form");
   if (ele.style.display == "block") {
       ele.style.display = "none";
		$('.user_information').css('display','none');
		
   }
   else {
        ele.style.display = "block";
		$('.user_information').css('display','none');
   }
	event.stopPropagation();
}
function Hide() {
    var ele = document.getElementById("search_form");
    if (ele.style.display == "block") {
       ele.style.display = "none";
   }
}

function showHidetool() {
    var ele = document.getElementById("search_form_tool");
    if (ele.style.display == "block") {
        ele.style.display = "block";
        ele.style.opacity = "1";
    }
    else {
        ele.style.display = "block";
    }
}

function showHidemenuleft() {
    var ele = document.getElementById("left_margin");
    if (ele.style.display == "block") {
        ele.style.display = "block";
        ele.style.opacity = "1";
    }
    else {
        ele.style.display = "none!important";
    }
}
//----------/// ONLY FOR PAGE FORMAT CHANGE LEFT SIDE--//
function showHidepageleft() {
    var ele = document.getElementById("right_tab");
    if (ele.style.display == "block") {
        ele.style.display = "block";
        ele.style.opacity = "1";
    }
}


function showHidepageright() {
    var ele = document.getElementById("right_tab");
    if (ele.style.display == "block") {
        ele.style.display = "block";
        ele.style.width = "block";
        ele.style.float = "left";
    }
    else {
        ele.style.display = "none";
    }
}

function leftmenutool() {
    var ele = document.getElementById("open");
    if (ele.style.display == "block") {
        ele.style.display = "block";
    }
    else {
        ele.style.display = "block";
    }
}

//$(document).ready(function($){

//$('#accordion-3').dcAccordion({
//eventType: 'click',
//autoClose:false,
//saveState: false,
//disableLink: false,
//showCount: false,
//speed: 'slow'
//});	
//$('.left_margin').css('display','block');
//$('.ac-small').css('height','140px');
//});

jQuery(document).ready(function() {
	

	
	
	window.left_scollx = $(window).height();
	//alert(left_scollx);
	$('.mail_content_1').css('height',left_scollx-104);
	//$('.full_view_content').css('height',left_scollx-71);
	//alert(left_scollx);
	/// INNER MAIL FILE STARED HERE
	var inbox_height = $(window).height();
	var mail_inner_height = inbox_height -170;
	$('.tab_first_content').css('height',mail_inner_height);
	$('#widget').css('height',mail_inner_height + 45 );

	$('.tab_first_content').css('overflow','auto');
	//alert(mail_inner_height);
	/// INNER MAIL FILE END HERE 
	
	//test
	//$('.row_content').click(function(){
		//alert('Hi');
		
      // var rowclick  = 0;
	 // if( rowclick  == 0)
	//  {
	//	  $('.mail_checked').css('input[type="checkbox"]','checkbox')
	//  }
		  //		});
		  
		  $('.down_arrow').click(function(){
			  
			   if($('.search_form_1').css('display')=='none')
			   {
				
				   $('.search_form_1').addClass('search_display');
			   }
			   else
			   {
				   $('.search_form_1').removeClass('search_display');
			   }
			    
			  
			  });
	  
	$('.row_check_box:first-child').click(function(){  
	      
             
		  $(this).parent().parent().parent().parent().toggleClass('selected_row');
		  
		  var count = $('.mail_checked:checked').length;
		  if( count == 0)
		      {
				 $('.row_content').removeClass('selected_row');
			     $('.search_form_tool').css('display', 'none');
				 $('.search_form_tool').removeClass('tool_display');
			  }
			  else if(count == 1)
			  {
				  $('.search_form_tool').css('display', 'block');
				  $('.hidden_option').removeClass('disable');
				  $('.search_form_tool').addClass('tool_display');
		      }
			  
			  else if(count >1)
			  { 
			       $('.search_form_tool').css('display', 'block');
				   $('.hidden_option').addClass('disable');
			  }
		  
		  
	});
    // initiate layout and plugins
    // App.init();
    // Charts.init();
    // Charts.initCharts();
    // Charts.initPieCharts();
    //$('.row_content').click(fortool);
});

function lunchboxOpen(lunchID) {
    document.getElementById('lunch_' + lunchID).style.display = "block";
    document.getElementById('clasp_' + lunchID).innerHTML = "<a href=\"javascript:lunchboxClose('" + lunchID + "');\">Close Lunchbox " + lunchID + "...</a>";
}
function lunchboxClose(lunchID) {
    document.getElementById('lunch_' + lunchID).style.display = "none";
    document.getElementById('clasp_' + lunchID).innerHTML = "<a href=\"javascript:lunchboxOpen('" + lunchID + "');\">Open Lunchbox " + lunchID + "...</a>";
}

// to show and hide mail view panel
// to show and hide mail view panel
function toggleViewPanel() {
	$('#widget').css('height','auto');
	$('.new_flag').css('display','none');
	$('.tab_first_content').removeClass('left_flag');
    //$('.widget_new').addClass('full_scroll');
    $('.widget_new').addClass('full_inbox');
    // $('#widget').css('overflow','hidden')
    $('.widget_new').removeClass('left_mail');
	$('.row_content').addClass('pading_main');
	$('.vsplitbar').removeClass('new_spertator');
	$('.mid_tab').css('top','80');
	$('.top_bottom_1').removeClass('top_margin');
	$('.mail_content').removeClass('new_mail_right_1');
	$('.mail_content').removeClass('mail_con_righ');
	$('.mail_content_1').removeClass('new_mail_light');
	$('.tab_main_div').removeClass('tab_left_con');
	$('.row_first').removeClass('mail_de_op');
	$('.top_tool_information').css('display','block');
	$('.row_left').removeClass('left_view_con')
    $('.mid_tab').attr('style', '');
    $('.mail_content').attr('style', '');
    $('.row_content').removeClass('left_view_mess');
    // reverting back from bottom view        
    //$('.mid_tab').css('height', '85%');
    // reverting back from left view
    $('.mid_tab').css('width', '100%');
    $('.mid_tab').css('float', '');
   // $('.mail_content').css('height', '37%');
    $('.mail_content').css('width', '100%');
    $('.mail_content').css('display', 'none');
    $('.small_image_flag').css('display','block');
    // turn off splitter 
    $('.vsplitbar').remove();
    $('.hsplitbar').remove();
    leftCalled = false;
    bottomCalled = false;
    leftPane = false;
    rightPane = false;
}
// to shift the view panel to left of the mailbox panel
function shiftViewLeft() {
    if (!leftPane) {
        toggleViewPanel();
                //$('.widget_new').addClass('full_scroll');
        $('.widget_new').removeClass('full_inbox');
        $('.new_flag').css('display','block');
        $('.tab_first_content').addClass('left_flag');         
		$('#widget').css('height','auto');
		$('.widget_new').addClass('left_mail');
		//$('#widget').css('overflow','hidden')
		$('.small_image_flag').css('display','none');
		$('.mid_tab').removeClass('bottom_tab');
		$('.row_content').removeClass('pading_main');
		$('.vsplitbar').addClass('new_spertator');
		$('.mid_tab').css('top','46');
		$('.top_tool_information').css('display','none');
		$('.top_bottom_1').removeClass('top_margin');
		$('.mail_content').addClass('new_mail_right_1');
		$('.mail_content_1').addClass('new_mail_light');
		$('.tab_main_div').addClass('tab_left_con');
		
		$('.mail_content').removeClass('mail_right_con');
        $('.mid_tab').css('float', 'left');
		$('.row_left').addClass('left_view_con');
		$('.row_content').addClass('left_view_mess');
		$('.row_first').addClass('mail_de_op');
        //$('.mid_tab').css('width', '50%');

       // $('.mail_content').css('height', '85%');
        //$('.mail_content').css('width', '49%');
        $('.mail_content').css('display', 'block');
		$('.mail_content').css('top','46');
        if (!leftCalled) {
            $('#widget').unbind();
            $('#widget').splitter({type: 'v'});
            leftCalled = true;
        } else {
            $('.vsplitbar').css('display', '');
        }
        $('.hsplitbar').remove();
        bottomCalled = false;
        leftPane = true;
    }
}
var leftCalled = false;
var bottomCalled = false;
var leftPane = false;
var rightPane = false;

function shiftViewBottom() {
    if (!rightPane) {
		$('.mid_tab').addClass('bottom_tab');
		$('.row_content').addClass('pading_main');
		$('.vsplitbar').removeClass('new_spertator');
		$('.mid_tab').css('top','80');
		$('.top_tool_information').css('display','block');
		$('.top_bottom_1').addClass('top_margin');
		$('.mail_content').addClass('mail_right_con');
		$('.mail_content').removeClass('new_mail_right_1');
		$('.mail_content').removeClass('mail_con_righ');
		$('.mail_content_1').removeClass('new_mail_light');
		$('.tab_main_div').removeClass('tab_left_con');
		 $('.small_image_flag').css('display','block');
	    $('.row_first').removeClass('mail_de_op');
		$('.row_left').removeClass('left_view_con')
		$('.row_content').removeClass('left_view_mess');
        toggleViewPanel();
        $('.mid_tab').css('height', '38%');
        $('.mail_content').css('display', 'block');
        //$('.mail_content').css('height', '37%');
		$('.mail_content').css('top','0');
	


        if (!bottomCalled) {
            $('#widget').unbind();
            $('#widget').splitter({type: 'h'});
            bottomCalled = true;

		
        
        } else {
            $('.hsplitbar').css('display', '');
        }
        $('.vsplitbar').remove();
        leftCalled = false;
        rightPane = true;
    }
}

function tabfirst() {

    if ($('.tab_first_content').css('display') == "block")
    {
		$('.tab_first_content').animate({
				height: "0px",
			},{
				duration: 500, 
				specialEasing: {
					height: "linear"
				},
				complete: function() {
					$('.tab_first_content').css('display', 'none');
				}
			}
		);
		
    }else {
		$('.tab_first_content').css('display', 'block');
		$('.tab_first_content').animate({
				height: "300px",
			},{
				duration: 500, 
				specialEasing: {
					height: "linear"
				},
				complete: function() {
					
				}
			}
		);
    }
}



function tabfirst1() {

    if ($('.tab_first_content1').css('display') == "block")
    {

        $('.tab_first_content1').animate({
				height: "0px",
			},{
				duration: 500, 
				specialEasing: {
					height: "linear"
				},
				complete: function() {
					$('.tab_first_content1').css('display', 'none');
				}
			}
		);

    }

    else {

        $('.tab_first_content1').css('display', 'block');
		$('.tab_first_content1').animate({
				height: "300px",
			},{
				duration: 500, 
				specialEasing: {
					height: "linear"
				},
				complete: function() {
					
				}
			}
		);


    }



}



function tabfirst2() {

    if ($('.tab_first_content2').css('display') == "block")
    {

        $('.tab_first_content2').animate({
				height: "0px",
			},{
				duration: 500, 
				specialEasing: {
					height: "linear"
				},
				complete: function() {
					$('.tab_first_content2').css('display', 'none');
				}
			}
		);

    }

    else {

        $('.tab_first_content2').css('display', 'block');
		$('.tab_first_content2').animate({
				height: "300px",
			},{
				duration: 500, 
				specialEasing: {
					height: "linear"
				},
				complete: function() {
					
				}
			}
		);


    }



}

function tabfirst3() {

    if ($('.tab_first_content3').css('display') == "block")
    {

        $('.tab_first_content3').animate({
				height: "0px",
			},{
				duration: 500, 
				specialEasing: {
					height: "linear"
				},
				complete: function() {
					$('.tab_first_content3').css('display', 'none');
				}
			}
		);

    }

    else {

        $('.tab_first_content3').css('display', 'block');
		$('.tab_first_content3').animate({
				height: "300px",
			},{
				duration: 500, 
				specialEasing: {
					height: "linear"
				},
				complete: function() {
					
				}
			}
		);


    }



}


function fortool()
{
    $('.search_form_tool').css('display', 'block');
    $('.row_content').removeClass('selected_row');
    $(this).addClass('selected_row');
}
// comment at 08-11-2014
function userinformation(e)
{

   if ($('.user_information').css('display') == 'none') {
		$('.search_form_1').css('display','none');


        $('.user_information').css('display', 'block');
		$('#search_form').css('display','none');

    }else {
         
		 $('.search_form_1').css('display','none');
        $('.user_information').css('display', 'none');

   }
	e.stopPropagation();
	

}
// USER INFO TOP
$('.new_user').click(function(){
	
	   if($('.user_information').css('display')=='none')
	   {
		 $('.user_information').addClass('user_info_top');
		   
		}
		else
		   {
			$('.user_information').removeClass('user_info_top')
			}
	
	});

function option_here()
{
    if ($('.mail_down_option').css('display') == "none")
    {
        $('.mail_down_option').css('display', 'block');
    }

    else {

        $('.mail_down_option').css('display', 'none');

    }

}



function option_here_1(event)
{
    if ($('.mail_down_option_1').css('display') == "none")
    {
        $('.mail_down_option_1').css('display', 'block');
    }

    else {

        $('.mail_down_option_1').css('display', 'none');

    }
	event.stopPropagation();

}



function mail_forward()
{

    if ($('.your_option').css('display') == "block")
    {

        $('.your_option').css('display', 'none');
        $('.mail_forward').css('display', 'block');


    }

    else {
        $('.your_option').css('display', 'block');
        $('.mail_forward').css('display', 'none');

    }



}


function mail_forward_1()
{

    if ($('.your_option_1').css('display') == "block")
    {

        $('.your_option_1').css('display', 'none');
        $('.mail_forward_1').css('display', 'block');


    }

    else {
        $('.your_option_1').css('display', 'block');
        $('.mail_forward_1').css('display', 'none');

    }



}


function mail_forward_11()
{

    if ($('.your_option_1').css('display') == "block")
    {

        $('.your_option_1').css('display', 'none');
        $('.mail_forward_11').css('display', 'block');


    }

    else {
        $('.your_option_1').css('display', 'block');
        $('.mail_forward_11').css('display', 'none');

    }



}





function down_mail(event)
{


    if ($('.main_bottom_option').css('display') == "none")
    {

        $('.main_bottom_option').css('display', 'block');



    }


    else {

        $('.main_bottom_option').css('display', 'none');


    }

event.stopPropagation();
}



function bootom_forward() {


    if ($('.for_bottom').css('display') == "none")
    {

        $('.for_bottom').css('display', 'block');

    }

    else {


        $('.for_bottom').css('display', 'none');

    }

event.stopPropagation();
}


function mail_to(event) {


    if ($('.to_me').css('display') == "none")
    {

        $('.to_me').css('display', 'block');

    }

    else {

        $('.to_me').css('display', 'none');

    }

event.stopPropagation();

}

/// MOUSE OVER TOOL TIPS 
	var popout=0;
$(document).ready(function(e) {
    $('.cheat_row').mouseover(function(){
		
		$('.chat_info').show();
		$('.chat_info' ).offset({  top:$(this).offset().top - $(this).height(), left: $(this).offset().left + $(this).width()+11 });
		//alert(window.scrollTop()+ window.innerHeight());
		var height = $(this).offset().top + $('.chat_info').height();
		var innerheight =window.innerHeight + window.scrollY;
		if( height > innerheight){
	           
			   $('.chat_info' ).offset({ top:$(this).offset().top - (height-innerheight)});
			  
			}
		
		
		//alert($(this).offset().top + " " + $(this).offset().left); 
		//alert($(this).width());
	});
	
	$('.chat_info').mouseout(function(){
		popout=0;
	        $('.chat_info').hide();
		
		});
		$('.chat_info').mouseover(function(){
			popout=1;
	        $('.chat_info').show();
		
		});
	
	$('.cheat_row').mouseout(function(){
		if(popout==0)
		{
			$('.chat_info').css('display','none');
		}
	});
});	
	
	
	
	
	
	
	
	
	
	/// Chat Search Option Here 
	$('.chat_h_right').click(function(){
			var adjust_height;
		     var chat_search_top = $(this).offset().top +  $('.chat_search').height();
			 var chat_search_1 = window.innerHeight + window.scrollY;
                
		
		if($('.chat_search').css('display')=='none')
		{
			$('.chat_search').css('display','block');
			$('.chat_search_11').css('display','none');
			if( chat_search_top > chat_search_1)
			 {
				 var adjust_height =($(this).offset().top - (chat_search_top - chat_search_1 )) ;
				//$('.chat_search').offset({ top: adjust_height});
				 
			}
			 
			 $('.chat_search').offset( { top:adjust_height,left:$(this).offset().left + $(this).width() + 25 } )
			 $('.chat_info').addClass('chat_info_hide');
		
			}
			else
			{
				

				$('.chat_search').css('display','none');
				$('.chat_info').removeClass('chat_info_hide');
			}

		});
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/// MOUSE OVER TOOL TIPS 
	var popout=0;
$(document).ready(function(e) {
    $('.cheat_row_11').mouseover(function(){
		
		$('.chat_info_1').show();
		$('.chat_info_1' ).offset({  top:$(this).offset().top - $(this).height(), left: $(this).offset().left + $(this).width()+10 });
		//alert(window.scrollTop()+ window.innerHeight());
		var height = $(this).offset().top + $('.chat_info_1').height();
		var innerheight =window.innerHeight + window.scrollY;
		if( height > innerheight){
	           
			   $('.chat_info_1' ).offset({ top:$(this).offset().top - (height-innerheight)});
			  
			}
		
		
		//alert($(this).offset().top + " " + $(this).offset().left); 
		//alert($(this).width());
	});
	
	$('.chat_info_1').mouseout(function(){
		popout=0;
	        $('.chat_info_1').hide();
		
		});
		$('.chat_info_1').mouseover(function(){
			popout=1;
	        $('.chat_info_1').show();
		
		});
	
	$('.cheat_row_11').mouseout(function(){
		if(popout==0)
		{
			$('.chat_info_1').css('display','none');
		}
	});
});	
	
	
	
	
	
	
	
	
	
		/// Chat Downarrow Only Option Here 
	$('.h_drop').click(function(){
		
			var adjust_height;
		     var chat_search_top = $(this).offset().top +  $('.chat_search').height();
			 var chat_search_1 = window.innerHeight + window.scrollY;
             
		
		if($('.chat_search_11').css('display')=='none')
		{
			$('.chat_search').css('display','none');
			$('.chat_search_11').css('display','block');
			if( chat_search_top > chat_search_1)
			 {
				 var adjust_height =($(this).offset().top - (chat_search_top - chat_search_1 )) ;
				//$('.chat_search').offset({ top: adjust_height});
				 
			}
			 
			 $('.chat_search_11').offset( { top:adjust_height,left:$('.chat_h_right').offset().left + 35 } )
		         $('.chat_info').addClass('chat_info_hide');
			}
			else
			{
				
				$('.chat_search_11').css('display','none');
 
				$('.chat_info').removeClass('chat_info_hide');
			}


		});
		
		
		
		
/// CHAT DOWN ARROW EVENT

//// Part 1
$('.chat_down_main ul li').click(function(){
	
	if($('.chat_down_submenu').css('display')=='none'){
		
		    $('.chat_down_submenu').css('display','block');
			$('.chat_downarrow').css('display','none')
		
		}
	
	}
);		

/// Part 2
$('.chat_main_menu').click(function(){
	
	if($('.chat_downarrow').css('display')=='none'){
		$('.chat_downarrow').css('display','block');
		$('.chat_down_submenu').css('display','none');
		
		
		}
	
	
	});
	
	/// Chat Menu Stared Here 
	$('.chat_status_menu').click(function(){
       
	   if($('.chat_status').css('display')=='none'){
		   
		   $('.chat_status').css('display','block');
		   $('.Blocked_status').css('display','none');
		   $('.Invites_status').css('display','none');
		   $('.Share_status').css('display','none');
		   
		   }
	   
	   
	});
		
			/// Invites Stared Here 
	$('.invites_menu').click(function(){
	
	   if($('.Blocked_status').css('display')=='none'){
		   
		   $('.chat_status').css('display','none');
		   $('.Blocked_status').css('display','block');
		   $('.Invites_status').css('display','none');
		   $('.Share_status').css('display','none');
		   
		   }
	});
	
				/// Invites Stared Here 
	$('.blocked_menu').click(function(){
	
	   if($('.Invites_status').css('display')=='none'){
		   
		   $('.chat_status').css('display','none');
		   $('.Blocked_status').css('display','none');
		   $('.Invites_status').css('display','block');
		   $('.Share_status').css('display','none');
		   
		   }

	});
	
					/// Invites Stared Here 
	$('.share_your_menu').click(function(){
	
  if($('.Share_status').css('display')=='none'){
		   
		   $('.chat_status').css('display','none');
		   $('.Blocked_status').css('display','none');
		   $('.Invites_status').css('display','none');
		   $('.Share_status').css('display','block');
		   
		   }
	});
	
	// CHAT SIGH OUT BOX 
	$('.chat_out').click(function(){
		if($('.chat_box_inner').css('display')=='block'){
			
			
			$('.chat_sign_box').css('display','block');
			$('.chat_box_inner').css('display','none');
			$('.chat_search_11').css('display','none')
			
			
			
			}
		
		});
		
		
			// CHAT SIGN IN BOX 
	$('.chat_sign_1_box').click(function(){
		
		if($('.chat_box_inner').css('display')=='none'){
			
			
			$('.chat_sign_box').css('display','none');
			$('.chat_box_inner').css('display','block');
			
			
			
			}
		
		});
		
		///Tool Menu 
		
		
	var popout_1=0;
$(document).ready(function(e) {	
		$('li.right_menu_1').mouseover(function(){
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
			
			});
							
	
    $('li.right_menu_1').mouseout(function(){
	popout_1=0
	
			$('.for_setting_1').hide();
		
    });

	/// MOUSE OVER TOOL TIPS 
    $('.for_setting_1').mouseover(function(){
			popout_1=1;
	        $('.for_setting_1').show();
		
	});
		
	
	$('.for_setting_1').mouseout(function(){
		 if(popout==0)
		{
	       $('.for_setting_1').hide();
		}
		
		});
		
/// Main Height Staed Here 

   $('.mid_tab').height()

	