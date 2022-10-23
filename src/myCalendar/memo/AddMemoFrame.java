package myCalendar.memo;


import myCalendar.Memo;
import myCalendar.MemoModel;
import myCalendar.utils.DateUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Date;
import java.util.Vector;

public class AddMemoFrame extends JFrame implements ActionListener {
	JTextField titleField;
	JTextArea contentField;
	private Boolean isUpdate=false;
	private Integer modifyId=null;
	public AddMemoFrame() {
		setTitle("添加备忘录");
		setLayout(null);
		setBounds(300,300,500,500);

		JLabel snoJLabel=new JLabel("标题");
		JLabel cnoJLabel=new JLabel("内容");

		titleField=new JTextField();
		contentField=new JTextArea();

		//设置控件位置和大小
		snoJLabel.setBounds(50,20,100,100);
		titleField.setBounds(100,50,200,30);
		cnoJLabel.setBounds(50,70,100,100);
		contentField.setBounds(100,100,200,110);

		JButton yesButton=new JButton("确定");
		yesButton.setBounds(100,300,80,30);
		JButton noButton=new JButton("取消");
		noButton.setBounds(200,300,80,30);
		add(snoJLabel);
		add(titleField);
		add(cnoJLabel);
		add(contentField);

		add(yesButton);
		add(noButton);
		//给添加选课设置的确定和取消按钮的监听事件
		yesButton.setActionCommand("确定");
		yesButton.addActionListener(this);
		noButton.setActionCommand("取消");
		noButton.addActionListener(this);

		setVisible(true);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


	public AddMemoFrame(Object info){
		setTitle("修改备忘录");
		setLayout(null);
		isUpdate=true;

		setBounds(300,300,500,500);
		Vector getInfo= (Vector) info;
		JLabel snoJLabel=new JLabel("标题");
		JLabel cnoJLabel=new JLabel("内容");

		String getTitle= (String) getInfo.get(1);
		String getContent= (String) getInfo.get(2);

		modifyId = (Integer) getInfo.get(0);

		titleField=new JTextField(getTitle);
		contentField=new JTextArea(getContent);

		//设置控件位置和大小
		snoJLabel.setBounds(50,20,100,100);
		titleField.setBounds(100,50,200,30);
		cnoJLabel.setBounds(50,70,100,100);
		contentField.setBounds(100,100,200,110);

		JButton yesButton=new JButton("确定");
		yesButton.setBounds(100,300,80,30);
		JButton noButton=new JButton("取消");
		noButton.setBounds(200,300,80,30);
		add(snoJLabel);
		add(titleField);
		add(cnoJLabel);
		add(contentField);

		add(yesButton);
		add(noButton);
		//给添加选课设置的确定和取消按钮的监听事件
		yesButton.setActionCommand("确定");
		yesButton.addActionListener(this);
		noButton.setActionCommand("取消");
		noButton.addActionListener(this);

		setVisible(true);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("确定")) {

			if(isUpdate){
				String title=titleField.getText();
				String content=contentField.getText();
				MemoContent memoContent=new MemoContent();
				memoContent.setId(modifyId);
				memoContent.setContent(content);
				memoContent.setCreateTime(DateUtil.format(new Date(),DateUtil.DATE_FORMAT_FORMAT));
				memoContent.setTitle(title);
				MemoModel.updateRowData(memoContent);
				JOptionPane.showMessageDialog(null, "修改成功");
				this.hide();
				Memo.memoModel.reloadTable();
				return;
			}

			String title=titleField.getText();
			String content=contentField.getText();
			MemoModel memoModel = Memo.memoModel;
			int size = memoModel.rowData.size();
			MemoContent memoContent=new MemoContent();
			memoContent.setId(size+1);
			memoContent.setContent(content);
			memoContent.setCreateTime(DateUtil.format(new Date(),DateUtil.DATE_FORMAT_FORMAT));
			memoContent.setTitle(title);
			try {
				MemoModel.writeDataToFile(memoContent);
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "添加成功");

		}else {
			this.hide();
			 Memo.memoModel.reloadTable();
		}
	}

}
