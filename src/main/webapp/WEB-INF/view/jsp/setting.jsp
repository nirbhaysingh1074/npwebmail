<%@page import="java.util.*"%>
<%@page import="webmail.webservice.client.WebmailClient,webmail.wsdl.*"%>
<html>
<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="images/favicon.ico"/>
<link type="text/css" rel="stylesheet" href="css/style.css"/>
<link type="text/css" rel="stylesheet" href="css/blue.css"/>
<link type="text/css" rel="stylesheet" href="css/jquery-ui.css"/>
<link type="text/css" rel="stylesheet" href="css/main_css.css"/>
<!--------------/// TAB STAED HERE ------------------->

<link href="setting/setting.css" rel="stylesheet" type="text/css" />
<link href="setting/tabcontent.css" rel="stylesheet" type="text/css" />
<!-------------/// TAB STRED HERE  END ----------------->
<!------<link rel="stylesheet" href="css/jquery-ui.css">--->
<script src="js/jquery-1.8.3.min.js"></script>
<script src="setting/tabcontent.js" type="text/javascript"></script>
<!----------/// SETTING JS AND CSS STARED HERE ----------->
<script src="setting/setting_js.js" type="text/javascript"></script>
<link href="setting/setting_css.css" rel="stylesheet" type="text/css" />
<!-----------/// SETTING END HERE ---------------->
<!---------/// ACCOUNT STRED HERE ---------->
<script src="setting/account.js" type="text/javascript"></script>
<link href="setting/account.css" rel="stylesheet" type="text/css" />
<link href="setting/account_new.css" rel="stylesheet" type="text/css" />
<!--------/// ACCOUNT END HERE ----------->
<!--<script src="js/jquery-ui.js"></script>-->
<script src="js/splitter.js"></script>
<script src="js/sytem-script.js"></script>
<script src="js/jquery-ui.js"></script>
<style>
.vsplitbar {
	width: 5px;
	background: #aaa;
}
/*.to:hover{ background:#eee;}*/
.r_top{ padding-top:0px !important;}
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
 <style type="text/css">
   

 .short{
	color:#FF0000;
}

 .weak{
	color:#E66C2C;
}

 .good{
	color:#2D98F3;
}

 .strong{
	color:#006400;
}
   </style>
