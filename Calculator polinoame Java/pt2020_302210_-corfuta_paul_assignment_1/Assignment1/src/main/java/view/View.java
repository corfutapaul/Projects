package view;

import javax.swing.*;

import java.awt.*;

public class View {

    private JFrame frame;
    private JTextArea TextArea;
    private JTextArea TextArea1;
    private JTextArea TextArea2;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;

    public View(String title) {

        frame = new JFrame(title);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 120);
        frame.setVisible(true);


        TextArea = new JTextArea("STUDENT");
        TextArea1 = new JTextArea("Polinom2");
        TextArea2 = new JTextArea("Rezultat");
        button1 = new JButton("Addition");
        button2 = new JButton("Substraction");
        button3 = new JButton("Derivate");
        button4 = new JButton("Multiplication");
        button5 = new JButton("Integrate");


        GroupLayout layout = new GroupLayout(frame.getContentPane());
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(
        		   layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        		           .addComponent(TextArea)
        		           .addComponent(TextArea2))
        		      .addComponent(TextArea1)
        		      .addComponent(button1)
        		      .addComponent(button2).addComponent(button3).addComponent(button4).addComponent(button5)      		      
        		);
        		layout.setVerticalGroup(
        		   layout.createSequentialGroup()
        		      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        		           .addComponent(TextArea)
        		           .addComponent(TextArea1)
        		           .addComponent(button1)
        		           .addComponent(button2).addComponent(button3).addComponent(button4).addComponent(button5))
        		      .addComponent(TextArea2)
        		);  		
        frame.getContentPane().setLayout(layout);
    }
    public JButton getButton5() {
		return button5;
	}

	public void setButton5(JButton button5) {
		this.button5 = button5;
	}

	public JButton getButton4() {
		return button4;
	}

	public void setButton4(JButton button4) {
		this.button4 = button4;
	}

	public JButton getButton3() {
		return button3;
	}

	public void setButton3(JButton button3) {
		this.button3 = button3;
	}

	public JButton getButton2() {
		return button2;
	}

	public void setButton2(JButton button2) {
		this.button2 = button2;
	}

	public JTextArea getTextArea1() {
		return TextArea1;
	}

	public void setTextArea1(JTextArea studentTextArea1) {
		this.TextArea1 = studentTextArea1;
	}

	public JTextArea getTextArea2() {
		return TextArea2;
	}

	public void setTextArea2(JTextArea studentTextArea2) {
		this.TextArea2 = studentTextArea2;
	}

	public JFrame getFrame() {

        return frame;

    }

    public void setFrame(JFrame frame) {

        this.frame = frame;

    }

    public JTextArea getTextArea() {

        return TextArea;

    }

    public void setTextArea(JTextArea studentTextArea) {

        this.TextArea = studentTextArea;

    }

    public JButton getButton1() {

        return button1;

    }

    public void setButton1(JButton button1) {

        this.button1 = button1;

    }

}