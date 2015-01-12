



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
    // initiate layout and plugins
    // App.init();
    // Charts.init();
    // Charts.initCharts();
    // Charts.initPieCharts();
    $('.row_content').click(fortool);
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
		$('.chat_info' ).offset({  top:$(this).offset().top - $(this).height() , left: $(this).offset().left + $(this).width() });
		
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
	