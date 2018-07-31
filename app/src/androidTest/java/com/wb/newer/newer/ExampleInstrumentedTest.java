package com.wb.newer.newer;

import android.content.Context;
import android.os.Environment;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.wb.newer.newer.http.HttpUtils;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.wb.newer.newer", appContext.getPackageName());
    }

    @Test
    public void getWxcode() {
        WxRequest request = new WxRequest();
        final String scene = "F0001";

//        {"page": "pages/index/index", "auto_color": false, "scene": "F0005", "line_color": {"r":"0","g":"0","b":"0"}, "is_hyaline": false, "width": 1080}
        request.setPage( "pages/index/index");
        request.setAuto_color(false);
        request.setScene(scene);
        WxRequest.LineColorBean bean = new WxRequest.LineColorBean();
        bean.setB("0");
        bean.setG("0");
        bean.setR("0");
        request.setLine_color(bean);
        request.setIs_hyaline(false);
        request.setWidth(2560);



        HttpUtils.INSTANCE.getRetrofit().create(wxCode.class)
                .getWxCode(request)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(ResponseBody file) {
                        Log.e("tag",file.byteStream().toString());
                        copyFile(file.byteStream(),Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)+"/test.png");
                    }


                    @Override
                    public void onError(Throwable e) {
                        Log.e("tag",e.getMessage());

                    }
                });
    }

    public interface wxCode {

        @POST("https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=12_y_TyLW-vezu8Oy7yDioWu2BIADsVVQngIjlVtdTw0AMGnqcDthKtiSLtz9-1p_HpJrN-PKfTuw-WTLkC5R2_4xe1HbMofBzBHupBZKf1SNArJXYHZdyJ9OHJA4vIsu4MjBJnorWVrj2RrYIvGJQbABABEL")
        Single<ResponseBody> getWxCode(@Body WxRequest request);

    }

    static class WxRequest {

        /**
         * page : pages/index/index
         * auto_color : false
         * scene : F0005
         * line_color : {"r":"0","g":"0","b":"0"}
         * is_hyaline : false
         * width : 1080
         */

        private String page;
        private boolean auto_color;
        private String scene;
        private LineColorBean line_color;
        private boolean is_hyaline;
        private int width;

        public String getPage() {
            return page;
        }

        public void setPage(String page) {
            this.page = page;
        }

        public boolean isAuto_color() {
            return auto_color;
        }

        public void setAuto_color(boolean auto_color) {
            this.auto_color = auto_color;
        }

        public String getScene() {
            return scene;
        }

        public void setScene(String scene) {
            this.scene = scene;
        }

        public LineColorBean getLine_color() {
            return line_color;
        }

        public void setLine_color(LineColorBean line_color) {
            this.line_color = line_color;
        }

        public boolean isIs_hyaline() {
            return is_hyaline;
        }

        public void setIs_hyaline(boolean is_hyaline) {
            this.is_hyaline = is_hyaline;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public static class LineColorBean {
            /**
             * r : 0
             * g : 0
             * b : 0
             */

            private String r;
            private String g;
            private String b;

            public String getR() {
                return r;
            }

            public void setR(String r) {
                this.r = r;
            }

            public String getG() {
                return g;
            }

            public void setG(String g) {
                this.g = g;
            }

            public String getB() {
                return b;
            }

            public void setB(String b) {
                this.b = b;
            }
        }
    }

    public void copyFile(InputStream inStream, final String newPath) {
        try {
            int bytesum = 0;
            int byteread = 0;
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                int length;
                while ( (byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread; //字节数 文件大小
                    System.out.println(bytesum);
                    fs.write(buffer, 0, byteread);
                }


                inStream.close();
        }
        catch (Exception e) {
            e.printStackTrace();

        }

    }
}