</head>
<body>
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
          </a> </div>
      </div>
      <div class="new_user" onClick="userinformation(event);" ><img src="images/photo.jpg" class="img" /></div>
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
          <a href="#">
          <div class="search_top">All Mail</div>
          </a>
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
  
  <!-------/// SEARCH FOR FILTER STRED HERE -------------->
  
  
 
  
  <!------/// SEARCH FOR FILTER END -------------->
  
  <!-----------/// USER INFORMATION BOX STARED HERE --------->
  <div class="user_information arrow_box"> 
    
    <!----------/// TOP SECTION STARED HERE ------------>
    <div class="user_top"> <a href="#"> <img src="images/photo_1.jpg" />
      <div class="change_images">Change photo</div>
      </a>
      <div class="left_top"> Hariom Srivastava <span>hariom15791@gmail.com</span>
        <div class="clear_2"></div>
        <div class="progress_bar"> <img src="images/Progress_Bar.png" />
          <div class="percentage_value"></div>
          <div class="clear"></div>
        </div>
        <div class="color_pad"><img src="images/color.png" /></div>
      </div>
    </div>
    <!------------/// TOP SECTION END HERE --------------> 
    <!----------/// BOTTOM SECTION STARED HERE ------------>
    <div class="user_bottom"> <a href="#">
      <div class="sing_out"> Sign out </div>
      </a> <a href="#">
      <div class="sing_out right_space"> Profile </div>
      </a> </div>
    
    <!------------/// BOTTOM SECTION END HERE --------------> 
    
  </div>
  <!---------------/// USER INFORMATION BOX END HERE -------------> 
  <!------/// SEARCH BOX STARED HERE ---------> 
  
  <!------/// SEARCH BOX END HERE -----------> 
  
  <!-----/// LEFT PANEL STARED HERE ------->
  <div class="left-pane" id="MySplitter"> 
    
    <!--------------///// WHEN LEFT IS OPEN STARED HERE ----->
    <div class="left_open"> 
      <!------- /// LEFT TOP STARED HERE ------->
      <div class="top_left"> <a href="#">
        <div class="top_right_icon" id="hide_left"> <img src="images/open_state.png" /> </div>
        </a> </div>
      
      <!------- /// LEFT TOP END HERE ------->
      <div class="clear"></div>
      <!-------/// LEFT MID CONTENT STARED HERE --------->
      <div class="left_tab_content">
        <div class="left_three_box">
          <ul class="left_margin" style="display: block;">
            <li><a href="#" class="active_mailbox">Inbox &nbsp;<span>(4)</span></a></li>
            <li><a href="#">Drafts</a></li>
            <li><a href="#">Sent Mail</a></li>
            <li><a href="#" >Spam</a></li>
            <li><a href="#">Trash</a></li>
            <div class="subfolder_create">
              <p title="All Project Related To Working">All Project Related To Working</p>
              <p>Backup Folder</p>
            </div>
            <li class="bottom dcjq-parent-li"> <a href="#" class="dcjq-parent active" style="padding-left: 9px;">Interesting css codes<span class="dcjq-icon"></span></a>
              <ul style="display: block;" class="subfolder_onhover">
                <li class="dcjq-parent-li"><a href="#" class="dcjq-parent active sub_folder_inner">Css Animation<span class="dcjq-icon"></span></a>
                  <ul class="left_margin" style="display: block;">
                    <li><a href="#">Animation Part 1</a></li>
                    <li><a href="#">Animation Part 2</a></li>
                    <li><a href="#">Animation Part 3</a></li>
                    <li><a href="#">Animation Part 4</a></li>
                    <li><a href="#">Animation Part 1</a></li>
                    <li><a href="#">Animation Part 2</a></li>
                    <li><a href="#">Animation Part 3</a></li>
                    <li><a href="#">Animation Part 4</a></li>
                    <li><a href="#">Animation Part 1</a></li>
                    <li><a href="#">Animation Part 2</a></li>
                    <li><a href="#">Animation Part 3</a></li>
                    <li><a href="#">Animation Part 4</a></li>
                    <li><a href="#">Animation Part 1</a></li>
                    <li><a href="#">Animation Part 2</a></li>
                    <li><a href="#">Animation Part 3</a></li>
                    <li><a href="#">Animation Part 4</a></li>
                    <li><a href="#">Animation Part 1</a></li>
                    <li><a href="#">Animation Part 2</a></li>
                    <li><a href="#">Animation Part 3</a></li>
                    <li><a href="#">Animation Part 4</a></li>
                    <li><a href="#">Animation Part 1</a></li>
                    <li><a href="#">Animation Part 2</a></li>
                    <li><a href="#">Animation Part 3</a></li>
                    <li><a href="#">Animation Part 4</a></li>
                    <li><a href="#">Animation Part 1</a></li>
                    <li><a href="#">Animation Part 2</a></li>
                    <li><a href="#">Animation Part 3</a></li>
                    <li><a href="#">Animation Part 4</a></li>
                    <li><a href="#">Animation Part 1</a></li>
                    <li><a href="#">Animation Part 2</a></li>
                    <li><a href="#">Animation Part 3</a></li>
                    <li><a href="#">Animation Part 4</a></li>
                  </ul>
                </li>
              </ul>
            </li>
            <li><a href="#"></a></li>
          </ul>
        </div>
        <div class="chat_box">
          <div class="chat_box_inner">
            <div class="chat_heading">
              <div class="chat_h_left"> <img src="images/photo.jpg" class="h_name" />
                <div class="chat_h_name">Hariom Srivastava</div>
                <div class="h_drop"><img src="images/down_mail.png" /></div>
              </div>
              <div class="chat_h_right"> <img src="images/search_1.jpg" /> </div>
            </div>
            <div class="chat_inner_content"> 
              <!---------------/// FIRST ROW STARTED HERE --------->
              <div class="cheat_row">
                <div class="small_images"> <img src="images/blank_man.jpg" /> </div>
                <div class="contact_information">
                  <p><strong>Jonathan Smith</strong><br>
                    Work for fun</p>
                </div>
                <div class="online_file"> <img src="images/online_file.png"> </div>
              </div>
              <!-------------/// Chat Info Box Stared here --------> 
              
              <!------------//// Chat Info Box End Here -----------> 
              <!--------------/// FIRST ROW END HERE ---------------> 
              
              <!---------------/// FIRST ROW STARTED HERE --------->
              <div class="cheat_row">
                <div class="small_images"> <img src="images/blank_man.jpg" /> </div>
                <div class="contact_information">
                  <p><strong>Jonathan Smith</strong><br>
                    Work for fun</p>
                </div>
                <div class="online_file"> <img src="images/online_file.png"> </div>
              </div>
              <!--------------/// FIRST ROW END HERE ---------------> 
              <!---------------/// FIRST ROW STARTED HERE --------->
              <div class="cheat_row">
                <div class="small_images"> <img src="images/blank_man.jpg" /> </div>
                <div class="contact_information">
                  <p><strong>Jonathan Smith</strong><br>
                    Work for fun</p>
                </div>
                <div class="online_file"> <img src="images/online_file.png"> </div>
              </div>
              <!--------------/// FIRST ROW END HERE ---------------> 
              <!---------------/// FIRST ROW STARTED HERE --------->
              <div class="cheat_row">
                <div class="small_images"> <img src="images/blank_man.jpg" /> </div>
                <div class="contact_information">
                  <p><strong>Jonathan Smith</strong><br>
                    Work for fun</p>
                </div>
                <div class="online_file"> <img src="images/online_file.png"> </div>
              </div>
              <!--------------/// FIRST ROW END HERE ---------------> 
              <!---------------/// FIRST ROW STARTED HERE --------->
              <div class="cheat_row">
                <div class="small_images"> <img src="images/blank_man.jpg" /> </div>
                <div class="contact_information">
                  <p><strong>Jonathan Smith</strong><br>
                    Work for fun</p>
                </div>
                <div class="online_file"> <img src="images/online_file.png"> </div>
              </div>
              <!--------------/// FIRST ROW END HERE ---------------> 
              <!---------------/// FIRST ROW STARTED HERE --------->
              <div class="cheat_row">
                <div class="small_images"> <img src="images/blank_man.jpg" /> </div>
                <div class="contact_information">
                  <p><strong>Jonathan Smith</strong><br>
                    Work for fun</p>
                </div>
                <div class="online_file"> <img src="images/online_file.png"> </div>
              </div>
              <!--------------/// FIRST ROW END HERE ---------------> 
              <!---------------/// FIRST ROW STARTED HERE --------->
              <div class="cheat_row">
                <div class="small_images"> <img src="images/blank_man.jpg" /> </div>
                <div class="contact_information">
                  <p><strong>Jonathan Smith</strong><br>
                    Work for fun</p>
                </div>
                <div class="online_file"> <img src="images/online_file.png"> </div>
              </div>
              <!--------------/// FIRST ROW END HERE ---------------> 
              <!---------------/// FIRST ROW STARTED HERE --------->
              <div class="cheat_row">
                <div class="small_images"> <img src="images/blank_man.jpg" /> </div>
                <div class="contact_information">
                  <p><strong>Jonathan Smith</strong><br>
                    Work for fun</p>
                </div>
                <div class="online_file"> <img src="images/online_file.png"> </div>
              </div>
              <!--------------/// FIRST ROW END HERE ---------------> 
              
            </div>
          </div>
          <!---------------//// CHAT SINGH IN ----------->
          <div class="chat_sign_box">
            <div class="chat_sign_images"> <img src="images/chat-4-xxl.png" /> </div>
            <div class="chat_sign_1_box">Sign in</div>
          </div>
          <!-----------------//// CHAT SINGH IN END ------->
          
          <div class="all_chat_option">
            <div class="chat_info arrow-left">
              <div class="chat_info_left">
                <p class="name">Hariom Srivastava</p>
                <span>hari@silvereye.co</span>
                <p class="com">Opportunities don't happen,you create them..</p>
              </div>
              <div class="chat_info_right"> <img src="images/photo_1.jpg" /> </div>
              <div class="clear"></div>
              <div class="bottom_option"> 
                <!-------/// Bottom _Left_part--->
                <div class="left_bottom">
                  <ul>
                    <li><a href="#">Contact info</a></li>
                    <li><a href="#">Emails</a></li>
                  </ul>
                </div>
                <!----------/// Bottom Left Part End ---> 
                <!--------/// Bottom Right part Stared Here ------>
                <div class="right_bottom">
                  <ul>
                    <li class="chat_mail"><a href="#"></a></li>
                    <li class="contact_mail"><a href="#"></a></li>
                    <li><a href="#"></a></li>
                  </ul>
                </div>
                <!---------------/// Bottom  Right Part End Here --------> 
                
              </div>
            </div>
            
            <!-----------------//// Chat Search option Here --------->
            <div class="chat_info_1 arrow-left">
              <div class="chat_info_left">
                <p class="name">Hariom Srivastava</p>
                <span>hari@silvereye.co</span>
                <p class="com">Opportunities don't happen,you create them..</p>
              </div>
              <div class="chat_info_right"> <img src="images/photo_1.jpg" /> </div>
              <div class="clear"></div>
              <div class="bottom_option"> 
                <!-------/// Bottom _Left_part--->
                <div class="left_bottom">
                  <ul>
                    <li><a href="#">Add to contacts</a></li>
                    <li><a href="#">Emails</a></li>
                  </ul>
                </div>
                <!----------/// Bottom Left Part End ---> 
                <!--------/// Bottom Right part Stared Here ------>
                <div class="right_bottom">
                  <ul>
                    <li class="chat_mail"><a href="#"></a></li>
                    <li class="contact_mail"><a href="#"></a></li>
                    <li><a href="#"></a></li>
                  </ul>
                </div>
                <!---------------/// Bottom  Right Part End Here --------> 
                
              </div>
            </div>
            <!---------------/// Chat Search End Here -----------> 
            <!-------/// Chat Search option--------->
            <div class="chat_search">
              <div class="heading_caht">
                <input type="text" placeholder="Search for people" />
              </div>
              <!-----//// Chat Search Content ----->
              <div class="chat_search_content"> 
                
                <!------------//// FIRST ROW --------->
                <div class="cheat_row_11">
                  <div class="small_images"> <img src="images/blank_man.jpg"> </div>
                  <div class="contact_information">
                    <p><strong>Jonathan Smith</strong><br>
                      Work for fun</p>
                  </div>
                  <div class="online_file"> <img src="images/online_file.png"> </div>
                </div>
                <!-----------/// FIRST ROW --------------> 
                <!------------//// FIRST ROW --------->
                <div class="cheat_row_11">
                  <div class="small_images"> <img src="images/blank_man.jpg"> </div>
                  <div class="contact_information">
                    <p><strong>Jonathan Smith</strong><br>
                      Work for fun</p>
                  </div>
                  <div class="online_file"> <img src="images/online_file.png"> </div>
                </div>
                <!-----------/// FIRST ROW --------------> 
                <!------------//// FIRST ROW --------->
                <div class="cheat_row_11">
                  <div class="small_images"> <img src="images/blank_man.jpg"> </div>
                  <div class="contact_information">
                    <p><strong>Jonathan Smith</strong><br>
                      Work for fun</p>
                  </div>
                  <div class="online_file"> <img src="images/online_file.png"> </div>
                </div>
                <!-----------/// FIRST ROW --------------> 
                <!------------//// FIRST ROW --------->
                <div class="cheat_row_11">
                  <div class="small_images"> <img src="images/blank_man.jpg"> </div>
                  <div class="contact_information">
                    <p><strong>Jonathan Smith</strong><br>
                      Work for fun</p>
                  </div>
                  <div class="online_file"> <img src="images/online_file.png"> </div>
                </div>
                <!-----------/// FIRST ROW --------------> 
                <!------------//// FIRST ROW --------->
                <div class="cheat_row_11">
                  <div class="small_images"> <img src="images/blank_man.jpg"> </div>
                  <div class="contact_information">
                    <p><strong>Jonathan Smith</strong><br>
                      Work for fun</p>
                  </div>
                  <div class="online_file"> <img src="images/online_file.png"> </div>
                </div>
                <!-----------/// FIRST ROW --------------> 
                <!------------//// FIRST ROW --------->
                <div class="cheat_row_11">
                  <div class="small_images"> <img src="images/blank_man.jpg"> </div>
                  <div class="contact_information">
                    <p><strong>Jonathan Smith</strong><br>
                      Work for fun</p>
                  </div>
                  <div class="online_file"> <img src="images/online_file.png"> </div>
                </div>
                <!-----------/// FIRST ROW --------------> 
                <!------------//// FIRST ROW --------->
                <div class="cheat_row_11">
                  <div class="small_images"> <img src="images/blank_man.jpg"> </div>
                  <div class="contact_information">
                    <p><strong>Jonathan Smith</strong><br>
                      Work for fun</p>
                  </div>
                  <div class="online_file"> <img src="images/online_file.png"> </div>
                </div>
                <!-----------/// FIRST ROW --------------> 
                <!------------//// FIRST ROW --------->
                <div class="cheat_row_11">
                  <div class="small_images"> <img src="images/blank_man.jpg"> </div>
                  <div class="contact_information">
                    <p><strong>Jonathan Smith</strong><br>
                      Work for fun</p>
                  </div>
                  <div class="online_file"> <img src="images/online_file.png"> </div>
                </div>
                <!-----------/// FIRST ROW --------------> 
                <!------------//// FIRST ROW --------->
                <div class="cheat_row_11">
                  <div class="small_images"> <img src="images/blank_man.jpg"> </div>
                  <div class="contact_information">
                    <p><strong>Jonathan Smith</strong><br>
                      Work for fun</p>
                  </div>
                  <div class="online_file"> <img src="images/online_file.png"> </div>
                </div>
                <!-----------/// FIRST ROW --------------> 
                <!------------//// FIRST ROW --------->
                <div class="cheat_row_11">
                  <div class="small_images"> <img src="images/blank_man.jpg"> </div>
                  <div class="contact_information">
                    <p><strong>Jonathan Smith</strong><br>
                      Work for fun</p>
                  </div>
                  <div class="online_file"> <img src="images/online_file.png"> </div>
                </div>
                <!-----------/// FIRST ROW --------------> 
                <!------------//// FIRST ROW --------->
                <div class="cheat_row_11">
                  <div class="small_images"> <img src="images/blank_man.jpg"> </div>
                  <div class="contact_information">
                    <p><strong>Jonathan Smith</strong><br>
                      Work for fun</p>
                  </div>
                  <div class="online_file"> <img src="images/online_file.png"> </div>
                </div>
                <!-----------/// FIRST ROW --------------> 
                <!------------//// FIRST ROW --------->
                <div class="cheat_row_11">
                  <div class="small_images"> <img src="images/blank_man.jpg"> </div>
                  <div class="contact_information">
                    <p><strong>Jonathan Smith</strong><br>
                      Work for fun</p>
                  </div>
                  <div class="online_file"> <img src="images/online_file.png"> </div>
                </div>
                <!-----------/// FIRST ROW --------------> 
                <!------------//// FIRST ROW --------->
                <div class="cheat_row_11">
                  <div class="small_images"> <img src="images/blank_man.jpg"> </div>
                  <div class="contact_information">
                    <p><strong>Jonathan Smith</strong><br>
                      Work for fun</p>
                  </div>
                  <div class="online_file"> <img src="images/online_file.png"> </div>
                </div>
                <!-----------/// FIRST ROW --------------> 
                <!------------//// FIRST ROW --------->
                <div class="cheat_row_11">
                  <div class="small_images"> <img src="images/blank_man.jpg"> </div>
                  <div class="contact_information">
                    <p><strong>Jonathan Smith</strong><br>
                      Work for fun</p>
                  </div>
                  <div class="online_file"> <img src="images/online_file.png"> </div>
                </div>
                <!-----------/// FIRST ROW --------------> 
                <!------------//// FIRST ROW --------->
                <div class="cheat_row_11">
                  <div class="small_images"> <img src="images/blank_man.jpg"> </div>
                  <div class="contact_information">
                    <p><strong>Jonathan Smith</strong><br>
                      Work for fun</p>
                  </div>
                  <div class="online_file"> <img src="images/online_file.png"> </div>
                </div>
                <!-----------/// FIRST ROW --------------> 
                <!------------//// FIRST ROW --------->
                <div class="cheat_row_11">
                  <div class="small_images"> <img src="images/blank_man.jpg"> </div>
                  <div class="contact_information">
                    <p><strong>Jonathan Smith</strong><br>
                      Work for fun</p>
                  </div>
                  <div class="online_file"> <img src="images/online_file.png"> </div>
                </div>
                <!-----------/// FIRST ROW --------------> 
                <!------------//// FIRST ROW --------->
                <div class="cheat_row_11">
                  <div class="small_images"> <img src="images/blank_man.jpg"> </div>
                  <div class="contact_information">
                    <p><strong>Jonathan Smith</strong><br>
                      Work for fun</p>
                  </div>
                  <div class="online_file"> <img src="images/online_file.png"> </div>
                </div>
                <!-----------/// FIRST ROW --------------> 
                <!------------//// FIRST ROW --------->
                <div class="cheat_row_11">
                  <div class="small_images"> <img src="images/blank_man.jpg"> </div>
                  <div class="contact_information">
                    <p><strong>Jonathan Smith</strong><br>
                      Work for fun</p>
                  </div>
                  <div class="online_file"> <img src="images/online_file.png"> </div>
                </div>
                <!-----------/// FIRST ROW --------------> 
                
              </div>
              <!--------/// Chat Search End --------> 
              
            </div>
            <!------------/// Chat Search Option End ------> 
            <!-------/// Chat Downarrow option--------->
            <div class="chat_search_11"> 
              
              <!-----//// Chat Search Content ----->
              <div class="chat_downarrow"> 
                <!--------------//// Chat Down Main Page ------------->
                <div class="chat_down_main">
                  <div class="chat_down_top">
                    <div class="chat_down_left"> <img src="images/photo.jpg" /> </div>
                    <div class="chat_down_right"> Hariom Srivastava <span>hari@silvereye.co</span> </div>
                    <div class="clear"></div>
                  </div>
                  <div class="clear"></div>
                  <ul>
                    <li class="chat_status_menu"><a href="#" >Chat&nbsp;Status </a></li>
                    <li class="invites_menu"><a href="#">Invites</a></li>
                    <li class="blocked_menu"><a href="#">Blocked&nbsp;People </a></li>
                    <li class="share_your_menu"><a href="#"> Share&nbsp;your&nbsp;status </a></li>
                  </ul>
                  <div class="clear"></div>
                  <div class="chat_out">Sign out of Chat</div>
                </div>
                <!------------------/// Chat Down Menu End -------------> 
                
              </div>
              <!--------/// Chat Search End --------> 
              <!------------/// Chat Sub menu ----------->
              <div class="chat_down_submenu"> 
                <!-------------// Chat Status box Stared here----------->
                <div class="chat_status"> 
                  <!--------///Chat Haeding ---->
                  <div class="chat_subheading">
                    <div class="chat_main_menu"><img src="images/portlet-remove-icon.png"/></div>
                    <p>Chat Status </p>
                    <div class="clear"></div>
                  </div>
                  <!-----------/// Chat Heading End Here -----> 
                  <!----------/// Chat Status Content ------->
                  <div class="chat_status_content">
                    <ul>
                      <li>
                        <input type="radio" />
                        <span>Online</span>
                        <div class="online"></div>
                      </li>
                      <li>
                        <input type="radio" />
                        <span>Offline</span>
                        <div class="offline"></div>
                      </li>
                      <li>
                        <input type="radio" />
                        <span>Busy</span>
                        <div class="busy"></div>
                      </li>
                      <li>
                        <input type="radio" />
                        <span>Away</span>
                        <div class="away"></div>
                      </li>
                    </ul>
                  </div>
                  <!-----------/// Chat Status  End Here -----> 
                </div>
                <!-------------/// Chat Status Box End Here---------> 
                <!-------------// Invites box Stared here----------->
                <div class="Blocked_status"> 
                  <!--------///Chat Haeding ---->
                  <div class="chat_subheading">
                    <div class="chat_main_menu"><img src="images/portlet-remove-icon.png"/></div>
                    <p>Invites </p>
                    <div class="clear"></div>
                  </div>
                  <!-----------/// Chat Heading End Here -----> 
                  <!----------/// Chat Status Content ------->
                  <div class="chat_status_content"> 
                    <!----------/// Main ROW Content ---------->
                    <div class="chat_row_content"> 
                      <!-----------/// INVITE ROW FIRST STARED HERE ---------->
                      <div class="invite_row"> 
                        <!---------------/// INVITE LEFT PART -------->
                        <div class="invite_left">Rohit Tiwari</div>
                        <!--------------/// INVITE LEFT END HERE --------> 
                        <!---------------/// INVITE RIGHT PART -------->
                        <div class="invite_right"> <a href="#">Unblock</a> </div>
                        <!--------------/// INVITE RIGHT END HERE -------->
                        <div class="clear"></div>
                      </div>
                      <!-------------/// INVITE Row END HERE --------------> 
                      <!-----------/// INVITE ROW FIRST STARED HERE ---------->
                      <div class="invite_row"> 
                        <!---------------/// INVITE LEFT PART -------->
                        <div class="invite_left">Rohit Tiwari</div>
                        <!--------------/// INVITE LEFT END HERE --------> 
                        <!---------------/// INVITE RIGHT PART -------->
                        <div class="invite_right"> <a href="#">Unblock</a> </div>
                        <!--------------/// INVITE RIGHT END HERE -------->
                        <div class="clear"></div>
                      </div>
                      <!-------------/// INVITE Row END HERE --------------> 
                      <!-----------/// INVITE ROW FIRST STARED HERE ---------->
                      <div class="invite_row"> 
                        <!---------------/// INVITE LEFT PART -------->
                        <div class="invite_left">Rohit Tiwari</div>
                        <!--------------/// INVITE LEFT END HERE --------> 
                        <!---------------/// INVITE RIGHT PART -------->
                        <div class="invite_right"> <a href="#">Unblock</a> </div>
                        <!--------------/// INVITE RIGHT END HERE -------->
                        <div class="clear"></div>
                      </div>
                      <!-------------/// INVITE Row END HERE --------------> 
                      <!-----------/// INVITE ROW FIRST STARED HERE ---------->
                      <div class="invite_row"> 
                        <!---------------/// INVITE LEFT PART -------->
                        <div class="invite_left">Rohit Tiwari</div>
                        <!--------------/// INVITE LEFT END HERE --------> 
                        <!---------------/// INVITE RIGHT PART -------->
                        <div class="invite_right"> <a href="#">Unblock</a> </div>
                        <!--------------/// INVITE RIGHT END HERE -------->
                        <div class="clear"></div>
                      </div>
                      <!-------------/// INVITE Row END HERE --------------> 
                    </div>
                    <!----------/// Main Row Content End Here ---------->
                    <div class="clear"></div>
                  </div>
                  <!-----------/// Chat Status  End Here ----->
                  <div class="clear"></div>
                </div>
                <!-------------/// Invites Box End Here---------> 
                <!-------------// Blocked People  box Stared here----------->
                <div class="Invites_status"> 
                  <!--------///Chat Haeding ---->
                  <div class="chat_subheading">
                    <div class="chat_main_menu"><img src="images/portlet-remove-icon.png"/></div>
                    <p>Blocked People</p>
                    <div class="clear"></div>
                  </div>
                  <!-----------/// Chat Heading End Here -----> 
                  <!----------/// Chat Status Content ------->
                  <div class="chat_status_content"> 
                    <!----------/// Main ROW Content ---------->
                    <div class="chat_row_content"> 
                      <!-----------/// INVITE ROW FIRST STARED HERE ---------->
                      <div class="invite_row"> 
                        <!---------------/// INVITE LEFT PART -------->
                        <div class="invite_left">Rohit Tiwari</div>
                        <!--------------/// INVITE LEFT END HERE --------> 
                        <!---------------/// INVITE RIGHT PART -------->
                        <div class="invite_right"> <a href="#">Unblock</a> </div>
                        <!--------------/// INVITE RIGHT END HERE -------->
                        <div class="clear"></div>
                      </div>
                      <!-------------/// INVITE Row END HERE --------------> 
                      <!-----------/// INVITE ROW FIRST STARED HERE ---------->
                      <div class="invite_row"> 
                        <!---------------/// INVITE LEFT PART -------->
                        <div class="invite_left">Rohit Tiwari</div>
                        <!--------------/// INVITE LEFT END HERE --------> 
                        <!---------------/// INVITE RIGHT PART -------->
                        <div class="invite_right"> <a href="#">Unblock</a> </div>
                        <!--------------/// INVITE RIGHT END HERE -------->
                        <div class="clear"></div>
                      </div>
                      <!-------------/// INVITE Row END HERE --------------> 
                      <!-----------/// INVITE ROW FIRST STARED HERE ---------->
                      <div class="invite_row"> 
                        <!---------------/// INVITE LEFT PART -------->
                        <div class="invite_left">Rohit Tiwari</div>
                        <!--------------/// INVITE LEFT END HERE --------> 
                        <!---------------/// INVITE RIGHT PART -------->
                        <div class="invite_right"> <a href="#">Unblock</a> </div>
                        <!--------------/// INVITE RIGHT END HERE -------->
                        <div class="clear"></div>
                      </div>
                      <!-------------/// INVITE Row END HERE --------------> 
                      <!-----------/// INVITE ROW FIRST STARED HERE ---------->
                      <div class="invite_row"> 
                        <!---------------/// INVITE LEFT PART -------->
                        <div class="invite_left">Rohit Tiwari</div>
                        <!--------------/// INVITE LEFT END HERE --------> 
                        <!---------------/// INVITE RIGHT PART -------->
                        <div class="invite_right"> <a href="#">Unblock</a> </div>
                        <!--------------/// INVITE RIGHT END HERE -------->
                        <div class="clear"></div>
                      </div>
                      <!-------------/// INVITE Row END HERE --------------> 
                    </div>
                    <!----------/// Main Row Content End Here ----------> 
                  </div>
                  <!-----------/// Chat Status  End Here -----> 
                </div>
                <!-------------/// Blocked People  Box End Here---------> 
                <!-------------// Blocked People  box Stared here----------->
                <div class="Share_status"> 
                  <!--------///Chat Haeding ---->
                  <div class="chat_subheading">
                    <div class="chat_main_menu"><img src="images/portlet-remove-icon.png"/></div>
                    <p> Share your status </p>
                    <div class="clear"></div>
                  </div>
                  <!-----------/// Chat Heading End Here -----> 
                  <!----------/// Chat Status Content ------->
                  <div class="chat_status_content"> 
                    
                    <!----------/// Chat ---------> 
                    <!----------/// Main ROW Content ---------->
                    <div class="chat_row_content"> 
                      <!-----------/// INVITE ROW FIRST STARED HERE ---------->
                      <div class="invite_row"> 
                        
                        <!---------------/// INVITE LEFT PART -------->
                        <div class="invite_left">Rohit Tiwari</div>
                        <!--------------/// INVITE LEFT END HERE --------> 
                        <!---------------/// INVITE RIGHT PART -------->
                        <div class="invite_right"> <a href="#">Unblock</a> </div>
                        <!--------------/// INVITE RIGHT END HERE -------->
                        <div class="clear"></div>
                      </div>
                      <!-------------/// INVITE Row END HERE --------------> 
                      <!-----------/// INVITE ROW FIRST STARED HERE ---------->
                      <div class="invite_row"> 
                        <!---------------/// INVITE LEFT PART -------->
                        <div class="invite_left">Rohit Tiwari</div>
                        <!--------------/// INVITE LEFT END HERE --------> 
                        <!---------------/// INVITE RIGHT PART -------->
                        <div class="invite_right"> <a href="#">Unblock</a> </div>
                        <!--------------/// INVITE RIGHT END HERE -------->
                        <div class="clear"></div>
                      </div>
                      <!-------------/// INVITE Row END HERE --------------> 
                      <!-----------/// INVITE ROW FIRST STARED HERE ---------->
                      <div class="invite_row"> 
                        <!---------------/// INVITE LEFT PART -------->
                        <div class="invite_left">Rohit Tiwari</div>
                        <!--------------/// INVITE LEFT END HERE --------> 
                        <!---------------/// INVITE RIGHT PART -------->
                        <div class="invite_right"> <a href="#">Unblock</a> </div>
                        <!--------------/// INVITE RIGHT END HERE -------->
                        <div class="clear"></div>
                      </div>
                      <!-------------/// INVITE Row END HERE --------------> 
                      <!-----------/// INVITE ROW FIRST STARED HERE ---------->
                      <div class="invite_row"> 
                        <!---------------/// INVITE LEFT PART -------->
                        <div class="invite_left">Rohit Tiwari</div>
                        
                        <!--------------/// INVITE LEFT END HERE --------> 
                        <!---------------/// INVITE RIGHT PART -------->
                        <div class="invite_right"> <a href="#">Unblock</a> </div>
                        <!--------------/// INVITE RIGHT END HERE -------->
                        <div class="clear"></div>
                      </div>
                      <!-------------/// INVITE Row END HERE --------------> 
                    </div>
                    <!----------/// Main Row Content End Here ----------> 
                    <!----------/// Chat End --------> 
                    
                  </div>
                  <!-----------/// Chat Status  End Here -----> 
                </div>
                <!-------------/// Blocked People  Box End Here---------> 
              </div>
              <!---------------//// Chat Down Sub Menu ----------> 
              
            </div>
            <!------------/// Chat Downarrow Option End ------> 
          </div>
        </div>
      </div>
      
      <!--------/// LEFT MID CONTENT END HERE --------------> 
      
      <!-------/// LEFT BOTTOM ICON STARED HERE --->
      <div id="mailview-bottom2" class="uibox bottom_mail">
        <ul class="background_bottom bootom_icon_ul">
          <li><a href="#"><img src="images/bootom_icon_1.png" class="icon1"></a></li>
          <li><a href="#"><img src="images/bootom_icon_2.png" class="icon_2"></a></li>
          <li><a href="#"><img src="images/bootom_icon_3.png" class="icon_3"></a></li>
          <li><a href="#"><img src="images/bootom_icon_4.png" class="icon_4"></a></li>
          <li><a href="#"><img src="images/bootom_icon_5.png"></a></li>
          <li><a href="#"><img src="images/bootom_icon_6.png"></a></li>
          <div class="clear"></div>
        </ul>
        <div class="clear"></div>
      </div>
      
      <!-------/// LEFT BOTTOM ICON END HERE ---> 
    </div>
    <!--------------///// WHEN LEFT IS OPEN End HERE -----> 
    
  </div>
  <!-----------/// LEFT PANNEL END HERE -------> 
  
  <!-------/// LEFT PANNEL WHEN IT CLOSE ------>
  <div class="left_close">
    <div class="top_left"> <a href="#">
      <div class="top_right_icon" id="show_left"> <img src="images/next_mail.png" /> </div>
      </a> </div>
    <div class="mid_close_content">
      <ul>
        <li> <a href="#"><img src="images/inbox_blue.png" /></a> </li>
        <li> <a href="#"><img src="images/sent.png" /></a> </li>
        <li> <a href="#"><img src="images/delet.png" /></a> </li>
        <li> <a href="#"><img src="images/all_folder.png" /></a> </li>
      </ul>
    </div>
    <div id="mailview-bottom1" class="uibox close_bottom">
      <ul>
        <li><a href="#"><img src="images/photo.jpg" /></a></li>
        <li><a href="#"><img src="images/blank_man.jpg" /></a></li>
        <li><a href="#"><img src="images/blank_man.jpg" /></a></li>
        <li><a href="#"><img src="images/blank_man.jpg" /></a></li>
        <li><a href="#"><img src="images/blank_man.jpg" /></a></li>
        <li><a href="#"><img src="images/blank_man.jpg" /></a></li>
        <li><a href="#"><img src="images/blank_man.jpg" /></a></li>
        <div class="clear"></div>
      </ul>
      <div class="clear"></div>
    </div>
  </div>
  <!-------/// LEFT PANNEL WHEN IT CLOSE END ------> 
  
  <!------/// RIGHT PANNEL ONLY FOR TOOL-------->
  
  <div class="right-pane">
  <div class="right_top_pannel setting_pages"> 

                        <!-------// RIGHT TOP TOOL END HERE -------->
                        <div class="for_tool">
                               <h2 class="dt">Settings</h2> 
                            <!---- RIGHT TOOL STARTED HERE ---->
                            <!--------/// Main Right Tool Stared Here -------->
                           <div class="right_tool_part">
                            <div class="right_tool"> <a href="#"> <img src="images/reload.png"> </a> </div>
                            <div class="right_tool_1">
                                <ul id="menu">
                                    <li> <img src="images/setting_toll.png" class="four_margin"></li>
                                    <li class="right_menu_1">
                                   <a href="#"> <img src="images/open_sub_menu.png" style="margin-left: 8px !important;"></a>
                                     <!--- <ul >
                                            <li> <a href="#">Settings</a></li>
                                            <li><a href="#">Themes</a></li>
                                            <li> <a href="#">Help</a></li>
                                        </ul>
                                    </li>
                                </ul>-->
                                
                            </div>
                            <div class="right_tool_1">
                                <ul id="menu">
                                    <li> <img src="images/multi_level.png"> <a href="#" class="sub_menu_link"><img src="images/open_sub_menu.png" style="margin-left: 8px;"></a>
                                        <ul>
                                            <li> <a href="#" onclick="toggleViewPanel()">Off</a></li>
                                            <li><a href="#" onclick="shiftViewLeft();">Left view</a></li>
                                            <li> <a href="#" onclick="shiftViewBottom();">Bottom view</a></li>
                                        </ul>
                                    </li>
                                </ul>
                            </div>
                            <a href="#">
                                <div class="right_tool_1"> <img src="images/next_mail.png" class="next_imag"> </div>
                            </a> <a href="#">
                                <div class="right_tool_1"> <img src="images/privious_mail.png" class="next_imag"> </div>
                            </a>
                            <div class="right_tool_2" style="margin-left: -14px;line-height: 29px;"> 1-10 of 300 </div>
                            </div>
                            
                            <!-------------------/// Main Right Tool End Here ------------->
                        </div>
                        <!------ RIGHT TOOL END HERE TOP ---->

                        
                    </div>
 
    <%
    WebmailClient webmailClient=(WebmailClient) request.getAttribute("webmailClient");
 	String fldr_lst=(String) request.getAttribute("fldr_lst");
 	HttpSession hs=request.getSession();
	String host=(String)hs.getAttribute("host");
	String id=(String)hs.getAttribute("id");
	String pass=(String)hs.getAttribute("pass");
	String port=(String)hs.getAttribute("port");
	 HashMap hm_fldr = new HashMap();
	 HashMap hm_fldr_path = new HashMap();
	 int hm_i=0;
    %>
    <!-----------------------------/// Main Cointer Stared here --------------->
    <div id="widget_setting" class="setting_scroll" > 
      <!---------------//// SETTING PAGES CONTENT STARED HERE ------------->
         <div class="right_top_pannel main_tab"> 
      <!---------------/// SETTING TAB STARED HERE -------------->
      <ul class="tabs" data-persist="true">
        <li><a href="#view1">General</a></li>
        <li><a href="#view2">Folders</a></li>
        <li><a href="#view3">Identities</a></li>
        <li><a href="#view4">Password</a></li>
        <li><a href="#view5">Filters</a></li>
       <!--  <li><a href="#view6">Forwarding and POP/IMAP</a></li>
        <li><a href="#view7">Chat</a></li>
        <li><a href="#view8">Plugins</a></li>
        <li><a href="#view9">Offline</a></li>
        <li><a href="#view10">Themes</a></li> -->
      </ul>
      <!-----------/// SETTING TAB END HERE ------------------> 
      
    </div>
      
      <div class="tabcontents">
        <div id="view1">
          <table cellpadding="0" class="cf" width="100%">
            <tbody>
              <tr class="r7">
                <td class="r8"><span class="rc">Language:</span></td>
                <td class="r9"><div><b>Silvereye IT Solutions Private Limited Mail display language:</b>
                    <select  class="a5p">
                      <option >Afrikaans</option>
                      <option >Azərbaycanca</option>
                      <option >Bahasa Indonesia</option>
                      <option >Bahasa Melayu</option>
                      <option >Català</option>
                      <option >Čeština</option>
                      <option >Cymraeg</option>
                      <option >Dansk</option>
                      <option >Deutsch</option>
                      <option >Eesti keel</option>
                      <option >English (UK)</option>
                      <option >English (US)</option>
                      <option >Español</option>
                      <option >Español (Latinoamérica)</option>
                      <option >Euskara</option>
                      <option >Filipino</option>
                      <option >Français</option>
                      <option >Français (Canada)</option>
                      <option >Galego</option>
                      <option >Hrvatski</option>
                      <option >Italiano</option>
                      <option >IsiZulu</option>
                      <option >Íslenska</option>
                      <option >Kiswahili</option>
                      <option >Latviešu</option>
                      <option >Lietuvių</option>
                      <option >Magyar</option>
                      <option >Norsk (Bokmål)</option>
                      <option >Nederlands</option>
                      <option >Polski</option>
                      <option >Português (Brasil)</option>
                      <option >Português (Portugal)</option>
                      <option >Română</option>
                      <option >Slovenčina</option>
                      <option >Slovenščina</option>
                      <option >Suomi</option>
                      <option >Svenska</option>
                      <option >Tiếng Việt</option>
                      <option >Türkçe</option>
                      <option >Ελληνικά</option>
                      <option >Български</option>
                      <option >Монгол</option>
                      <option >Русский</option>
                      <option >Српски</option>
                      <option >Українська</option>
                      <option >Հայերեն</option>
                      <option >&#8235;עברית&#8236;&lrm;</option>
                      <option >&#8235;اردو&#8236;&lrm;</option>
                      <option >&#8235;العربية&#8236;&lrm;</option>
                      <option >&#8235;فارسی&#8236;&lrm;</option>
                      <option >नेपाली</option>
                      <option >मराठी</option>
                      <option >हिन्दी</option>
                      <option >বাংলা</option>
                      <option >ગુજરાતી</option>
                      <option >தமிழ்</option>
                      <option >తెలుగు</option>
                      <option >ಕನ್ನಡ</option>
                      <option >മലയാളം</option>
                      <option >සිංහල</option>
                      <option >ภาษาไทย</option>
                      <option >ພາສາລາວ</option>
                      <option >ქართული</option>
                      <option >አማርኛ (Amharic)</option>
                      <option >ᏣᎳᎩ (Cherokee)</option>
                      <option >ភាសាខ្មែរ</option>
                      <option >中文 (香港)</option>
                      <option >中文 (繁體)</option>
                      <option >中文 (简体)</option>
                      <option >日本語</option>
                      <option >한국어</option>
                    </select>
                    <a href="#" class="sA" target="_blank">Change language settings for other Google products</a></div>
                  <div><span  style="display: none;"> - Seeing squares instead of characters? <a href="#" class="sA" target="_blank">Learn how to fix it.</a></span>
                    <div id=":vu" style="display:"><br>
                      <input type="checkbox"   class="qE" checked="true">
                      <div class="Tn">
                        <label ><span class="rc">Enable input tools</span> - Use various text input tools to type in the language of your choice</label>
                        <span> - <span class="sA">Edit tools</span></span> - <a href="#" class="e" target="_blank" >Learn more</a></div>
                      <br>
                      <table cellspacing="0" cellpadding="0" class="cf ntxFbe">
                        <tbody>
                          <tr class="C7">
                            <td class="C6"><input type="radio"  checked=""></td>
                            <td class="C6"><span class="rS">
                              <label>Right-to-left editing support off</label>
                              </span></td>
                          </tr>
                        </tbody>
                      </table>
                      <table cellspacing="0" cellpadding="0" class="cf ntxFbe">
                        <tbody>
                          <tr class="C7">
                            <td class="C6"><input type="radio"  ></td>
                            <td class="C6"><span class="rS">
                              <label >Right-to-left editing support on</label>
                              </span></td>
                          </tr>
                        </tbody>
                      </table>
                    </div>
                  </div></td>
              </tr>
              <tr class="r7">
                <td class="r8"><span class="rc">Phone numbers:</span></td>
                <td class="r9"><table>
                    <tbody>
                      <tr class="cf rc">
                        <td class="default">Default country code:
                          <select>
                            <option >Afghanistan</option>
                            <option >Albania</option>
                            <option >Algeria</option>
                            <option >American Samoa</option>
                            <option >Andorra</option>
                            <option >Angola</option>
                            <option >Anguilla</option>
                            <option >Antigua and Barbuda</option>
                            <option >Argentina</option>
                            <option >Armenia</option>
                            <option >Aruba</option>
                            <option >Ascension Island</option>
                            <option >Australia</option>
                            <option >Austria</option>
                            <option >Azerbaijan</option>
                            <option >Bahamas</option>
                            <option >Bahrain</option>
                            <option >Bangladesh</option>
                            <option >Barbados</option>
                            <option >Belarus</option>
                            <option >Belgium</option>
                            <option >Belize</option>
                            <option >Benin</option>
                            <option >Bermuda</option>
                            <option >Bhutan</option>
                            <option >Bolivia</option>
                            <option >Bonaire, Sint Eustatius, and Saba</option>
                            <option >Bosnia and Herzegovina</option>
                            <option >Botswana</option>
                            <option >Brazil</option>
                            <option >Brunei</option>
                            <option >Bulgaria</option>
                            <option >Burkina Faso</option>
                            <option >Burundi</option>
                            <option >Cambodia</option>
                            <option >Cameroon</option>
                            <option >Canada</option>
                            <option >Cape Verde</option>
                            <option >Cayman Islands</option>
                            <option >Central African Republic</option>
                            <option >Chad</option>
                            <option >Chile</option>
                            <option>China</option>
                            <option >Colombia</option>
                            <option >Comoros and Mayotte</option>
                            <option >Congo</option>
                            <option >Congo Dem Rep</option>
                            <option >Cook Islands</option>
                            <option >Costa Rica</option>
                            <option >Cote d'Ivoire</option>
                            <option >Croatia</option>
                            <option >Cuba</option>
                            <option >Curaçao</option>
                            <option >Cyprus</option>
                            <option >Czech Republic</option>
                            <option >Denmark</option>
                            <option >Diego Garcia</option>
                            <option >Djibouti</option>
                            <option >Dominica</option>
                            <option >Dominican Republic</option>
                            <option >Ecuador</option>
                            <option >Egypt</option>
                            <option >El Salvador</option>
                            <option >Equatorial Guinea</option>
                            <option >Eritrea</option>
                            <option >Estonia</option>
                            <option >Ethiopia</option>
                            <option >Falkland Islands</option>
                            <option >Faroe Islands</option>
                            <option >Fiji</option>
                            <option >Finland</option>
                            <option >France</option>
                            <option >French Guiana</option>
                            <option >French Polynesia</option>
                            <option >Gabon</option>
                            <option >Gambia</option>
                            <option >Georgia</option>
                            <option >Germany</option>
                            <option >Ghana</option>
                            <option >Gibraltar</option>
                            <option >Greece</option>
                            <option >Greenland</option>
                            <option >Grenada</option>
                            <option >Guadeloupe</option>
                            <option >Guam</option>
                            <option >Guatemala</option>
                            <option >Guinea</option>
                            <option >Guinea Bissau</option>
                            <option >Guyana</option>
                            <option >Haiti</option>
                            <option >Honduras</option>
                            <option >Hong Kong</option>
                            <option >Hungary</option>
                            <option >Iceland</option>
                            <option >India</option>
                            <option >Indonesia</option>
                            <option >Iran</option>
                            <option >Iraq</option>
                            <option >Ireland</option>
                            <option>Israel</option>
                            <option >Italy</option>
                            <option >Jamaica</option>
                            <option >Japan</option>
                            <option >Jordan</option>
                            <option >Kazakhstan</option>
                            <option >Kenya</option>
                            <option >Kiribati</option>
                            <option >Korea, North</option>
                            <option >Korea, South</option>
                            <option >Kuwait</option>
                            <option >Kyrgyzstan</option>
                            <option >Laos</option>
                            <option >Latvia</option>
                            <option >Lebanon</option>
                            <option >Lesotho</option>
                            <option >Liberia</option>
                            <option >Libya</option>
                            <option >Liechtenstein</option>
                            <option >Lithuania</option>
                            <option >Luxembourg</option>
                            <option >Macao</option>
                            <option >Macedonia</option>
                            <option >Madagascar</option>
                            <option >Malawi</option>
                            <option >Malaysia</option>
                            <option >Maldives</option>
                            <option >Mali</option>
                            <option >Malta</option>
                            <option >Marshall Islands</option>
                            <option >Martinique</option>
                            <option >Mauritania</option>
                            <option >Mauritius</option>
                            <option >Mexico</option>
                            <option >Micronesia</option>
                            <option >Moldova</option>
                            <option >Monaco</option>
                            <option >Mongolia</option>
                            <option >Montenegro</option>
                            <option >Montserrat</option>
                            <option >Morocco</option>
                            <option >Mozambique</option>
                            <option >Myanmar</option>
                            <option >Namibia</option>
                            <option >Nauru</option>
                            <option >Nepal</option>
                            <option >Netherlands</option>
                            <option >New Caledonia</option>
                            <option >New Zealand</option>
                            <option >Nicaragua</option>
                            <option >Niger</option>
                            <option >Nigeria</option>
                            <option >Niue</option>
                            <option >Norfolk Island</option>
                            <option >Northern Mariana Islands</option>
                            <option >Norway</option>
                            <option >Oman</option>
                            <option >Pakistan</option>
                            <option >Palau</option>
                            <option >Palestine</option>
                            <option >Panama</option>
                            <option >Papua New Guinea</option>
                            <option >Paraguay</option>
                            <option >Peru</option>
                            <option >Philippines</option>
                            <option >Poland</option>
                            <option >Portugal</option>
                            <option >Puerto Rico</option>
                            <option >Qatar</option>
                            <option >Reunion</option>
                            <option >Romania</option>
                            <option >Russia</option>
                            <option >Rwanda</option>
                            <option >Saint Barthélemy</option>
                            <option >Saint Helena</option>
                            <option >Saint Kitts and Nevis</option>
                            <option >Saint Lucia</option>
                            <option >Saint Martin</option>
                            <option >Saint Pierre and Miquelon</option>
                            <option >Saint Vincent Grenadines</option>
                            <option >Samoa</option>
                            <option >San Marino</option>
                            <option >Sao Tome and Principe</option>
                            <option >Saudi Arabia</option>
                            <option >Senegal</option>
                            <option >Serbia</option>
                            <option >Seychelles</option>
                            <option >Sierra Leone</option>
                            <option >Singapore</option>
                            <option >Sint Maarten</option>
                            <option >Slovakia</option>
                            <option >Slovenia</option>
                            <option >Solomon Islands</option>
                            <option >Somalia</option>
                            <option >South Africa</option>
                            <option >South Sudan</option>
                            <option >Spain</option>
                            <option >Sri Lanka</option>
                            <option >Sudan</option>
                            <option >Suriname</option>
                            <option >Swaziland</option>
                            <option >Sweden</option>
                            <option >Switzerland</option>
                            <option >Syria</option>
                            <option >Taiwan</option>
                            <option >Tajikistan</option>
                            <option >Tanzania</option>
                            <option >Thailand</option>
                            <option >Timor-Leste</option>
                            <option >Togo</option>
                            <option >Tokelau</option>
                            <option >Tonga</option>
                            <option >Trinidad and Tobago</option>
                            <option >Tunisia</option>
                            <option >Turkey</option>
                            <option >Turkmenistan</option>
                            <option >Turks and Caicos</option>
                            <option >Tuvalu</option>
                            <option >Uganda</option>
                            <option >Ukraine</option>
                            <option >United Arab Emirates</option>
                            <option >United Kingdom</option>
                            <option >United States</option>
                            <option >Uruguay</option>
                            <option >Uzbekistan</option>
                            <option >Vanuatu</option>
                            <option >Vatican City</option>
                            <option >Venezuela</option>
                            <option >Vietnam</option>
                            <option >Virgin Islands, British</option>
                            <option >Virgin Islands, US</option>
                            <option >Wallis and Futuna</option>
                            <option >Yemen</option>
                            <option >Zambia</option>
                            <option >Zimbabwe</option>
                          </select>
                          </td>
                      </tr>
                    </tbody>
                  </table></td>
              </tr>
              <tr class="r7">
                <td class="r8"><span class="rc">Maximum page size:</span></td>
                <td class="r9"><table>
                    <tbody>
                      <tr class="cf rc default" >
                        <td>Show
                          <select class="rA">
                            <option >10</option>
                            <option >15</option>
                            <option >20</option>
                            <option >25</option>
                            <option >50</option>
                            <option >100</option>
                          </select>
                          conversations per page</td>
                      </tr>
                      <tr class="rc default">
                        <td>Show
                          <select class="rA" >
                            <option >50</option>
                            <option >100</option>
                            <option >250</option>
                          </select>
                          contacts per page</td>
                      </tr>
                    </tbody>
                  </table></td>
              </tr>
              <tr class="r7">
                <td class="r8"><span class="rc">Images:</span><br></td>
                <td class="r9"><table cellspacing="0" cellpadding="0" class="cf ntxFbe">
                    <tbody>
                      <tr class="C7">
                        <td class="C6"><input type="radio" ></td>
                        <td class="C6"><span class="rS">
                          <label >Always display external images</label>
                          </span> - <span><a href="#" class="e"  target="_blank">Learn more</a></span></td>
                      </tr>
                    </tbody>
                  </table>
                  <table cellspacing="0" cellpadding="0" class="cf ntxFbe">
                    <tbody>
                      <tr class="C7">
                        <td class="C6"><input type="radio"></td>
                        <td class="C6"><span class="rS">
                          <label >Ask before displaying external images</label>
                          </span></td>
                      </tr>
                    </tbody>
                  </table></td>
              </tr>
              <tr class="r7">
                <td class="r8"><span class="rc">Default reply behavior:</span><br>
                  <a href="#" class="e"  target="_blank">Learn more</a></td>
                <td class="r9"><table cellspacing="0" cellpadding="0" class="cf ntxFbe">
                    <tbody>
                      <tr class="C7">
                        <td class="C6"><input type="radio" ></td>
                        <td class="C6"><span class="rS">
                          <label >Reply</label>
                          </span></td>
                      </tr>
                    </tbody>
                  </table>
                  <table cellspacing="0" cellpadding="0" class="cf ntxFbe">
                    <tbody>
                      <tr class="C7">
                        <td class="C6"><input type="radio" ></td>
                        <td class="C6"><span class="rS">
                          <label >Reply all</label>
                          </span></td>
                      </tr>
                    </tbody>
                  </table></td>
              </tr>
              <tr class="r7">
                <td class="r8"><span class="rc">Default text style:</span><br>
                  <span class="ra">(Use the 'Remove Formatting' button on the toolbar to reset the default text style)</span></td>
                <td class="r9">&nbsp;</td>
              </tr>
              <tr class="r7">
                <td class="r8"><span class="rc">Conversation View:</span><br>
                  <span class="ra">(sets whether emails of the same topic are grouped together)</span></td>
                <td class="r9"><table cellspacing="0" cellpadding="0" class="cf ntxFbe">
                    <tbody>
                      <tr class="C7">
                        <td class="C6"><input type="radio" ></td>
                        <td class="C6"><span class="rS">
                          <label >Conversation view on</label>
                          </span></td>
                      </tr>
                    </tbody>
                  </table>
                  <table cellspacing="0" cellpadding="0" class="cf ntxFbe">
                    <tbody>
                      <tr class="C7">
                        <td class="C6"><input type="radio"></td>
                        <td class="C6"><span class="rS">
                          <label >Conversation view off</label>
                          </span></td>
                      </tr>
                    </tbody>
                  </table></td>
              </tr>
              <tr class="r7">
                <td class="r8"><span class="rc">Send and Archive:</span><br>
                  <a href="#" class="e"  target="_blank">Learn more</a></td>
                <td class="r9"><table cellspacing="0" cellpadding="0" class="cf ntxFbe">
                    <tbody>
                      <tr class="C7">
                        <td class="C6"><input type="radio" ></td>
                        <td class="C6"><span class="rS">
                          <label >Show "Send &amp; Archive" button in reply</label>
                          </span></td>
                      </tr>
                    </tbody>
                  </table>
                  <table cellspacing="0" cellpadding="0" class="cf ntxFbe">
                    <tbody>
                      <tr class="C7">
                        <td class="C6"><input type="radio" ></td>
                        <td class="C6"><span class="rS">
                          <label >Hide "Send &amp; Archive" button in reply</label>
                          </span></td>
                      </tr>
                    </tbody>
                  </table></td>
              </tr>
              <tr class="r7">
                <td class="r8"><span class="rc">Stars:</span></td>
                <td class="r9"><div ><span class="rc">Drag the stars between the lists.</span>&nbsp; The stars will rotate in the order shown below when you click successively. To learn the name of a star for search, hover your mouse over the image.
                    <table cellpadding="0" class="cf r0">
                      <tbody>
                        <tr>
                          <td class="al1">Presets:</td>
                          <td class="dDFE0d">&nbsp;</td>
                        </tr>
                        <tr>
                          <td class="sC">In use:</td>
                          <td class="Ut">&nbsp;</td>
                        </tr>
                        <tr>
                          <td class="qB">Not in use:</td>
                          <td class="Ut">&nbsp;</td>
                        </tr>
                      </tbody>
                    </table>
                  </div></td>
              </tr>
              <tr class="r7" guidedhelpid="desktop_notifications_row">
                <td class="r8"><span class="rc">Desktop Notifications:</span><br>
                  <span class="ra">(allows Silvereye IT Solutions Private Limited Mail to display popup notifications on your desktop when new email messages arrive)</span><br>
                  <a href="#" class="e"  target="_blank">Learn more</a></td>
                <td class="r9"><span  idlink="" class="sA" tabindex="0" role="link">Click here to enable desktop notifications for Silvereye IT Solutions Private Limited Mail.</span><br>
                  <table cellspacing="0" cellpadding="0" class="cf ntxFbe">
                    <tbody>
                      <tr class="C7">
                        <td class="C6"><input type="radio" ></td>
                        <td class="C6"><span class="rS">
                          <label >New mail notifications on</label>
                          </span> - <span>Notify me when any new message arrives in my inbox or primary tab</span></td>
                      </tr>
                    </tbody>
                  </table>
                  <table cellspacing="0" cellpadding="0" class="cf ntxFbe">
                    <tbody>
                      <tr class="C7">
                        <td class="C6"><input type="radio"></td>
                        <td class="C6"><span class="rS">
                          <label >Important mail notifications on</label>
                          </span> - <span>Notify me only when an important message arrives in my inbox</span></td>
                      </tr>
                    </tbody>
                  </table>
                  <table cellspacing="0" cellpadding="0" class="cf ntxFbe">
                    <tbody>
                      <tr class="C7">
                        <td class="C6"><input type="radio" ></td>
                        <td class="C6"><span class="rS">
                          <label >Mail notifications off</label>
                          </span></td>
                      </tr>
                    </tbody>
                  </table></td>
              </tr>
              <tr class="r7">
                <td class="r8"><span class="rc">Keyboard shortcuts:</span><br>
                  <a href="#" class="e"  target="_blank">Learn more</a></td>
                <td class="r9"><table cellspacing="0" cellpadding="0" class="cf ntxFbe">
                    <tbody>
                      <tr class="C7">
                        <td class="C6"><input type="radio" ></td>
                        <td class="C6"><span class="rS">
                          <label>Keyboard shortcuts off</label>
                          </span></td>
                      </tr>
                    </tbody>
                  </table>
                  <table cellspacing="0" cellpadding="0" class="cf ntxFbe">
                    <tbody>
                      <tr class="C7">
                        <td class="C6"><input type="radio" ></td>
                        <td class="C6"><span class="rS">
                          <label>Keyboard shortcuts on</label>
                          </span></td>
                      </tr>
                    </tbody>
                  </table></td>
              </tr>
              <tr class="r7">
                <td class="r8"><span class="rc">Button labels:</span><br>
                  <a href="#" class="e" target="_blank">Learn more</a></td>
                <td class="r9"><table cellspacing="0" cellpadding="0" class="cf ntxFbe">
                    <tbody>
                      <tr class="C7">
                        <td class="C6"><input type="radio"></td>
                        <td class="C6"><span class="rS">
                          <label>Icons</label>
                          </span></td>
                      </tr>
                    </tbody>
                  </table>
                  <table cellspacing="0" cellpadding="0" class="cf ntxFbe">
                    <tbody>
                      <tr class="C7">
                        <td class="C6"><input type="radio" ></td>
                        <td class="C6"><span class="rS">
                          <label >Text</label>
                          </span></td>
                      </tr>
                    </tbody>
                  </table></td>
              </tr>
              <tr class="r7">
                <td class="r8"><span class="rc">My picture:</span><br>
                  <a href="#" class="e"  target="_blank">Learn more</a></td>
                <td class="r9"><div  style="display: block;"><span >Select a picture</span> that everyone will see when you email them.</div>
                  <div  style="display: none;">
                    <table cellpadding="0" class="cf">
                      <tbody>
                        <tr>
                          <td><div class="qC"><span  class="rX sA ou" >Change picture</span></div>
                            <img id=":wa" class="rJ" alt="Picture"></td>
                          <td class="rK"><table cellspacing="0" cellpadding="0" class="cf ntxFbe">
                              <tbody>
                                <tr class="C7">
                                  <td class="C6"><input type="radio" ></td>
                                  <td class="C6"><span class="rS">
                                    <label>Visible to everyone</label>
                                    </span></td>
                                </tr>
                              </tbody>
                            </table>
                            <table cellspacing="0" cellpadding="0" class="cf ntxFbe">
                              <tbody>
                                <tr class="C7">
                                  <td class="C6"><input type="radio" ></td>
                                  <td class="C6"><span class="rS">
                                    <label >Visible only to people I can chat with</label>
                                    </span></td>
                                </tr>
                              </tbody>
                            </table>
                            If you have a Google Profile picture then it is always visible to everyone.</td>
                        </tr>
                      </tbody>
                    </table>
                  </div></td>
              </tr>
              <tr class="r7">
                <td class="r8"><span class="rc">People Widget:</span><br>
                  <a href="#" class="e"  target="_blank">Learn more</a></td>
                <td class="r9"><table cellspacing="0" cellpadding="0" class="cf ntxFbe">
                    <tbody>
                      <tr class="C7">
                        <td class="C6"><input type="radio" ></td>
                        <td class="C6"><span class="rS">
                          <label >Show the people widget</label>
                          </span> - <span>Display information about participants to the right of each conversation</span></td>
                      </tr>
                    </tbody>
                  </table>
                  <table cellspacing="0" cellpadding="0" class="cf ntxFbe">
                    <tbody>
                      <tr class="C7">
                        <td class="C6"><input type="radio" ></td>
                        <td class="C6"><span class="rS">
                          <label >Hide the people widget</label>
                          </span></td>
                      </tr>
                    </tbody>
                  </table></td>
              </tr>
              <tr class="r7">
                <td class="r8"><span class="rc">Create contacts for auto-complete:</span></td>
                <td class="r9"><table cellspacing="0" cellpadding="0" class="cf ntxFbe">
                    <tbody>
                      <tr class="C7">
                        <td class="C6"><input type="radio" ></td>
                        <td class="C6"><span class="rS">
                          <label >When I send a message to a new person, add them to Other Contacts so that I can auto-complete to them next time</label>
                          </span></td>
                      </tr>
                    </tbody>
                  </table>
                  <table cellspacing="0" cellpadding="0" class="cf ntxFbe">
                    <tbody>
                      <tr class="C7">
                        <td class="C6"><input type="radio" ></td>
                        <td class="C6"><span class="rS">
                          <label>I'll add contacts myself</label>
                          </span></td>
                      </tr>
                    </tbody>
                  </table></td>
              </tr>
              <tr class="r7">
                <td class="r8"><span class="rc" >Signature:</span><br>
                  <span class="ra">(appended at the end of all outgoing messages)</span><br>
                  <a href="#" class="e"  target="_blank">Learn more</a></td>
                <td class="r9"><table cellspacing="0" cellpadding="0" class="cf ntxFbe">
                    <tbody>
                      <tr class="C7">
                        <td class="C6"><input type="radio" ></td>
                        <td class="C6"><span class="rS">
                          <label >No signature</label>
                          </span></td>
                      </tr>
                    </tbody>
                  </table>
                  <table cellspacing="0" cellpadding="0" class="cf ntxFbe">
                    <tbody>
                      <tr class="C7">
                        <td class="C6">&nbsp;</td>
                        <td class="C6">&nbsp;</td>
                      </tr>
                    </tbody>
                  </table>
                  <table cellspacing="0" cellpadding="0" class="cf ntxFbe">
                    <tbody>
                      <tr class="C7">
                        <td class="C6"><input type="radio" style="visibility:hidden"></td>
                        <td class="C6"><input type="checkbox">
                          &nbsp;
                          <label >Insert this signature before quoted text in replies and remove the "--" line that precedes it.</label></td>
                      </tr>
                    </tbody>
                  </table></td>
              </tr>
              <tr class="r7">
                <td class="r8"><span class="rc">Personal level indicators:</span></td>
                <td class="r9"><table cellspacing="0" cellpadding="0" class="cf ntxFbe">
                    <tbody>
                      <tr class="C7">
                        <td class="C6"><input type="radio" ></td>
                        <td class="C6"><span class="rS">
                          <label>No indicators</label>
                          </span></td>
                      </tr>
                    </tbody>
                  </table>
                  <table cellspacing="0" cellpadding="0" class="cf ntxFbe">
                    <tbody>
                      <tr class="C7">
                        <td class="C6"><input type="radio" ></td>
                        <td class="C6"><span class="rS">
                          <label >Show indicators</label>
                          </span> - <span>Display an arrow (&nbsp;<b>›</b>&nbsp;) by messages sent to my address (not a mailing list), and a double arrow (&nbsp;<b>»</b>&nbsp;) by messages sent only to me.</span></td>
                      </tr>
                    </tbody>
                  </table></td>
              </tr>
              <tr class="r7">
                <td class="r8"><span class="rc">Snippets:</span></td>
                <td class="r9"><table cellspacing="0" cellpadding="0" class="cf ntxFbe">
                    <tbody>
                      <tr class="C7">
                        <td class="C6"><input type="radio"></td>
                        <td class="C6"><span class="rS">
                          <label>Show snippets</label>
                          </span> - <span>Show snippets of the message (like Google web search!).</span></td>
                      </tr>
                    </tbody>
                  </table>
                  <table cellspacing="0" cellpadding="0" class="cf ntxFbe">
                    <tbody>
                      <tr class="C7">
                        <td class="C6"><input type="radio" ></td>
                        <td class="C6"><span class="rS">
                          <label>No snippets</label>
                          </span> - <span>Show subject only.</span></td>
                      </tr>
                    </tbody>
                  </table></td>
              </tr>
              <tr class="r7">
  <td class="r8"><span class="rc">Vacation responder:</span><br>
    <span class="ra">(sends an automated reply to incoming messages. If a contact sends you several messages, this automated reply will be sent at most once every 4 days)</span><br>
    <a href="#" class="e"  target="_blank">Learn more</a></td>
  <td class="r9"><table class="cf a8d" cellspacing="0" cellpadding="0">
      <tbody>
        <tr class="ntxFbe C7">
          <td class="C6"><input type="radio"></td>
          <td colspan="3" class="C6"><span class="rS">
            <label >Vacation responder off</label>
            </span></td>
        </tr>
        <tr class="ntxFbe C7">
          <td class="C6"><input type="radio"></td>
          <td colspan="3" class="C6"><span class="rS">
            <label >Vacation responder on</label>
            </span></td>
        </tr>
        <tr class="wbjtpc">
          <td></td>
          <td><label class="NR">First day:</label></td>
          <td class="sG"><input class="sE" type="date" >
            </td>
          <td class="sG"><input type="checkbox" >
            <label class="NR_1">Last day:</label>
            <input class="sE" type="text" >
            </td>
        </tr>
        <tr class="wbjtpc">
          <td class="Db"></td>
          <td class="Db">Subject:</td>
          <td colspan="2" class="sG Db"><input type="text"  maxlength="250" class="Da" ></td>
        </tr>
        <tr class="wbjtpc">
          <td class="Db"></td>
          <td class="Db">Message:</td>
          <td colspan="2" class="sG Db"><div  class="Vb">
              
              <table cellpadding="0" class="cf An" >
                <tbody>
                  <tr>
                    <td class="Aq">&nbsp;</td>
                    <td class="Ap">&nbsp;
                    
                      </td>
                    <td class="Aq">&nbsp;</td>
                  </tr>
                </tbody>
              </table>
            </div></td>
        </tr>
        <tr>
          <td></td>
          <td></td>
          <td colspan="2" class="sD"><input type="checkbox"  class="C8">
            &nbsp;
            <label class="C9">Only send a response to people in my Contacts</label></td>
        </tr>
        <tr>
          <td></td>
          <td></td>
          <td colspan="2" class="sD"><input type="checkbox"  class="C8">
            &nbsp;
            <label  class="C9">Only send a response to people in Silvereye IT Solutions Private Limited</label></td>
        </tr>
      </tbody>
    </table></td>
