package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import backend.Card;
import backend.Main;
import backend.Moves;
import backend.Suit;
import backend.Value;

public class Window extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private static final int SPRITE_WIDTH = 72;
	private static final int SPRITE_HEIGHT = 96;
	
	private JPanel dealerCards, playerCards;
	private JButton split, next;
	
	public Window() {
		super("Blackjack Tutor");
		setSize(900, 1000);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		//Dealer Pane
		dealerCards = new JPanel();
		dealerCards.setMaximumSize(new Dimension(getWidth(), 450));
		dealerCards.setLayout(new BoxLayout(dealerCards, BoxLayout.X_AXIS));
		dealerCards.setBackground(Color.GREEN.darker().darker());
		dealerCards.setAlignmentX(.5f);
		
		//Player Pane
		playerCards = new JPanel();
		playerCards.setMaximumSize(new Dimension(getWidth(), 450));
		playerCards.setLayout(new BoxLayout(playerCards, BoxLayout.X_AXIS));
		playerCards.setBackground(Color.GREEN.darker().darker());
		
		//Buttons Pane
		JPanel buttonsPane = new JPanel();
		buttonsPane.setMaximumSize(new Dimension(getWidth(), 100));
		buttonsPane.setLayout(new BoxLayout(buttonsPane, BoxLayout.X_AXIS));
		buttonsPane.setBackground(Color.GREEN.darker().darker());
		
		JButton hit = new JButton("Hit");
		hit.addActionListener((e) -> Main.checkMove(Moves.HIT));
		buttonsPane.add(hit);
		
		JButton stay = new JButton("Stay");
		stay.addActionListener((e) -> Main.checkMove(Moves.STAY));
		buttonsPane.add(stay);
		
		JButton double_bn = new JButton("Double");
		double_bn.addActionListener((e) -> Main.checkMove(Moves.DOUBLE));
		buttonsPane.add(double_bn);
		
		split = new JButton("Split");
		split.addActionListener((e) -> Main.checkMove(Moves.SPLIT));
		split.setEnabled(false);
		buttonsPane.add(split);
		
		next = new JButton("Next Hand");
		next.addActionListener((e) -> Main.deal());
		next.setAlignmentX(1f);
		next.setEnabled(false);
		buttonsPane.add(next);
		
		//Container
		JPanel center = new JPanel();
		center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
		center.add(dealerCards);
		center.add(playerCards);
		center.add(buttonsPane);
		
		getContentPane().add(center);
	}
	
	public void addDealerCard(Card card) {
		int startX = getIndex(Value.values(), card.getValue()) * SPRITE_WIDTH;
		int startY = getIndex(Suit.values(), card.getSuit()) * SPRITE_HEIGHT;
		/*
		JLabel sprite;
		try {
			BufferedImage icon = ImageIO.read(new File("spritesheet.png")).getSubimage(startX, startY, SPRITE_WIDTH, SPRITE_HEIGHT);
			sprite = new JLabel(new ImageIcon(icon));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			sprite = new JLabel("Error");
		}
		*/
		try {
			dealerCards.add(new JLabel(new ImageIcon(ImageIO.read(new File("spritesheet.png")).getSubimage(startX, startY, SPRITE_WIDTH, SPRITE_HEIGHT))));
		} catch (IOException e) {
			dealerCards.add(new JLabel("Error"));
		}
		
		setVisible(true);
	}
	
	public void addPlayerCard(Card card) {
		int startX = getIndex(Value.values(), card.getValue()) * SPRITE_WIDTH;
		int startY = getIndex(Suit.values(), card.getSuit()) * SPRITE_HEIGHT;
		
		JLabel sprite;
		try {
			BufferedImage icon = ImageIO.read(new File("spritesheet.png")).getSubimage(startX, startY, SPRITE_WIDTH, SPRITE_HEIGHT);
			sprite = new JLabel(new ImageIcon(icon));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			sprite = new JLabel("Error");
		}
		playerCards.add(sprite);
	}
	
	public void clearDealerCards() {
		dealerCards.removeAll();
	}
	
	public void clearPlayerCards() {
		playerCards.removeAll();
	}
	
	private int getIndex(Object[] arr, Object target) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].equals(target))
				return i;
		}
		return -1;
	}
	
	public void canSplit(boolean canSplit) {
		split.setEnabled(canSplit);
	}
	
	public void finishedHand(boolean finished) {
		next.setEnabled(finished);
	}

}
