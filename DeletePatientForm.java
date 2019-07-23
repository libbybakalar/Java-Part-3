import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Control;

public class DeletePatientForm extends Dialog{

	protected Object result;
	protected Shell shlDelete;
	private Text txtPattNum;

	/**
	 * Launch the application.
	 * @param 
	 */
	
	public DeletePatientForm(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlDelete.open();
		shlDelete.layout();
		Display display = getParent().getDisplay();
		while (!shlDelete.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlDelete = new Shell();
		shlDelete.setSize(729, 219);
		shlDelete.setText("Delete Patient");
		
		txtPattNum = new Text(shlDelete, SWT.BORDER);
		txtPattNum.setBounds(320, 10, 153, 31);
		
		Label lblDelete = new Label(shlDelete, SWT.NONE);
		lblDelete.setBounds(38, 58, 628, 35);
		
		Label lblPatientNumber = new Label(shlDelete, SWT.NONE);
		lblPatientNumber.setBounds(176, 13, 138, 25);
		lblPatientNumber.setText("Patient Number: ");
		
		Button btnSearch = new Button(shlDelete, SWT.NONE);
		btnSearch.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {				
				BMI var = new BMI();
				try {
					String number = txtPattNum.getText();
					int num = Integer.parseInt(number);	
					for(int i = 0; i < Mainform.bmiList.size(); i++) {
						var = Mainform.bmiList.get(i);
						if(var.getNumber() == (num)){ 
							break;
						}
					}
					if(var.getNumber() == (num)){ 
						lblDelete.setText(var.toString());
					}
					else {
						MessageBox messageBox = new MessageBox(shlDelete, 0);
					    messageBox.setText("Patient number Error");
					    messageBox.setMessage("Patient number not found");
					    messageBox.open();
					}
				}
				catch(Exception exc) {
					MessageBox messageBox = new MessageBox(shlDelete, 0);
				    messageBox.setText("Patient number Error");
				    messageBox.setMessage("Patient number not found");
				    messageBox.open();
				}
			}
		});
		btnSearch.setBounds(10, 116, 105, 35);
		btnSearch.setText("Search");
		
		Button btnSubmit = new Button(shlDelete, SWT.NONE);
		btnSubmit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				BMI var = new BMI();
				try {
					String number = txtPattNum.getText(); // what user entered 
					int num = Integer.parseInt(number);	 // changed to an integer
					for(int i = 0; i < Mainform.bmiList.size(); i++) {
						var = Mainform.bmiList.get(i);
						if(var.getNumber() == (num)){ 
							Mainform.bmiList.remove(var);
						}
					}
				}
				catch(Exception exc) {
					MessageBox messageBox = new MessageBox(shlDelete, 0);
				    messageBox.setText("Deleting Patient Error");
				    messageBox.setMessage("Error, Patient not deleted. ");
				    messageBox.open();
				}					
			}			
		});
		btnSubmit.setBounds(282, 116, 105, 35);
		btnSubmit.setText("Submit");
		
		Button btnReturn = new Button(shlDelete, SWT.NONE);
		btnReturn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlDelete.close();
			}
		});
		btnReturn.setBounds(592, 116, 105, 35);
		btnReturn.setText("Return");
		shlDelete.setTabList(new Control[]{txtPattNum, btnSearch, btnSubmit, btnReturn});
		
		

	}

}
