package com.lm.lm_client.abstraction;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class ServerConnectionHandler 
{
    private final SocketChannel socketChannel;
    private final Selector selector;
    private final ObjectOutputStream outputStream;
    private final ObjectInputStream inputStream;

    public ServerConnectionHandler(InetAddress serverAddress, int port) throws IOException 
    {
    	this.socketChannel = SocketChannel.open();
        this.socketChannel.configureBlocking(false);
        this.socketChannel.connect(new InetSocketAddress(serverAddress, port));

        while (!socketChannel.finishConnect()) {
            // Wait for the connection to complete
            // This may block the current thread
        }

        this.selector = Selector.open();
        this.socketChannel.register(selector, SelectionKey.OP_READ);
        this.outputStream = new ObjectOutputStream(socketChannel.socket().getOutputStream());
        this.inputStream = new ObjectInputStream(socketChannel.socket().getInputStream());
    }

    public void writeObject(Object object) throws IOException 
    {
        outputStream.writeObject(object);
        outputStream.flush();
    }

    public Object readObject() throws IOException, ClassNotFoundException 
    {
        selector.select();
        Set<SelectionKey> selectedKeys = selector.selectedKeys();
        Iterator<SelectionKey> keyIterator = selectedKeys.iterator();

        while (keyIterator.hasNext()) 
        {
            SelectionKey key = keyIterator.next();
            if (key.isReadable()) 
            {
                Object object = inputStream.readObject();
                return object;
            }
            keyIterator.remove();
        }
        return null;
    }
}
