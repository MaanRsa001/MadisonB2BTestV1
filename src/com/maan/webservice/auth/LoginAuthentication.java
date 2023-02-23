package com.maan.webservice.auth;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.traversal.DocumentTraversal;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.NodeIterator;

import com.maan.adminnew.common.CommonService;
import com.maan.webservice.service.PasswordEnc;

public class LoginAuthentication {
	public boolean getAuthenticateLogin(final String userName,final String password){
		boolean result = false;
		System.out.println("u:"+userName+"P:"+password);

		try{
			final DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			final DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			final Document doc = docBuilder.parse (new File(CommonService.getApplicationPath() + "/LoginInfo/PaymentWSAuth.xml"));
			DocumentTraversal traversal = (DocumentTraversal) doc;
			NodeIterator iterator = traversal.createNodeIterator(
					doc.getDocumentElement(), NodeFilter.SHOW_ELEMENT, null, true);
			for (Node n = iterator.nextNode(); n != null; n = iterator.nextNode()) {
				String tagName = ((Element) n).getTagName();
				if(tagName.indexOf("User-Name")!=-1){
					NodeList ch = ((Element)n).getChildNodes();
					String user = (String)ch.item(0).getNodeValue().trim();
					if(userName.equals(user)){
						String indx = tagName.replaceAll("User-Name", "");
						final NodeList n2 =doc.getElementsByTagName("Password"+indx);
						final Element e2=(Element) n2.item(0) ;
						ch = e2.getChildNodes();
						String pwd = (String)ch.item(0).getNodeValue().trim();
						System.out.println("pwd:"+pwd+"user:"+user);
						if(password.length() > 0 ){
							if(PasswordEnc.crypt(password).equals(pwd)){
								result = true;
							}else{
								result = false;
								// throw new SystemUnavailableException("Authentication Failed due to Invalid Details");
							}
						}else{
							result=false;
						}
						break;
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public static void main(final String[] argss){
		LoginAuthentication auth = new LoginAuthentication();
		System.out.println("Result=>"+auth.getAuthenticateLogin("AdityaBirla","abibl123"));
	}


}
