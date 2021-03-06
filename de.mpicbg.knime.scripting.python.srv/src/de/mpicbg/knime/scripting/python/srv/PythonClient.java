package de.mpicbg.knime.scripting.python.srv;

import gnu.cajo.utils.extra.TransparentItemProxy;

import java.io.File;
import java.io.IOException;


/**
 * Document me!
 *
 * @author Tom Haux
 */
public class PythonClient implements Python {
    private Python python;

    /**
     * Create PythonClient that communicates with the PythonServer
     */
    public PythonClient() {
        this(Python.DEFAULT_HOST, Python.DEFAULT_PORT);
    }

    /**
     * Create PythonClient that communicates with the PythonServer
     */
    public PythonClient(String serverName, int serverPort) {
        try {
            String url = "//" +
                    serverName + ":" + serverPort + "/" + Python.REGISTRY_NAME;
            python =
                    (Python) TransparentItemProxy.getItem(url, new Class[]{Python.class});
        } catch (Throwable
                e) {
            throw new RuntimeException(e);
        }
    }

    public File createTempFile(String prefix, String suffix) {
        return python.createTempFile(prefix, suffix);
    }

    public String getFilePath(File file) {
        return python.getFilePath(file);
    }

    public boolean deleteFile(File file) {
        return python.deleteFile(file);
    }

    public CommandOutput executeCommand(String[] command) {
        return python.executeCommand(command);
    }

    public int openFile(File file) throws IOException {
        return python.openFile(file);
    }

    public byte[] readFile(int descriptor) throws IOException {
        return python.readFile(descriptor);
    }

    public void writeFile(int descriptor, byte[] bytes) throws IOException {
        python.writeFile(descriptor, bytes);
    }

    public void closeFile(int descriptor) throws IOException {
        python.closeFile(descriptor);
    }
}
