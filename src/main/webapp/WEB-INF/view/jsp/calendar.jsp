<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
</head>
<body>
<div class="right-pane"> 
    
    <!-----------------------------/// Main Cointer Stared here --------------->
    <div id="widget" > 
      
      <!-------/// CALENDER TOP THREE OPTION ----------->
      <div class="top_three_calender"> 
        
        <!---/// FIRST CALENDER ------>
        <div class="first_cal_option"><div class="create_cal_icon"></div> Create Calender </div>
        <!------/// FIRST CALENDER END HERE --------> 
        <!---/// SECOND CALENDER ------>
        <div class="first_cal_option_1"><div class="create_cal_icon_1"></div>Create Task</div>
        <!------/// SECOND CALENDER END HERE --------> 
        <!---/// THIRD CALENDER ------>
        <div class="first_cal_option_2"><div class="create_cal_icon_2"></div>Create Event</div>
        <!------/// THIRD CALENDER END HERE --------> 
        
      </div>
      <!---------/// CALENDER TOP THREE OPTION END HERE -------->
      <div class="agenda">Agenda</div>
      <div class="right_tool_part for_calender">
        <div class="right_tool"> <a href="#"> <img src="images/reload.png"> </a> </div>
        <div class="right_tool_1">
          <ul id="menu" >
            <li> <img src="images/setting_toll.png" class="four_margin" ></li>
            <li class="right_menu_1" > <img src="images/open_sub_menu.png" style="margin-left: 8px !important;"> 
              <!-- <ul class="for_setting">
                                            <li> <a href="#">Settings</a></li>
                                            <li><a href="#">Themes</a></li>
                                            <li> <a href="#">Help</a></li>
                                        </ul>--> 
            </li>
          </ul>
        </div>
      </div>
      
      <!---------------/// Tab Content Stared Here Off VIEW ---------------------------->
      <div id='calendar'></div>
      <!-----------------/// Tab Content End Here Off VIEW----------------------------> 
      
      <!-------------------/// Right View and Bottom View Tab Stared Here --------------->
      
      <div class="mail_content">
        <div class="mail_content_1" style="width: 48.5%; float: right; display: block; min-height: 75%; max-height: 95%;">
          <div class="mail_left_content"> 
            
            <!--------------/// Top Portion Started Here ------------>
            
            <div class="top_bottom_1">
              <h1>All New Job Opportunities for Fresher </h1>
              <div class="mail_right_1"> <a href="#">
                <div class="printer"></div>
                </a> <a href="#">
                <div class="fullscreen"></div>
                </a> </div>
              <div class="clear"></div>
              <div class="clear"></div>
            </div>
            
            <!------------------/// Top Portion End Here ----------------> 
            
            <!------// MAIL HEADER STARED HERE ------->
            <div class="mail_header mail_header_top" style="padding-top: 0px;"> 
              
              <!---- MAIL HEADER LEFT ------>
              <div class="mail_left">
                <div class="images_hover"> <img src="images/mail_icon.png"> 
                  
                  <!---------/// Images Details Stared Here ---------->
                  <div class="images_details">
                    <div class="mail_left_1"> <span>Timesjobs Job Alert</span>
                      <div class="clear"></div>
                      jobalert@timesjobs.com </div>
                    <img src="images/unnamed.png"> 
                    
                    <!-----------//// Bottom Images Details ------->
                    
                    <div class="send_mail_deatils"> <a href="#">Add to contacts</a> <a href="#">Emails</a> 
                      
                      <!-------/// Right Portion ------->
                      <div class="right_forw"> <a href="#">
                        <div class="mail_for"> <img src="images/plus_option.gif"> </div>
                        </a> <a href="#">
                        <div class="mail_for_1"> <img src="images/mail_fow.png"> </div>
                        </a> </div>
                      
                      <!-------/// Right Portion End -------> 
                      
                    </div>
                    
                    <!----------//// Bottom End Here -----------------> 
                    
                  </div>
                </div>
                
                <!--------/// Images End Here ----------------->
                
                <p class="first_p"><b>Techgig</b> <a href="#"><span>&lt;</span>jobs@techgig.com&gt;<span></span></a></p>
                <div class="mail_row">
                  <p class="last_p">to me </p>
                  <a href="#" onClick="mail_to(event);">
                  <div class="mail_deatil"></div>
                  </a> 
                  
                  <!-------------//// To Me ------------>
                  
                  <div class="to_me" style="display: none;">
                    <ul>
                      <li class="comm_width"><span>from: </span></li>
                      <li> Timesjobs Job Alert <span>&lt;</span>jobalert@timesjobs.com <span>&gt;</span></li>
                      <div class="clear"></div>
                      <li class="comm_width"><span>reply-to:</span></li>
                      <li> Timesjobs Job Alert &lt;jobalert@timesjobs.com&gt;</li>
                      <div class="clear"></div>
                      <li class="comm_width"><span>to:</span></li>
                      <li> hariom15791@gmail.com</li>
                      <div class="clear"></div>
                      <li class="comm_width"><span>date:</span></li>
                      <li> Sun, May 4, 2014 at 4:13 AM</li>
                      <div class="clear"></div>
                      <li class="comm_width"><span>subject:</span></li>
                      <li>Hariom, Optume IT Solutions Pvt. Ltd. jobs for you</li>
                      <div class="clear"></div>
                      <li class="comm_width"><span>mailed-by:</span></li>
                      <li>timesjobs.com</li>
                      <div class="clear"></div>
                      <li class="comm_width"><span>signed-by:</span></li>
                      <li>timesjobs.com</li>
                      <div class="clear"></div>
                      <li class="imag_upload"> <span>Images from this sender are always displayed.</span><a href="#">Don't display from now on.</a>
                        <div class="clear"></div>
                      </li>
                    </ul>
                  </div>
                  
                  <!--------------//// To Me End ------------> 
                  
                </div>
              </div>
              <!------- MAIL HEADER RIGHT ---> 
              <!---- MAIL HEADER LEFT ------>
              <div class="mail_right">
                <div style="float:left;margin-top: 7px;">10:21 AM (6 hours ago) </div>
                <div class="bottom_flag"></div>
                <div class="mail_option"> <a title="Reply" href="#">
                  <div class="back"> <img src="images/back_one.png"></div>
                  </a> <a title="More" href="#" onClick="option_here_1(event);">
                  <div class="option"> <img src="images/open_sub_menu.png"></div>
                  </a> </div>
              </div>
              <!------- MAIL HEADER RIGHT --->
              <div class="clear"></div>
            </div>
            <!-----/// MAIL END HEADER -----------> 
            <!----- /// MAIL MID CONTENT ----->
            <div class="mail_content_1"> 
              
              <!---------// Mail Option Are Here ------------>
              <div class="mail_down_option_1">
                <ul>
                  <li><a href="#">Reply</a></li>
                  <div class="clear"></div>
                  <li><a href="#">Reply to all</a></li>
                  <div class="clear"></div>
                  <li><a href="#">Forward</a></li>
                  <div class="clear"></div>
                  <li><a href="#">Open chat</a></li>
                  <div class="clear"></div>
                  <li><a href="#">Filter messages like this</a></li>
                  <div class="clear"></div>
                  <li><a href="#">Print</a></li>
                  <div class="clear"></div>
                  <li><a href="#">Add Techgig to Contacts list</a></li>
                  <div class="clear"></div>
                  <li><a href="#">Delete this message</a></li>
                  <div class="clear"></div>
                  <li><a href="#">Report spam</a></li>
                </ul>
                <div class="clear"></div>
              </div>
              <!-----------/// Mail Option End Here -------------->
              
              <p>Hi there!</p>
              <p>We hope you enjoy this webmail frontend as we here at Afterlogic, do. </p>
              <p>Please feel free to click, tap and drag-n-drop everything around. :) </p>
              <p>However, please note with this demo you can send emails to this demo email account only. It's just not to allow folks to send spam from here. </p>
              <p>Should you find that you need some help with getting this thing to work on your network, here are your options:</p>
              <p>Documentation, which should be your starting point<br>
                Helpdesk, to get assistance from our swift &amp; friendly Webmail Support Team
                Community Forum, where folks can help each other, discuss things and just hang around.<br>
                Support Team is also there, but Helpdesk is preferred.</p>
              <p>Also don't forget to get in touch with us at Facebook and Twitter.</p>
              <p>For those of you who are Big Guys, and should require dedicated support and the team behind the product, please call us +1-973-784-1100 or just drop us a message here.</p>
              <!---------------/// ATTACHMENT STARTED HERE ----------->
              <div class="main_attachment">
                <div class="attachment_file attachment_top"> 
                  <!-------------/// LEFT ATTACHMENT ---------->
                  <div class="left_attachment">
                    <p><span>2</span> Attachments</p>
                    <div class="clear"></div>
                  </div>
                  <!------------/// LEFT ATTACHMENT END -------> 
                  <!------------/// RIGHT ATTACHMENT ------->
                  <div class="left_attachment"></div>
                  <!----------/// RIGHT ATTACHMENT ---------> 
                </div>
                <!--------/// ATTACHMENT CONTENT ---->
                <div class="attachment_content">
                  <ul>
                    <!-------------/// MAIN ATTACHMENT ROW CONTENT  STARED HERE ------------>
                    <li>
                      <div class="attachment_con_box">
                        <div class="attachment_images"> <img src="images/photo_1.jpg" /> </div>
                        <div class="attach_con_bottom"> <img src="images/icon_1_pdf_x16.png" /> <span>Important Images for Web.pdf</span> </div>
                        <div class="attach_mousehover">
                          <div class="attach_row"><img src="images/icon_1_pdf_x16.png" /> <span>Important Images for Web.pdf</span>
                            <div class="clear"></div>
                            <div class="attachment_size">2 KB</div>
                          </div>
                          <div class="clear"></div>
                          <div class="attachment_option"> <img src="images/download.png" /> </div>
                        </div>
                      </div>
                    </li>
                    <!-------------/// MAIN ATTACHMENT ROW CONTENT  END HERE ------------> 
                    <!-------------/// MAIN ATTACHMENT ROW CONTENT  STARED HERE ------------>
                    <li>
                      <div class="attachment_con_box">
                        <div class="attachment_images"> <img src="images/photo_1.jpg" /> </div>
                        <div class="attach_con_bottom"> <img src="images/icon_1_pdf_x16.png" /> <span>Important Images for Web.pdf</span> </div>
                        <div class="attach_mousehover">
                          <div class="attach_row"><img src="images/icon_1_pdf_x16.png" /> <span>Important Images for Web.pdf</span>
                            <div class="clear"></div>
                            <div class="attachment_size">2 KB</div>
                          </div>
                          <div class="clear"></div>
                          <div class="attachment_option"> <img src="images/download.png" /> </div>
                        </div>
                      </div>
                    </li>
                    <!-------------/// MAIN ATTACHMENT ROW CONTENT  END HERE ------------> 
                    <!-------------/// MAIN ATTACHMENT ROW CONTENT  STARED HERE ------------>
                    <li>
                      <div class="attachment_con_box">
                        <div class="attachment_images"> <img src="images/photo_1.jpg" /> </div>
                        <div class="attach_con_bottom"> <img src="images/icon_1_pdf_x16.png" /> <span>Important Images for Web.pdf</span> </div>
                        <div class="attach_mousehover">
                          <div class="attach_row"><img src="images/icon_1_pdf_x16.png" /> <span>Important Images for Web.pdf</span>
                            <div class="clear"></div>
                            <div class="attachment_size">2 KB</div>
                          </div>
                          <div class="clear"></div>
                          <div class="attachment_option"> <img src="images/download.png" /> </div>
                        </div>
                      </div>
                    </li>
                    <!-------------/// MAIN ATTACHMENT ROW CONTENT  END HERE ------------> 
                    <!-------------/// MAIN ATTACHMENT ROW CONTENT  STARED HERE ------------>
                    <li>
                      <div class="attachment_con_box">
                        <div class="attachment_images"> <img src="images/photo_1.jpg" /> </div>
                        <div class="attach_con_bottom"> <img src="images/icon_1_pdf_x16.png" /> <span>Important Images for Web.pdf</span> </div>
                        <div class="attach_mousehover">
                          <div class="attach_row"><img src="images/icon_1_pdf_x16.png" /> <span>Important Images for Web.pdf</span>
                            <div class="clear"></div>
                            <div class="attachment_size">2 KB</div>
                          </div>
                          <div class="clear"></div>
                          <div class="attachment_option"> <img src="images/download.png" /> </div>
                        </div>
                      </div>
                    </li>
                    <!-------------/// MAIN ATTACHMENT ROW CONTENT  END HERE ------------> 
                    <!-------------/// MAIN ATTACHMENT ROW CONTENT  STARED HERE ------------>
                    <li>
                      <div class="attachment_con_box">
                        <div class="attachment_images"> <img src="images/photo_1.jpg" /> </div>
                        <div class="attach_con_bottom"> <img src="images/icon_1_pdf_x16.png" /> <span>Important Images for Web.pdf</span> </div>
                        <div class="attach_mousehover">
                          <div class="attach_row"><img src="images/icon_1_pdf_x16.png" /> <span>Important Images for Web.pdf</span>
                            <div class="clear"></div>
                            <div class="attachment_size">2 KB</div>
                          </div>
                          <div class="clear"></div>
                          <div class="attachment_option"> <img src="images/download.png" /> </div>
                        </div>
                      </div>
                    </li>
                    <!-------------/// MAIN ATTACHMENT ROW CONTENT  END HERE ------------> 
                    <!-------------/// MAIN ATTACHMENT ROW CONTENT  STARED HERE ------------>
                    <li>
                      <div class="attachment_con_box">
                        <div class="attachment_images"> <img src="images/photo_1.jpg" /> </div>
                        <div class="attach_con_bottom"> <img src="images/icon_1_pdf_x16.png" /> <span>Important Images for Web.pdf</span> </div>
                        <div class="attach_mousehover">
                          <div class="attach_row"><img src="images/icon_1_pdf_x16.png" /> <span>Important Images for Web.pdf</span>
                            <div class="clear"></div>
                            <div class="attachment_size">2 KB</div>
                          </div>
                          <div class="clear"></div>
                          <div class="attachment_option"> <img src="images/download.png" /> </div>
                        </div>
                      </div>
                    </li>
                    <!-------------/// MAIN ATTACHMENT ROW CONTENT  END HERE ------------> 
                    <!-------------/// MAIN ATTACHMENT ROW CONTENT  STARED HERE ------------>
                    <li>
                      <div class="attachment_con_box">
                        <div class="attachment_images"> <img src="images/photo_1.jpg" /> </div>
                        <div class="attach_con_bottom"> <img src="images/icon_1_pdf_x16.png" /> <span>Important Images for Web.pdf</span> </div>
                        <div class="attach_mousehover">
                          <div class="attach_row"><img src="images/icon_1_pdf_x16.png" /> <span>Important Images for Web.pdf</span>
                            <div class="clear"></div>
                            <div class="attachment_size">2 KB</div>
                          </div>
                          <div class="clear"></div>
                          <div class="attachment_option"> <img src="images/download.png" /> </div>
                        </div>
                      </div>
                    </li>
                    <!-------------/// MAIN ATTACHMENT ROW CONTENT  END HERE ------------> 
                    <!-------------/// MAIN ATTACHMENT ROW CONTENT  STARED HERE ------------>
                    <li>
                      <div class="attachment_con_box">
                        <div class="attachment_images"> <img src="images/photo_1.jpg" /> </div>
                        <div class="attach_con_bottom"> <img src="images/icon_1_pdf_x16.png" /> <span>Important Images for Web.pdf</span> </div>
                        <div class="attach_mousehover">
                          <div class="attach_row"><img src="images/icon_1_pdf_x16.png" /> <span>Important Images for Web.pdf</span>
                            <div class="clear"></div>
                            <div class="attachment_size">2 KB</div>
                          </div>
                          <div class="clear"></div>
                          <div class="attachment_option"> <img src="images/download.png" /> </div>
                        </div>
                      </div>
                    </li>
                    <!-------------/// MAIN ATTACHMENT ROW CONTENT  END HERE ------------> 
                    <!-------------/// MAIN ATTACHMENT ROW CONTENT  STARED HERE ------------>
                    <li>
                      <div class="attachment_con_box">
                        <div class="attachment_images"> <img src="images/photo_1.jpg" /> </div>
                        <div class="attach_con_bottom"> <img src="images/icon_1_pdf_x16.png" /> <span>Important Images for Web.pdf</span> </div>
                        <div class="attach_mousehover">
                          <div class="attach_row"><img src="images/icon_1_pdf_x16.png" /> <span>Important Images for Web.pdf</span>
                            <div class="clear"></div>
                            <div class="attachment_size">2 KB</div>
                          </div>
                          <div class="clear"></div>
                          <div class="attachment_option"> <img src="images/download.png" /> </div>
                        </div>
                      </div>
                    </li>
                    <!-------------/// MAIN ATTACHMENT ROW CONTENT  END HERE ------------> 
                    <!-------------/// MAIN ATTACHMENT ROW CONTENT  STARED HERE ------------>
                    <li>
                      <div class="attachment_con_box">
                        <div class="attachment_images"> <img src="images/photo_1.jpg" /> </div>
                        <div class="attach_con_bottom"> <img src="images/icon_1_pdf_x16.png" /> <span>Important Images for Web.pdf</span> </div>
                        <div class="attach_mousehover">
                          <div class="attach_row"><img src="images/icon_1_pdf_x16.png" /> <span>Important Images for Web.pdf</span>
                            <div class="clear"></div>
                            <div class="attachment_size">2 KB</div>
                          </div>
                          <div class="clear"></div>
                          <div class="attachment_option"> <img src="images/download.png" /> </div>
                        </div>
                      </div>
                    </li>
                    <!-------------/// MAIN ATTACHMENT ROW CONTENT  END HERE ------------> 
                    <!-------------/// MAIN ATTACHMENT ROW CONTENT  STARED HERE ------------>
                    <li>
                      <div class="attachment_con_box">
                        <div class="attachment_images"> <img src="images/photo_1.jpg" /> </div>
                        <div class="attach_con_bottom"> <img src="images/icon_1_pdf_x16.png" /> <span>Important Images for Web.pdf</span> </div>
                        <div class="attach_mousehover">
                          <div class="attach_row"><img src="images/icon_1_pdf_x16.png" /> <span>Important Images for Web.pdf</span>
                            <div class="clear"></div>
                            <div class="attachment_size">2 KB</div>
                          </div>
                          <div class="clear"></div>
                          <div class="attachment_option"> <img src="images/download.png" /> </div>
                        </div>
                      </div>
                    </li>
                    <!-------------/// MAIN ATTACHMENT ROW CONTENT  END HERE ------------> 
                    <!-------------/// MAIN ATTACHMENT ROW CONTENT  STARED HERE ------------>
                    <li>
                      <div class="attachment_con_box">
                        <div class="attachment_images"> <img src="images/photo_1.jpg" /> </div>
                        <div class="attach_con_bottom"> <img src="images/icon_1_pdf_x16.png" /> <span>Important Images for Web.pdf</span> </div>
                        <div class="attach_mousehover">
                          <div class="attach_row"><img src="images/icon_1_pdf_x16.png" /> <span>Important Images for Web.pdf</span>
                            <div class="clear"></div>
                            <div class="attachment_size">2 KB</div>
                          </div>
                          <div class="clear"></div>
                          <div class="attachment_option"> <img src="images/download.png" /> </div>
                        </div>
                      </div>
                    </li>
                    <!-------------/// MAIN ATTACHMENT ROW CONTENT  END HERE ------------> 
                    <!-------------/// MAIN ATTACHMENT ROW CONTENT  STARED HERE ------------>
                    <li>
                      <div class="attachment_con_box">
                        <div class="attachment_images"> <img src="images/photo_1.jpg" /> </div>
                        <div class="attach_con_bottom"> <img src="images/icon_1_pdf_x16.png" /> <span>Important Images for Web.pdf</span> </div>
                        <div class="attach_mousehover">
                          <div class="attach_row"><img src="images/icon_1_pdf_x16.png" /> <span>Important Images for Web.pdf</span>
                            <div class="clear"></div>
                            <div class="attachment_size">2 KB</div>
                          </div>
                          <div class="clear"></div>
                          <div class="attachment_option"> <img src="images/download.png" /> </div>
                        </div>
                      </div>
                    </li>
                    <!-------------/// MAIN ATTACHMENT ROW CONTENT  END HERE ------------> 
                    <!-------------/// MAIN ATTACHMENT ROW CONTENT  STARED HERE ------------>
                    <li>
                      <div class="attachment_con_box">
                        <div class="attachment_images"> <img src="images/photo_1.jpg" /> </div>
                        <div class="attach_con_bottom"> <img src="images/icon_1_pdf_x16.png" /> <span>Important Images for Web.pdf</span> </div>
                        <div class="attach_mousehover">
                          <div class="attach_row"><img src="images/icon_1_pdf_x16.png" /> <span>Important Images for Web.pdf</span>
                            <div class="clear"></div>
                            <div class="attachment_size">2 KB</div>
                          </div>
                          <div class="clear"></div>
                          <div class="attachment_option"> <img src="images/download.png" /> </div>
                        </div>
                      </div>
                    </li>
                    <!-------------/// MAIN ATTACHMENT ROW CONTENT  END HERE ------------> 
                    <!-------------/// MAIN ATTACHMENT ROW CONTENT  STARED HERE ------------>
                    <li>
                      <div class="attachment_con_box">
                        <div class="attachment_images"> <img src="images/photo_1.jpg" /> </div>
                        <div class="attach_con_bottom"> <img src="images/icon_1_pdf_x16.png" /> <span>Important Images for Web.pdf</span> </div>
                        <div class="attach_mousehover">
                          <div class="attach_row"><img src="images/icon_1_pdf_x16.png" /> <span>Important Images for Web.pdf</span>
                            <div class="clear"></div>
                            <div class="attachment_size">2 KB</div>
                          </div>
                          <div class="clear"></div>
                          <div class="attachment_option"> <img src="images/download.png" /> </div>
                        </div>
                      </div>
                    </li>
                    <!-------------/// MAIN ATTACHMENT ROW CONTENT  END HERE ------------>
                  </ul>
                </div>
                <!----------/// ATTACHMENT CONTENT END -----> 
                
              </div>
              <!---------------/// ATTACHMENT END HERE ------------> 
            </div>
            <div class="clear"></div>
            <!------------/// Bottom Portion Here(Mail Replay) --------------->
            <div class="bottom_left" style="display: block;"> </div>
            <!------------//// Bottom Portion Here --------------->
            
            <div class="your_option_1" onClick="mail_forward_11();" style="margin-top: 64px;">Click here to <a href="#">Reply</a> or <a href="#">Forward</a></div>
            <!-------------//// Replay Details Are Here --------------->
            <div class="mail_forward_11"> 
              
              <!-------------/// FORWARD TOP ------------->
              <div class="forward_top">
                <div class="forward_mail"> <a href="#" class="mail_left_for"> <img src="images/back_one.png"> </a> <a href="#" class="mail_right_for" onClick="down_mail(event);"> <img src="images/open_sub_menu.png"> </a>
                  <div class="main_bottom_option"> </div>
                </div>
                <div class="forward_mail_left"> </div>
              </div>
              <!-------------/// FORWARD TOP -------------> 
              
              <!-------------/// FORWARD TOP ------------->
              <div class="forward_mid"> </div>
              <!-------------/// FORWARD TOP -------------> 
              
              <!-------------/// FORWARD TOP ------------->
              <div class="forward_bottom"> 
                
                <!----------------/// Left Portion --------------->
                
                <div class="for_bottom_left"> Send </div>
                
                <!----------------/// Left Portion End ------------> 
                
                <!----------------/// Right Portion --------------->
                
                <div class="for_bottom_mid"> <a href="#" class="font_image"> <img src="images/a_fonts.gif"> </a>
                  <div class="bor_1"></div>
                  <a href="#" class="attach_image"> <img src="images/attachment.png"> </a> <a href="#" class="plus"> <img src="images/plus_black.png">
                  <div class="plus_option"></div>
                  </a> </div>
                
                <!----------------/// Right Portion End ------------> 
                
                <!----------------/// Right Portion --------------->
                
                <div class="for_bottom_right"> <a href="#" class="dustbin"><img src="images/tool.png"></a>
                  <div class="bor_1"></div>
                  <a href="#" class="bottom_down_1" onClick="bootom_forward(event);"><img src="images/open_sub_menu.png"></a> </div>
                
                <!----------------/// Right Portion End ------------> 
                
              </div>
              <!-------------/// FORWARD TOP ------------->
              <div class="for_bottom"> </div>
            </div>
            <!--------------/// Replay Details End Here ----------------> 
            
          </div>
          
          <!------/// MAIL MID CONTENT ---->
          <div class="clear"></div>
          <div class="clear"></div>
        </div>
      </div>
      
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

