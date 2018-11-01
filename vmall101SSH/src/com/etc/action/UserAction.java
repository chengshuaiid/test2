package com.etc.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import com.etc.vmall101.bean.User;
import com.etc.vmall101.service.UserService;
import com.etc.vmall101.service.impl.UserServiceImpl;
import com.opensymphony.xwork2.Action;

public class UserAction extends BaseAction {
	private String username;
	private String password;
	private String repwd;
	private String identity;
	private String email;
	private String sessionid;
	
	private String remenber;
	private String targetPage;
	private String VCInput;
	
	HttpServletResponse response;
	
	public String login() throws Exception{
		User user = new User();
		user.setUname(username);
		user.setPassword(password);
		UserService us = new UserServiceImpl();
		if(us.login(user)!=null){
			User u = us.login(user);
			if(remenber==null){
				/*HttpSession session = ServletActionContext.getRequest().getSession();
				session.setMaxInactiveInterval(900);*/
			}
			session.put("user", u);
			if("用户".equals(u.getIdentity())){
				this.addActionMessage("<script>alert('登录层功！')</script>");
				this.targetPage="/index.jsp";
				return Action.INPUT;
			}else{
				this.addActionMessage("<script>alert('管理员');location='/user/login.jsp'</script>");
				this.targetPage="/user/login.jsp";
				return Action.NONE;
			}
		}else{
			this.addActionMessage("<script>alert('用户名或密码错误，请重新输入');hsitory.back()</script>");
			this.targetPage="/user/login.jsp";
			return Action.NONE;
		}
	}
	
	public String logout() throws Exception{
		session.remove("user");
		session.remove("shopCartList");
		this.addActionMessage("<script>alert('注销成功！')</script>");
		this.targetPage="/user/login.jsp";
		return Action.NONE;
	}
	public String register() throws Exception{
		if(username==null ||password==null ||repwd==null ||email==null){
			this.addActionMessage("<script>alert('请填写用户信息！');location.href='/user/register.jsp'</script>");
			this.targetPage="/user/register.jsp";
			return Action.NONE;
		}
		if(!password.equals(repwd)){
			this.addActionMessage("<script>alert('两次密码不一致！');history.back()</script>");
			this.targetPage="/user/register.jsp";
			return Action.NONE;
		}
		User user = new User();
		user.setUname(username);
		user.setPassword(password);
		user.setIdentity("用户");
		user.setEmail(email);
		user.setSessionid(Long.toString(System.currentTimeMillis()));
		UserService us = new UserServiceImpl();
		if(us.register(user)){
			this.addActionMessage("<script>alert('注册成功！请登录');location='login.jsp'</script>");
			this.targetPage="/user/login.jsp";
			return Action.SUCCESS;
		}else{
			this.addActionMessage("<script>alert('注册失败！请重试');location='register.jsp'</script>");
			this.targetPage="/user/register.jsp";
			return Action.NONE;
		}
	}
	public String checkusername() throws Exception{
		username = new String(username.getBytes("iso8859-1"), "utf-8");
		UserService us = new UserServiceImpl();
		User user = new User();
		user.setUname(username);
		String responseText = us.registerName(user) ? "no" : "yes";
		this.inputStream = new ByteArrayInputStream(responseText.getBytes("utf-8"));
		
		return "checkusername";
	}
	public String checkemail() throws Exception{
		email = new String(email.getBytes("iso8859-1"),"utf8");
		
		UserService us = new UserServiceImpl();
		User user = new User();
		user.setEmail(email);
		String responseText = us.registerEmail(user) ? "no" : "yes";
		this.inputStream = new ByteArrayInputStream(responseText.getBytes("utf-8"));
		return "checkemail";
	}
	public String checkvalcode() throws Exception{
		String VCImg = (String) session.get("valCodeInSession");
		String responseText = VCImg.toLowerCase().equals(VCInput.toLowerCase()) ? "no" : "yes";
		this.inputStream = new ByteArrayInputStream(responseText.getBytes("utf-8"));
		
		return "checkvalcode";	
	}
	
	private InputStream inputStream;

	public InputStream getInputStream() {
		return inputStream;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getRepwd() {
		return repwd;
	}

	public void setRepwd(String repwd) {
		this.repwd = repwd;
	}

	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSessionid() {
		return sessionid;
	}
	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}

	public String getRemenber() {
		return remenber;
	}

	public void setRemenber(String remenber) {
		this.remenber = remenber;
	}

	public String getTargetPage() {
		return targetPage;
	}

	public void setTargetPage(String targetPage) {
		this.targetPage = targetPage;
	}

	public String getVCInput() {
		return VCInput;
	}

	public void setVCInput(String vCInput) {
		VCInput = vCInput;
	}
	
}
