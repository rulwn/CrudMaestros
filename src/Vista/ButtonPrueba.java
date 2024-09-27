/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author raule
 */
public class ButtonPrueba extends JButton{
    private String hint = "";
    private Icon icon;

    public ButtonPrueba() {
        setOpaque(false);
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setForeground(new Color(66, 66, 66));
        setFocusPainted(false);
        setContentAreaFilled(false);
        setBorderPainted(false);

        icon = new ImageIcon("/Drawable/location_on_24dp_000000_FILL0_wght400_GRAD0_opsz24");
        setIcon(icon);
        setHorizontalAlignment(SwingConstants.RIGHT);
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(Color.WHITE);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

        g2.setColor(new Color(173, 181, 189));
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);

        if (getText().isEmpty() && hint != null && !hint.isEmpty()) {
            g2.setColor(new Color(173, 181, 189));
            FontMetrics fm = g2.getFontMetrics();
            g2.drawString(hint, getInsets().left, getHeight() / 2 + fm.getAscent() / 2 - 2);
        }

        g2.dispose();
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        
    }

    @Override
    public void updateUI() {
        super.updateUI();
        setBackground(new Color(0, 0, 0, 0));
    }
}