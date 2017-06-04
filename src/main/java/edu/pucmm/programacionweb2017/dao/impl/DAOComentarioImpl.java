package edu.pucmm.programacionweb2017.dao.impl;

import edu.pucmm.programacionweb2017.dao.DAOComentario;
import edu.pucmm.programacionweb2017.database.DBConexion;
import edu.pucmm.programacionweb2017.modelo.Comentario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gusta on 03-Jun-17.
 */
public class DAOComentarioImpl implements DAOComentario {
    private static final Logger logger = LoggerFactory.getLogger(DAOArticuloImpl.class);

    private final String INSERT = "INSERT INTO COMENTARIO (COMENTARIO, AUTOR_ID, ARTICULO_ID) VALUES (?,?,?)";
    private final String DELETE = "DELETE FROM COMENTARIO WHERE ID = ? AND COMENTARIO = ? AND AUTOR_ID = ? AND ARTICULO_ID = ?";
    private final String UPDATE = "UPDATE COMENTARIO SET COMENTARIO = ?, AUTOR_ID = ?, ARTICULO_ID = ? WHERE ID = ?";
    private final String SELECT = "SELECT ID,COMENTARIO,AUTOR_ID,ARTICULO_ID FROM COMENTARIO";
    private final String SELECT_POR_ID = "SELECT ID,COMENTARIO,AUTOR_ID,ARTICULO_ID FROM COMENTARIO WHERE ID = ?";

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private Statement statement = null;

    @Override
    public void insertar(Comentario comentario) {
        try {
            DBConexion dbConexion = new DBConexion();

            connection = dbConexion.getConexion();
            preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setString(1, comentario.getComentario());
            preparedStatement.setLong(2, comentario.getAutor().getId());
            preparedStatement.setLong(3, comentario.getArticulo().getId());

            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            logger.debug("Error al hacer el insert en la clase comentario.", e);
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
    public void actualizar(Comentario comentario) {
        try {
            DBConexion dbConexion = new DBConexion();

            connection = dbConexion.getConexion();
            preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, comentario.getComentario());
            preparedStatement.setLong(2, comentario.getAutor().getId());
            preparedStatement.setLong(3, comentario.getArticulo().getId());
            preparedStatement.setLong(4, comentario.getId());

            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            logger.debug("Error al hacer el update en la clase comentario.", e);
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
    public void borrar(Comentario comentario) {
        try {
            DBConexion dbConexion = new DBConexion();

            connection = dbConexion.getConexion();
            preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setLong(1, comentario.getId());
            preparedStatement.setString(2, comentario.getComentario());
            preparedStatement.setLong(3, comentario.getAutor().getId());
            preparedStatement.setLong(4, comentario.getArticulo().getId());

            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            logger.debug("Error al hacer el delete en la clase comentario.", e);
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
    public List<Comentario> encontrarTodos() {
        List<Comentario> list = null;
        Comentario comentario = null;

        try {
            list = new ArrayList<>();

            DBConexion dbConexion = new DBConexion();
            connection = dbConexion.getConexion();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT);

            while (resultSet.next()) {
                comentario = new Comentario();
                comentario.setId(resultSet.getLong("ID"));
                comentario.setComentario(resultSet.getString("COMENTARIO"));
                comentario.setAutor(new DAOUsuarioImpl().encontrarPorId(resultSet.getLong("AUTOR_ID")));
                comentario.setArticulo(new DAOArticuloImpl().encontrarPorId(resultSet.getLong("ARTICULO_ID")));

                list.add(comentario);
            }

            resultSet.close();

            return list;
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
        }
    }

    @Override
    public Comentario encontrarPorId(Long id) {
        Comentario comentario = null;
        try {
            DBConexion dbConexion = new DBConexion();

            connection = dbConexion.getConexion();
            preparedStatement = connection.prepareStatement(SELECT_POR_ID);
            preparedStatement.setLong(1, id);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                comentario = new Comentario();

                comentario.setId(resultSet.getLong("ID"));
                comentario.setComentario(resultSet.getString("COMENTARIO"));
                comentario.setAutor(new DAOUsuarioImpl().encontrarPorId(resultSet.getLong("AUTOR_ID")));
                comentario.setArticulo(new DAOArticuloImpl().encontrarPorId(resultSet.getLong("ARTICULO_ID")));
            }

            preparedStatement.close();
            connection.close();

            return comentario;
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
