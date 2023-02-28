package org.apache.hugegraph.backend.store.raft.zip;

import org.apache.hugegraph.util.CompressUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.zip.Checksum;

/**
 * @author wuchaojing
 * @date 2023/2/23 4:10 下午
 * @wiki
 * @description
 **/
public class SerialZipStrategy implements ZipStrategy {
    private static final Logger LOG = LoggerFactory.getLogger(SerialZipStrategy.class);

    @Override
    public void compressZip(final String rootDir, final String sourceDir, final String outputZipFile,
                            final Checksum checksum) throws Throwable {
        LOG.info("Start to compress snapshot in serial strategy");
        CompressUtil.compressZip(rootDir, sourceDir, outputZipFile, checksum);
    }

    @Override
    public void decompressZip(final String sourceZipFile, final String outputDir, final Checksum checksum)
        throws Throwable {
        LOG.info("Start to decompress snapshot in serial strategy");
        CompressUtil.decompressZip(sourceZipFile, outputDir, checksum);
    }
}
