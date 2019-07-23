import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Control;

public class NewPatientForm extends Dialog{

	protected Object result;
	protected Shell shlNew;
	private Text txtPatNum;
	private Text txtPatName;
	private Text txtHeight;
	private Text txtWeight;
	private Button btnSubmit;

	/**
	 * Launch the application.
	 * @param args
	 */
	public NewPatientForm(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlNew.open();
		shlNew.layout();
		Display display = getParent().getDisplay();
		while (!shlNew.isDisposed()) {
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
		shlNew = new Shell();
		shlNew.setSize(450, 300);
		shlNew.setText("New Patient");
		
		Button btnReturn = new Button(shlNew, SWT.NONE);
		btnReturn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlNew.close();
			}
		});
		btnReturn.setBounds(313, 199, 105, 35);
		btnReturn.setText("Return");
		
		txtPatNum = new Text(shlNew, SWT.BORDER);
		txtPatNum.setBounds(195, 31, 131, 31);
		
		txtPatName = new Text(shlNew, SWT.BORDER);
		txtPatName.setBounds(195, 68, 131, 31);
		
		Label lblPatientNumber = new Label(shlNew, SWT.NONE);
		lblPatientNumber.setBounds(58, 34, 131, 25);
		lblPatientNumber.setText("Patient Number:");
		
		Label lblPatientName = new Label(shlNew, SWT.NONE);
		lblPatientName.setBounds(70, 71, 119, 25);
		lblPatientName.setText("Patient Name: ");
		
		Label lblHeight = new Label(shlNew, SWT.NONE);
		lblHeight.setBounds(126, 108, 62, 25);
		lblHeight.setText("Height: ");
		
		txtHeight = new Text(shlNew, SWT.BORDER);
		txtHeight.setBounds(195, 105, 131, 31);
		
		txtWeight = new Text(shlNew, SWT.BORDER);
		txtWeight.setBounds(195, 148, 131, 31);
		
		Label lblWeight = new Label(shlNew, SWT.NONE);
		lblWeight.setBounds(123, 151, 65, 25);
		lblWeight.setText("Weight: ");
		
		btnSubmit = new Button(shlNew, SWT.NONE);
		btnSubmit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					Mainform.bmiList.add(new BMI(Integer.parseInt(txtPatNum.getText()),
							txtPatName.getText().toLowerCase(), 
							Integer.parseInt(txtWeight.getText()), 
							Integer.parseInt(txtHeight.getText()), Mainform.option));
				}
				catch(Exception e1){
					MessageBox messageBox = new MessageBox(shlNew, 0);
				    messageBox.setText("Entry Error ");
				    messageBox.setMessage("Invalid Entry ");
				    messageBox.open();
				}
			}
		});
		btnSubmit.setBounds(10, 199, 105, 35);
		btnSubmit.setText("Submit");
		shlNew.setTabList(new Control[]{txtPatNum, txtPatName, txtHeight, txtWeight, btnSubmit, btnReturn});

	}
}
