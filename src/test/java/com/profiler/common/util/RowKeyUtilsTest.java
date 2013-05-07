package com.profiler.common.util;

import com.profiler.common.bo.SqlMetaDataBo;
import junit.framework.Assert;
import org.junit.Test;

/**
 *
 */
public class RowKeyUtilsTest {
    @Test
    public void testGetSqlId() throws Exception {
        long startTime = System.currentTimeMillis();
        SqlMetaDataBo sqlMetaDataBo = new SqlMetaDataBo("agent", 1, startTime);
        byte[] agents = sqlMetaDataBo.toRowKey();


        SqlMetaDataBo sqlId = new SqlMetaDataBo();
        sqlId.readRowKey(agents);

        Assert.assertEquals(sqlId.getAgentId(), "agent");
        Assert.assertEquals(sqlId.getHashCode(), 1);
        Assert.assertEquals(sqlId.getStartTime(), startTime);
    }
}
