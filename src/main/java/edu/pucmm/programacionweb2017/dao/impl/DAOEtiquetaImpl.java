package edu.pucmm.programacionweb2017.dao.impl;

import edu.pucmm.programacionweb2017.dao.DAOEtiqueta;
import edu.pucmm.programacionweb2017.database.DBConexion;
import edu.pucmm.programacionweb2017.modelo.Etiqueta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gusta on 03-Jun-17.
 */
public class DAOEtiquetaImpl implements DAOEtiqueta {
    private static final Logger logger = LoggerFactory.getLogger(DAOArticuloImpl.class);

    private final String INSERT = "INSERT INTO ETIQUETA (ID,ETIQUETA) VALUES (?,?)";
    private final String DELETE = "DELETE FROM ETIQUETA WHERE ID = ? AND ETIQUETA = ?";
    private final String UPDATE = "UPDATE ETIQUETA SET ETIQUETA = ? WHERE ID = ?";
    private final String SELECT = "SELECT ID,ETIQUETA FROM ETIQUETA";
    private final String SELECT_POR_ID = "SELECT ID,ETIQUETA FROM ETIQUETA WHERE ID = ?";

    private final String INSERT_ARTICULO_ETIQUETAS = "INSERT INTO ARTICULO_ETIQUETAS (ID, ARTICULO_ID, ETIQUETA_ID) VALUES (?,?,?)";

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private Statement statement = null;

    @Override
    public void insertar(Etiqueta etiqueta) {
        try {
            DBConexion dbConexion = new DBConexion();

            connection = dbConexion.getConexion();
            preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setLong(1, etiqueta.getId());
            preparedStatement.setString(2, etiqueta.getEtiqueta());

            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            logger.debug("Error al hacer el insert en la clase etiqueta.", e);
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
    public void actualizar(Etiqueta etiqueta) {
        try {
            DBConexion dbConexion = new DBConexion();

            connection = dbConexion.getConexion();
            preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, etiqueta.getEtiqueta());
            preparedStatement.setLong(2, etiqueta.getId());

            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            logger.debug("Error al hacer el update en la clase etiqueta.", e);
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
    public void borrar(Etiqueta etiqueta) {
        try {
            DBConexion dbConexion = new DBConexion();

            connection = dbConexion.getConexion();
            preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setLong(1, etiqueta.getId());
            preparedStatement.setString(2, etiqueta.getEtiqueta());

            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            logger.debug("Error al hacer el delete en la clase etiqueta.", e);
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
    public List<Etiqueta> encontrarTodos() {
        List<Etiqueta> list = null;
        Etiqueta etiqueta = null;

        try {
            list = new ArrayList<>();

            DBConexion dbConexion = new DBConexion();
            connection = dbConexion.getConexion();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT);

            while (resultSet.next()) {
                etiqueta = new Etiqueta();
                etiqueta.setId(resultSet.getLong("ID"));
                etiqueta.setEtiqueta(resultSet.getString("ETIQUETA"));

                list.add(etiqueta);
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
    public Etiqueta encontrarPorId(Long id) {
        Etiqueta etiqueta = null;
        try {
            DBConexion dbConexion = new DBConexion();

            connection = dbConexion.getConexion();
            preparedStatement = connection.prepareStatement(SELECT_POR_ID);
            preparedStatement.setLong(1, id);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                etiqueta = new Etiqueta();

                etiqueta.setId(resultSet.getLong("ID"));
                etiqueta.setEtiqueta(resultSet.getString("ETIQUETA"));
            }

            preparedStatement.close();
            connection.close();

            return etiqueta;
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
