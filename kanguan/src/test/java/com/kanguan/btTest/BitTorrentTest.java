package com.kanguan.btTest;

import com.kanguan.BaseTest;
import com.kanguan.util.BytesUtil;
import org.eclipse.bittorrent.TorrentFile;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author ZSS
 * @date 2020/3/22 21:39
 * @description 磁力链接解析测试
 */
public class BitTorrentTest extends BaseTest {

    @Test
    public void btTest() throws IOException {
        String path = "E:\\Eye.for.an.Eye.以眼还眼.2019.中英字幕.BDrip.720P-自由译者联盟.torrent";
        TorrentFile file = new TorrentFile(new File(path));
        String[] filenames = file.getFilenames();
        System.out.println("filenames ：" + new String(filenames[0].getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
        long[] lengths = file.getLengths();
        long length = lengths[0];
        String s = BytesUtil.setSize((int) length);
        System.out.println("size : " + s);
    }
}
