package dao_interfaces;

import java.sql.Connection;
import java.sql.SQLException;

public interface I_DAOConexion {
	I_DAOConexion getConexion() throws SQLException;
	public Connection getLink();
}