<!---------------------/// JS CALENDER STARED HERE ------------> 

<script>
            $(document).ready(function() {
		
		$('#calendar').fullCalendar({
			header: {
				left: 'prev,next today',
				center: 'title',
				right: 'month,agendaWeek,agendaDay'
			},
			defaultDate: '2014-11-12',
			selectable: true,
			selectHelper: true,
			select: function(start, end) {
				
				/// TEST ADD HERE 
				
				if($('.calender_pop').css('display')=='none')
				{
					
					$('.calender_pop').css('display','block');
					 $('.web_dialog_overlay').show();
					
					
			}
				
				$('.save_cal').click(function(){
					
				     var input_value = $('.summary').val();
					//alert(input_value);
					
					});
				
				/// ********************** ///
				///     NOTE  stred here  ///
				/// ******************** ///
				
				
				////  i am not change the value when or append the value in each box beause it affect in development
				  //   if  you want to new  state only remove the comment of //var title = prompt('Event Title:'); only 
				  /// and hide the new test event
				
				
				
				
				/// ********************** ///
				///     NOTE  end         ///
				/// ******************** ///
				
				
				/// TEST ADD END HERE 
				
				
				//var title = prompt('Event Title:');
				//var title = prompt('Event Title:');
				
				
				var eventData;
				if (title) {
					eventData = {
						title: title,
						start: start,
						end: end
					};
					alert(title);
					$('#calendar').fullCalendar('renderEvent', eventData, true); // stick? = true
				}
				$('#calendar').fullCalendar('unselect');
			},
			editable: true,
			eventLimit: true, // allow "more" link when too many events
			events: [
				{
					title: 'All Day Event',
					start: '2014-11-01'
				},
				{
					title: 'Long Event',
					start: '2014-11-07',
					end: '2014-11-10'
				},
				{
					id: 999,
					title: 'Repeating Event',
					start: '2014-11-09T16:00:00'
				},
				{
					id: 999,
					title: 'Repeating Event',
					start: '2014-11-16T16:00:00'
				},
				{
					title: 'Conference',
					start: '2014-11-11',
					end: '2014-11-13'
				},
				{
					title: 'Meeting',
					start: '2014-11-12T10:30:00',
					end: '2014-11-12T12:30:00'
				},
				{
					title: 'Lunch',
					start: '2014-11-12T12:00:00'
				},
				{
					title: 'Meeting',
					start: '2014-11-12T14:30:00'
				},
				{
					title: 'Happy Hour',
					start: '2014-11-12T17:30:00'
				},
				{
					title: 'Dinner',
					start: '2014-11-12T20:00:00'
				},
				{
					title: 'Birthday Party',
					start: '2014-11-13T07:00:00'
				},
				{
					title: 'Click for Google',
					url: 'http://google.com/',
					start: '2014-11-28'
				}
			]
		});
		
	});


