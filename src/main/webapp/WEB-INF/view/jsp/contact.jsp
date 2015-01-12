<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
function getWebmailContactRefresh( ) {
	//alert("contact");
	//var fdrname=document.getElementById('hid_active_fldr').value;
	//alert(fdrname)
	//var pview=document.getElementById("hid_panel_view").value;
	//alert(pview);
var start='0';
var end='15';
document.getElementById('div_progress').style.display= 'block';
//		document.getElementById('action_gif').style.display= 'block';
//alert('hi');
		
		
		
		$.ajax({
			type : "GET",
			url : "${pageContext.request.contextPath}/getContactList",
			data : {
				
			},
			contentType : "application/json",
			success : function(data) {
				// $("#fileSystem").html(data);
				// alert(data);
					//alert(data);
						$("#contact_cnt_div_full").html(data);
				//document.getElementById('action_gif').style.display= 'none';
				//getAllMailCount(fdrname);
						document.getElementById('div_progress').style.display= 'none';
			}
		});


	}
</script>
</head>
<body>
<div class="right-pane"> 
    
    <!-----------------------------/// Main Cointer Stared here --------------->
    <div id="widget" > 
    <!-----// CONTACT HEADER STARED ------->
           <div class="for_tool_contact">
      
      <!-------/// CALENDER TOP THREE OPTION ----------->
           <div class="top_three_con"> 
        
                    <!---/// FIRST CALENDER ------>
                    <div class="first_con_option"><div class="create_con_icon"></div> Create Group </div>
                    <!------/// FIRST CALENDER END HERE --------> 
                    <!---/// SECOND CALENDER ------>
                    <div class="first_con_option_1"><div class="create_con_icon_1"></div>Create Contact</div>
                    <!------/// SECOND CALENDER END HERE --------> 
                     <!---/// THIRD CALENDER ------>
                    <div class="first_con_option_2"><div class="create_con_icon_2"></div>E-Mail</div>
                    <!------/// THIRD CALENDER END HERE --------> 
                         <!---/// SECOND CALENDER ------>
                    <div class="first_con_option_4"><div class="create_con_icon_4"></div>SMS</div>
                    <!------/// SECOND CALENDER END HERE --------> 
                    <!---/// THIRD CALENDER ------>
                    <div class="first_con_option_3"><div class="create_con_icon_3"></div>More</div>
                    <!---/// CONTACT MORE OPTION STARED ------->
                    <div class="con_more">
                        <ul>  
                           <li><a href="#">Merge contacts</a></li>
                           <li><a href="#">Delete contacts</a></li>
                           <li class="mange_sharing"><a href="#">Manage Sharing</a></li>
                           <li><a href="#">Import...</a></li>
                           <li><a href="#">Export...</a></li>
                           <li><a href="#">Print...</a></li>
                           <li><a href="#">Find & merge duplicates...</a></li>
                           <li><a href="#">Restore contacts...</a></li>
                           <li><a href="#">Sort by</a></li>
                           <li><a href="#">First Name</a></li>
                           <li><a href="#">Last Name</a></li>
                        </ul>
                      <div class="clear"></div>
                    </div>
                    <!----------/// CONTACT END HERE ------------>
                    <!------/// THIRD CALENDER END HERE --------> 
        
      </div>
      <!---------/// CALENDER TOP THREE OPTION END HERE -------->
      <!---// TOOL --->
              <div class="right_tool_part cal_rig">
                   <!--/// CONTACT VIEW CHANGES OPTION STARED HERE --------->
                   <div class="con_viw">
                       <ul>
                         <a href="contact_pages.html"/> <li class="list_active">
                              <div class="con_list"></div>
                          </li></a>
                         <a href="contact_pages(details_view).html"/> <li>
                              <div class="de_list"></div>
                          
                          </li></a>
                       </ul>
                       <div class="clear"></div>
                   </div>
                   <!----/// CONTACT VIEW CHANGES END HERE ----------->
                   
                   <!---------/// CONTACT VIEW CHAGES END ---->
                   <div class="cal_next_and_Pre">
                   
                      <a href="#"><div class="right_tool_1"> <img src="images/next_mail.png" class="next_imag"> </div></a>
                      <a href="#"><div class="right_tool_1"> <img src="images/privious_mail.png" class="next_imag"> </div></a>
                      <!-- -PAGINATION -->
                      <div id="pagination_div" class="right_tool_2_11" style=" line-height: 29px;">1-15 of 44</div>
                    </div>
                    
                    
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
              </div>
      <!---/// TOOL END ---->
      </div>
      <!----------/// CONTACT HEADER END HERE ------->
      <!--/// LIST ACCORDING TO A TO 10 STRAED HERE ------->
      <div class="na_li">
           <table>
                   <tr>
                          <td class="na_li_active">123</td>
                          <td>A</td>
                          <td>B</td>
                          <td>C</td>
                          <td>D</td>
                          <td>E</td>
                          <td>F</td>
                          <td>G</td>
                          <td>H</td>
                          <td>I</td>
                          <td>J</td>
                          <td>K</td>
                          <td>L</td>
                          <td>M</td>
                          <td>N</td>
                          <td>O</td>
                          <td>P</td>
                          <td>Q</td>
                          <td>R</td>
                          <td>S</td>
                          <td>T</td>
                          <td>U</td>
                          <td>V</td>
                          <td>W</td>
                          <td>X</td>
                          <td>Y</td>
                          <td>Z</td>
                     </tr>
           </table>
      
      </div>
      <!-----// LIST ACCORDING TO A TO 10 END HERE -------->
             <!--------/// SPACE FOR CONTACT PAGES ------------->
             <!---//TABLE HAEDER STARED -------->
             <table class="con_he">
                <tr>
                    <td class="con_box"><input type="checkbox"/></td>
                    <td class="con_imag"><img src="images/white_man.png" /></td>
                    <td class="con_name">Name</td>
                    <td class="con_email">Email</td>
                    <td class="con_email">Mobile</td>
                    <td class="con_department">Department</td>
                    <td class="con_address">Address</td>
                </tr>
             </table>
             <!--------/// TABLE HEADER END HERE ------->
             <div id="contact_cnt_div_full" class="contact_content">
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
             </div>
             
             <!----------/// SPACE FOR CONTECT PAGES END HERE ------>
      
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
                         <div class="mail_left_1"> 
                    <span>Timesjobs Job Alert</span>
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
                  </a> 
                  </div>
                
                <!----------------/// Right Portion End ------------> 
                
                <!----------------/// Right Portion --------------->
                
                <div class="for_bottom_right"> 
                <a href="#" class="dustbin"><img src="images/tool.png"></a>
                  <div class="bor_1"></div>
                  <a href="#" class="bottom_down_1" onClick="bootom_forward(event);"><img src="images/open_sub_menu.png"></a> 
                  </div>
                
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












