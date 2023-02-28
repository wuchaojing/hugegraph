package org.apache.hugegraph.backend.store.raft.zip;

import org.apache.hugegraph.config.CoreOptions;
import org.apache.hugegraph.config.HugeConfig;

/**
 * @author wuchaojing
 * @date 2023/2/23 4:07 下午
 * @wiki
 * @description
 **/
public class ZipStrategyManager {
    private static ZipStrategy[] zipStrategies = new ZipStrategy[5];
    private static byte DEFAULT_STRATEGY = 1;
    public static final byte SERIAL_STRATEGY = 1;
    public static final byte PARALLEL_STRATEGY = 2;

    static {
        addZipStrategy(SERIAL_STRATEGY, new SerialZipStrategy());
    }

    public static void addZipStrategy(final int idx, final ZipStrategy zipStrategy) {
        if (zipStrategies.length <= idx) {
            final ZipStrategy[] newZipStrategies = new ZipStrategy[idx + 5];
            System.arraycopy(zipStrategies, 0, newZipStrategies, 0, zipStrategies.length);
            zipStrategies = newZipStrategies;
        }
        zipStrategies[idx] = zipStrategy;
    }

    public static ZipStrategy getDefault() {
        return zipStrategies[DEFAULT_STRATEGY];
    }

    public static void init(final HugeConfig config) {
        if (config.get(CoreOptions.RAFT_SNAPSHOT_PARALLEL_COMPRESS)) {
            // add parallel zip strategy
            if (zipStrategies[PARALLEL_STRATEGY] == null) {
                final ZipStrategy zipStrategy = new ParallelZipStrategy(
                    config.get(CoreOptions.RAFT_SNAPSHOT_COMPRESS_THREADS),
                    config.get(CoreOptions.RAFT_SNAPSHOT_DECOMPRESS_THREADS));
                ZipStrategyManager.addZipStrategy(ZipStrategyManager.PARALLEL_STRATEGY, zipStrategy);
                DEFAULT_STRATEGY = PARALLEL_STRATEGY;
            }
        }
    }

    private ZipStrategyManager() {
    }

}
