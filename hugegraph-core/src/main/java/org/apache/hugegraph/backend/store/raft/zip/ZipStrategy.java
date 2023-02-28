package org.apache.hugegraph.backend.store.raft.zip;

import java.util.zip.Checksum;

/**
 * @author wuchaojing
 * @date 2023/2/23 4:01 下午
 * @wiki
 * @description
 **/
public interface ZipStrategy {
    /**
     * Compress files to zip
     *
     * @param rootDir    the origin file root dir
     * @param sourceDir  the origin file source dir
     * @param outputZipFile the target zip file
     * @param checksum   checksum
     */
    void compressZip(final String rootDir, final String sourceDir, final String outputZipFile, final Checksum checksum)
        throws Throwable;

    /**
     * Decompress zip to files
     *
     * @param sourceZipFile the origin zip file
     * @param outputDir  the target file dir
     * @param checksum   checksum
     */
    void decompressZip(final String sourceZipFile, final String outputDir, final Checksum checksum) throws Throwable;
}
