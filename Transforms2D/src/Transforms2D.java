import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Transforms2D extends JPanel {

	private class Display extends JPanel {
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D)g;
			g2.translate(300,300);
			int whichTransform = transformSelect.getSelectedIndex();
			
			int n = 7;
			int R = 150;
			int[] xPoints = new int[n];
			int[] yPoints = new int[n];
			for (int i= 0;i < n;i++)
			{
			double angle = 2 * Math.PI / n * i;
			xPoints[i] = (int)(R*Math.cos(angle));
			yPoints[i] = (int)(R*Math.sin(angle));
			}

			switch(whichTransform) {
			case 1: 
				g2.scale(1.0/2, 1.0/2);
				break;
				case 2: 
					g2.rotate(Math.PI / 4);
					break;
				case 3: 
					g2.scale(-1.0 / 2, 1.0);
					g2.rotate(Math.PI);
					break;
				case 4: 
					g2.shear(0.25, 0);
					break;
				case 5: 
					g2.scale(1.0, 1.0 / 2);
					g2.translate(0, -450);
					break;
				case 6: 
					g2.shear(0, -0.5);
					g2.rotate(Math.PI / 2);
					break;
				case 7:
					g2.scale(-1.0 / 2, -1.0);
					break;
				case 8:
					g2.rotate(Math.PI / 6);
					g2.scale(1.0, 1.0 / 2);
					g2.translate(0, 250);
					break;
				case 9:
					g2.scale(-1.0, -1.0);
					g2.translate(-160, 0);
					g2.shear(0, 0.4);
					break;
			}
			g2.drawPolygon(xPoints, yPoints, n);		
		}
	}

	private Display display;
	private BufferedImage pic;
	private JComboBox<String> transformSelect;

	public Transforms2D() throws IOException {
		pic = ImageIO.read(getClass().getClassLoader().getResource("shuttle.jpg"));
		display = new Display();
		display.setBackground(Color.YELLOW);
		display.setPreferredSize(new Dimension(600,600));
		transformSelect = new JComboBox<String>();
		transformSelect.addItem("None");
		for (int i = 1; i < 10; i++) {
			transformSelect.addItem("No. " + i);
		}
		transformSelect.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				display.repaint();
			}
		});
		setLayout(new BorderLayout(3,3));
		setBackground(Color.GRAY);
		setBorder(BorderFactory.createLineBorder(Color.GRAY,10));
		JPanel top = new JPanel();
		top.setLayout(new FlowLayout(FlowLayout.CENTER));
		top.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
		top.add(new JLabel("Transform: "));
		top.add(transformSelect);
		add(display,BorderLayout.CENTER);
		add(top,BorderLayout.NORTH);
	}


	public static void main(String[] args) throws IOException {
		JFrame window = new JFrame("2D Transforms");
		window.setContentPane(new Transforms2D());
		window.pack();
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		window.setLocation( (screen.width - window.getWidth())/2, (screen.height - window.getHeight())/2 );
		window.setVisible(true);
	}

}