package edu.pucmm.programacionweb2017.dao.impl;

import edu.pucmm.programacionweb2017.dao.DAOUsuario;
import edu.pucmm.programacionweb2017.database.DBConexion;
import edu.pucmm.programacionweb2017.modelo.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gusta on 03-Jun-17.
 */
public class DAOUsuarioImpl implements DAOUsuario {
    private static final Logger logger = LoggerFactory.getLogger(DAOUsuarioImpl.class);

    private final String INSERT = "INSERT INTO USUARIO (ID, USERNAME, NOMBRE, PASSWORD, ADMINISTRATOR, AUTOR) VALUES (?,?,?,?,?,?)";
    private final String DELETE = "DELETE FROM USUARIO WHERE ID = ? AND USERNAME = ? AND NOMBRE = ? AND PASSWORD = ? AND ADMINISTRATOR = ? AND AUTOR = ?";
    private final String UPDATE = "UPDATE USUARIO SET USERNAME = ?, NOMBRE = ?, PASSWORD = ?, ADMINISTRATOR = ?, AUTOR = ? WHERE ID = ?";
    private final String SELECT = "SELECT ID,USERNAME,NOMBRE,PASSWORD,ADMINISTRATOR,AUTOR FROM USUARIO";
    private final String SELECT_POR_ID = "SELECT ID,USERNAME,NOMBRE,PASSWORD,ADMINISTRATOR,AUTOR FROM USUARIO WHERE ID = ?";
    private final String SELECT_POR_NOMBRE = "";
    private final String SELECT_POR_USUARIO = "SELECT ID,USERNAME,NOMBRE,PASSWORD,ADMINISTRATOR,AUTOR FROM USUARIO WHERE USERNAME = ?";

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private Statement statement = null;

    @Override
    public void insertar(Usuario usuario) {
        try {
            DBConexion dbConexion = new DBConexion();

            connection = dbConexion.getConexion();
            preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setLong(1, usuario.getId());
            preparedStatement.setString(2, usuario.getUsername());
            preparedStatement.setString(3, usuario.getNombre());
            preparedStatement.setString(4, usuario.getPassword());
            preparedStatement.setBoolean(5, usuario.isAdministrator());
            preparedStatement.setBoolean(6, usuario.isAutor());

            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            logger.debug("Error al hacer el insert en la clase usuario.", e);
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
    public void actualizar(Usuario usuario) {
        try {
            DBConexion dbConexion = new DBConexion();

            connection = dbConexion.getConexion();
            preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, usuario.getUsername());
            preparedStatement.setString(2, usuario.getNombre());
            preparedStatement.setString(3, usuario.getPassword());
            preparedStatement.setBoolean(4, usuario.isAdministrator());
            preparedStatement.setBoolean(5, usuario.isAutor());
            preparedStatement.setLong(6, usuario.getId());

            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            logger.debug("Error al hacer el update en la clase usuario.", e);
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
    public void borrar(Usuario usuario) {
        try {
            DBConexion dbConexion = new DBConexion();

            connection = dbConexion.getConexion();
            preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setLong(1, usuario.getId());
            preparedStatement.setString(2, usuario.getUsername());
            preparedStatement.setString(3, usuario.getNombre());
            preparedStatement.setString(4, usuario.getPassword());
            preparedStatement.setBoolean(5, usuario.isAdministrator());
            preparedStatement.setBoolean(6, usuario.isAutor());

            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            logger.debug("Error al hacer el delete en la clase usuario.", e);
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
    public List<Usuario> encontrarTodos() {
        List<Usuario> list = null;
        Usuario usuario = null;

        try {
            list = new ArrayList<>();

            DBConexion dbConexion = new DBConexion();
            connection = dbConexion.getConexion();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT);

            while (resultSet.next()) {
                usuario = new Usuario();
                usuario.setId(resultSet.getLong("ID"));
                usuario.setUsername(resultSet.getString("USERNAME"));
                usuario.setNombre(resultSet.getString("NOMBRE"));
                usuario.setPassword(resultSet.getString("PASSWORD"));
                usuario.setAdministrator(resultSet.getBoolean("ADMINISTRATOR"));
                usuario.setAutor(resultSet.getBoolean("AUTOR"));

                list.add(usuario);
            }

            resultSet.close();

            return list;
        } catch (SQLException e) {
            logger.debug("Error al hacer select en usuario.", e);
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
    public Usuario encontrarPorId(Long id) {
        Usuario usuario = null;
        try {
            DBConexion dbConexion = new DBConexion();

            connection = dbConexion.getConexion();
            preparedStatement = connection.prepareStatement(SELECT_POR_ID);
            preparedStatement.setLong(1, id);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                usuario = new Usuario();

                usuario.setId(resultSet.getLong("ID"));
                usuario.setUsername(resultSet.getString("USERNAME"));
                usuario.setNombre(resultSet.getString("NOMBRE"));
                usuario.setPassword(resultSet.getString("PASSWORD"));
                usuario.setAdministrator(resultSet.getBoolean("ADMINISTRATOR"));
                usuario.setAutor(resultSet.getBoolean("AUTOR"));
            }

            preparedStatement.close();
            connection.close();

            return usuario;
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

    @Override
    public Usuario encontrarPorNombre(String nombre) {
        return null;
    }

    @Override
    public Usuario encontrarPorCuentaUsuario(String usuario) {
        Usuario u = null;
        try {
            DBConexion dbConexion = new DBConexion();

            connection = dbConexion.getConexion();
            preparedStatement = connection.prepareStatement(SELECT_POR_USUARIO);
            preparedStatement.setString(1, usuario);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                u = new Usuario();

                u.setId(resultSet.getLong("ID"));
                u.setUsername(resultSet.getString("USERNAME"));
                u.setNombre(resultSet.getString("NOMBRE"));
                u.setPassword(resultSet.getString("PASSWORD"));
                u.setAdministrator(resultSet.getBoolean("ADMINISTRATOR"));
                u.setAutor(resultSet.getBoolean("AUTOR"));
            }

            preparedStatement.close();
            connection.close();

            return u;
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
