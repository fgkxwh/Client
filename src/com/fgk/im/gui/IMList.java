package com.fgk.im.gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/** 
* @author fanguangkai E-mail: fgkxwh@126.com
* @version 创建时间：2016年2月16日 下午8:25:49 
* 好友列表界面 
*/
public class IMList implements IGui {

	private JFrame frame;
	private JPanel panel;
	private JLabel labelUserInfo;
	private JList<Object> listFriend;
	
	public IMList() {
		initComponents();
		setEvent();
	}
	
	
	@Override
	public void initComponents() {
		
		frame = new JFrame("im");
		frame.setSize(200, 800);
		frame.setVisible(true);
		
		//临时数据
		labelUserInfo = new JLabel("xxxxzzzzzzzzssssssszzzz");
		
		//临时数据
		Vector<Object> friend = new Vector<Object>();
		friend.add("");//第一个元素单击无法显示
		friend.add("zzzz");
		friend.add("qqqq");
		listFriend = new JList<>(friend);
		
		panel = new JPanel();
		panel.add(labelUserInfo);
		panel.add(listFriend);
		
		frame.add(panel);
	}




	@Override
	public void setEvent() {
		
		//列表单击事件
		listFriend.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){  
		       
				_listFriendActionPerformed(e);
		      }  
		});
		
	}

	//列表单击事件处理函数
	private void _listFriendActionPerformed(MouseEvent event){
		
		 if(event.getClickCount()==2){   //双击
	        	JList listFriend =  (JList) event.getSource();
	        	int index = listFriend.locationToIndex(event.getPoint());
	        	if (index > 0) {
	        		String username = listFriend.getModel().getElementAt(index).toString();
	        		System.out.println(username);
	        		//调用聊天界面.....
				}
	        }
	}


	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		
		new IMList();

	}

}
