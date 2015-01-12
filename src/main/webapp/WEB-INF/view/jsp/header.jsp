<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.security.Principal"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
    <head>
   
        <script src="js/jquery-1.8.3.min.js"></script>
        <script src="js/splitter.js"></script>
        <script src="js/sytem-script.js"></script>
      <script src="js/jquery-ui.js"></script>
      <script src="js/jquery.splitter-0.14.0.js"></script>
      <link href='css/compose_new.css' rel='stylesheet' type='text/css' />
    <link type="text/css" rel="stylesheet" href="css/jquery-ui.css"/>
        <link rel="shortcut icon" href="images/favicon.ico"/>
        <link type="text/css" rel="stylesheet" href="css/style.css"/>
        <link type="text/css" rel="stylesheet" href="css/blue.css"/>
        <link type="text/css" rel="stylesheet" href="css/main_css.css"/>
        <link href='css/setting.css' rel='stylesheet' type='text/css' />
        <link href='css/compose_new.css' rel='stylesheet' type='text/css' />
         <link href='css/jquery.splitter-bottom.css' rel='stylesheet' type='text/css' />
		 <script src="js/compose_event.js"></script>
		 <!-----------/// CK EDITOR STARED HERE ------------>
	<script src="ckeditor/ckeditor.js"></script>
	<script src="ckeditor/jquery.js"></script>
    <!-----------/// CK EDITOR END HERE -------------->
       
       <!-----------/// Contact start HERE -------------->
        <!--   <link href='css/contact_css.css' rel='stylesheet' type='text/css' />
		 <script src="js/contact_js.js"></script> -->
       <!-----------/// Contact END HERE -------------->
       
       
       
        <style>
            .vsplitbar {
                width: 5px;
                background: #aaa;
            }
        </style>

        <style>
            #spliter2 .a {
                background-color: #2d2d2d;
            }
            #spliter2 .b {
                background-color: #2d002d;
            }
            #foo {
                background-color: #E92727;
            }
            #x {
                background-color: #EFBD73;
            }
            #y {
                background-color: #EF3e32;
            }
            #b {
                background-color: #73A4EF;
            }
            #bar {
                background-color: #BEE927;
            }
            .vsplitbar {
         width: 2px;
background: #f5f5f5;
margin-top: -20px;
cursor: col-resize !important;
border-left: 1px solid #ccc;
border-right: 1px solid #ccc;
            }
        </style>
        
        
      <script src="js/event.js" type="text/javascript" language="javascript" ></script> 
            <script src="js/tab_event.js" type="text/javascript" language="javascript" ></script> 
            <script type='text/javascript' src='js/jquery.dcjqaccordion.2.7.min.js'></script> 
            <script type="text/javascript">
                                                $(document).ready(function($) {
                                                    $('#accordion-3').dcAccordion({
                                                        eventType: 'click',
                                                        autoClose: false,
                                                        saveState: false,
                                                        disableLink: false,
                                                        showCount: false,
                                                        speed: 'slow'
                                                    });
                                                });
            </script> 

            <link rel="stylesheet" type="text/css" href="css/jquery.jscrollpane.css" />
            <script type="text/javascript" src="js/jquery.jscrollpane.min.js"></script>
            <script type="text/javascript">
                                                $(document).ready(function() {
                                                    if (!$.browser.webkit) {
                                                        $('.container').jScrollPane();
                                                    }
                                                });
            </script>
               
        
        
<script type="text/javascript">
function getWebmailfldr(){
	//alert("meeeeeeeeeeeeeeeeeee");
	var fdrname=document.getElementById('hid_active_fldr').value;
	 $.ajax({
         type: "GET",
         url: "${pageContext.request.contextPath}/getWbmailfolder",
        // data: {'path':folderPath},
         contentType: "application/json",
         success: function (data) {
            // $("#fileSystem").html(data);
           // alert(data);
            $("#folder_div").html(data);
            document.getElementById(fdrname).className = "active_mailbox";
         }
     });
	
}

