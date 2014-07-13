import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import static javax.swing.JLayeredPane.putLayer;

public class SlideEg extends JPanel {
   private SlideContainer slideContainer = new SlideContainer();
   private JLabel piclabel;
   private JLabel piclabel2;
   private ImageIcon img;
   private int counter;

   public SlideEg() {
      //img = new ImageIcon(this.getClass().getResource("Evil0.png"));
      setLayout(new BorderLayout());
      add(slideContainer, BorderLayout.CENTER);

      JLabel helloLabel = new JLabel("Hello", SwingConstants.CENTER);
      slideContainer.add(helloLabel);

      
      counter = 0;
      Timer myTimer = new Timer(5000, new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            JLabel goodbyeLabel = new JLabel("Goodbye", SwingConstants.CENTER);
            goodbyeLabel.setOpaque(true);
            goodbyeLabel.setBackground(Color.pink);
            
            img = new ImageIcon(this.getClass().getResource("pic"+counter+".jpg"));
            piclabel = new JLabel(img);
            slideContainer.add(piclabel);
            counter++;
            if(counter == 5) {
                counter = 0;
            }
         }
      });
      //myTimer.setRepeats(false);
      myTimer.start();
   }
}

class SlideContainer extends JLayeredPane {
   private static final int PREF_W = 400;
   private static final int PREF_H = PREF_W;
   private static final int SLIDE_DELAY = 20;
   protected static final int DELTA_X = 2;
   Component oldComponent;

   public SlideContainer() {
      setLayout(null);
   }

   @Override
   public Dimension getPreferredSize() {
      return new Dimension(PREF_W, PREF_H);
   }

   @Override
   public Component add(Component comp) {
      Component[] comps = getComponents();
      if (comps.length > 0) {
         oldComponent = comps[0];
      }
      if (oldComponent == comp) {
         return super.add(comp);
      }
      if (oldComponent != null) {
         putLayer((JComponent) oldComponent, JLayeredPane.DEFAULT_LAYER);
      }
      Component returnResult = super.add(comp);
      putLayer((JComponent) comp, JLayeredPane.DRAG_LAYER);
      comp.setSize(getPreferredSize());
      comp.setVisible(true);
      comp.setLocation(getPreferredSize().width, 0);
      slideFromRight(comp, oldComponent);
      return returnResult;
   }

   private void slideFromRight(final Component comp,
         final Component oldComponent2) {
      new Timer(SLIDE_DELAY, new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent aEvt) {
            int x = comp.getX();
            if (x <= 0) {
               comp.setLocation(0, 0);
               putLayer((JComponent) comp, JLayeredPane.DEFAULT_LAYER);
               if (oldComponent2 != null) {
                  remove(oldComponent2);
               }
               ((Timer) aEvt.getSource()).stop();
            } else {
               x -= DELTA_X;
               comp.setLocation(x, 0);
            }
            repaint();
         }
      }).start();
   }
}