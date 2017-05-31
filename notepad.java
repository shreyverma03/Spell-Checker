import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import java.util.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

class PopUpDemo extends JPopupMenu implements ActionListener
{
    JMenuItem anItem;
    JMenuItem anItem2;
    JMenuItem anItem3;
    JMenuItem anItem4;
    JMenuItem anItem5;
    String command=" ", s1;
    JTextArea tett;
    public PopUpDemo(){}
    public PopUpDemo(String s, JTextArea tttt)
    {
    	this.s1=s;
    	tett=tttt;
    	String []a=new String[5];
      	try
       	{
       		int k=0;
        	FileInputStream fis = new FileInputStream("dict.txt");
        	Scanner scanner = new Scanner(fis);
          	while(scanner.hasNextLine())
            {
            	String l=new String();
            	l=scanner.nextLine();
            	if(l.charAt(0)==s.charAt(0))
            	{
            		a[k]=l;
            		k++;
            		if(k==5)
            			break;
            	}
            } 
            anItem = new JMenuItem(a[0]);
        	add(anItem);
        	anItem2 = new JMenuItem(a[1]);
        	add(anItem2);
        	anItem3 = new JMenuItem(a[2]);
        	add(anItem3);
        	anItem4 = new JMenuItem(a[3]);
        	add(anItem4);
        	anItem5 = new JMenuItem(a[4]);
        	add(anItem5);
         	anItem.addActionListener(this);
			anItem2.addActionListener(this);
			anItem3.addActionListener(this);
			anItem4.addActionListener(this);
			anItem5.addActionListener(this);
        }
        catch(IOException e)
        {}
    }
	public void actionPerformed(ActionEvent ae)
	{
		command=(String)ae.getActionCommand();
		int i=tett.getText().indexOf(s1);
		tett.replaceRange(command,i,i+s1.length());
		System.out.println(command);
	}
}

public class notepad extends JFrame implements ActionListener
{
	MenuBar mbar;
	Menu file,edit,format,font,font1,font2;
	MenuItem item1,item2,item3,item4;
	MenuItem item5,item6,item7,item8,item9,item10, item11;
	MenuItem fname1,fname2,fname3;
	MenuItem fstyle1,fstyle2,fstyle3,fstyle4;
	MenuItem fsize1,fsize2,fsize3,fsize4;

	JPanel mainpanel;
	JTextArea text;

	Font f;
	String months[]={
		"Jan","Feb","Mar","Apr",
		"May","Jun","Jul","Aug",
		"Sep","Oct","Nov","Dec"};

	GregorianCalendar  gcalendar;


	String command=" ";
	String str=" ";

	String str1=" ",str2=" ",str3=" ";
	String str4=" ";

	String str6=" ";
	String str7=" ",str8=" ",str9=" ";

	int len1;

	int i=0;
	int pos1;
	int len;

