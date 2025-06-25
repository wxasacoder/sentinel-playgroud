package org.wx.config;

import com.alibaba.csp.sentinel.datasource.FileRefreshableDataSource;
import com.alibaba.csp.sentinel.datasource.FileWritableDataSource;
import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.WritableDataSource;
import com.alibaba.csp.sentinel.init.InitFunc;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.csp.sentinel.transport.util.WritableDataSourceRegistry;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FileDataSourceInit implements InitFunc, InitializingBean {

    @Override
    public void init() throws Exception {
        String flowRulePath = "./flowRule.json";
        String deRulePath = "./deRule.json";

        ReadableDataSource<String, List<FlowRule>> flowDs = new FileRefreshableDataSource<>(
            flowRulePath, source -> JSON.parseObject(source, new TypeReference<List<FlowRule>>() {})
        );
        ReadableDataSource<String, List<DegradeRule>> deDs = new FileRefreshableDataSource<>(
                deRulePath, source -> JSON.parseObject(source, new TypeReference<List<DegradeRule>>() {})
        );
        // 将可读数据源注册至 FlowRuleManager.
        FlowRuleManager.register2Property(flowDs.getProperty());
        DegradeRuleManager.register2Property(deDs.getProperty());

        WritableDataSource<List<FlowRule>> wds = new FileWritableDataSource<>(flowRulePath, this::encodeJson);
        WritableDataSource<List<DegradeRule>> deWds = new FileWritableDataSource<>(deRulePath, this::encodeJson);
        // 将可写数据源注册至 transport 模块的 WritableDataSourceRegistry 中.
        // 这样收到控制台推送的规则时，Sentinel 会先更新到内存，然后将规则写入到文件中.
        WritableDataSourceRegistry.registerFlowDataSource(wds);
        WritableDataSourceRegistry.registerDegradeDataSource(deWds);
    }

    private <T> String encodeJson(T t) {
        return JSON.toJSONString(t);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.init();
    }
}