<%@ page isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html <s:if test="locale.language == 'ar'">dir="rtl"</s:if> >
	<body>
		<table width="100%">
       	 	<tr align="center">
       	 		<td  style="color:red;"><s:actionerror/></td>
       	 	</tr>
		</table>
	</body>
</html>