/* function getWebmailsubfldr(){
	alert("meeeeeeeeeeeeeeeeeeeSub=");
	 $.ajax({
         type: "GET",
         url: "${pageContext.request.contextPath}/getWbmailsubfolder",
        // data: {'path':folderPath},
         contentType: "application/json",
         async: false,
         success: function (data) {
            // $("#fileSystem").html(data);
            alert(data);
         }
     });
	
} */
</script>
<!-- start  bottom view splitter -->
<script>
       jQuery(function($) {
    $(window).on('resize', function() {
        var height = $(window).height()-135;
		//alert(height);
        console.log(height);
		// LEFT VIEW CHAT
		$('#foo').height(height).split({ orientation:'horizontal', limit:50 });
		$('#foo').css('height',height);
        $('#b').height(height / 2)-30;
		//$('.chat_inner_content').height(height / 2);
		
		/* 	$('#widget').height(height).split({ orientation:'horizontal', limit:50 });
		$('#widget').css('height',height);
        $('.mail_right_con').height(height / 2)-30; */
		
    }).trigger('resize');
});

  
    </script>
    <!-- end bottom view splitter -->

<script type="text/javascript">
	function getAltImage(imgid) {
		var pic = document.getElementById(imgid);
		pic.src = "images/blank_man.jpg"
	}
	
	
function getAltImageDisp(imgid) {
	var pic = document.getElementById(imgid);
	pic.src = "images/unnamed.png"
}

function sendmsg() {
	
}
</script>
<script type="text/javascript">

function checkEmail(email) {
	//alert(email);
	var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	return filter.test(email);
}
</script>
<script type="text/javascript">
function mailSend() {
	var to=document.getElementById("hid_to_id").value;
		
	var cc=document.getElementById("hid_cc_id").value;
	
	
	var bcc=document.getElementById("hid_bcc_id").value;
	
	
	var sub=document.getElementById("sub_id").value;
	var cntt = CKEDITOR.instances['editor1'].getData();
	var sts=false;
	if(sub==null || sub=="")
		{
		sts = confirm("The Subject field is empty. Would you like to enter Subject?");
		}
	if(sts==false)
		{
		document.getElementById('mail_sending').style.display= 'block';
		$.ajax({
	         type: "GET",
	         url: "${pageContext.request.contextPath}/sendComposeMail",
	        data: {'to':to, 'cc':cc, 'bcc':bcc, 'sub':sub, 'cntt':cntt},
	         contentType: "application/json",
	         success: function (data) {
	            // $("#fileSystem").html(data);
	            
	            document.getElementById('mail_sending').style.display= 'none';
	            document.getElementById('mail_sent').style.display= 'block';
	            //alert(data);
	            document.getElementById('div_for_compose').style.display= 'none';
	            document.getElementById('div_for_inbox').style.display= 'block';
	            setTimeout( "jQuery('#mail_sent').hide();",3000 );
	         
	         //   $("#folder_div").html(data);
	        //    document.getElementById(fdrname).className = "active_mailbox";
	         }
	     });
		
		
		}
	
}
</script>

<script type="text/javascript">
function changeHBG(nm) {
	//alert(nm);
	$(".header").css("background-image","none");
	$(".header").css("background-color",nm);
	$(".search_button").css("background-image","none");
	$(".search_button").css("background-color",nm);
	$(".ui-widget-header").css("background-image","none");
	$(".ui-widget-header").css("background-color",nm);
}
</script>
</head>
    <body onload="getWebmailfldr(),getWebmailInboxRefresh()"> 
    
    
    
    
    
    
    
    
    




<%
    HttpSession head_hs=request.getSession();
    String act_fldr= head_hs.getAttribute("active_folder").toString();
    String mailid=head_hs.getAttribute("id").toString();
    String fname=head_hs.getAttribute("fname").toString();
    String img_myurl=head_hs.getAttribute("img_myurl").toString();
   
                        String path_img=img_myurl+mailid+".jpg";
                        String path_img_id=mailid+"nomyimage";
                        %>

