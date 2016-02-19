package com.fgk.im.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.fgk.im.util.AppCache;

/** 
* @author fanguangkai E-mail: fgkxwh@126.com
* @version 创建时间：2016年2月16日 下午8:25:49 
* 好友列表界面 
*/
public class FriendList implements IGui {

	private String username;
	private Vector<Object> friends = new Vector<Object>();
	private JFrame frame;
	private JPanel panel;
	private JLabel labelUserInfo;
	private JList<Object> listFriend;
	
	public FriendList() {
//		initComponents();
//		setEvent();
	}
	
	
	@Override
	public void initComponents() {
		
		frame = new JFrame("好友列表");
		frame.setSize(200, 800);
		frame.setVisible(true);
		
		//临时数据
		labelUserInfo = new JLabel("欢迎你xxxxxxxxxxxx"+"<"+getUsername()+">");
		
		//临时数据
//		Vector<Object> friend = new Vector<Object>();
		
		listFriend = new JList<Object>(getFriends());
		listFriend.setBorder(new LineBorder(Color.black));
		
		panel = new JPanel();
		panel.add(labelUserInfo);
		panel.add(listFriend);
		
		//布局,简单的流式布局
		panel.setLayout(new FlowLayout());
		
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
	        		//调用聊天界面
	        		PrivateTalk privateTalk = new PrivateTalk();
	        		privateTalk.setUsername(username);
	        		privateTalk.show();
	        		AppCache.privateTalk = privateTalk;
				}
	        }
	}


	@Override
	public void show() {
		initComponents();
		setEvent();
		
	}
	
	public static void main(String[] args) {
		
		new FriendList().show();

	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public Vector<Object> getFriends() {
		return friends;
	}


	public void setFriends(Vector<Object> friends){
		
		this.friends = friends;
	}
	
	public void addFriend(Object friend) {
		
		friends.add("");//第一个元素单击无法显示
		this.friends.add(friend);
	}

}
