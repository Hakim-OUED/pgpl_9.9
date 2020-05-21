package pglp.commandes;

import pglp.dao.CercleDao;
import pglp.dao.Dao;
import pglp.dao.DaoFactory;
import pglp.exceptions.*;
import pglp.formes.*;

import java.io.IOException;
import java.sql.SQLException;

public class CmdLoad implements Commande {
  private static String identifiant;
  public CmdLoad(final String id){
    identifiant = id;
  }

  @Override
  public String execute() throws UnknowFormException, SQLException, IOException, ClassNotFoundException, KeyAlreadyExistException, DrawingNotExistException, UnloadedDrawingException, DrawingAlreadyLoadException {
    int status = 0;
    if (CmdCreate.formes.containsKey(identifiant)){
      throw new DrawingAlreadyLoadException();
    }else {
      Forme dessin = Dao.get(identifiant);
      if (dessin instanceof Cercle){
        Cercle cercle = (Cercle) dessin;
        CmdCreate.formes.put(identifiant,cercle);
        status = 1;
        return String.valueOf(status);
      } else if (dessin instanceof Carre){
        Carre carre = (Carre) dessin;
        CmdCreate.formes.put(identifiant,carre);
        status = 1;
        return String.valueOf(status);
      } else if (dessin instanceof Rectangle){
        Rectangle rectangle = (Rectangle) dessin;
        CmdCreate.formes.put(identifiant,rectangle);
        status = 1;
        return String.valueOf(status);
      } else if (dessin instanceof Triangle) {
        Triangle triangle = (Triangle) dessin;
        CmdCreate.formes.put(identifiant,triangle);
        status = 1;
        return String.valueOf(status);
      } else if (dessin instanceof FormeGroupe) {
        FormeGroupe groupe = (FormeGroupe) dessin;
        CmdCreate.formes.put(identifiant,groupe);
        status = 1;
        return String.valueOf(status);
      } else throw new DrawingNotExistException();
    }
  }
}