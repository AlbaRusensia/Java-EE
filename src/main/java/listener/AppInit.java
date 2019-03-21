package listener;

import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import connectionPool.BasicConnectionPool;

public class AppInit implements ServletContextListener {

	public void contextInitialized(ServletContextEvent arg0) {
		try {
			BasicConnectionPool.create("jdbc:mysql://localhost:3306/MyStoreSecond?serverTimezone=UTC&useSSL=false", "root",
					"1111");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}

}
