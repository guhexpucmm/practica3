package edu.pucmm.programacionweb2017.dao.impl;

import edu.pucmm.programacionweb2017.dao.DAOArticulo;
import edu.pucmm.programacionweb2017.database.DBConexion;
import edu.pucmm.programacionweb2017.modelo.Articulo;
import edu.pucmm.programacionweb2017.modelo.Comentario;
import edu.pucmm.programacionweb2017.modelo.Etiqueta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gusta on 02-Jun-17.
 */
public class DAOArticuloImpl extends DAO implements DAOArticulo {
    private static final Logger logger = LoggerFactory.getLogger(DAOArticuloImpl.class);

    private final String INSERT = "INSERT INTO ARTICULO (TITULO, CUERPO, AUTOR_ID, FECHA) VALUES (?,?,?,?)";
    private final String INSERT_COMENTARIOS = "INSERT INTO ARTICULO_COMENTARIOS (ARTICULO_ID, COMENTARIO_ID) VALUES (?,?)";
    private final String INSERT_ETIQUETAS = "INSERT INTO ARTICULO_ETIQUETAS (ARTICULO_ID, ETIQUETA_ID) VALUES (?,?)";
    private final String DELETE = "DELETE FROM ARTICULO WHERE ID = ? AND TITULO = ? AND CUERPO = ? AND AUTOR_ID = ? AND FECHA = ?";
    private final String UPDATE = "UPDATE ARTICULO SET TITULO = ?, CUERPO = ?, AUTOR_ID = ?, FECHA = ? WHERE ID = ?";
    private final String SELECT = "SELECT ID,TITULO,CUERPO,AUTOR_ID,FECHA FROM ARTICULO";
    private final String SELECT_POR_ID = "SELECT ID,TITULO,CUERPO,AUTOR_ID,FECHA FROM ARTICULO WHERE ID = ?";
    private final String SELECT_COMENTARIOS = "SELECT C.ID,C.COMENTARIO,C.AUTOR_ID,C.ARTICULO_ID FROM COMENTARIO C LEFT JOIN ARTICULO_COMENTARIOS AC ON C.ID = AC.COMENTARIO_ID AND C.ARTICULO_ID = ?";
    private final String SELECT_ETIQUETAS = "SELECT E.ID,E.ETIQUETA FROM ETIQUETA E LEFT JOIN ARTICULO_ETIQUETAS AE ON E.ID = AE.ETIQUETA_ID LEFT JOIN ARTICULO A ON AE.ARTICULO_ID = ?";

    private DBConexion dbConexion;

    public DAOArticuloImpl() {
        dbConexion = new DBConexion();
    }

    @Override
    public void insertar(Articulo articulo) {
        try {
            DBConexion dbConexion = new DBConexion();

            connection = dbConexion.getConexion();
            preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setString(1, articulo.getTitulo());
            preparedStatement.setString(2, articulo.getCuerpo());
            preparedStatement.setLong(3, articulo.getAutor().getId());
            preparedStatement.setDate(4, articulo.getFecha());

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
    public void insertarComentarios(Articulo articulo) {
        try {
            DBConexion dbConexion = new DBConexion();

            connection = dbConexion.getConexion();
            preparedStatement = connection.prepareStatement(INSERT_COMENTARIOS);

            for (Comentario comentario : articulo.getListaComentarios()) {
                preparedStatement.setLong(1, articulo.getId());
                preparedStatement.setLong(2, comentario.getId());

                preparedStatement.executeUpdate();
            }

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
    public void insertarEtiquetas(Articulo articulo) {
        try {
            DBConexion dbConexion = new DBConexion();

            connection = dbConexion.getConexion();
            preparedStatement = connection.prepareStatement(INSERT_ETIQUETAS);

            for (Etiqueta etiqueta : articulo.getListaEtiquetas()) {
                preparedStatement.setLong(1, articulo.getId());
                preparedStatement.setLong(2, etiqueta.getId());

                preparedStatement.executeUpdate();
            }

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
        List<Articulo> list = null;
        Articulo articulo = null;

        try {
            list = new ArrayList<>();

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
                articulo.setListaComentarios(encontrarComentarios(articulo));
                articulo.setListaEtiquetas(encontrarEtiquetas(articulo));

                list.add(articulo);
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
            }

            preparedStatement.close();
            connection.close();

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

    @Override
    public List<Comentario> encontrarComentarios(Articulo articulo) {
        List<Comentario> list = null;
        Comentario comentario = null;

        try {
            list = new ArrayList<>();

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
    public List<Etiqueta> encontrarEtiquetas(Articulo articulo) {
        List<Etiqueta> list = null;
        Etiqueta etiqueta = null;

        try {
            list = new ArrayList<>();

            DBConexion dbConexion = new DBConexion();
            connection = dbConexion.getConexion();
            preparedStatement = connection.prepareStatement(SELECT_ETIQUETAS);
            preparedStatement.setLong(1, articulo.getId());

            resultSet = preparedStatement.executeQuery();

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
}
