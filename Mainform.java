import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

public class Mainform {
	
	protected Shell shell;
	public static List<BMI> bmiList = new ArrayList<BMI>();
	private final static String FILENAME = "bmi.ser";
	private File file;
	public static int option = 2;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Mainform window = new Mainform();
			window.open();
			try
	        {
	            FileOutputStream outFile = new FileOutputStream(FILENAME, false); 
				ObjectOutputStream outStream = new ObjectOutputStream(outFile); 	              	               
	            outStream.writeObject(bmiList);	          
	            outStream.close(); 
	            outFile.close();
	        } 
	        catch(IOException ex) 
	        { 
	            System.out.println("Error writing BMI to file" + ex.getMessage()); 
	        }
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	
	private void doShowMessageBox(Event event) {
		// TODO Auto-generated method stub
		 int style = SWT.APPLICATION_MODAL | SWT.ICON_QUESTION | SWT.YES
	                | SWT.NO;
	        MessageBox messageBox = new MessageBox(shell, style);
	        messageBox.setText("Information");
	        messageBox.setMessage("Really close application?");
	        event.doit = messageBox.open() == SWT.YES;
	}
	

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(482, 332);
		shell.setText("BMI Calculator");
		
		//new patient button
		Button btnNewPatient = new Button(shell, SWT.NONE);
		btnNewPatient.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				NewPatientForm npf = new NewPatientForm(shell,0);
				npf.open();
			}
		});
		btnNewPatient.setBounds(42, 76, 135, 35);
		btnNewPatient.setText("New Patient");
		
		//change patient button
		Button btnChangePatient = new Button(shell, SWT.NONE);
		btnChangePatient.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ChangePatientForm cpf = new ChangePatientForm(shell,0);
				cpf.open();
			}
		});
		btnChangePatient.setBounds(42, 151, 135, 35);
		btnChangePatient.setText("Change Patient");
		
		//delete button
		Button btnDeletePatient = new Button(shell, SWT.NONE);
		btnDeletePatient.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DeletePatientForm dpf = new DeletePatientForm(shell,0);
				dpf.open();
			}
		});
		btnDeletePatient.setBounds(251, 151, 161, 35);
		btnDeletePatient.setText("Delete Patient");
		
		//display all button
		Button btnDisplayAllPatients = new Button(shell, SWT.NONE);
		btnDisplayAllPatients.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DisplayAllForm da = new DisplayAllForm(shell, 0);
				da.open();
			}
		});
		btnDisplayAllPatients.setBounds(251, 76, 161, 35);
		btnDisplayAllPatients.setText("Display All Patients");
		
		//exit button
		Button btnExit = new Button(shell, SWT.NONE);
		btnExit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.close();
			}
		});
		btnExit.setBounds(162, 212, 105, 35);
		btnExit.setText("Exit");
		
		Label lblWelcome = new Label(shell, SWT.NONE);
		lblWelcome.setFont(SWTResourceManager.getFont("Franklin Gothic Demi", 20, SWT.NORMAL));
		lblWelcome.setBounds(129, 10, 189, 47);
		lblWelcome.setText("Welcome!");
		shell.setTabList(new Control[]{btnNewPatient, btnDisplayAllPatients, btnChangePatient, btnDeletePatient, btnExit});
		try
        {
			file = new File(FILENAME);
			if(file.exists()) {
				FileInputStream inFile = new FileInputStream(FILENAME);
				ObjectInputStream inStream = new ObjectInputStream(inFile);            
	            bmiList = (List<BMI>)inStream.readObject();            
	            inStream.close(); 
	            inFile.close();  
			}
        } 
        catch(IOException ex) 
        { 
            System.out.println("Error reading Song file"); 
        } 
		catch(ClassNotFoundException ex) 
        { 
            
        }
	}
}
