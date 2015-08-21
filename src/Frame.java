import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Demo program for Swing to search for a word recommendation from typed text.
 * @author Glen Feldkamp
 *
 */
public class Frame extends JFrame 
{
	private JButton button;
	private JTextField entryField;
	private JScrollPane scrollText;
	private JList wordList;
	private ArrayList<String> wordsTyped;
	private WordManager wordManager = new WordManager();
	
	public Frame() 
	{
		super();
		setSize(500,800);
		
		setResizable(true);
		setTitle("Automatic Word Complete");
		
		this.setLayout(new BorderLayout());
		
		//panel for implementing entry field
		JPanel textPanel = new JPanel();
		textPanel.setLayout(new FlowLayout());
		this.add(textPanel, BorderLayout.NORTH);
		
		//panel for implementing open button
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		this.add(buttonPanel, BorderLayout.SOUTH);
		
		//configure entry field
		entryField = new JTextField(16);
		entryField.setHorizontalAlignment(JTextField.LEFT);
		textPanel.add(entryField);
		
		//configure button position
		button = new JButton("Open");
		buttonPanel.add(button);
				
		//Button Listener for open button
		OpenButtonListener obl = new OpenButtonListener();
		button.setActionCommand("open");
		button.addActionListener(obl);
				
		//Document Listener for text field
		MyDocumentListener docl = new MyDocumentListener();
		entryField.getDocument().addDocumentListener(docl);
		
		//Mouse listener on text area
		wordList = new JList();
		MyMouseListener mouse = new MyMouseListener();
		wordList.addMouseListener(mouse);
		scrollText = new JScrollPane(wordList);
		this.add(scrollText, BorderLayout.CENTER);
	
	}
	
	public static void main(String[] args) 
	{
		Frame wordFrame = new Frame();
		wordFrame.setVisible(true);

	}

	//Method for opening the wordlist
	private class OpenButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			JFileChooser opener = new JFileChooser();
			int returnVal = opener.showOpenDialog(getParent());
			if(returnVal == opener.APPROVE_OPTION) {
				File file = opener.getSelectedFile();
				try {
					Scanner scanner = new Scanner(file);
					wordManager.add(scanner);
					scanner.close();
					
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			} else {
				System.out.println("File selection canceled.");
			}
			
		}
	}
	
	private class MyDocumentListener implements DocumentListener
	{
		
		@Override
		public void changedUpdate(DocumentEvent arg0) {
			//not needed for plain text			
		}

		//puts data from text field into a string
		//creates array list of words using the Wordmanager class provided
		//JList then has the data set appropriately using toArray since JList.setData
		//requires an Object[] parameter.
		@Override
		public void insertUpdate(DocumentEvent arg0) {
			String typed = entryField.getText();
			wordsTyped = wordManager.getWords(typed);
			wordList.setListData(wordsTyped.toArray());
			
		}

		//same as previous method
		@Override
		public void removeUpdate(DocumentEvent arg0) {
			String typed = entryField.getText();
			wordsTyped = wordManager.getWords(typed);
			wordList.setListData(wordsTyped.toArray());
			
		}
		
	}
	
	private class MyMouseListener implements MouseListener 
	{

		//Only need method for a single click so others left blank
		@Override
		public void mouseClicked(MouseEvent arg0) {
			//integer is current position clicked on word list which is then used to set
			//the text fields word appropriately
			int position = wordList.locationToIndex(arg0.getPoint());
			entryField.setText(wordsTyped.get(position));
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}

}
