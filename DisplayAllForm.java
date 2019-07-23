import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Control;


public class DisplayAllForm extends Dialog{

	protected Object result;
	protected Shell shlDisplayAll;
	private Text txtData;

	/**
	 * Creates the dialog.
	 * @param parent
	 * @param style
	 */
	
	public DisplayAllForm(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	
	public Object open() {
		createContents();
		shlDisplayAll.open();
		shlDisplayAll.layout();
		Display display = getParent().getDisplay();
		while (!shlDisplayAll.isDisposed()) {
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
		shlDisplayAll = new Shell();
		shlDisplayAll.setSize(701, 496);
		shlDisplayAll.setText("Display All Patients");
				
		String str = "";
		for(BMI b : Mainform.bmiList) {
        	str += b.toString() + "\n";
		}
		
		Label label = new Label(shlDisplayAll, SWT.NONE);
		label.setBounds(41, 71, 617, 307);
		
		label.setText(str);
		
		Button btnReturn = new Button(shlDisplayAll, SWT.NONE);
		btnReturn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlDisplayAll.close();
			}
		});
		btnReturn.setText("Return");
		btnReturn.setBounds(563, 395, 105, 35);
		
		Label lblName = new Label(shlDisplayAll, SWT.NONE);
		lblName.setBounds(110, 30, 56, 25);
		lblName.setText("Name: ");
		
		Label lblHeight = new Label(shlDisplayAll, SWT.NONE);
		lblHeight.setBounds(188, 30, 66, 25);
		lblHeight.setText("Height:");
		
		Label lblWeight = new Label(shlDisplayAll, SWT.NONE);
		lblWeight.setBounds(264, 30, 81, 25);
		lblWeight.setText("Weight:");
		
		Label lblType = new Label(shlDisplayAll, SWT.NONE);
		lblType.setBounds(363, 30, 56, 25);
		lblType.setText("Type: ");
		
		Label lblBmi = new Label(shlDisplayAll, SWT.NONE);
		lblBmi.setBounds(444, 30, 39, 25);
		lblBmi.setText("BMI:");
		
		Label lblStatus = new Label(shlDisplayAll, SWT.NONE);
		lblStatus.setBounds(539, 30, 81, 25);
		lblStatus.setText("Status: ");
		
		Label lblNumber = new Label(shlDisplayAll, SWT.NONE);
		lblNumber.setBounds(20, 30, 81, 25);
		lblNumber.setText("Number: ");
		shlDisplayAll.setTabList(new Control[]{btnReturn});

	}
}
