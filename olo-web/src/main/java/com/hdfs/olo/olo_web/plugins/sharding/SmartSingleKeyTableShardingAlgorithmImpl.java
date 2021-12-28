package com.hdfs.olo.olo_web.plugins.sharding;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.SingleKeyTableShardingAlgorithm;

/**
 */
@SuppressWarnings("rawtypes")
public class SmartSingleKeyTableShardingAlgorithmImpl implements SingleKeyTableShardingAlgorithm {
	
	private  Logger logger = LoggerFactory.getLogger(getClass());
	
	public String doEqualSharding(Collection availableTargetNames, ShardingValue shardingValue) {
		logger.info("SmartSingleKeyTableShardingAlgorithmImpl   doEqualSharding");
		return null;
	}

	public Collection<String> doInSharding(Collection tableNames, ShardingValue shardingValue) {
		List<String> shardingSuffix = new ArrayList<String>();
		if(null == tableNames || null == shardingValue) return shardingSuffix;
        long partId = 0;
        String columnName = shardingValue.getColumnName();
        Object value = shardingValue.getValue();
        if ("privId".equals(columnName)) {
            partId = ((Long) value) % tableNames.size();
        } else if ("order_id".equals(columnName)) {
            partId = ((Long) value) % tableNames.size();
        }
//        for (String name : tableNames.toArray()) {
//            if (name.endsWith(partId + "")) {
//                shardingSuffix.add(name);
//                return shardingSuffix;
//            }
//        }
        return shardingSuffix;
	}

	public Collection doBetweenSharding(Collection availableTargetNames, ShardingValue shardingValue) {
		logger.info("SmartSingleKeyTableShardingAlgorithmImpl   doBetweenSharding");
		return null;
	}
}
