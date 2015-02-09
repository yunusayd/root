package imageArranger;

import javax.swing.JFrame;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.geom.Dimension2D;
import java.io.File;

public class Main extends JFrame implements ImageCatalogCallBack {
	private JTextField txtSource;
	private JTextField txtTarget;
	private JLabel lblStat;
	private ImageCatalog imageCatalog;
	public Main() {
		setTitle("Image Arranger");
		getContentPane().setLayout(
				new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(153dlu;default):grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(22dlu;default):grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));

		JLabel lblNewLabel = new JLabel("Source");
		getContentPane().add(lblNewLabel, "2, 2, right, default");

		txtSource = new JTextField();
		getContentPane().add(txtSource, "4, 2, fill, default");
		txtSource.setColumns(10);

		JButton btnSource = new JButton("...");
		btnSource.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				startCatalog();
			}
		});
		getContentPane().add(btnSource, "6, 2");

		JLabel lblTarget = new JLabel("Target");
		getContentPane().add(lblTarget, "2, 4, right, default");

		txtTarget = new JTextField();
		txtTarget.setColumns(10);
		getContentPane().add(txtTarget, "4, 4, fill, default");

		JButton btnTarget = new JButton("...");
		btnTarget.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String folder = chooseFolderDialog();
				if(folder != "")
				{
					txtTarget.setText(folder);
				}
			}
		});
		getContentPane().add(btnTarget, "6, 4");
		Container pane = getContentPane(); 
		pane.setSize(new Dimension(800,600));
		
		JButton btnStartGrouping = new JButton("Start Grouping");
		btnStartGrouping.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		getContentPane().add(btnStartGrouping, "4, 6");
		
		lblStat = new JLabel("Stat");
		getContentPane().add(lblStat, "4, 10");
	}

	private void startCatalog()
	{
		String folder = chooseFolderDialog();
		if(folder != "")
		{
			txtSource.setText(folder);
			imageCatalog = new ImageCatalog(folder, this);
			imageCatalog.start();
		}
	}
	private String chooseFolderDialog() {
		JFileChooser chooser = new JFileChooser();

		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			return chooser.getSelectedFile().getAbsolutePath();
		}
		return "";
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main().setVisible(true);

	}

	// CallBack
	public void progress(int fileIx, int totalFile, File file) {
		lblStat.setText(String.format("%d/%d => %s", fileIx, totalFile, file.getName()));
	}

	public void finished() {
		lblStat.setText("Finished! total file: "+imageCatalog.fileCount+", total catalog:"+imageCatalog.catalogList.size());
	}

}