	public notepad(String str)
	{

		super(str);

		mainpanel=new JPanel();
		mainpanel=(JPanel)getContentPane();
		mainpanel.setLayout(new FlowLayout());

        

		mbar=new MenuBar();
		setMenuBar(mbar);
	
		file=new Menu("File");
		edit=new Menu("Edit");
		format=new Menu(" ");
		font=new Menu("Font");
		font1=new Menu("Font Style");
		font2=new Menu("Size");

		

		file.add(item1=new MenuItem("New"));
		file.addSeparator();
		file.add(item2=new MenuItem("Open"));
		file.add(item3=new MenuItem("Save As"));
		file.addSeparator();
		file.add(item4=new MenuItem("Exit"));
	//	MenuShortcut exitShortCut= new MenuShortcut(KeyEvent.VK_X);
	//	MenuItem exitItem=new MenuItem("Exit",exitShortCut);
		mbar.add(file);

		
		
		edit.add(item5=new MenuItem("Cut"));
		edit.add(item6=new MenuItem("Copy"));
		edit.add(item7=new MenuItem("Paste"));
		edit.addSeparator();
		edit.add(item8=new MenuItem("Delete"));
		edit.add(item10=new MenuItem("Select All"));
		edit.addSeparator();
		edit.add(item9=new MenuItem("Time/Date"));
		edit.addSeparator();
		edit.add(item11=new MenuItem("Add to Dictionary"));
		mbar.add(edit);

		format.add(font);
		format.add(font1);
		format.add(font2);

		font.add(fname1=new MenuItem("Courier"));
		font.add(fname2=new MenuItem("Sans Serif"));
		font.add(fname3=new MenuItem("Monospaced"));

		font1.add(fstyle1=new MenuItem("Regular"));
		font1.add(fstyle2=new MenuItem("Bold"));
		font1.add(fstyle3=new MenuItem("Italic"));
		font1.add(fstyle4=new MenuItem("Bold Italic"));

		font2.add(fsize1=new MenuItem("12"));
		font2.add(fsize2=new MenuItem("14"));
		font2.add(fsize3=new MenuItem("18"));
		font2.add(fsize4=new MenuItem("20"));

		mbar.add(format);


	
		item1.addActionListener(this);
		item2.addActionListener(this);
		item3.addActionListener(this);
		item4.addActionListener(this);
		item5.addActionListener(this);
		item6.addActionListener(this);
		item7.addActionListener(this);
		item8.addActionListener(this);
		item9.addActionListener(this);
		item10.addActionListener(this);
		item11.addActionListener(this);
		fname1.addActionListener(this);
		fname2.addActionListener(this);
		fname3.addActionListener(this);
		fstyle1.addActionListener(this);
		fstyle2.addActionListener(this);
		fstyle3.addActionListener(this);
		fstyle4.addActionListener(this);
		fsize1.addActionListener(this);
		fsize2.addActionListener(this);
		fsize3.addActionListener(this);

    	DocumentListener documentListener = new DocumentListener() 
    	{
      		public void changedUpdate(DocumentEvent documentEvent)
      		{
        		//checkIt(documentEvent);
      		}
      		public void insertUpdate(DocumentEvent documentEvent) 
      		{
        		checkIt(documentEvent);
      		}
      		public void removeUpdate(DocumentEvent documentEvent) 
      		{
        		checkIt(documentEvent);
      		}
      		private void checkIt(DocumentEvent documentEvent) 
      		{
      				char []fulText=text.getText().toCharArray();
      				//System.out.println(fulText);
      				int pos=text.getCaretPosition();
      				if(pos>text.getText().length())
      					pos=text.getText().length()-1;
      				if(pos==-1||pos==0)
      				{
      					pos=0;
      					return;
      				}
      				//System.out.println(pos);
      				int l;
      				for(l=pos-1;l>=0;l--)
      				{
      					if(fulText[l]==' '||fulText[i]=='.')
      						break;
      				}
      				int k;
      				if(fulText[pos]!=' '||fulText[i]=='.')
      				{
      					for(k=pos;k<text.getText().length();k++)
      					{
      						if(fulText[k]==' ')
      							break;
      					}
      				}
      				else k=pos-1;
      				if(pos>text.getText().length())
      					pos=text.getText().length()-1;
      				if(l<0)
      					l=0;
      				if(k>=text.getText().length())
      					k=text.getText().length()-1;

      				if(fulText[l]==' ')
      					l++;
      				String checkS=text.getText().substring(l, k+1);
      				checkS.trim();
        		try
       			{
        			boolean isThere=false;
        			FileInputStream fis = new FileInputStream("dict.txt");
        			Scanner scanner = new Scanner(fis);
            		while(scanner.hasNextLine())
            		{
            			if(checkS.compareTo(scanner.nextLine())==0)
      					{
      						isThere=true;
      							break;
      					}
            		} 
        			scanner.close();
                 	if(isThere==false)
      				{
                    	String fontName=f.getName();
						int fontSize=f.getSize();
						f=new Font(fontName,Font.BOLD,fontSize);
						text.setFont(f);
						//System.out.println("worng");
      				}
      				else
      				{
                   		String fontName=f.getName();
						int fontSize=f.getSize();
						f=new Font(fontName,Font.PLAIN,fontSize);
						text.setFont(f);
						//System.out.println("not worng");
      				}
      			}	
               	catch(IOException e)
				{	}
    		}
    	};

		text=new JTextArea(70,70);
		mainpanel.add(text);
   		MouseAdapter ma=new MouseAdapter()
    	{
      		public void mousePressed(MouseEvent e)
      		{
        		if (e.getButton() == MouseEvent.BUTTON1) // BUTTON3 is the right mouse button
        		{

        			if(e.getClickCount()==2)
        			{
                        String str1=text.getSelectedText();
        				PopUpDemo menu = new PopUpDemo(str1, text);
        				menu.show(e.getComponent(), e.getX(), e.getY());
        				System.out.println("right button pressed");
        			}
        		}
      		}
    	};
		text.addMouseListener(ma);

		f=new Font("Monotype Corsiva",Font.PLAIN,15);
		text.setFont(f);
		text.getDocument().addDocumentListener(documentListener);
	}
	public void actionPerformed(ActionEvent ae)
	{

		command=(String)ae.getActionCommand();

		if(command.equals("New"))
		{
			dispose();
			notepad note1 = new notepad("Untitled-");
			note1.setSize(500,500);
			note1.setVisible(true);
		}

		try
		{

			if(command.equals("Open"))
			{

				str4=" ";
				FileDialog dialog=new FileDialog(this,"Open");
				dialog.setVisible(true);
				str1=dialog.getDirectory();
				str2=dialog.getFile();
				str3=str1+str2;
				File f=new File(str3);
				FileInputStream fobj=new FileInputStream(f);
				len=(int)f.length();
				for(int j=0;j<len;j++)
				{
					char str5=(char)fobj.read();
					str4=str4 + str5;

				}

				text.setText(str4);
	
			}
		}
		catch(IOException e)
		{
		
		}
		try
		{
			if(command.equals("Add to Dictionary"))
			{
				String str111=text.getSelectedText();
				FileWriter fw = new FileWriter("dict.txt", true);
				fw.write(str111);
				fw.close();
				String fontName=f.getName();
				int fontSize=f.getSize();
				f=new Font(fontName,Font.PLAIN,fontSize);
				text.setFont(f);
			}
		}
		catch(IOException e)
		{
		
		}
		try
		{

			if(command.equals("Save As"))
			{
				FileDialog dialog1=new FileDialog(this,"Save As",FileDialog.SAVE);
				dialog1.setVisible(true);

				str7=dialog1.getDirectory();
				str8=dialog1.getFile();
				str9=str7+str8;


				str6=text.getText();
				len1=str6.length();
				byte buf[]=str6.getBytes();

				File f1=new File(str9);
				FileOutputStream fobj1=new FileOutputStream(f1);
				for(int k=0;k<len1;k++)
				{
					fobj1.write(buf[k]);
				}
				fobj1.close();
			}

			this.setTitle(str8);

		}
		catch(IOException e)
		{
		
		}

		if(command.equals("Exit"))
		{
			System.exit(0);
		}

		if(command.equals("Cut"))
		{
			str=text.getSelectedText();
			i=text.getText().indexOf(str);
			text.replaceRange(" ",i,i+str.length());
		}

		if(command.equals("Copy"))
		{
			str=text.getSelectedText();
		}

		if(command.equals("Paste"))
		{
			pos1=text.getCaretPosition();
			text.insert(str,pos1);
		}
		if(command.equals("Delete"))
		{
			String msg=text.getSelectedText();
			i=text.getText().indexOf(msg);
			text.replaceRange(" ",i,i+msg.length());
		}
		if(command.equals("Time/Date"))
		{
			gcalendar=new GregorianCalendar();
			String h=String.valueOf(gcalendar.get(Calendar.HOUR));
			String m=String.valueOf(gcalendar.get(Calendar.MINUTE));
			String s=String.valueOf(gcalendar.get(Calendar.SECOND));
			String date=String.valueOf(gcalendar.get(Calendar.DATE));
			String mon=months[gcalendar.get(Calendar.MONTH)];
			String year=String.valueOf(gcalendar.get(Calendar.YEAR));
			String hms="Time"+" - "+h+":"+m+":"+s+"  Date"+"  -  "+date+""+mon+""+year;
			int loc=text.getCaretPosition();
			text.insert(hms,loc);
		}
		if(command.equals("Courier"))
		{
			int fontSize=f.getSize();
			int fontStyle=f.getStyle();

			f=new Font("Courier",fontStyle,fontSize);
			text.setFont(f);
		}
		if(command.equals("Sans Serif"))
		{
			int fontSize=f.getSize();
			int fontStyle=f.getStyle();

			f=new Font("Sans Serif",fontStyle,fontSize);
			text.setFont(f);
		}

		if(command.equals("Monospaced"))
		{
			int fontSize=f.getSize();
			int fontStyle=f.getStyle();

			f=new Font("Monospaced",fontStyle,fontSize);
			text.setFont(f);
		}
		if(command.equals("Regular"))
		{
			String fontName=f.getName();
			int fontSize=f.getSize();
	
			f=new Font(fontName,Font.PLAIN,fontSize);
			text.setFont(f);
		}
		if(command.equals("Bold"))
		{
			String fontName=f.getName();
			int fontSize=f.getSize();

			f=new Font(fontName,Font.BOLD,fontSize);
			text.setFont(f);
		}
		if(command.equals("Italic"))
		{
			String fontName=f.getName();
			int fontSize=f.getSize();

			f=new Font(fontName,Font.ITALIC,fontSize);
			text.setFont(f);
		}
		if(command.equals("Bold Italic"))
		{
			String fontName=f.getName();
			int fontSize=f.getSize();

			f=new Font(fontName,Font.BOLD|Font.ITALIC,fontSize);
			text.setFont(f);
		}

		if(command.equals("12"))
		{
			String fontName=f.getName();
			int fontStyle=f.getStyle();

			f=new Font(fontName,fontStyle,12);
			text.setFont(f);
		}

		if(command.equals("14"))
		{
			String fontName=f.getName();
			int fontStyle=f.getStyle();
	
			f=new Font(fontName,fontStyle,14);
			text.setFont(f);
		}
		if(command.equals("18"))
		{
			String fontName=f.getName();
			int fontStyle=f.getStyle();

			f=new Font(fontName,fontStyle,18);
			text.setFont(f);
		}
		if(command.equals("20"))
		{
			String fontName=f.getName();
			int fontStyle=f.getStyle();

			f=new Font(fontName,fontStyle,20);
			text.setFont(f);
		}
		if(command.equals("Select All"))
		{
			String strText=text.getText();
			int strLen=strText.length();
			text.select(0,strLen);
		}


	}
	
    

	public static void main(String args[])
	{
		notepad note = new notepad("Untitled-Notepad");
		note.setSize(1000,1500);
		note.setVisible(true);
	}
}

			                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     