package pglp;

import pglp.commandes.Commande;
import pglp.exceptions.UnknowFormException;

import java.io.IOException;
import java.sql.SQLException;

public class DrawingApp {

  /**
   * Execute le programme.
   * @param args ensemble d'arguments
   * @throws IOException Exception des saisies
   */
  public static void run(final String args) throws IOException, UnknowFormException, SQLException, ClassNotFoundException {

    DrawingTUI drawingTUI = new DrawingTUI();
    Commande cmd;
    while (true) {
     cmd = drawingTUI.nextCommand(args);
      cmd.execute();
    }
  }
}
