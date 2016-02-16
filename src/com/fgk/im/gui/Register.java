package com.fgk.im.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;

import com.fgk.im.socket.MessageSend;
import com.fgk.im.util.MyValidate;

/** 
* @author fanguangkai E-mail: fgkxwh@126.com
* @version 创建时间：2016年2月16日 下午3:29:55 
* 注册界面
*/
public class Register {

	private JFrame frame;
	private JPanel panel;
	private JLabel labelUsername;
	private JLabel labelPassword;
	private JLabel labelPasswordRep;//重复输入密码
	private JTextArea textAreaUsername;
	private JPasswordField password;
	private JPasswordField passwordRep;//重复输入密码
	private JButton buttonConfirm;
	private JButton buttonCancel;
	
	public Register(){
		
		_initComponents();
		_setEvent();
	}
	
	private void _initComponents(){
		
		frame = new JFrame("注册");
		frame.setSize(500, 500);
		frame.setVisible(true);
		
		panel = new JPanel();
		
		labelUsername = new JLabel("用户名：");
		labelPassword = new JLabel("密码：");
		labelPasswordRep = new JLabel("再次输入密码：");
		
		textAreaUsername = new JTextArea();
		textAreaUsername.setColumns(10);
		
		password = new JPasswordField();
		password.setColumns(10);
		
		passwordRep = new JPasswordField(10);
		passwordRep.setColumns(10);
		
		buttonConfirm = new JButton("确定");
		buttonCancel = new JButton("取消");
		
		panel.add(labelUsername);
		panel.add(textAreaUsername);
		panel.add(labelPassword);
		panel.add(password);
		panel.add(labelPasswordRep);
		panel.add(passwordRep);
		panel.add(buttonConfirm);
		panel.add(buttonCancel);
		
		//布局设置
		panel.setLayout(new FlowLayout());
		
		frame.add(panel);
		
	}
	
	private void _setEvent(){
		
		//确定按钮单击事件
		buttonConfirm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				_buttonConfirmActionPerformed(e);
			}
		});
		
		//取消按钮单击事件
		buttonCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				_buttonCancelActionPerformed(e);
			}
		});
		
	}
	
	/**
	 * 确定按钮  buttonConfirm的事件监听处理函数
	 * @param event
	 */
	private void _buttonConfirmActionPerformed(ActionEvent event){
		
		String strUsername = this.textAreaUsername.getText();
		String strPassword = this.password.getText();
		String strPasswordRep = this.passwordRep.getText();
		
		if (MyValidate.isNullOrEmpty(strUsername,strPassword,strPasswordRep)) {//如果输入的用户名，密码，和重复输入的密码有为空的
			
			System.out.println("输入不能为空");
			return;
			
		}else {//输入的都不为空
			
			if (strPassword.equals(strPasswordRep)) {//如果两次输入的密码相同
				
				MessageSend.send("UserService/register",strUsername,strPassword);
			}else {
				
				System.out.println("两次输入的密码不相同");
				return;
			}
		}
		
	}
	
	/**
	 * 取消按钮  buttonCancel的事件监听处理函数
	 * @param event
	 */
	private void _buttonCancelActionPerformed(ActionEvent event){
		
		System.exit(0);//退出此界面
		
	}
	
	public static void main(String[] args) {

		new Register();
	}

}
