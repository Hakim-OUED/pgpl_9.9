package pglp.commandes;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import pglp.exceptions.UnloadedDrawingException;

/**
 * Commande pour deplacer un dessin.
 */
public class CmdMove implements Commande {

  private static List<Integer> parsedParams = new ArrayList();
  private static String identifiant;

  /**
   * Constructeur de la commande Move.
   * @param params liste des paramètres de la commande
   */
  public CmdMove(String[] params) {
    identifiant = params[0];
    Pattern p = Pattern.compile("-?\\d+");
    Matcher m = p.matcher(params[1]);
    while (m.find()) {
      int n = Integer.parseInt(m.group());
      parsedParams.add(n);
    }

  }


  @Override
  public String execute() throws UnloadedDrawingException {
    if (CmdCreate.formes.containsKey(identifiant)) {
      int nx = parsedParams.get(0);
      int ny = parsedParams.get(1);
      CmdCreate.formes.get(identifiant).deplacer(nx, ny);
      parsedParams.clear();
      return identifiant + " a été déplacé";
    } else {
      parsedParams.clear();
      throw new UnloadedDrawingException(identifiant);
    }
  }
}
