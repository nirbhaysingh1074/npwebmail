<%@page import="webmail.wsdl.Folder"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<script src="js/jquery-1.8.3.min.js"></script>
 <script src="js/jquery.autocomplete.js"></script>
<script type="text/javascript">
    $(document).ready(function($) {
    $("#share_input").autocomplete("${pageContext.request.contextPath}/autocompldap");
    });
 </script>


 		<%
 		webmail.wsdl.Folder currentFolder=(webmail.wsdl.Folder)request.getAttribute("currentFolder");
 		System.out.println("current"+currentFolder);
 		HttpSession hs=request.getSession();
 		String urid=hs.getAttribute("id").toString();
 		String dom="";
 		String arr[]=urid.split("@");
 		if(arr.length>1)
 		{
 			dom=arr[1];
 		}
        %>
<h1>Sharing
<div class="mange_can_top">X</div>
</h1>
<input type="hidden" id="hid_dom" value="<%=dom %>"/>
<div class="table_append">
<table class="append_tr">
<tr class="share_bottom">
  <td colspan="2">Who has access</td>
</tr>
<tr>
   
<td colspan="2" class="own_row">
<span class="you_share"><%=currentFolder.getCreatedBy() %> </span>
<span class="text_right">Is Owner </span>
</td> 
                       </tr>
                       <tr>
                         <td colspan="2" class="invite_people_list">
                             <div class="invite_people_box">
                              <%
                              String f="";
                                      List<String> users=currentFolder.getUserRead();
                                      String[] user=users.toString().split(",");
                                      int ii=1;
                                      for(String str:user){
                                   	   if(ii>1){
                                   	   if(!str.equals("admin")){
                                   	   str=str.replace("]", "");
                                   	   str=str.replace("[", "");
                                   	   str = str.split("/")[0];
                                   	   System.out.println(str+(str.equals(",")||str.equals("")||str.equals(" ")||str.equals("]")||str.equals("[")));
                                   	   if((str.equals(",")||str.equals("")||str.equals(" ")||str.equals("]")||str.equals("["))){}else{
                                   		   String flag="Can View";
                                   		   f="ur";
                                   		 /*   if(currentFolder.getUserWrite().toString().contains(str)){
                                   			   flag="Can Edit";
                                   			   f="uw";
                                   		   } */
                                   		   if(currentFolder.getUserSecurity().toString().contains(str)){
												flag="Can Manage"  ;                                  
												f="us";
                                   		   } 
                                                    	   %>
                             <div class="select_append">
                             <div class="share_content">
                             <input class="userforshare userforshhare"  type="text" readonly="readonly" value="<%=str %>" /></div>
                             <div class="select_option">
                             <%
                             System.out.println("flag : "+f);
                             if(f.equals("ur")){
                            	 %>
                            	 <div class="can_edite">
                                            <select  class="permissionsforshare">
                                               <option value="ur" selected="selected">Can View </option>
                                              <!--  <option value="uw" >Can Edit </option> -->
                                                <option value="us">Can Manage </option> 
                                           </select>
                                      </div>
                            	 
                            	 <%
                            	 }
                         if(f.equals("us")){
                            	 %>
                          		<div class="can_edite">
                                            <select  class="permissionsforshare">
                                               <option value="ur">Can View </option>
                                              <!--  <option value="uw">Can Edit </option> -->
                                               <option value="us" selected="selected">Can Manage </option> 
                                           </select>
                                      </div> 
                            	 
                            	 <%
                            	}
                            // if(f.equals("uw")){
                            	 %>
                            	<!--  <div class="can_edite">
                                            <select  class="permissionsforshare">
                                               <option value="ur">Can View </option>
                                               <option value="uw" selected="selected">Can Edit </option>
                                               <option value="us">Can Manage </option>
                                           </select>
                                      </div> -->
                            	 
                            	 <%
                            	// }
                             %>
                             
                             
                             
                             </div>
                             <div class="remove_select delete_shre"> <img onclick="shareDelete(this.id)"  id="delete,delimeter,<%=currentFolder.getFolderPath()+",delimeter,"+str+",delimeter,"+f %>" src="images/tool.png" /></div><div class="clear"></div></div><div class="clear"></div></div>
                               <%}}}ii++;} %>
                             
                             <div class="clear"></div>
                             </div>
                         </td>
                       </tr>
                       <tr class="share_bottom">
                           <td colspan="2">Invite people:</td>
                       </tr>
                       <tr>
                           <td><input type="text"  class="initive_people " id="share_input"/></td>
                           <td>
                              <div class="can_edit">
                                     
                                      <div class="can_edite">
                                            <select  class="permissionsforshare npselect">
                                               <option value="ur">Can View </option>
                                               <!-- <option value="uw">Can Edit </option> -->
                                                <option value="us">Can Manage </option> 
                                           </select>
                                      </div>
                                       <div class="share_more">Add</div>
                                     
                              </div>
                          </td>
                       </tr>
                   </table>
					<script type="text/javascript">
					$(function() {
					 window.availableTags = [ <%-- <%
					                  String userslist="";
					                     ArrayList<String> userlist=(ArrayList<String>)request.getAttribute("userlist");
					                   for(String str:userlist){
					                	   if(!currentFolder.getCreatedBy().equals(str)){
					                	%>   <%="'"+str+"',"%>
					                   <%}}
					                     %> --%>];
					
					$( ".userforshare" ).autocomplete({
					source: availableTags
					});
					});
					</script>
                   <div class="clear"></div>
                     <div class="share_save_close">
                 <div class="your_self"><input type="checkbox"/><a href="#">Notify via Email </a></div>
                    <div class="share_save_box">
                              <div class="cancel_share mange_can">Cancel</div>
                               <div class="send_share cancel_share" id="shareButtonId" onclick="assignSinglePermissions('currentFile')" >Save</div>
                               <div class="clear"></div>
                    </div>
                 <div class="clear"></div>
                 </div>
                 <div class="clear"></div>
                 
                 <script type="text/javascript">
                 function shareDelete(folderPathP){
                	 var fp=folderPathP.split(",delimeter,");
                	// alert(fp[4]);
             		$.ajax({
    					type : "GET",
    					url : "${pageContext.request.contextPath}/removeAssignedPermission",
    					data : {
    						'folderPath' : fp[1],
    						'value' : fp[3],
    						'user' : fp[2],
    					},
    					contentType : "application/json",
    					async : true,
    					success : function(data) {
    						if(data=="true"){location.href="ajaxTrue";}else{
    							//alert("Shared Successfully");
    						}
    					}
    				}); 
                	 
                	 
                	 
                 }
                 
                 </script>