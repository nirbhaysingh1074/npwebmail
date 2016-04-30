<%@page import="webmail.wsdl.CreateCalendarRequest"%>
<%@page import="java.util.List"%>
<html>
    <head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">   
<!------/// SCRIPT FOR CALENDER---------->  
<script src="js/contact_js.js"></script>
<link type="text/css" rel="stylesheet" href="css/contact_css.css" />
<!---------/// SCRIPT END ----------------->
      </head>
    <body >             
             <div class="left-pane" > 
    
    <!--------------///// WHEN LEFT IS OPEN STARED HERE ----->
    <div class="left_open"> 
      <!------- /// LEFT TOP STARED HERE ------->
      <!-- <div class="top_left"> <a href="#">
        <div class="top_right_icon" id="hide_left"> <img src="images/open_state.png" /> </div>
        </a> </div> -->
      
      <!------- /// LEFT TOP END HERE ------->
      <div class="clear"></div>
      <!-------/// LEFT MID CONTENT STARED HERE --------->
      <div class="left_tab_content" >
        <div class="left_three_box calender_js" > 
          <!---------/// Create calender --------> 
          <!--  <div class="create_calender">Create</div>
                           <!---------/// create calender end here ------> 
          <!-----------/// MY CALENDER ----------------->
          <div class="my_claender">My Calendars</div>
          <div class="my_calender_content">
            <ul>
            <%
            HttpSession hs = request.getSession();
    		String fname = (String) hs.getAttribute("fname");
            System.out.println(">>>>>>>>>>>>>>>>>>>>> user name : " + fname);
            List <CreateCalendarRequest> calendarlist=(List <CreateCalendarRequest>)request.getAttribute("calendarfilelist");
            List<String> filenames=(List<String>)request.getAttribute("filenames");
            int i=0;
            for(CreateCalendarRequest cl: calendarlist)
            {  	      
            	String calname = filenames.get(i);
            	if(calname.equals("default.ics"))
            	{
		            %>
		              <li id="<%=filenames.get(i)%>">
		                <div class="color_calender" style="background-color: <%=cl.getCalColor()%>"></div>
		                <span style="color: black;"><%=cl.getCalID() %></span>
		                <div class="cal_option"><img src="images/cal-open.png" /></div>
		                <div class="clear"></div>
		              </li>
		            <%
//             		break;
            	}
	            i++;
            }
            
            i=0;
            for(CreateCalendarRequest cl: calendarlist)
            {  	      
            	String calname = filenames.get(i);
            	if(!calname.equalsIgnoreCase("default.ics"))
            	{
		            %>
		              <li id="<%=filenames.get(i)%>">
		                <div class="color_calender" style="background-color: <%=cl.getCalColor()%>"></div>
		                <span style="color: black;"><%=cl.getCalID() %></span>
		                <div class="cal_option"><img src="images/cal-open.png" /></div>
		                <div class="clear"></div>
		              </li>
		            <%
            		
            	}
	            i++;
            }
            
            
            %>  
            </ul>
             <!------/// CALENDER MORE OPTION ------->
               
                <!----------/// CALENDER MORE OPTION END HERE ---------->
            
          </div>
       </div>
          <!-------------/// MY CALENDER END HERE --------> 
		<div class="left_three_box calender_js" >           
          <!-----------/// Other Activies CALENDER ----------------->
          <div class="other_claender other_bottom_arrow">Shared with Me</div>
          <div class="other_calender_content" style="display: block;">
            <ul>
             <%   
             List <CreateCalendarRequest> sharedcalendarlist=(List<CreateCalendarRequest>)request.getAttribute("sharedcalendarfilelist");
             List<String> sharedfilenames=(List<String>)request.getAttribute("sharedfilenames");
             i=0;
            for(CreateCalendarRequest cl: sharedcalendarlist)
            {  	            	
            	
            	String calendarfilename=cl.getCalID();
            	String calendarfilecolor=cl.getCalColor();                	
            %>
              <li id="<%=sharedfilenames.get(i)%>">
                <div class="color_calender" style="background-color: <%=calendarfilecolor%>"></div>
                <span style="color: black;" title="<%=calendarfilename %> (<%=cl.getUserid() %>)"><%=calendarfilename %> (<%=cl.getUserid() %>)</span>
                </li>
               <%i++;} %>
             </ul>
                   <!------/// CALENDER MORE OPTION ------->
               <!--  <div class="other_calender_option">
                  <ul>
                    <li><a href="#">Calendar settings</a></li>
                    <li><a href="#">Edit notifications</a></li>
                    <li style="padding: 0px !important;"><a href="#">Share this Calendar</a></li>
                    <li><a href="#">Create event on this calendar</a></li>
                    <li><a href="#">Display only this Calendar</a></li>
                    <li class="calender_color"></li>
                    <li><a href="#">Choose custom color</a></li>
                  </ul>
                  <div class="clear"></div>
                </div> -->
                <!----------/// CALENDER MORE OPTION END HERE ---------->
          </div>
          <!-------------/// Other Activies CALENDER END HERE --------> 
          
        </div>
       </div>
     </div>
   </div>  
</body>
</html>