<!-------------------//// NEW SPILITTER END HERE ---------------> 

<!------------/// CREATE CALENDER POP STARED HERE ----------->
<!-------------/// EDITE NAME ------------>
<!--------// CREATE GROUP POP STRED HERE ----------->
<div class="group_name">
  <h1>Create Group<div class="cancel_top cancel_grop_top">X</div></h1>
  <table class="Sj">
    <tbody>
      <tr>
        <td class="Sl">Group Name</td>
        <td><input class="nr Sk" type="text" value=""></td>
      </tr>
      <tr>
          <td colspan="4">
                   <div class="save_right_name"> <div class="save_icon_name "></div> Save</div>
                   <div class="cancel_right_name cancel_grop"><div class="edite_icon_name"></div>Cancel</div>
          </td>
      </tr>
    </tbody>
  </table>
</div>
<!----------/// CREATE GROUP END HERE ---------------->
<div class="edite_name_box">
  <h1>Edit name<div class="cancel_top">X</div></h1>
  <table class="Sj">
    <tbody>
      <tr>
        <td class="Sl">Prefix:</td>
        <td><input class="nr Sk" type="text" value=""></td>
      </tr>
      <tr>
        <td class="Sl">First:</td>
        <td><input class="nr Sk" type="text" value="Hari"></td>
      </tr>
      <tr>
        <td class="Sl">Middle:</td>
        <td><input class="nr Sk" type="text" value="Om"></td>
      </tr>
      <tr>
        <td class="Sl">Last:</td>
        <td><input class="nr Sk" type="text" value="Srivastava"></td>
      </tr>
      <tr>
        <td class="Sl">Suffix:</td>
        <td><input class="nr Sk" type="text" value=""></td>
      </tr>
      <tr>
          <td colspan="4">
                   <div class="save_right_name"> <div class="save_icon_name"></div> Save</div>
                   <div class="cancel_right_name"><div class="edite_icon_name"></div>Cancel</div>
          </td>
      </tr>
    </tbody>
  </table>
</div>

