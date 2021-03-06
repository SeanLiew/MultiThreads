package iol;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketOption;
import java.net.SocketOptions;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Set;

public class TestNio {
    public static void main(String[] args) throws IOException {
        //111111111
        //Service端的Channel，监听端口的
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        //设置为非阻塞
        serverChannel.configureBlocking(false);
        //nio的api规定这样赋值端口
        serverChannel.bind(new InetSocketAddress(8000));
        //显示Channel是否已经启动成功，包括绑定在哪个地址上
        System.out.println("服务端启动成功，监听端口为8000，等待客户端连接..."+ serverChannel.getLocalAddress());

        //22222222
        //声明selector选择器
        Selector selector = Selector.open();
        //这句话的含义，是把selector注册到Channel上面，
        //每个客户端来了之后，就把客户端注册到Selector选择器上,默认状态是Accepted
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);

        //33333333
        //创建buffer缓冲区，声明大小是1024，底层使用数组来实现的
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        RequestHandler requestHandler = new RequestHandler();

        Object o = serverChannel.blockingLock();

        Set<SocketOption<?>> socketOptions = serverChannel.supportedOptions();

        System.out.println(socketOptions);

        SocketChannel accept = serverChannel.accept();

        Set<SocketOption<?>> socketOptions1 = accept.supportedOptions();

        System.out.println(socketOptions1);

        accept.setOption(StandardSocketOptions.SO_RCVBUF, 1234);


        int soKeepalive = SocketOptions.SO_KEEPALIVE;

        SelectionKey selectionKey = serverChannel.keyFor(selector);

        int select = selector.select();

        SelectorProvider provider = serverChannel.provider();

    }
}