<div style="top: 0px; left: 0px; width: 100%; background-color: white; height: 100%; position: absolute; z-index: 9999999;" id="div_progress">



<link href="http://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel="stylesheet">
      
      <style>
        div.meter {
		border: 1px solid #b0b0b0;
    border-radius: 3px;
    box-shadow: 0 3px 5px 0 #d3d0d0 inset;
    height: 11px;
    margin-left: 43%;
   /* margin-top: 15%;*/
    position: absolute;
    width: 250px;
    /* viewing purposes */
 
  /* viewing purposes */
  -webkit-box-shadow: inset 0 3px 5px 0 #d3d0d0;
  -moz-box-shadow: inset 0 3px 5px 0 #d3d0d0;
  box-shadow: inset 0 3px 5px 0 #d3d0d0;
  -webkit-border-radius: 3px;
  -moz-border-radius: 3px;
  -ms-border-radius: 3px;
  -o-border-radius: 3px;

}
div.meter span {
  display: block;
  height: 100%;
  animation: grower 19s linear;
  -moz-animation: grower 19s linear;
  -webkit-animation: grower 19s linear;
  -o-animation: grower 19s linear;
  position: relative;
  top: -1px;
  left: -1px;
  -webkit-border-radius: 3px;
  -moz-border-radius: 3px;
  -ms-border-radius: 3px;
  -o-border-radius: 3px;
  border-radius: 3px;
  -webkit-box-shadow: inset 0px 3px 5px 0px rgba(0, 0, 0, 0.2);
  -moz-box-shadow: inset 0px 3px 5px 0px rgba(0, 0, 0, 0.2);
  box-shadow: inset 0px 3px 5px 0px rgba(0, 0, 0, 0.2);
  border: 1px solid #3c84ad;
  background: #6eb2d1;
  background-image: -webkit-gradient(linear, 0 0, 100% 100%, color-stop(0.25, rgba(255, 255, 255, 0.2)), color-stop(0.25, transparent), color-stop(0.5, transparent), color-stop(0.5, rgba(255, 255, 255, 0.2)), color-stop(0.75, rgba(255, 255, 255, 0.2)), color-stop(0.75, transparent), to(transparent));
  background-image: -webkit-linear-gradient(45deg, rgba(255, 255, 255, 0.2) 25%, transparent 25%, transparent 50%, rgba(255, 255, 255, 0.2) 50%, rgba(255, 255, 255, 0.2) 75%, transparent 75%, transparent);
  background-image: -moz-linear-gradient(45deg, rgba(255, 255, 255, 0.2) 25%, transparent 25%, transparent 50%, rgba(255, 255, 255, 0.2) 50%, rgba(255, 255, 255, 0.2) 75%, transparent 75%, transparent);
  background-image: -ms-linear-gradient(45deg, rgba(255, 255, 255, 0.2) 25%, transparent 25%, transparent 50%, rgba(255, 255, 255, 0.2) 50%, rgba(255, 255, 255, 0.2) 75%, transparent 75%, transparent);
  background-image: -o-linear-gradient(45deg, rgba(255, 255, 255, 0.2) 25%, transparent 25%, transparent 50%, rgba(255, 255, 255, 0.2) 50%, rgba(255, 255, 255, 0.2) 75%, transparent 75%, transparent);
  -webkit-background-size: 45px 45px;
  -moz-background-size: 45px 45px;
  -o-background-size: 45px 45px;
  background-size: 45px 45px;
}
div.meter span:before {
  content: '';
  display: block;
  width: 100%;
  height: 50%;
  position: relative;
  top: 50%;
  background: rgba(0, 0, 0, 0.03);
}
div.meter p {
    color: #333;
    font-family: "Helvetica";
    font-size: 15px;
    font-weight: bold;
    line-height: 25px;
    margin: -4px 100px;
    position: absolute;
    text-shadow: 0 1px rgba(255, 255, 255, 0.6);
    top: 0;
}
@keyframes grower {
  0% {
    width: 0%;
  }
}

@-moz-keyframes grower {
  0% {
    width: 0%;
  }
}