<!----/// CREATE POP HERE ----->
<div class="create_contact">
   <h1>Create Contact
     <div class="cancel_top cancel_grop_top create_top">X</div>
   </h1>
   <!----// SAVE AND CANCEl --------->
   <table class="con_he_list_right_pop">
          <tbody>
              <tr>
                <td colspan="4"><div class="save_right_pop">
                    <div class="save_icon_pop"></div>
                    Save</div>
                    </td>
              </tr>
        </tbody>
    </table>
   <!------/// SAVE END HERE ---------->
   <div class="right_con_part_pop"> 
          
          <!------------/// RIGHT CONTENT STARED HERE -------------->
          <table>
            <tr>
              <td colspan="3" class="right_info_heading_pop">Personal Information</td>
            </tr>
            <tr>
              <td class="right_name_first_pop">Full Name </td>
              <td class="right_text_pop"><input type="text" value="Hariom Srivastava" />
                <div class="edite_name">
                  <div class="name_edit"></div>
                  Edit Name</div></td>
              <td rowspan="3"><img src="images/photo_1.jpg"/>
                <div class="save_chnage_pop">Change Image</div></td>
            </tr>
            <tr>
              <td class="right_name_first_pop">Company</td>
              <td><input type="text"  value="Silvereye IT solution Pvt.Ltd"/></td>
            </tr>
            <tr>
              <td class="right_name_first_pop">Job Title</td>
              <td><input type="text" value="Web Designer"/></td>
            </tr>
            <tr class="right_name_first_pop">
              <td colspan="3" class="right_info_heading">Internet</td>
            </tr>
            <tr class="right_name_first_pop">
              <td class="right_name_first">email</td>
              <td colspan="2"><input type="text" value="hari@silvereye.co" /></td>
            </tr>
            <tr class="right_name_first_pop">
              <td class="right_name_first">Display as</td>
              <td colspan="2"><input type="text"  value="Hariom"/></td>
            </tr>
            <tr class="right_name_first_pop">
              <td class="right_name_first">Web page address</td>
              <td colspan="2"><input type="text" value="silvereye.co"/></td>
            </tr>
            <tr class="right_name_first_pop">
              <td class="right_name_first">IM Address</td>
              <td colspan="2"><input type="text" value="" /></td>
            </tr>
            <tr class="right_name_first_pop">
              <td colspan="3" class="right_info_heading">Phone Number</td>
            </tr>
            <tr class="right_name_first_pop">
              <td class="right_name_first">Business</td>
              <td colspan="2"><input type="text" value="" /></td>
            </tr>
            <tr class="right_name_first_pop">
              <td class="right_name_first">Home</td>
              <td colspan="2"><input type="text" value="Mirzapur" /></td>
            </tr>
            <tr class="right_name_first_pop">
              <td class="right_name_first">Business Fax</td>
              <td colspan="2"><input type="text" value="+91-8956890678" /></td>
            </tr>
            <tr class="right_name_first_pop">
              <td class="right_name_first">Mobile</td>
              <td colspan="2"><input type="text" value="+91-9067567897" /></td>
            </tr>
            <tr class="right_name_first_pop">
              <td colspan="3" class="right_info_heading">Address</td>
            </tr>
            <tr class="right_name_first_pop">
              <td class="right_name_first">Business</td>
              <td colspan="2"><input type="text" value="Silvereye IT Solution Pvt.Ltd" /></td>
            </tr>
          </table>
          <!------------/// RIGHT CONTENT END ----------> 
          
        </div>
        <!---/// RIGHT PART END HERE ---------> 
        <!---------/// DETAILS VIEW END HERE -------------> 
        
      </div>

