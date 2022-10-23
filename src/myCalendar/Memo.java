package myCalendar;


import myCalendar.memo.AddMemoFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class Memo  extends JFrame implements ActionListener {


    int x_size, y_size;
    JPanel panel = new JPanel(new BorderLayout());
    JPanel panel1 = new JPanel();
    JTextField nameField=new JTextField(20);
    JButton selectbtn=new JButton();
    JTable jTable = new JTable();
    JScrollPane panel2=  new JScrollPane();
    JButton  addBtn=new JButton("���");
    JButton  updateBtn=new JButton("�޸�");
    JButton  deleteBtn=new JButton("ɾ��");
    public  static  MemoModel memoModel = new MemoModel();
    public Memo(){

        super("����¼");
        setSize(600, 700);
        x_size = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth());
        y_size = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight());
        setLocation((x_size - 300) / 2, (y_size - 350) / 2);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //��ʼ������
        memoModel.reloadTable();
        panel1.add(addBtn);
        panel1.add(updateBtn);
        panel1.add(deleteBtn);

        jTable.setModel(memoModel);
        panel2.setViewportView(jTable);


        JPanel jPaneln = new JPanel();
        JLabel nameJLabel = new JLabel("���⣺");

        selectbtn = new JButton("��ѯ");
        panel1.add(nameJLabel);
        panel1.add(nameField);
        panel1.add(selectbtn);

        panel.add(jPaneln,BorderLayout.WEST);
        panel.add(panel1, BorderLayout.NORTH);
        panel.add(panel2, BorderLayout.CENTER);
        panel.setBackground(Color.white);
        panel1.setBackground(Color.white);
        panel2.setBackground(Color.white);
        //event
        addBtn.addActionListener(new AddBtnAction());
        updateBtn.addActionListener(new updateBtnAction());
        deleteBtn.addActionListener(new deleteBtnAction());
        selectbtn.addActionListener(new searchBtnAction());
        setContentPane(panel);
        setVisible(true);
        setResizable(false);
    }


    class AddBtnAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            new AddMemoFrame();
        }
    }

    class updateBtnAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            int index = jTable.getSelectedRow();
            if (index == -1) {
                JOptionPane.showMessageDialog(null, "δѡ��");
            } else {
                Object user = Memo.memoModel.rowData.get(index);
                new AddMemoFrame(user).show();
            }
        }
    }

    class searchBtnAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String text = nameField.getText();
            if(text.equals("")){
                return;
            }
            MemoModel.searchTitle(text);
        }
    }


    class deleteBtnAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
// TODO Auto-generated method stub
            int index = jTable.getSelectedRow();
            if (index == -1) {
                JOptionPane.showMessageDialog(null, "δѡ��");
            } else {

                Object meMo = Memo.memoModel.rowData.get(index);
                Integer id = (Integer) ((Vector) meMo).get(0);
                MemoModel.remoteData(id);
                JOptionPane.showMessageDialog(null, "ɾ���ɹ�");
                Memo.memoModel.reloadTable();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }




}





