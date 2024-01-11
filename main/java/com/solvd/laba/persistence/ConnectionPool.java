package com.solvd.laba.persistence;

import com.solvd.laba.config.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ConnectionPool {
    private static volatile ConnectionPool instance;
    private final List<Connection> connectionList;

    private ConnectionPool() {
        try {
            Class.forName(Config.DRIVER.getValue());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unable to find driver class.", e);
        }

        int connectionPoolSize = Integer.parseInt(Config.POOL_SIZE.getValue());
        this.connectionList = new ArrayList<>(connectionPoolSize);
        IntStream.range(0, connectionPoolSize)
                .boxed()
                .forEach(index -> connectionList.add(createConnection()));
    }

    private Connection createConnection() {
        Connection connection;

        try {
            connection = DriverManager.getConnection(Config.URL.getValue(), Config.USER.getValue(), Config.PASSWORD.getValue());
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create connection.", e);
        }
        return connection;
    }

    public synchronized Connection getConnection() {
        while (connectionList.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Interrupted while waiting for a connection.", e);
            }
        }

        return connectionList.remove(connectionList.size() - 1);
    }
    public static ConnectionPool getInstance() {
        if (instance == null) {
            synchronized (ConnectionPool.class) {
                if (instance == null) {
                    instance = new ConnectionPool();
                }
            }
        }
        return instance;
    }
    public synchronized void releaseConnection(Connection connection) {
        synchronized (connectionList) {
            connectionList.add(connection);
            notifyAll();
        }
    }

    public synchronized void closeConnections() {
        connectionList.forEach(connection -> {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        connectionList.clear();
    }
}