<!--------// EDITE NAME ----->      
<div class="create_contact_edit">
   <h1>Edit Contact
     <div class="cancel_top cancel_grop_top create_top_edit">X</div>
   </h1>
   <!----// SAVE AND CANCEl --------->
   <table class="con_he_list_right_pop_edit">
          <tbody>
              <tr>
                <td colspan="4">
                 <div class="save_right_edit"><div class="save_icon_edit"></div>Save</div>
                 <div class="cancel_edit"><div class="cancel_icon_edit">X</div>Cancel</div>
                 <div class="cancel_right_edit"><div class="edite_icon_edit"></div>Edit</div>
                 <div class="add_more_edit"><div class="add_more_icon_edit"></div>Add More</div>
                 
               
                    </td>
              </tr>
        </tbody>
    </table>
   <!------/// SAVE END HERE ---------->
   <div class="right_con_part_pop_edit"> 
          
          <!------------/// RIGHT CONTENT STARED HERE -------------->
          <table>
            <tr>
              <td colspan="3" class="right_info_heading_edit">Personal Information</td>
            </tr>
            <tr>
              <td class="right_name_first_pop_edit">Full Name </td>
              <td class="right_text_pop_edit"><input type="text" value="Hariom Srivastava" />
                <div class="edite_name_edit">
                  <div class="name_edit_edit"></div>
                  Edit Name</div></td>
              <td rowspan="3"><img src="images/photo_1.jpg"/>
                <div class="save_chnage_pop_edit">Change Image</div></td>
            </tr>
            <tr>
              <td class="right_name_first_pop_edit">Company</td>
              <td><input type="text"  value="Silvereye IT solution Pvt.Ltd"/></td>
            </tr>
            <tr>
              <td class="right_name_first_pop_edit">Job Title</td>
              <td><input type="text" value="Web Designer"/></td>
            </tr>
            <tr class="right_name_first_pop_edit">
              <td colspan="3" class="right_info_heading_edit">Internet</td>
            </tr>
            <tr class="right_name_first_pop_edit">
              <td class="right_name_first_edit">email</td>
              <td colspan="2"><input type="text" value="hari@silvereye.co" /></td>
            </tr>
            <tr class="right_name_first_pop_edit">
              <td class="right_name_first_edit">Display as</td>
              <td colspan="2"><input type="text"  value="Hariom"/></td>
            </tr>
            <tr class="right_name_first_pop_edit">
              <td class="right_name_first_edit">Web page address</td>
              <td colspan="2"><input type="text" value="silvereye.co"/></td>
            </tr>
            <tr class="right_name_first_pop_edit">
              <td class="right_name_first_edit">IM Address</td>
              <td colspan="2"><input type="text" value="" /></td>
            </tr>
            <tr class="right_name_first_pop_edit">
              <td colspan="3" class="right_info_heading_edit">Phone Number</td>
            </tr>
            <tr class="right_name_first_pop_edit">
              <td class="right_name_first_edit">Business</td>
              <td colspan="2"><input type="text" value="" /></td>
            </tr>
            <tr class="right_name_first_pop_edit">
              <td class="right_name_first_edit">Home</td>
              <td colspan="2"><input type="text" value="Mirzapur" /></td>
            </tr>
            <tr class="right_name_first_pop_edit">
              <td class="right_name_first_edit">Business Fax</td>
              <td colspan="2"><input type="text" value="+91-8956890678" /></td>
            </tr>
            <tr class="right_name_first_pop_edit">
              <td class="right_name_first_edit">Mobile</td>
              <td colspan="2"><input type="text" value="+91-9067567897" /></td>
            </tr>
            <tr class="right_name_first_pop_edit">
              <td colspan="3" class="right_info_heading_edit">Address</td>
            </tr>
            <tr class="right_name_first_pop_edit">
              <td class="right_name_first_edit">Business</td>
              <td colspan="2"><input type="text" value="Silvereye IT Solution Pvt.Ltd" /></td>
            </tr>
          </table>
          <!------------/// RIGHT CONTENT END ----------> 
          
        </div>
        <!---/// RIGHT PART END HERE ---------> 
        <!---------/// DETAILS VIEW END HERE -------------> 
        
      </div>
<!--------/// EDITE NAME END HERE -------->
<!---------// SHARING PAGES STARTED---------->
<div class="sharing_mange">
   <h1>Sharing settings - Delegate all 10 contacts in My Contacts <div class="mange_can_top">X</div></h1>
   <div class="table_append">
                   <table class="append_tr">
                       <tr>
                           <td colspan="2">Link to share (only accessible by collaborators)</td>
                       </tr>
                       <tr>
                           <td colspan="2"><input type="text"  class="link_share"/></td>
                       </tr>
                       <tr class="share_bottom">
                           <td colspan="2">Who has access</td>
                       </tr>
                       <tr>
                           <td>Hari Om Srivastava (you)<span class="email_share">hari@silvereye.co</span></td>
                           <td class="text_right">Is Owner</td>
                       </tr>
                       <tr class="share_bottom">
                           <td colspan="2">Invite people:</td>
                       </tr>
                       <tr>
                           <td><input type="text"  class="initive_people" /></td>
                           <td>
                              <div class="can_edit">
                                      <div class="share_more">Add More</div>
                                      <div class="can_edite">Can Edit</div>
                                     
                              </div>
                          </td>
                       </tr>
                   </table>
                   <div class="clear"></div>
   
        </div>
          <table>
            <tr>
           <td colspan="2"><input type="checkbox" />Notify people via email<a href="#">Add message</a></td>
          </tr>
   </table> 
                 <div>
                       <div class="your_self"><input type="checkbox"/><a href="#">Send a copy to myself </a></div>
                       <div class="cancel_share mange_can">Cancel</div>
                       <div class="send_share">Send</div>
            
  </div>
                 
                 
</div>
<!---------// SHARING PAGES END---------->

<!-------/// CREATE POP END HERE ------->
  <div class="web_dialog_overlay" ></div>
<!------------/// EDITE STARED HERE --------->

</body>
</html>