</tr>
              <tr class="r7">
                <td class="r8"><span class="rc">Outgoing message encoding:</span><br>
                  <a href="#" class="e"  target="_blank">Learn more</a></td>
                <td class="r9"><table cellspacing="0" cellpadding="0" class="cf ntxFbe">
                    <tbody>
                      <tr class="C7">
                        <td class="C6"><input type="radio" ></td>
                        <td class="C6"><span class="rS">
                          <label for=":wu">Use Unicode (UTF-8) encoding for outgoing messages</label>
                          </span></td>
                      </tr>
                    </tbody>
                  </table>
                  <table cellspacing="0" cellpadding="0" class="cf ntxFbe">
                    <tbody>
                      <tr class="C7">
                        <td class="C6"><input type="radio"></td>
                        <td class="C6"><span class="rS">
                          <label >Avoid Unicode (UTF-8) encoding for outgoing messages</label>
                          </span></td>
                      </tr>
                    </tbody>
                  </table></td>
              </tr>
              <tr class="r7" guidedhelpid="save_changes_row">
                <td colspan="2"><div class="rU" role="navigation">
                    <button >Save Changes</button>
                    &nbsp;&nbsp;
                    <button >Cancel</button>
                  </div></td>
              </tr>
            </tbody>
          </table>
        </div>
        <div id="view2">
          <table id="fldr_tbl" class="cf alO new_folder_append" >
            <tbody>
              <tr>
                <td class="r8 alL">Folders</td>
                <td class="alL new_show_hide">Subscribed</td>
                <td class="alL new_show_hide">Messages</td>
                 <td class="alL new_show_hide">Delete</td>
              </tr>
             <%
             String arr[]=fldr_lst.split(";");
     		//System.out.println("************************* webmail folder lenth="+arr.length);
     		for(int i=0; i<arr.length; i++)
     		{
     			String prm="";
     			if(arr[i].equalsIgnoreCase("Inbox") || arr[i].equalsIgnoreCase("Drafts") || arr[i].equalsIgnoreCase("Sent") || arr[i].equalsIgnoreCase("Junk") || arr[i].equalsIgnoreCase("Trash") || arr[i].equalsIgnoreCase("Archive")  )
     			{
     				prm=arr[i];
     				String hm_val=arr[i];
 					hm_fldr.put(hm_i, hm_val);
 					hm_fldr_path.put(hm_i, prm);
 					hm_i++;
     			%>
     			<tr id="tr_<%=prm %>" class="To">
                <td class="alT"><div class="al6"><%=arr[i] %></div></td>
                <td class="alQ"><input type="checkbox" checked="checked" disabled="disabled" /></td>
                <td class="alQ"><%=arr[i+3] %></td>
              	<td class="alQ"></td>
                </tr>
     			<%
     			}
     			else
     			{
     				prm=arr[i];
     				int flg=Integer.parseInt(arr[i+1]);
     				String chk="";
     				if(arr[i+2].equalsIgnoreCase("true"))
					{
						 chk="checked='checked'";
					}
     				if(flg>0)
     				{
     					String hm_val=arr[i];
     					hm_fldr.put(hm_i, hm_val);
     					hm_fldr_path.put(hm_i, prm);
     					hm_i++;
     					%>
     	     			<tr id="tr_<%=prm %>" class="To">
     	                <td class="alT"><div class="al6"><%=arr[i] %></div></td>
     	                <td class="alQ"><input type="checkbox"  <%=chk %> onclick="folderSubscribe(this.id)" id="chk_<%=arr[i] %>" /></td>
     	                <td class="alQ"><%=arr[i+3] %></td>
     	              	<td class="alQ"><img id="del_<%=prm %>" onclick="deleteFolder(this.id)" src="images/tool.png"></td>
     	                </tr>
     	     			<%	
     					GetWebmailSubFolderResponse wsfr=webmailClient.getWebmailSubFolderRequest(host, id, pass, arr[i]);
     					String mysubfdr=wsfr.getGetWebmailSubFolder();
     					String subarr[]=mysubfdr.split(";");
    					String spath1=arr[i];
    					for(int j=0;j < subarr.length;j++)
    					{
    						prm=arr[i]+"."+subarr[j];
    						int flgsub=Integer.parseInt(subarr[j+1]);
    						String chk1="";
							if(subarr[j+2].equalsIgnoreCase("true"))
							{
								 chk1="checked='checked'";
							}
    						if(flgsub>0)
    						{
    							String hm_val2="&nbsp;&nbsp;&nbsp;&nbsp;"+subarr[j];
    		     				hm_fldr.put(hm_i, hm_val2);
    		 					hm_fldr_path.put(hm_i, prm);
    		 					hm_i++;
    							%>
    							<tr id="tr_<%=prm %>" class="To">
                				<td class="alT"><div class="al6">&nbsp;&nbsp;&nbsp;&nbsp;<%=subarr[j] %></div></td>
                				<td class="alQ"><input type="checkbox" <%=chk1 %> onclick="folderSubscribe(this.id)" id="chk_<%=prm %>" /></td>
                				<td class="alQ"><%=subarr[j+3] %></td>
              					<td class="alQ"><img id="del_<%=prm %>" onclick="deleteFolder(this.id)" src="images/tool.png"></td>
                				</tr>
    							<%
    							GetWebmailSubFolderResponse wsfr1=webmailClient.getWebmailSubFolderRequest(host, id, pass, spath1+"."+subarr[j]);
    							String mysubfdr1=wsfr1.getGetWebmailSubFolder();
    							String subarr1[]=mysubfdr1.split(";");
    							String spath2=subarr[j];
    							for(int k=0;k < subarr1.length;k++)
    							{
    								prm=arr[i]+"."+subarr[j]+"."+subarr1[k];
    								String chk2="";
    								
        							if(subarr1[k+2].equalsIgnoreCase("true"))
        							{
        								 chk2="checked='checked'";
        							}
        							String hm_val1="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+subarr1[k];
        		     				hm_fldr.put(hm_i, hm_val1);
        		 					hm_fldr_path.put(hm_i, prm);
        		 					hm_i++;
    								%>
        							<tr id="tr_<%=prm %>" class="To">
                    				<td class="alT"><div class="al6">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=subarr1[k] %></div></td>
                    				<td class="alQ"><input type="checkbox" <%=chk2 %> onclick="folderSubscribe(this.id)" id="chk_<%=prm %>" /></td>
                    				<td class="alQ"><%=subarr1[k+3] %></td>
                  					<td class="alQ"><img id="del_<%=prm %>" onclick="deleteFolder(this.id)" src="images/tool.png"></td>
                    				</tr>
        							<%
        							
        							k=k+3;
        							
    							}
    						}
    						else
    						{
    							String hm_val2="&nbsp;&nbsp;&nbsp;&nbsp;"+subarr[j];
    		     				hm_fldr.put(hm_i, hm_val2);
    		 					hm_fldr_path.put(hm_i, prm);
    		 					hm_i++;
    							%>
    							<tr id="tr_<%=prm %>" class="To">
                				<td class="alT"><div class="al6">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=subarr[j] %></div></td>
                				<td class="alQ"><input type="checkbox" <%=chk1 %> onclick="folderSubscribe(this.id)" id="chk_<%=prm %>" /></td>
                				<td class="alQ"><%=subarr[j+3] %></td>
              					<td class="alQ"><img id="del_<%=prm %>" onclick="deleteFolder(this.id)" src="images/tool.png"></td>
                				</tr>
    							<%
    						
    						}
    						
    						j=j+3;
    					}
    					}
     				else
     				{
     					String hm_val=arr[i];
     					hm_fldr.put(hm_i, hm_val);
     					hm_fldr_path.put(hm_i, prm);
     					hm_i++;
     					%>
     	     			<tr id="tr_<%=arr[i] %>" class="To">
     	                <td class="alT"><div class="al6"><%=arr[i] %></div></td>
     	                <td class="alQ"><input type="checkbox"  <%=chk %>  onclick="folderSubscribe(this.id)" id="chk_<%=arr[i] %>" /></td>
     	                <td class="alQ"><%=arr[i+3] %></td>
     	              	<td class="alQ"><img id="del_<%=arr[i] %>" onclick="deleteFolder(this.id)" src="images/tool.png"></td>
     	                </tr>
     	     			<%	
     				}
     				}
     				
     				i=i+3;
     				
     				
     			}
     			
     		
             %>
             
             <!-- 
              <tr  class="To">
                <td class="alT"><div class="al6">Inbox</div></td>
                <td class="alQ"><input type="checkbox" checked="checked" disabled="disabled" /></td>
                <td class="alQ"></td>
              	<td class="alQ"></td>
              </tr>
              <tr  class="To">
                <td class="alT"><div class="al6">Starred</div></td>
                <td class="alQ"><input type="checkbox" /> </td>
                <td class="alQ">256</td>
              	<td class="alQ"><img src="images/tool.png"></td>
              </tr>
               -->
            
              
              </tbody>
              </table>
              <table class="new_folder_create">
              <tbody>
                      <tr class="To">
                            <td class="alT"><div class="al6">Trash</div></td>
                            <td class="alQ"><span class="alP">show</span>&nbsp;<span class="alR">hide</span></td>
                            <td class="alQ"></td>
                            <td class="alQ"></td>
                            <td class="YQh8id">&nbsp;</td>
                      </tr>
              </tbody>
              </table>

             <table>
             <tbody>
              <tr>
                <td class="al0" colspan="5"><div class="">
                    <button  class="alZ new_folder">Create New Folder</button>
                  </div></td>
              </tr>

            </tbody>
          </table>
        </div>
        
        <div id="view3">
          <table cellpadding="0" class="cf" width="100%">
            <tbody>
              <tr class="r7 r_top">
                <td class="r8"><span class="rc">Display Name</span></td>
                <td class="r9" width="70%"><input type="text" /></td>
              </tr>
               <tr class="r7 r_top">
                <td class="r8"><span class="rc">Email</span></td>
                <td class="r9" width="70%"><input type="text" /></td>
              </tr>
              <tr class="r7 r_top">
                <td class="r8"><span class="rc">Organization</span></td>
                <td class="r9" width="70%"><input type="text" /></td>
              </tr>
              <tr class="r7 r_top">
                <td class="r8"><span class="rc">Reply-To</span></td>
                <td class="r9" width="70%"><input type="text" /></td>
              </tr>
               <tr class="r7 r_top">
                <td class="r8"><span class="rc">Date of Birth</span></td>
                <td class="r9" width="70%"><input type="date" /></td>
              </tr>
              <tr class="r7 r_top">
                <td class="r8"><span class="rc">Date of Joinning</span></td>
                <td class="r9" width="70%"><input type="date" /></td>
              </tr>
              <tr class="r7 r_top">
                <td class="r8"><span class="rc">Anniversary</span></td>
                <td class="r9" width="70%"><input type="date" /></td>
              </tr>
              <tr class="r7 r_top">
                <td class="r8"><span class="rc">Postal Address</span></td>
                <td class="r9" width="70%"><textarea rows="6" cols="70"></textarea></td>
              </tr>
              <tr class="r7 r_top">
                <td class="r8"><span class="rc">Postal Code</span></td>
                <td class="r9" width="70%"><input type="text" /></td>
              </tr>
              <tr class="r7 r_top">
                <td class="r8"><span class="rc">Mobile</span></td>
                <td class="r9" width="70%"><input type="text" /></td>
              </tr>
               <tr class="r7 r_top">
                <td class="r8"><span class="rc">Home Mobile</span></td>
                <td class="r9" width="70%"><input type="text" /></td>
              </tr>
              <tr class="r7 r_top">
                <td class="r8"><span class="rc">Telephone Number</span></td>
                <td class="r9" width="70%"><input type="text" /></td>
              </tr>
               
              
              
              
              
              
              

            </tbody>
          </table>
           <div class="clear"></div>
          <div class="search_button account_save"> Save </div>
        </div>
        <div id="view4">
          <table cellpadding="0" class="cf" width="100%">
            <tbody>
              <tr class="r7 r_top">
                <td class="r8"><span class="rc">Current Password</span></td>
                <td class="r9" width="50%"><input id="cur_pass" type="password" /></td>
                <td class="r8"> </td>
              </tr>
               <tr class="r7 r_top">
                <td class="r8"><span class="rc">New Password</span></td>
                <td class="r9" width="50%">
			<input name="np" id="password" required type="password"  onblur="setNCP()"  />
   		 	<input type="hidden" id="hid_ps" name="hid_ps" value="0" />
   		  <div class="ermsg" style="clear: both;float: left; margin-top:3px;" id="result"></div>
				</td>
                <td class="r8"> 
                 <div  style="height: 20px; margin-top: -20px;">
  <p >
        <strong> Need a random password?</strong></p>
        <p style="margin-top: -10px;">
        <input type="checkbox" id="setpass" value="1" name="setpass" onclick="setPath(this.id)"/>
		<%Random ran=new Random();
        int rno=ran.nextInt(100000);
        int dg=rno%10;
        String x = Integer.toString(rno);
        char c1=(char)(70+dg);
        char c2=(char)(110+dg);
		x = x.substring(0, 1) + c1+(char)(80+dg) + x.substring(1, x.length());
		x = x.substring(0, x.length()-1) + c2 + x.substring(x.length()-1, x.length());
         out.print(x); %>
         <input type="hidden" name="rpass" id="rpass" value="<%=x %>"/>
		</p>
    </div>
                </td>
              </tr>
              <tr class="r7 r_top">
                <td class="r8"><span class="rc">Confirm New Password</span></td>
                <td class="r9" width="50%">
                <input name="cnp" id="cnp" required type="password"  onblur="checkNCP(this.value)"  />
        		<div style="clear: both;float: left; margin-top:3px;" id="cnp_msg1"></div> 
                </td>
                <td class="r8"> </td>
              </tr>
             <tr class="r7 r_top">
                <td class="r8"><span class="rc"></span></td>
                <td class="r9" width="50%">
                 <div class="clear"></div>
          <div class="search_button account_save" onclick="changePass()"> Save </div>
                </td>
                <td class="r8"> </td>
              </tr>

            </tbody>
          </table>
          
        </div>
        <div id="view5">
          <table cellpadding="0" class="cf filter_data" width="100%" role="list">
            <tbody>
              <tr class="r7" role="listitem">
                <td class="CZ" colspan="3"><b>The following filters are applied to all incoming mail:</b></td>
              </tr>
              </tbody>
              </table>
              <table width="100%">
              <tr class="r7" role="listitem" style="display: none;">
                <td class="CZ" colspan="3">The <span  class="e" >Inbox setting for important messages</span> is set to "Override filters." That means "Skip Inbox" filter rules will be ignored for messages that are important.</td>
              </tr>
              <tr>
                <td class="rZ" colspan="3"></td>
              </tr>
              <tr role="listitem">
                <td class="yV" colspan="3">Select: <span class="yU"><span class="rW sA" >All</span>, <span   class="rW sA" >None</span></span></td>
              </tr>
              <tr role="listitem">
                <td colspan="3"><button class="qR" disabled="">Export</button>
                  <button class="qR"  disabled="">Delete</button></td>
              </tr>
              <tr role="listitem">
                <td class="rG" colspan="3"><span class="sA new_create_filter">Create a new filter</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <span class="sA import_filter" >Import filters</span></td>
              </tr>
            </tbody>
          </table>
        </div>
       
        
        
        
        
      </div>
      <!---------------/// SETTING END HERE -----------------------> 
      
      <!-------------------/// Right View and Bottom View Tab End Here ---------------> 
      
    </div>
    
    <!--------------------/// Mail Cointer End Here ----------------->
    <div class="clear"></div>
  </div>
