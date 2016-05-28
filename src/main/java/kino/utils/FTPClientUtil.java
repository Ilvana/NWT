package kino.utils;

import org.apache.commons.net.ftp.*;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ekusundzija on 29/05/16.
 */
public class FTPClientUtil {
    private static final Logger logger = Logger.getLogger(FTPClientUtil.class);

    private static final int CONNECTION_TIMEOUT = 10000;
    private static final int KEEP_ALIVE_TIMEOUT = 300;
    private static final int SERVER_PORT = 21;

    public static boolean uploadFile(InputStream inputStream, String fileName, String directoryPath,
                                     String serverAddress, Integer serverPort,
                                     String userName, String password,
                                     Integer connectionTimeout, Integer keepAliveTimeout) {
        boolean result = false;

        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient = connectFTPClient(directoryPath, serverAddress, serverPort, userName, password, connectionTimeout, keepAliveTimeout);
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            result = ftpClient.storeFile(fileName, inputStream);

            inputStream.close();
            ftpClient.logout();

            return result;
        } catch (Exception e) {
            logger.error("Could not complete FTP action, exception: " + e.getMessage());
            return result;
        } finally {
            disconnect(ftpClient);
        }
    }

    private static FTPClient connectFTPClient(String directoryPath,
                                              String serverAddress, Integer serverPort,
                                              String userName, String password,
                                              Integer connectionTimeout, Integer keepAliveTimeout) throws Exception {
        Integer defaultConnectionTimeout = setConnectionTimeout(connectionTimeout);
        Integer defaultKeepAliveTimeout = setKeepAliveTimeout(keepAliveTimeout);
        Integer defaultServerPort = setServerPort(serverPort);

        FTPClient ftpClient = new FTPClient();
        ftpClient.setConnectTimeout(defaultConnectionTimeout);
        ftpClient.setControlKeepAliveTimeout(defaultKeepAliveTimeout);
        ftpClient.connect(serverAddress, defaultServerPort);
        if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
            ftpClient.disconnect();
            throw new FTPConnectionClosedException("Failed to connect to server: " + serverAddress + ":" + defaultServerPort);
        } else {
            ftpClient.login(userName, password);
            ftpClient.enterLocalPassiveMode();

            setWorkingDirectory(directoryPath, ftpClient);

            return ftpClient;
        }
    }

    private static Integer setConnectionTimeout(Integer connectionTimeout) {
        return connectionTimeout != null ? connectionTimeout : CONNECTION_TIMEOUT;
    }

    private static Integer setKeepAliveTimeout(Integer keepAliveTimeout) {
        return keepAliveTimeout != null ? keepAliveTimeout : KEEP_ALIVE_TIMEOUT;
    }

    private static Integer setServerPort(Integer serverPort) {
        return serverPort != null ? serverPort : SERVER_PORT;
    }

    private static void setWorkingDirectory(String directoryPath, FTPClient ftpClient) throws IOException {
        if (directoryPath != null) {
            ftpClient.changeWorkingDirectory(directoryPath);
        }
    }

    private static void disconnect(FTPClient ftpClient) {
        if (!ftpClient.isConnected()) {
            return;
        }
        try {
            ftpClient.disconnect();
        } catch (Exception e) {
            logger.error("Could not disconnect FTP, exception: " + e.getMessage());
        }
    }


}
