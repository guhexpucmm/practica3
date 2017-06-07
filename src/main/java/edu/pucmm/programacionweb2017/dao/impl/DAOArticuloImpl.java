package edu.pucmm.programacionweb2017.dao.impl;

import edu.pucmm.programacionweb2017.dao.DAOArticulo;
import edu.pucmm.programacionweb2017.database.DBConexion;
import edu.pucmm.programacionweb2017.modelo.Articulo;
import edu.pucmm.programacionweb2017.modelo.Comentario;
import edu.pucmm.programacionweb2017.modelo.Etiqueta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gusta on 02-Jun-17.
 */
public class DAOArticuloImpl implements DAOArticulo {
    private static final Logger logger = LoggerFactory.getLogger(DAOArticuloImpl.class);

    private final String INSERT = "INSERT INTO ARTICULO (ID,TITULO, CUERPO, AUTOR_ID, FECHA) VALUES (?,?,?,?,?)";
    private final String DELETE = "DELETE FROM ARTICULO WHERE ID = ? AND TITULO = ? AND CUERPO = ? AND AUTOR_ID = ? AND FECHA = ?";
    private final String UPDATE = "UPDATE ARTICULO SET TITULO = ?, CUERPO = ?, AUTOR_ID = ?, FECHA = ? WHERE ID = ?";
    private final String SELECT = "SELECT * FROM ARTICULO";
    private final String SELECT_POR_ID = "SELECT ID,TITULO,CUERPO,AUTOR_ID,FECHA FROM ARTICULO WHERE ID = ?";
    private final String SELECT_COMENTARIOS = "SELECT * FROM COMENTARIO WHERE ARTICULO_ID = ?";

    //LISTA DE ETIQUETAS
    private final String SELECT_ETIQUETAS = "INSERT INTO ARTICULO_ETIQUETAS (ID, ARTICULO_ID, ETIQUETA_ID) VALUES (?,?,?)";

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private Statement statement = null;

    private List<Comentario> listaComentarios;
    private List<Etiqueta> listaEtiquetas;

    public DAOArticuloImpl() {

    }

    @Override
    public void insertar(Articulo articulo) {
        try {
            DBConexion dbConexion = new DBConexion();

            connection = dbConexion.getConexion();
            preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setLong(1, articulo.getId());
            preparedStatement.setString(2, articulo.getTitulo());
            preparedStatement.setString(3, articulo.getCuerpo());
            preparedStatement.setLong(4, articulo.getAutor().getId());
            preparedStatement.setDate(5, articulo.getFecha());

            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            logger.debug("Error al hacer el insert en la clase articulo.", e);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    logger.debug("Error al cerrar el prepared statement", e);
                }
            }

            if (connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    logger.debug("Error al cerrar la conexion de la bd", e);
                }
        }
    }

    @Override
    public void actualizar(Articulo articulo) {
        try {
            DBConexion dbConexion = new DBConexion();

            connection = dbConexion.getConexion();
            preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, articulo.getTitulo());
            preparedStatement.setString(2, articulo.getCuerpo());
            preparedStatement.setLong(3, articulo.getAutor().getId());
            preparedStatement.setDate(4, articulo.getFecha());
            preparedStatement.setLong(5, articulo.getId());

            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            logger.debug("Error al hacer el update en la clase articulo.", e);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    logger.debug("Error al cerrar el prepared statement", e);
                }
            }

            if (connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    logger.debug("Error al cerrar la conexion de la bd", e);
                }
        }
    }

    @Override
    public void borrar(Articulo articulo) {
        try {
            DBConexion dbConexion = new DBConexion();

            connection = dbConexion.getConexion();
            preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setLong(1, articulo.getId());
            preparedStatement.setString(2, articulo.getTitulo());
            preparedStatement.setString(3, articulo.getCuerpo());
            preparedStatement.setLong(4, articulo.getAutor().getId());
            preparedStatement.setDate(5, articulo.getFecha());

            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            logger.debug("Error al hacer el delete en la clase articulo.", e);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    logger.debug("Error al cerrar el prepared statement", e);
                }
            }

            if (connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    logger.debug("Error al cerrar la conexion de la bd", e);
                }
        }
    }

    @Override
    public List<Articulo> encontrarTodos() {
        List<Articulo> list = new ArrayList<>();
        List<Etiqueta> listaEtiqueta = new ArrayList<>();

        Articulo articulo = null;

        try {
            DBConexion dbConexion = new DBConexion();
            connection = dbConexion.getConexion();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT);

            while (resultSet.next()) {
                articulo = new Articulo();
                articulo.setId(resultSet.getLong("ID"));
                articulo.setTitulo(resultSet.getString("TITULO"));
                articulo.setCuerpo(resultSet.getString("CUERPO"));
                articulo.setAutor(new DAOUsuarioImpl().encontrarPorId(resultSet.getLong("AUTOR_ID")));
                articulo.setFecha(resultSet.getDate("FECHA"));
                articulo.setResumen(articulo.getResumen());
                articulo.setListaComentarios(encontrarComentarios(articulo));

                list.add(articulo);
            }

            resultSet.close();
        } catch (SQLException e) {
            logger.debug("Error al hacer select.", e);
            return null;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    logger.debug("Error al cerrar el statement en el insert.");
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    logger.debug("Error al cerrar la conexion en el insert", e);
                }
            }

            return list;
        }
    }

    private List<Comentario> encontrarComentarios(Articulo articulo) {
        List<Comentario> listComentario = new ArrayList<>();

        Comentario comentario = null;

        try {
            DBConexion dbConexion = new DBConexion();

            connection = dbConexion.getConexion();
            preparedStatement = connection.prepareStatement(SELECT_COMENTARIOS);
            preparedStatement.setLong(1, articulo.getId());

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                comentario = new Comentario();
                comentario.setId(resultSet.getLong("ID"));
                comentario.setComentario(resultSet.getString("COMENTARIO"));
                comentario.setAutor(new DAOUsuarioImpl().encontrarPorId(resultSet.getLong("AUTOR_ID")));
                comentario.setArticulo(new DAOArticuloImpl().encontrarPorId(resultSet.getLong("ARTICULO_ID")));

                listComentario.add(comentario);
            }

            resultSet.close();
        } catch (SQLException e) {
            logger.debug("Error al hacer select.", e);
            return null;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    logger.debug("Error al cerrar el statement en el insert.");
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    logger.debug("Error al cerrar la conexion en el insert", e);
                }
            }

            return listComentario;
        }
    }


    @Override
    public Articulo encontrarPorId(Long id) {
        Articulo articulo = null;
        try {
            DBConexion dbConexion = new DBConexion();

            connection = dbConexion.getConexion();
            preparedStatement = connection.prepareStatement(SELECT_POR_ID);
            preparedStatement.setLong(1, id);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                articulo = new Articulo();

                articulo.setId(resultSet.getLong("ID"));
                articulo.setTitulo(resultSet.getString("TITULO"));
                articulo.setCuerpo(resultSet.getString("CUERPO"));
                articulo.setAutor(new DAOUsuarioImpl().encontrarPorId(resultSet.getLong("AUTOR_ID")));
                articulo.setFecha(resultSet.getDate("FECHA"));
                articulo.setListaComentarios(encontrarComentarios(articulo));
            }

            return articulo;
        } catch (SQLException e) {
            logger.debug("Error al hacer el select.", e);
            return null;
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    logger.debug("Error al cerrar el prepared statement", e);
                }
            }

            if (connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    logger.debug("Error al cerrar la conexion de la bd", e);
                }
        }
    }
}
