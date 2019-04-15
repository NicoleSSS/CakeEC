package com.mumu.cake.net.interceptors;

import com.mumu.cake.util.file.FileUtil;

import java.io.IOException;

import androidx.annotation.RawRes;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * @ClassName: DebugInterceptor
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/3/12 9:25
 * @Version: 1.0
 */
public class DebugInterceptor extends BaseInterceptor{

    private final String DEBUG_URL;
    private final int DEBUG_RAE_ID;

    public DebugInterceptor(String debugUrl, int rawId) {
        this.DEBUG_URL = debugUrl;
        this.DEBUG_RAE_ID = rawId;
    }

    private Response getResponse(Chain chain, String json){
        return new Response.Builder()
                .code(200)
                .addHeader("Content-Type", "application/json")
                .body(ResponseBody.create(MediaType.parse("application/json"), json))
                .message("OK")
                .request(chain.request())
                .protocol(Protocol.HTTP_1_1)
                .build();
    }

    private Response debugResponse(Chain chain, @RawRes int rawId){
        final String json = FileUtil.getRawFile(rawId);
        return getResponse(chain, json);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        final String url = chain.request().url().toString();
        if(url.contains(DEBUG_URL)){
            return debugResponse(chain, DEBUG_RAE_ID);
        }
        return chain.proceed(chain.request());
    }
}
