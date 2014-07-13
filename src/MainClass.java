/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cjae
 */
public class MainClass {
    
    public MainClass() {
        
        LoginDialog ldl = new LoginDialog(null, true);
        ldl.setLocationRelativeTo(null);
        ldl.setVisible(true);
        
        if(ldl.isLoginsucceeded()) {
            if(ldl.getUserpane() == 1) {
                AdminFrame adminFrame = new AdminFrame();
                adminFrame.setVisible(true);
            }else if(ldl.getUserpane() == 2){
                CourseAdviserFrame csframe = new CourseAdviserFrame();
                csframe.setVisible(true);
            }else {
                 UserFrame userframe = new UserFrame();
                userframe.setVisible(true);
            }
        }
    }
    
     private static void setPreview() {
         try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainClass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
     
    public static void main(String[]args) {
        setPreview();
        
        MainClass mainClass = new MainClass();
    }
    
}
