package net.marco01809.primesworkbench;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class PrimesMenuBar extends JMenuBar {
	private static final long serialVersionUID = 1L;

	private JMenuItem mntmExportieren;

	public PrimesMenuBar(ActionListener exportActionListener) {
		JMenu mnDatei = new JMenu("Datei");
		add(mnDatei);

		this.mntmExportieren = new JMenuItem("Primzahlen Exportieren...");
		mntmExportieren.setToolTipText("Exportiert die Primzahlen in eine Datei.");
		mntmExportieren.addActionListener(exportActionListener);
		mntmExportieren.setEnabled(false);
		mnDatei.add(mntmExportieren);

		JMenuItem mntmBeenden = new JMenuItem("Beenden");
		mntmBeenden.setToolTipText("Beendet PrimesWorkbench.");
		mntmBeenden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		JSeparator separator = new JSeparator();
		mnDatei.add(separator);
		mnDatei.add(mntmBeenden);

		JMenu mnHilfe = new JMenu("Hilfe");
		add(mnHilfe);

		JMenuItem mntmberPrimesworkbench = new JMenuItem("Über PrimesWorkbench");
		try {
			mntmberPrimesworkbench.setIcon(new ImageIcon(ImageIO.read(PrimesMenuBar.class.getResourceAsStream("/img/logo_96x96.png")).getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
		} catch (IOException e) {
			PrimesApplication.error(e, false);
		} catch (IllegalArgumentException e) {
			PrimesApplication.error(e, false);
		}
		mntmberPrimesworkbench.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JFrame f = new JFrame("Über PrimesWorkbench");
				f.setLocationRelativeTo(null);
				f.getContentPane().setLayout(new BorderLayout());
				try {
					f.getContentPane().add(new JLabel(new ImageIcon(ImageIO.read(PrimesMenuBar.class.getResourceAsStream("/img/splash.png")))), BorderLayout.CENTER);
				} catch (IOException e) {
					PrimesApplication.error(e, false);
				} catch (IllegalArgumentException e) {
					PrimesApplication.error(e, false);
				}
				JLabel text = new JLabel("<html>" + PrimesGUI.GUI_WINDOW_TITLE + "<br>Copyright (c) 2013<br>Jan Erik Petersen (github.com/Marco01809)<br>Danish Belal (github.com/danishbelal)</html>");
				JPanel textPanel = new JPanel();
				textPanel.setBorder(new EmptyBorder(1, 1, 1, 1));
				textPanel.add(text);
				f.getContentPane().add(textPanel, BorderLayout.PAGE_END);
				f.pack();
				f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				try {
					f.setIconImage(ImageIO.read(PrimesGUI.class.getResourceAsStream("/img/logo_96x96.png")));
				} catch (IOException e) {
					PrimesApplication.error(e, false);
				} catch (IllegalArgumentException e) {
					PrimesApplication.error(e, false);
				}
				f.setLocationRelativeTo(PrimesMenuBar.this.getParent());
				f.setResizable(false);
				f.setVisible(true);
			}
		});
		
		if (Desktop.isDesktopSupported()) {
		JMenuItem mntnLink = new JMenuItem("PrimesWorkbench auf github");
		mntnLink.setToolTipText("Öffnet http://github.com/danishbelal/Primes");
		mntnLink.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					Desktop.getDesktop().browse(new URL("http://github.com/danishbelal/Primes").toURI());
				} catch (MalformedURLException e) {
					PrimesApplication.error(e, false);
				} catch (IOException e) {
					PrimesApplication.error(e, false);
				} catch (URISyntaxException e) {
					PrimesApplication.error(e, false);
				}
			}
		});
		mnHilfe.add(mntnLink);
		}
		mnHilfe.add(mntmberPrimesworkbench);
	}

	public void setExportButtonEnabled(boolean b) {
		mntmExportieren.setEnabled(b);
	}
}
