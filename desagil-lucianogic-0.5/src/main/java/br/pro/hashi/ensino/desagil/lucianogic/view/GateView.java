package br.pro.hashi.ensino.desagil.lucianogic.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;

import br.pro.hashi.ensino.desagil.lucianogic.model.Gate;
import br.pro.hashi.ensino.desagil.lucianogic.model.Switch;


public class GateView extends FixedPanel implements ItemListener, MouseListener {

	// Necessario para serializar objetos desta classe.
	private static final long serialVersionUID = 1L;


	private Image image;

	private JCheckBox[] inBoxes;
	private JCheckBox outBox;

	private Switch[] switches;
	private Gate gate;
	public int x, y,x1,y1;
	public boolean alavanca=false;


	public GateView(Gate gate) {
		super(205, 180);

		this.gate = gate;

		image = loadImage(gate.toString());

		int size = gate.getSize();

		inBoxes = new JCheckBox[size];

		switches = new Switch[size];

		for(int i = 0; i < size; i++) {
			inBoxes[i] = new JCheckBox();

			inBoxes[i].addItemListener(this);

			switches[i] = new Switch();

			gate.connect(switches[i], i);
		}

		outBox = new JCheckBox();

		outBox.setEnabled(false);

		if(size == 1) {
			add(inBoxes[0], 0, 60, 20, 20);			
		}
		else {
			for(int i = 0; i < size; i++) {
				add(inBoxes[i], 0, (i + 1) * 40, 20, 20);			
			}			
		}

		add(outBox, 184, 60, 20, 20);

		outBox.setSelected(gate.read());
	}


	@Override
	public void itemStateChanged(ItemEvent event) {
		int i;
		for(i = 0; i < inBoxes.length; i++) {
			if(inBoxes[i] == event.getSource()) {
				break;
			}
		}

		switches[i].setOn(inBoxes[i].isSelected());

		outBox.setSelected(gate.read());
	}


	// Necessario para carregar os arquivos de imagem.
	private Image loadImage(String filename) {
		URL url = getClass().getResource("/img/" + filename + ".png");
		ImageIcon icon = new ImageIcon(url);
		return icon.getImage();
	}


	@Override
	public void paintComponent(Graphics g) {
		// Evita bugs visuais em alguns sistemas operacionais.
		super.paintComponent(g);

		g.drawImage(image, 10, 20, 184, 140, null);
		
		Graphics2D g2 = (Graphics2D) g;
		x=10;
		y=10;
		int x2=5;
		int y2=5;
		int rectwidth =10;
		int rectheight=10;
//		Ellipse2D oval = new Ellipse2D.Double(x, y, rectwidth, rectheight);
		g2.draw(new Ellipse2D.Double(x, y,
                rectwidth,
                rectheight));
		if (alavanca == true){
		g2.draw(new Line2D.Double(x, y, x2, y2));
		}else{
			g2.draw(new Line2D.Double(x, y, (-x2), (-y2)));
			alavanca=false;
		}
		

		// Evita bugs visuais em alguns sistemas operacionais.
		getToolkit().sync();
    }


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if ((e.getButton() == 1) && e.getX() == x && e.getY()==y ) {
			System.out.println("opa");
		      repaint();
		    // JOptionPane.showMessageDialog(null,e.getX()+ "\n" + e.getY());
		   }
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if ((e.getButton() == 1) && e.getX() == x && e.getY()==y ) {
			System.out.println("opa");
		      repaint();
		    // JOptionPane.showMessageDialog(null,e.getX()+ "\n" + e.getY());
		   }
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