</div>
<div class="for_setting_1">
  <ul id="menu" class="extra_menu">
    <li> <a href="#">&nbsp; </a>
      <ul class="for_setting new_submenu">
        <li> <a href="#">Settings</a></li>
        <li><a href="#">Themes</a></li>
        <li> <a href="#">Help</a></li>
      </ul>
    </li>
  </ul>
</div>
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
            
            <!---------/// CREATE FILTER STARTED HERE ---------->
            
   <div id="search_form" class=" filter_serach">
     
    <div class="form">
      <form action="" method="get" class="filter_form">
        <div class="to">
          <div class="name search_text">Filter</div>
          <div class="clear"></div>
          <input type="text" name="" value="" class="border input filter_from " id="mytext" placeholder="From">
          <input type="text" name="" value="" class="border input filter_to" id="mytext" placeholder="To">
          <input type="text" name="" value="" class="border input filter_subject" id="mytext" placeholder="Subject">
          <input type="text" name="" value="" class="border input filter_has" id="mytext" placeholder="Has the words">
          <div class="check filter_check">
            <input name="" type="checkbox" value="" id="mytext">
            <span>Has attachment</span></div>
          <div class="clear"></div>
          <div class="check check_upper dont">
            <input name="" type="checkbox" value="">
            <span>Don't include chats</span></div>
        </div>
        <div class="filter_size">
               <span>Size</span>
               <div class="all_greater">
               <div class="filter_greater"> 
                        <span class="greater_than">greater than </span>
                        <span class="less_than">less than</span>
               </div>
               <div class="filter_greate_all">
                           <ul>
                              <li class="greater_list">greater than</li>
                              <li class="less_list">less than</li>
                           </ul>
                        </div>
               </div>
               <input type="text" />
               <!----------/// KB MB ---------->
               <div class="km_mb_main">
                         <div class="filter_mb_kb">
                               <span class="mb">MB</span>
                               <span class="kb">KB</span>
                               <span class="byte">Bytes</span>
                         </div>
                         
                         <div class="mb_kb_list">
                             <ul>
                                <li class="mb_list">MB</li>
                                <li class="kb_list">KB</li>
                                <li class="bytes_list">Bytes</li>
                             </ul>
                         
                         </div>
                         
                         
               </div>
              <!--------/// KB MB -------------->
               
        <div class="clear"></div>
        </div>
        <div class="clear"></div>
        <div class="search_button filter_buttom">Cancel </div>
        <div class="search_button filter_new_event next_filter">Next </div>
        <!--<div class="filter_new_event"> Create filter with this search <span>&#10143</span></div>-->
      </form>
    </div>
  </div>
            <!----------// CREATE FILTER END ------------------->
            
            
            
            
            
            
            <!------/// FILTER NEXT PAGE STRED HERE -------->
             <!---------/// FILTER NEXT PAGES ---------->
  
    <div id="search_form" class=" filter_next_page">
    <div class="form">
      <form action="" method="get" class="filter_form">
        <div class="to">
          <div class="name">When a message arrives that matches this search: </div>
          <div class="clear"></div>
          
             <!---/// INNER PART STARTED HERE  --->
             <div  class="main_check_box">
  <div class="inner_check_box">
    <div class="check_box_row">
      <input type="checkbox" id=":x4">
      <label for=":x4" class="aD">Skip the Inbox (Archive it)</label>
       <div class="clear"></div>
    </div>
    <div class="check_box_row">
      <input type="checkbox" id=":x5">
      <label for=":x5" class="aD">Mark as read</label>
       <div class="clear"></div>
    </div>
    <div class="check_box_row">
      <input type="checkbox" id=":x6">
      <label for=":x6" class="aD">Star it</label>
       <div class="clear"></div>
    </div>
    <div class="check_box_row">
      <input type="checkbox" id=":x7">
      <label for=":x7" class="aD">Apply the label:</label>
      <div  class="chose_leble_main">
           <div class="chose_lable_inner">
              <div class="lable_option" id="lable_option">Choose label...</div>
           </div>
           <!-------// CHOOSE LABLE FILE -------->
           <div class="select_lable">
               <ul>
                  <li> Test 1 </li>
                  <li> Test 2 </li>
                  <li> Test 3 </li>
                  <li> Test 4 </li>
                  <li> Test 5 </li>
                  <li> Test 6 </li>
                  <li> Test 7 </li>
                  <li> Test 8 </li>
                  <li> Test 9 </li>
                  <li> Test 10 </li>
                  <li> Test 11 </li>
                  <li> Test 12 </li>
                  <li> Test 13 </li>
                  <li> Test 14 </li>
                  <li> Test 15 </li>
                  <li> Test 16 </li>
                </ul>  
           </div>
           <!--------/// END LABLE FILE ------------->
      </div>
      <div class="clear"></div>
    </div>
    <div class="check_box_row"><span class="Z0">
      <input type="checkbox" >
      <label for=":x9" class="aD">Forward it</label>
      <span class="add_forwarding"><span  >add forwarding address</span></span></span>
       <div class="clear"></div>
      </div>
    <div class="check_box_row">
      <input type="checkbox" >
      <label  class="aD">Delete it</label>
       <div class="clear"></div>
    </div>
    <div class="check_box_row">
      <input type="checkbox" >
      <label  class="aD">Never send it to Spam</label>
       <div class="clear"></div>
    </div>
    <div class="check_box_row">
      <input type="checkbox" >
      <label  class="aD">Always mark it as important</label>
       <div class="clear"></div>
    </div>
    <div class="check_box_row">
      <input type="checkbox" >
      <label  class="aD">Never mark it as important</label>
       <div class="clear"></div>
    </div>
    <div class="check_box_row">
      <input type="checkbox" >
      <label  class="aD">Categorize as:</label>
      <div  class="category_main">
            <div class="chose_category_main">
               <div class="select_category" id="select_category" >Choose category...</div>
            </div>
            <!----// OPTION FOR CATEGORY -------->
            <div class="category_option">
            
               <ul>
                  <li>TEST 11</li>
                  <li>TEST 12</li>
                  <li>TEST 13</li>
                  <li>TEST 14</li>
                  <li>TEST 15</li>
                  <li>TEST 16</li>
                  <li>TEST 17</li>
                  <li>TEST 18</li>
                  <li>TEST 19</li>
                  <li>TEST 19</li>
                  <li>TEST 11</li>
                  <li>TEST 11</li>
                  <li>TEST 11</li>
                  <li>TEST 11</li>
                  <li>TEST 11</li>
               </ul>
            
            </div>
            <!----// OPTION FOR CATEGORY END ------->
      </div>
       <div class="clear"></div>
    </div>
  </div>
