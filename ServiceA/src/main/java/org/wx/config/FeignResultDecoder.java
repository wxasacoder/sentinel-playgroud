//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.wx.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import feign.FeignException;
import feign.Response;
import feign.Util;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;

import java.io.IOException;
import java.lang.reflect.Type;



public class FeignResultDecoder extends ResponseEntityDecoder {
    private static final Logger log = LoggerFactory.getLogger(FeignResultDecoder.class);

    public FeignResultDecoder(Decoder decoder) {
        super(decoder);
    }

    public Object decode(Response response, Type type) throws IOException, DecodeException, FeignException {
//        if (type instanceof ParameterizedTypeImpl && ((ParameterizedTypeImpl)type).getRawType().getName().equals(Result.class.getName())) {
//            return super.decode(response, type);
//        } else
            if (response.body() == null) {
            throw new DecodeException(response.status(), "没有返回有效的数据", response.request());
        } else {
            String bodyStr = Util.toString(response.body().asReader(Util.UTF_8));
            Result result = (Result)JSON.parseObject(bodyStr, Result.class);
            if (!"SUCCESS".equals(result.getStatus())) {
                throw new FeignClientException(response.status(), result.getErrorCode(), result.getErrorMsg());
            } else {
                try {
                    Object obj = result != null && result.getContent() != null ? JSON.parseObject(JSON.toJSONString(result.getContent()), type, new Feature[0]) : null;
                    return obj;
                } catch (Exception var6) {
                    Exception ex = var6;
                    throw new FeignClientException(response.status(), "feignError", "我调feign凉凉了", ex);
                }
            }
        }
    }
}