@-webkit-keyframes grower {
  0% {
    width: 0%;
  }
}

@-o-keyframes grower {
  0% {
    width: 0%;
  }
}
     </style>
     
   <div style="margin-top: 14%; margin-left: 43%;">  Loading <%=mailid %>...</div>
     <div class="meter">
     
  <span style="width:97%"></span>
  <p></p>
</div>

</div>
  
    
    <input type='hidden' value='<%=act_fldr %>' id='hid_active_fldr' />
    <div id="action_gif" class="my_notification" style="display: none;left: 46%;">
    Loading...
    </div>
     <div id="mail_sending" class="my_notification" style="display: none;left: 46%;">
    Sending...
    </div>
     <div id="mail_sent" class="my_notification" style="display: none;left: 43%;">
    Your message has been sent. 
    </div>
        <div class="minwidth">
            <!----------/// HEADER STARED HERE -------->
            <div class="header">
                <div class="header_inner">
                    <div class="logo"> <img src="images/logo.png" /> </div>
                    <div class="header_right">
                     <div class="main_search_folder">
                        <div class="search_it">
                            <input type="text" onClick="return Hide();" placeholder="Search Here....">
                            <a href="#">
                                <div class="down_arrow" onClick="return showHide(event);"></div>
                            </a> 
                            <!---------- /// SEARCH FROM STARTED HERE -----> 

                            <!-----------/// SEARCH FROM END HERE ---------> 
                            <a href="#">
                                <div class="search_icon"></div>
                            </a> 


                        </div>
                        
                     </div>   
                        <div class="new_user" onClick="userinformation(event);" > <img  src="<%=path_img %>" onerror="getAltImage(this.id)" id="<%=path_img_id %>" /></div>


                        

                    </div>

                </div>

            </div>
            <!----------/// HEADER END HERE ----------> 
            <!---------/// MID CONTENT STARED HERE ---->
            <div class="content"> 
            <div id="search_form" class="search_form_1">
                    <div class="form">
                        <form action="" method="get">
                            <div class="to">
                                <div class="name search_text">Search</div>
                                <a href="#"><div class="search_top">All Mail</div></a>
                                <div class="clear"></div>
                                <div class="name">From</div>
                                <input type="text" name="" value="" class="border input" id="mytext">
                                <div class="name">To</div>
                                <input type="text" name="" value="" class="border input" id="mytext">
                                <div class="name">Subject</div>
                                <input type="text" name="" value="" class="border input" id="mytext">
                                <div class="name">Has the words</div>
                                <input type="text" name="" value="" class="border input" id="mytext">
                                <div class="name">Dosen't have</div>
                                <input type="text" name="" value="" class="border input" id="mytext">
                                <div class="check">
                                    <input name="" type="checkbox" value="" id="mytext">
                                    <span>Has attachment</span></div>
                                <div class="clear"></div>
                                <div class="check check_upper">
                                    <input name="" type="checkbox" value="">
                                    <span>Don't include chats</span></div>
                            </div>
                            <div class="search_button"><a href="#" title="Search"> <span class="search_icon2"> </span></a> </div>
                        </form>
                    </div>
                </div>
                <!-----------/// USER INFORMATION BOX STARED HERE --------->
                <div class="user_information arrow_box">

                    <!----------/// TOP SECTION STARED HERE ------------>
                    <div class="user_top">
                        <a href="#">
                       
                            <img height='96px' width='96px' src="<%=path_img %>" onerror="getAltImage(this.id)" id="<%=path_img_id %>" />
                            <div class="change_images">Change photo</div>
                        </a>

                        <div class="left_top">
                            <%=fname %>
                            <span><%=mailid %></span>
                            <div class="clear_2"></div>
                            <div class="progress_bar">
                             <%
                             int per=0;
                             String per_name="bar5.png";
                             
                                 String qper=(String)request.getAttribute("QuotaPer");
                                 if(qper!=null && !(qper.equals("")))
                                 {
                                	 int pval=Integer.parseInt(qper.trim());
                                	 per=pval;
                                	 if(pval<=5)
                                	 {
                                		 per_name="bar5.png";
                                	 }
                                	 else if(pval<=10)
                                	 {
                                		 per_name="bar10.png";
                                	 }
                                	 else if(pval<=20)
                                	 {
                                		 per_name="bar20.png";
                                	 }
                                	 else if(pval<=30)
                                	 {
                                		 per_name="bar30.png";
                                	 }
                                	 else if(pval<=40)
                                	 {
                                		 per_name="bar40.png";
                                	 }
                                	 else if(pval<=50)
                                	 {
                                		 per_name="bar50.png";
                                	 }
                                	 else if(pval<=60)
                                	 {
                                		 per_name="bar60.png";
                                	 }
                                	 else if(pval<=70)
                                	 {
                                		 per_name="bar70.png";
                                	 }
                                	 else if(pval<=80)
                                	 {
                                		 per_name="bar80.png";
                                	 }
                                	 else if(pval<=85)
                                	 {
                                		 per_name="bar85.png";
                                	 }
                                	 else if(pval<=90)
                                	 {
                                		 per_name="bar90.png";
                                	 }
                                	 else if(pval<=100)
                                	 {
                                		 per_name="bar100.png";
                                	 }
                                	
                                 }
                                 
                                 %>
                                 <img src="images/<%=per_name %>" />
                                 <div class="percentage_value">
                                
                                 <%=per %>%
                                 </div>
                                 <div class="clear"></div>
                            </div>
                               <div style="width: 170px;">
                                <!-- <img src="images/color.png" /> -->
                                <table><tr>
                              <td style="padding-right: 2px;" width="20px"> <a style="cursor: pointer;" name="#5AC8FA" onclick="changeHBG(this.name)"> <img style="width:20px !important; height: 20px;"  src="images/bg1.png" /> </a></td>
                               <td style="padding-right: 2px;" width="20px"><a style="cursor: pointer;" name="#FFCC00" onclick="changeHBG(this.name)"> <img style="width:20px !important; height: 20px;" src="images/bg2.png" /> </a></td>
                               <td style="padding-right: 2px;" width="20px"><a style="cursor: pointer;" name="#FF9500" onclick="changeHBG(this.name)"> <img style="width:20px !important; height: 20px;" src="images/bg3.png" /> </a></td>
                               <td style="padding-right: 2px;" width="20px"><a style="cursor: pointer;" name="#FF2D55" onclick="changeHBG(this.name)"> <img style="width:20px !important; height: 20px;" src="images/bg4.png" /> </a></td>
                               <td style="padding-right: 2px;" width="20px"><a style="cursor: pointer;" name="#007AFF" onclick="changeHBG(this.name)"> <img style="width:20px !important; height: 20px;" src="images/bg5.png" /> </a></td>
                               <td style="padding-right: 2px;" width="20px"><a style="cursor: pointer;" name="#4CD964" onclick="changeHBG(this.name)"> <img style="width:20px !important; height: 20px;" src="images/bg6.png" /> </a></td>
                               <td style="padding-right: 2px;" width="20px"><a style="cursor: pointer;" name="#FF3B30" onclick="changeHBG(this.name)"> <img style="width:20px !important; height: 20px;" src="images/bg7.png" /> </a></td>
                               <td width="20px"><a style="cursor: pointer;" name="#8E8E93" onclick="changeHBG(this.name)"> <img style="width:20px !important; height: 20px;" src="images/bg8.png" /> </a></td>
                               </tr></table>
                               </div>
                        </div>
					</div>
        <!------------/// TOP SECTION END HERE -------------->
        <!----------/// BOTTOM SECTION STARED HERE ------------>
                    <div class="user_bottom">
                     
                            <a href="#">
                            <div class="sing_out">

                                Sign out

                            </div>
                        </a>
                           <a href="#">
                            <div class="sing_out right_space">
<%
//String QutPer=  request.getAttribute("QuotaPer").toString();
%>
                                Profile

                            </div>
                        </a>




                    </div>

                    <!------------/// BOTTOM SECTION END HERE -------------->





                </div>