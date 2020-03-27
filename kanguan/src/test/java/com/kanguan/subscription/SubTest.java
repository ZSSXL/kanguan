package com.kanguan.subscription;

import com.kanguan.BaseTest;
import com.kanguan.entity.vo.SubResourceVo;
import com.kanguan.mapper.ResourceMapper;
import com.kanguan.mapper.SubscriptionMapper;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author ZSS
 * @date 2020/3/27 16:02
 * @description 订阅测试
 */
public class SubTest extends BaseTest {

    @Autowired
    private SubscriptionMapper subscriptionMapper;
    @Autowired
    private ResourceMapper resourceMapper;

    /**
     * 获取订阅测试
     */
    @Test
    public void getSubResourceVoTest() {
        String subscriber = "2c211cabe58f4d349b8dcfc11d343a9a";
        List<SubResourceVo> subResourceVos = subscriptionMapper.selectSubBySubscriber(subscriber);
        for (SubResourceVo sr : subResourceVos) {
            String updateTimeByObject = resourceMapper.getUpdateTimeByObject(sr.getSubObject());
            if (StringUtils.isEmpty(updateTimeByObject)) {
                sr.setUpdateTime("null");
            } else {
                sr.setUpdateTime(updateTimeByObject);
            }
            System.out.println(sr);
        }
    }

    @Test
    public void getResourceUpdateTest() {
        // bc0dff6eaa0549749d192e1d84b37aa0
        String object = "e94bc75eab9240bdbcd41a22235a4ed";
        String updateTimeByObject = resourceMapper.getUpdateTimeByObject(object);
        System.out.println(updateTimeByObject);
    }

}