</script>
<link rel='stylesheet' href='css/jquery-ui.min.css' />
<link href='css/fullcalendar.css' rel='stylesheet' />
<link href='css/fullcalendar.print.css' rel='stylesheet' media='print' />
<script src='js/moment.min.js'></script> 
<!--<script src='../lib/jquery.min.js'></script>---> 
<script src='js/fullcalendar.min.js'></script>
<!-----------/// COLOR PICKER STRED HERE ----------->
    <link rel="stylesheet" type="text/css" href="color_picker/spectrum.css">
    <script type="text/javascript" src="color_picker/spectrum.js"></script>
    <script type='text/javascript' src='color_picker/docs.js'></script>
<!-------------/// COLOR PICKER END HERE --------------->
<style>
	#calendar {
		/*max-width: 900px;*/
		margin: 0 auto;
	}
</style>
<!--------------------/// JS CALENDER END HERE ------------------> 

<!------------------/// NEW SPILITTER STARED HERE --------------> 
<script>
       jQuery(function($) {
    $(window).on('resize', function() {
        var height = $(window).height()-135;
		//alert(height);
        console.log(height);
        $('#foo').height(height).split({ orientation:'horizontal', limit:50 });
		$('#foo').css('height',height)
        $('#b').height(height / 2)-30;
		//$('.chat_inner_content').height(height / 2);
		
		
    }).trigger('resize');
});

  
    </script> 
