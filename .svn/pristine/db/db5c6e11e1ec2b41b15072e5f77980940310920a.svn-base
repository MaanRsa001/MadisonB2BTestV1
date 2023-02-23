<script>
function change_class(val) { 
	document.getElementById(val).className='buttonsMenuBlueMOvar';
}
function RevertClass(val) {
	document.getElementById(val).className='buttonsMenuBlue';
}
</script>
<div class="table0">
	<div class="tablerow">
		<%		
			String productName="";
			String openCoverMississippiNo="";
			String openCoverCustomerName="";
			
			String s1=""+session.getAttribute("mode");
			String path = request.getContextPath();
			String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		%>
	</div>
	<div class="tablerow">
		<div class="layoutHeader">
			<div class="pullLeft">
				<img src="${pageContext.request.contextPath}/images/mgen-logo.png" border="0" >
			</div>
			<div class="pullRight" style="color: #000000;">
				<font color="red" style="font: bold;font-size: 30px;"><s:property value="#session.userLoginMode"/>&nbsp;&nbsp;Environment</font> <br/>
				<span style="font-size: 12px;">
					<a href="${pageContext.request.contextPath}/HomeUser.action">Home</a>&nbsp;&nbsp;|&nbsp;&nbsp;			
					<a href="${pageContext.request.contextPath}/Loginout.action">Sign Out</a>&nbsp;&nbsp;&nbsp;&nbsp;
					<%	if(s1.trim().equals("s")) {	%>
				 		<font style="font-weight: bold;color:#000000"> LoginId&nbsp;:&nbsp;&nbsp;<%=(String)session.getAttribute("RSAUSER")%>&nbsp;&nbsp;- <%=productName%> </font>
				 	<%	} else { %>
				 		<font style="font-weight: bold;color:#000000"> LoginId&nbsp;:&nbsp;&nbsp;<%=(String)session.getAttribute("user")%>&nbsp;- <%=productName%></font>
				 	<%	} %>
				</span>
			</div>
			<br class="clear"/>
		</div>
	</div>	
</div>