</div>
             <!------// INNER PART END HERE ---------->


        </div>
 
        <div class="clear"></div>
        <div class="search_button filter_buttom_create">Create filter </div>
        <div class="search_button  filter_back">Back </div>
        <div class="search_button filter_buttom filter_space">Cancel </div>
        
      </form>
    </div>
  </div>
  
  <!---------// FILTER NEXT PAGES END HERE ------------>
            <!------/// FILTER END HERE --------------->
           
 <script type="text/javascript">
   
   $(document).ready(function() {

	$('#password').keyup(function(){
		$('#result').html(checkStrength($('#password').val()))
	})	
	
	function checkStrength(password){
    
	//initial strength
    var strength = 0
	
    //if the password length is less than 6, return message.
    if (password.length < 6) { 
		$('#result').removeClass()
		$('#result').addClass('short')
		document.getElementById("hid_ps").value="0";
		return 'Too short' 
	}
    
    //length is ok, lets continue.
	
	//if length is 8 characters or more, increase strength value
	if (password.length > 7) strength += 1
	
	//if password contains both lower and uppercase characters, increase strength value
	if (password.match(/([a-z].*[A-Z])|([A-Z].*[a-z])/))  strength += 1
	
	//if it has numbers and characters, increase strength value
	if (password.match(/([a-zA-Z])/) && password.match(/([0-9])/))  strength += 1 
	
	//if it has one special character, increase strength value
    if (password.match(/([!,%,&,@,#,$,^,*,?,_,~])/))  strength += 1
	
	//if it has two special characters, increase strength value
    if (password.match(/(.*[!,%,&,@,#,$,^,*,?,_,~].*[!,%,&,@,#,$,^,*,?,_,~])/)) strength += 1
	
	//now we have calculated strength value, we can return messages
	
	//if value is less than 2
	if (strength < 2 ) {
		$('#result').removeClass()
		$('#result').addClass('weak')
		document.getElementById("hid_ps").value="0";
		return 'Weak'			
	} else if (strength == 2 ) {
		$('#result').removeClass()
		$('#result').addClass('good')
		document.getElementById("hid_ps").value="1";
		return 'Good'		
	} else {
		$('#result').removeClass()
		$('#result').addClass('strong')
		document.getElementById("hid_ps").value="2";
		return 'Strong'
	}
}
});
   </script>  
   <script type="text/javascript">
function removeMsg(id)
{
var element = document.getElementById(id);
element.parentNode.removeChild(element);
//alert(id);
}

</script>


<script type="text/javascript">
function changePass()
{
	var pass=document.getElementById("password").value;
	var cnp=document.getElementById("cnp").value;
	var cur_pass=document.getElementById("cur_pass").value;
var no=document.getElementById('hid_ps').value;
 
if(cur_pass==null ||  cur_pass=="" || pass==null ||  pass=="" || cnp==null ||  cnp=="")
	  {
	  alert("All Fields must be Filled .");
	  }
else  if(parseInt(no)<1) {
    alert('Password must be Good or Strong.');
}
else if(pass != cnp)
	  {
	  alert("Confirm password is not matched");
	  }
else
	  {
	$.ajax({
		type : "GET",
		url : "${pageContext.request.contextPath}/changePassword",
		data : {
			'cur_pass':cur_pass, 'new_pass': pass, 'con_new_pass': ncp
		},
		contentType : "application/json",
		success : function(data) {
			 alert(data);
			
		}
	}); 
	  }
	  
}




function setNCP() {
	document.getElementById("cnp").value="";
	document.getElementById("cnp_msg1").innerHTML="";
}


function checkNCP(val) {
	if(val!="")
	{
	var val1=document.getElementById("password").value;
	if(val==val1)
	{
      document.getElementById("cnp_msg1").innerHTML = "<font color='green'>Confirm password is matched..</font>";
	 }
	 else
	 {
	 document.getElementById("cnp_msg1").innerHTML = "<font color='red'>Confirm password is not matched.</font>";
	document.getElementById("cnp").value="";
	 }
	 }
}

function setPath(val)
{

var chk=document.getElementById(val).checked;
if(chk)
{
var ps=document.getElementById("rpass").value;
document.getElementById("password").value=ps;
document.getElementById("cnp").value=ps;
document.getElementById("hid_ps").value="2";
document.getElementById("result").innerHTML="";
document.getElementById("cnp_msg1").innerHTML="";
/* document.getElementById("cnp_msg1").innerHTML"";  */
}
else
{
document.getElementById("password").value="";
document.getElementById("cnp").value="";
document.getElementById("hid_ps").value="0";
}
}
</script>
            
            
            
            <!---------// CREATE NEW FOLDER IN ACCOUNT --------->
              <div class="account_new_folder">
                     
                     <input type="text" id="new_fldr" name="new_fldr" placeholder="Folder Name" class="folder_icon" />
                     <div class="clear"></div>
                    <select id="parent_fldr" name="parent_fldr">
                     <option value="-">--</option>
                     <%
                     Set set = hm_fldr.entrySet();
                     Set set1 = hm_fldr_path.entrySet();
                   
                     // Get an iterator
                     Iterator i = set.iterator();
                     Iterator j = set1.iterator();
                     // Display elements
                     while(i.hasNext() && j.hasNext()) {
                        Map.Entry me = (Map.Entry)i.next();
                        Map.Entry me1 = (Map.Entry)j.next();
                        %>
                        <option value="<%=me1.getValue() %>"> <%=me.getValue() %></option>
                        <%
                     }
                     %>
                         <!--  <option> TEST 1</option>
                          <option> TEST 2</option>
                          <option> TEST 3</option>
                          <option> TEST 4</option>
                          <option> TEST 5</option>
                          <option> TEST 6</option>
                          <option> TEST 7</option>
                      -->
                     </select>
                    
                     <div class="clear"></div>
                     <div onclick="creatFolder()" class="search_button  create_folder">Create </div>
                     <div class="search_button filter_buttom filter_space">Cancel </div>
                     <div class="clear"></div>
          <script type="text/javascript">
          function creatFolder() 
          {
			var nm=document.getElementById("new_fldr").value;
			if ( nm.indexOf('.') > -1 || nm.indexOf('/') > -1 || nm.indexOf('\\') > -1 || nm.indexOf(';') > -1 ) 
				{
				  alert( "found it" );
				 
				}
			else
				{
				//alert(nm);
				var element = document.getElementById("parent_fldr");
			    var path = element.options[element.selectedIndex].value;
			   // alert(op);
				document.getElementById("new_fldr").value="";
				$.ajax({
					type : "GET",
					url : "${pageContext.request.contextPath}/createNewFolder",
					data : {
						'path':path, 'name':nm
					},
					contentType : "application/json",
					success : function(data) {
						 alert(data);
						 if(data=="true")
							 {
							 if(path=="-")
								 {
								 $('table.new_folder_append >tbody').append("<tr id='tr_"+nm+"' class='To'><td class='alT'><div class='al6'>"+nm+"</div></td> <td class='alQ'><input type='checkbox' checked='checked' onclick='folderSubscribe(this.id)' id='chk_"+nm+"'></td><td class='alQ'>0</td><td class='alQ'><img id='del_"+nm+"' onclick='deleteFolder(this.id)' src='images/tool.png'></td></tr>");	
								 }
							 else
								 {
								 var r_in= document.getElementById("tr_"+path).rowIndex;
								 var fldr_nm="";
								 var arr=path.split(".");
								 for(i=0;i< arr.length; i++)
									 {
									 fldr_nm+="&nbsp;&nbsp;&nbsp;&nbsp;";
									 }
								 fldr_nm+=nm;
								 $('#fldr_tbl tr').eq(r_in).after("<tr id='tr_"+path+"."+nm+"' class='To'><td class='alT'><div class='al6'>"+fldr_nm+"</div></td> <td class='alQ'><input type='checkbox' checked='checked' onclick='folderSubscribe(this.id)' id='chk_"+path+"."+nm+"'></td><td class='alQ'>0</td><td class='alQ'><img id='del_"+path+"."+nm+"' onclick='deleteFolder(this.id)' src='images/tool.png'></td></tr>");
								 }
							 }
						 $('.account_new_folder').hide();
						 $('.web_dialog_overlay').hide();
					}
				});
				}
		}
          
          function deleteFolder(nm) 
          {
        	var fldnm="";
        	var arr=nm.split("_");
        	if(arr.length>1)
        		{
        		fldnm=arr[1];
        		}
        	var r = confirm("Delete this folder and everything in it?");
        	if (r == true) {
        	   
        	
        	$.ajax({
				type : "GET",
				url : "${pageContext.request.contextPath}/deleteFolder",
				data : {
					'fldnm':fldnm
				},
				contentType : "application/json",
				success : function(data) {
					 alert(data);
					 var r_in= document.getElementById("tr_"+fldnm).rowIndex;
					 var table = document.getElementById("fldr_tbl");
					 $("#parent_fldr option[value='"+fldnm+"']").remove();
					 table.deleteRow(r_in);
				}
			});
        	 // alert(nm); 
        	}
          }
        	
        	function folderSubscribe(chk_id) {
        		//alert(chk_id);
        		 var nm= document.getElementById(chk_id).checked;
        		//alert(nm);
        		var fldnm="";
            	var arr=chk_id.split("_");
            	if(arr.length>1)
            		{
            		fldnm=arr[1];
            		}
            	$.ajax({
    				type : "GET",
    				url : "${pageContext.request.contextPath}/folderSubscription",
    				data : {
    					'fldnm':fldnm, 'stat': nm
    				},
    				contentType : "application/json",
    				success : function(data) {
    					 alert(data);
    					
    				}
    			}); 
			} 
          
          </script>    
              
              </div>

            <!--------/// CREATE END HERE ------------>
            
            
            
            
            
            <div class="web_dialog_overlay"></div>
            
            
</body>
</html>