<!-------------------//// NEW SPILITTER END HERE ---------------> 

<!------------/// CREATE CALENDER POP STARED HERE ----------->
<div class="calender_pop"> 
  <!--------/// HEADER STARED HERE ----------->
  <div class="pop_header">Create event
  
      <span>X</span>
  </div>
  <!----------/// HEADER END HERE -------> 
  <!----------// POP TAB STRED HERE -------->
  <div class="pop_tab">
         <ul>
             <li class="gen_opt gen_active"><div class="gen_icon"></div>General options</li>
             <li class="repe"><div class="repe_icon"></div>Repeat</li>
             <li class="remind"><div class="reminder_icon"></div>Reminders</li>
             <li class="work_g"><div class="working_icon"></div>Workgroup</li>
         </ul>
  
  
  
  <!---  <div class="gen_opt">General options</div>
    <div class="repe">Repeat</div>
    <div class="remind">Reminders</div>
    <div class="work_g">Workgroup</div> --->
  </div>
  <!---------/// POP TAB END HERE -------->
  <div class="clear"></div>
  <!----------/// POPUP CREATE CONTENT BOX ---------->

  <div class="pop_content"> 
    <!------------//// POP CONTENT GENERAL OPTION --------->
    <div class="gen_content">
      <table>
        <tr>
          <td>Summary</td>
          <td><input type="text" class="summary" /></td>
        </tr>
        <tr>
          <td>Location</td>
          <td><input type="text" /></td>
        </tr>
        <tr>
          <td>Calendar</td>
          <td><select>
              <option>Nirbhay Singh calendar</option>
              <option>JJJ</option>
              <option>JJJ</option>
              <option>JJJ</option>
              <option>JJJ</option>
              <option>JJJ</option>
            </select></td>
        </tr>
        <tr>
          <td>Start date</td>
          <td><input type="date" />
            <input type="text" class="date_stared" /></td>
        </tr>
        <tr>
          <td>End date</td>
          <td><input type="date" />
            <input type="text" class="date_stared" /></td>
        </tr>
        <tr>
          <td>All day</td>
          <td><input type="checkbox"/></td>
        </tr>
        <tr>
          <td>Description</td>
          <td><textarea></textarea></td>
        </tr>
      </table>
      <div class="clear"></div>
    </div>
    <!------------//// POP CONTENT GENERAL OPTION END ---------> 
    <!--------/// REPEAT CONTENT ------>
    <div class="repeat_cal">
      <table>
        <tr>
          <td>Repeat</td>
          <td><select>
              <option >No repetitions</option>
              <option >Daily</option>
              <option >Weekly</option>
              <option >Monthly</option>
              <option >Yearly</option>
            </select></td>
        </tr>
        <tr>
          <td>Count</td>
          <td><input type="text" /></td>
        </tr>
        <tr>
          <td>Until</td>
          <td><input type="text" /></td>
        </tr>
      </table>
    </div>
    <!-----------/// REPEAT CONTENT END HERE ------> 
    <!-----------// REMINDER CAL STRED --------->
    <div class="reminder_cal">
      <table>
        <tr>
          <td colspan="2" class="new_line">This event has no configured reminders</td>
        </tr>
        <tr>
          <td colspan="2" class="new_line_1">New reminder:</td>
        </tr>
        <tr>
          <td><img src="images/new_reminder.png" /></td>
          <td >
            <select >
              <option >minutes</option>
              <option >hours</option>
              <option >days</option>
              <option >weeks</option>
            </select>
            <select>
              <option >before</option>
              <option >after</option>
            </select>
            <select >
              <option >start</option>
              <option >end</option>
            </select>
            </td>
        </tr>
        <tr>
           <td colspan="2">New reminder:</td>
        </tr>
        <tr>
            <td><img src="images/calender_new.png" /></td>
            <td><input type="date"/> <input type="text" class="date_stared"/></td>
        </tr>
      </table>
    </div>
    <!-----------/// REMINDER CAL END HERE ------->
    <!-----------//// WORKGROUP CAL ------------>
    <div class="workgroup_cal">
             <table>
                <tr >
                   <td>Privacy</td>
                   <td>
                              <select>
                                    <option>Public</option>
                                    <option>Private</option>
                                    <option>Confidential</option>
                              </select>
                   </td>
                </tr>
                <tr>
                   <td>Show this time as</td>
                   <td>
                            <select>
                                  <option>Busy</option>
                                  <option>Free</option>
                            </select>
                   
                   </td>
                </tr>
                <tr>
                   <td></td>
                   <td></td>
                </tr>
             </table>
    
    
    </div>
    <!------------/// WORKGROUP CAL END HERE ------->
    <div class="clear"></div>
    <div class="save_cal">Save</div>
    <div class="cancel_cal">Cancel</div>
    <div class="clear"></div>
  </div>
  <!-------------/// POPUP CREATE CONTENT BOX END HERE ------------> 
  
</div>

<!---------//// CREATE THE CALENDER STRED HERE --------------->
<div class="create_cal">
<!---/// HEADER ----->
<div class="pop_header">New Calendar <span>X</span> </div>
<!-----/// HEADER END HERE ------->
<!----/// CONTENT STRED HERE ------>
<div class="inn_con_cal">

  <table>
     <tr>
          <td>Display Name</td>
           <td><input type="text" class="dis_name" /></td>
     </tr>
     <tr>
          <td>Display Color</td>
           <td><input type='text' id='custom' /></td>
     </tr>
  </table>

</div>
  <div class="clear"></div>
    <div class="save_cal_1">Save</div>
    <div class="cancel_cal_1">Cancel</div>
    <div class="clear"></div>

<!------/// CONTENT END HERE ---------->


</div>

<!-----------/// CREATE THE CALENDER END HERE ---------------->
  <div class="web_dialog_overlay"></div>
<!------------/// CREATE CALENDER POP END HERE -------------->


</body>
</html>