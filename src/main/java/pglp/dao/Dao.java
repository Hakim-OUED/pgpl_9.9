package pglp.dao;

import pglp.exceptions.KeyAlreadyExistException;
import pglp.formes.Forme;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Dao<T> {

  public String nomBd;
  public static Connection conn = null;
  ArrayList<Statement> statements = new ArrayList<Statement>();
  PreparedStatement psInsert;
  PreparedStatement psUpdate;
  static PreparedStatement psSelect;
  ResultSet rs = null;
  static final String SQL_SERIALIZE_OBJECT = "INSERT INTO dessin(id, objet, description) VALUES (?,?,?)";
  static final String SQL_DESERIALIZE_OBJECT = "SELECT * FROM dessin WHERE id = ?";
  static final String SQL_UPDATE_OBJECT = "UPDATE dessin set objet = ? , description = ? WHERE id = ?";
  static final String SQL_DELETE_OBJECT = "DELETE FROM dessin WHERE id = ?";


  public Dao() {

  }


  public static Forme get(String nom) throws SQLException, IOException, ClassNotFoundException {
    Forme forme = null;
    psSelect = conn.prepareStatement(SQL_DESERIALIZE_OBJECT);
    psSelect.setString(1, nom);
    ResultSet rs = psSelect.executeQuery();
    rs.next();
    byte[] b = rs.getBytes(2);
    ByteArrayInputStream is = new ByteArrayInputStream(b);
    ObjectInputStream ois = new ObjectInputStream(is);
    forme = (Forme) ois.readObject();
    rs.close();
    is.close();
    ois.close();
    psSelect.close();
    return forme;
  }

  public abstract List<T> getAll();
  public abstract T getSpecific(String id) throws SQLException, IOException, ClassNotFoundException;
  public abstract void create(T t) throws SQLException, IOException, ClassNotFoundException, KeyAlreadyExistException;

  public abstract void update(T t) throws IOException, SQLException;

  public abstract void delete(T t) throws SQLException;

}
