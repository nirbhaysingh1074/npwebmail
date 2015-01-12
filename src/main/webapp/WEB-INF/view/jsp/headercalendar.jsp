<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.security.Principal"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
    <head>
 <link rel="shortcut icon" href="images/favicon.ico"/>
<link type="text/css" rel="stylesheet" href="css/style.css"/>
<link type="text/css" rel="stylesheet" href="css/blue.css"/>
<link type="text/css" rel="stylesheet" href="css/jquery-ui.css"/>

<!------<link rel="stylesheet" href="css/jquery-ui.css">--->
<script src="js/jquery-1.8.3.min.js"></script>
<!--<script src="js/jquery-ui.js"></script>-->
<script src="js/splitter.js"></script>
<script src="js/sytem-script.js"></script>
<script src="js/jquery-ui.js"></script> 
<!------/// SCRIPT FOR CALENDER---------->  
<script src="js/calender_js.js"></script>
<link type="text/css" rel="stylesheet" href="css/calender_css.css" />
<!---------/// SCRIPT END ----------------->

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
    <body > 
    
    
    
    
    



<%
   	HttpSession head_hs=request.getSession();
    //String act_con_fldr= head_hs.getAttribute("active_contact").toString();
    String mailid=head_hs.getAttribute("id").toString();
    String fname=head_hs.getAttribute("fname").toString();
    String img_myurl=head_hs.getAttribute("img_myurl").toString();
   
    String path_img=img_myurl+mailid+".jpg";
    String path_img_id=mailid+"nomyimage";
 %>


  
  
    
  <%--  <input type='hidden' value='<%=act_con_fldr %>' id='hid_active_contact_fldr' />  --%>
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
  </body>
  </html>