import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import net.proteanit.sql.DbUtils;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
public class javaCrud {

	private JFrame frame;
	private JTextField txtedition;
	private JTextField txtbname;
	private JTextField txtprice;
	private JPanel panel_1;
	private JPanel panel_2;
	private JLabel lblNewLabel_1_2;
	private JTextField txtbid;
	private JButton btnUpdate;
	private JButton btnNewButton_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					javaCrud window = new javaCrud();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public javaCrud() {
		initialize();
		initialize();
        Connect();
        table_load();
	}
	  Connection con;
	  PreparedStatement pst;
	  ResultSet rs;
	  private JTable table;
	
	
	
	  public void Connect()
	  {
	      try {
	    	  //use Class.forName() on the class that implements the java.sql.Driver interface. 
	          Class.forName("com.mysql.jdbc.Driver");
	          con = DriverManager.getConnection("jdbc:mysql://localhost/javacrud", "root","");
	      }
	      catch (ClassNotFoundException ex)
	      {
	          ex.printStackTrace();
	      }
	      catch (SQLException ex)
	      {
	          ex.printStackTrace();
	      }
	                    
	  }
	  public void table_load(){
	    	try{
			    pst = con.prepareStatement("select * from book");
			    rs = pst.executeQuery();
			    table.setModel(DbUtils.resultSetToTableModel(rs));//DbUtils library is a great utility library designed 
			    //to simplify JDBC and reducing the problems of resource leak and to have cleaner code.
		    } 
	    	catch (SQLException e) 
	    	 {
	    		e.printStackTrace();
		  } 
	    }
	  
	  
	  

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 15));
		frame.setBounds(100, 100, 977, 590);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("BOOK SHOP");
		lblNewLabel.setBounds(368, 11, 152, 50);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(26, 93, 458, 203);
		panel.setBorder(new TitledBorder(null, "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("BOOK NAME");
		lblNewLabel_1.setBounds(27, 43, 144, 32);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Edition");
		lblNewLabel_1_1.setBounds(27, 86, 144, 32);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Price");
		lblNewLabel_1_1_1.setBounds(27, 129, 144, 32);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		panel.add(lblNewLabel_1_1_1);
		
		txtedition = new JTextField();
		txtedition.setBounds(148, 91, 288, 29);
		panel.add(txtedition);
		txtedition.setColumns(10);
		
		txtbname = new JTextField();
		txtbname.setColumns(10);
		txtbname.setBounds(148, 46, 288, 29);
		panel.add(txtbname);
		
		txtprice = new JTextField();
		txtprice.setColumns(10);
		txtprice.setBounds(148, 132, 288, 29);
		panel.add(txtprice);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bname,edition,price;
               bname = txtbname.getText();
               edition = txtedition.getText();
               price = txtprice.getText();
               try {
                   pst = con.prepareStatement("insert into book(name,edition,price)values(?,?,?)");
                   pst.setString(1, bname);
                   pst.setString(2, edition);
                   pst.setString(3, price);
                   pst.executeUpdate();
                   JOptionPane.showMessageDialog(null, "Record Addedddd!!!!!");
                   table_load();
                   txtbname.setText("");
                   txtedition.setText("");
                   txtprice.setText("");
                   txtbname.requestFocus();
               }

               catch (SQLException e1)
               {
                   e1.printStackTrace();


               }
				
			}
		});
		btnNewButton.setBounds(28, 324, 103, 50);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 17));
		frame.getContentPane().add(btnNewButton);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtbname.setText("");
                txtedition.setText("");
                txtprice.setText("");
                txtbid.setText("");
                txtbname.requestFocus();
			}
		});
		btnClear.setBounds(203, 324, 103, 50);
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 17));
		frame.getContentPane().add(btnClear);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
			}
		});
		btnExit.setBounds(381, 324, 103, 50);
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 17));
		frame.getContentPane().add(btnExit);
		
		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Search", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		panel_1.setBounds(38, 473, 299, -65);
		frame.getContentPane().add(panel_1);
		
		panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Search", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(26, 396, 458, 79);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		lblNewLabel_1_2 = new JLabel("Book Id");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_1_2.setBounds(10, 24, 117, 32);
		panel_2.add(lblNewLabel_1_2);
		
		txtbid = new JTextField();
		txtbid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				 try {
			          String id = txtbid.getText();
			          		pst = con.prepareStatement("select name,edition,price from book where id = ?");
			                pst.setString(1, id);
			                ResultSet rs = pst.executeQuery();

			            if(rs.next()==true){
			            	String name = rs.getString(1);
			                String edition = rs.getString(2);
			                String price = rs.getString(3);
			                
			                txtbname.setText(name);
			                txtedition.setText(edition);
			                txtprice.setText(price);
			             }   
			            else{
			            	txtbname.setText("");
			            	txtedition.setText("");
			                txtprice.setText("");
			                txtbid.setText("");
			             }
			         } 
				
				 catch (SQLException ex) {
			           
			     }
			}
		});
		txtbid.setColumns(10);
		txtbid.setBounds(110, 24, 288, 29);
		panel_2.add(txtbid);
		
		
		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
				String bname,edition,price,bid;
				bname = txtbname.getText();
				edition = txtedition.getText();
				price = txtprice.getText();
				bid  = txtbid.getText();
				
				 try {
						pst = con.prepareStatement("update book set name= ?,edition=?,price=? where id =?");
						pst.setString(1, bname);
			            pst.setString(2, edition);
			            pst.setString(3, price);
			            pst.setString(4, bid);
			            pst.executeUpdate();
			            JOptionPane.showMessageDialog(null, "Record Update!!!!!");
			            table_load();
			           
			            txtbname.setText("");
			            txtedition.setText("");
			            txtprice.setText("");
			            txtbid.setText("");
			            txtbname.requestFocus();
					}

		            catch (SQLException e1) {
						
						e1.printStackTrace();
					}
	
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnUpdate.setBounds(533, 396, 118, 68);
		frame.getContentPane().add(btnUpdate);//getContentPane() method retrieves the content pane layer
												//so that you can add an object to it. 
		
		btnNewButton_2 = new JButton("Delete");
		btnNewButton_2.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
                String bid;
                bid  = txtbid.getText();
	
			 try {
					pst = con.prepareStatement("delete from book where id =?");
		            pst.setString(1, bid);
		            pst.executeUpdate();
		            JOptionPane.showMessageDialog(null, "Record Delete!!!!!");
		            table_load();
		            txtbname.setText("");
		            txtedition.setText("");
		            txtprice.setText("");
		            txtbid.setText("");
		            txtbname.requestFocus();
				}
		
		        catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnNewButton_2.setBounds(718, 392, 118, 68);
		frame.getContentPane().add(btnNewButton_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(511, 83, 386, 288);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
	}
}
