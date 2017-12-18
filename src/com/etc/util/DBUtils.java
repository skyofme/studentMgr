package com.etc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBUtils {

	private final static String driverName = "com.mysql.jdbc.Driver";
	private final static String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8";
	private final static String username = "root";
	private final static String password = "root";

	private Connection connection = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	public Connection getConnection() {
		try {
			Class.forName(driverName);
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println("数据库连接失败！");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("没有找到数据库驱动！");
			e.printStackTrace();
		}
		return connection;
	}

	public int excuteUpdate(String sql, Object... args) {
		try {
			pstmt = getConnection().prepareStatement(sql);
			if (args != null) {
				for (int i = 0; i < args.length; i++) {
					pstmt.setObject(i + 1, args[i]);
				}
			}
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("执行更新操作失败！");
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return 0;
	}

	public List<Object> excuteQuery(String sql, Object... args) {
		List<Object> resultList = new ArrayList<Object>();
		try {
			pstmt = getConnection().prepareStatement(sql);
			if (args != null) {
				for (int i = 0; i < args.length; i++) {
					pstmt.setObject(i + 1, args[i]);
				}
			}
			rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = null;
			rsmd = rs.getMetaData();

			int columnCount = rsmd.getColumnCount();

			while (rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					map.put(rsmd.getColumnLabel(i), rs.getObject(i));
				}
				resultList.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return resultList;
	}

	public void closeAll() {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}