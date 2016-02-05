package com.fgk.im.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;

import org.hibernate.classic.Validatable;

import com.fgk.im.bean.Data;
import com.fgk.im.socket.MessageSend;
import com.fgk.im.socket.SocketHandler;
import com.fgk.im.util.Validate;

import junit.framework.Assert;
import junit.framework.Test;

public class Login{
	
	private JFrame frame;
	private JPanel panel;
	private JButton buttonConfirm;//确定按钮
	private JLabel labelUserName;//用户名标签
	private JLabel labelPwd;//密码标签
	private JButton buttonCancel;//取消按钮
	private JTextArea textAreaUsername;//用户名输入框
	private JPasswordField passwordFieldPwd;//密码输入框
	
	public Login(){
		
		_initComponents();//界面初始化
		_setEvent();//组件设置监听事件
	}
	
	private void _initComponents(){
		
		frame = new JFrame("Login");
		frame.setSize(500, 500);
		frame.setVisible(true);
		
		panel = new JPanel();
		panel.setVisible(true);
		
		buttonConfirm =  new JButton("确定");
		buttonCancel = new JButton("取消");
		
		labelUserName = new JLabel("用户名");
		labelPwd = new JLabel("密码");
		
		textAreaUsername = new JTextArea();
		textAreaUsername.setColumns(10);
		passwordFieldPwd = new JPasswordField();
		passwordFieldPwd.setColumns(20);
		
		panel.add(buttonConfirm);
		panel.add(buttonCancel);
		panel.add(labelUserName);
		panel.add(labelPwd);
		panel.add(textAreaUsername);
		panel.add(passwordFieldPwd);
		
		
		frame.add(panel);
	}
	
	private void _setEvent(){
		
		//login 按钮单击事件
		buttonConfirm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				_buttonConfirmActionPerformed(e);
			}
		});
	}
	
	/**
	 * button的事件监听处理函数
	 * @param event
	 */
	private void _buttonConfirmActionPerformed(ActionEvent event){
		
		String strUsername =  this.textAreaUsername.getText();//用户输入的用户名
		String strPassword = this.passwordFieldPwd.getText();//用户输入的密码

		List<Object> params = new ArrayList<Object>();
		params.add(strUsername);
		params.add(strPassword);
		
		if (SocketHandler.session !=null) {
			
			MessageSend.send(new Data("UserService/login",params));
		}else {
			
			System.out.println("SocketHandler.session 为空，数据发送失败");
		}
	}
	
	
	//界面测试主函数
	public static void main(String[] args){
		
		new Login();
	}
	
	public void start(){
		new Login();
	}
	
}
