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

public class ChangePatientForm extends Dialog{

	protected Object result;
	protected Shell shlChange;
	private Text txtPatNum;
	private Text txtPatName;
	private Text txtHeight;
	private Text txtWeight;

	/**
	 * Launch the application.
	 * @param parent
	 * @param style
	 */
	
	public ChangePatientForm(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */

	public Object open() {
		createContents();
		shlChange.open();
		shlChange.layout();
		Display display = getParent().getDisplay();
		while (!shlChange.isDisposed()) {
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
		shlChange = new Shell();
		shlChange.setSize(580, 227);
		shlChange.setText("Change Patient");
		
		Button btnSearch = new Button(shlChange, SWT.NONE);
		btnSearch.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean valid = false;
				BMI var = new BMI();
				try {
					String number = txtPatNum.getText();  // user entered 
					int num = Integer.parseInt(number);	  // change string to integer
					
					for(int i = 0; i < Mainform.bmiList.size(); i++) {
						var = Mainform.bmiList.get(i);
						if(var.getNumber() == (num)){ 	// if bmi list number equals number user entered	
							
							int weight = Mainform.bmiList.get(i).getWeight();
							txtWeight.setText(Integer.toString(weight));	
							
							int height = (Mainform.bmiList).get(i).getHeight();
							txtHeight.setText(Integer.toString(height));
							
							String name = Mainform.bmiList.get(i).getName();
							txtPatName.setText(name);
							
							valid = true;
							break;
						}
					}
					if (valid == false) {
						MessageBox messageBox = new MessageBox(shlChange, 0);
					    messageBox.setText("Patient number Error");
					    messageBox.setMessage("Patient number not found");
					    messageBox.open();
					}
				}
				catch(Exception exc) {
					MessageBox messageBox = new MessageBox(shlChange, 0);
				    messageBox.setText("Patient number Error");
				    messageBox.setMessage("Patient number not found");
				    messageBox.open();
				}
			}
		});
		btnSearch.setBounds(10, 115, 217, 35);
		btnSearch.setText("Search by Patient Number");
		
		Button btnSubmit = new Button(shlChange, SWT.NONE);
		btnSubmit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					for(int i = 0; i < Mainform.bmiList.size(); i++) {
						BMI var = Mainform.bmiList.get(i);					
						String number = txtPatNum.getText();
						int num = Integer.parseInt(number);	
						
						if(var.getNumber() == (num)){							
							Mainform.bmiList.get(i).setNumber(num);
							
							String weight = txtWeight.getText();
							int w = Integer.parseInt(weight);	
							Mainform.bmiList.get(i).setWeight(w);
							
							String height = txtHeight.getText();
							int h = Integer.parseInt(height);	
							Mainform.bmiList.get(i).setHeight(h);
							
							String name = txtPatName.getText();
							Mainform.bmiList.get(i).setName(name);
							break;
						}
					}
				}
				catch (Exception exc) {
					MessageBox messageBox = new MessageBox(shlChange, 0);
				    messageBox.setText("Error");
				    messageBox.setMessage("Error, Patient not changed. ");
				    messageBox.open();
				}
			}
		});
		btnSubmit.setBounds(274, 115, 105, 35);
		btnSubmit.setText("Submit");
		
		Button btnReturn = new Button(shlChange, SWT.NONE);
		btnReturn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlChange.close();
			}
		});
		btnReturn.setBounds(425, 115, 105, 35);
		btnReturn.setText("Return");
		
		txtPatNum = new Text(shlChange, SWT.BORDER);
		txtPatNum.setBounds(161, 16, 131, 31);
		
		Label lblPatNum = new Label(shlChange, SWT.NONE);
		lblPatNum.setText("Patient Number:");
		lblPatNum.setBounds(24, 19, 131, 25);
		
		Label lblPatName = new Label(shlChange, SWT.NONE);
		lblPatName.setText("Patient Name: ");
		lblPatName.setBounds(37, 56, 118, 25);
		
		txtPatName = new Text(shlChange, SWT.BORDER);
		txtPatName.setBounds(161, 53, 131, 31);
		
		Label lblHeight = new Label(shlChange, SWT.NONE);
		lblHeight.setText("Height: ");
		lblHeight.setBounds(314, 16, 65, 25);
		
		txtHeight = new Text(shlChange, SWT.BORDER);
		txtHeight.setBounds(385, 13, 131, 31);
		
		txtWeight = new Text(shlChange, SWT.BORDER);
		txtWeight.setBounds(385, 53, 131, 31);
		
		Label lblWeight = new Label(shlChange, SWT.NONE);
		lblWeight.setText("Weight: ");
		lblWeight.setBounds(314, 56, 65, 25);
		shlChange.setTabList(new Control[]{txtPatNum, txtPatName, txtHeight, txtWeight, btnSearch, btnSubmit, btnReturn});
		
		

	}

}
